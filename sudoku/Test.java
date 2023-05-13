public class Test {
  public static void main(String[] args) {
    double tasaIndividuo = 0.2;
    double tasaMutacion = 0.2;
    double tasaCruce = 0.3;
    double tasaMutacionYCruce = 0.3;

    double sumaTasas = tasaIndividuo + tasaMutacion + tasaCruce + tasaMutacionYCruce;

    // Calcula la probabilidad de cada parámetro
    double probIndividuo = tasaIndividuo / sumaTasas;

    double probMutacion = tasaMutacion / sumaTasas;
    
    double probCruce = tasaCruce / sumaTasas;
    
    double probMutacionYCruce = tasaMutacionYCruce / sumaTasas;
    

    // Genera un número aleatorio entre 0 y 1
    double random = Math.random();

    System.out.println("number" + random);
    // Selecciona un parámetro de acuerdo con su probabilidad
    if (random < probIndividuo) {
      System.out.println("identico");
    } else if (random < probIndividuo + probMutacion) {
      System.out.println("mutacion");
    } else if (random < probIndividuo + probMutacion + probCruce) {
      System.out.println("cruce");
    } else {
      System.out.println("mutacion y cruce");
    }
  }
// public Individuo seleccionarIndividuo() {
//     int sumaFitness = 0;
//     for (Individuo individuo : poblacion) {
//         sumaFitness += individuo.getFitness();
//     }
//     
//     int r = (int) (Math.random() * sumaFitness); // Genera un número aleatorio entre 0 y la suma total de los fitness
//     
//     int sumaParcial = 0;
//     for (Individuo individuo : poblacion) {
//         sumaParcial += individuo.getFitness();
//         if (sumaParcial >= r) {
//             return individuo; // Retorna el individuo en el que se detuvo el recorrido
//         }
//     }
//     return null; // Si no se encuentra ningún individuo, retorna null
// }
}
