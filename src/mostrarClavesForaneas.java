import java.sql.*;

public class mostrarClavesForaneas {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/Empleados1";
        String user = "root";
        String password = "Primera2024";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            DatabaseMetaData metaData = conn.getMetaData();

            // Obtener claves foráneas que referencian a la tabla departamentos
            ResultSet foreignKeys = metaData.getExportedKeys(null, null, "departamentos");

            System.out.println("TABLAS QUE REFERENCIAN A DEPARTAMENTOS:");
            System.out.println("==============================");

            while (foreignKeys.next()) {
                String fkTableName = foreignKeys.getString("FKTABLE_NAME");
                String fkColumnName = foreignKeys.getString("FKCOLUMN_NAME");
                String pkTableName = foreignKeys.getString("PKTABLE_NAME");
                String pkColumnName = foreignKeys.getString("PKCOLUMN_NAME");

                System.out.println("Tabla PK: " + pkTableName +
                        ", Clave Primaria: " + pkColumnName);
                System.out.println("Tabla FK: " + fkTableName +
                        ", Clave Ajena: " + fkColumnName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
