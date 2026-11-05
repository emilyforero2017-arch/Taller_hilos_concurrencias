package Reportequedebeesperar;

import java.util.List;

public class CalculadorSuma implements Runnable {
    private final List<Integer> numeros;
    private int resultado;

    public CalculadorSuma(List<Integer> numeros) {
        this.numeros = numeros;
    }

    @Override 
    public void run(){
        int suma = 0;
        for (int numero : numeros ){
            suma += numero;
        }
        resultado = suma;

    }
    public int getResultado() {
        return resultado;
    }

}
