//BORRADO. Crea un programa Java (nombre del fichero: AC14Eje4.java) que al ejecutarlo
//desde la línea de comandos reciba un identificador de empleado y lo borre. Se hará un borrado
//lógico marcando el registro con la siguiente información: el identificador será igual a -1, el
//apellido será igual al identificador que se borra, y el departamento y salario serán 0.






import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class AC14Eje4 {
    public static void main(String[] args) throws IOException {
        File fichero = new File("F:\\FP DAW\\FicherosProg\\AleatorioEmple.dat");
        RandomAccessFile file = new RandomAccessFile(fichero, "rw");
        int id, dep;
        double salario;
        char apellido[] = new char[10], aux;

        Scanner consola = new Scanner(System.in);
        System.out.print("INTRODUZCA EL ID DE EMPLEADO A BORRAR: ");
        int identificador= consola.nextInt();
        int posicion = (identificador - 1) * 36;
        file.seek(posicion);

        file.writeInt(-1);
        posicion = posicion + 20;
        file.seek(posicion);
        if (posicion > file.length()) {
            System.out.println("EMPLEADO NO EXISTE");
        } else {
        file.writeInt(0);
        file.writeLong(0L);
        file.writeDouble(0.0);
        System.out.println("EMPLEADO BORRADO");
        posicion = (identificador - 1) * 36;
        file.seek(posicion); // nos posicionamos para leer todos los datos
        id = file.readInt();
        for (int i = 0; i < apellido.length; i++) {
            aux = file.readChar();//recorro uno a uno los caracteres del apellido
            apellido[i] = aux; //los voy guardando en el array
        }
        String apellidoS= new String(apellido);
        dep = file.readInt();
        salario = file.readDouble();
        System.out.println("ID: " +id+ " Apellido: " +apellidoS+ " Departamento: " +dep+
                " Salario: " +salario);
        }
    }
}