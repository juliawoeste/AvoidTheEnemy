//Julia Woeste
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Avatar {
	private BufferedImage avatar1, avatar2, avatar3;
	private int width, height;
	private AvatarHolder holder;
	private int x, y;
	private int avatarselection; // 1 is vanilla, 2 is chocolate, 3 is strawberry

	public Avatar(AvatarHolder holder) {
		// reads the images and sets them to avatars
		try {
			avatar1 = ImageIO.read(new File("./images/Vanilla_Icecream.png"));
			avatar2 = ImageIO.read(new File("./images/ChocolateIcecream.png"));
			avatar3 = ImageIO.read(new File("./images/Strawberry_Icecream.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		// sets the width and height and y
		width = 100;
		height = 150;
		y = 400;
		// association for holder
		this.holder = holder;

	}

	public void paint(Graphics2D brush) {
		// paints the avatar. the holder class where 1 is the vanilla, 2 is the
		// chocolate, 3 strawberry
		if (holder.getAvatar() == 1) {
			brush.drawImage(avatar1, x, y, width, height, null);// draws image
		}
		if (holder.getAvatar() == 2) {
			brush.drawImage(avatar2, x, y, width, height, null);
		}
		if (holder.getAvatar() == 3) {
			brush.drawImage(avatar3, x, y, width, height, null);
		}
	}

	// gets the X location and returns x
	public int getXLocation() {
		return x;

	}

	// gets the y location and returns y
	public int getYLocation() {
		return y;
	}

	// sets the location, passes in integers, a and b
	// association x with and y with b
	public void setLocation(int a, int b) {
		this.x = a;
		this.y = b;

	}
	public int getHeight() {
		return height;
	}

	// gets the width of the enemy
	public int getWidth() {
		return width;
	}
}
