
//Julia Woeste
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Enemy {
	// instance variables
	private BufferedImage enemy;
	private ArrayList<Image> enemyImage = new ArrayList<>();
	private int width, height;
	private int x, y, directionX = 1, directionY = 1;
	private double randomX;
	private DrawingPanel panel;
	private boolean isCounted;

	public Enemy(DrawingPanel panel) {
		this.panel = panel; // association

		// creates the enemy image
		try {

			enemy = ImageIO.read(new File("./images/Sun_Image.png"));
			enemyImage.add(enemy);

		} catch (IOException e) {
		}

		width = 50;
		height = 50;
		// randomizes the location of the enemies
		randomX = Math.random() * 3;
		int randomnumber = new Random().nextInt(2);
		if (randomnumber == 0) {
			randomX *= -1;
		}
	}

	// paints the enemy
	public void paint(Graphics2D brush) {
		brush.drawImage(enemy, x, y, width, height, null);
	}

	// gets the x location
	public int getXLocation() {
		return x;
	}

	// gets y location
	public int getYLocation() {
		return y;
	}

	// sets the location
	public void setLocation(int a, int b) {
		x = a;
		y = b;
	}

	// gets the height of the enemy
	public int getHeight() {
		return height;
	}

	// gets the width of the enemy
	public int getWidth() {
		return width;
	}

	// checks to see if the avatar and enemy collide
	public boolean collide(Avatar avatar1) {
		return new Rectangle(avatar1.getXLocation(), avatar1.getYLocation(), avatar1.getWidth(), avatar1.getHeight())
				.intersects(new Rectangle(x, y, width, height));
	}

	// move methods for the enemy so the bounce off the sides and fall through the
	// bottom
	public void move(int dx, int dy) {
		if (x < 0) {
			randomX *= -1;
			x = 0;

		} else if (x > panel.getWidth()) {
			randomX *= -1;
			x = panel.getWidth();
		}

		setLocation((int) ((randomX + x)), dy * directionY + y);

	}

	// gets the count of the enemies
	public boolean count() {
		if (!isCounted) {
			isCounted = true;
			return true;

		} else {
			return false;
		}
	}

}
