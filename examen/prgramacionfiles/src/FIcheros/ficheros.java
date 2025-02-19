package FIcheros;
import javax.swing.*;
import java.io.*;
public class ficheros {
     public static void main(String[] args) {
        File dir = new File("C:\\Users\\dagar\\Desktop\\1er Curso");
        String[] archivos = dir.list();
        System.out.printf("Ficheros en el directorio actual: %d %n", archivos.length);
        for (int i = 0; i < archivos.length; i++){
            File f2 = new File(dir, archivos[i]);
            System.out.printf("Nombre: %s, es fichero?: %b, es directorio?: %b %n", archivos[i], f2.isFile(),f2.isDirectory());
        }
     }
}
