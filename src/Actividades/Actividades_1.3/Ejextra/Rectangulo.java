package Ejextra;

import java.util.Scanner;

public class Rectangulo {
    public static void main(String[] args) {
        // Crear un objeto Scanner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);

        // Solicitar al usuario las dimensiones del rectángulo
        System.out.print("Introduce el alto del rectángulo: ");
        double alto = scanner.nextDouble();

        System.out.print("Introduce el ancho del rectángulo: ");
        double ancho = scanner.nextDouble();

        // Calcular el perímetro y el área
        double perimetro = 2 * (alto + ancho);
        double area = alto * ancho;

        // Determinar si el rectángulo está en posición horizontal o vertical
        String posicion = (ancho > alto) ? "horizontal" : (alto > ancho) ? "vertical" : "cuadrado";

        // Mostrar los resultados
        System.out.println("\nResultados:");
        System.out.println("Perímetro: " + perimetro);
        System.out.println("Área: " + area);
        System.out.println("Posición: " + posicion);

        // Cerrar el scanner
        scanner.close();
    }
}

