import java.util.Arrays;
public class Board {
    private int[][] board;
    private int boardSize;
    private int seedSize;

    public Board(int boardSize, int seedSize){
        this.boardSize = boardSize;
        this.seedSize = seedSize;
        this.board = new int [2][boardSize];
        for (int i = 0; i < 2; i++) {
            Arrays.fill(board[i], seedSize);
        }
    }

    public Board(int[][]board){
        this.board = new int[2][board[0].length];
        for (int a = 0; a < 2; a++){
            for (int b = 0; b < board[0].length; b++){
                this.board[a][b] = board[a][b];
            }
        }
        this.boardSize = board[0].length;
    }

    public void setBoard(int[][]board){
        for (int a = 0; a < 2; a++){
            for (int b = 0; b < board[0].length; b++){
                this.board[a][b] = board[a][b];
            }
        }
        this.boardSize = board[0].length;
    }

    public int play(Pointer pointer, int pick, int playerNum){
        pointer.setPointer((playerNum-1),pick);
        // Initialize a seedNum that is not zero to start loop
        int seedNum = 1;
        while (seedNum != 0) {
            // Get seed number from the slot user pick then remove the seed in the slot
            seedNum = readSeed(pointer.getRowIndex(), pointer.getColIndex());
            removeAllSeed(pointer.getRowIndex(),pointer.getColIndex());
            for (int i = 0; i < seedNum; i++) {
                // Move pointer and place seed accordingly
                pointer.movePointer(getBoardSize());
                addSeed(pointer.getRowIndex(), pointer.getColIndex());
            }
            // Move to next slot and check the seed number
            pointer.movePointer(getBoardSize());
            seedNum = readSeed(pointer.getRowIndex(), pointer.getColIndex());
        }
        // Move to next slot and take the seed for score
        pointer.movePointer(getBoardSize());
        int score = readSeed(pointer.getRowIndex(),pointer.getColIndex());
        removeAllSeed(pointer.getRowIndex(),pointer.getColIndex());
        return score;
    }

    public void removeAllSeed(int row, int col){
        this.board[row][col] = 0;
    }

    public int readSeed(int row, int col){
        return this.board[row][col];
    }

    public void addSeed(int row, int col){
        this.board[row][col] ++;
    }

    public boolean checkEnd(){
        boolean end = false;
        for (int a[] : this.board){
            // Set the boolean back to true in case the first row is set it to false 
            // Since only one row empty then game will end
            int rowSeedNum = 0;
            for (int b : a){
                rowSeedNum = rowSeedNum + b;
            }
            if (rowSeedNum <= 1){
                end = true;
                break;
            }
        }
        return end;
    }

    public int getBoardSize(){
        return this.boardSize;
    }

    public int[][] getBoard(){
        return board;
    }

    public int getSeedSize(){
        return seedSize;
    }
}
