package com.mentormate;

/**
 * Implements the type of errors.
 */
public interface ErrorGrid {
    // Displayed when the user enters invalid cell parameters.
    String INVALID_CELL_INFORMATION = "Sorry! Your cell coordinates are wrong!";

    // Displayed when the user enters width which is more than 1000.
    String WIDTH_GREATER_THAN_1000 = "Width must be less than 1000!";

    // Displayed when the user enters a negative number or 0.
    String ZERO_NEGATIVE = "Please type a positive number!";

    // Displayed when the height is less than the width.
    String HEIGHT_LESS_THAN_WIDTH = "Height must be greater than width!";
}
