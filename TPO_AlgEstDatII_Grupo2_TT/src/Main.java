import impl.Mapa;

public class Main {
    public static void main(String[] args) {
        Mapa mapa = new Mapa();
        mapa.inicializarMapa();

        System.out.println(
                "--------------------------------------------------------------------------------------------------------------------------\n" +
                        "---------------------------------------------- EJERCICIO I ---------------------------------------------------------------\n" +
                        "--------------------------------------------------------------------------------------------------------------------------");
        // Agregar Provincias y sus respectivas ciudades.
        mapa.cargarCiudad("Buenos Aires", "CABA");
        mapa.cargarCiudad("Buenos Aires", "La Plata");
        mapa.cargarCiudad("Buenos Aires", "Mar del Plata");
        mapa.cargarCiudad("Buenos Aires", "Tandil");

        // Ciudades de Córdoba
        mapa.cargarCiudad("Córdoba", "Ciudad de Córdoba");
        mapa.cargarCiudad("Córdoba", "Río Cuarto");
        mapa.cargarCiudad("Córdoba", "Villa Carlos Paz");

        // Ciudades de Salta
        mapa.cargarCiudad("Salta", "Cafayate");

        // Ciudades de Chubut
        mapa.cargarCiudad("Chubut", "Rawson");
        mapa.cargarCiudad("Chubut", "Trelew");
        mapa.cargarCiudad("Chubut", "Puerto Madryn");

        // Eliminar Ciudades
        //mapa.eliminarCiudad("Córdoba", "Río Cuarto");
        //mapa.eliminarCiudad("Buenos Aires", "Tandil");
        //mapa.eliminarCiudad("Salta", "Cafayate");
        //mapa.eliminarCiudad("Chubut", "Puerto Madryn");

        // Cargar rutas
        mapa.agregarRuta("CABA", "Mar del Plata", 400);
        mapa.agregarRuta("CABA", "La Plata", 60);
        mapa.agregarRuta("CABA", "Tandil", 350);
        mapa.agregarRuta("CABA", "Ciudad de Córdoba", 1300);

        mapa.agregarRuta("Mar del Plata", "CABA", 500);
        mapa.agregarRuta("Mar del Plata", "Ciudad de Córdoba", 1800);

        mapa.agregarRuta("La Plata", "Ciudad de Córdoba", 1500);
        mapa.agregarRuta("La Plata", "Rawson", 2700);

        mapa.agregarRuta("Tandil", "CABA", 480);

        mapa.agregarRuta("Ciudad de Córdoba", "Rawson", 2800);
        mapa.agregarRuta("Ciudad de Córdoba", "Río Cuarto", 200);

        mapa.agregarRuta("Río Cuarto", "Puerto Madryn", 1150);

        mapa.agregarRuta("Villa Carlos Paz", "Ciudad de Córdoba", 40);
        mapa.agregarRuta("Villa Carlos Paz", "Río Cuarto", 250);
        mapa.agregarRuta("Villa Carlos Paz", "Trelew", 1400);

        mapa.agregarRuta("Rawson", "Villa Carlos Paz", 1200);
        mapa.agregarRuta("Rawson", "Cafayate", 2200);
        mapa.agregarRuta("Rawson", "Trelew", 20);


        // Eliminar Ruta
        //mapa.eliminarRuta("CABA", "La Plata");
        //mapa.eliminarRuta("Ciudad de Córdoba", "Rawson");

        mapa.listarProvincias();
        mapa.listarCiudades("Buenos Aires");
        mapa.listarCiudades("Córdoba");
        mapa.listarCiudades("Salta");
        mapa.listarCiudades("Chubut");

        System.out.println(
                "\n--------------------------------------------------------------------------------------------------------------------------\n" +
                        "---------------------------------------------- EJERCICIO II --------------------------------------------------------------\n" +
                        "--------------------------------------------------------------------------------------------------------------------------");
        mapa.listarCiudadesVecinas("CABA");
        mapa.listarCiudadesVecinas("Villa Carlos Paz");
        mapa.listarCiudadesVecinas("Rawson");


        System.out.println(
                "\n--------------------------------------------------------------------------------------------------------------------------\n" +
                        "---------------------------------------------- EJERCICIO III -------------------------------------------------------------\n" +
                        "--------------------------------------------------------------------------------------------------------------------------");
        mapa.listarCiudadesPuente("CABA", "Río Cuarto");
        mapa.listarCiudadesPuente("Rawson", "Villa Carlos Paz");
        mapa.listarCiudadesPuente("Tandil", "Trelew");


        System.out.println(
                "\n--------------------------------------------------------------------------------------------------------------------------\n" +
                        "---------------------------------------------- EJERCICIO IV --------------------------------------------------------------\n" +
                        "--------------------------------------------------------------------------------------------------------------------------");
        mapa.listarCiudadesPredecesoras("CABA");
        mapa.listarCiudadesPredecesoras("La Plata");
        mapa.listarCiudadesPredecesoras("Cafayate");

        System.out.println(
                "\n--------------------------------------------------------------------------------------------------------------------------\n" +
                        "---------------------------------------------- EJERCICIO V ---------------------------------------------------------------\n" +
                        "--------------------------------------------------------------------------------------------------------------------------");
        mapa.listarCiudadesExtremo();

        System.out.println(
                "\n--------------------------------------------------------------------------------------------------------------------------\n" +
                        "---------------------------------------------- EJERCICIO VI --------------------------------------------------------------\n" +
                        "--------------------------------------------------------------------------------------------------------------------------");
        mapa.listarCiudadesFuertementeConectadas();

        System.out.println(
                "\n--------------------------------------------------------------------------------------------------------------------------\n" +
                        "---------------------------------------------- EJERCICIO VII -------------------------------------------------------------\n" +
                        "--------------------------------------------------------------------------------------------------------------------------");
        mapa.calcularCamino("CABA", "Trelew");
        System.out.println();
        mapa.calcularCamino("Cafayate", "Villa Carlos Paz");
        System.out.println();
        mapa.calcularCamino("Río Cuarto", "Puerto Madryn");
    }
}