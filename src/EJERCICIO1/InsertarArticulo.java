package EJERCICIO1;

import java.sql.*;
import java.text.SimpleDateFormat;

public class InsertarArticulo {
    public static void main(String[] args) {
        if (args.length != 5) {
            System.out.println("Uso: java EJERCICIO1.InsertarArticulo <cod> <nombre con espacios> <precio(decimal)> <fecha_caducidad:dd/MM/yyyy> <stock>");
            return;
        }

        int cod = Integer.parseInt(args[0]);
        String nombre = args[1];
        double precio = Double.parseDouble(args[2]);
        String fechaStr = args[3];
        int stock = Integer.parseInt(args[4]);

        String url = "jdbc:mysql://localhost:3306/ejercicio1";
        String usuario = "root";
        String contraseña = "Primera2024";

        try {
            // Convertir fecha en formato dd/MM/yyyy a java.sql.Date
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date utilDate = formatoFecha.parse(fechaStr);
            java.sql.Date fechaCaducidad = new java.sql.Date(utilDate.getTime());

            try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
                 PreparedStatement stmt = conexion.prepareStatement("INSERT INTO articulos (cod, nombre, precio, fecha_caducidad, stock) VALUES (?, ?, ?, ?, ?)")) {

                stmt.setInt(1, cod);
                stmt.setString(2, nombre);
                stmt.setDouble(3, precio);
                stmt.setDate(4, fechaCaducidad);
                stmt.setInt(5, stock);

                int filas = stmt.executeUpdate();
                System.out.println("Artículo insertado. Filas afectadas: " + filas);
            }

        } catch (Exception e) {
            System.out.println("Error al insertar el artículo:");
            e.printStackTrace();
        }
    }
}



