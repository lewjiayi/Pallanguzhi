# Pallanguzhi

## Data Structure and Algorithim Assignment

### March 2019

##### by

#### Lew Jiayi

#### Tan Chia Aun

#### The Ying

---

**Game Description**

<p style="text-align: justify">
Mancala is a common name for a family of 2-player board game played with small stones, beans or seeds. Pallanguzhi is such an ancient Tamil mancala game, it originates from Tamilnadu and later spread to Sri Lanka, Malaysia, and other countries. In Malaysia, it is a popular game called congkak. The game is played by two players with a board, constructed of various material, with a serious of holes arranged in rows usually two. Playing pieces are seeds, beans, stones, and marbles. The board configuration is wary among different games. The purpose of this assignment is to analyze the  ppropriate data structure, algorithm, and implement the game by choosing the programming language of your choice. Playing the game. This game has various ways in terms of holes and seeds. For this assignment, we consider a board with 2 rows of 7 holes. There are 14 holes in total. At the start of the game, every hole is filled with 4 seeds (2 x 7 x 4 = 56 seeds). The objective is to capture more seeds than the opponent. In other words, leave the opponent with no legal move or without seeds on the opponent side. In the beginning, the starting player (Player 1) lifts the seeds from any of the player holes and going counter-clockwise, distributes one seed in each hole. If the player reaches the end of the holes, player continues on the opposite side of the board. When the player drops his last seed, the player takes the seeds from the next hole and continues placing them in this way. If the last seed falls into a hole with an empty hole beyond (next to it), the seeds in the hole beyond the empty hole are captured by the player and put into his store and the player turn is over. The next player continues to play in the same way, taking seeds from any of player side holes and going around placing seeds in a counter-clockwise direction.
</p>

---

#### Game Play

1. Choose amount of player

> The game support up to two player. You can choose zero and watch two computer play with each other

2. Choose board size

> Input how long you want the board to be. Making it too short and you will miss the fun!

3. Choose seed size

> Choose the amount of seed you want to have in each hole of the board in the start the game

4. Play!

> Input the number of column of the hole you want to pick from at your turn

5. If you made a mistake....

> You can't undo anything you do in your life :weary:
> But you can undo in the game! :ok_woman:
> Simply type in ```undo``` in the input to undo to your previous move
