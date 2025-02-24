package FichDirect;

import java.io.File;

public class Eliminar {
    public static void main(String[] args) {
        // Si se pasa un argumento, se usa ese nombre; de lo contrario, se usa el nombre por defecto.
        String nombreArchivo = (args.length > 0) ? args[0] : "mi_archivo.txt";
        File archivo = new File(nombreArchivo);

        if (!archivo.exists()) {
            System.out.println("El archivo \"" + archivo.getName() + "\" no existe.");
            return;
        }

        if (archivo.delete()) {
            System.out.println("Archivo \"" + archivo.getName() + "\" eliminado correctamente.");
        } else {
            System.out.println("No se pudo eliminar el archivo \"" + archivo.getName() + "\".");
        }
    }
}
