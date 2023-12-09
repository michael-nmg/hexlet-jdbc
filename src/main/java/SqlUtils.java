import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SqlUtils {

    public static String sqlCreateTable(List<String> titles) {
        StringBuilder result = new StringBuilder("CREATE TABLE celestials (");
        result.append("id BIGINT PRIMARY KEY AUTO_INCREMENT,");

        String columns = titles.stream()
                .map(x -> x + " varchar(255)")
                .collect(Collectors.joining(","));

        result.append(columns);
        result.append(")");
        return result.toString();
    }

    public static String sqlInsert(List<String> titles, String values) {
        StringBuilder result = new StringBuilder("INSERT INTO celestials (");
        result.append(titles.stream().collect(Collectors.joining(",")));
        result.append(") VALUES ");
        result.append(values);
        return result.toString();
    }

    public static String insertingValues(List<Map<String, String>> samples, List<String> titles) {
        return samples.stream()
                .map(map -> "(" + titles.stream()
                        .map(title -> "'" + map.get(title) + "'")
                        .collect(Collectors.joining(",")) + ")")
                .collect(Collectors.joining(","));
    }

}
