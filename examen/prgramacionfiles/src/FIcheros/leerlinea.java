package FIcheros;
import java.io.*;
public class leerlinea {
    public static void main (String[] args){
        try {
            BufferedReader nuevo = new BufferedReader(new FileReader("ARCHIVO.TXT"));
            String linea;
            while ((linea = nuevo.readLine()) != null) {
                System.out.println(linea);
                nuevo.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("no se encuentra el fichero");
        } catch (IOException io) {
            System.out.println("error de E/S");
        }

    }
}
