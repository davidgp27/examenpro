//INSERCIÓN. Crea un programa Java (nombre del fichero: AC13Eje2.java) que inserte datos en
//el fichero aleatorio. El programa se ejecutará desde la línea de comandos y debe recibir 4
//parámetros: Identificador de empleado, apellido, departamento y salario. Antes de insertar se
//comprobará si el identificador existe, en ese caso se debe visualizar un mensaje indicándolo; si
//no existe se deberá insertar.





import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class AC13Eje2 {
    public static void main(String[] args) throws IOException {
        File fichero = new File("F:\\FP DAW\\FicherosProg\\AleatorioEmple.dat");
        RandomAccessFile file = new RandomAccessFile(fichero, "rw");

        int id, dep, posicion;
        double salario;
        char apellido[] = new char[10], aux;

        Scanner consola = new Scanner(System.in);
        System.out.print("- INTRODUZCA EL IDENTIFICADOR DEL NUEVO EMPLEADO: ");
        int identificador = consola.nextInt();
        posicion = (identificador - 1) * 36;

        if (posicion < file.length()) {
            System.out.println("EMPLEADO YA EXISTE");
        } else {
            file.seek(posicion);
            file.writeInt(identificador);
            System.out.print("Introduzca el apellido: ");
            consola.nextLine(); // Consumir salto de línea pendiente
            String apellidoS = consola.nextLine();
            StringBuffer buffer = new StringBuffer(apellidoS);
            buffer.setLength(10);
            file.writeChars(buffer.toString());
            System.out.print("Inserte el departamento: ");
            dep = consola.nextInt();
            file.writeInt(dep);
            System.out.print("Inserte el salario: ");
            salario = consola.nextDouble();
            file.writeDouble(salario);
            System.out.print("EMPLEADO AÑADIDO: ");
            System.out.println("ID: " +identificador+ " Apellido: " +apellidoS+ " Departamento: " +dep+
                    " Salario: " +salario);
        }
    }
}
