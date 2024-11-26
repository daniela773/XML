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
import java.util.List;

public class XML {
    public static void leerXML(){
        try {
            File file=new File("xml/entrenamientos.xml");
            DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
            DocumentBuilder builder= factory.newDocumentBuilder();
            Document doc=builder.parse(file);
            Entrenamiento entrenamiento =new Entrenamiento();
            int id;
            String nombre;
            int duracion;
            String nivel;
            Element elemento;

            NodeList nodeList=doc.getElementsByTagName("entrenamiento");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node=nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE){ //nodo con elemntos y accedemos a uno de ellos
                    elemento=(Element) node;

                    id=Integer.parseInt(elemento.getAttribute("id")); //acceder a un atributo
                    nombre= elemento.getElementsByTagName("nombre").item(0).getTextContent(); //acceder a los datos dentro de la etiqueta, index 0 porq damos por suspuesto q solo hay una etiqueta de nombre en cada, sino hacer otro buvle for
                    duracion=Integer.parseInt(elemento.getElementsByTagName("duracion").item(0).getTextContent());
                    nivel=elemento.getElementsByTagName("nivel").item(0).getTextContent();

                    entrenamiento.setId(id);
                    entrenamiento.setNombre(nombre);
                    entrenamiento.setDuracion(duracion);
                    entrenamiento.setNivel(nivel);

                }
            }
            ListaEntrenamiento.addItem(entrenamiento);

        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }

    public static void agregarXML(ListaEntrenamiento lista){

        try {
            File file =new File("xml/entrenamientos.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            Element root = doc.getDocumentElement();

            for (Entrenamiento entrenamiento: lista.getListaEntrenamiento()) {

                Element nuevoEntrenamiento = doc.createElement("entrenamiento");
                nuevoEntrenamiento.setAttribute("id", String.valueOf(entrenamiento.getId()));

                Element nombre = doc.createElement("nombre");
                nombre.setTextContent(entrenamiento.getNombre());

                Element duracion = doc.createElement("duracion");
                duracion.setTextContent(String.valueOf(entrenamiento.getDuracion()));

                Element nivel = doc.createElement("nivel");
                nivel.setTextContent(entrenamiento.getNivel());

                nuevoEntrenamiento.appendChild(nombre);
                nuevoEntrenamiento.appendChild(duracion);
                nuevoEntrenamiento.appendChild(nivel);

                root.appendChild(nuevoEntrenamiento);

                // Guardar los cambios en el archivo XML
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
