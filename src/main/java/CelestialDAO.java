import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import java.sql.ResultSet;
import java.sql.Connection;

import java.sql.SQLException;

public final class CelestialDAO {
    private Connection connection;
    private static final List<String> PARAMS = List.of("radius", "region", "security", "solarSystemName");

    public CelestialDAO(Connection connection) {
        this.connection = connection;
    }

    public void saveAll(List<Celestial> celestials) throws SQLException {
        var sqlInsert = SqlUtils.sqlInsert(PARAMS);
        System.out.println(sqlInsert);

        try (var preparedStatement = connection.prepareStatement(sqlInsert)) {
            for (var celestial : celestials) {
                preparedStatement.setString(1, celestial.getRadius());
                preparedStatement.setString(2, celestial.getRegionID());
                preparedStatement.setString(3, celestial.getSecurity());
                preparedStatement.setString(4, celestial.getSolarSystemName());
                preparedStatement.executeUpdate();
            }
        }
    }

    public Optional<Celestial> find(Long id) throws SQLException {
        var sql = "SELECT * FROM celestials WHERE id = ?";
        try (var statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                var name = resultSet.getString("solarSystemName");
                var radius = resultSet.getString("radius");
                var region = resultSet.getString("region");
                var security = resultSet.getString("security");
                var celestial = new CelestialBuilder()
                        .setId(id)
                        .setSolarSystemName(name)
                        .setRadius(radius)
                        .setRegionID(region)
                        .setSecurity(security)
                        .build();
                return Optional.of(celestial);
            }
            return Optional.empty();
        }
    }

    // BEGIN (write your solution here)
    public void deleteWhereSmallRadius() throws SQLException {
        var sqlDelete = "DELETE FROM celestials WHERE radius LIKE '1%'";
        try (var preparedStatement = connection.prepareStatement(sqlDelete)) {
            preparedStatement.executeUpdate();
        }
    }


    public List<Celestial> getFirstFive() throws SQLException {
        var sqlSelection = "SELECT * FROM celestials ORDER BY radius DESC LIMIT 5";
        try (var statementSelect = connection.createStatement()) {
            List<Celestial> result = new ArrayList<>();
            var resultSet = statementSelect.executeQuery(sqlSelection);
            while (resultSet.next()) {
                var id = resultSet.getLong("id");
                var radius = resultSet.getString("radius");
                var region = resultSet.getString("region");
                var name = resultSet.getString("solarSystemName");
                var security = resultSet.getString("security");
                var celestial = new CelestialBuilder()
                        .setId(id)
                        .setRadius(radius)
                        .setRegionID(region)
                        .setSolarSystemName(name)
                        .setSecurity(security)
                        .build();
                result.add(celestial);
            }
            return result;
        }
    }

    public List<Celestial> getAllOrderByRadius() throws SQLException {
        var sql = "SELECT * FROM celestials ORDER BY radius DESC";
        try (var statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            List<Celestial> list = new ArrayList<>();
            while (resultSet.next()) {
                var id = resultSet.getLong("id");
                var name = resultSet.getString("solarSystemName");
                var radius = resultSet.getString("radius");
                var region = resultSet.getString("region");
                var security = resultSet.getString("security");
                var celestial = new CelestialBuilder()
                        .setId(id)
                        .setSolarSystemName(name)
                        .setRadius(radius)
                        .setRegionID(region)
                        .setSecurity(security)
                        .build();
                list.add(celestial);
            }
            return list;
        }
    }
}

