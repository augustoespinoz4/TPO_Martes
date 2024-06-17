package api;

public interface DiccionarioMultipleProvinciasTDA {
    void InicializarDiccionario();
    void Agregar(String provincia, String ciudad);
    void EliminarProvincia(String provincia);
    void EliminarCiudad(String provincia, String ciudad);
    ConjuntoTDA<String> RecuperarCiudades(String provincia);
    ConjuntoTDA<String> Provincias();
}
