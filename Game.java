import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Game represents a 2048 game that contains all the information of the game, including the Board,
 * Tiles, and score. Also, it offers several methods:
 *
 * setUp() - set up the game
 *
 * keyPressed(KeyEvent) - reacts when a key is pressed
 * keyTyped(KeyEvent) - reacts when a key is typed
 * keyReleased(KeyEvent) - reacts when a key is released
 *
 * paint(Graphics) - paint the game
 * drawTile(Graphics, Tile, int, int) - draw the Tile at given location
 *
 * main() - the main method
 *
 * @author Jiaying Zheng
 * @version Feb 7, 2023
 */
public class Game extends JPanel implements KeyListener {
  Board board = new Board();
  static Game game = new Game();
  static JFrame frame = new JFrame("2048");
  static final int margin = 25;



  /**
   * To set up the game.
   */
  public static void setUp() {
    frame.addKeyListener(game);
    frame.getContentPane().add(game);
    frame.setSize(400 + 2 * margin, 400 + 100 + 2 * margin);
    frame.setVisible(true);
    frame.setResizable(false);
  }



  /**
   * To listen for a key pressed.
   * - w or up-arrow means move up
   * - a or left-arrow means move left
   * - s or down-arrow means move down
   * - d or right-arrow means move right
   */
  @Override
  public void keyPressed(KeyEvent e) {
    // If w or up-arrow is pressed
    if (e.getKeyChar() == 'w' || e.getKeyCode() == KeyEvent.VK_UP) {
      board.up();
      board.spawn();
      frame.repaint();
    }
    // If a or left-arrow is pressed
    else if (e.getKeyChar() == 'a' || e.getKeyCode() == KeyEvent.VK_LEFT) {
      board.left();
      board.spawn();
      frame.repaint();
    }
    // If s or right-arrow is pressed
    else if (e.getKeyChar() == 's' || e.getKeyCode() == KeyEvent.VK_DOWN) {
      board.down();
      board.spawn();
      frame.repaint();
    }
    // If d or right-arrow is pressed
    else if (e.getKeyChar() == 'd' || e.getKeyCode() == KeyEvent.VK_RIGHT) {
      board.right();
      board.spawn();
      frame.repaint();
    }
    else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
      board = new Board();
      frame.repaint();
    }
  }

  /**
   * NOT USED
   * @param e the event to be processed
   */
  @Override
  public void keyTyped(KeyEvent e) {
  }

  /**
   * NOT USED
   * @param e the event to be processed
   */
  @Override
  public void keyReleased(KeyEvent e) {

  }



  /**
   * To paint the game.
   *
   * @param g  the <code>Graphics</code> context in which to paint
   */
  public void paint(Graphics g) {
    super.paint(g);

    Font font;
    Color titleColor = new Color(118,110,101);
    Color textColor = new Color(250,246,242);
    Color backgroundColor = new Color(188,172,159);

    // Draw the title 2048
    String gameTitle = "2048";
    font = new Font ("Arial",Font.BOLD, 75);
    g.setFont(font);
    g.setColor(titleColor);
    g.drawString(gameTitle, margin, margin + 50);

    // Draw the score and background
    String scoreTitle = "score:";
    String score = board.getScore() + "";
    font = new Font("Arial", Font.BOLD, 20);
    g.setFont(font);
    FontMetrics fm = g.getFontMetrics();

    g.setColor(backgroundColor);
    // Score is longer
    if(scoreTitle.length() < score.length()) {
      g.fillRoundRect((400 + margin) - 20 - fm.stringWidth(score), margin,
              20 + fm.stringWidth(score), 50, 10, 10);
      g.setColor(textColor);
      g.drawString(scoreTitle, (400 + margin) - 10 - fm.stringWidth(score) / 2 - fm.stringWidth(scoreTitle) / 2, margin + 12 - (fm.getHeight() / 2) + fm.getAscent());
      g.drawString(score, (400 + margin) - 10 - fm.stringWidth(score), margin + 50 - 8);
    }
    // Score title is longer
    else {
      g.fillRoundRect((400 + margin) - 20 - fm.stringWidth(scoreTitle), margin,
              20 + fm.stringWidth(scoreTitle), 50, 10, 10);
      g.setColor(textColor);
      g.drawString(scoreTitle, (400 + margin) - 10 - fm.stringWidth(scoreTitle), margin + 12 - (fm.getHeight() / 2) + fm.getAscent());
      g.drawString(score, (400 + margin) - 10 - fm.stringWidth(scoreTitle) / 2 - fm.stringWidth(score) / 2, margin + 50 - 8);
    }

    // Draw Board's background
    g.setColor(backgroundColor);
    g.fillRoundRect(margin, 100, 400, 400, 10, 10);

    // Draw Tiles
    for(int i = 0; i < board.getGrid(); i++) {
      for(int j = 0; j < board.getGrid(); j++) {
        drawTile(g, board.getTile(i, j), i, j);
      }
    }

    if(board.isGameOver()) {
      g.setColor(new Color(188,172,159, 127));
      g.fillRoundRect(margin, 100, 400, 400, 10, 10);

      String over = "Game Over!";
      font = new Font("Arial", Font.BOLD, 50);
      g.setFont(font);
      g.setColor(titleColor);
      fm = g.getFontMetrics();
      g.drawString(over, (400 + 2 * margin) / 2 - fm.stringWidth(over) / 2, 300 - fm.getHeight() / 2 + fm.getAscent());
    }
  }

  /**
   * To draw a Tile.
   * - find the proper Tile size based on the grid size with margin of 10 in bewteen
   * - find the proper x and y location
   * - draw the background
   * - draw the value
   * @param g is the Graphics
   * @param tile is the Tile to be drawn
   * @param i is the row number
   * @param j is the column number
   */
  public void drawTile(Graphics g, Tile tile, int i, int j) {
    int titleSize = (400 - (board.getGrid() + 1) * 10) / board.getGrid();
    int x = margin + 10 + (j * (titleSize + 10));
    int y = 110 + (i * (titleSize + 10));

    // Draw Tile's background
    g.setColor(tile.getBackgroundColor(board.getMultiplier()));
    g.fillRoundRect(x, y, titleSize, titleSize, 10, 10);
    // Draw value
    String value = tile.toString();
    Font font = new Font("Arial",Font.BOLD, 25);
    g.setFont(font);
    FontMetrics fm = g.getFontMetrics();
    g.setColor(tile.getTextColor(board.getMultiplier()));
    g.drawString(value,
            x + (titleSize / 2) - (fm.stringWidth(value) / 2),
            y + (titleSize / 2) - (fm.getHeight() / 2) + fm.getAscent());
  }


  public static void main(String[] args) {
    setUp();
  }
}