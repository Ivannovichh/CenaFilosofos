# 🍽️ El Problema de la Cena de los Filósofos -- Java (Semaphore)

Proyecto realizado para el ejercicio del **Tema 7 de Programación
Multihilo**, donde se implementa en Java la clásica metáfora de
concurrencia **"La cena de los filósofos"**, utilizando **semáforos**
para sincronización y prevención de interbloqueo e inanición.

------------------------------------------------------------------------

## 🧠 1. Descripción General

### 🎯 1.1. Objetivos del ejercicio

-   Implementar una solución en **Java** al problema de la cena de los
    filósofos.\
-   Utilizar la clase **`Semaphore`** del paquete
    `java.util.concurrent`.\
-   Garantizar la **exclusión mutua** al usar los palillos.\
-   Evitar **interbloqueo (deadlock)** e **inanición (starvation)**.\
-   Mostrar mensajes del estado de cada filósofo en ejecución.

------------------------------------------------------------------------

## 🍜 1.2. Descripción del Problema

Cinco filósofos se sientan alrededor de una mesa circular. Cada uno
alterna entre:

-   🤔 **Pensar**\
-   😋 **Intentar comer**\
-   🍝 **Comer**

Cada filósofo tiene un palillo a su izquierda, pero necesita **dos
palillos** para comer.\
La solución debe permitir que todos coman sin bloquear el sistema ni
impedir que alguno coma indefinidamente.

------------------------------------------------------------------------

## 🧩 1.2.1. Requisitos Técnicos

✔️ Cada filósofo debe ser un **hilo independiente**\
✔️ Cada palillo debe ser representado con un **semáforo**\
✔️ Se debe prevenir **deadlock**\
✔️ Se debe evitar la **inanición**\
✔️ El programa debe imprimir estados claros en consola

------------------------------------------------------------------------

## 📦 Estructura del Proyecto

    src/
     └── java/
          ├── Model/
          │     ├── Filosofo.java   👨‍🏫 Lógica del hilo
          │     └── Palillo.java    🥢 Semáforo individual
          └── View/
                └── Mesa.java       🍽️ Inicio y configuración

------------------------------------------------------------------------

## 🛠️ 2. Diseño de la Solución

### 🧱 Componentes

-   **Filósofo** → Hilo (`Runnable`)\
-   **Palillo** → Recurso compartido (`Semaphore`)\
-   **Mesa** → Crea los palillos y los filósofos

### 🔐 Uso de Semáforos

Cada palillo se representa con un:

``` java
Semaphore palillo = new Semaphore(1);
```

Esto asegura que solo un filósofo pueda usarlo a la vez.

### 🔄 Estrategia anti--deadlock

Para evitar deadlock, los filósofos adquieren palillos en un orden
determinado.

------------------------------------------------------------------------

## 💻 3. Implementación

### 🔁 Método `run()` de `Filosofo`

Cada filósofo repite el ciclo:

1.  🧠 Pensar\
2.  🥢 Intentar adquirir los dos palillos\
3.  🍝 Comer\
4.  🔓 Liberar ambos palillos

### 🥢 Sincronización esencial

``` java
palilloIzquierdo.acquire();
palilloDerecho.acquire();
// comer
palilloIzquierdo.release();
palilloDerecho.release();
```

------------------------------------------------------------------------

## 🛡️ 4. Prevención de Interbloqueo e Inanición

### ✔️ Evitar interbloqueo

-   Adquisición de palillos en orden predecible\
-   No se permite la espera circular\
-   Control explícito de recursos

### ✔️ Evitar inanición

-   Todos los filósofos obtienen acceso a los palillos de manera justa\
-   Los semáforos otorgan turnos sin discriminación\
-   Se garantiza que todos comerán eventualmente

------------------------------------------------------------------------

## 📤 5. Resultados de la Ejecución

Ejemplo de salida esperada:

    Filósofo 1 está pensando...
    Filósofo 1 intenta comer...
    Filósofo 1 está comiendo...
    Filósofo 3 intenta comer...
    Filósofo 5 está pensando...
    Filósofo 2 está comiendo...

📸 Se pueden añadir capturas reales de tu ejecución.

------------------------------------------------------------------------

## 📚 6. Conclusiones

### 🎓 Lecciones aprendidas

-   Uso práctico de **hilos y semáforos**\
-   Control de acceso a recursos compartidos\
-   Prevención realista de problemas de concurrencia\
-   Comprensión de deadlocks y starvation\
-   Diseño de algoritmos concurrentes

### 🚀 Posibles mejoras

-   Interfaz gráfica completa\
-   Tiempos aleatorios más variados\
-   Añadir logs con colores o tablas\
-   Ampliar a más filósofos o variaciones

------------------------------------------------------------------------

## 👨‍💻 Autor

**Iván Sánchez Juárez**\
Proyecto educativo para el módulo de Programación Multihilo.

------------------------------------------------------------------------
