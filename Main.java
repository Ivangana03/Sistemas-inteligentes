import java.util.*;
import java.util.List;
import java.util.Set;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) {
    Laberinto newLaberinto = new Laberinto(0.3);
    Node res = AStarTree.aStar(newLaberinto.matriz, newLaberinto.estadoInicial,
        newLaberinto.estadoObjetivo);
    if (res instanceof Node) {
      System.out.println("row: " + res.getRow() + " col: " + res.getCol());
      System.out.println("G: " + res.getG());
      System.out.println("H: " + res.getH());
      System.out.println("F: " + res.getF());
      System.out.println("row: " + res.getParent().getRow() + " col: " +
          res.getParent().getCol());
      System.out.println("G: " + res.getParent().getG());
      System.out.println("H: " + res.getParent().getH());
      System.out.println("F: " + res.getParent().getF());

    } else {
      System.out.println("No solution");
    }
    newLaberinto.imprimirMatriz();

  }
}
