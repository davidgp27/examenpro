package FichDirect;

import java.io.File;
import java.io.IOException;

public class CrearArchivo {
    public static void main(String[] args) {
        // Asegurarse de que el directorio existe
        File directorio = new File("C:\\mi_directorio");
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado.");
            } else {
                System.out.println("No se pudo crear el directorio.");
                return;
            }
        }
        
        // Crear archivo dentro del directorio
        File archivo = new File(directorio, "archivo.txt");

        try {
            if (archivo.createNewFile()) {
                System.out.println("Archivo creado: " + archivo.getName());
            } else {
                System.out.println("El archivo ya existe.");
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error.");
            e.printStackTrace();
        }
    }
}
