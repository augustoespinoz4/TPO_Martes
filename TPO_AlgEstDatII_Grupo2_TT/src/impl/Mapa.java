package impl;

import api.*;

import static algoritmos.MetodosConjunto.*;

public class Mapa implements MapaTDA {

    DiccionarioMultipleProvinciasTDA provincias = new DiccionarioMultipleProvinciasDinamico();
    GrafoCiudadesTDA mapaCiudades = new GrafoCiudadesEstatico();

    @Override
    public void inicializarMapa() {
        provincias.InicializarDiccionario();
        mapaCiudades.InicializarGrafo();
    }

    @Override
    public void listarProvincias() {
        ConjuntoTDA<String> conjuntoProvincias = provincias.Provincias();
        System.out.println("Provincias: " + imprimirConjunto(conjuntoProvincias));
    }

    @Override
    public void listarCiudades(String provincia) {
        ConjuntoTDA<String> conjuntoCiudades = provincias.RecuperarCiudades(provincia);
        if (conjuntoCiudades != null && !conjuntoCiudades.ConjuntoVacio()) {
            System.out.println("Las ciudades de " + provincia + " son: " + imprimirConjunto(conjuntoCiudades));
        } else {
            System.out.println("No hay ciudades registradas para la provincia de " + provincia);
        }
    }

    @Override
    public void cargarCiudad(String provincia, String ciudad) {
        provincias.Agregar(provincia, ciudad);
        mapaCiudades.AgregarCiudad(ciudad);
    }

    @Override
    public void eliminarCiudad(String provincia, String ciudad) {
        provincias.EliminarCiudad(provincia, ciudad);
        mapaCiudades.EliminarCiudad(ciudad);
    }

    @Override
    public void agregarRuta(String ciudad1, String ciudad2, int km) {
        mapaCiudades.AgregarRuta(ciudad1, ciudad2, km);
    }

    @Override
    public void eliminarRuta(String ciudad1, String ciudad2) {
        mapaCiudades.EliminarRuta(ciudad1, ciudad2);
    }

    @Override
    public void listarCiudadesVecinas(String ciudad) {
        ConjuntoTDA<String> ciudades = mapaCiudades.Ciudades();
        ConjuntoTDA<String> ciudadesVecinas = new ConjuntoDinamico<>();
        ciudadesVecinas.InicializarConjunto();

        // Recorrer las ciudades en el grafo para verificar cuáles son vecinas
        while (!ciudades.ConjuntoVacio()) {
            String otraCiudad = ciudades.Elegir();
            ciudades.Sacar(otraCiudad);

            // Verificar si hay una ruta desde la ciudad pasada por parámetro a otraCiudad
            if (mapaCiudades.ExisteRuta(ciudad, otraCiudad)) {
                ciudadesVecinas.Agregar(otraCiudad);
            }
        }

        if (!ciudadesVecinas.ConjuntoVacio()) {
            System.out.println("Las ciudades vecinas a " + ciudad + " son: " + imprimirConjunto(ciudadesVecinas));
        }
        else {
            System.out.println("No hay ciudades vecinas registradas para " + ciudad);
        }
    }

    @Override
    public void listarCiudadesPuente(String ciudad1, String ciudad2) {
        ConjuntoTDA<String> ciudades = mapaCiudades.Ciudades();
        ConjuntoTDA<String> ciudadesPuente = new ConjuntoDinamico<>();
        ciudadesPuente.InicializarConjunto();

        // Recorrer todas las ciudades en el grafo para verificar cuáles son puentes
        while (!ciudades.ConjuntoVacio()) {
            String ciudadPuente = ciudades.Elegir();
            ciudades.Sacar(ciudadPuente);

            // Verificar si hay una ruta desde ciudad1 a ciudadPuente y desde ciudadPuente a ciudad2
            if (mapaCiudades.ExisteRuta(ciudad1, ciudadPuente) && mapaCiudades.ExisteRuta(ciudadPuente, ciudad2)) {
                ciudadesPuente.Agregar(ciudadPuente);
            }
        }

        // Imprimir las ciudades puente y la distancia total
        if (!ciudadesPuente.ConjuntoVacio()) {
            System.out.println("Las ciudades puente entre " + ciudad1 + " y " + ciudad2 + " son:");
            while (!ciudadesPuente.ConjuntoVacio()) {
                String ciudadPuente = ciudadesPuente.Elegir();
                ciudadesPuente.Sacar(ciudadPuente);

                int distanciaTotal = mapaCiudades.kilometrosRuta(ciudad1, ciudadPuente) +
                        mapaCiudades.kilometrosRuta(ciudadPuente, ciudad2);

                System.out.println("Ciudad puente: " + ciudadPuente + ", Distancia total: " + distanciaTotal + " km");
            }
        } else {
            System.out.println("No hay ciudades puente registradas entre " + ciudad1 + " y " + ciudad2);
        }
    }

