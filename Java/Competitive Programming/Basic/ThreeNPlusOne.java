import java.util.Scanner;

/**
 * Given bounds of i and j, find the maximum cycle length
 * Cycle: given a value of n, if n is even, divide by 2, but if it is odd, multiply by 3 and add 1. Cycle terminates at n=1
 */
public class ThreeNPlusOne {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int i = scan.nextInt();
        int j = scan.nextInt();
        System.out.println(i + " " + j + " " + cycleLength(i, j));
    }

    public static int cycleLength(int i, int j) {
        int max_cycle_length = 0;
        int cycle_length = 1;
        while (i <= j) {
            int n = i;
            cycle_length = 1;
            while (n != 1) {
                n = n % 2 == 0 ? n >> 1 : (3*n) + 1;
                cycle_length++;
            }
            max_cycle_length = Math.max(max_cycle_length, cycle_length);
            ++i;
        }
        return max_cycle_length;
    }
}