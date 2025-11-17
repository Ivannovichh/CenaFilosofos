package View;

import Model.Filosofo;
import Model.Palillo;

import java.util.concurrent.CountDownLatch;

public class Mesa {

    public static final int FILOSOFOS = 5;       // Número de filósofos y palillos
    public static final int CICLOS_MINIMOS = 2; // Número de veces que comerá cada uno

    public static void main(String[] args) {
        Palillo[] palillos = new Palillo[FILOSOFOS];
        Thread[] filosofos = new Thread[FILOSOFOS];

        String[] nombres = {"Sócrates", "Platón", "Aristóteles", "Descartes", "Kant"};

        // Latch para esperar a que todos coman el número de ciclos
        // Se inicializa con el NÚMERO TOTAL de comidas (5 filósofos * 2 ciclos = 10)
        CountDownLatch latch = new CountDownLatch(FILOSOFOS * CICLOS_MINIMOS);

        // Crear los palillos
        for (int i = 0; i < FILOSOFOS; i++) {
            palillos[i] = new Palillo();
        }

        // Crear los filósofos y asignarles palillos y ciclos
        for (int i = 0; i < FILOSOFOS; i++) {
            Palillo izquierdo = palillos[i];
            Palillo derecho = palillos[(i + 1) % FILOSOFOS];

            // Se pasa el número de ciclos al constructor
            Filosofo F = new Filosofo(nombres[i], izquierdo, derecho, latch, CICLOS_MINIMOS);

            filosofos[i] = new Thread(F, nombres[i]);
            filosofos[i].start();
        }

        try {
            // Espera hasta que el latch llegue a cero (10 comidas en total)
            latch.await();
            Thread.sleep(1000); // Pequeño retraso antes del mensaje final
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Mensaje final actualizado
        System.out.println("Todos los filósofos han comido " + CICLOS_MINIMOS + " veces.");
    }
}