/**
 * // regionID,constellationID,solarSystemID,solarSystemName,x,y,z,xMin,xMax,yMin,yMax,zMin,zMax,luminosity,border,fringe,corridor,hub,international,regional,constellation,security,factionID,radius,sunTypeID,securityClass
 * // 10000001,20000001,30000001,Tanoo,-88510792599980608.0000000000,42369443966878896.0000000000,-44513525346479696.0000000000,-88511903148490592.0000000000,-88509256471760608.0000000000,42369297927489104.0000000000,42369645749263904.0000000000,44513036355892800.0000000000,44514111689782896.0000000000,0.0157500000,1,0,0,1,1,1,None,0.8583240688,500007,1323338364984.0000000000,45041,B
 */

import java.util.Map;
import java.util.List;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.BufferedReader;
import java.sql.DriverManager;

import java.sql.SQLException;

public class Main {

    private static final String REGION = "10000002"; // The Forge
    private static final String PATH = "./src/main/resources/mapSolarSystems.csv";
    private static final Path FILE_PATH = Paths.get(PATH).normalize();

    public static void main(String[] args) throws SQLException {

        try (var conn = DriverManager.getConnection("jdbc:h2:mem:hexlet_test")) {

            try (BufferedReader buffer = Files.newBufferedReader(FILE_PATH)) {
                List<String> titles = Arrays.asList(buffer.readLine().split(","));

                List<Map<String, String>> celestials = buffer.lines()
                        .filter(line -> line.startsWith(REGION))
                        .map(line -> line.split(","))
                        .map(arr -> IntStream
                                .range(0, titles.size())
                                .boxed()
                                .collect(Collectors.toMap(
                                        titles::get,
                                        index -> arr[index])))
                        .toList();

                String param = "security";
                List<Map<String, String>> samples = celestials.stream()
                        .filter(celestial -> 0.5 <= Double.parseDouble(celestial.get(param))
                                && Double.parseDouble(celestial.get(param)) < 0.6)
                        .sorted(Comparator.comparing(x -> Double.parseDouble(x.get("radius"))))
                        .peek(sstm -> System.out.format("%s - %s - %s%n",
                                sstm.get("radius"),
                                sstm.get("solarSystemName"),
                                sstm.get(param)))
                        .toList();

                StringBuilder sqlCreate = new StringBuilder("CREATE TABLE celestials (");
//                sqlCreate.append("id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,");
                sqlCreate.append("id BIGINT PRIMARY KEY AUTO_INCREMENT,");

                String columns = titles.stream()
                        .map(x -> x + " varchar(255)")
                        .collect(Collectors.joining(","));

                sqlCreate.append(columns);
                sqlCreate.append(")");

                System.out.println(sqlCreate.toString());

                var statement = conn.createStatement();
                statement.execute(sqlCreate.toString());
                statement.close();

                ///////////////////////////////////////////////////////

                StringBuilder sqlSamples = new StringBuilder("INSERT INTO celestials (");
                sqlSamples.append(titles.stream().collect(Collectors.joining(",")));
                sqlSamples.append(") VALUES ");

                var values = samples.stream()
                        .map(map -> "(" + titles.stream().map(title -> "'" + map.get(title) + "'").collect(Collectors.joining(",")) + ")")
                        .collect(Collectors.joining(","));
                sqlSamples.append(values);

                System.out.println(sqlSamples);

                var statement2 = conn.createStatement();
                statement2.executeUpdate(sqlSamples.toString());
                statement2.close();

                ///////////////////////////////////////////////////////

                var sql3 = "SELECT * FROM celestials";
                var statement3 = conn.createStatement();
                // Здесь вы видите указатель на набор данных в памяти СУБД
                var resultSet = statement3.executeQuery(sql3);
                // Набор данных — это итератор
                // Мы перемещаемся по нему с помощью next() и каждый раз получаем новые значения
                while (resultSet.next()) {
                    System.out.format("%s - %s - %s%n",
                            resultSet.getString("radius"),
                            resultSet.getString("solarSystemName"),
                            resultSet.getString("security"));
                }
                statement3.close();

            } catch (Exception throwable) {
                throwable.getStackTrace();
            }

        } catch (Exception e) {
            System.out.println(Arrays.stream(e.getStackTrace()).toList());
        }
    }
}
