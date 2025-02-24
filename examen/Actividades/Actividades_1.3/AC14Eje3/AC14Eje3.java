//MODIFICACIÓN. Crea un programa Java (nombre del fichero: AC14Eje3.java) que reciba
//desde la línea de comandos un identificados de empleado y un importe. Se debe realizar la
//modificación del salario. La modificación consistirá en sumar al salario del empleado el importe
//introducido. El programa debe visualizar el apellido, el salario antiguo y el nuevo. Si el
//identificador no existe se visualizará un mensaje indicándolo.






import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class AC14Eje3 {
    public static void main(String[] args) throws IOException {
        File fichero = new File("F:\\FP DAW\\FicherosProg\\AleatorioEmple.dat");
        RandomAccessFile file = new RandomAccessFile(fichero, "rw");

        int id, dep;
        double salario;
        char apellido[] = new char[10], aux;
        double salarioAumentar;

        Scanner consola = new Scanner(System.in);
        System.out.print("INTRODUZCA ID DEL EMPLEADO: ");
        id = consola.nextInt();
        int posicion = (id - 1) * 36;
        file.seek(posicion);

        if (posicion >= file.length()) {
            System.out.println("IDENTIFICADOR NO VÁLIDO, EMPLEADO NO EXISTE");
        } else {
            id = file.readInt();
            for (int i = 0; i < apellido.length; i++) {
                aux = file.readChar();//recorro uno a uno los caracteres del apellido
                apellido[i] = aux; //los voy guardando en el array
            }
            String apellidoS = new String(apellido);//convierto a String el array
            dep = file.readInt();
            salario = file.readDouble();

            System.out.println("ID: " + id + " Apellido: " + apellidoS + " Departamento: " + dep +
                    " Salario: " + salario);

            System.out.print("Introduzca cantidad de salario adicional: ");
            salarioAumentar = consola.nextDouble();
            salario = salarioAumentar + salario;
            posicion = posicion + 4 + 20 + 4;
            file.seek(posicion);
            file.writeDouble(salario);

            System.out.println("Apellido: " + apellidoS +
                    " Salario Nuevo: " + salario);
        }
    }
}
