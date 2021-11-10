package com.awards.service;

import java.util.logging.Logger;

public class ExtraPointsStrategy implements PointsCalculationStrategy {

    private Logger log = Logger.getLogger("TotalPointsStrategy");

    @Override
    public Integer calculatePoint(float floatValue) {
        Integer value = (int) floatValue;
        if (value > 200) {
            return (value - 200)*3 + 250;
        } else {
            if (value > 100) {
                log.info("(" + value + " - 100)*2 + 50");
                return (value - 100)*2 + 50;
            } else if (value > 50) {
                log.info(value + " - 50");
                return value - 50;
            }
        }
        return 0;
    }
}
