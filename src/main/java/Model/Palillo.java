package Model;

import java.util.concurrent.Semaphore;

public class Palillo {
    private final Semaphore semaforo;

    public Palillo() {
        // Semáforo con 1 permiso y fair=true para evitar starvation
        this.semaforo = new Semaphore(1, true);
    }

    public void coger() throws InterruptedException {
        semaforo.acquire();
    }

    public void soltar() {
        semaforo.release();
    }
}
