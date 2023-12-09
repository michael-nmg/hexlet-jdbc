/**
 * regionID,constellationID,solarSystemID,solarSystemName,x,y,z,xMin,xMax,yMin,yMax,zMin,zMax,luminosity,border,fringe,corridor,hub,international,regional,constellation,security,factionID,radius,sunTypeID,securityClass
 * 10000001,20000001,30000001,Tanoo,-88510792599980608.0000000000,42369443966878896.0000000000,-44513525346479696.0000000000,-88511903148490592.0000000000,-88509256471760608.0000000000,42369297927489104.0000000000,42369645749263904.0000000000,44513036355892800.0000000000,44514111689782896.0000000000,0.0157500000,1,0,0,1,1,1,None,0.8583240688,500007,1323338364984.0000000000,45041,B
 */

import java.util.Arrays;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.DriverManager;

import java.sql.SQLException;

public class Main {

    private static final String PATH = "./src/main/resources/mapSolarSystems.csv";
    private static final Path FILE_PATH = Paths.get(PATH).normalize();
    private static final String REGION = "10000002"; // The Forge
    private static final String PARAM_1 = "security";
    private static final String PARAM_2 = "radius";
    private static final String PARAM_3 = "solarSystemName";

    public static void main(String[] args) throws SQLException {
        var titles = FileUtils.getTitles(FILE_PATH);
        var celestials = FileUtils.getCelestialsOfRegion(FILE_PATH, titles, REGION);
        var samples = FileUtils.getSamples(celestials, PARAM_1, PARAM_2, PARAM_3);

        try (var connection = DriverManager.getConnection("jdbc:h2:mem:hexlet_test")) {

            var sqlCreated = SqlUtils.sqlCreateTable(titles);
            System.out.println(sqlCreated);

            try (var statement = connection.createStatement()) {
                statement.execute(sqlCreated);
            }

            ///////////////////////////////////////////////////////

            var values = SqlUtils.insertingValues(samples, titles);
            var sqlSamples = SqlUtils.sqlInsert(titles, values);
            System.out.println(sqlSamples);

            try (var statementInsert = connection.createStatement()) {
                statementInsert.executeUpdate(sqlSamples.toString());
            }

            ///////////////////////////////////////////////////////

            var sql3 = "SELECT * FROM celestials";

            try (var statementSelect = connection.createStatement()) {
                var resultSet = statementSelect.executeQuery(sql3);
                while (resultSet.next()) {
                    System.out.format("%s - %s - %s%n",
                            resultSet.getString("radius"),
                            resultSet.getString("solarSystemName"),
                            resultSet.getString("security"));
                }
            }
        } catch (Exception e) {
            System.out.println(Arrays.stream(e.getStackTrace()).toList());
        }
    }
}
