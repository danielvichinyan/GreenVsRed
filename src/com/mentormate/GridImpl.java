package com.mentormate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Implementation of the methods used in the Grid interface.
 */
public class GridImpl implements Grid {

    // holds the green numbers to change
    private final List<Integer> greenNumbers = Arrays.asList(0, 1, 4, 5, 7, 8);
    // holds the red numbers to change
    private final List<Integer> redNumbers = Arrays.asList(0, 1, 2, 4, 5, 7, 8);

    private long[][] matrix;

    public long[][] getMatrix() {
        return matrix;
    }

    /**
     * Sets the matrix size.
     *
     * @param width
     * @param height
     */
    public void setMatrixSize(int width, int height) {
        gridLimits(width, height);

        this.matrix = new long[width][height];
    }

    /**
     * Displays the appropriate errors, checking the user input.
     * Assuming x <= y <= 1000.
     *
     * @param width
     * @param height
     */
    private void gridLimits(long width, long height) {

        if (width > height) {
            System.out.printf("%s%n", ErrorGrid.HEIGHT_LESS_THAN_WIDTH);

        } else if (width >= 1000) {
            System.out.printf("Width %s%n", ErrorGrid.WIDTH_GREATER_THAN_1000);

        } else if (height >= 1000) {
            System.out.printf("Height %s%n", ErrorGrid.WIDTH_GREATER_THAN_1000);

        } else if (width <= 0) {
            System.out.printf("Width %s%n", ErrorGrid.ZERO_NEGATIVE);

        } else if (height <= 0) {
            System.out.printf("Height %s%n", ErrorGrid.ZERO_NEGATIVE);
        }
    }

    /**
     * Constructor.
     * Sets the matrix size.
     *
     * @param rows
     * @param cols
     */
    public GridImpl(int rows, int cols) {
        setMatrixSize(rows, cols);
    }

    /**
     * Checks if a cell is eligible to change its type.
     * @param currentCell
     *
     * @return if the cell is eligible -> true, otherwise false
     */
    @Override
    public boolean isEligibleToChangeType(Cell currentCell) {
        List<Integer> surroundingCells = surroundingCells(this.matrix, currentCell);
        int greenSurroundingCellsCount = (int) surroundingCells.stream().filter(e -> e == 1).count();

        switch (currentCell.getCellType()) {
            case GREEN:
                return greenNumbers.contains(greenSurroundingCellsCount);
            case RED:
                return !redNumbers.contains(greenSurroundingCellsCount);
        }

        return false;
    }

    @Override
    public List<Integer> surroundingCells(long[][] grid, Cell cell) {
        List<Integer> cellsSurrounding = new ArrayList<>();

        for (int indexRow = -1; indexRow <= 1; indexRow++) {
            for (int indexCol = -1; indexCol <= 1; indexCol++) {
                int row = cell.getRow() + indexRow;
                int column = cell.getCol() + indexCol;

                if (isInBoundaries(grid, row, column)) {
                    if ((indexRow != 0) || (indexCol != 0)) {
                        cellsSurrounding.add((int) grid[row][column]);
                    }
                }
            }
        }

        return cellsSurrounding;
    }

    /**
     * Checks if the cell is in the boundaries.
     *
     * @param grid
     * @param col
     */
    @Override
    public boolean isInBoundaries(long[][] grid, int row, int col) {
        return row < grid.length && row >= 0 && col < grid[row].length && col >= 0;
    }

    @Override
    public long countGenerations(Cell cell, int generationTurns) {
        long numberOfGenerations = 0;

        if (isInBoundaries(matrix, cell.getRow(), cell.getCol())) {
            for (int i = 0; i < generationTurns; i++) {
                this.matrix = gridGeneration(matrix);

                if (matrix[cell.getRow()][cell.getCol()] == 1) {
                    numberOfGenerations += 1;
                }
            }

            return numberOfGenerations;
        }

        System.out.printf("%s%n", ErrorGrid.INVALID_CELL_INFORMATION);
        return numberOfGenerations;
    }

    /**
     * Returns the grid after a generation has been done.
     *
     * @param matrix
     */
    @Override
    public long[][] gridGeneration(long[][] matrix) {
        Cell currentCell;
        long[][] resultGrid = Arrays.stream(matrix).map(long[]::clone).toArray(long[][]::new);

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                currentCell = new Cell(matrix, row, col);
                switch (currentCell.getCellType()) {
                    case RED:
                        if (isEligibleToChangeType(currentCell)) {
                            resultGrid[row][col] = 1;
                        }
                        break;
                    case GREEN:
                        if (isEligibleToChangeType(currentCell)) {
                            resultGrid[row][col] = 0;
                        }
                        break;
                }
            }
        }
        return resultGrid;
    }
}