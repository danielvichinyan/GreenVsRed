package com.mentormate;

import java.util.Arrays;
import java.util.Scanner;
/**
 * The RedGreen class combines the functionality of all the other classes.
 * It implements a "Green vs. Red" game which is played on a 2D grid.
 */
public class Main {

    public static void main(String[] args) {
        // take the user input
        Scanner scanner = new Scanner(System.in);

        int[] gridSize = Arrays.stream(scanner.nextLine().split(",\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        GridImpl grid = new GridImpl(gridSize[0], gridSize[1]);

        for (int i = 0; i < grid.getMatrix().length; i++) {
            // initial state (generation zero)
            long[] generationZero = Arrays.stream(scanner.nextLine().split(""))
                    .mapToLong(Long::parseLong)
                    .toArray();

            grid.getMatrix()[i] = generationZero;
        }

        String[] lastArguments = scanner.nextLine().split(",\\s+");

        int targetCellRow = Integer.parseInt(lastArguments[0]);
        int targetCellColumn = Integer.parseInt(lastArguments[1]);
        Cell targetCell = new Cell(grid.getMatrix(), targetCellRow, targetCellColumn);

        int generationTimes = Integer.parseInt(lastArguments[2]);
        long greenColorGenerationCount = grid.countGenerations(targetCell, generationTimes);

        System.out.printf("# expected result: %d", greenColorGenerationCount);
    }
}
