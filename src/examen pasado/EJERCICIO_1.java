import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class EJERCICIO_1 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        File fichero = new File("Fruteria_David Ricardo.dat");
        RandomAccessFile file = new RandomAccessFile(fichero, "rw");
        int opcion;
        do {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Insertar producto");
            System.out.println("2. modificar producto");
            System.out.println("3. Mostrar todos los productos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    insertarproducto(file);
                    break;
                case 2:
                    modificarProducto(file);
                    break;
                case 3:
                    mostrarTodosproductos(file);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);

        file.close();
        scanner.close();
    }
    private static void insertarproducto(RandomAccessFile file) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el codigo del nuevo producto: ");
        int id = scanner.nextInt();
        long posicion = (id - 1) * 86;

        if (posicion < file.length()) {
            System.out.println("El ID ya existe.");
            return;
        }

        System.out.print("Introduce el nombre del nuevo producto: ");
        String producto = scanner.next();
        System.out.print("Introduce el grupo del nuevo producto: ");
        String grupo = scanner.next();
        System.out.print("Introduce el precio del nuevo producto: ");
        double precio = scanner.nextDouble();
        System.out.print("Introduce el stock del nuevo producto");
        int stock = scanner.nextInt();
        int borradologico = 0;
        file.seek(posicion);
        file.writeInt(id);
        StringBuffer buffer = new StringBuffer(producto);
        buffer.setLength(17);
        file.writeChars(buffer.toString());
        StringBuffer buffer1 = new StringBuffer(grupo);
        buffer1.setLength(16);
        file.writeChars(buffer1.toString());
        file.writeDouble(precio);
        file.writeInt(stock);

        System.out.println("Producto insertado correctamente");
    }
    private static void modificarProducto(RandomAccessFile file) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Escriba el código del producto a MODIFICAR en el caso de no querer modificar introduzca 0: ");
        int id = scanner.nextInt();
        long posicion = (id - 1) * 86;

        if (posicion >= file.length()) {
            System.out.println("codigo: " + id + ", no existe producto");
            return;
        }
        int opcion1;
        System.out.print("Escriba el código del producto a MODIFICAR en el caso de no querer modificar introduzca 0: ");
        do {
            System.out.println("(21) Para modificar el NOMBRE del producto");
            System.out.println("(22) Para modificar el GRUPO del producto");
            System.out.println("(23) Para modificar el PRECIO");
            System.out.println("(24) Para modificar el STOCK");
            System.out.println("(0) Para salir");
            opcion1 = scanner.nextInt();
            switch (opcion1) {
                case 21:
                    System.out.print("Introduce el nuevo producto");
                    String producto = scanner.next();
                    file.seek(posicion + 4); // Posición después del Cod
                    file.writeBytes(producto);
                    break;
                case 22:
                    System.out.print("Introduce nuevo grupo");
                    String grupo = scanner.next();
                    file.seek(posicion + 38); // Posición después del producto
                    file.writeBytes(grupo);
                    break;
                case 23:
                    System.out.print("Introduce el nuevo precio: ");
                    double precio = scanner.nextDouble();
                    file.seek(posicion + 70); // Posición después del grupo
                    file.writeDouble(precio);
                    break;
                case 24:
                    System.out.print("Introduce el stock");
                    int stock = scanner.nextInt();
                    file.seek(posicion + 78); // Posición después del precio
                    file.writeInt(stock);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

            System.out.println("Producto modificado correctamente.");
        } while (opcion1 != 0);
    }
    private static void mostrarTodosproductos(RandomAccessFile file) throws IOException {
        long posicion = 0;
        file.seek(posicion);

        while (posicion < file.length()) {
            int id = file.readInt();
            char[] productoArray = new char[17];
            for (int i = 0; i < productoArray.length; i++) {
                productoArray[i] = file.readChar();
            }
            String producto = new String(productoArray).trim();
            char[] grupoArray = new char[16];
            for (int i = 0; i < grupoArray.length; i++) {
                grupoArray[i] = file.readChar();
            }
            String grupo = new String(grupoArray).trim();
            double precio = file.readDouble();
            int stock = file.readInt();

            if (id != 0) {
                System.out.println("COD: " + id + ", Producto: " + producto + ", Grupo: " + grupo + ", Precio: " + precio+", Stock:"+stock);
            }

            posicion += 86;
            file.seek(posicion);
        }
    }

}
