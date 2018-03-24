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
    // fun numbers tbd
  };
}
