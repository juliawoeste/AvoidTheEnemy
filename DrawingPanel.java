
//Julia Woeste
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class DrawingPanel extends JPanel {
	// instance variables
	private Enemy enemy;
	private Avatar avatar1, avatar2;
	private Timer timer;
	private int xDirection, yDirection;
	private int dpWidth, dpHeight;
	private int width, height, x, y;
	private AvatarHolder holder;
	private ControlPanel controlpanel;
	private int speed;
	private BufferedImage background, lostimage, winimage;
	private ArrayList<Enemy> enemies = new ArrayList<>();
	private EnemyHolder eholder;
	private boolean lost, win;

	public DrawingPanel(int dpWidth, int dpHeight, AvatarHolder holder, ControlPanel controlpanel, Timer timer,
			EnemyHolder eholder) {
		super();// calls super class

		lost = false;
		win = false;
		this.holder = holder; // associations
		this.controlpanel = controlpanel; // associations
		this.eholder = eholder; // associations

		xDirection = 1;
		yDirection = 1;

		this.dpWidth = dpWidth;
		this.dpHeight = dpHeight;

		this.x = x;
		this.y = y;

		// sets size, perferred size, and background for the drawing panel
		this.setSize(1000, 900);
		this.setPreferredSize(new Dimension(1000, 900));
		this.setBackground(Color.white);

		// creates avatar and passes the holder
		avatar1 = new Avatar(holder);

		try {
			background = ImageIO.read(new File("./images/Background_Image.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// adds action listner for the timer
		
		timer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(win == false && lost == false) {
				// moves the enemy and passes the int (variable speed) to change the speed of
				// the suns to easy, normal, hard
				moveEnemy(speed, speed);

				if (enemies.size() < eholder.getHolder()) {
					createEnemy();

				}

				// checks in enemy hits avatar
				boolean isEmemyonscreen = false;
				for (Enemy enemy : enemies) {
					if (enemy.collide(avatar1)) {
					
						lost = true;
					
						// System.out.println("bye");
					}
					if(enemy.getYLocation() < getHeight()) {
						isEmemyonscreen = true;
					}
				}
				if(isEmemyonscreen == false) {
					win = true;
				}
				repaint();
			}
		}
		});

		// adds mouse motion listeners to move the avatar
		this.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				// gets the location so the avatar can only move left and right
				avatar1.setLocation(e.getX(), avatar1.getYLocation());

			}

			@Override
			public void mouseMoved(MouseEvent e) {
				avatar1.setLocation(e.getX(), avatar1.getYLocation());
			}

		});

		// adds key listener so the avatar can move using the left and right keys
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("hi");
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					avatar1.setLocation(avatar1.getXLocation() + 10, avatar1.getYLocation());
					repaint();
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					avatar1.setLocation(avatar1.getXLocation() - 10, avatar1.getYLocation());
					repaint();
				}
			}
		});

		// sets the focus for the keys
		this.setFocusable(true);
		requestFocusInWindow();

		// starts and stops the timer
		timer.start();
		timer.stop();

	}

	// paints the images
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D brush = (Graphics2D) g;
		// this is needed when using images
		brush.drawImage(background, 0, 0, getWidth(), getHeight(), null);

		// for every enemy it is painted
		for (Enemy enemy : enemies) {
			enemy.paint(brush);
		}
		avatar1.paint(brush);

		// if the user loses the then the you lost image pops up
		if (lost) {
			try {
				lostimage = ImageIO.read(new File("./images/You_Lose.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

			brush.drawImage(lostimage, 0, 0, getWidth(), getHeight(), null);
			
		}
		if (win) {
			try {
				winimage = ImageIO.read(new File("./images/You_Win.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			brush.drawImage(winimage, 0, 0, getWidth(), getHeight(), null);
		}
		
		requestFocus();

	}

	// move enemy method and updates the score
	public void moveEnemy(int dx, int dy) {
		// from the array list for enemies, for every enemy
		for (Enemy enemy : enemies) {
			enemy.move(dx, dy);
			if (enemy.getYLocation() > getHeight() && enemy.count()) {
				controlpanel.updateScore();
			}
		}
	}

	// the enemy speed it set to an int where 1 is easy, 2 is normal, 3 is hard
	public void enemySpeed(int x) {
		speed = x;
	}

	// creates multiple enemies
	public void createEnemy() {
		Enemy enemy = new Enemy(this);
		// sets the location of the enemy to be random, nbeed to type cast since enenmy
		// is not an int
		enemy.setLocation((int) (Math.random() * getWidth()), 10);
		enemies.add(enemy);
	}

}