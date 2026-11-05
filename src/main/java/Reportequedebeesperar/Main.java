package Reportequedebeesperar;

import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        List<Integer> numeros = List.of(12, 7, 25, 9, 17, 30);

        if (numeros.isEmpty()) {
            throw new IllegalArgumentException(
                "La lista no puede estar vacía para calcular el máximo"
            );
        }

        CalculadorSuma calculadorSuma = new CalculadorSuma(numeros);
        CalculadorMaximo calculadorMaximo = new CalculadorMaximo(numeros);

        Thread hiloSuma = new Thread(calculadorSuma, "hilo-suma");
        Thread hiloMaximo = new Thread(calculadorMaximo, "hilo-maximo");

        hiloSuma.start();
        hiloMaximo.start();

        hiloSuma.join();
        hiloMaximo.join();

        System.out.println("Suma: " + calculadorSuma.getResultado());
        System.out.println("Máximo: " + calculadorMaximo.getResultado());
    }
}

