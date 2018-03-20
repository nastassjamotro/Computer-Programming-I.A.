// Nastassja Motro

import java.awt.Colors;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
// do I care about fonts?
import java.awt.event.ActionEvent;
import java.awt.event.ActinListener;
import java.awt.event.KeyAdapater;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JFrame;
// do I want a timer?

public class PacmanBoard extends JPanel implements ActionListener {
	private Dimension dim;
	private Image image;
	private Color mcol = new Color(0,0,255); // color of maze
	private Color dotcol = new Color(255); // color of the little dots scattered around the maze
	private boolean inTheGame = false; // are you playing the game. false until game starts after introduction screen
	private boolean dying = false; // always false until you actually die

	// sizes of things TBD
	private final int blocks =  
	private final int numberOfBlocks =
	private final int screen = // blocks * numberOfBlocks to make sure it fits exactly right

	// amount of objects
	private final int ghosts = // how many ghosts there will be on screen

	// images
	private Image ghost;
	private Image pacmanUP, pacmanDOWN, pacmanRIGHT, pacmanLEFT; // different pacman positions
	private Image pacman2UP, pacman2DOWN, pacman2RIGHT, pacman2LEFT; 
	// another pacman for different lives?

	private final short levelData[] = {
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

	// speeds of pacman
	private int speedOptions[] {0, 1,3,5};
	private final int maxSpeed = ...;
	private int currentSpeed = ...;

	short[] screenData;

	public PacmanBaord() {
		GetImages(); // gets images for the different objects on the screen

		addKeyListener(new TAdapter());

		screenData = new short[numberOfBlocks * numberOfBlocks];
		setFocusable(true); // supposed the give the JPanel which extends the Component, the power to get focused
		setDoubleBuffered(true); // supposed to...

		Dimension d = new Dimension(600,600); // subject to change
		setBackgroundColor(Color.BLACK);
		// should i set the mColor here instead of earlier?

		/*

		setting number of objects of each of the ghosts and pacman and dots on the board here...

		*/
	}

	public void Play(Graphics2D g2D) {
		if (dying) {
			Dead(); // make class later that tells the program what to do when the player dies
		} else {
			DrawPacman(g2D);
			MovePacman();
			MoveGhosts(g2D);
		}
	}

	// introduction screen to the game
	public void IntroScreen(Graphics2D g2D) {
		g2D.setColor(new Color(/* pick a color*/));
		g2D.drawRect(/*figure out size later*/);
		g2D.setColor(new Color(/*pick a color*/));
		g2D.fillRect(/*figure out size later*/);

		/*
		this section is the initiation for the game by the player
		*/
	}

	// this is the score box for the game that will be seen while the game is going on
	public void ScoreSection(Graphics2D g2D) {
		/*
		will fill this out later
		*/
	}

	// the referennce to if pacman died in the game
	public void Dead() {
		/*
		need to decide first if i want multiple lives 
		*/
	}

	// drawing the ghosts
	public void DrawGhosts(Graphics2D g2D, int x, int y) {
		g2D.drawImage(ghost, x, y, this);
	}

	// movement of the ghost...shoot me
	public void MoveGhosts() {
		// see back of chem ia for ideas
	}

	// drawing pacman
	public void DrawPacman(Graphics2D g2D) {
		 /*
        need to do a thing for each of the different sides of pacman
        WAIT, DO I NEED TO DO A DIFFERENT CLASS FOR EACH VIEW OF PACMAN....NOOOOOOOOOOOOOOo
        */
	}

	// movement of pacman
	public void MovePacman() {

	}

	//initiating the game
	public void GameInit() {

	}

	// levels?

	// getting images
	public void getImages() {

	}

	public void paint(Garphics g) {
		super.paint(g);
		Graphics2D g2D = (Graphics g2D) g;
		g2D.setColor(Color.BLACK);
		g2D.fillRect(/*figure size out later*/);

		// finally drawing everything made earlier
		DrawMaze(g2D);
		DrawScore(g2D);
		if (ingame) {
			Play(g2D);
		} else {
			IntroScreen(g2D);
		}
		g.drawImage(/*need earlier stuff for this that I don't have*/);
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

	class TAdapter extends KeyAdapter {
		public void keyPressed(keyEvent e) {
			int key = e.getKeyCode();

			if(ingame) {
				if(key == key.Event)
			}
		}
	}
}
