// Nastassja Motro

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Image;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Event;
import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PacmanBoard extends JPanel implements ActionListener {
  private Dimension dim;
  
  private final Color dots = new Color(255, 234, 16); // color of the dots on the maze
  private Color mColor; // color of the maze
  private final Font font = new Font("Serif", Font.BOLD, 16); // font to be used for the score and introduction screen
  
  // all the images
  private Image image;
  private Image ghost;
  private Image pacman;
  private Image pacmanUp, pacmanDown, pacmanRight, pacmanLeft;
  private Image pacman2Up, pacman2Down, pacman2Right, pacman2Left;
  private Image pacman3Up, pacman3Down, pacman3Right, pacman3Left;
  
  private boolean dead = false; // always false until the player dies
  private boolean inGame = false; // always false until player initiates the game in the introduciton screen
  private int pacsLeft; // more than one life
  private int score;
  
  // setting position and movement for the ghosts and pacman
  private int[] ghostX, ghostY, ghostdX, ghostdY;
  private int pacmanX, pacmanY, pacmandX, pacmandY;
  private int[] dx, dy;
  private int recdX, recdY;
  private int viewdX, viewdY;
  
  private int GHOST_NUMBER = 6; // how many ghosts are there
  private int GHOST_MAX = 12; // how many ghosts can there be in total
  
  // the animation stuff is for pacman
  private final int ANIMATION_DELAY = 2;
  private final int ANIMATION_COUNT = 4;
  private int pacmanCount = ANIMATION_DELAY;
  private int pacmanPosition = 0;
  private int pacmanDirection = 1; //can only be facing or going one direction at a time
  
  // setting up speeds
  private final int PACMAN_SPEED = 6;
  private final int speeds[] = {1, 2, 3, 4, 6, 8};
  private final int maxSpeed = 6;
  private int currentSpeed = 3;
  
  private final int BLOCKS = 24;
  private final int NUMBER_OF_BLOCKS = 15;
  private final int SCREEN = BLOCKS * NUMBER_OF_BLOCKS;
  private final short level[] = {
    19, 26, 26, 26, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 22,
    21, 0, 0, 0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
    21, 0, 0, 0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
    21, 0, 0, 0, 17, 16, 16, 24, 16, 16, 16, 16, 16, 16, 20,
    17, 18, 18, 18, 16, 16, 20, 0, 17, 16, 16, 16, 16, 16, 20,
    17, 16, 16, 16, 16, 16, 20, 0, 17, 16, 16, 16, 16, 24, 20,
    25, 16, 16, 16, 24, 24, 28, 0, 25, 24, 24, 16, 20, 0, 21,
    1, 17, 16, 20, 0, 0, 0, 0, 0, 0, 0, 17, 20, 0, 21,
    1, 17, 16, 16, 18, 18, 22, 0, 19, 18, 18, 16, 20, 0, 21,
    1, 17, 16, 16, 16, 16, 20, 0, 17, 16, 16, 16, 20, 0, 21,
    1, 17, 16, 16, 16, 16, 20, 0, 17, 16, 16, 16, 20, 0, 21,
    1, 17, 16, 16, 16, 16, 16, 18, 16, 16, 16, 16, 20, 0, 21,
    1, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20, 0, 21,
    1, 25, 24, 24, 24, 24, 24, 24, 24, 24, 16, 16, 16, 18, 20,
    9, 8, 8, 8, 8, 8, 8, 8, 8, 8, 25, 24, 24, 24, 28
  };
  
  private short[] sData;
  private Timer timer;
  
  public PacmanBoard() {
    loadImages();
    initBoard();
    initVar();
  }
  
  // initializing the board setup
  public void initBoard() {
    addKeyListener(new TAdapter());
    setBackground(Color.BLACK);
    setFocusable(true); // lets component (jpanel) gain power of getting focused
    setDoubleBuffered(true); // double buffer creates an image off screen then displays it all at once
  }
  
  // initiates variables in the game
  public void initVar() {
    dim = new Dimension(400, 400);
    maze = new Color(13, 255, 71);
    ghostX = new int[GHOST_MAX];
    ghostY = new int[GHOST_MAX];
    ghostdX = new int[GHOST_MAX];
    ghostdY = new int[GHOST_MAX];
    dx = new int[4];
    dy = new int[4];
    sData = new short[NUMBER_OF_BLOCKS * NUMBER_OF_BLOCKS];
    timer = new Timer(40, this);
    timer.start();
  }
  
  @Override
  public void addNotification() {
    super.addNotification();
    initGame();
  }
  
  // initiating game as a whole with starting game variables

  public void initGame() {
    score = 0;
    currentSpeed = 3;
    GHOST_NUMBER = 6;
    pacsLeft = 3;
    initLevel();
  }
  
  // initializing each level
  public void initLevel() {
    for(int element : level) {
      for(int i = 0; i < NUMBER_OF_BLOCKS * NUMBER_OF_BLOCKS; i++) {
        sData[i] = element;
      }
    }
    contLevel();
  }
  
  // continuing the level after initializing
  public void contLevel() {
    int dx = 1;
    int random;
    for(int element : ghostSpeed) {
      for(short i = 0; i < GHOST_NUMBER; i++) {
        ghostX[i] = BLOCKS * 4;
        ghostY[i] = BLOCKS * 4;
        ghostdX[i] = dx;
        ghostdY[i] = 0;
        dx = -dx;
        random = (int) (Math.random() * (currentSpeed + 1));
        if(random > currentSpeed) {
          random = currentSpeed;
        }
        element = speeds[random];
      }
    }
    pacmanX = BLOCKS * 7;
    pacmanY = BLOCKS * 11;
    pacmandX = 0;
    pacmandY = 0;
    recdX = 0;
    recdY = 0;
    viewdX = -1;
    viewdY = 0;
    dead = false;
  }
  
  // loading all the images using ImageIcon
  private void loadImages() {
    ghost = new ImageIcon("images/ghost.png").getImage();
    pacman = new ImageIcon("images/ghost.png").getImage();
    pacmanUp = new ImageIcon("images/ghost.png").getImage();
    pacmanDown = new ImageIcon("images/ghost.png").getImage();
    pacmanRight = new ImageIcon("images/ghost.png").getImage();
    pacmanLeft = new ImageIcon("images/ghost.png").getImage();
    pacman2Up = new ImageIcon("images/ghost.png").getImage();
    pacman2Down = new ImageIcon("images/ghost.png").getImage();
    pacman2Right = new ImageIcon("images/ghost.png").getImage();
    pacman2Left = new ImageIcon("images/ghost.png").getImage();
    pacman3Up = new ImageIcon("images/ghost.png").getImage();
    pacman3Down = new ImageIcon("images/ghost.png").getImage();
    pacman3Right = new ImageIcon("images/ghost.png").getImage();
    pacman3Left = new ImageIcon("images/ghost.png").getImage();
  }
  
  private void play(Graphics2D g2D) {
    if (dead) {
      death();
    } else {
      check();
      ghostMovement();
      pacMovement();
      drawPac();
    }
  }
  
  /* there's an introduction screen that pops up before the player is able to play the game
     it prompts the user to press the 's' key in order to start the game in which it then proceeds to the actual game screen
  */
  private void introScreen(Graphics2D g2D) {
    g2D.setColor(new Color(23, 46, 255));
    g2D.fillRect(50, SCREEN / 2 - 20, SCREEN - 100, 50);
    g2D.setColor(Color.WHITE);
    g2D.drawRect(50, SCREEN / 2 - 20, SCREEN - 100, 50);
    String s = "Press s to start.";
    Font smallF = new Font("Serif", Font.BOLD, 16);
    FontMetrics met = this.getFontMetrics(smallF);
    g2D.setColor(Color.WHITE);
    g2D.setFont(smallF);
    g2D.drawString(s, (SCREEN - met.stringWidth(s)) / 2, SCREEN / 2);
  }
  
  // this draws up a game score at the bottom right hand corner of the screen
  private void gameScore(Graphics2D g) {
    String s;
    g.setFont(font);
    g.setColor(new Color(255, 25, 42));
    s = "Score: " + score;
    g.drawString(s, SCREEN / 2 + 96, SCREEN + 16);
    for (int i = 0; i  pacsLeft
  }
}
