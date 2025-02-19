package FIcheros;
import java.io.*;

public class leeercadena {
    public static void main(String[] args) throws IOException {
        File david1 = new File("heroes.txt");
        FileWriter cad = new FileWriter(david1, true);
        String prov[] = {"axe","tidehunter","sand king", "abbadon","doom","slardar","legion comander", "necro" };
        for (int i=0; i < prov.length; i++) {
            cad.write(prov [i]+ "\n");
        }
    cad.close();
    }
}
