package DataBase;

import Logica.Alumno;
import Logica.ListaAlumnos;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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
    public static void leerXML(){
        try {
            File file=new File("xml/alumnos.xml");
            DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
            DocumentBuilder builder= factory.newDocumentBuilder();
            Document doc=builder.parse(file);
            Alumno alumno =new Alumno();
            int id;
            String nombre;
            int notaFinal;


            NodeList nodeList=doc.getElementsByTagName("alumno");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node=nodeList.item(i);
                Element elemento;

                if (node.getNodeType() == Node.ELEMENT_NODE){ //nodo con elementos y accedemos a uno de ellos
                    elemento=(Element) node;

                    id=Integer.parseInt(elemento.getElementsByTagName("id").item(0).getTextContent());
                    nombre= elemento.getElementsByTagName("nombre").item(0).getTextContent(); //acceder a los datos dentro de la etiqueta, index 0 porq damos por suspuesto q solo hay una etiqueta de nombre en cada, sino hacer otro buvle for
                    notaFinal=Integer.parseInt(elemento.getElementsByTagName("notaFinal").item(0).getTextContent());

                    alumno.setId(id);
                    alumno.setNombre(nombre);
                    alumno.setNotaFinal(notaFinal);

                }
            }
            ListaAlumnos.addItem(alumno);

        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }

    public static void agregarXML(ListaAlumnos lista){

        try {
            File file =new File("xml/alumnos.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            Element root = doc.getDocumentElement();

            for (Alumno alumno: lista.getListaAlumnos()) {

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
