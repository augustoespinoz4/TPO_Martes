package api;

public interface MapaTDA {
    void inicializarMapa();

    void listarProvincias();
    void listarCiudades(String provincia);
    void cargarCiudad(String provincia, String ciudad);
    void eliminarCiudad(String provincia, String ciudad);
    void agregarRuta(String ciudad1, String ciudad2, int km);
    void eliminarRuta(String ciudad1, String ciudad2);


    void listarCiudadesVecinas(String ciudad);
    void listarCiudadesPuente(String ciudad1, String ciudad2);
    void listarCiudadesPredecesoras(String ciudad);
    void listarCiudadesExtremo();
    void listarCiudadesFuertementeConectadas();
    void calcularCamino(String ciudad1, String ciudad2);
}