    @Override
    public void listarCiudadesPredecesoras(String ciudad) {
        ConjuntoTDA<String> ciudades = mapaCiudades.Ciudades();
        ConjuntoTDA<String> ciudadesPredecesoras = new ConjuntoDinamico<>();
        ciudadesPredecesoras.InicializarConjunto();

        // Recorrer todas las ciudades en el grafo para verificar cuáles son predecesoras
        while (!ciudades.ConjuntoVacio()) {
            String otraCiudad = ciudades.Elegir();
            ciudades.Sacar(otraCiudad);

            // Verificar si hay una ruta desde otraCiudad hacia la ciudad pasada por parámetro
            if (mapaCiudades.ExisteRuta(otraCiudad, ciudad)) {
                ciudadesPredecesoras.Agregar(otraCiudad);
            }
        }

        // Imprimir las ciudades predecesoras encontradas
        if (!ciudadesPredecesoras.ConjuntoVacio()) {
            System.out.println("Las ciudades predecesoras a " + ciudad + " son: " + imprimirConjunto(ciudadesPredecesoras));
        } else {
            System.out.println("No hay ciudades predecesoras registradas para " + ciudad);
        }
    }

    @Override
    public void listarCiudadesExtremo() {
        ConjuntoTDA<String> ciudades = mapaCiudades.Ciudades();
        ConjuntoTDA<String> ciudadesExtremo = new ConjuntoDinamico<>();
        ciudadesExtremo.InicializarConjunto();

        // Recorrer todas las ciudades en el grafo
        while (!ciudades.ConjuntoVacio()) {
            String ciudad = ciudades.Elegir();
            ciudades.Sacar(ciudad);

            // Obtener las ciudades vecinas (ciudades a las que se puede llegar desde 'ciudad')
            ConjuntoTDA<String> vecinas = obtenerCiudadesVecinas(ciudad);

            // Si no tiene ciudades vecinas, es una ciudad extremo
            if (vecinas.ConjuntoVacio()) {
                ciudadesExtremo.Agregar(ciudad);
            }
        }

        // Imprimir las ciudades extremo encontradas
        if (!ciudadesExtremo.ConjuntoVacio()) {
            System.out.println("Las ciudades extremo son: " + imprimirConjunto(ciudadesExtremo));
        } else {
            System.out.println("No hay ciudades extremo registradas.");
        }
    }

    @Override
    public void listarCiudadesFuertementeConectadas() {
        ConjuntoTDA<String> ciudades = mapaCiudades.Ciudades();
        ConjuntoTDA<String> ciudadesVisitadas = new ConjuntoDinamico<>();
        ciudadesVisitadas.InicializarConjunto();
        ConjuntoTDA<String> ciudadesFuertementeConectadas = new ConjuntoDinamico<>();
        ciudadesFuertementeConectadas.InicializarConjunto();

        // Recorrer todas las ciudades en el grafo
        while (!ciudades.ConjuntoVacio()) {
            String ciudad1 = ciudades.Elegir();
            ciudades.Sacar(ciudad1);

            ConjuntoTDA<String> ciudadesRestantes = copiarConjunto(ciudades);

            // Verificar rutas de ida y vuelta entre ciudad1 y cada otra ciudad
            while (!ciudadesRestantes.ConjuntoVacio()) {
                String ciudad2 = ciudadesRestantes.Elegir();
                ciudadesRestantes.Sacar(ciudad2);

                // Si existe ruta de ida y vuelta entre ciudad1 y ciudad2
                if (mapaCiudades.ExisteRuta(ciudad1, ciudad2) && mapaCiudades.ExisteRuta(ciudad2, ciudad1)) {
                    ciudadesFuertementeConectadas.Agregar(ciudad1);
                    ciudadesFuertementeConectadas.Agregar(ciudad2);
                }
            }

            ciudadesVisitadas.Agregar(ciudad1);
        }

        // Imprimir las ciudades fuertemente conectadas encontradas
        if (!ciudadesFuertementeConectadas.ConjuntoVacio()) {
            System.out.println("Las ciudades fuertemente conectadas son: " + imprimirConjunto(ciudadesFuertementeConectadas));
        } else {
            System.out.println("No hay ciudades fuertemente conectadas registradas.");
        }
    }

