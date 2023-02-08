/**
 * Board represents a 2048 game board that contains the Tiles and their information, and it
 * also provides different constructors that allows customization of the Board, including grid size
 * and multiplier. Additionally, it offers several methods:
 *
 * toString() - GETTER METHOD: the string representation of the Board
 * getGrid() - GETTER METHOD: the grid size
 * getTile(int, int) - GETTER METHOD: the Tile
 * getMultiplier() - GETTER METHOD: the multiplier
 * getScore() - GETTER METHOD: the current score
 * printBoard() - TESTING PURPOSES: print the Board in the console
 *
 * containEmptyTile() - return if the Board still has an empty Tile in it or not
 * isGameOver() - return if the game is over or not
 *
 * spawn() - spawn a Tile at a random location on the Board
 * compress(Tile[]) - compress a list of number and remove all the zeroes in it (ex. 2002 -> 2200)
 * merge(Tile[]) - merge the same number in a list of number (ex. 2200 -> 4000)
 *
 * left() - move Tiles to the left
 * right() - move Tiles to the right
 * up() - move Tiles up
 * down() - move Tiles down
 *
 * @author Jiaying Zheng
 * @version Feb 7, 2023
 */
public class Board {
  // grid: the grid size of the Board
  // multiplier: the root of the number
  // board: a 2D array of Tiles
  // score: the current score of the game
  private final int grid;
  private final int multiplier;
  private Tile[][] board;
  public int score = 0;



  /**
   * Constructor for a default game with:
   * grid: 4
   * multiplier: 2
   */
  public Board() {
    // To initialize the Board with 4x4 grid size
    this.board = new Tile[4][4];
    this.grid = 4;
    // To initialize the multiplier wiht 2
    this.multiplier = 2;

    // To loop through the rows of the Board
    for (int i = 0; i < 4; i++) {
      // To loop through the columns of the Board
      for (int j = 0; j < 4; j++) {
        // To add an empty Tile at the [i][j] location
        this.board[i][j] = new Tile();
      }
    }

    // To spawn two Tiles
    this.spawn();
    this.spawn();
  }

  /**
   * Constructor for a game with:
   * grid: 4
   * multiplier: input
   *
   * @param multiplier is the multiplier of the game
   */
  public Board(int multiplier) {
    // To initialize the Board with 4x4 grid size
    this.board = new Tile[4][4];
    this.grid = 4;
    // To initialize the multiplier with the given input
    this.multiplier = multiplier;

    // To loop through the rows of the Board
    for (int i = 0; i < 4; i++) {
      // To loop through the columns of the Board
      for (int j = 0; j < 4; j++) {
        // To add a empty Tile at the [i][j] location
        this.board[i][j] = new Tile();
      }
    }

    // To spawn two Tiles
    this.spawn();
    this.spawn();
  }

  /**
   * Constructor for a game with:
   * grid: input
   * multiplier: input
   *
   * @param multiplier is the multiplier of the game
   * @param grid is the grid of the game
   */
  public Board(int multiplier, int grid) {
    // To initialize the Board with the given input
    this.board = new Tile[grid][grid];
    this.grid = grid;
    // To initialize the multiplier with the given input
    this.multiplier = multiplier;

    // To loop through the rows of the Board
    for (int i = 0; i < this.grid; i++) {
      // To loop through the columns of the Board
      for (int j = 0; j < this.grid; j++) {
        // To add a empty Tile at the [i][j] location
        this.board[i][j] = new Tile();
      }
    }

    // To spawn two Tiles
    this.spawn();
    this.spawn();
  }

  /**
   * TESTING PURPOSES:
   * Constructor for a game with
   * grid: 4
   * multiplier: 2
   * Tile[][]: input
   */
  public Board(Tile[][] board) {
    // To initialize the Board with given Tiles
    this.board = board;
    this.grid = board.length;
    // To initialize the multiplier with 2
    this.multiplier = 2;
  }



  /**
   * GETTER METHOD:
   * To convert the board into a string with a new line for each row, and an empty Tile would be
   * represented as "_" while other Tiles are represented as their own value.
   *
   * @return the string representation of the Board
   */
  @Override
  public String toString() {
    // To create a temp variable to store the tile information
    String temp = "";

    // To loop through the rows of the Board
    for (int i = 0; i < this.grid; i++) {
      // To loop through the columns of the Board
      for (int j = 0; j < this.grid; j++) {
        // To add the string representation of the Tile to temp
        temp += this.board[i][j].toString();
      }
      // To determine if it is the last row and do not start a new line
      if (i == this.grid - 1) {
        // Break
        break;
      }
      // To start a new line for the next row
      temp += "\r\n";
    }

    // To return the string
    return temp;
  }

