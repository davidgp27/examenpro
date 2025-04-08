import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCExample2 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/empresa_db";
        String user = "root";
        String password = "Primera2024";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM departamentos");

            int rowCount = 0;
            while (rs.next()) {
                rowCount++;
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String ubicacion = rs.getString("ubicacion");

                System.out.println("Fila #" + rowCount + ": ID: " + id + ", Nombre: " + nombre + ", Ubicación: " + ubicacion);
            }

            System.out.println("Total de filas recuperadas: " + rowCount);

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
