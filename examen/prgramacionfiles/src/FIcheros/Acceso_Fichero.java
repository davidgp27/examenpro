package FIcheros;

import  java.io.*;

public class Acceso_Fichero {
    public static void main(String[] args){
    Leer_Fichero accediendo = new Leer_Fichero();
    accediendo.lee();
    }
}
class Leer_Fichero {
    public void lee(){
        try {
            FileReader entrada=new FileReader("C:/Users/dagar/Desktop/archivo.txt");
            int c=entrada.read();
            while(c!=-1){
                c= entrada.read();
                char letra=(char)c;
                System.out.print(letra);
            }
        } catch (IOException e) {
            System.out.println("No se ha encontrado el archivo");
        }
    }

}