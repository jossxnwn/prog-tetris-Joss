# Proyecto Tetris

(DOCUMENTACIÓN EN DESARROLLO)

# 1. Introducción

El presente proyecto recoge los puntos esenciales para el desarrollo de una versión del clásico videojuego Tetris para PC, compatible con los sistemas operativos Windows, macOS y Linux. Este proyecto nace con una doble finalidad: por un lado, ofrecer al usuario final una experiencia de entretenimiento y, por otro, servir como ejercicio práctico de aprendizaje para el alumno, quien a lo largo del proceso adquirirá conocimientos y habilidades fundamentales en conceptos avanzados de Java mediante el desarrollo de un videojuego.

El producto está orientado principalmente a un público adulto de 30 años en adelante, tanto hombres como mujeres, familiarizados con este tipo de juegos.


# 2. Gestión del proyecto

## Objetivos
- Crear un videojuego Tetris sin errores.
- Adquirir los conocimientos de Java para desarrollar un juego de estas características.


## Alcance
- **Funcionalidades incluidas**:
  - Interfaz gráfica (una **única ventana** de juego).
  - Juego para 1 jugador.
  - Único **modo de juego**: clásico.
    - Sistema de niveles:
      - **Aumento de nivel** por alcanzar un valor determinado de puntos.
      - **Velocidad incremental**. El aumento de nivel implica aumento de la velocidad de caída de las piezas, aumentando la dificultad del juego.
    - El juego termina cuando una pieza no cabe en el tablero.
  - **Piezas**
    - Un total de 7 piezas.
    - Cada pieza tiene un color y forma diferente.
  - **Controles** por teclado de piezas
    - Rotación de pieza.
    - Desplazamiento de pieza horizontalmente (derecha-izquierda).
    - Aceleración vertical.
    - Salto vertical (caída de pieza, *hard drop*).
  - Panel de información
    - Mostrar información de controles al usuario.
    - Contador de líneas eliminadas/completadas.
    - Puntuación actual.
    - Nivel actual.
  - Sistema de **puntuación**:
    - Contabilizar líneas completadas (empieza en 0, valor entero).
    - Multiplicador de puntos por líneas eliminadas a la vez (1: 100 pts, 2: 300 pts, 3: 500 pts, 4: 800 pts).
    - Multiplicador por rachas consecutivas (2ª vez: x1.2, 3ª vez: x1.5, 4ª+ vez: $2^{x-3}$) 4ª vez y posteriores:
    - Multiplicador = 2^(x-3) donde x es el número de eliminaciones consecutivas.
    - La racha se reinicia cuando una pieza se coloca sin eliminar ninguna línea.
  - Sistema de **incremento de nivel**:
    - Empieza en el nivel 1.
    - Nivel 2 a los 500 puntos.
    - Fórmula general de progresión:.
    - | Nivel | Cálculo   | Valor |
    - | ----- | --------- | ----- |
    - | 1     | 500 × 1²  | 500   |
    - | 2     | 500 × 2²  | 2000  |
    - | 3     | 500 × 3²  | 4500  |
    - | 4     | 500 × 4²  | 8000  |
    - | 5     | 500 × 5²  | 12500 |
    - | 6     | 500 × 6²  | 18000 |
    - | 7     | 500 × 7²  | 24500 |
    - | 8     | 500 × 8²  | 32000 |
    - | 9     | 500 × 9²  | 40500 |
    - | 10    | 500 × 10² | 50000 |

  - **Tamaño tablero del juego**
    - Tamaño 24 altura, 10 ancho.
    - 4 Para poder aparecer las piezas

  - **Generacion de piezas**
    - Pocision de aparicion en el centro.
    - Siempre en posicion base .
    - Piezas lineales I, J, L aparecen planas(la J y la L tendran el extremo saliente mirando hacia arriba).
    - Piezas S, Z y T se mantendran de forma base igual a la imagen del Github.
    - Se usara ramdon para hacer la salida aleatoria.
    - Las piezas caen al estilo Soft drop: acelera la caída de la pieza mientras se mantiene la tecla pulsada.


