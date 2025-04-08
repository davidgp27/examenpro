import java.sql.*;

public class InsertarDepartamento {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Uso: java InsertarDepartamento <codigo> <nombre> <presupuesto>");
            return;
        }

        int codigo = Integer.parseInt(args[0]);
        String nombre = args[1];
        int presupuesto = Integer.parseInt(args[2]);

        String url = "jdbc:mysql://localhost:3306/empleados1";
        String usuario = "root";
        String contraseña = "Primera2024";

        try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
             PreparedStatement stmt = conexion.prepareStatement("INSERT INTO departamentos VALUES (?, ?, ?)")) {

            stmt.setInt(1, codigo);
            stmt.setString(2, nombre);
            stmt.setInt(3, presupuesto);

            int filas = stmt.executeUpdate();
            System.out.println("Departamento insertado. Filas afectadas: " + filas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
