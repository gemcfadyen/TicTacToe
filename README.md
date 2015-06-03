# TicTacToe 

[![Build Status](https://travis-ci.org/gemcfadyen/TicTacToe.svg?branch=master)](https://travis-ci.org/gemcfadyen/TicTacToe)
[![Coverage Status](https://coveralls.io/repos/gemcfadyen/TicTacToe/badge.svg?branch=master)]  
(https://coveralls.io/r/gemcfadyen/TicTacToe?branch=master)

Tic Tac Toe is a game where players take turn in marking the cells in a 3 x 3 grid. The player who succeeds in placing three respective marks in a horizontal, vertical or diagonal row wins the game.

# The Brief
Create a game that allows a human to play Noughts &amp; Crosses (TicTacToe) against an unbeatable computer player.  The computer player must never lose and should win when possible.

# To Run
- clone the repository:
```
git clone git@github.com:gemcfadyen/TicTacToe.git
```
- Using the gradle wrapper invoke the program:
```
gradlew run
```
~~Alternatively you can invoke the main method of the Game class in your IDE.~~
- When prompted enter your choice as to whether you, as the human player would like to go first (H) or, whether you want the automated computer player to go first (A).
- The human player always has the symbol 'O'
- To take a move, when prompted, enter the number of the cell you wish you place your symbol. Cells are zero indexed based and will be displayed with each turn:
```
 | (0) | (1) | (2) |
 | (3) | (4) | (5) |
 | (6) | (7) | (8) |
```
- If you enter an invalid option you will be re-prompted.
- When a game is over, you have the option to play again (Y/N). 
- If you opt to play again, you will be re-prompted as to which player you want to open the game.
- Good Luck!
