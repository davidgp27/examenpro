//A continuación, has otro programa Java (o crea un método dentro del anterior programa)
//que muestre los identificadores de los empleados borrados (nombre del fichero:
//AC14Eje4-1.java).





import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class AC14Eje4_1 {
    public static void main(String[] args) throws IOException {
        File fichero = new File("F:\\FP DAW\\FicherosProg\\AleatorioEmple.dat");
        RandomAccessFile file = new RandomAccessFile(fichero, "r");
        int id, posicion, dep;
        double salario;
        char apellido[] = new char[10], aux;
        posicion = 0;
        file.seek(posicion);
        for (;;) {
            id = file.readInt();
            if (id == -1) {
                for (int i = 0; i < apellido.length; i++) {
                    aux = file.readChar();//recorro uno a uno los caracteres del apellido
                    apellido[i] = aux; //los voy guardando en el array
                }
                String apellidoS= new String(apellido);
                dep = file.readInt();
                salario = file.readDouble();
                System.out.println("ID: " +id+ " Apellido: " +apellidoS+ " Departamento: " +dep+
                        " Salario: " +salario);
                posicion = posicion + 32;
            } else {
                posicion = posicion + 32;
            }
        }
    }
}