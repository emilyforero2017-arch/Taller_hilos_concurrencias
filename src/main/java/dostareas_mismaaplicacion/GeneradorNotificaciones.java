package dostareas_mismaaplicacion;

public class GeneradorNotificaciones implements Runnable {

    @Override 
    public void run (){
        for (int paso = 1; paso <5; paso++){
            System.out.println("Notificacion paso " + paso + "en el hilo " + Thread.currentThread().getName());
        }
    }

}
