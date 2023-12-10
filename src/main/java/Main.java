/**
 * regionID,constellationID,solarSystemID,solarSystemName,x,y,z,xMin,xMax,yMin,yMax,zMin,zMax,luminosity,border,fringe,corridor,hub,international,regional,constellation,security,factionID,radius,sunTypeID,securityClass
 * 10000001,20000001,30000001,Tanoo,-88510792599980608.0000000000,42369443966878896.0000000000,-44513525346479696.0000000000,-88511903148490592.0000000000,-88509256471760608.0000000000,42369297927489104.0000000000,42369645749263904.0000000000,44513036355892800.0000000000,44514111689782896.0000000000,0.0157500000,1,0,0,1,1,1,None,0.8583240688,500007,1323338364984.0000000000,45041,B
 */

import java.util.Arrays;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.DriverManager;

import java.sql.SQLException;
import java.util.List;

public class Main {

    private static final String PATH = "./src/main/resources/mapSolarSystems.csv";
    private static final Path FILE_PATH = Paths.get(PATH).normalize();
    private static final String REGION = "10000002"; // The Forge
    private static final String PARAM_1 = "security";
    private static final String PARAM_2 = "radius";
    private static final String PARAM_3 = "solarSystemName";

    public static void main(String[] args) throws SQLException {
        var paramList = List.of("radius", "region", "security", "solarSystemName");
        var titles = FileUtils.getTitles(FILE_PATH);
//        var celestials1 = FileUtils.getCelestialsOfRegion(FILE_PATH, titles, REGION);
        var celestials = FileUtils.getCelestialsOfRegion(FILE_PATH, REGION);
//        var samples1 = FileUtils.getSamples(celestials1, PARAM_1, PARAM_2, PARAM_3);
        var samples = FileUtils.getSamples(celestials);

        try (var connection = DriverManager.getConnection("jdbc:h2:mem:hexlet_test")) {

            var sqlCreated = SqlUtils.sqlCreateTable(paramList);
            System.out.println(sqlCreated);

            try (var statement = connection.createStatement()) {
                statement.execute(sqlCreated);
            }

            ///////////////////////////////////////////////////////
            var sqlInsert = SqlUtils.sqlInsert(paramList);
            System.out.println(sqlInsert);

            try (var preparedStatement = connection.prepareStatement(sqlInsert)) {
                for (var celestial : samples) {
                    preparedStatement.setString(1, celestial.getRadius());
                    preparedStatement.setString(2, celestial.getRegionID());
                    preparedStatement.setString(3, celestial.getSecurity());
                    preparedStatement.setString(4, celestial.getSolarSystemName());
                    preparedStatement.executeUpdate();
                }
            }

            ///////////////////////////////////////////////////////
            var sqlSelection = "SELECT * FROM celestials ORDER BY radius DESC LIMIT 5";

            try (var statementSelect = connection.createStatement()) {
                var resultSet = statementSelect.executeQuery(sqlSelection);
                while (resultSet.next()) {
                    System.out.format("%s - %s - %s%n",
                            resultSet.getString("radius"),
                            resultSet.getString("solarSystemName"),
                            resultSet.getString("security"));
                }
            }
            ///////////////////////////////////////////////////////
            var sqlDelete = "DELETE FROM celestials WHERE radius LIKE '1%'";

            try (var preparedStatement = connection.prepareStatement(sqlDelete)) {
                preparedStatement.executeUpdate();
            }

            ///////////////////////////////////////////////////////
            System.out.println("===============================================");
            ///////////////////////////////////////////////////////
            var sqlSelection2 = "SELECT * FROM celestials ORDER BY radius DESC";

            try (var statementSelect = connection.createStatement()) {
                var resultSet = statementSelect.executeQuery(sqlSelection2);
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
