import java.util.ArrayList;

class KadanesAlgorithm {

    /**
     * Kadane's Algorithm as found in the class notes, modified to act as if the arrays are circular.
     *
     * @param A The array of which to find the max subarray
     * @return The value of the max subarray
     */
    long maxSum(ArrayList<Long> A) {

        // In addition to maintaining the max sum so far, I also store the min sum so far and the total
        long total = 0;
        long minSumSoFar = 0;
        long minSumTok = 0;

        long maxSumSoFar = 0;
        long maxSumTok = 0;

        for (Long k : A) {
            // Updating total
            total = total + k;
            maxSumTok = maxSumTok + k;
            // Updating max sum
            if (maxSumTok < 0)
                maxSumTok = 0;
            if (maxSumSoFar < maxSumTok)
                maxSumSoFar = maxSumTok;
            // Updating min sum
            minSumTok = minSumTok + k;
            if (minSumTok > 0)
                minSumTok = 0;
            if (minSumSoFar > minSumTok)
                minSumSoFar = minSumTok;
        }

        // Comparing noncircular sum to circular sum
        if (maxSumSoFar > (total - minSumSoFar))
            return maxSumSoFar;
        else return (total - minSumSoFar);
    }
}
