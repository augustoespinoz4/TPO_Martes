package impl;

import api.ConjuntoTDA;
import api.DiccionarioMultipleProvinciasTDA;

public class DiccionarioMultipleProvinciasDinamico implements DiccionarioMultipleProvinciasTDA {

    // Clase NodoProvincia
    private class NodoProvincia {
        String nombreProvincia;
        NodoCiudad ciudades;
        NodoProvincia siguiente;
    }

    // Clase NodoCiudad
    private class NodoCiudad {
        String nombreCiudad;
        NodoCiudad siguiente;
    }

    NodoProvincia primero; // Primer nodo de provincia

    @Override
    public void InicializarDiccionario() {
        primero = null;
    }

    @Override
    public void Agregar(String provincia, String ciudad) {
        NodoProvincia nodoProvincia = BuscarProvincia(provincia);
        if (nodoProvincia == null) { // Si la provincia NO existe...
            nodoProvincia = new NodoProvincia();
            nodoProvincia.nombreProvincia = provincia; // Se crea la nueva provincia.
            nodoProvincia.siguiente = primero; // El siguiente nodo ahora apunta al primero.
            primero = nodoProvincia; // Y ahora el primero es el nuevo nodo de provincia.
        }
        NodoCiudad aux = BuscarCiudad(nodoProvincia, ciudad);
        if (aux == null) { // Si la ciudad NO está asociada a la provincia entonces...
            NodoCiudad nuevaCiudad = new NodoCiudad(); // Se crea un nuevo nodo de ciudad
            nuevaCiudad.nombreCiudad = ciudad; // Asociamos la ciudad al nuevo nodo de ciudad
            nuevaCiudad.siguiente = nodoProvincia.ciudades; // El siguiente nodo de ciudad apunta al mismo lugar que nodoProvincia.
            nodoProvincia.ciudades = nuevaCiudad; // Y ahora nodoProvincia apunta al nuevo nodo de ciudad
        }
    }

    @Override
    public void EliminarProvincia(String provincia) {
        if (primero != null) {
            if (primero.nombreProvincia.equals(provincia)) {
                primero = primero.siguiente;
            } else {
                NodoProvincia recorrer = primero;
                while (recorrer.siguiente != null && !recorrer.siguiente.nombreProvincia.equals(provincia)) {
                    recorrer = recorrer.siguiente;
                }
                if (recorrer.siguiente != null) {
                    recorrer.siguiente = recorrer.siguiente.siguiente;
                }
            }
        }
    }

    @Override
    public void EliminarCiudad(String provincia, String ciudad) {
        NodoProvincia nodoProvincia = BuscarProvincia(provincia);
        if (nodoProvincia != null) {
            EliminarCiudadEnNodo(nodoProvincia, ciudad);
            if (nodoProvincia.ciudades == null) {
                EliminarProvincia(provincia);
            }
        }
    }

    @Override
    public ConjuntoTDA<String> RecuperarCiudades(String provincia) {
        NodoProvincia nodoProvincia = BuscarProvincia(provincia);
        ConjuntoTDA<String> conjuntoCiudades = new ConjuntoDinamico<>();
        if (nodoProvincia != null) {
            NodoCiudad recorrer = nodoProvincia.ciudades;
            while (recorrer != null) {
                conjuntoCiudades.Agregar(recorrer.nombreCiudad);
                recorrer = recorrer.siguiente;
            }
        }
        return conjuntoCiudades;
    }

    @Override
    public ConjuntoTDA<String> Provincias() {
        ConjuntoTDA<String> conjuntoProvincias = new ConjuntoDinamico<>();
        NodoProvincia recorrer = primero;
        while (recorrer != null) {
            conjuntoProvincias.Agregar(recorrer.nombreProvincia);
            recorrer = recorrer.siguiente;
        }
        return conjuntoProvincias;
    }

    private NodoProvincia BuscarProvincia(String provincia) {
        NodoProvincia recorrer = primero;
        while (recorrer != null && !recorrer.nombreProvincia.equals(provincia)) {
            recorrer = recorrer.siguiente;
        }
        return recorrer;
    }

    private NodoCiudad BuscarCiudad(NodoProvincia nodoProvincia, String ciudad) {
        NodoCiudad recorrer = nodoProvincia.ciudades;
        while (recorrer != null && !recorrer.nombreCiudad.equals(ciudad)) {
            recorrer = recorrer.siguiente;
        }
        return recorrer;
    }

    private void EliminarCiudadEnNodo(NodoProvincia nodoProvincia, String ciudad) {
        if (nodoProvincia.ciudades != null) {
            if (nodoProvincia.ciudades.nombreCiudad.equals(ciudad)) {
                nodoProvincia.ciudades = nodoProvincia.ciudades.siguiente;
            } else {
                NodoCiudad recorrer = nodoProvincia.ciudades;
                while (recorrer.siguiente != null && !recorrer.siguiente.nombreCiudad.equals(ciudad)) {
                    recorrer = recorrer.siguiente;
                }
                if (recorrer.siguiente != null) {
                    recorrer.siguiente = recorrer.siguiente.siguiente;
                }
            }
        }
    }
}
