import com.qqwing.*;
import org.jgap.*;

public class Main {
  public static void main(String[] args) {
    System.out.println("Hello World");
    QQWing test = new QQWing();
    test.generatePuzzle();
    // test.setPrintStyle(PrintStyle.ONE_LINE);
    test.printPuzzle();
    test.solve();
    test.printSolution();

  }

  public static void test(IChromosome a_potentialSolution) {
    System.out.println("Hello World");
  }
}
