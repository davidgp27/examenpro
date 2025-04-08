import java.sql.*;

public class ModificarSalario {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Uso: java ModificarSalario <departamento> <aumento>");
            return;
        }

        int departamento = Integer.parseInt(args[0]);
        int aumento = Integer.parseInt(args[1]);

        String url = "jdbc:mysql://localhost:3306/empleados1";
        String usuario = "root";
        String contraseña = "Primera2024";

        try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
             PreparedStatement stmt = conexion.prepareStatement("UPDATE personas SET salario = salario + ? WHERE departamento_id = ?")) {

            stmt.setInt(1, aumento);
            stmt.setInt(2, departamento);

            int filas = stmt.executeUpdate();
            System.out.println("Salario modificado. Filas afectadas: " + filas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

