import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;
public class Ficheros1_5 {
    public static void main(String [] argv) throws IOException, ParserConfigurationException, TransformerException{
        File fichero = new File("Personas1.dat");
        RandomAccessFile random = new RandomAccessFile(fichero, "r");
        char dni[] = new char[10], aux1;
        char nombre[] = new char[15], aux2;
        char apellido [] = new char [30], aux3;
        int edad, posicion = 0;
        boolean casado;
        char telefono[] = new char[15], aux4;
        char direccion[] = new char[60], aux5;

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, "Personas", null);
            document.setXmlVersion("1.0");
            while (random.getFilePointer() < random.length()) {
                random.seek(posicion);
                for (int i = 0; i < dni.length; i++) {
                    aux1 = random.readChar();//recorro uno a uno los caracteres del apellido
                    dni[i] = aux1; //los voy guardando en el array
                }
                String dnis= new String(dni).trim();//convierto a String el array
                for (int i = 0; i < nombre.length; i++) {
                    aux2 = random.readChar();//recorro uno a uno los caracteres del apellido
                    nombre[i] = aux2; //los voy guardando en el array
                }
                String nombres= new String(nombre).trim();//convierto a String el array
                for (int i = 0; i < apellido.length; i++) {
                    aux3 = random.readChar();//recorro uno a uno los caracteres del apellido
                    apellido[i] = aux3; //los voy guardando en el array
                }
                String apellidos= new String(apellido).trim();//convierto a String el array
                edad =random.readInt(); //obtengo edad
                casado = random.readBoolean(); //obtengo casado
                for (int i = 0; i < telefono.length; i++) {
                    aux4 = random.readChar();//recorro uno a uno los caracteres del apellido
                    telefono[i] = aux4; //los voy guardando en el array
                }
                String telefonos= new String(telefono).trim();//convierto a String el array
                for (int i = 0; i < direccion.length; i++) {
                    aux5 = random.readChar();//recorro uno a uno los caracteres del apellido
                    direccion[i] = aux5; //los voy guardando en el array
                }
                String direcciones= new String(direccion).trim();//convierto a String el array
                    Element raiz = document.createElement("Persona"); //nodo empleado
                    document.getDocumentElement().appendChild(raiz);
                    CrearElemento("DNI",dnis, raiz, document); //añadir DNO
                    CrearElemento("Nombre",nombres, raiz, document); //Nombre
                    CrearElemento("Apellido",apellidos, raiz, document); //Apellidos
                    CrearElemento("Edad",Integer.toString(edad), raiz, document); //añadir Edad
                    CrearElemento("Casado",Boolean.toString(casado), raiz, document); //casado true
                    CrearElemento("Teléfono",telefonos, raiz, document); //Teléfono
                    CrearElemento("Dirección",direcciones, raiz, document); //Dirección
                posicion= posicion + 265 ; // me posiciono para el sig empleado

                if (random.getFilePointer() == random.length()) {
                    break;
                }

            }
            Source source = new DOMSource(document);
            Result result = new StreamResult(new java.io.File("Personas.xml"));

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
        random.close();
    }//fin del main
    static void CrearElemento(String datoPerso, String valor,
                              Element raiz, Document document){
        Element elem = document.createElement(datoPerso); //creamos hijo
        Text text = document.createTextNode(valor); //damos valor
        raiz.appendChild(elem); //pegamos el elemento hijo a la raiz
        elem.appendChild(text); //pegamos el valor
    }

}
