public class AI extends Player {
    public AI(int playerNum, String playerType){
        super(playerNum, playerType);
    }

    public int aiPlay(int[][] origboard){
        int move = -1;
        int score = -1;
        int [][] something = new int[2][origboard[0].length];
        Pointer aiPointer = new Pointer();
        for (int i = 0; i < origboard[0].length; i++) {
            for (int a = 0; a < 2; a++){
                for (int b = 0; b < origboard[0].length; b++){
                    something[a][b] = origboard[a][b];
                }
            }
            Board aiboard = new Board(something);
            aiPointer.setPointer((getPlayerNum()-1),i);
            int seedNum = aiboard.readSeed(aiPointer.getRowIndex(), aiPointer.getColIndex());
            if (seedNum != 0) {
                while (seedNum != 0) {
                    // Get seed number from the slot user pick then remove the seed in the slot
                    seedNum = aiboard.readSeed(aiPointer.getRowIndex(), aiPointer.getColIndex());
                    aiboard.removeAllSeed(aiPointer.getRowIndex(), aiPointer.getColIndex());
                    for (int j = 0; j < seedNum; j++) {
                        // Move pointer and place seed accordingly
                        aiPointer.movePointer(aiboard.getBoardSize());
                        aiboard.addSeed(aiPointer.getRowIndex(), aiPointer.getColIndex());
                    }
                    // Move to next slot and check the seed number
                    aiPointer.movePointer(aiboard.getBoardSize());
                    seedNum = aiboard.readSeed(aiPointer.getRowIndex(), aiPointer.getColIndex());
                }
                // Move to next slot and take the seed for score
                aiPointer.movePointer(aiboard.getBoardSize());
                // Choose best move
                if (aiboard.readSeed(aiPointer.getRowIndex(), aiPointer.getColIndex()) > score) {
                    score = aiboard.readSeed(aiPointer.getRowIndex(), aiPointer.getColIndex());
                    move = i;
                }
            }
            //Reset board
            aiboard = null;
        }
        return move;
    }
}
