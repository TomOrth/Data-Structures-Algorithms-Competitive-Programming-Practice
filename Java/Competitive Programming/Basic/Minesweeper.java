import java.util.Scanner;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

/**
 * Given the size (m*n) and the field where * are bombs and . are empty spots, build a minesweeper field with the number of adjacent bombs and the bombs revealed
 */
public class Minesweeper {

    /**
     * Class to represent an Entry in a 2D array that can be enqueued to a Queue
     */
    static class Entry {
        public int r;
        public int c;
        
        public Entry(int r, int c) {
            this.r = r;
            this.c = c;
        }

    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<char[][]> fields = new ArrayList<>();
        int i = 0, j = 0, fieldNum = 1;
        boolean run = true;
        while (run) {
            i = scan.nextInt();
            j = scan.nextInt();
            if (i == 0 && j == 0) {
                run = false;
            }
            else {
                fields.add(buildField(scan, i, j));
            }
        }
        System.out.println("");
        for (int x = 0; x < fields.size(); ++x) {
            char field[][] = fields.get(x);
            printField(field.length, field[0].length, field, x+1);
        }
    }

    /**
     * Builds a given field
     * @param scan Scanner to read input
     * @param i The limit for the rows in the field
     * @param j The limit for the columns in the field
     * @return The newly generated field 
     */
    public static char[][] buildField(Scanner scan, int i, int j) {
        char field[][] = new char[i][j];
        for (int r = 0; r < i; ++r) {
            String temp = scan.next();
            for (int c = 0; c < j; ++c) {
                field[r][c] = temp.charAt(c);
            }
        }
        char fieldOut[][] = new char[i][j];
        for (int x = 0; x < i; ++x) {
            Arrays.fill(fieldOut[x], '0');
        }
        bfs(i, j, field, fieldOut);        
        return fieldOut;
    }

    /**
     * Prints a field
     * @param i The limit for the rows in the field
     * @param j The limit for the columns in the field
     * @param field The field to print
     * @param fieldNum The field number
     */
    public static void printField(int i, int j, char[][] field, int fieldNum) {
        System.out.println("Field " + fieldNum);
        for (int r = 0; r < i; ++r) {
            for (int c = 0; c < j; ++c) {
                System.out.print(field[r][c]);
            }
            System.out.println("");
        }
        System.out.println("");
    }

    /**
     * A Breadth First Search algorithm to calculate the adjacent bomb values
     * @param i The limit for the rows in the field
     * @param j The limit for the columns in the field
     * @param field The input field of *'s and .'s
     * @param fieldOut The output field of *'s and numbers
     */
    public static void bfs(int i, int j, char[][] field, char[][] fieldOut) {
        boolean visited[][] = new boolean[i][j];
        Queue<Entry> q = new LinkedList<>();
        q.add(new Entry(0, 0));
        while (q.peek() != null) {
            Entry p = q.poll();
            if (!visited[p.r][p.c]) {
                visited[p.r][p.c] = true;
                if (field[p.r][p.c] == '*') {
                    fieldOut[p.r][p.c] = '*';
                    int tempR = p.r, tempC = p.c;
                    if (tempR+1 >= i) {
                        tempR = 0;
                        tempC += 1;
                    } else {
                        tempR += 1;
                    } 
                    if (tempC < j) {
                        q.add(new Entry(tempR, tempC));
                    }
                } else {
                    addBombCount(i, j, p.r, p.c, p.r, p.c, field, fieldOut, q);
                    addBombCount(i, j, p.r, p.c, p.r+1, p.c, field, fieldOut, q);
                    addBombCount(i, j, p.r, p.c, p.r-1, p.c, field, fieldOut, q);
                    addBombCount(i, j, p.r, p.c, p.r, p.c+1, field, fieldOut, q);
                    addBombCount(i, j, p.r, p.c, p.r, p.c-1, field, fieldOut, q);
                    addBombCount(i, j, p.r, p.c, p.r-1, p.c-1, field, fieldOut, q);
                    addBombCount(i, j, p.r, p.c, p.r+1, p.c-1, field, fieldOut, q);
                    addBombCount(i, j, p.r, p.c, p.r-1, p.c+1, field, fieldOut, q);
                    addBombCount(i, j, p.r, p.c, p.r+1, p.c+1, field, fieldOut, q);
                }
            }
        }
    }

    /**
     * Adds up the bomb count for the adjacent values
     */
    public static void addBombCount(int i, int j, int r, int c, int nR, int nC, char[][] field, char[][] fieldOut, Queue<Entry> q) {
        if ((nR >= 0 && nR < i && nC >= 0 && nC < j)) {
            if (field[nR][nC] == '*') { 
                int value = fieldOut[r][c] - '0';
                fieldOut[r][c] = (char)(++value + '0');
            }
            q.add(new Entry(nR, nC));
        }
    }
}