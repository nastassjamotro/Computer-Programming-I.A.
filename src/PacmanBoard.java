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
}