    @Override
    public void calcularCamino(String ciudad1, String ciudad2) {
        // Verificar si las ciudades existen en el mapa
        boolean existeCiudad1 = false;
        boolean existeCiudad2 = false;

        ConjuntoTDA<String> ciudades = mapaCiudades.Ciudades();
        ConjuntoTDA<String> copiaCiudades = new ConjuntoDinamico<>();
        copiaCiudades.InicializarConjunto();

        while (!ciudades.ConjuntoVacio()) {
            String ciudad = ciudades.Elegir();
            ciudades.Sacar(ciudad);
            copiaCiudades.Agregar(ciudad);
            if (ciudad.equals(ciudad1)) {
                existeCiudad1 = true;
            }
            if (ciudad.equals(ciudad2)) {
                existeCiudad2 = true;
            }
        }

        if (!existeCiudad1 || !existeCiudad2) {
            System.out.println("Una o ambas ciudades no existen en el mapa.");
            return;
        }

        // Estructuras para Dijkstra
        DiccionarioSimpleTDA<String, Integer> distanciaMinima = new DiccionarioSimpleDinamico<>();
        DiccionarioSimpleTDA<String, String> predecesor = new DiccionarioSimpleDinamico<>();
        ConjuntoTDA<String> ciudadesNoVisitadas = new ConjuntoDinamico<>();
        ciudadesNoVisitadas.InicializarConjunto();
        ciudades = copiaCiudades;

        // Inicialización de distanciaMinima y predecesor
        while (!ciudades.ConjuntoVacio()) {
            String ciudad = ciudades.Elegir();
            ciudades.Sacar(ciudad);
            distanciaMinima.Agregar(ciudad, Integer.MAX_VALUE); // Distancia infinita al principio
            predecesor.Agregar(ciudad, null); // Sin predecesor al principio
            ciudadesNoVisitadas.Agregar(ciudad);
        }
        distanciaMinima.Agregar(ciudad1, 0); // Distancia de ciudad1 a ciudad1 es 0

        // Proceso de Dijkstra
        boolean continuar = true;
        while (continuar && !ciudadesNoVisitadas.ConjuntoVacio()) {
            String ciudadActual = null;
            int distanciaMinimaActual = Integer.MAX_VALUE;

            // Encontrar la ciudad no visitada con la menor distancia mínima
            ConjuntoTDA<String> ciudadesRestantes = distanciaMinima.Claves();
            boolean encontrada = false; // Variable para controlar si se encontró una ciudad válida

            while (!ciudadesRestantes.ConjuntoVacio() && !encontrada) {
                String ciudad = ciudadesRestantes.Elegir();
                ciudadesRestantes.Sacar(ciudad);
                if (ciudadesNoVisitadas.Pertenece(ciudad)) {
                    int distancia = distanciaMinima.Recuperar(ciudad);
                    if (distancia < distanciaMinimaActual) {
                        ciudadActual = ciudad;
                        distanciaMinimaActual = distancia;
                        encontrada = true; // Se encontró una ciudad válida
                    }
                }
            }

            if (!encontrada) {
                continuar = false; // No se encontró ninguna ciudad válida, salir del bucle
            } else {
                ciudadesNoVisitadas.Sacar(ciudadActual);

                // Iterar sobre las ciudades vecinas
                ConjuntoTDA<String> ciudadesVecinas = obtenerCiudadesVecinas(ciudadActual);
                while (!ciudadesVecinas.ConjuntoVacio()) {
                    String vecina = ciudadesVecinas.Elegir();
                    ciudadesVecinas.Sacar(vecina);

                    int distanciaDesdeActual = distanciaMinima.Recuperar(ciudadActual) + mapaCiudades.kilometrosRuta(ciudadActual, vecina);

                    if (distanciaDesdeActual < distanciaMinima.Recuperar(vecina)) {
                        distanciaMinima.Agregar(vecina, distanciaDesdeActual);
                        predecesor.Agregar(vecina, ciudadActual);
                    }
                }
            }
        }

        // Reconstrucción del camino más corto
        if (distanciaMinima.Recuperar(ciudad2) < Integer.MAX_VALUE) {
            System.out.println("La distancia mínima desde " + ciudad1 + " a " + ciudad2 + " es: " + distanciaMinima.Recuperar(ciudad2) + " km");
            System.out.print("El camino es: ");
            reconstruirCamino(ciudad1, ciudad2, predecesor);
            System.out.println();
        } else {
            System.out.println("No hay camino posible entre " + ciudad1 + " y " + ciudad2);
        }
    }

    // Método auxiliar para reconstruir el camino más corto desde ciudad1 a ciudad2
    private void reconstruirCamino(String ciudad1, String ciudad2, DiccionarioSimpleTDA<String, String> predecesor) {
        if (!ciudad2.equals(ciudad1)) {
            reconstruirCamino(ciudad1, predecesor.Recuperar(ciudad2), predecesor);
            System.out.print(" -> " + ciudad2);
        } else {
            System.out.print(ciudad1);
        }
    }

    private ConjuntoTDA<String> obtenerCiudadesVecinas(String ciudad) {
        ConjuntoTDA<String> ciudades = mapaCiudades.Ciudades();
        ConjuntoTDA<String> ciudadesVecinas = new ConjuntoDinamico<>();
        ciudadesVecinas.InicializarConjunto();

        // Recorrer todas las ciudades en el grafo para verificar cuáles son vecinas
        while (!ciudades.ConjuntoVacio()) {
            String otraCiudad = ciudades.Elegir();
            ciudades.Sacar(otraCiudad);

            // Verificar si hay una ruta desde la ciudad pasada por parámetro a otraCiudad
            if (mapaCiudades.ExisteRuta(ciudad, otraCiudad)) {
                ciudadesVecinas.Agregar(otraCiudad);
            }
        }

        return ciudadesVecinas;
    }

}
