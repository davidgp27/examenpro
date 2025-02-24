import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Fruteria_DG {
        public static void main(String[] args) throws IOException {
            File fichero = new File(".\\Fruteria_David Ricardo.dat");
            //declara el fichero de acceso aleatorio
            RandomAccessFile file = new RandomAccessFile(fichero, "rw");
            //arrays con los datos
            String producto[] = {"Mandarinas","Lechugas","Melones","Berenjenas",
                    "Zanahorias","Manzanas", "Tomates"}; //productos
            String grupo[] = {"Frutas","Verduras","Frutas","Hortalizas",
                    "Hortalizas","Frutas", "Verduras"}; //grupo
            Double precio[]={2.50, 1.25, 0.90, 2.30,
                    0.80, 2.40, 1.85};//precios
            int stock[] = {1, 0, 0, 1, 1, 1, 1};
            int borradoLogico[]  = {0,0,0,0,0,0,0};
            StringBuffer buffer = null ;//buffer para almacenar apellido
            int n=producto.length;//numero de elementos del array
            StringBuffer buffer2 = null;
            int m=grupo.length;

            for (int i=0;i<n; i++){ //recorro los arrays
                file.writeInt(i+1); //uso i+1 para identificar producto
                buffer = new StringBuffer( producto[i] );
                buffer.setLength(17); //17 caracteres para el producto
                file.writeChars(buffer.toString());//insertar producto
                buffer2 = new StringBuffer( grupo[i] );
                buffer2.setLength(16); //16 caracteres para el producto
                file.writeChars(buffer2.toString());//insertar producto
                file.writeDouble(precio[i]);//insertar precio
                file.writeInt(stock[i]);
                file.writeInt(borradoLogico[i]);

            }
            file.close(); //cerrar fichero
        }
    }


