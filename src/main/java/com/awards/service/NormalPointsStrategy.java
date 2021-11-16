package com.awards.service;

import java.util.logging.Logger;

public class NormalPointsStrategy implements PointsCalculationStrategy {

    private Logger log = Logger.getLogger("TotalPointsStrategy");
    private static final Integer N100 = 100;
    private static final Integer N50 = 50;

    @Override
    public Integer calculatePoint(float floatValue) {
        Integer value = (int) floatValue;
        if (value > N100) {
            log.info("(" + value + " - 100)*2 + 50");
            return (value - N100) * 2 + N50;
        } else if (value > N50) {
            log.info(value + " - 50");
            return value - N50;
        }
        return 0;
    }
}
