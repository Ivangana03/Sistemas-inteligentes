import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.qqwing.*;
import com.qqwing.Difficulty;

public class Main {
  public static void main(String[] args) {

    double tasaIndividuo = 0.1;
    double tasaMutacion = 0.5;
    double tasaCruce = 0.3;
    double tasaMutacionYCruce = 0.3;
    int poblacionSize = 100;
    int generaciones = 90000;
    boolean solucion = false;
    int[][] matrixSudokuCopy;
    int[][] matrixSudoku;
    int fitness = 0;

    do {

      Difficulty difficulty = Difficulty.EASY;
      int[] sudoku = SudokuGenerador.computePuzzleByDifficulty(difficulty);
      matrixSudokuCopy = SudokuGenerador.arrayToMatrix(sudoku);
      matrixSudoku = PencilMarking.pencilMarking(matrixSudokuCopy);
      Individuo individuoTest = new Individuo(matrixSudoku);
      fitness = individuoTest.getFitness();
    } while (fitness < 90);
    System.out.println("Sudoku generado:");
    System.out.println(SudokuGenerador.printMatrix(matrixSudokuCopy));
    Individuo individuo2 = new Individuo(matrixSudokuCopy);
    System.out.println("Fitness: " + individuo2.getFitness());
    System.out.println("Sudoku generado filling:");
    System.out.println(SudokuGenerador.printMatrix(matrixSudoku));
    Individuo individuo = new Individuo(matrixSudoku);
    System.out.println("Fitness: " + individuo.getFitness());
    AlgoritmoGenetico algoritmoGenetico = new AlgoritmoGenetico(matrixSudoku,
        poblacionSize);

    while (generaciones > 0 && !solucion) {
      if (generaciones % 1000 == 0) {
        System.out.println("Generación: " + generaciones);
      }
      List<Individuo> nuevaPoblacion = new ArrayList<Individuo>();
      for (int i = 0; i < poblacionSize; i++) {
        // Seleccionar individuo
        Individuo individuoPadre = algoritmoGenetico.seleccionarIndividuo2();

        // Aplicar operador
        double randomOperacion = Math.random();
        if (randomOperacion < tasaIndividuo) {
          // agregar el individuo a la nueva población sin aplicar operador
          Individuo nuevoIndividuo = algoritmoGenetico.mejorIndividuo();
          nuevaPoblacion.add(nuevoIndividuo);
        } else if (randomOperacion < tasaIndividuo + tasaMutacion) {
          // mutar el individuo y agregarlo a la nueva población
          Individuo nuevoIndividuo = algoritmoGenetico.mutarIndividuo(individuoPadre);
          nuevaPoblacion.add(nuevoIndividuo);
        } else if (randomOperacion < tasaIndividuo + tasaMutacion + tasaCruce) {
          // cruzar el individuo con otro y agregarlo a la nueva población
          Individuo individuoPadre2 = algoritmoGenetico.seleccionarIndividuo2();
          Individuo nuevoIndividuo = algoritmoGenetico.cruzarIndividuos(individuoPadre, individuoPadre2);
          nuevaPoblacion.add(nuevoIndividuo);
        } else {
          // mutar y cruzar el individuo y agregarlo a la nueva población
          Individuo individuoPadre2 = algoritmoGenetico.seleccionarIndividuo2();
          Individuo nuevoIndividuo = algoritmoGenetico.cruzarIndividuos(individuoPadre, individuoPadre2);
          nuevoIndividuo = algoritmoGenetico.mutarIndividuo(nuevoIndividuo);
          nuevaPoblacion.add(nuevoIndividuo);
        }

      }
      algoritmoGenetico.setPoblacion(nuevaPoblacion);
      // Evaluar si el individuo es solución
      solucion = algoritmoGenetico.buscarSolucion();
      generaciones--;
    }

    if (solucion) {
      System.out.println("Solución encontrada");
      System.out.println(algoritmoGenetico.getSolucion());
    } else {
      System.out.println("Solución no encontrada");
      Individuo mejorIndividuo = algoritmoGenetico.mejorIndividuo();
      System.out.println(mejorIndividuo);
      System.out.println("Fitness: " + mejorIndividuo.getFitness());
    }

  }

}
