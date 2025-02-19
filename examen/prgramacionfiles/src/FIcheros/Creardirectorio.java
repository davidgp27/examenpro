package FIcheros;

import java.io.File;
import java.io.IOException;

public class Creardirectorio {
    public static void main(String[] args) {
        File d = new File ("newdirectorio");
        File f1 = new File (d,"david1.txt");
        File f2 = new File(d,"david2.txt");
        d.mkdir();
        try {
            if (f1.createNewFile()) {
                System.out.println("Fichero1 creado correctamente");
            } else {
                System.out.println("No se ha podido crear correctamente");
            }
            if (f2.createNewFile()){
                System.out.println("Fichero2 creado correctamente");
            } else {
                System.out.println("No se ha podido crear correctamente");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        f1.renameTo(new File(d,"Fichero1nuevo"));
        try {
            File f3 = new File(d,"FIchero3.txt");
            f3.createNewFile();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        if (f2.delete()){
            System.out.println("Fichero borrado");
        } else {
            System.out.println("No se ha podido borrar el fichero");
        }

    }
}
