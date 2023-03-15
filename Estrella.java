package Estrella;

import java.util.*;

public class Estrella {

    private int columnas = 80;
    private int filas = 60;
    private int obstaculos = 60 * 80;
    private Random r = new Random();
    private char celdas[][];

    public Estrella()
    {
        celdas = new char[filas][columnas];
    }

    public void generarObstaculos(Estrella estr)
    {
        int i = 0;

        while (i < obstaculos)
        {
            int f = (int) Math.random() * 60;
            int c = (int) Math.random() * 80;

            if(celdas[f][c] == ' ')
            {
                celdas[f][c] = '*';
                i++;
            }
        }
    }

    public void generarCasillaSalida(Estrella estr)
    {
        int f = (int) Math.random()*60;
        int c = (int) Math.random()*80;

        while(celdas[f][c] != ' ')
        {
            celdas[f][c] = 'I';
        }
    }

    public void generarCasillaObjetivo(Estrella estr)
    {
        int f = (int) Math.random()*60;
        int c = (int) Math.random()*80;

        while(celdas[f][c] != ' ')
        {
            celdas[f][c] = 'G';
        }
    }

    public generarCaminoOptimo(Estrella estr)
    {
        while(openset != null)
        {
            //current = nodo openset con menor valor
            if(current == 'G')
            {
                return reconstruct_path(parent, current);
            }
            //remove current from openset
            //add current to closedset
            //for each(//neighbor : nodos_vecinos(current))
                {
                        //if neighboor in closedset
                            //continue
                        //tentative_g = g[current] + dist_between(current, neighbor)
                        //if(neighbor not in openset or tentative_g < g[neighbor])
                            //parent[neighbor] = current;
                            //g[neighbor] = tentative_g;
                            //f[neighbor] = g[neighbor] + h[neighbor]
                            //if neighbor not in openset
                                //add neighbor to openset
                }
        }
    }

    public String toString()
    {
        String s = "";

        for(int f = 0; f < filas; f++)
        {
            for(int c = 0; c < columnas; c++)
            {
                s += (celdas[f][c] == 0 ? "." :String.format("%d", celdas[f][c]));
            }
        }

        return s;
    }

    public int main()
    {
        Estrella estr;
        generarObstaculos(estr);
        generarCasillaSalida(estr);
        generarCasillaObjetivo(estr);

        return 0;
    }
}
