
//Julia Woeste
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class App extends JFrame {
	private AvatarHolder holder;
	private DrawingPanel drawingPanel;
	private Timer timer;
	private EnemyHolder eholder;

	public App() {
		super("AVOID THE ENEMIES");
		this.setLayout(new BorderLayout());

		// instances of the holders
		holder = new AvatarHolder();
		eholder = new EnemyHolder();

		timer = new Timer(5, null);

		// creating a control panel, drawing panel and adding them to the borderlayout
		ControlPanel controlpanel = new ControlPanel(holder, eholder, timer);
		drawingPanel = new DrawingPanel(1000, 900, holder, controlpanel, timer, eholder);
		controlpanel.updateDrawingPanel(drawingPanel);
		this.add(drawingPanel, BorderLayout.CENTER);
		this.add(controlpanel, BorderLayout.SOUTH);

		this.setSize(1000, 900);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setVisible(true);

		drawingPanel.requestFocus();
		drawingPanel.requestFocusInWindow();
		drawingPanel.setFocusable(true);
	}

	public static void main(String[] args) {
		new App();
	}
}

//how the game works: change the slider to choose how many enemies, choose an avatar, choose a diffuilty and press the start/pause buttons
//move the avatar with keys or with the mouse
// avoid the enemies, when an enemy hits the avatar you lose if you dodge all the enemies you win with your score shown at the bottom
// you can puse, start or quit at any time 