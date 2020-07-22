package com.mentormate;

/**
 * The Cell class implements a cell which is a part of a grid.
 * Each cell can be either Green(1) or Red(0).
 */
public class Cell {

    private int col;
    private int row;

    private CellType cellType;

    /**
     * Default Constructor.
     */
    public Cell() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Constructor.
     *
     * @param grid
     * @param row
     * @param col
     */
    public Cell(long[][] grid, int row, int col) {
        setRow(row);
        setCol(col);
        setCellType(grid, row, col);
    }

    public int getRow() {
        return this.row;
    }

    public CellType getCellType() {
        return this.cellType;
    }

    /**
     * Sets the type of cell.
     * There are two cell types: Red and Greed
     *
     * @param grid
     * @param width
     * @param height
     */
    public void setCellType(long[][] grid, int width, int height) {
        if(grid[width][height] == 0) {
            this.cellType = CellType.RED;
        } else if (grid[width][height] == 1) {
            this.cellType = CellType.GREEN;
        }
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getCol() {
        return this.col;
    }
}
