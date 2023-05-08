import com.qqwing.Difficulty;

public class Main {
  public static void main(String[] args) {
    System.out.println("Hello World");
    Difficulty d = Difficulty.EASY;
    int[] sudoku = sudoku.computePuzzleByDifficulty(d);
    int[][] matrix = sudoku.arrayToMatrix(sudoku);
    sudoku.printMatrix(matrix);
  }


}
