import java.util.*;
public class Game {
    Input input = new Input();
    Stack<Board> gameStack = new Stack<Board>();
    Stack<Integer> gameP1Score = new Stack<Integer>();
    Stack<Integer> gameP2Score = new Stack<Integer>();
    int playerCount;
    int rounds;
    boolean isUndo;

    public void run(){
        playerCount = choosePlayerCount();
        Player player1 = null;
        AI playerAI1 = null;
        Player player2 = null;
        AI playerAI2 = null;
        switch (playerCount){
            case 0:
                playerAI1 = new AI(1, "AI");
                playerAI2 = new AI(2, "AI");
                break;
            case 1:
                player1 = new Player(1, "Human");
                playerAI1 = new AI(2, "AI");
                break;
            case 2:
                player1 = new Player(1, "Human");
                player2 = new Player(2, "Human");
                break;
        }
        // Get board and seed size
        System.out.println("Board Size?");
        int boardSize = input.readInt();
        System.out.println("Seed size?");
        int seedSize = input.readInt();
        Board board = new Board(boardSize, seedSize);
        Pointer pointer = new Pointer();
        // Initialize necessarily variable 
        boolean gameStop = false;
        rounds = 1;
        // Start game
        while (!gameStop) {
            isUndo = false;
            move(board, player1, player2, playerAI1, playerAI2, playerCount, pointer);
            gameStop = board.checkEnd();
             if(!isUndo){
                 rounds++;
            }
        }
        gameEnd(board, player1, player2, playerAI1, playerAI2, playerCount);
    }

    private void play(Board board, Player player, Pointer pointer){
        String pick = chooseMove(board, player.getPlayerNum());
        //Print picked slot
        if (pick.equals("undo")){
            isUndo = true;
        }
        else {
            Integer pickInt = Integer.parseInt(pick);
            System.out.printf("You picked slot %s \n", (pickInt + 1));
            // Set pointer to where player pick, player can choose only their side so row is pre-set
            player.addScore(board.play(pointer, pickInt, player.getPlayerNum()));
        }
    }

    private void play(Board board, AI playerAI, Pointer pointer){
        int pick = playerAI.aiPlay(board.getBoard());
        System.out.printf("You picked slot %s \n", (pick + 1));
        playerAI.addScore(board.play(pointer, pick, playerAI.getPlayerNum()));
    }

    private int choosePlayerCount(){
        System.out.println("How many player?");
        int playerCount = -1;
        while (playerCount < 0 || playerCount > 3) {
            playerCount = input.readInt();
            if (playerCount < 0 || playerCount > 3){
                System.out.println("Between 0 ~ 2 only!");
            }
        }
        return  playerCount;
    }

    private void printBoard(Board board){
        System.out.println();
        System.out.println("_______________________________________________");
        System.out.println();
        for (int i = 0; i < 2; i++){
            for (int j = 0; j < board.getBoardSize(); j++){
                System.out.print("  |  ");
                System.out.print(board.readSeed(i,j));
            }
            System.out.println();
            System.out.println("_______________________________________________");
            System.out.println();
        }
    }

    private void printGame(Board board, Player player1, Player player2){
        printBoard(board);
        System.out.println("P1 Score: " + player1.getScore());
        System.out.println("P2 Score: " + player2.getScore());
    }

    private void printGame(Board board, Player player1, AI playerAI1){
        printBoard(board);
        System.out.println("P1 Score: " + player1.getScore());
        System.out.println("P2 Score: " + playerAI1.getScore());
    }

    private void printGame(Board board, AI playerAI1, AI playerAI2){
        printBoard(board);
        System.out.println("P1 Score: " + playerAI1.getScore());
        System.out.println("P2 Score: " + playerAI2.getScore());
    }

    private void gameEnd(Board board, Player player1, Player player2, AI playerAI1, AI playerAI2, int playerCount){
        printBoard(board);
        int winner = 0;
        System.out.println("Final Score:");
        switch (playerCount){
            case 0:
                winner = checkWinner(playerAI1, playerAI2);
                System.out.println("P1 Score: " + playerAI1.getScore());
                System.out.println("P2 Score: " + playerAI2.getScore());
                break;
            case 1:
                winner = checkWinner(player1, playerAI1);
                System.out.println("P1 Score: " + player1.getScore());
                System.out.println("P2 Score: " + playerAI1.getScore());
                break;
            case 2:
                winner = checkWinner(player1, player2);
                System.out.println("P1 Score: " + player1.getScore());
                System.out.println("P2 Score: " + player2.getScore());
                break;
        }
        System.out.printf("Winner: P%s\n", winner);
    }

