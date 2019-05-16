import java.util.Scanner;
public class Game {

    Scanner input = new Scanner(System.in);


    public void run(){
        Player player2 = null;
        AI playerAI = null;
        System.out.println("How many player?");
        int playerCount = input.nextInt();
        Player player1 = new Player();
        if (playerCount == 2){
            player2 = new Player();
        }
        else{
            playerAI = new AI();
        }

        System.out.println(player1.getScore());
        System.out.println(playerAI.getScore());
        System.out.println("Board Size?");
        int boardSize = input.nextInt();
        System.out.println("Seed size?");
        int seedSize = input.nextInt();
        Board board = new Board(boardSize, seedSize);
    }

    public int checkWinner(Player player1, Player player2){
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


}
