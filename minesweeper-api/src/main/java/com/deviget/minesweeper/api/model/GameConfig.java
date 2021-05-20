/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deviget.minesweeper.api.model;

import java.util.Arrays;
import java.util.Random;
import java.util.logging.Logger;

/**
 * The GameConfig Model class
 */
public class GameConfig {

    private static final Logger logger = Logger.getLogger("GameConfig");

    private int rows;
    private int columns;
    private int mines;
    private int[][] configArray;

    private static final int MINE_IDENTIFIER = -1;

    /**
     * Instantiates a new Game config.
     *
     * @param rows    the rows
     * @param columns the columns
     * @param mines   the mines
     */
    public GameConfig(int rows, int columns, int mines) {
        this.rows = rows;
        this.columns = columns;
        this.mines = mines;
    }

    /**
     * Gets rows.
     *
     * @return the rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * Sets rows.
     *
     * @param rows the rows
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * Gets columns.
     *
     * @return the columns
     */
    public int getColumns() {
        return columns;
    }

    /**
     * Sets columns.
     *
     * @param columns the columns
     */
    public void setColumns(int columns) {
        this.columns = columns;
    }

    /**
     * Gets mines.
     *
     * @return the mines
     */
    public int getMines() {
        return mines;
    }

    /**
     * Sets mines.
     *
     * @param mines the mines
     */
    public void setMines(int mines) {
        this.mines = mines;
    }

    /**
     * Get config array int [ ] [ ].
     *
     * @return the int [ ] [ ]
     */
    public int[][] getConfigArray() {
        return configArray;
    }

    /**
     * Configure game.
     */
    public void configureGame() {
        //start array of game with rows and cols
        configArray = new int[this.getRows()][this.getColumns()];
        //generate Random posittion of the mines
        configureMines(configArray, this.getMines());
        //calculate numbers
        calculateNumbers(configArray);
    }

    /**
     * Configure mines.
     *
     * @param startingArray the starting array
     * @param mines         the mines
     */
    public void configureMines(int[][] startingArray, int mines) {
        int counter = 0;
        Random random = new Random();
        while (counter < mines) {
            int x = random.nextInt(this.getColumns());
            int y = random.nextInt(this.getRows());
            startingArray[y][x] = MINE_IDENTIFIER;
            counter++;
        }
        printConfig(startingArray);
    }

    /**
     * Calculate numbers.
     *
     * @param startingArray the starting array
     */
    public void calculateNumbers(int[][] startingArray) {
        for (int r = 0; r < this.getRows(); r++) {
            for (int c = 0; c < this.getColumns(); c++) {
                if (startingArray[r][c] != MINE_IDENTIFIER) {
                    startingArray[r][c] = numAdjacentMines(c, r, startingArray);
                }
            }
        }
        printConfig(startingArray);
    }

    /**
     * Num adjacent mines.
     *
     * @param column        the column
     * @param row           the row
     * @param startingArray the starting array
     * @return the int
     */
    public int numAdjacentMines(int column, int row, int[][] startingArray) {
        int counter = 0;
        for (int r = row - 1; r <= row + 1; r++) {
            for (int c = column - 1; c <= column + 1; c++) {
                if ((c >= 0 && c < this.getColumns()) && (r >= 0 && r < this.getRows()) && startingArray[r][c] == -1) {
                    counter++;
                }
            }
        }
        return counter;
    }

    /**
     * Print config.
     *
     * @param startingArray the starting array
     */
    public void printConfig(int[][] startingArray) {
        logger.info("Config:" + Arrays.deepToString(configArray));
    }
}