    private int checkWinner(Player player1, Player player2){
        int p1Score = player1.getScore();
        int p2Score = player2.getScore();
        int winner = 0;
        if (p1Score > p2Score){
            winner = 1;
        }
        else if (p2Score > p1Score){
            winner = 2;
        }
        return winner;
    }

    private int checkWinner(Player player1, AI playerAI1){
        int p1Score = player1.getScore();
        int p2Score = playerAI1.getScore();
        int winner = 0;
        if (p1Score > p2Score){
            winner = 1;
        }
        else if (p2Score > p1Score){
            winner = 2;
        }
        return winner;
    }

    private int checkWinner(AI playerAI1, AI playerAI2){
        int p1Score = playerAI1.getScore();
        int p2Score = playerAI2.getScore();
        int winner = 0;
        if (p1Score > p2Score){
            winner = 1;
        }
        else if (p2Score > p1Score){
            winner = 2;
        }
        return winner;
    }

    private String chooseMove(Board board, int playerNum){
        boolean choice = false;
        String pick = null;
        while (!choice) {
            pick = input.readString();
            if (pick.equals("undo")){
                if (rounds == 1){
                    System.out.println("Cant undo in first round");
                }
                else {
                    break;
                }
            }
            else {
                Integer pickInt = Integer.parseInt(pick);
                pickInt--;
                if (pickInt > (board.getBoardSize() - 1) || pickInt < 0) {
                    System.out.println("Out of range");
                } else if (board.readSeed((playerNum - 1), pickInt) == 0) {
                    System.out.println("Can't choose empty slot!");
                } else {
                    pick = String.valueOf(pickInt);
                    break;
                }
            }
        }
        return pick;
    }

    private void move(Board board, Player player1, Player player2, AI playerAI1, AI playerAI2, int playerCount, Pointer pointer){
        System.out.printf("\nRound: " + rounds);
        switch (playerCount){
            case 0:
                printGame(board, playerAI1, playerAI2);
                if (rounds%2 == 1) {
                    System.out.printf("P1, from left which one to pick from? (1 - %s)\n", board.getBoardSize());
                    play(board, playerAI1, pointer);
                }
                else {
                    System.out.printf("P2, from left which one to pick from? (1 - %s)\n", board.getBoardSize());
                    play(board,playerAI2, pointer);
                }
                break;
            case 1:
                printGame(board, player1, playerAI1);
                if (rounds%2 == 1) {
                    gameStack.push(new Board(board.getBoard()));
                    gameP1Score.push(player1.getScore());
                    gameP2Score.push(playerAI1.getScore());
                    System.out.printf("P1, from left which one to pick from? (1 - %s)\n", board.getBoardSize());
                    play(board, player1, pointer);
                    if (isUndo){
                        gameStack.pop();
                        gameP1Score.pop();
                        gameP2Score.pop();
                        undoBoard(board);
                        undoScore(player1, playerAI1);
                        rounds = rounds - 2;
                    }
                }
                else {
                    System.out.printf("P2, from left which one to pick from? (1 - %s)\n", board.getBoardSize());
                    play(board,playerAI1, pointer);
                }
                break;
            case 2:
                gameStack.push(new Board(board.getBoard()));
                gameP1Score.push(player1.getScore());
                gameP2Score.push(player2.getScore());
                printGame(board, player1, player2);
                if (rounds%2 == 1) {
                    System.out.printf("P1, from left which one to pick from? (1 - %s)\n", board.getBoardSize());
                    play(board, player1, pointer);
                }
                else {
                    System.out.printf("P2, from left which one to pick from? (1 - %s)\n", board.getBoardSize());
                    play(board,player2, pointer);
                }
                if (isUndo){
                    gameStack.pop();
                    gameP1Score.pop();
                    gameP2Score.pop();
                    undoScore(player1, player2);
                    undoBoard(board);
                    rounds --;
                }
                break;
        }
    }

    private void undoBoard(Board board){
        Board a = gameStack.pop();
        board.setBoard(a.getBoard());
    }

    private void undoScore(Player player1, AI playerAI1){
        int s1 = gameP1Score.pop();
        int s2 = gameP2Score.pop();
        player1.setScore(s1);
        playerAI1.setScore(s2);

    }

    private void undoScore(Player player1, Player player2){
        int s1 = gameP1Score.pop();
        int s2 = gameP2Score.pop();
        player1.setScore(s1);
        player2.setScore(s2);

    }

}
