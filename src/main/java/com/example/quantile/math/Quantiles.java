package com.example.quantile.math;

import static java.lang.Double.*;
import static java.lang.Double.POSITIVE_INFINITY;

/**
 * A Mathematics class to calculate quantiles of dataset
 */
public final class Quantiles {

    private Quantiles() {

    }

    /**
     * This function try to estimate the result same with numpy.quantile() func in python
     * This function has side effect, when it modifies the array dataset
     *
     * @param dataset:    Array of double
     * @param percentile: Percentile by % to calculate
     * @return double value that represent percentile of dataset
     */

    public static double calculate(double[] dataset, double percentile) {
        if (percentile > 100 || percentile < 0)
            throw new IllegalArgumentException("Percentile must in range [0, 100]");
        String[] spliced = Double.toString(percentile).split("\\.");
        int decimal = spliced[1].length();
        int index = (int) (Math.pow(10, decimal) * percentile);
        int s = (int) (Math.pow(10, decimal) * 100);

        long numerator = (long) index * (long) (dataset.length - 1);

        int quotient = (int) numerator / s;
        int remainder = (int) (numerator - (long) quotient * (long) s);
        shuffleInplaceArray(quotient, dataset, 0, dataset.length - 1);
        if (remainder == 0) {
            return dataset[quotient];
        } else {
            shuffleInplaceArray(quotient + 1, dataset, quotient + 1, dataset.length - 1);
            if (dataset[quotient] == NEGATIVE_INFINITY) {
                return dataset[quotient + 1] == POSITIVE_INFINITY ? NaN : NEGATIVE_INFINITY;
            } else {
                return dataset[quotient + 1] == POSITIVE_INFINITY ? POSITIVE_INFINITY : dataset[quotient]
                        + (dataset[quotient + 1] - dataset[quotient]) * remainder / s;
            }
        }
    }

    /**
     * Shuffle inplace, select the element which would appear at a given index in dataset if it were sorted.
     * @param required: is index in range [from, to]
     * @param  array:  dataset.
     * @param from: from index.
     * @param to: to index.
     * All the values with idnexes in the range[0, from] and [to, array.length-1] should be greater than or equal to all values
     * with indexes in the range [from, to]
     */

    private static void shuffleInplaceArray(int required, double[] array, int from, int to) {
        int partitionPoint;
        if (required == from) {
            partitionPoint = from;

            for (int index = from + 1; index <= to; ++index) {
                if (array[partitionPoint] > array[index]) {
                    partitionPoint = index;
                }
            }

            if (partitionPoint != from) {
                swap(array, partitionPoint, from);
            }

        } else {
            while (to > from) {
                partitionPoint = partitionArray(array, from, to);
                if (partitionPoint >= required) {
                    to = partitionPoint - 1;
                }

                if (partitionPoint <= required) {
                    from = partitionPoint + 1;
                }
            }

        }
    }

    /**
     * Performs a partition operation on the slice of array with elements in the range [from, to].
     * Uses the median of from, to, and the midpoint between them as a pivot. Returns the index which the slice is partitioned around
     */

    private static int partitionArray(double[] array, int from, int to) {
        int mid = from + to >>> 1;
        boolean toLessThanMid = array[to] < array[mid];
        boolean midLessThanFrom = array[mid] < array[from];
        boolean toLessThanFrom = array[to] < array[from];
        if (toLessThanMid == midLessThanFrom) {
            swap(array, mid, from);
        } else if (toLessThanMid != toLessThanFrom) {
            swap(array, from, to);
        }
        double pivot = array[from];
        int partitionPoint = to;

        for (int i = to; i > from; --i) {
            if (array[i] > pivot) {
                swap(array, partitionPoint, i);
                --partitionPoint;
            }
        }

        swap(array, from, partitionPoint);
        return partitionPoint;
    }

    private static void swap(double[] array, int i, int j) {
        double temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
