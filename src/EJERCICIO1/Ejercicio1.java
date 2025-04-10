package EJERCICIO1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Ejercicio1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Insertar ARTICULO");
            System.out.println("2. Insertar PEDIDO");
            System.out.println("3. Modificar stock");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    insertarArticulo(args);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);

        scanner.close();
    }
    private static void insertarArticulo(String[] args){

        try {
            Class.forName("com.mysql.jdbc.Driver"); //Cargar el driver
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/ejercicio1", "root", "Primera2024");

            //Recuperar arg de main
            String cod = args[0];        //articulo
            String nombre = args[1];    //nombre1
            String precio = args[2];        //precio
            String fecha_cad = args[3];        //fecha
            String stock = args[4];        //num dept


            //Articulo
            String consultarticulo = "SELECT COUNT(*) FROM articulos WHERE COD = ?";
            PreparedStatement pstmtArticulo = conexion.prepareStatement(consultarticulo);
            pstmtArticulo.setInt(1, Integer.parseInt(cod));
            ResultSet resultadoArticulo = pstmtArticulo.executeQuery();
            resultadoArticulo.next();


            int countArticulo = resultadoArticulo.getInt(1);
            resultadoArticulo.close();
            pstmtArticulo.close();

            if (countArticulo > 0) {
                System.out.println("Error: El empleado con número " + cod + " ya existe.");
                conexion.close();
                return;
            }

            //precio
            if (Float.parseFloat(precio) <= 0) {
                System.out.println("Error: El precio debe ser mayor que 0.");
                conexion.close();
                return;
            }
            //stock
            if (Integer.parseInt(stock) <= 0) {
                System.out.println("Error: El stock debe ser mayor que 0.");
                conexion.close();
                return;
            }


            //fecha
            LocalDate fecha_cadu = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String fechaCadStr = fecha_cadu.format(formatter);


            String sql = String.format("INSERT INTO EMPLEADOS VALUES(%s, '%s', '%s', %s, '%s')",
                    cod, fecha_cadu, precio);

            System.out.println(sql);

            Statement sentencia = conexion.createStatement();
            int filas = sentencia.executeUpdate(sql);

            if (filas > 0) {
                System.out.println("Artículo insertado correctamente.");
            } else {
                System.out.println("Error al insertar el Articulo.");
            }
            sentencia.close();
            conexion.close();

        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el driver JDBC: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error de SQL: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Uno o más argumentos numéricos no son válidos.");
        }
    }
    }



