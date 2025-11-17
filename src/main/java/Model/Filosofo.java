package Model;

import java.util.concurrent.CountDownLatch;

public class Filosofo implements Runnable {
    private final String nombre;
    private final Palillo izquierdo;
    private final Palillo derecho;
    private final CountDownLatch latch;
    private final int ciclosComida;      // Número de veces que debe comer
    private int comidasRealizadas = 0; // Contador de comidas

    public Filosofo(String nombre, Palillo izquierdo, Palillo derecho, CountDownLatch latch, int ciclosComida) {
        this.nombre = nombre;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
        this.latch = latch;
        this.ciclosComida = ciclosComida; // Se recibe el número de ciclos
    }

    @Override
    public void run() {
        try {
            // El bucle se ejecuta hasta que haya comido el número de veces requerido
            while (comidasRealizadas < ciclosComida) {
                // Pensamiento del filósofo
                pensar();

                // Mensaje indicando que intenta comer
                System.out.println(nombre + " intenta comer (vez " + (comidasRealizadas + 1) + ")...");
                Thread.sleep(600); // Retraso para diferenciar los mensajes

                // Estrategia asimétrica para evitar deadlock
                if (nombre.hashCode() % 2 == 0) {
                    izquierdo.coger();
                    Thread.sleep(400);
                    derecho.coger();
                } else {
                    derecho.coger();
                    Thread.sleep(400);
                    izquierdo.coger();
                }

                // Comer
                comer();

                // Incrementa el contador de comidas
                comidasRealizadas++;
                System.out.println(nombre + " HA COMIDO " + comidasRealizadas + " VECES.");

                // Disminuye el latch en CADA comida
                latch.countDown();

                // Suelta los palillos
                izquierdo.soltar();
                derecho.soltar();

                // Mensaje de que dejó los palillos
                System.out.println(nombre + " deja los palillos.");
                Thread.sleep(600);
            }
            // Mensaje cuando un filósofo termina todos sus ciclos
            System.out.println(nombre + " ha terminado de comer sus " + ciclosComida + " veces.");

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void pensar() throws InterruptedException {
        System.out.println(nombre + " pensando...");
        Thread.sleep((long) (Math.random() * 1500) + 1000);
    }

    private void comer() throws InterruptedException {
        System.out.println(nombre + " comiendo...");
        Thread.sleep((long) (Math.random() * 1200) + 800);
    }
}