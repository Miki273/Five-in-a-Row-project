import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class ResultGUI extends JFrame {

	private JPanel contentPane;
	private JLabel lblName;
	private JLabel lblCongrat;
	private JLabel lblResult;
	private JButton btnStart;
	private JButton btnBack;
	
	public ResultGUI() {}
	
	public ResultGUI(String playerName, BoardGUI boardGUI) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 400);
		contentPane = new JPanel() {
            protected void paintComponent(java.awt.Graphics g) {
                super.paintComponent(g);
                Image image = new ImageIcon(getClass().getResource("/ResultGUI.png")).getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnBack = new JButton("Back to Menu");
		btnBack.setBounds(72, 289, 147, 29);
		contentPane.add(btnBack);
		
		
		if (playerName == "tie") {
			lblName = new JLabel("Oops...");
			lblName.setFont(new Font("Wawati SC", Font.BOLD, 23));
			lblName.setBounds(72, 125, 156, 39);
			contentPane.add(lblName);
			
			lblCongrat = new JLabel("There is a tie!");
			lblCongrat.setFont(new Font("Wawati SC", Font.BOLD, 23));
			lblCongrat.setBounds(72, 175, 178, 51);
			contentPane.add(lblCongrat);
		}
		else {
			lblName = new JLabel(playerName + " wins!");
			lblName.setFont(new Font("Wawati SC", Font.BOLD, 23));
			lblName.setBounds(72, 125, 156, 39);
			contentPane.add(lblName);
			
			lblCongrat = new JLabel("Congratulations!");
			lblCongrat.setFont(new Font("Wawati SC", Font.BOLD, 23));
			lblCongrat.setBounds(72, 175, 178, 51);
			contentPane.add(lblCongrat);
		}
		
		btnStart = new JButton("Start New Game");
		btnStart.setBounds(72, 248, 147, 29);
		contentPane.add(btnStart);
		
		lblResult = new JLabel("Result");
		lblResult.setFont(new Font("Wawati SC", Font.BOLD, 23));
		lblResult.setBounds(115, 45, 156, 39);
		contentPane.add(lblResult);
		
		btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	boardGUI.boardGUIClosed();
            	dispose();
                StartGUI frm = new StartGUI();
                frm.show();
            }
        });
		
		btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	boardGUI.boardGUIClosed();
            	dispose();
            	MainGUI frm = new MainGUI();
            	frm.show();
            }
        });
	}
}
