import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel {
	private JPanel graphicPanel;
	private JLabel lblTurn;
	private Stones stones;
	
	public BoardPanel(Stones s, boolean isBlack) {
		this.stones = s;
		graphicPanel = new GraphicPanel();
		lblTurn = new JLabel(isBlack ? "BLACK to play" : "WHITE to play");
		lblTurn.setFont(new Font("Serif", Font.PLAIN, 18));
		this.setLayout(new BorderLayout());
		this.add(graphicPanel, BorderLayout.CENTER);
        this.add(lblTurn, BorderLayout.SOUTH);
	}
	
	public void setTurn(String turn, int mode) {
		lblTurn.setText(turn);
		if (mode == 1)
			lblTurn.setForeground(new Color(255, 255, 255));
		else
			lblTurn.setForeground(new Color(0, 0, 0));
	}
	
	private class GraphicPanel extends JPanel {
		public void paint(Graphics g) {
			// set the color of the board
			g.setColor(new Color(168,132,98));
			g.fillRect(35,35,525,525);
			// draw the board
			g.setColor(Color.BLACK);
			for (int i = 0; i < 15; i++){
	            g.drawLine(50, 50 + i*35, 540, 50 + i*35);
	            g.drawLine(50 + i*35, 50, 50 + i*35, 540);
	        }
			// draw a point in the middle
			g.fillOval(290,290,10,10);
			// display the stones
			stones.display(g);
		}
	}
}
