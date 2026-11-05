package EstadisticasporPeticiones;
import java.util.List;

public class EstadisticasPares implements Runnable {

    private final List<Integer> numeros;
    private final int inicio; // Se incluye
    private final int fin;    // No se incluye

    private int cantidadPares;
    private long sumaPares;

    public EstadisticasPares(List<Integer> numeros, int inicio, int fin) {
        this.numeros = numeros;
        this.inicio = inicio;
        this.fin = fin;
    }

    @Override
    public void run() {
        for (int i = inicio; i < fin; i++) {
            int numero = numeros.get(i);

            if (numero % 2 == 0) {
                cantidadPares++;
                sumaPares += numero;
            }
        }
    }

    public int getCantidadPares() {
        return cantidadPares;
    }

    public long getSumaPares() {
        return sumaPares;
    }
}

