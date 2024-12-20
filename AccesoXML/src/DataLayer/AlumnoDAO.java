package DataLayer;

import DataBase.XML;
import Logica.Alumno;
import org.w3c.dom.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
public class AlumnoDAO {

    // Método para agregar un nuevo alumno al DataBase.XML
    public static void agregar(Alumno alumno) {
        Document doc=null;
        Element root=null;
        try {
            File file = new File("C:\\xampp\\htdocs\\dashboard\\datosajax\\datos\\alumnos.xml");
            doc=XML.accederDocumento();
            root = doc.getDocumentElement();

            // Crear un nuevo elemento "alumno"
            Element nuevoAlumno = doc.createElement("alumno");

            Element id = doc.createElement("id");
            id.setTextContent(String.valueOf(alumno.getId()));

            Element nombre = doc.createElement("nombre");
            nombre.setTextContent(alumno.getNombre());

            Element notaFinal = doc.createElement("notaFinal");
            notaFinal.setTextContent(String.valueOf(alumno.getNotaFinal()));

            nuevoAlumno.appendChild(id);
            nuevoAlumno.appendChild(nombre);
            nuevoAlumno.appendChild(notaFinal);

            root.appendChild(nuevoAlumno);

            // Guardar los cambios en el archivo DataBase.XML
            XML.guardarCambios(doc,file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para leer todos los alumno del DataBase.XML
    public static List<Alumno> leerTodos() {
        int id;
        String nombre;
        int notaFinal;
        Document doc;
        Node node;
        Element elemento;
        List<Alumno> alumnos = new ArrayList<Alumno>();

        try {
            File file = new File("C:\\xampp\\htdocs\\dashboard\\datosajax\\datos\\alumnos.xml");

            doc = XML.accederDocumento();

            NodeList nodeList = doc.getElementsByTagName("alumno");

            for (int i = 0; i < nodeList.getLength(); i++) {
                node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    elemento = (Element) node;

                    id = Integer.parseInt(elemento.getElementsByTagName("id").item(0).getTextContent());
                    nombre = elemento.getElementsByTagName("nombre").item(0).getTextContent();
                    notaFinal = Integer.parseInt(elemento.getElementsByTagName("notaFinal").item(0).getTextContent());
                    alumnos.add(new Alumno(id, nombre, notaFinal));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return alumnos;
    }

    public static void leer( ){
        List<Alumno> alumnos=leerTodos();

        for(Alumno alumno:alumnos){
            System.out.println("Id: "+alumno.getId()+"\n" +
                    "Nombre: "+alumno.getNombre()+"\n" +
                    "Nota final: "+alumno.getNotaFinal()+"\n");
        }
    }

    // Método para actualizar un alumno existente
    public static void actualizar(int id, Alumno nuevoAlumno) {
        Element elemento;
        try {
            File file = new File("C:\\xampp\\htdocs\\dashboard\\datosajax\\datos\\alumnos.xml");
            Document doc = XML.accederDocumento();

            NodeList nodeList = doc.getElementsByTagName("alumno");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    elemento = (Element) node;

                    if ((Integer.parseInt(elemento.getElementsByTagName("id").item(0).getTextContent())) == id) {
                        elemento.getElementsByTagName("id").item(0).setTextContent(String.valueOf(nuevoAlumno.getId()));
                        elemento.getElementsByTagName("nombre").item(0).setTextContent(nuevoAlumno.getNombre());
                        elemento.getElementsByTagName("notaFinal").item(0).setTextContent(String.valueOf(nuevoAlumno.getNotaFinal()));
                    }
                }
            }

// Guardar los cambios en el archivo DataBase.XML
            XML.guardarCambios(doc,file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar un alumno por ID
    public static void eliminar(int id) {
        try {
            File file = new File("C:\\xampp\\htdocs\\dashboard\\datosajax\\datos\\alumnos.xml");

            Document doc = XML.accederDocumento();

            NodeList nodeList = doc.getElementsByTagName("alumno");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) node;

                    if ((Integer.parseInt(elemento.getElementsByTagName("id").item(0).getTextContent())) == id) {
                        elemento.getParentNode().removeChild(elemento);
                        break;
                    }
                }
            }

            // Guardar los cambios en el archivo DataBase.XML
            XML.guardarCambios(doc,file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ajax(){
        try {
            // URL del archivo XML
            URI uri = new URI("http://localhost/dashboard/datosajax/index.html");

            // Verificar si el escritorio puede abrir una URL
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                desktop.browse(uri); // Abre la URL en el navegador predeterminado
            } else {
                System.out.println("El escritorio no es compatible.");
            }
        } catch (URISyntaxException e) {
            System.out.println("URL mal formada: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error al abrir la URL: " + e.getMessage());
        }
    }
}


