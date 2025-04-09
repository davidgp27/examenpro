import java.sql.*;

public class pruebaexecute {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/dbempresa";
        String usuario = "root";
        String contraseña = "Primera2024";

        try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
             Statement sentencia = conexion.createStatement()) {

            // PRIMERA CONSULTA: SELECT
            String sqlSelect = "SELECT * FROM departamentos";
            boolean valor = sentencia.execute(sqlSelect);

            if (valor) {
                ResultSet rs = sentencia.getResultSet();
                ResultSetMetaData metaData = rs.getMetaData();
                int columnas = metaData.getColumnCount();

                System.out.println("Resultados de SELECT:");
                while (rs.next()) {
                    for (int i = 1; i <= columnas; i++) {
                        System.out.print(rs.getString(i) + "\t");
                    }
                    System.out.println();
                }
                rs.close();
            } else {
                System.out.println("No se obtuvieron resultados de SELECT.");
            }

            // SEGUNDA CONSULTA: UPDATE
            String sqlUpdate = "UPDATE departamentos SET dnombre = LOWER(dnombre)";
            boolean updateResult = sentencia.execute(sqlUpdate);

            if (!updateResult) {
                int filasAfectadas = sentencia.getUpdateCount();
                System.out.println("UPDATE ejecutado. Filas afectadas: " + filasAfectadas);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

