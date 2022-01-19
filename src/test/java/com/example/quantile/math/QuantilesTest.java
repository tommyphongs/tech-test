package com.example.quantile.math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantilesTest {

    @Test
    public void testQuantile() {
        Assertions.assertEquals(Quantiles.calculate(new double[]{1,3,4,5}, 3), 1.18);
        Assertions.assertEquals(Quantiles.calculate(new double[]{1, 2, 3, 4, 5, 6, 7, 8},30), 3.1);

    }

    @Test
    public void testNegPercentile() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Quantiles.calculate(new double[]{1, 2, 3, 4, 5}, -20);
        });

        String expectedMessage = "Percentile must in range";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}