package api;

public interface ConjuntoTDA<T> {
    void InicializarConjunto();
    boolean ConjuntoVacio();
    void Agregar(T clave);
    T Elegir();
    void Sacar(T clave);
    boolean Pertenece(T clave);
}