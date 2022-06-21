package benchmark.util;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public final class Util {
    private static final int MIN = 1;
    private static final int MAX = 10001;

    private Util() {
    }

    public static int randomInt() {
        return ThreadLocalRandom.current().nextInt(MIN, MAX);
    }

    public static IntStream randomIntStream() {
        return ThreadLocalRandom.current().ints(MIN, MAX).distinct();
    }

    public static int parseQueryCount(String textValue) {
        if (textValue == null) {
            return 1;
        }
        int parsedValue;
        try {
            parsedValue = Integer.parseInt(textValue);
        } catch (NumberFormatException e) {
            return 1;
        }
        return Math.min(500, Math.max(1, parsedValue));
    }
}
