public class Pointer {
    private int rowIndex;
    private int colIndex;


    public void setPointer(int rowIndex, int colIndex){
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }

    public int getRowIndex(){
        return rowIndex;
    }

    public int getColIndex(){
        return  colIndex;
    }

// Move the pointer to next slot counter clockwise
    public void movePointer(int boardSize){
        // If on first row then move left
        if (rowIndex == 0) {
            colIndex--;
        }
        // If on second row then move right
        else if (rowIndex == 1) {
            colIndex++;
        }
        // If goes out of range the move the column back then move to next row
        if (colIndex < 0 || colIndex > (boardSize-1)) {
            if (rowIndex == 0) {
                rowIndex++;
                colIndex++;
            } else {
                rowIndex--;
                colIndex--;
            }
        }
    }
}
