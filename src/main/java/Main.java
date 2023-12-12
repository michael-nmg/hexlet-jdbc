/**
 * regionID,constellationID,solarSystemID,solarSystemName,x,y,z,xMin,xMax,yMin,yMax,zMin,zMax,luminosity,border,fringe,corridor,hub,international,regional,constellation,security,factionID,radius,sunTypeID,securityClass
 * 10000001,20000001,30000001,Tanoo,-88510792599980608.0000000000,42369443966878896.0000000000,-44513525346479696.0000000000,-88511903148490592.0000000000,-88509256471760608.0000000000,42369297927489104.0000000000,42369645749263904.0000000000,44513036355892800.0000000000,44514111689782896.0000000000,0.0157500000,1,0,0,1,1,1,None,0.8583240688,500007,1323338364984.0000000000,45041,B
 */

import java.util.List;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.DriverManager;

public class Main {

    private static final String PATH = "./src/main/resources/mapSolarSystems.csv";
    private static final Path FILE_PATH = Paths.get(PATH).normalize();
    private static final String REGION = "10000002"; // The Forge
    private static final List<String> PARAMS = List.of("radius", "region", "security", "solarSystemName");


    public static void main(String[] args) {
        var celestials = FileUtils.getCelestialsOfRegion(FILE_PATH, REGION);
        var samples = FileUtils.getSamples(celestials);

        try (var connection = DriverManager.getConnection("jdbc:h2:mem:celestial_test")) {
            CelestialDAO dao = new CelestialDAO(connection);
            var sqlCreated = SqlUtils.sqlCreateTable(PARAMS);
            System.out.println(sqlCreated);

            try (var statement = connection.createStatement()) {
                statement.execute(sqlCreated);
            }

            dao.saveAll(samples);

            var firstFive = dao.getFirstFive();
            firstFive.forEach(System.out::println);
//            firstFive.forEach(x -> System.out.println(x.getAllValues()));

            dao.deleteWhereSmallRadius();
            System.out.println("===============================================");

            var result = dao.getAllOrderByRadius();
            result.forEach(System.out::println);

        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
