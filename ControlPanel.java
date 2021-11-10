//Julia Woeste
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ControlPanel extends JPanel {
	//instance variables
	private int count;
	private AvatarHolder holder;
	private JLabel score;
	private JButton easy, normal, hard, pause, quit;
	private DrawingPanel drawingpanel;
	private Timer timer;

	public ControlPanel(AvatarHolder holder, EnemyHolder enemyholder, Timer timer) {
		super(); //calls super class
		this.drawingpanel = drawingpanel;//associates drawingpanel 
		this.timer = timer;//associates timer
		this.holder = holder;//associates holder
		
		//creates a slider with the min at 2 and max at 10
		JSlider slider = new JSlider(2, 10);
		slider.setMajorTickSpacing(1);//makes the ticks
		slider.setPaintTicks(true);//paints the ticks and makes it true 

		//creates a label for the jlabel
		JLabel sliderLabel = new JLabel();
		//makes a change listener for the value of the slider
		slider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				sliderLabel.setText("Number of Enemies: " + slider.getValue() + ""); //sets the text of the label
				enemyholder.setHolder(slider.getValue()); //sets the holder of the enemy and gets the value of the slider for the number of enemies
			}

		});
		slider.setValue(6);
		enemyholder.setHolder(6);

		//creates a jpanel with grid layoout
		JPanel controlpanel = new JPanel(new GridLayout(4, 1));
		this.setLayout(new FlowLayout());
		
		//creates avatar buttons
		JButton a1 = new JButton("Avatar 1");
		JButton a2 = new JButton("Avatar 2");
		JButton a3 = new JButton("Avatar 3");

		//creates level buttons
		easy = new JButton("Easy");
		normal = new JButton("Normal");
		hard = new JButton("Hard");

		//creates pause and quit buttons 
		pause = new JButton("Start/Pause");
		quit = new JButton("Quit");

		//creates a button group for avatars
		ButtonGroup bg1 = new ButtonGroup();
		//adds the avatars to the buttons group 
		bg1.add(a1);
		bg1.add(a2);
		bg1.add(a3);

		//creates a button group for the levels
		ButtonGroup bg2 = new ButtonGroup();
		//adds levels  to the buttons group
		bg2.add(easy);
		bg2.add(normal);
		bg2.add(hard);

		//creates a button group for pause and quit
		ButtonGroup bg3 = new ButtonGroup();
		//adds the buttons to button groups
		bg3.add(pause);
		bg3.add(quit);

		//sets the size of the avatar
		a1.setSize(50, 50);
		a2.setSize(50, 50);
		a2.setSize(50, 50);

		//sets size of level buttons
		easy.setSize(50, 50);
		normal.setSize(50, 50);
		hard.setSize(50, 50);

		pause.setSize(50, 50);
		quit.setSize(50, 50);

		//creates a panel for avatar row
		JPanel avatarRow = new JPanel();
		avatarRow.add(a1);
		avatarRow.add(a2);
		avatarRow.add(a3);
		controlpanel.add(avatarRow);
		this.add(controlpanel);

		//creates a jpanel for the difficulty row
		JPanel difficultyRow = new JPanel();
		difficultyRow.add(easy);
		difficultyRow.add(normal);
		difficultyRow.add(hard);
		controlpanel.add(difficultyRow);
		this.add(controlpanel);

		//creates jpanel for buttons row
		JPanel buttons = new JPanel();
		buttons.add(pause);
		buttons.add(quit);
		controlpanel.add(buttons);
		this.add(controlpanel);

		//creates a jLabel for the score
		score = new JLabel("Score: " + count);

		//adds everything to the control panel
		controlpanel.add(score);
		this.add(controlpanel);
		controlpanel.add(slider);
		controlpanel.add(sliderLabel);
		score.setHorizontalAlignment(JLabel.CENTER);

		//action listeners for all the buttons 
		a1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				holder.setAvatar(1);
				// changes the avatar when buttons are pressed
			}

		});
		a2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				holder.setAvatar(2);

			}

		});

		a3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				holder.setAvatar(3);
				
			}

		});

		easy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				drawingpanel.enemySpeed(1);

			}

		});

		normal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				drawingpanel.enemySpeed(2);

			}

		});

		hard.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				drawingpanel.enemySpeed(2);

			}

		});

		pause.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// start and stop the timer
				if (timer.isRunning()) {
					timer.stop();
				} else {
					timer.start();
				}
			}

		});
		quit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				//exits the program when the button is clicked 
			}

		});

	}

	//updates the drawing panel
	public void updateDrawingPanel(DrawingPanel drawingPanel) {
		this.drawingpanel = drawingPanel;
	}
	//updates the score with the text
	public void updateScore() {
		count = count + 1;
		score.setText("Score: " + count);
	}

}
