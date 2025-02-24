package FichDirect;

import java.io.File;

public class Listar {
    public static void main(String[] args) {
        File directorio = new File("mi_directorio");

        if (directorio.exists() && directorio.isDirectory()) {
            String[] archivos = directorio.list();
            if (archivos != null) {
                for (String archivo : archivos) {
                    System.out.println(archivo);
                }
            }
        } else {
            System.out.println("El directorio no existe.");
        }
    }
}