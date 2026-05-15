# 🎮 Guía Técnica: Arquitectura y Métodos del Tetris MVC

Este documento proporciona una explicación detallada de cada clase y método del proyecto Tetris, estructurado bajo el patrón de diseño **Modelo-Vista-Controlador (MVC)**. Esta guía está diseñada para facilitar la comprensión profunda de la lógica, el flujo de datos y la renderización del juego.

---

## 🏗️ 1. Estructura General (MVC)

El proyecto se divide en tres capas principales para separar la lógica de los datos, la interfaz de usuario y la gestión de eventos:

1.  **Modelo (`TetrisMain.model`)**: Gestiona los datos, el estado del tablero y las reglas matemáticas (puntos, colisiones).
2.  **Vista (`TetrisMain.view`)**: Se encarga de la representación gráfica y los componentes de la interfaz (Swing).
3.  **Controlador (`TetrisMain.controller`)**: Actúa como puente, procesando la entrada del usuario y coordinando las actualizaciones entre el modelo y la vista.

---

## 📂 2. Capa de Modelo (Lógica y Datos)

### `TetrisModel.java`
Es el núcleo lógico del juego. No tiene conocimiento de la interfaz gráfica.
* `spawnPiece()`: Transfiere la pieza de "recámara" a la posición activa y genera una nueva pieza aleatoria.
* `canMove(int newX, int newY, int[][] shape)`: Comprueba si una pieza puede ocupar una posición específica sin colisionar con los bordes o bloques ya fijados.
* `lockPiece()`: "Ancla" los bloques de la pieza actual a la matriz permanente del tablero (`grid`).
* `checkLines()`: Escanea el tablero para encontrar filas llenas, las elimina y desplaza las superiores hacia abajo. Devuelve el número de líneas eliminadas.
* `updateScore(int lines)`: Calcula los puntos basados en las líneas eliminadas, aplicando multiplicadores por líneas múltiples y rachas (*streaks*).
* `addHardDropPoints(int rowsDropped)`: Suma puntos extra por la distancia recorrida en una caída instantánea.
* `getters (getGrid, getScore, etc.)`: Permiten que otras capas consulten el estado actual del juego.

### `Piece.java` (Clase Abstracta)
Define el comportamiento base de cualquier pieza.
* `rotate()`: Realiza una rotación matricial de 90° en sentido horario.
* `getters/setters`: Gestionan la posición (x, y), el color y la forma (matriz) de la pieza.

### `PieceFactory.java`
* `getRandomPiece()`: Implementa la probabilidad de aparición. Incluye la lógica para generar piezas clásicas y las "piezas secretas" con probabilidad de 0.001%.

### `Setting.java`
Contiene la configuración global y estática.
* `getters/setters`: Gestionan dimensiones del tablero, tamaño de celdas, nivel inicial y resolución de ventana.

### `DatabaseManager.java`
* `saveRecord(String user, int score)`: Persiste la puntuación en `records.txt`.
* `getRecords()`: Recupera la lista de puntuaciones desde el archivo.

---

## 🎮 3. Capa de Controlador (Gestión de Eventos)

### `TetrisController.java`
Gestiona el tiempo y la respuesta a las teclas.
* `initTimers()`: Arranca el `gameTimer` (gravedad) y el `inputTimer` (fluidez de movimiento).
* `processInput()`: Implementa el sistema **DAS/ARR** para que mover la pieza lateralmente al mantener pulsada una tecla sea rápido y fluido.
* `updateGame()`: Ejecuta el ciclo de caída por gravedad. Si hay colisión, llama a `handleCollision`.
* `handleCollision()`: Coordina el bloqueo de piezas, suma de puntos, subida de nivel y el spawn de la siguiente pieza. Detecta el *Game Over*.
* `handleKeyPress(int keyCode)`: Traduce las pulsaciones de teclado en acciones (rotar, mover, hard drop).
* `handleKeyRelease(int keyCode)`: Detiene los movimientos automáticos cuando el usuario suelta la tecla.
* `updateSpeed()`: Ajusta el retraso del temporizador según el nivel actual del modelo.

---

## 🖼️ 4. Capa de Vista (Interfaz Gráfica)

### `BoardPanel.java`
Representa el tablero de juego principal.
* `paintComponent(Graphics g)`: Calcula el tamaño de celda dinámico (responsivo) y dibuja la cuadrícula, las piezas fijas, la sombra (*Ghost Piece*) y la pieza activa.
* `triggerShake()` / `reduceShake()`: Gestionan el efecto visual de temblor de pantalla al realizar un *hard drop*.
* `drawBlock()` / `drawGhostBlock()`: Métodos auxiliares para dibujar celdas individuales con efectos de volumen o transparencia.

### `GameWindow.java`
La ventana principal que ensambla el juego.
* `updateStats(int s, int l, int n)`: Actualiza los textos de Puntuación, Nivel y Líneas en el HUD.
* `updateNextPiece(Piece p)`: Envía la información al panel de previsualización.
* `gameOver(int score)`: Muestra el mensaje de fin de juego y regresa al menú principal.
* `getBoardPanel()`: Permite al controlador acceder al panel para solicitar redibujados (`repaint`).

### `NextPiecePanel.java`
* `setNextPiece(Piece p)`: Recibe la pieza que vendrá después y solicita un redibujado.
* `paintComponent(Graphics g)`: Dibuja la pieza de forma centrada e independiente del tablero principal.

### `MainMenuWindow.java`, `RecordsWindow.java` y `SettingsWindow.java`
* Gestionan la navegación, la visualización del ranking ordenado y la modificación de parámetros en tiempo real antes de iniciar la partida.

### `RoundedPanel.java`
* Componente base estético que dibuja fondos con bordes redondeados y contornos suavizados (*Antialiasing*) para todos los menús y paneles del juego.

---

## 🚀 5. Flujo de Ejecución

1.  **Inicio**: `Main` abre `MainMenuWindow`.
2.  **Jugar**: `MainMenuWindow` crea una `GameWindow`.
3.  **Conexión**: `GameWindow` crea el `TetrisModel`, el `BoardPanel` y finalmente el `TetrisController`.
4.  **Loop**: El `TetrisController` actualiza el `TetrisModel` cada X milisegundos.
5.  **Renderizado**: Tras cada cambio, el controlador ordena a la vista (`repaint`) que lea los nuevos datos del modelo y los dibuje.
