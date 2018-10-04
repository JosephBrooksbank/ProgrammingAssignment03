import java.util.ArrayList;

/**
 * Class for everything involved with the Divide and Conquer solution of Maximum Subarrays, given that the array
 * is circular.
 *
 * @author Joseph Brooksbank
 */
class DivideAndConquer {

   private long total;
    /**
     * Method to find the maximum subarray crossing between two halves, as described in section 4.1 of the textbook.
     *
     * @param A    The array which is being searched
     * @param low  The smaller index of the area to search
     * @param mid  The midpoint of the area to search
     * @param high The larger index of the area to search
     * @return The maximum value subarray which crosses over index mid.
     */
    private SubArrayData findMaxCrossingArray(ArrayList<Long> A, int low, int mid, int high) {

        long maxLeft = 0;
        long maxRight = 0;

        long leftSum = Long.MIN_VALUE;
        long sum = 0;
        for (int i = mid; i >= low; i--) {
            sum = sum + A.get(i);

            // Special case: All values of the array are Long.MIN_VALUE
            if (sum == Long.MIN_VALUE && leftSum == Long.MIN_VALUE) {
                maxLeft = i;
            }

            if (sum > leftSum) {
                leftSum = sum;
                maxLeft = i;
            }
        }
        long rightSum = Long.MIN_VALUE;
        sum = 0;
        for (int j = mid + 1; j <= high; j++) {
            sum = sum + A.get(j);

            // Special case: All values of the array are Long.MIN_VALUE
            if (sum == Long.MIN_VALUE && rightSum == Long.MIN_VALUE) {
                maxRight = j;
            }

            if (sum > rightSum) {
                rightSum = sum;
                maxRight = j;
            }
        }
        return new SubArrayData(maxLeft, maxRight, leftSum + rightSum);

    }

    /**
     *Finding the smallest Mininum sub array using Kadane's Algorithm to preserve efficiency (2n log n = n log n )
     *
     * @param A    The array of which to find the minimum subarray
     * @param low  The small index of the array to find
     * @param high The high index of the array to find
     * @return The smallest continuous subarray
     */
    private SubArrayData findMinSubArray(ArrayList<Long> A, int low, int high) {
        long lowReturn = 0;
        long highReturn = 0;
        long minSumSoFar = 0;
        long minSumTok = 0;

        for (int k = low; k <= high; k++) {
            // Updating min sum
            minSumTok = minSumTok + A.get(k);
            if (minSumTok > 0) {
                minSumTok = 0;
                lowReturn = k + 1;
            }
            if (minSumSoFar > minSumTok)
                minSumSoFar = minSumTok;
                highReturn = k;
        }
        return new SubArrayData(lowReturn, highReturn, minSumSoFar);
    }

    /**
     * Method to find the largest subarray which wraps circularly
     *
     * @param A    The array of which to search
     * @param low  The smaller index
     * @param high The larger index
     * @return The highest value subarray which wraps around
     */
    private SubArrayData findMaxCircularArray(ArrayList<Long> A, int low, int high) {
        // Finding the minimum subarray
        SubArrayData minimumArray = findMinSubArray(A, low, high);

        // The "Maximum Circular" subarray is the values on either side of the minimum subarray --
        // Here I am moving the indexes to contain the values outside of the minimum array rather than inside it.
        // Don't have to worry about what happens when the minimum subarray is at the end of an array, because in that
        // case the sum is equal to the left or right sum, which returns before this does.
        long leftIndex = minimumArray.getRight() + 1;
        long rightIndex = minimumArray.getLeft() - 1;
        return new SubArrayData(leftIndex, rightIndex, total - minimumArray.getSum());

    }

    /**
     * Method to find the maximum subarray of a circular array, as specified in section 4.1 of the textbook.
     *
     * @param A    The array to search
     * @param low  The low bound of the array to search
     * @param high The high bound of the array to search
     * @return The value and indices of the maximum subarray of array A.
     */
    private SubArrayData findMaxSubArray(ArrayList<Long> A, int low, int high) {
        int mid = (low + high) / 2;
        if (high == low) {
            return new SubArrayData(low, high, A.get(high));
        } else {

            SubArrayData leftSide = findMaxSubArray(A, low, mid);
            SubArrayData rightSide = findMaxSubArray(A, mid + 1, high);
            // Addition to calculate the max array which includes a wrap
            SubArrayData maxCircular = findMaxCircularArray(A, low, high);
            SubArrayData crossing = findMaxCrossingArray(A, low, mid, high);

            // Getting sums out of custom 3-tuple
            long leftSum = leftSide.getSum();
            long rightSum = rightSide.getSum();
            long crossingSum = crossing.getSum();
            long circularSum = maxCircular.getSum();

            // Comparing all 4 sums
            if (leftSum >= rightSum && leftSum >= crossingSum && leftSum >= circularSum)
                return leftSide;
            else if (rightSum >= leftSum && rightSum >= crossingSum && rightSum >= circularSum)
                return rightSide;
            else if (circularSum >= leftSum && circularSum >= rightSum && circularSum >= crossingSum)
                return maxCircular;
            else
                return crossing;
        }
    }

    /***
     * Helper Function to start recursive loop
     * @param A     Array to find maximum subsection of
     * @return      lower and upper indices and value of max subarray
     */
    SubArrayData findMaxSubArray(ArrayList<Long> A){
        // Calculating total once
        total = 0;
        for (Long k : A) {
            total += k;
        }

        return findMaxSubArray(A, 0, A.size() -1);
    }
}
