/**
 * A simple 3-tuple class for containing data in the Divide and Conquer "Max Sub Array" algorithm. The names of the
 * variables are not necessarily 'correct', as the 3-tuple is used in multiple cases.
 *
 * @author Joseph Brooksbank
 */
public class SubArrayData {

    private long left, right, sum;

    SubArrayData(long left, long right, long sum) {
        this.left = left;
        this.right = right;
        this.sum = sum;
    }

    long getLeft() { return left; }

    long getRight() {
        return right;
    }

    long getSum() {
        return sum;
    }

    @Override
    public String toString() {
        return "SubArrayData{" +
                "Left Index=" + left +
                ", Right index=" + right +
                ", Value=" + sum +
                '}';
    }
}
