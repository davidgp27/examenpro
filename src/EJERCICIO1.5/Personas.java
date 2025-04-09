import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Personas {
    public static void main(String[] args) {
        File fichero = new File("Personas1.dat");

        try (RandomAccessFile random = new RandomAccessFile(fichero, "rw")) {
            // Ejemplo de datos de personas
            String[][] personas = {
                    {"123456789A", "Carlos", "García Pérez", "30", "true", "600123456", "Calle Mayor 10, Madrid"},
                    {"987654321B", "Laura", "Martínez Gómez", "25", "false", "690987654", "Av. Libertad 5, Barcelona"},
                    {"567890123C", "Miguel", "Fernández López", "40", "true", "675456789", "Paseo del Prado 20, Valencia"}
            };

            for (String[] persona : personas) {
                escribirCadena(random, persona[0], 10); // DNI (10 chars)
                escribirCadena(random, persona[1], 15); // Nombre (15 chars)
                escribirCadena(random, persona[2], 30); // Apellido (30 chars)
                random.writeInt(Integer.parseInt(persona[3])); // Edad (int)
                random.writeBoolean(Boolean.parseBoolean(persona[4])); // Casado (boolean)
                escribirCadena(random, persona[5], 15); // Teléfono (15 chars)
                escribirCadena(random, persona[6], 60); // Dirección (60 chars)
            }

            System.out.println("✅ Archivo 'Personas.dat' creado correctamente.");

        } catch (IOException e) {
            System.err.println("❌ Error al escribir en el archivo: " + e.getMessage());
        }
    }

    // Método para escribir una cadena en un RandomAccessFile con un tamaño fijo
    private static void escribirCadena(RandomAccessFile file, String texto, int longitud) throws IOException {
        StringBuilder sb = new StringBuilder(texto);
        sb.setLength(longitud); // Asegurar que tenga longitud fija
        file.writeChars(sb.toString()); // Escribir caracteres fijos
    }
}

