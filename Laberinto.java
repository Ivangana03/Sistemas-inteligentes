import java.util.Random;
import java.util.Arrays;

public class Laberinto {
  static final int numfila = 6;
  static final int numcolumna = 8;
  public boolean[][] matriz;
  public int[] estadoInicial;
  public int[] estadoObjetivo;

  public Laberinto(double fraccionObstaculos) {
    matriz = new boolean[numfila][numcolumna];
    generarObstaculos(fraccionObstaculos);
    generarEstadosInicialYObjetivo();
  }

  private void generarObstaculos(double fraccionObstaculos) {
    int numObstaculos = (int) (numfila * numcolumna * fraccionObstaculos);
    Random rand = new Random();
    while (numObstaculos > 0) {
      int fila = rand.nextInt(numfila);
      int columna = rand.nextInt(numcolumna);
      if (!matriz[fila][columna]) {
        matriz[fila][columna] = true;
        numObstaculos--;
      }
    }
  }

  private void generarEstadosInicialYObjetivo() {
    Random rand = new Random();
    estadoInicial = generarEstadoAleatorio(rand);
    estadoObjetivo = generarEstadoAleatorio(rand);
    while (Arrays.equals(estadoInicial, estadoObjetivo) || matriz[estadoInicial[0]][estadoInicial[1]]
        || matriz[estadoObjetivo[0]][estadoObjetivo[1]]) {
      estadoInicial = generarEstadoAleatorio(rand);
      estadoObjetivo = generarEstadoAleatorio(rand);
    }
  }

  private int[] generarEstadoAleatorio(Random rand) {
    int fila = rand.nextInt(numfila);
    int columna = rand.nextInt(numcolumna);
    return new int[] { fila, columna };
  }

  public void imprimirMatriz() {
    // Imprimir matriz en la consola
    for (int fila = 0; fila < numfila; fila++) {
      for (int columna = 0; columna < numcolumna; columna++) {
        int[] estadoActual = { fila, columna };
        if (this.matriz[fila][columna]) {
          System.out.print("[*]");

        } else {
          if (Arrays.equals(this.estadoInicial, estadoActual)) {
            System.out.print("[I]");
          } else if (Arrays.equals(this.estadoObjetivo, estadoActual)) {
            System.out.print("[F]");
          } else {

            System.out.print("[ ]");
          }

        }
      }
      System.out.println();
    }

  }

  // métodos adicionales...
}
