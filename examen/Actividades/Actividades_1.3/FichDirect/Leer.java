package FichDirect;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Leer {
    public static void main(String[] args) {
        File archivo = new File("mi_archivo.txt");
        try {
            Scanner lector = new Scanner(archivo);
            while (lector.hasNextLine()) {
                System.out.println(lector.nextLine());
            }
            lector.close();
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no fue encontrado.");
            e.printStackTrace();
        }
    }
}
