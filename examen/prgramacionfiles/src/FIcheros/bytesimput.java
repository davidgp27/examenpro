package FIcheros;

import java.io.*;

public class bytesimput {
    public static void main(String[] args) throws IOException {
        File fichero = new File("Bytes.dat");
        FileOutputStream out = new FileOutputStream(fichero, true);
        FileInputStream in = new FileInputStream(fichero);
        int i;
        for (i = 0; i <100;i++){
            out.write(i);
        }
        out.close();

        while((i = in.read()) !=-1){
            System.out.println(i);
        }
        in.close();
    }
}