  /**
   * GETTER METHOD:
   * Return the grid size
   * @return the grid
   */
  public int getGrid() {
    // Return grid
    return this.grid;
  }

  /**
   * GETTER METHOD:
   * Return the Tile at given location
   *
   * @param x is the x-axis
   * @param y is the y-axis
   * @return the Tile at x y
   */
  public Tile getTile(int x, int y) {
    return this.board[x][y];
  }

  /**
   * GETTER METHOD:
   * Return the multiplier
   * @return the multiplier
   */
  public int getMultiplier() {
    return this.multiplier;
  }

  /**
   * GETTER METHOD:
   * Return the current game score.
   *
   * @return the current score of the game
   */
  public int getScore() {
    // To return the current score of the game
    return this.score;
  }

  /**
   * TESTING PURPOSES:
   * To print the board as a string in the console.
   */
  public void printBoard() {
    // To print the string of a row
    System.out.println(this);
    // To print the score
    System.out.println("Score: " + this.getScore());
  }



  /**
   * To check if game still contains an empty Tile.
   * - go through every Tiles on the Board to check
   *
   * @return if there is an empty Tile or not
   */
  public boolean containEmptyTile() {
    // To loop through the rows of the Board
    for(int i = 0; i < this.grid; i++) {
      // To loop through the columns of the Board
      for(int j = 0; j < this.grid; j++) {
        // To check if the Tile at [i][j] location is empty
        if(this.board[i][j].isEmpty()) {
          // Return true if the Tile is empty
          return true;
        }
      }
    }

    // Return false if there is no empty Tile
    return false;
  }

  /**
   * To check if game is over.
   * - check first if there is an empty Tile, which means game is not over
   * - check horizontally and vertically for duplicate number, since duplicate number means the
   * Tiles can still be moved, which means game is not over
   *
   * @return if the game is over or not
   */
  public boolean isGameOver() {
    // To check if there is an empty tile
    if (containEmptyTile()) {
      // Return false because there is still an empty Tile
      return false;
    }

    // To check horizontal for duplicate number
    for(int i = 0; i < this.grid; i++){
      for(int j = 0; j < this.grid - 1; j++){
        if(this.board[i][j].getValue() == this.board[i][j + 1].getValue()) {
          return false;
        }
      }
    }

    // To check vertical for duplicate number
    for(int i = 0; i < this.grid - 1; i++) {
      for(int j = 0; j < this.grid; j++) {
        if(this.board[i][j].getValue() == this.board[i + 1][j].getValue()) {
          return false;
        }
      }
    }

    // Return true if none duplicate number exist
    return true;
  }



  /**
   * To spawn a random tile on the board.
   * - check first if there is an empty Tile to spawn
   * - pick a random location and attempt to spawn a new Tile
   * - iterate until an empty Tile is found
   */
  public void spawn() {
    // To check if there is an empty tile to spawn
    if(this.containEmptyTile()) {
      // To loop until a tile is spawned
      while (true) {
        // To randomly pick a tile in the board
        int randomRow = (int) Math.floor(Math.random() * ((this.grid - 1) - 0 + 1) + 0);
        int randomCol = (int) Math.floor(Math.random() * ((this.grid - 1) - 0 + 1) + 0);
        // If the random tile is empty and can spawn a new number
        if (this.board[randomRow][randomCol].isEmpty()) {
          // To create the tile with a value of the multiplier
          this.board[randomRow][randomCol] = new Tile(this.multiplier);
          // To break the loop
          break;
        }
      }
    }
  }

