package EJERCICIO1;

import java.sql.*;
import java.util.Scanner;

public class ConsultarPedido {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduce el código del pedido: ");
        int codPedido = scanner.nextInt();

        String url = "jdbc:mysql://localhost:3306/ejercicio1";
        String usuario = "root";
        String contraseña = "Primera2024";

        String consulta = """
                SELECT 
                    p.CODPEDIDO, 
                    p.fecha_pedido, 
                    p.id_comprador, 
                    a.nombre AS nombre_articulo, 
                    a.precio, 
                    p.cantidad, 
                    (a.precio * p.cantidad) AS total
                FROM pedidos p
                JOIN articulos a ON p.id_articulo = a.cod
                WHERE p.CODPEDIDO = ?
                """;

        try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
             PreparedStatement stmt = conexion.prepareStatement(consulta)) {

            stmt.setInt(1, codPedido);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Datos del pedido:");
                System.out.println("Código del pedido: " + rs.getInt("CODPEDIDO"));
                System.out.println("Fecha del pedido: " + rs.getDate("fecha_pedido"));
                System.out.println("ID del comprador: " + rs.getInt("id_comprador"));
                System.out.println("Nombre del artículo: " + rs.getString("nombre_articulo"));
                System.out.println("Precio unitario: " + rs.getDouble("precio"));
                System.out.println("Cantidad: " + rs.getInt("cantidad"));
                System.out.println("Total: " + rs.getDouble("total"));
            } else {
                System.out.println("No se encontró ningún pedido con el código: " + codPedido);
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar el pedido:");
            e.printStackTrace();
        }
    }
}