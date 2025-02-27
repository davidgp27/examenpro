import java.io.File;
import javax.xml.parsers.*;
import org.w3c.dom.*;
public class LeerXml{
    public static void main(String argv[]) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("Personas.xml"));
            document.getDocumentElement().normalize();
            System.out.println("Elemento raíz: " + document.getDocumentElement().getNodeName());

            //crea una lista con todos los nodos empleado

            NodeList personas = document.getElementsByTagName("Persona");

            //recorrer la lista
            for (int i = 0; i < personas.getLength(); i ++) {

                Node person = personas.item(i); //obtener un nodo

                if (person.getNodeType() == Node.ELEMENT_NODE) {//tipo de nodo
                    Element elemento = (Element) person; //obtener los elementos el nodo
                    System.out.println("DNI: " + getNodo("DNI", elemento));
                    System.out.println("Nombre: " + getNodo("Nombre", elemento));
                    System.out.println("Apellido: " + getNodo("Apellido", elemento));
                    System.out.println("Edad: " + getNodo("Edad", elemento));
                    System.out.println("Casado: " + getNodo("Casado", elemento));
                    System.out.println("Teléfono: " + getNodo("Teléfono", elemento));
                    System.out.println("Dirección: " + getNodo("Dirección", elemento));
                }

            }
        } catch (Exception e) {e.printStackTrace();}
    }//fin de main

    //obtener la información de un nodo
    private static String getNodo(String etiqueta, Element elem)
    {
        NodeList nodo= elem.getElementsByTagName(etiqueta).item(0).getChildNodes();
        Node valornodo = (Node) nodo.item(0);
        return valornodo.getNodeValue();//devuelve el valor del nodo
    }
}//fin de la clase