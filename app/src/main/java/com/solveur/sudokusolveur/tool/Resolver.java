package com.solveur.sudokusolveur.tool;


/**
 * Created by huber on 19/10/2017.
 *
 * Resolve a Sudoku grid. If the grid is incorrect, return it without change
 */

public class Resolver {

    private Resolver(){};

    private static int onlyPossibleValue(boolean[] tab){
        for(int i = 0;i<9;i++){
            if(tab[i]){
                return i+1;
            }
        }
        return 0;
    }
    private static int nbPossibleValue(boolean[] tab){
        int res=0;
        for(int i = 0;i<9;i++){
            if(tab[i]){
                res++;
            }
        }
        return res;
    }
    private static boolean[][][] checkPossibilities(int[][] grid,boolean[][][]possibleValues){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(grid[i][j]==0){
                    for(int k=0;k<9;k++){
                        if(grid[i][k]!=0){
                            possibleValues[i][j][grid[i][k] -1]=false;
                        }
                        if(grid[k][j]!=0){
                            possibleValues[i][j][grid[k][j]-1]=false;
                        }
                        System.err.println("***************"+(k%3 + (i/3)*3)+"**************"+ (k/3 + (j/3)*3 )+"**************");
                        if(grid[k%3 + (i/3)*3][k/3 + (j/3)*3 ]!=0){
                            possibleValues[i][j][grid[k%3 + (i/3)*3][k/3 + (j/3)*3 ] -1]=false;
                        }
                    }
                }
            }
        }
        return possibleValues;
    }
    public static int[][] ResolveSudoku(int[][] grid){

        boolean[][][] possibleValues = new boolean[9][9][9];

        int seted = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] != 0) {
                    seted++;
                    for (int k = 0; k < 9; k++) {
                        possibleValues[i][j][k] = false;
                    }
                }else{
                    for (int k = 0; k < 9; k++) {
                        possibleValues[i][j][k] = true;
                    }
                }
            }
        }
        checkPossibilities( grid,possibleValues);
        while(seted<81) {
            boolean updated = false;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if(nbPossibleValue(possibleValues[i][j])==1){
                        grid[i][j]=onlyPossibleValue(possibleValues[i][j]);
                        updated=true;
                        seted++;
                    }
                }
            }
            if(!updated){
                //TODO faire un paris
                System.err.println("***************************cas non pris en charge**********************************");
                break;
            }else{
                checkPossibilities( grid,possibleValues);
            }
        }
        return grid;
    }
}

