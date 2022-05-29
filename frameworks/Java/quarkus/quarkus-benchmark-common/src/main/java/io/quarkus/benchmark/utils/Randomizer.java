package io.quarkus.benchmark.utils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * The rules of the benchmark frequently require randomly generated numbers
 * in the range from 1 to 10000.
 * Often multiple numbers are needed, and in this case we need to avoid duplicates
 * because otherwise the ORM optimisations will invalidate our operations
 * (Hibernate ORM will skip unnecessary operations but this is specifically disallowed,
 * and it's not possible to disable this behaviour in ORM as it's an intrinsic
 * aspect of correctness of an ORM).
 * Because of this twist in the rules, we're better off writing a custom helper
 * than making vanilla use of the Java platform randomizer.
 */
public final class Randomizer {
    static final short MIN_OF_RANGE = 1;
    static final short MAX_OF_RANGE = 10000;
    static final short RANGE_SPACE = MAX_OF_RANGE - MIN_OF_RANGE + 1;
    private static final LocalRandom LOCAL_RANDOM = new LocalRandom() {
        @Override
        public Integer getNextRandom() {
            return ThreadLocalRandom.current().nextInt(MIN_OF_RANGE, RANGE_SPACE);
        }

        @Override
        public Integer getNextRandomExcluding(int exclusion) {
            int result;
            do {
                result = getNextRandom();
            } while (result == exclusion);
            return result;
        }
    };

    public static LocalRandom current() {
        return LOCAL_RANDOM;
    }
}
