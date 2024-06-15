package impl;

import api.ConjuntoTDA;
import api.DiccionarioSimpleTDA;

public class DiccionarioSimpleDinamico<K, V> implements DiccionarioSimpleTDA<K, V> {

    private static class NodoDS<K, V> {
        K clave;
        V valor;
        NodoDS<K, V> sig;
    }

    NodoDS<K, V> primero;

    @Override
    public void InicializarDiccionario() {
        primero = null;
    }

    @Override
    public void Agregar(K clave, V valor) {
        NodoDS<K, V> aux = buscarNodo(clave);
        if (aux == null) {
            aux = new NodoDS<>();
            aux.clave = clave;
            aux.sig = primero;
            primero = aux;
        }
        aux.valor = valor;
    }

    @Override
    public void Eliminar(K clave) {
        if (primero != null) {
            if (primero.clave.equals(clave)) {
                primero = primero.sig;
            } else {
                NodoDS<K, V> recorrer = primero;
                while (recorrer.sig != null && !recorrer.sig.clave.equals(clave)) {
                    recorrer = recorrer.sig;
                }
                if (recorrer.sig != null) {
                    recorrer.sig = recorrer.sig.sig;
                }
            }
        }
    }

    @Override
    public V Recuperar(K clave) {
        NodoDS<K, V> nodoClave = buscarNodo(clave);
        return nodoClave != null ? nodoClave.valor : null;
    }

    @Override
    public ConjuntoTDA<K> Claves() {
        ConjuntoTDA<K> conjuntoClaves = new ConjuntoDinamico<>();
        conjuntoClaves.InicializarConjunto();

        NodoDS<K, V> recorrer = primero;
        while (recorrer != null) {
            conjuntoClaves.Agregar(recorrer.clave);
            recorrer = recorrer.sig;
        }
        return conjuntoClaves;
    }

    private NodoDS<K, V> buscarNodo(K clave) {
        NodoDS<K, V> recorrer = primero;
        while (recorrer != null && !recorrer.clave.equals(clave)) {
            recorrer = recorrer.sig;
        }
        return recorrer;
    }
}
