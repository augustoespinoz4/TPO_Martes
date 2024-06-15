package impl;

import api.PilaTDA;

public class PilaDinamica<T> implements PilaTDA<T> {

    private static class Nodo<T> {
        T info;
        Nodo<T> sig;
    }

    Nodo<T> primero;

    public void InicializarPila() {
        primero = null;
    }

    public void Apilar(T x) {
        Nodo<T> aux = new Nodo<>();
        aux.info = x;
        aux.sig = primero;
        primero = aux;
    }

    public void Desapilar() {
        if (primero != null) {
            primero = primero.sig;
        }
    }

    public T Tope() {
        return primero != null ? primero.info : null;
    }

    public boolean PilaVacia() {
        return (primero == null);
    }
}
