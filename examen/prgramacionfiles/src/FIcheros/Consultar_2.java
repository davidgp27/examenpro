package FIcheros;
import java.io.*;
public class Consultar_2 {
    public static void main(String[] args) throws IOException {
        File fichero = new File(".\\AleatorioEmple.dat");
        //declara el fichero de acceso aleatorio
        RandomAccessFile file = new RandomAccessFile(fichero, "r");
        //
        int id, dep, posicion;
        Double salario;
        char apellido[] = new char[10], aux;
        posicion=0; //para situarnos al principio
        int identificador = 5;
        posicion = (identificador - 1 ) * 36; //calculo donde empieza el registro
        if(posicion >= file.length())
            System.out.println("ID: " + posicion + ", NO EXISTE EMPLEADO...");
        else{
            file.seek(posicion);//nos posicionamos
            id=file.readInt(); // obtengo id de empleado
            //obtener resto de los datos, como en el ejemplo anterior
        }
    }
}

