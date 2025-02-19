package FIcheros;
import java.io.*;

public class leer {
    public static void main(String[] args) throws IOException {
        File f1 = new File("C:\\Users\\dagar\\Desktop\\archivo.txt");
        FileReader rd = new FileReader(f1);
        int i;
        while ((i = rd.read()) != -1) {
            System.out.print((char) i);
        }
        rd.close();
    }
}
