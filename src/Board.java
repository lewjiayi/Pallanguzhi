import java.util.Arrays;
public class Board {
    private int[][] board;

    public Board(int boardSize, int seedSize){
        board = new int [2][boardSize];
        Arrays.fill(board, seedSize);
    }

    public void removeAllSeed(int row, int col){
        board[row][col] = 0;
    }

    public int readSeed(int row, int col){
        return board[row][col];
    }

    public void addSeed(int row, int col){
        board[row][col] ++;
    }
}
