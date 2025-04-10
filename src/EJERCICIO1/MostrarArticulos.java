package EJERCICIO1;

import java.sql.*;
import java.util.Scanner;

public class MostrarArticulos {
    public static void main(String[] args) {
        // Configuración de la conexión
        String url = "jdbc:mysql://localhost:3306/ejercicio1";
        String usuario = "root";
        String contraseña = "Primera2024";

        // Consulta SQL para obtener todos los artículos
        String consulta = "SELECT COD, nombre, precio, fecha_caducidad, stock FROM articulos ORDER BY COD";

        try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
             Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(consulta)) {

            System.out.println("\nLISTADO DE ARTÍCULOS:");

            // Recorrer todos los resultados
            while (rs.next()) {
                int cod = rs.getInt("COD");
                String nombre = rs.getString("nombre");
                float precio = rs.getFloat("precio");
                Date fechaCad = rs.getDate("fecha_caducidad");
                int stock = rs.getInt("stock");

                System.out.println("Código: " + cod);
                System.out.println("Nombre: " + nombre);
                System.out.println("Precio: " + precio);
                System.out.println("Fecha caducidad: " + fechaCad);
                System.out.println("Stock: " + stock);
                System.out.println("---------------------");
            }

            System.out.println("Fin del listado");

        } catch (SQLException e) {
            System.out.println("Error al consultar los artículos:");
            e.printStackTrace();
        }
    }
}