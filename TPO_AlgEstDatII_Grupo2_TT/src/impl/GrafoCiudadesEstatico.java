package impl;

import api.GrafoCiudadesTDA;
import api.ConjuntoTDA;

public class GrafoCiudadesEstatico implements GrafoCiudadesTDA {
    static final int MAX_CIUDADES = 100;
    int[][] matrizAdyacencia; // Matriz de adyacencia para almacenar las rutas y sus kilómetros
    String[] ciudades;
    int cantidadCiudades;

    @Override
    public void InicializarGrafo() {
        matrizAdyacencia = new int[MAX_CIUDADES][MAX_CIUDADES];
        ciudades = new String[MAX_CIUDADES];
        cantidadCiudades = 0;
    }

    @Override
    public void AgregarCiudad(String ciudad) {
        ciudades[cantidadCiudades] = ciudad;
        for (int i = 0; i <= cantidadCiudades; i++) {
            matrizAdyacencia[cantidadCiudades][i] = 0;
            matrizAdyacencia[i][cantidadCiudades] = 0;
        }
        cantidadCiudades++;
    }

    @Override
    public void EliminarCiudad(String ciudad) {
        int indiceCiudad = obtenerIndiceCiudad(ciudad);
        for (int k = 0; k < cantidadCiudades; k++)
            matrizAdyacencia[k][indiceCiudad] = matrizAdyacencia[k][cantidadCiudades - 1];
        for (int k = 0; k < cantidadCiudades; k++)
            matrizAdyacencia[indiceCiudad][k] = matrizAdyacencia[cantidadCiudades - 1][k];

        ciudades[indiceCiudad] = ciudades[cantidadCiudades - 1];
        cantidadCiudades--;
    }

    private int obtenerIndiceCiudad(String ciudad) {
        int i = cantidadCiudades - 1;
        while (i >= 0 && !ciudades[i].equals(ciudad)) {
            i--;
        }
        return i;
    }

    @Override
    public ConjuntoTDA<String> Ciudades() {
        ConjuntoTDA<String> conjuntoCiudades = new ConjuntoDinamico<>();
        conjuntoCiudades.InicializarConjunto();
        for (int i = 0; i < cantidadCiudades; i++) {
            conjuntoCiudades.Agregar(ciudades[i]);
        }
        return conjuntoCiudades;
    }

    @Override
    public void AgregarRuta(String ciudad1, String ciudad2, int km) {
        int indiceCiudad1 = obtenerIndiceCiudad(ciudad1);
        int indiceCiudad2 = obtenerIndiceCiudad(ciudad2);
        matrizAdyacencia[indiceCiudad1][indiceCiudad2] = km;
    }

    @Override
    public void EliminarRuta(String ciudad1, String ciudad2) {
        int indiceCiudad1 = obtenerIndiceCiudad(ciudad1);
        int indiceCiudad2 = obtenerIndiceCiudad(ciudad2);
        matrizAdyacencia[indiceCiudad1][indiceCiudad2] = 0;
    }

    @Override
    public boolean ExisteRuta(String ciudad1, String ciudad2) {
        int indiceCiudad1 = obtenerIndiceCiudad(ciudad1);
        int indiceCiudad2 = obtenerIndiceCiudad(ciudad2);
        return matrizAdyacencia[indiceCiudad1][indiceCiudad2] != 0;
    }

    @Override
    public int kilometrosRuta(String ciudad1, String ciudad2) {
        int indiceCiudad1 = obtenerIndiceCiudad(ciudad1);
        int indiceCiudad2 = obtenerIndiceCiudad(ciudad2);
        return matrizAdyacencia[indiceCiudad1][indiceCiudad2];
    }
}
