package algoritmos;

import api.ConjuntoTDA;
import impl.ConjuntoDinamico;

public class MetodosConjunto<T> {

    public static <T> String imprimirConjunto(ConjuntoTDA<T> conjunto) {
        String resultado = "(";
        ConjuntoTDA<T> conjuntoAux = new ConjuntoDinamico<>();
        conjuntoAux.InicializarConjunto();

        while (!conjunto.ConjuntoVacio()) {
            T elemento = conjunto.Elegir();
            conjunto.Sacar(elemento);
            conjuntoAux.Agregar(elemento);
            resultado += elemento;
            if (!conjunto.ConjuntoVacio()) {
                resultado += ", ";
            }
        }

        // Restaurar el conjunto original
        while (!conjuntoAux.ConjuntoVacio()) {
            T elemento = conjuntoAux.Elegir();
            conjunto.Agregar(elemento);
            conjuntoAux.Sacar(elemento);
        }

        resultado += ")";

        return resultado;
    }

    public static <T> ConjuntoTDA<T> copiarConjunto(ConjuntoTDA<T> conjunto) {
        ConjuntoTDA<T> copia = new ConjuntoDinamico<>();
        copia.InicializarConjunto();

        ConjuntoTDA<T> aux = new ConjuntoDinamico<>();
        aux.InicializarConjunto();

        while (!conjunto.ConjuntoVacio()) {
            T elemento = conjunto.Elegir();
            conjunto.Sacar(elemento);
            copia.Agregar(elemento);
            aux.Agregar(elemento);
        }

        while (!aux.ConjuntoVacio()) {
            T elemento = aux.Elegir();
            aux.Sacar(elemento);
            conjunto.Agregar(elemento);
        }

        return copia;
    }
}
