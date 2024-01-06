package com.pacman;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import com.pacman.entities.Entity;
import com.pacman.entities.Ghost;
import com.pacman.entities.Pacman;
import javax.swing.*;


public class Main {
	// Window used by the entire game
	private static final JFrame frame = new JFrame();

	/**
	 Main entrypoint to correctly initialise the window and start a game
	*/
	public static void main(String[] args) throws InterruptedException {
		// Initialises the window
		initWindow();

		// Graphics update loop for the window
		Timer renderTime = new Timer(1, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.repaint();
			}
		});
		renderTime.start();

		// Initialises a new game
		Game.init();
	}

	/**
	 Sets up the window, and starts the KeyListener
	*/
	private static void initWindow() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				frame.setSize(414, 438);
				frame.setMinimumSize(new Dimension(414, 438));
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().setBackground(Color.BLACK);

				// Keyboard key listener
				frame.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent e) {

						Pacman pacman = Game.getPacman();
						if (pacman == null) return;

						// Pacman controls
						if (e.getKeyCode() == 38 || e.getKeyChar() == 'z') pacman.setDirection(Entity.Direction.UP);
						else if (e.getKeyCode() == 39 || e.getKeyChar() == 'd') pacman.setDirection(Entity.Direction.RIGHT);
						else if (e.getKeyCode() == 40 || e.getKeyChar() == 's') pacman.setDirection(Entity.Direction.DOWN);
						else if (e.getKeyCode() == 37 || e.getKeyChar() == 'q') pacman.setDirection(Entity.Direction.LEFT);

						// Reset the game by pressing ENTER.
						else if (e.getKeyChar() == '\n') Game.prepareToReset();
					}
				});

				frame.setVisible(true);
				frame.setFocusable(true);
				frame.requestFocus();
			}
		});
	}

	/**
	 Removes everything from the rendering list
	*/
	public static void resetRenderers() {
		Component[] components = frame.getContentPane().getComponents();

		for (Component component : components) frame.remove(component);

		frame.invalidate();
		frame.repaint();
	}

	/**
	 Appends a component to the window's rendering list

	 @param renderer The JComponent to add to that list
	*/
	public static void addRenderer(JComponent renderer) {
		frame.add(renderer);
	}

	/**
	 Removes a specific component from the window's rendering list

	 @param renderer The JComponent to remove
	*/
	public static void removeRenderer(JComponent renderer) {
		frame.remove(renderer);
	}
}
