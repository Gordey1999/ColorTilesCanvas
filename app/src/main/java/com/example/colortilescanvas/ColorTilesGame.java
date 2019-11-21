package com.example.colortilescanvas;

public class ColorTilesGame {
    private int rows, cols;
    private boolean[][] field;

    public ColorTilesGame(int rows, int cols) {
        restart(rows, cols);
    }

    public void restart(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        field = new boolean[rows][cols];
        restart();
    }

    public void restart() {
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                field[i][j] = Math.random() > 0.5 ? true : false;
    }

    public void switchTile(int row, int col) {
        for (int i = 0; i < rows; i++) {
            field[i][col] = !field[i][col];
        }
        for (int i = 0; i < cols; i++) {
            field[row][i] = !field[row][i];
        }
        field[row][col] = !field[row][col];
    }

    public boolean hasWon() {
        int count = 0;

        for (boolean[] row : field)
            for (boolean tile : row)
                count += tile ? 1 : 0;

        int all = rows * cols;
        return count == all || count == 0 ? true : false;
    }

    public boolean[][] getField() {
        return field;
    }

    public boolean get(int row, int col) {
        return field[row][col];
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}