- **Rotacion de piezas**
  - Las piezas se crearan dentro de un array 4x4 donde alineadas al centro derecha.
  - La rotacion sera del array completo en sentido horario.
  - Controles flechas laterales movimiento, flecha superior cambio de posicion de la ficha, inferior acelera, espacio caida instantanea haciendo Hard drop:
    La pieza cae instantáneamente hasta la posición más baja posible.

  (0, 0, 0, 0
  1, 1, 1, 1
  0, 0, 0, 0
  0, 0, 0, 0)

  (0, 0, 0, 0
  0, 1, 0, 0
  0, 1, 1, 1
  0, 0, 0, 0)

  (0, 0, 0, 0
  0, 1, 1, 0
  0, 1, 1, 0
  0, 0, 0, 0)


    (0, 0, 0, 0       (0, 0, 0, 0        (0, 0, 0, 0        (0, 1, 1, 0
    0, 0, 0, 1        0, 1, 0, 0         1, 1, 1, 0         0, 0, 1, 0
    0, 1, 1, 1        0, 1, 0, 0         1, 0, 0, 0         0, 0, 1, 0
    0, 0, 0, 0)       0, 1, 1, 0)        0, 0, 0, 0)        0, 0, 0, 0)


    (0, 0, 0, 0
    0, 0, 1, 0
    0, 1, 1, 1
    0, 0, 0, 0)


    (0, 0, 0, 0
    0, 1, 1, 0
    0, 0, 1, 1
    0, 0, 0, 0)


    (0, 0, 0, 0
    0, 0, 1, 1
    0, 1, 1, 0
    0, 0, 0, 0)

    (0, 0, 0, 1
    0, 0, 1, 0
    0, 1, 0, 0   //Secreto (0.001%)
    1, 0, 0, 0)

    (0, 0, 0, 0
    0, 0, 1, 0
    0, 0, 0, 0   //Secreto (0.001%)
    0, 0, 0, 0)


- **Funcionalidades no incluidas**:
  - Pausar juego.
  - Histórico de puntuaciones.
  - Multijugador.
  - Menú principal y ajustes de personalización.

- **Cierre de Alcance y Limitaciones**:
  - El juego se ejecutará en una ventana de dimensiones fijas para garantizar la estabilidad visual.
  - No se implementará almacenamiento en base de datos; la sesión es volátil.
  - Las piezas no pueden atravesar otras piezas ni salir del tablero.
  - Cuando una fila se llena completamente, se elimina.
  - Las filas superiores descienden una posición.


## Requisitos funcionales

- **RF1.** Representación visual y lógica de los 7 tipos de piezas (Tetriminos).
- **RF2.** Sistema de movimiento lateral y rotación de piezas detectando colisiones con el tablero.
- **RF3.** Implementación de caída acelerada y caída instantánea (*hard drop*).
- **RF4.** Algoritmo de detección, eliminación de líneas completas y desplazamiento de las superiores.
- **RF5.** Motor de puntuación que aplique bonos por líneas múltiples y rachas consecutivas.
- **RF6.** Control de niveles que incremente la velocidad de caída según la puntuación acumulada.
- **RF7.** Interfaz de usuario (HUD) para mostrar estadísticas de juego en tiempo real.


## Planificación temporal
El desarrollo se estima en **14 días de trabajo efectivo** distribuidos en **5 semanas**.

| Semana | Fase | Hitos / Actividades |
| :--- | :--- | :--- |
| **1** | **I: Diseño** | Configuración de la ventana principal y lógica de la matriz del tablero. |
| **2** | **II: Mecánicas** | Lógica de las 7 piezas, rotaciones y sistema de colisiones básico. |
| **3** | **III: Lógica** | Implementación de eliminación de líneas, sistema de puntos y rachas. |
| **4** | **IV: Progresión** | Programación de niveles, aumento de velocidad y diseño del HUD. |
| **5** | **V: Cierre** | Pruebas de errores, ajustes de dificultad y entrega de documentación. |


## Asignación de recursos
- **Humanos**: 1 desarrollador (alumno).
- **Software**: Java JDK, IDE (IntelliJ/Eclipse), Git.


# 3. Desarrollo del proyecto

## Diseño
(Mockups)

## Arquitectura
(Arquitectura de software)


# 4. Propuestas de mejora
- **Animaciones**: Explosión cuando se completa una fila.
- **Modos de juego extra**: Mapa abierto lateral, cronómetro o modo difícil.
- **Menús**: Inclusión de menú principal y ajustes de sistema.