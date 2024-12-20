package Logica;

import DataBase.XML;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Consultas {

    public static void notaMedia() {
        try {
            // Acceder al documento XML
            Document doc = XML.accederDocumento();
            NodeList listaNotas = doc.getElementsByTagName("notaFinal"); // Obtener todas las etiquetas <notaFinal>
            double resultado;
            double suma = 0;
            int totalNotas = listaNotas.getLength();

            // Recorrer las etiquetas <notaFinal> y sumar sus valores
            for (int i = 0; i < totalNotas; i++) {
                Node nota = listaNotas.item(i);

                if (nota.getNodeType() == Node.ELEMENT_NODE) {
                    // Obtener el valor de la nota y sumarlo
                    suma += Double.parseDouble(nota.getTextContent().trim());
                }
            }

            // Calcular la nota media

            if (totalNotas>0){
                resultado=suma/totalNotas;
            }else {
                resultado=0;
            }

            // Mostrar la nota media
            System.out.printf("La nota media de la clase es: %.2f\n", resultado);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al calcular la nota media.");
        }
    }


    public static void estadistica() {
        try {
            // Acceder al documento XML
            Document doc = XML.accederDocumento();
            Node root = doc.getDocumentElement();

            // Inicializar las variables
            List<Integer> nodosPorNivel = new ArrayList<>();  // lista para contar nodos por nivel.
            int niveles = calcularNiveles(root, nodosPorNivel, 0);  // Este es el número de niveles.
            int totalNodos = contarNodos(root);

            // Imprimir el reporte de estadísticas
            System.out.println("Estadísticas del XML:");
            System.out.println("Número de niveles: " + niveles);
            System.out.println("Número total de nodos: " + totalNodos);
            System.out.println("Número de nodos por nivel:");

            // Imprimir nodos por nivel
            for (int i = 0; i < niveles; i++) {  // Solo imprimimos hasta el nivel máximo encontrado
                if (nodosPorNivel.size() > i && nodosPorNivel.get(i) > 0) {
                    System.out.println("Nivel " + i + ": " + nodosPorNivel.get(i) + " nodos");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al calcular las estadísticas del XML.");
        }
    }

    // Método recursivo para contar nodos por nivel
    private static int calcularNiveles(Node nodo, List<Integer> nodosPorNivel, int nivel) {
        int maxNivel = nivel;

        if (nodo.getNodeType() == Node.ELEMENT_NODE) {
            // Si la lista no tiene suficientes niveles, agregar niveles faltantes
            while (nodosPorNivel.size() <= nivel) {
                nodosPorNivel.add(0);  // Agregar un nivel con 0 nodos
            }

            // Incrementar el contador de nodos para el nivel actual
            nodosPorNivel.set(nivel, nodosPorNivel.get(nivel) + 1);

            // Contar los niveles máximos
            NodeList hijos = nodo.getChildNodes();
            for (int i = 0; i < hijos.getLength(); i++) {
                maxNivel = Math.max(maxNivel, calcularNiveles(hijos.item(i), nodosPorNivel, nivel + 1));
            }
        }
        return maxNivel;
    }

    // Método para contar el número total de nodos
    private static int contarNodos(Node nodo) {
        int total = 0; // Inicializar el contador

        // Procesar solo nodos de tipo ELEMENT_NODE
        if (nodo.getNodeType() == Node.ELEMENT_NODE) {
            total = 1; // Contar el nodo actual como un elemento válido
            NodeList hijos = nodo.getChildNodes();
            for (int i = 0; i < hijos.getLength(); i++) {
                if (nodo.getNodeType() == Node.ELEMENT_NODE){
                    total += contarNodos(hijos.item(i)); // Sumar los nodos hijos recursivamente
                }
            }
        }

        return total;
    }



}
