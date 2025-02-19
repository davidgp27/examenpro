package FIcheros;
import java.util.Scanner;
import java.io.*;
public class menu {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        File fichero = new File("AleatorioEmple.dat");
        RandomAccessFile file = new RandomAccessFile(fichero, "rw");

        int opcion;
        do {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Consultar empleado");
            System.out.println("2. Insertar empleado");
            System.out.println("3. Modificar empleado");
            System.out.println("4. Borrar empleado");
            System.out.println("5. Mostrar todos los empleados");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    consultarEmpleado(file);
                    break;
                case 2:
                    insertarEmpleado(file);
                    break;
                case 3:
                    modificarEmpleado(file);
                    break;
                case 4:
                    borrarEmpleado(file);
                    break;
                case 5:
                    mostrarTodosEmpleados(file);
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

    private static void consultarEmpleado(RandomAccessFile file) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el ID del empleado a consultar: ");
        int id = scanner.nextInt();
        long posicion = (id - 1) * 36;

        if (posicion >= file.length()) {
            System.out.println("ID: " + id + ", NO EXISTE EMPLEADO...");
        } else {
            file.seek(posicion);
            int empleadoId = file.readInt();
            char[] apellidoArray = new char[10];
            for (int i = 0; i < apellidoArray.length; i++) {
                apellidoArray[i] = file.readChar();
            }
            String apellido = new String(apellidoArray).trim();
            int dep = file.readInt();
            double salario = file.readDouble();

            System.out.println("ID: " + empleadoId + ", Apellido: " + apellido + ", Departamento: " + dep + ", Salario: " + salario);
        }
    }

    private static void insertarEmpleado(RandomAccessFile file) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el ID del nuevo empleado: ");
        int id = scanner.nextInt();
        long posicion = (id - 1) * 36;

        if (posicion < file.length()) {
            System.out.println("El ID ya existe.");
            return;
        }

        System.out.print("Introduce el apellido del empleado: ");
        String apellido = scanner.next();
        System.out.print("Introduce el departamento del empleado: ");
        int dep = scanner.nextInt();
        System.out.print("Introduce el salario del empleado: ");
        double salario = scanner.nextDouble();

        file.seek(posicion);
        file.writeInt(id);
        StringBuffer buffer = new StringBuffer(apellido);
        buffer.setLength(10);
        file.writeChars(buffer.toString());
        file.writeInt(dep);
        file.writeDouble(salario);

        System.out.println("Empleado insertado correctamente.");
    }

    private static void modificarEmpleado(RandomAccessFile file) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el ID del empleado a modificar: ");
        int id = scanner.nextInt();
        long posicion = (id - 1) * 36;

        if (posicion >= file.length()) {
            System.out.println("ID: " + id + ", NO EXISTE EMPLEADO...");
            return;
        }

        file.seek(posicion);
        int empleadoId = file.readInt();
        if (empleadoId == 0) {
            System.out.println("El empleado con ID " + id + " está borrado.");
            return;
        }

        System.out.print("Introduce el nuevo departamento del empleado: ");
        int dep = scanner.nextInt();
        System.out.print("Introduce el nuevo salario del empleado: ");
        double salario = scanner.nextDouble();

        file.seek(posicion + 24); // Posición después del ID y apellido
        file.writeInt(dep);
        file.writeDouble(salario);

        System.out.println("Empleado modificado correctamente.");
    }

    private static void borrarEmpleado(RandomAccessFile file) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el ID del empleado a borrar: ");
        int id = scanner.nextInt();
        long posicion = (id - 1) * 36;

        if (posicion >= file.length()) {
            System.out.println("ID: " + id + ", NO EXISTE EMPLEADO...");
            return;
        }

        file.seek(posicion);
        file.writeInt(0); // Borrado lógico

        System.out.println("Empleado borrado correctamente.");
    }

    private static void mostrarTodosEmpleados(RandomAccessFile file) throws IOException {
        long posicion = 0;
        file.seek(posicion);

        while (posicion < file.length()) {
            int id = file.readInt();
            char[] apellidoArray = new char[10];
            for (int i = 0; i < apellidoArray.length; i++) {
                apellidoArray[i] = file.readChar();
            }
            String apellido = new String(apellidoArray).trim();
            int dep = file.readInt();
            double salario = file.readDouble();

            if (id != 0) {
                System.out.println("ID: " + id + ", Apellido: " + apellido + ", Departamento: " + dep + ", Salario: " + salario);
            }

            posicion += 36;
            file.seek(posicion);
        }
    }
}
