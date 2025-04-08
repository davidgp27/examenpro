import java.io.*;
import java.sql.*;

public class script {
    public static void ejecutarScriptMySQL() {
        File scriptFile = new File("C://Users//Usuario_Mañana//Downloads//scriptmysql.sql");
        System.out.println("\n\nFichero de consulta : " +
                scriptFile.getName());
        System.out.println("Convirtiendo el fichero a cadena...");
        BufferedReader entrada = null;
        try {
            entrada = new BufferedReader(new FileReader(scriptFile));
        } catch (FileNotFoundException e) {
            System.out.println("ERROR NO HAY FILE: " + e.getMessage());
        }
        String linea = null;
        StringBuilder stringBuilder = new StringBuilder();
        String salto = System.getProperty("line.separator");
        try {
            while ((linea = entrada.readLine()) != null) {
                stringBuilder.append(linea);
                stringBuilder.append(salto);
            }
        } catch (IOException e) {
            System.out.println("ERROR de E/S, al operar " +
                    e.getMessage());
        }
        String consulta = stringBuilder.toString();
        System.out.println(consulta);
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR Driver:" + e.getMessage());
        }
        try {
            Connection connmysql = DriverManager.getConnection
                    ("jdbc:mysql://localhost/script?allowMultiQueries=true",
                            "root", "Primera2024");
            Statement sents = connmysql.createStatement();
            int res = sents.executeUpdate(consulta);
            System.out.println("Script creado con  xito, res = " + res);
            connmysql.close();
            sents.close();
        } catch (SQLException e) {
            System.out.println("ERROR AL EJECUTAR EL SCRIPT: "
                    + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ejecutarScriptMySQL();
    }

}

