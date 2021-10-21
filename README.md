### Snake Game
A console-based version of the beloved [game of Snake](https://www.google.com/search?q=play+snake) using OOP in Java.

## Play the game

The user can move the snake using the following commands:
    - `move [n]`. This moves the snake `n` squares, in the direction it is currently facing. `move` with no parameters moves the snake by `1` square.
    - `up | right | down | left` changes the snake orientation and moves the snake `1` square in the specified direction.
    - When the snake eats an apple, its tail grows by `1 square` and a new apple is added to the game area.
    
Game stops when snake eats itself or hits the wall. A GAME OVER! is printed on the command line.

# Settings

You can modify game settings in the `settings.properties` file. You can set the number of rows and columns on the board (`rows`,`columns`), the number of apples (`appleCount`) 
and the starting coordinates for the snake (`startRow`,`startColumn`).
If not set, the defaults are 6 for rows and columns, 3 for apple count and 4 for starting row and column.
