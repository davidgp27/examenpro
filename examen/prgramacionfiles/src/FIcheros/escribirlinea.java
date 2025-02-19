package FIcheros;

import java.io.*;


public class escribirlinea {
    public static void main(String[] args){
        try {
            BufferedWriter fichero = new BufferedWriter(new FileWriter("ARCHIVO.TXT"));
            for (int i = 1; i < 11; i++) {
                fichero.write("Fila numero: " + i);
                fichero.newLine();
            }
            fichero.close();
        } catch (
        FileNotFoundException e) {
            System.out.println("No se encuentra el fichero");
        } catch (
        IOException io) {
            System.out.println("error de E/S");
        }

    }
}
