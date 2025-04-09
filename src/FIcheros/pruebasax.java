package FIcheros;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.jar.Attributes;

public class pruebasax {
    public static void main(String[] args)
            throws FileNotFoundException, IOException, SAXException {
        XMLReader procesadorXML = XMLReaderFactory.createXMLReader();
        GestionContenido gestor= new GestionContenido();
        procesadorXML.setContentHandler(gestor);
        InputSource fileXML = new InputSource("alumnos.xml");
        procesadorXML.parse(fileXML);
    }
}//fin PruebaSax1
class GestionContenido extends DefaultHandler {
    public GestionContenido() {
        super();
    }
    public void startDocument() {
        System.out.println("Comienzo del Documento XML");
    }


    public void endDocument() {
        System.out.println("Final del Documento XML");
    }
    public void startElement(String uri, String nombre,
                             String nombreC, Attributes atts) {
        System.out.printf("\tPrincipio Elemento: %s %n",nombre);
    }
    public void endElement(String uri, String nombre,
                           String nombreC) {
        System.out.printf("\tFin Elemento: %s %n", nombre);
    }
    public void characters(char[] ch, int inicio, int longitud)
            throws SAXException {
        String car=new String(ch, inicio, longitud);
        //quitar saltos de línea
        car = car.replaceAll("[\t\n]","");
        System.out.printf ("\tCaracteres: %s %n", car);
    }
}//fin GestionContenido