package EJERCICIO1;

import java.sql.*;

public class insertarPedidos {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Uso: java EJERCICIO1.InsertarPedido <cod_comprador> <cod_articulo> <cantidad>");
            return;
        }

        int codComprador = Integer.parseInt(args[0]);
        int codArticulo = Integer.parseInt(args[1]);
        int cantidad = Integer.parseInt(args[2]);

        String url = "jdbc:mysql://localhost:3306/ejercicio1";
        String usuario = "root";
        String contraseña = "Primera2024";

        try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña)) {
            // Verificar si el artículo existe y obtener stock
            String consultaStock = "SELECT stock FROM articulos WHERE cod = ?";
            try (PreparedStatement stmtStock = conexion.prepareStatement(consultaStock)) {
                stmtStock.setInt(1, codArticulo);
                ResultSet rs = stmtStock.executeQuery();

                if (!rs.next()) {
                    System.out.println("Error: El artículo con código " + codArticulo + " no existe.");
                    return;
                }

                int stockDisponible = rs.getInt("stock");

                if (cantidad > stockDisponible) {
                    System.out.println("Error: No hay suficiente stock. Stock disponible: " + stockDisponible);
                    return;
                }

                // Insertar el pedido
                String insertPedido = "INSERT INTO pedidos (id_comprador, id_articulo, cantidad, fecha_pedido) VALUES (?, ?, ?, CURRENT_DATE)";
                try (PreparedStatement stmtInsert = conexion.prepareStatement(insertPedido)) {
                    stmtInsert.setInt(1, codComprador);
                    stmtInsert.setInt(2, codArticulo);
                    stmtInsert.setInt(3, cantidad);

                    int filas = stmtInsert.executeUpdate();

                    // Actualizar el stock del artículo
                    String actualizarStock = "UPDATE articulos SET stock = stock - ? WHERE cod = ?";
                    try (PreparedStatement stmtUpdate = conexion.prepareStatement(actualizarStock)) {
                        stmtUpdate.setInt(1, cantidad);
                        stmtUpdate.setInt(2, codArticulo);
                        stmtUpdate.executeUpdate();
                    }

                    System.out.println("Pedido insertado correctamente. Filas afectadas: " + filas);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al insertar el pedido:");
            e.printStackTrace();
        }
    }
}

