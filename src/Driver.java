import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main Class for Programming Assignment 03
 *
 * @author Joseph Brooksbank
 * @version 10/3/2018
 */
public class Driver {

    // A static number of tests
    private static final int ITER = 500;

    public static void main(String[] args) throws FileNotFoundException {

        // Getting array from file
        Scanner stdin = new Scanner(new FileInputStream(args[0]));
        ArrayList<Long> inputData = new ArrayList<>();
        while (stdin.hasNext()) {
            inputData.add(stdin.nextLong());
        }

        DivideAndConquer divideAndConquer = new DivideAndConquer();
        KadanesAlgorithm kadanesAlgorithm = new KadanesAlgorithm();
        long divideSum = 0;
        long kadanesSum = 0;

        // Timing for recursive method
        CpuTimer divideTimer = new CpuTimer();
        for (int i = 0; i <= ITER; i++) {
            divideSum = divideAndConquer.findMaxSubArray(inputData).getSum();
        }
        double CpuTime = divideTimer.getElapsedCpuTime();
        double divideAvgTime = CpuTime / ITER;

        // Timing for linear method
        CpuTimer kadanesTimer = new CpuTimer();
        for (int i = 0; i <= ITER; i++) {
            kadanesSum = kadanesAlgorithm.maxSum(inputData);
        }
        CpuTime = kadanesTimer.getElapsedCpuTime();
        double kadanesAvgTime = CpuTime / ITER;

        // Printing to stdout
        System.out.println(inputData.size() + ", \"R\", " + divideAvgTime + ", " + divideSum);
        System.out.println(inputData.size() + ", \"K\", " + kadanesAvgTime + ", " + kadanesSum);

    }
}
