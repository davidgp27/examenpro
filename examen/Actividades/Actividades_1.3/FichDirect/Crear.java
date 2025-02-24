package FichDirect;

import java.io.File;

public class Crear {
    public static void main(String[] args) {
        File directorio = new File("C:\\mi_directorio"); 


        if (directorio.mkdir()) {
            System.out.println("Directorio creado.");
        } else {
            System.out.println("El directorio ya existe o no se pudo crear.");
        }
    }
}