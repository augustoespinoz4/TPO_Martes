package api;

public interface GrafoCiudadesTDA {
    void InicializarGrafo();
    void AgregarCiudad(String ciudad);
    void EliminarCiudad(String ciudad);
    ConjuntoTDA<String> Ciudades();
    void AgregarRuta(String ciudad1, String ciudad2, int km);
    void EliminarRuta(String ciudad1, String ciudad2);
    boolean ExisteRuta(String ciudad1, String ciudad2);
    int kilometrosRuta(String ciudad1, String ciudad2);
}
