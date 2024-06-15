package algoritmos;

import api.ConjuntoTDA;
import api.DiccionarioMultipleProvinciasTDA;
import impl.ConjuntoDinamico;
import impl.DiccionarioMultipleProvinciasDinamico;

import java.util.Random;

public class MetodosDiccionarioMultiple {

    public static String imprimirDiccionarioM(DiccionarioMultipleProvinciasTDA diccionario) {
        String resultado = "{";
        ConjuntoTDA<String> provincias = diccionario.Provincias();

        while (!provincias.ConjuntoVacio()) {
            String provincia = provincias.Elegir();
            ConjuntoTDA<String> ciudades = diccionario.RecuperarCiudades(provincia);
            resultado += " Provincia: " + provincia + ", Ciudades[";

            while (!ciudades.ConjuntoVacio()) {
                String ciudad = ciudades.Elegir();
                ciudades.Sacar(ciudad);
                resultado += ciudad;
                if (!ciudades.ConjuntoVacio()) {
                    resultado += ", ";
                }
            }
            resultado += "]";
            provincias.Sacar(provincia);
            if (!provincias.ConjuntoVacio()) {
                resultado += " ;";
            }
        }

        resultado += " }";

        return resultado;
    }


    public static DiccionarioMultipleProvinciasTDA crearCopiaDiccionarioM(DiccionarioMultipleProvinciasTDA diccionarioOriginal) {
        DiccionarioMultipleProvinciasTDA diccionarioCopia = new DiccionarioMultipleProvinciasDinamico();
        diccionarioCopia.InicializarDiccionario();

        ConjuntoTDA<String> provincias = diccionarioOriginal.Provincias();

        while (!provincias.ConjuntoVacio()) {
            String provincia = provincias.Elegir();
            ConjuntoTDA<String> ciudades = diccionarioOriginal.RecuperarCiudades(provincia);

            while (!ciudades.ConjuntoVacio()) {
                String ciudad = ciudades.Elegir();
                diccionarioCopia.Agregar(provincia, ciudad);
                ciudades.Sacar(ciudad);
            }
            provincias.Sacar(provincia);
        }

        return diccionarioCopia;
    }

    public static void agregarRandomDiccionarioM(DiccionarioMultipleProvinciasTDA diccionario, int cantElementos, int limInferiorClave, int limSuperiorClave, int limInferiorValor, int limSuperiorValor) {
        if (cantElementos <= ((limSuperiorClave - limInferiorClave) + 1)) {
            ConjuntoTDA<String> provincias = new ConjuntoDinamico<>();
            provincias.InicializarConjunto();
            Random random = new Random();
            int i = 0;
            while (i < cantElementos) {
                String provinciaRandom = "Provincia" + random.nextInt((limSuperiorClave - limInferiorClave) + 1) + limInferiorClave; // Ejemplo de generación de provincia aleatoria
                String ciudadRandom = "Ciudad" + random.nextInt((limSuperiorValor - limInferiorValor) + 1) + limInferiorValor; // Ejemplo de generación de ciudad aleatoria
                if (!provincias.Pertenece(provinciaRandom)) {
                    provincias.Agregar(provinciaRandom);
                    diccionario.Agregar(provinciaRandom, ciudadRandom);
                    i++;
                }
            }
        }
    }

}
