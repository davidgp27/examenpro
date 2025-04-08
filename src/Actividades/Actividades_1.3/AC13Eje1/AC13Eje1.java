//CONSULTA. Crea un programa Java (nombre del fichero: AC13Eje1.java) que consulte los
//datos de un empleado del fichero aleatorio. El programa se ejecutará desde la línea de
//comandos y debe recibir un identificador de empleado. Si el empleado existe se visualizarán
//sus datos, si no existe se visualizará un mensaje indicándolo.


import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class AC13Eje1 {
    public static void main(String[] args) throws IOException {
        File fichero = new File ("F:\\FP DAW\\FicherosProg\\AleatorioEmple.dat");
        RandomAccessFile file = new RandomAccessFile(fichero, "r");

        int id, dep;
        double salario;
        char apellido[] = new char [10], aux;

        System.out.print("INTRODUZCA EL IDENTIFICADOR DEL EMPLEADO: ");
        Scanner consola = new Scanner(System.in);
        int identificador = consola.nextInt();
        int posicion = (identificador - 1)*36;
        if (posicion > file.length()){
            System.out.println("EMPLEADO NO EXISTE");
        }
        else {
            file.seek(posicion);
            id = file.readInt();
            for (int i = 0; i < apellido.length; i++) {
                aux = file.readChar();
                apellido[i] = aux;
            }
            String apellidoS = new String (apellido);
            dep = file.readInt();
            salario = file.readDouble();
            System.out.println("ID: " +id+ " Apellido: " +apellidoS+ " Departamento: " +dep+
                    " Salario: " +salario);
        }
    }
}
