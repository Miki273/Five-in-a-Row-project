import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream; 

@SuppressWarnings("serial")
public class BoardGUI extends JFrame {
	private BoardPanel boardPanel;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenu mnMode;
	private JMenuItem mntmLight;
	private JMenuItem mntmDark;
	private JMenuItem mntmSave;
	private JMenuItem mntmMain;
	private Stones stones;
	private boolean isBlack = true;
	private int winNum = 0;
	private boolean enableClick = true;
	private int mode = 0;
	
	public BoardGUI() {}
	
    public BoardGUI(Stones s, boolean ib, String player1, String player2){
    	if (s == null) {
    		stones = new Stones();
    		stones.setName(player1, player2);
		} else {
			stones = new Stones(s);
		}
    	isBlack = ib;
    	
    	menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menu = new JMenu("Menu");
		menuBar.add(menu);
		
		mnMode = new JMenu("Change Mode");
		menu.add(mnMode);
		
		mntmLight = new JMenuItem("Light Mode");
		mnMode.add(mntmLight);
		mntmLight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mode = 0;
            	boardPanel.setBackground(Color.white);
            	boardPanel.setTurn(isBlack ? "BLACK to play" : "WHITE to play", mode);
            }
        });
		
		mntmDark = new JMenuItem("Dark Mode");
		mnMode.add(mntmDark);
		mntmDark.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mode = 1;
            	boardPanel.setBackground(Color.DARK_GRAY);
            	boardPanel.setTurn(isBlack ? "BLACK to play" : "WHITE to play", mode);
            }
        });
		
		mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	save(isBlack, stones);
            }
        });
		menu.add(mntmSave);

		mntmMain = new JMenuItem("Back to Main Menu");
		mntmMain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainGUI frm = new MainGUI();
                frm.show();
            }
        });
		menu.add(mntmMain);
    	
        setTitle("Five in a Row");
        setSize(600, 650);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        boardPanel = new BoardPanel(stones, isBlack);
        boardPanel.setBounds(0, 0, 600, 650);
        // we need to click to place stones
        boardPanel.addMouseListener(new MouseHandle());
        add(boardPanel);
    }
    
    private class MouseHandle extends MouseAdapter {
    	public void mousePressed(MouseEvent e) {
    		// check if click is allowed
    		if (!enableClick)
    			return;
    		
    		// get the x and y coordinates where the mouse is pressed
    		int x = e.getX();
            int y = e.getY();
            
            // check if the click is inside of the board
            if (x >= 50 && x <= 540 && y >= 50 && y <=540) {
            	// calculate the row and column the click is on
            	int row = Math.round((y - 50) / 35f);
            	int col = Math.round((x - 50) / 35f);
            	
            	// try to place a stone at where the mouse is clicked
            	if (stones.addStone(row, col, isBlack)) {
            		// refresh the screen
            		repaint();
            		// determine if any player wins
            		winNum = stones.isWin(row, col, isBlack);
            		
            		// alternate between black and white
                	isBlack = !isBlack;
                	boardPanel.setTurn(isBlack ? "BLACK to play" : "WHITE to play", mode);
                		
                	// check if it's a tie or if one player wins
                	if ((winNum == 0 && stones.isFull()) || winNum == 1 || winNum == 2) {
                		enableClick = false;
                		ResultGUI frm = new ResultGUI(winNum == 0? "tie": stones.getName(winNum), BoardGUI.this);
                		frm.show();
            		} 
            	}
            }
    	}
    }
    
    // close the board
    public void boardGUIClosed() {
    	dispose();
    }
    
    private void save(boolean isBlack, Stones stones){
		int response = JOptionPane.showConfirmDialog(null, "Saving current progress will overwrite the previous save.\nClick OK to continue", "Warning",
                JOptionPane.OK_CANCEL_OPTION);
    	if (response == JOptionPane.OK_OPTION) {
    		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("data.bin"))) {
        		// store next color in the file
        		outputStream.writeBoolean(isBlack);
   	   	     	// store stones in the file
   	   	     	outputStream.writeObject(stones);
   	   	    } catch (IOException exc) {
   	   	    	System.out.println(exc.getClass().getName());
   	   	    } 
    	}
	}
}
