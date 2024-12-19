package DataLayer;

import DataBase.XML;
import Logica.Alumno;
import org.w3c.dom.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
public class AlumnoDAO {

    // Método para agregar un nuevo alumno al DataBase.XML
    public static void agregar(Alumno alumno) {
        Document doc=null;
        Element root=null;
        try {
            File file = new File("xml/alumnos.xml");
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
            File file = new File("xml/alumnos.xml");

            doc = XML.accederDocumento();

            NodeList nodeList = doc.getElementsByTagName("alumno");

            for (int i = 0; i < nodeList.getLength(); i++) {
                node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    elemento = (Element) node;

                    id = Integer.parseInt(elemento.getElementsByTagName("id").item(0).getTextContent());
                    nombre = elemento.getElementsByTagName("nombre").item(0).getTextContent();
                    notaFinal = Integer.parseInt(elemento.getElementsByTagName("duracion").item(0).getTextContent());
                    alumnos.add(new Alumno(id, nombre, notaFinal));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return alumnos;
    }

    // Método para actualizar un alumno existente
    public static void actualizar(int id, Alumno nuevoAlumno) {
        Element elemento;
        try {
            File file = new File("xml/alumnos.xml");
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
            File file = new File("xml/alumnos.xml");

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
}