  /**
   * To compress the number together with no zero in between.
   * - check if the number is 0
   * - remove the 0 and move the rest forward
   * - iterate until no 0 left
   * - add the 0 at the end to keep up with the length
   *
   * @param list is the list of number to be compressed
   * @return the compressed list
   */
  public Tile[] compress(Tile[] list) {
    // To create a list to store the compressed number
    Tile[] temp = new Tile[list.length];
    // To loop through the list and create tiles in it
    for(int i = 0; i < temp.length; i++) {
      // To create the tile
      temp[i] = new Tile();
    }

    // To create a counter for the index
    int count = 0;
    // To loop through the given list and look for numbers
    for(int i = 0; i < list.length; i++) {
      // If the number is not zero
      if(list[i].getValue() != 0) {
        // To copy the number to the compressed list
        temp[count].setValue(list[i].getValue());
        // To add one to the counter
        count++;
      }
    }

    // To loop through the rest of the compressed list
    for(int j = count; j < temp.length; j++) {
      // To create a zero in it
      temp[j].setValue(0);
    }

    // Return the list
    return temp;
  }

  /**
   * To merge the adjacent number together and multiplying them together.
   * - check if the adjacent Tile has the same value
   * - merge the number and update the score
   * - make the adjacent Tile empty
   * - iterate until the end of the list of number
   *
   * @param list is the list of number to be merged
   * @return the merged list
   */
  public Tile[] merge(Tile[] list) {
    // To loop through the list
    for(int i = 0; i < list.length - 1; i++) {
      // To check if the number after it is the same number
      if(list[i].getValue() == list[i + 1].getValue() && list[i].getValue() != 0) {
        // To multiply the number
        list[i].setValue(list[i].getValue() * this.multiplier);
        // To keep track of the score
        this.score += list[i].getValue();
        // To set the number to zero
        list[i + 1].setValue(0);
      }
    }
    // Return list
    return list;
  }



  /**
   * To move left.
   * - compress
   * - merge
   * - compress
   */
  public void left() {
    // To loop through every row
    for(int i = 0; i < this.grid; i++) {
      // To compress first
      this.board[i] = this.compress(this.board[i]);
      // To merge
      this.board[i] = this.merge(this.board[i]);
      // To compress again
      this.board[i] = this.compress(this.board[i]);
    }
  }

  /**
   * To move right.
   * - translate the rows first for compress and merge
   * - compress
   * - merge
   * - compress
   */
  public void right() {
    // To loop through the rows
    for(int i = 0; i < this.grid; i++) {
      // To create a list to store the reversed number
      Tile[] temp = new Tile[this.grid];
      // To loop through the columns
      for(int j = 0; j < this.grid; j++) {
        // To assign the value
        temp[j] = new Tile(this.board[i][this.grid - 1- j].getValue());
      }
      // To compress the list
      temp = this.compress(temp);
      // To merge the list
      temp = this.merge(temp);
      // To compress the list
      temp = this.compress(temp);
      // To loop through the columns again
      for(int k = 0; k < this.grid; k++) {
        // To reverse the numbers
        this.board[i][k].setValue(temp[this.grid - k - 1].getValue());
      }
    }
  }

  /**
   * To move up.
   * - translate the columns first for compress and merge
   * - compress
   * - merge
   * - compress
   */
  public void up() {
    // To loop through the columns
    for(int i = 0; i < this.grid; i++) {
      // To create a list to store the number
      Tile[] temp = new Tile[this.grid];
      // To loop through the rows
      for(int j = 0; j < this.grid; j++) {
        // To get the number
        temp[j] = new Tile(this.board[j][i].getValue());
      }
      // To compress the list
      temp = this.compress(temp);
      // To merge the list
      temp = this.merge(temp);
      // To compress the list
      temp = this.compress(temp);
      // To loop through the list
      for(int k = 0; k < this.grid; k++) {
        // To retrieve the numbers
        this.board[k][i].setValue(temp[k].getValue());
      }
    }
  }

  /**
   * To move down.
   * - translate the columns first for compress and merge
   * - compress
   * - merge
   * - compress
   */
  public void down() {
    // To loop through the columns
    for(int i = 0; i < this.grid; i++) {
      // To create a list to store the number
      Tile[] temp = new Tile[this.grid];
      // To loop through the rows
      for(int j = 0; j < this.grid; j++) {
        // To retrieve the number
        temp[j] = new Tile(this.board[this.grid - j - 1][i].getValue());
      }
      // To compress the list
      temp = this.compress(temp);
      // To merge the list
      temp = this.merge(temp);
      // To compress the list
      temp = this.compress(temp);
      // To loop through the rows
      for(int k = 0; k < this.grid; k++) {
        // To assign the value
        this.board[this.grid - k - 1][i].setValue(temp[k].getValue());
      }
    }
  }
}
