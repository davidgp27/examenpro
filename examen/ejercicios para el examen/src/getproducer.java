
import java.sql.*;

public class getproducer {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/empleados1";
        String usuario = "root";
        String contraseña = "Primera2024";

        try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña)) {
            DatabaseMetaData metaData = conexion.getMetaData();
            ResultSet procedimientos = metaData.getProcedures(null, null, "%");

            System.out.println("Procedimientos y funciones en la base de datos:");
            while (procedimientos.next()) {
                System.out.println(procedimientos.getString("PROCEDURE_NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

