# Proyecto Tetris

(DOCUMENTACIÓN EN DESARROLLO)

# 1. Introducción

El presente proyecto recoge los puntos esenciales para el desarrollo de una versión del clásico videojuego Tetris para PC, compatible con los sistemas operativos Windows, macOS y Linux. Este proyecto nace con una doble finalidad: por un lado, ofrecer al usuario final una experiencia de entretenimiento y, por otro, servir como ejercicio práctico de aprendizaje para el alumno, quien a lo largo del proceso adquirirá conocimientos y habilidades fundamentales en conceptos avanzados de Java mediante el desarrollo de un videojuego.

El producto está orientado principalmente a un público adulto de 30 años en adelante, tanto hombres como mujeres, familiarizados con este tipo de juegos.


# 2. Gestión del proyecto

## Objetivos
- crear un videojuego Tetris sin errores
- adquirir los conocimientos de Java para desarrollar un juego de estas características


## Alcance
- Funcionalidades **includas**:
  - Interfaz gráfica (una **única ventana** de juego).
  - Único **modo de juego**: clásico.
    - Sistema de niveles:
      - **Aumento de nivel** por alcanzar un valor determinado de puntos.
      - **Velocidad incemental**. El aumento de nivel implica aumento de la velocidad de caída de las piezas, aumentando la dificultad del juego.
    - El juego termina cuando una pieza no cabe en el tablero.
  - **Piezas**
    - Un total de 7 piezas
    - Cada pieza tiene un [color y forma diferente](./img/Piezas_Tetris_99.png)
  - **Controles** por teclado de piezas
    - Rotación de pieza
    - Desplazamiento de pieza horizontalmente (derecha-izquierda)
    - Aceleración vertical
    - Salto vertical (caída de pieza, *hard drop*)
  - Panel de información
    - Mostrar información de controles al usuario.
    - Contador de líneas eliminadas/completadas
    - Puntuación actual.
    - Nivel actual.
  - Sistema de **puntuación**:
    - Contabilizar líneas completadas
      - Empieza en 0.
      - La puntuación es valor entero.
      - Va incrementando por línea completada + bonificación (en caso de haberla).
    - Multiplicador de puntos en base a (rachas):
      - ¿Cuántas líneas elimino a la vez? Puntuación por líneas eliminadas a la vez (máximo de 4):
        - 1 línea: 10 puntos.
        - 2 líneas: 20 puntos.
        - 3 líneas: 50 puntos.
        - 4 líneas: 80 puntos.
      - ¿Cuántas veces seguidas elimino líneas?
        - 1ª vez: sin bonificación
        - 2ª vez: x1.2 sobre los puntos obtenidos por eliminar línea(s).
        - 3ª vez: x1.5 sobre los puntos obtenidos por eliminar línea(s).
        - 4ª vez y posteriores: 2^(x-3), siendo 'x' la vez seguida.
  - Sistema de **incremento de nivel**:
    - Empieza en el nivel 1.
    - Los niveles enteros.
    - El segundo nivel se alcanza al conseguir 500 puntos.
    - A partir del nivel 2, para incrementar de nivel, los puntos necesarios serán iguales al límite anterior multiplicado por 1.5. Ejemplo:
      - Nivel 1: hasta 499.
      - Nivel 2: de (499)+1 a ROUND(499 * 1.5).
      - Nivel 3: de (499 * 1.5)+1 a 499 * 1.5^2
      - Nivel 4: de (499 * 1.5^2)+1 a 499 * 1.5^3
      - Fórmula general: (499 * 1.5^x-2)+1 a 499 *1.5^x-1
- Funcionalidades **no incluidas**:
  - Pausar juego.
  - Histórico de puntuaciones.
  - Modos de juego:
    - Mapa abierto lateral: hai un hueco en los laterales izquierdo-derecho y, si se introduce una ficha por uno, sale por el opuesto otra ficha diferente.
    - Cronómetro: empieza un temporizador con cuenta regresiva. Solo aumenta si se obtienen bonificaciones.
    - Difícil: velocidad incrementada y con una fila roja en el medio que producirá una caída instanténea de la ficha.
  - Ninguna característica relacionada con multijugador.
  - Menú principal: logo, ajustes, jugar, nombre usuario, salir.
  - Ajustes: pantalla completa, modo oscuro, idioma.

## Requisitos funcionales

## Planificación temporal
14 días (5 semanas)

## Asignación de recursos





# 3. Desarrollo del proyecto
## Diseño
## Arquitectura


# 4. Propuestas de mejora
- Animaciones:
  - Explosión cuando se completa una fila.


### NOTAS
- cronómetro
  - bonus por racha
- ancho y alto tablero
- sistema de puntuación
- velocidad
- bonus
- números de piezas y formas
- mapas diferentes??
- gestión de jugadores
  - pedir nombre
  - guardar estadísticas y puntuación
  - pueden jugar jugadores diferentes?