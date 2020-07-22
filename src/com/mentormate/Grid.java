package com.mentormate;

import java.util.List;
/**
 * Implements the Grid interface with the appropriate methods.
 * Methods are used in the GridImpl class.
 */
public interface Grid {

    boolean isEligibleToChangeType(Cell currentCell);

    List<Integer> surroundingCells(long[][] grid, Cell cell);

    boolean isInBoundaries(long[][] grid, int row, int col);

    long countGenerations(Cell cell, int generationTurns);

    long [][] gridGeneration(long[][] matrix);
}
