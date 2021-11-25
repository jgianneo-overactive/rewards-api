package com.awards.service;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class PointStrategy {
    private static final Logger log = Logger.getLogger("PointsStrategy class");
    private static final Integer N200 = 200;
    private static final Integer N100 = 100;
    private static final Integer N50 = 50;

    static PointsCalculationStrategy extraPointStrategy = floatValue -> {
        log.info("Extra Points Strategy selected whit value " + floatValue);
        Integer intValue = floatValue.intValue();
        if (intValue > N200) {
            return (intValue - N200) * 3 + 250;
        } else {
            if (intValue > N100) {
                return (intValue - N100) * 2 + N50;
            } else if (intValue > N50) {
                return intValue - N50;
            }
        }
        return 0;
    };

    static PointsCalculationStrategy normalPointStrategy = floatValue -> {
        log.info("Normal Points Strategy selected whit value " + floatValue);
        Integer intValue = floatValue.intValue();
        if (intValue > N100) {
            return (intValue - N100) * 2 + N50;
        } else if (intValue > N50) {
            return intValue - N50;
        }
        return 0;
    };

    static PointsCalculationStrategy simplePointStrategy = floatValue -> {
        log.info("Simple Points Strategy selected whit value " + floatValue);
        Integer intValue = floatValue.intValue();
        if (intValue > N50) {
            return intValue - N50;
        }
        return 0;
    };

    static PointsCalculationStrategy noPointStrategy = floatValue -> {
        log.info("No Strategy selected whit value " + floatValue);
        return 0;
    };

    public static final List<PointsCalculationStrategy> strategy = Arrays.asList(
        noPointStrategy,
        simplePointStrategy,
        normalPointStrategy,
        extraPointStrategy
    );
}
