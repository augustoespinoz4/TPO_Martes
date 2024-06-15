package api;

public interface PilaTDA<T> {
    void InicializarPila();
    void Apilar(T x);
    void Desapilar();
    T Tope();
    boolean PilaVacia();
}
