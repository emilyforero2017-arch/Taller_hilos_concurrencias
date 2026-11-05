package dostareas_mismaaplicacion;

public class Main {
    public void main(String[]args ) throws InterruptedException {
        Thread hiloComprobantes = new Thread(new GeneradorComprobantes(), "hiloComprobantes");

        Thread hiloNotificaciones = new Thread (new GeneradorNotificaciones(), "hiloNotificaciones");

        hiloComprobantes.start();
        hiloNotificaciones.start();

        hiloComprobantes.join();
        hiloNotificaciones.join();

        System.out.println("Proceso finalizado");
    }

}
