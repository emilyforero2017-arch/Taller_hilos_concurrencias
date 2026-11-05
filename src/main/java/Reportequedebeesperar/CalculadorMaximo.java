package Reportequedebeesperar;

import java.util.List;

public class CalculadorMaximo implements Runnable {

    private final List<Integer> numeros;
    private int resultado;

    public CalculadorMaximo(List<Integer> numeros) {
        this.numeros = numeros;
    }

    @Override
    public void run() {
        int maximo = numeros.get(0);

        for (int numero : numeros) {
            if (numero > maximo) {
                maximo = numero;
            }
        }

        resultado = maximo;
    }

    public int getResultado() {
        return resultado;
    }
}
