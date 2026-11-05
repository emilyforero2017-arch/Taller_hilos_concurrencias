package EstadisticasporPeticiones;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        List<Integer> numeros = new ArrayList<>();

        for (int numero = 1; numero <= 10_000; numero++) {
            numeros.add(numero);
        }

        // Prueba principal con cuatro trabajadores.
        procesar(numeros, 4, 5_000, 25_005_000L);

        // La división entre tres trabajadores no es exacta.
        procesar(numeros, 3, 5_000, 25_005_000L);

        // Hay menos elementos que trabajadores.
        procesar(List.of(1, 2), 4, 1, 2L);
    }

    private static void procesar(
            List<Integer> numeros,
            int cantidadTrabajadores,
            int paresEsperados,
            long sumaEsperada
    ) throws InterruptedException {

        List<EstadisticasPares> tareas = new ArrayList<>();
        List<Thread> hilos = new ArrayList<>();

        // Sirve para comprobar que cada posición se asignó una sola vez.
        boolean[] posicionesAsignadas = new boolean[numeros.size()];
        int posicionesCubiertas = 0;

        int tamanioBase = numeros.size() / cantidadTrabajadores;
        int sobrantes = numeros.size() % cantidadTrabajadores;
        int inicio = 0;

        for (int i = 0; i < cantidadTrabajadores; i++) {
            int tamanioRango = tamanioBase;

            if (i < sobrantes) {
                tamanioRango++;
            }

            int fin = inicio + tamanioRango;

            // Comprobamos el rango antes de iniciar los hilos.
            for (int posicion = inicio; posicion < fin; posicion++) {
                if (posicionesAsignadas[posicion]) {
                    throw new IllegalStateException(
                        "La posición " + posicion + " se asignó dos veces"
                    );
                }

                posicionesAsignadas[posicion] = true;
                posicionesCubiertas++;
            }

            EstadisticasPares tarea =
                    new EstadisticasPares(numeros, inicio, fin);

            Thread hilo = new Thread(tarea, "trabajador-" + (i + 1));

            tareas.add(tarea);
            hilos.add(hilo);

            inicio = fin;
        }

        if (posicionesCubiertas != numeros.size()) {
            throw new IllegalStateException(
                "No se asignaron todos los elementos"
            );
        }

        // Iniciamos todas las tareas antes de esperar a cualquiera.
        for (Thread hilo : hilos) {
            hilo.start();
        }

        // Esperamos a que todas terminen.
        for (Thread hilo : hilos) {
            hilo.join();
        }

        // Cada tarea tiene resultados propios. Main los reúne al final.
        int cantidadTotalPares = 0;
        long sumaTotalPares = 0;

        for (EstadisticasPares tarea : tareas) {
            cantidadTotalPares += tarea.getCantidadPares();
            sumaTotalPares += tarea.getSumaPares();
        }

        // Comprobaciones automáticas de los resultados.
        if (cantidadTotalPares != paresEsperados) {
            throw new IllegalStateException(
                "Cantidad incorrecta de pares. Esperado: "
                + paresEsperados + ", obtenido: " + cantidadTotalPares
            );
        }

        if (sumaTotalPares != sumaEsperada) {
            throw new IllegalStateException(
                "Suma incorrecta. Esperada: "
                + sumaEsperada + ", obtenida: " + sumaTotalPares
            );
        }

        System.out.println("Trabajadores: " + cantidadTrabajadores);
        System.out.println("Elementos asignados: " + posicionesCubiertas);
        System.out.println("Cantidad de pares: " + cantidadTotalPares);
        System.out.println("Suma de pares: " + sumaTotalPares);
        System.out.println("Comprobaciones correctas");
        System.out.println();
    }
}