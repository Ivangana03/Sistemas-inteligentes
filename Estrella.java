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

    public iniciarEstrella(Estrella estr)
    {
        generarObstaculos(estr);
        generarCasillaSalida(estr);
        generarCasillaObjetivo(estr);


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
}
