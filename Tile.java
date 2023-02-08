import java.awt.*;

/**
 * The Tile represents a Tile in a Board, and it stores the information of a Tile, including
 * if it is empty or not and its value. Additionally, it offers several methods:
 *
 * toString() - GETTER METHOD: the string representation of the Tile
 * isEmpty() - GETTER METHOD: if the Tile is empty or not
 * getValue() - GETTER METHOD: the value of the Tile
 * getBackgroundColor(int) - GETTER METHOD: the background color
 * getTextColor(int) - GETTER METHOD: the text color
 *
 * setValue(int) - set the value of the Tile
 *
 * @author Jiaying Zheng
 * @version Feb 7, 2023
 */
public class Tile {
  // value: the value of the tile
  // isEmpty: if the tile is empty or not
  // backgroundColor: the background color
  private int value;
  private boolean isEmpty;
  Color[] backgroundColor = {new Color(205,191,180),
          new Color(210, 225, 230),
          new Color(170, 200,220),
          new Color(120, 174, 211),
          new Color(100, 140, 190),
          new Color(79, 119, 170),
          new Color(65, 80, 145),
          new Color(47, 56, 123),
          new Color(29, 37, 112),
          new Color(12, 2, 62)};



  /**
   * Constructor for a default tile with:
   * value: 0
   * isEmpty: true
   */
  public Tile() {
    this.value = 0;
    this.isEmpty = true;
  }

  /**
   * Constructor for a tile with:
   * value: given value
   * isEmpty: true if value = 0 or the opposite
   *
   * @param value is the given value for the tile
   */
  public Tile(int value) {
    this.value = value;
    if(value != 0) {
      this.isEmpty = false;
    }
    else{
      this.isEmpty = true;
    }
  }



  /**
   * GETTER METHOD:
   * To get the string representation of the Tile.
   *
   * @return the string representation of the Tile
   */
  public String toString() {
    if(isEmpty) {
      return " ";
    }
    return this.value + "";
  }

  /**
   * GETTER METHOD:
   * Return the Tile's status of empty or not empty.
   *
   * @return if the Tile is empty
   */
  public boolean isEmpty() {
    return isEmpty;
  }

  /**
   * GETTER METHOD:
   * Return the value of the Tile
   *
   * @return the value of the tile
   */
  public int getValue() {
    return this.value;
  }

  /**
   * GETTER METHOD:
   * Return the background color
   * @param multiplier is the multiplier
   * @return the background color
   */
  public Color getBackgroundColor(int multiplier) {
    if(this.isEmpty) {
      return this.backgroundColor[0];
    }

    int index = (int) (Math.log(this.value) / Math.log(multiplier));
    if(index >= 9) {
      return backgroundColor[9];
    }
    return backgroundColor[index];
  }

  /**
   * GETTER METHOD:
   * Return the text color
   * @param multiplier is the multiplier
   * @return the text color
   */
  public Color getTextColor(int multiplier) {
    if(this.isEmpty) {
      return new Color(118,110,101);
    }
    int index = (int) (Math.log(this.value) / Math.log(multiplier));
    if(index <= 3) {
      return new Color(118,110,101);
    }
    return new Color(250,246,242);
  }



  /**
   * To set the value of a tile.
   * @param value is the value to be set on the tile
   */
  public void setValue(int value) {
    this.value = value;
    if(value == 0){
      this.isEmpty = true;
    }
    else {
      this.isEmpty = false;
    }
  }
}