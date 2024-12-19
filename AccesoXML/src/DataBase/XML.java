package DataBase;

import Logica.Alumno;
import Logica.ListaAlumnos;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;


public class XML {
    public static Document accederDocumento() {
        File file = new File("xml/alumnos.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        Document doc = null;
        try {
            builder = factory.newDocumentBuilder();
            doc = builder.parse(file);

        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
        return doc;
    }

    public static void guardarCambios(Document doc, File file) {

        TransformerFactory transformerFactory;
        Transformer transformer;
        DOMSource source;
        StreamResult result;

        try {

            transformerFactory = TransformerFactory.newInstance();
            transformer = transformerFactory.newTransformer();
            source = new DOMSource(doc);
            result = new StreamResult(file);
            transformer.transform(source, result);

        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    public static void cambiar(ListaAlumnos lista) {

        try {
            File file = new File("xml/alumnos.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            Element root = doc.getDocumentElement();

            for (Alumno alumno : lista.getListaAlumnos()) {

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
                StreamResult result = new StreamResult(file);
                transformer.transform(source, result);
            }

        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }
}
