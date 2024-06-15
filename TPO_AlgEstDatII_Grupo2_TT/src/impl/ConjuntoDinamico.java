package impl;

import api.ConjuntoTDA;

import java.util.Random;

public class ConjuntoDinamico<T> implements ConjuntoTDA<T> {

    private static class Nodo<T> {
        T info;
        Nodo<T> sig;
    }

    Nodo<T> primero;
    int contador;

    public void InicializarConjunto() {
        primero = null;
        contador = 0;
    }

    public boolean ConjuntoVacio() {
        return (primero == null);
    }

    public void Agregar(T clave) {
        if (!this.Pertenece(clave)) {
            Nodo<T> aux = new Nodo<>();
            aux.info = clave;
            aux.sig = primero;
            primero = aux;
            contador++;
        }
    }

    public T Elegir() {
        if (contador == 0) {
            return null; // Si el conjunto está vacío, retornamos null
        }

        Random random = new Random();
        int ubicacion = random.nextInt(contador);
        Nodo<T> recorrerAux = primero;
        for (int i = 0; i < ubicacion; i++) {
            recorrerAux = recorrerAux.sig;
        }
        return recorrerAux.info;
    }

    public void Sacar(T clave) {
        if (primero != null) {
            if (primero.info.equals(clave)) {
                primero = primero.sig;
                contador--;
            } else {
                Nodo<T> recorrer = primero;
                while (recorrer.sig != null && !recorrer.sig.info.equals(clave)) {
                    recorrer = recorrer.sig;
                }
                if (recorrer.sig != null) {
                    recorrer.sig = recorrer.sig.sig;
                    contador--;
                }
            }
        }
    }

    public boolean Pertenece(T clave) {
        Nodo<T> recorrer = primero;
        while (recorrer != null && !recorrer.info.equals(clave)) {
            recorrer = recorrer.sig;
        }
        return (recorrer != null);
    }
}
