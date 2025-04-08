import java.sql.*;

public class MostrarCLavePrimaria {
    public static void main(String[] args) {
        // Configura estos valores según tu entorno
        String url = "jdbc:mysql://localhost:3306/Empleados1?useSSL=false";
        String user = "root"; // tu usuario de MySQL
        String password = "Primera2024"; // tu contraseña de MySQL

        try {
            // Carga explícita del driver (opcional para nuevas versiones)
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(url, user, password)) {
                DatabaseMetaData metaData = conn.getMetaData();

                // Obtener claves primarias de la tabla departamentos
                ResultSet primaryKeys = metaData.getPrimaryKeys(null, null, "departamentos");

                System.out.println("CLAVE PRIMARIA TABLA DEPARTAMENTOS:");
                System.out.println("============================");

                while (primaryKeys.next()) {
                    String pkName = primaryKeys.getString("COLUMN_NAME");
                    System.out.println("Clave Primaria: " + pkName);
                }

            } catch (SQLException e) {
                System.out.println("Error en la conexión o consulta:");
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("No se encontró el driver JDBC de MySQL");
            e.printStackTrace();
        }
    }
}
