package DataLayer;

import DataBase.XML;
import Logica.Alumno;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
public class AlumnoDAO {
    private final String filePath;

    // Constructor que recibe la ruta del archivo DataBase.XML
    public AlumnoDAO(String filePath) {

        this.filePath = filePath;
    }

    // Método para agregar un nuevo alumno al DataBase.XML
    public void agregar(Alumno alumno) {
        Document doc=null;
        Element root=null;
        try {

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
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para leer todos los alumno del DataBase.XML
    public List<Alumno> leerTodos() {
        int id;
        String nombre;
        int notaFinal;
        Node node;
        Element elemento;
        List<Alumno> alumnos = new ArrayList<>();

        try {
            File file = new File(filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

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
    public void actualizar(int id, Alumno nuevoAlumno) {
        Element elemento;
        try {
            File file = new File(filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

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
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar un alumno por ID
    public void eliminar(int id) {
        try {
            File file = new File(filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

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
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


