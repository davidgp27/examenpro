package FIcheros;
import java.io.*;
public class dataoutinStream {
    public static void main(String [] args) throws IOException {
        File cadena = new File("cadena.dat");
        FileOutputStream out = new FileOutputStream(cadena);
        DataOutputStream outarrasy = new DataOutputStream(out);

        String heroes[] = {"lina","kunka","ursa","fenix","abbadon","pa","od","viper","dazzle","cristal", "jakiro"};
        int kills [] = {10, 15, 30, 5, 8, 22, 26, 15, 3, 5, 3};
        for (int i = 0; i < heroes.length;i++) {
            outarrasy.writeUTF(heroes[i]);
            outarrasy.writeInt(kills[i]);
        }
        outarrasy.close();

        FileInputStream in = new FileInputStream(cadena);
        DataInputStream im = new DataInputStream(in);
        String n;
        int e;
        try {
            while (true) {
                n = im.readUTF();
                e = im.readInt();
                System.out.println("Heroe: "+n +", kills: "+e);
            }
        } catch (IOException ex) {
        }
        im.close();
    }
}
