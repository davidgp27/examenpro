package FichDirect;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Escribir {
    public static void main(String[] args) {
        File archivo = new File("mi_archivo.txt");

        try {
            FileWriter escritor = new FileWriter(archivo, true); // 'true' para añadir al archivo
            escritor.write("Nuevo contenido en el archivo.\n");
            escritor.close();
            System.out.println("Contenido añadido.");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al escribir en el archivo.");
            e.printStackTrace();
        }
    }
}