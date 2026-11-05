package dostareas_mismaaplicacion;

public class GeneradorComprobantes implements Runnable {
    
    @Override 
    public void run (){
        for (int paso  = 1; paso <5; paso++){
            System.out.println("Comprobante paso " + paso + "en el hilo " + Thread.currentThread().getName());
        }
    }
}
