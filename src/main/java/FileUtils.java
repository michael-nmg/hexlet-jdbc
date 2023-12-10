import java.nio.file.Path;
import java.nio.file.Files;
import java.io.BufferedReader;

import java.util.Map;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

public class FileUtils {

    public static List<String> getTitles(Path filepath) {
        List<String> titles = new ArrayList<>();
        try (BufferedReader buffer = Files.newBufferedReader(filepath)) {
            titles = Arrays.asList(buffer.readLine().split(","));
        } catch (Exception exception) {
            exception.getStackTrace();
        }
        return titles;
    }

    public static List<Map<String, String>> getCelestialsOfRegion(Path filepath, List<String> titles, String region) {
        List<Map<String, String>> celestials = new ArrayList<>();
        try (BufferedReader buffer = Files.newBufferedReader(filepath)) {
            celestials = buffer.lines()
                    .filter(line -> line.startsWith(region))
                    .map(line -> line.split(","))
                    .map(arr -> IntStream
                            .range(0, titles.size())
                            .boxed()
                            .collect(Collectors.toMap(
                                    titles::get,
                                    index -> arr[index])))
                    .toList();
        } catch (Exception exception) {
            exception.getStackTrace();
        }
        return celestials;
    }

    public static List<Celestial> getCelestialsOfRegion(Path filepath, String region) {
        List<Celestial> celestials = new ArrayList<>();

        try (BufferedReader buffer = Files.newBufferedReader(filepath)) {
            celestials = buffer.lines()
                    .filter(line -> line.startsWith(region))
                    .map(line -> line.split(","))
                    .map(arr -> {
                        CelestialBuilder builder = new CelestialBuilder();
                        builder.setRegionID(arr[0]);
                        builder.setRadius(arr[23]);
                        builder.setSolarSystemName(arr[3]);
                        builder.setSecurity(arr[21]);
                        return builder.build();
                    })
                    .toList();
        } catch (Exception exception) {
            exception.getStackTrace();
        }

        return celestials;
    }

    public static List<Map<String, String>> getSamples(
            List<Map<String, String>> celestials,
            String param1,
            String param2,
            String param3) {
        return celestials.stream()
                .filter(celestial -> 0.5 <= Double.parseDouble(celestial.get(param1))
                        && Double.parseDouble(celestial.get(param1)) < 0.6)
                .sorted(Comparator.comparing(x -> Double.parseDouble(x.get(param2))))
                .peek(sstm -> System.out.format("%s - %s - %s%n",
                        sstm.get(param2),
                        sstm.get(param3),
                        sstm.get(param1)))
                .toList();
    }

    public static List<Celestial> getSamples(List<Celestial> celestials) {
        return celestials.stream()
                .filter(celestial -> 0.5 <= Double.parseDouble(celestial.getSecurity())
                        && Double.parseDouble(celestial.getSecurity()) < 0.6)
                .sorted(Comparator.comparing(x -> Double.parseDouble(x.getRadius())))
                .peek(sstm -> System.out.format("%s - %s - %s%n",
                        sstm.getRadius(),
                        sstm.getSolarSystemName(),
                        sstm.getSecurity()))
                .toList();
    }

}
