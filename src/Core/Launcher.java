package Core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import FileManagement.Reading;

public class Launcher extends JFrame implements ActionListener {
	// Path to load game from, if chosen
	private String			filePath;
	public static Reading	readFile;

	// For the frame
	int						screenWidth		= 275;
	int						screenHeight	= 200;

	// Buttons
	private JButton			newGame, loadGame, exit;

	// Button details
	int						buttonWidth		= 150;
	int						buttonHeight	= 40;

	public Launcher() {
		// Set layout to null, so each button can be placed arbitrarily (chosen coords)
		getContentPane().setLayout(null);

		// Calling methods for buttons
		addButtons();

		// JFrame Stuff
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setSize(screenWidth, screenHeight);
		setTitle("Acreage Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
	}

	private void addButtons() {
		newGame = new JButton("Start New Game");
		loadGame = new JButton("Load Previous Game");
		exit = new JButton("Exit");

		// Setting Bounds and Placing Buttons
		newGame.setBounds((screenWidth - buttonWidth) / 2, 5, buttonWidth, buttonHeight);
		loadGame.setBounds((screenWidth - buttonWidth) / 2, 50, buttonWidth, buttonHeight);
		exit.setBounds((screenWidth - buttonWidth) / 2, 95, buttonWidth, buttonHeight);

		// Adding Buttons to Frame
		getContentPane().add(newGame);
		getContentPane().add(loadGame);
		getContentPane().add(exit);

		newGame.addActionListener(this);
		loadGame.addActionListener(this);
		exit.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == newGame) {
			Game g = new Game();
			g.level.generateLevel();
			g.start();
		} else if (e.getSource() == loadGame) {
			String home = System.getProperty("user.home");
			readFile = new Reading(home + "/Desktop/temp.txt");
			Game g = new Game();
			g.level.generateLevel(Launcher.readFile.fileTileID);
			g.start();
		} else if (e.getSource() == exit) {
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		// Set a UI look/feel to the JButton
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		new Launcher();
	}
}