package impl;

import api.ConjuntoTDA;
import api.GrafoCiudadesTDA;

public class GrafoCiudadesDinamico implements GrafoCiudadesTDA {
    class NodoGrafo {
        String ciudad;
        NodoArista arista;
        NodoGrafo sigNodo;
    }

    class NodoArista {
        int etiqueta;
        NodoGrafo nodoDestino;
        NodoArista sigArista;
    }

    NodoGrafo origen;

    @Override
    public void InicializarGrafo() {
        origen = null;
    }

    @Override
    public void AgregarCiudad(String ciudad) {
        NodoGrafo aux = new NodoGrafo();
        aux.ciudad = ciudad;
        aux.arista = null;
        aux.sigNodo = origen;
        origen = aux;
    }

    @Override
    public void EliminarCiudad(String ciudad) {
        // Se recorre la lista de vértices para remover el nodo v
        // y las aristas con este vértice.

        // Distingue el caso de que sea el primer nodo
        if (origen != null && origen.ciudad.equals(ciudad)) {
            origen = origen.sigNodo;
        }

        NodoGrafo aux = origen;
        while (aux != null) {
            // Remueve de aux todas las aristas hacia v.
            this.EliminarAristaNodo(aux, ciudad);

            if (aux.sigNodo != null && aux.sigNodo.ciudad.equals(ciudad)) {
                // Si el siguiente nodo de aux es "ciudad", lo elimina
                aux.sigNodo = aux.sigNodo.sigNodo;
            }
            aux = aux.sigNodo;
        }
    }

    // Si en las aristas del "nodo" existe una arista hacia "ciudad", la elimina
    private void EliminarAristaNodo(NodoGrafo nodo, String ciudad) {
        NodoArista aux = nodo.arista;

        if (aux != null) {
            // Si la arista a eliminar es la primera en
            // la lista de nodos adyacentes
            if (aux.nodoDestino.ciudad.equals(ciudad)) {
                nodo.arista = aux.sigArista;
            } else {
                while (aux.sigArista != null && !aux.sigArista.nodoDestino.ciudad.equals(ciudad)) {
                    aux = aux.sigArista;
                }
                if (aux.sigArista != null) {
                    // Quita la referencia a la arista hacia ciudad
                    aux.sigArista = aux.sigArista.sigArista;
                }
            }
        }
    }

    @Override
    public ConjuntoTDA<String> Ciudades() {
        ConjuntoTDA<String> conjuntoCiudades = new ConjuntoDinamico<>();
        conjuntoCiudades.InicializarConjunto();

        NodoGrafo recorrer = origen;
        while (recorrer != null) {
            conjuntoCiudades.Agregar(recorrer.ciudad);
            recorrer = recorrer.sigNodo;
        }

        return conjuntoCiudades;
    }

    @Override
    public void AgregarRuta(String ciudad1, String ciudad2, int km) {
        NodoGrafo n1 = buscarNodoGrafo(ciudad1);
        NodoGrafo n2 = buscarNodoGrafo(ciudad2);

        // La nueva arista se inserta al inicio de la lista
        // de nodos adyacentes del nodo origen.
        NodoArista aux = new NodoArista();
        aux.etiqueta = km;
        aux.nodoDestino = n2;
        aux.sigArista = n1.arista;
        n1.arista = aux;
    }

    private NodoGrafo buscarNodoGrafo(String ciudad) {
        NodoGrafo recorrer = origen;
        while (recorrer != null && !recorrer.ciudad.equals(ciudad)) {
            recorrer = recorrer.sigNodo;
        }
        return recorrer;
    }

    @Override
    public void EliminarRuta(String ciudad1, String ciudad2) {
        NodoGrafo n1 = buscarNodoGrafo(ciudad1);
        EliminarAristaNodo(n1, ciudad2);
    }

    @Override
    public boolean ExisteRuta(String ciudad1, String ciudad2) {
        NodoGrafo n1 = buscarNodoGrafo(ciudad1);

        NodoArista recorrer = n1.arista;
        while (recorrer != null && !recorrer.nodoDestino.ciudad.equals(ciudad2)) {
            recorrer = recorrer.sigArista;
        }
        // Sólo si se encontró la arista buscada, aux no es null
        return recorrer != null;
    }

    @Override
    public int kilometrosRuta(String ciudad1, String ciudad2) {
        NodoGrafo n1 = buscarNodoGrafo(ciudad1);

        NodoArista recorrer = n1.arista;
        while (recorrer != null && !recorrer.nodoDestino.ciudad.equals(ciudad2)) {
            recorrer = recorrer.sigArista;
        }
        // Se encontró la arista entre los dos nodos.
        return recorrer.etiqueta;
    }
}
