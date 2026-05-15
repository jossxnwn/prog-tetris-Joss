package TetrisMain.model;

import java.awt.Color;

public abstract class PiezaBase implements PuntuacionPieza{
    protected int[][] shape;
    protected Color color;
    protected int x, y;

    /**
     * Metodo de ubicacion de aparicion de la pieza
     */
    public PiezaBase() throws MatrizInvalidaException{
        this.x = 3; // Aparece centrada en un tablero de 10 de ancho
        this.y = 0; // Aparece arriba
    }

    @Override
    public int getMultiplicadorPuntuacion (){
        return 1;
    }


    /*Getters y setters*/
    public int[][] getShape() { return shape; }
    public Color getColor() { return color; }
    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

    /**
     * Metodo para rotar la pieza cambiando la posicion del array
     */
    public void rotate() {
        int n = shape.length;
        int[][] rotated = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rotated[j][n - 1 - i] = shape[i][j];
            }
        }
        shape = rotated;
    }

    public void validarForma() throws MatrizInvalidaException{
        if (shape == null || shape.length !=4){
            throw new MatrizInvalidaException("Error: error en la dimension");
        }
        for (int [] fila : shape){
            if (fila.length != 4){
                throw new MatrizInvalidaException("Error: error en la dimension");
            }
            for (int valor : fila){
                if (valor != 0 && valor != 1){
                    throw new MatrizInvalidaException("Error: error de valor");
                }
            }
        }
    }
}