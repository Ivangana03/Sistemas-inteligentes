import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

public class AlgoritmoGenetico {
  private List<Individuo> poblacion;
  private int cantidadPoblacion;
  private int[][] sudoku;
  private Individuo solucion;

  public AlgoritmoGenetico(int[][] sudoku, int cantidadPoblacion) {
    this.sudoku = sudoku;
    this.cantidadPoblacion = cantidadPoblacion;
    this.poblacion = generarPoblacion();
  }

  public List<Individuo> getPoblacion() {
    return poblacion;
  }

  public void setPoblacion(List<Individuo> poblacion) {
    this.poblacion = poblacion;
  }

  public Individuo getSolucion() {
    return solucion;
  }

  public List<Individuo> generarPoblacion() {
    List<Individuo> poblacion = new ArrayList<Individuo>();
    for (int i = 0; i < cantidadPoblacion; i++) {
      poblacion.add(generarIndividuo(this.sudoku));
    }
    return poblacion;
  }

  // Identifica cuales son los numeros faltantes para cada fila.
  // Genera una permutación aleatoria de los dígitos de cada fila, sin
  // intercambiar las posiciones originales del sudoku
  public Individuo generarIndividuo(int[][] sudoku) {
    int[][] sudokuPermutado = new int[9][9];
    for (int i = 0; i < 9; i++) {
      List<Integer> numeros = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
      for (int j = 0; j < 9; j++) {
        if (sudoku[i][j] != 0) {
          numeros.remove((Integer) sudoku[i][j]);
        }
      }
      Collections.shuffle(numeros);
      for (int j = 0; j < 9; j++) {
        if (sudoku[i][j] == 0) {
          sudokuPermutado[i][j] = numeros.remove(0);
        } else {
          sudokuPermutado[i][j] = sudoku[i][j];
        }
      }
    }
    return new Individuo(sudokuPermutado);
  }

  public Individuo seleccionarIndividuo() {
    int sumaFitness = 0;
    for (Individuo individuo : this.poblacion) {
      sumaFitness += individuo.getFitness();
    }

    int random = (int) (Math.random() * sumaFitness); // Genera un número aleatorio entre 0 y la suma total de los

    int sumaParcial = 0;
    for (Individuo individuo : poblacion) {
      sumaParcial += individuo.getFitness();
      if (sumaParcial >= random) {
        return individuo; // Retorna el individuo en el que se detuvo el recorrido
      }
    }
    return null; // Si no se encuentra ningún individuo, retorna null
  }

  public Individuo cruzarIndividuos(Individuo individuo1, Individuo individuo2) {
    int[][] childSudoku = new int[9][9];
    int crossoverRow = (int) (Math.random() * 9); // seleccionar una fila aleatoria

    // copiar las primeras "crossoverRow" filas del primer individuo al hijo
    for (int row = 0; row < crossoverRow; row++) {
      System.arraycopy(individuo1.getGenes()[row], 0, childSudoku[row], 0, 9);
    }

    // copiar las restantes filas del segundo individuo al hijo
    for (int row = crossoverRow; row < 9; row++) {
      System.arraycopy(individuo2.getGenes()[row], 0, childSudoku[row], 0, 9);
    }

    return new Individuo(childSudoku);
  }

  public Individuo mutarIndividuo(Individuo individuo) {
    // hacer este proceso 5 veces
    int[][] nuevoSudoku = new int[9][9];
    // for (int x = 0; x < 5; x++) {
    int casilla1, casilla2, fila;
    do {
      casilla1 = (int) (Math.random() * 9); // Selecciona una casilla aleatoria de la fila
      casilla2 = (int) (Math.random() * 9); // Selecciona otra casilla aleatoria de la fila
      fila = (int) (Math.random() * 9); // Selecciona una fila aleatoria
    } while (casilla1 == casilla2 || this.sudoku[fila][casilla1] != 0 || this.sudoku[fila][casilla2] != 0);
    // Verifica que las casillas sean diferentes y que ambas estén vacías en el
    // sudoku original

    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        if (i == fila && j == casilla1) { // Intercambia las casillas seleccionadas
          nuevoSudoku[i][j] = individuo.getGenes()[i][casilla2];
        } else if (i == fila && j == casilla2) {
          nuevoSudoku[i][j] = individuo.getGenes()[i][casilla1];
        }
      }
    }
    // }
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        if (nuevoSudoku[i][j] == 0) { // Intercambia las casillas seleccionadas
          nuevoSudoku[i][j] = individuo.getGenes()[i][j];
        }
      }
    }
    return new Individuo(nuevoSudoku);
  }

  // metodo para recorrer la poblacion y buscar si algun individuo tiene un
  // fitness de 162
  public boolean buscarSolucion() {
    for (Individuo individuo : this.poblacion) {
      if (individuo.getFitness() == 162) {
        this.solucion = individuo;
        return true;
      }
    }
    return false;
  }

  // recorrer la poblacion y encontrar el individuo con el mejor fitness
  public Individuo mejorIndividuo() {
    Individuo mejorIndividuo = this.poblacion.get(0);
    for (Individuo individuo : this.poblacion) {
      if (individuo.getFitness() > mejorIndividuo.getFitness()) {
        mejorIndividuo = individuo;
      }
    }
    return mejorIndividuo;
  }

  public Individuo seleccionarIndividuo2() {
    int totalFitness = 0;
    for (Individuo individuo : this.poblacion) {
      totalFitness += individuo.getFitness();
    }

    double[] probabilidades = new double[this.poblacion.size()];
    double probAcumulada = 0.0;
    for (int i = 0; i < this.poblacion.size(); i++) {
      double probabilidad = (double) this.poblacion.get(i).getFitness() / totalFitness;
      probAcumulada += probabilidad;
      probabilidades[i] = probAcumulada;
    }

    double numeroAleatorio = Math.random();
    for (int i = 0; i < this.poblacion.size(); i++) {
      if (numeroAleatorio <= probabilidades[i]) {
        return this.poblacion.get(i);
      }
    }

    return this.poblacion.get(poblacion.size() - 1);
  }

  public Individuo seleccionarIndividuo3() {
    // Ordenar el arreglo de individuos en orden descendente según su propiedad
    // "fitness"
    Collections.sort(this.poblacion, Comparator.comparing(Individuo::getFitness).reversed());

    // Crear un arreglo con los 10 mejores individuos
    Individuo[] mejoresIndividuos = this.poblacion.stream()
        .sorted(Comparator.comparing(Individuo::getFitness).reversed())
        .limit(10)
        .toArray(Individuo[]::new);

    // Generar un número aleatorio entre 0 y 9
    Random rand = new Random();
    int indiceAleatorio = rand.nextInt(10);

    // Retornar el individuo que se encuentra en el índice aleatorio generado en el
    // arreglo de los 10 mejores individuos
    return mejoresIndividuos[indiceAleatorio];
  }

  public Individuo seleccionarMejorFitness() {
    // Ordenamos los individuos por su valor de fitness (de mayor a menor)
    this.poblacion.sort(Comparator.comparingInt(Individuo::getFitness).reversed());

    // Generamos un número aleatorio entre 0 y la suma de todos los fitness
    int numAleatorio = (int) (Math.random() * (this.poblacion.size() * (this.poblacion.size() + 1)) / 2);

    // Seleccionamos el individuo de la rueda de selección
    int acumulado = 0;
    for (int i = 0; i < this.poblacion.size(); i++) {
      acumulado += i + 1;
      if (acumulado > numAleatorio) {
        return this.poblacion.get(i);
      }
    }

    // Si no se seleccionó ningún individuo, devolvemos null
    return null;
  }
}
