import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;

@SuppressWarnings("serial")
public class StartGUI extends JFrame {

    private JPanel contentPane;
    private JTextField textPlayer2Name;
    private JTextField textPlayer1Name;
    private JLabel lblPlayer1Name;
    private JLabel lblPlayer2Name;
    private JLabel lblEnterName;
    private JButton btnConfirm;
    private JButton btnBack;

    public StartGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 300, 400);

        contentPane = new JPanel() {
            protected void paintComponent(java.awt.Graphics g) {
                super.paintComponent(g);
                Image image = new ImageIcon(getClass().getResource("/StartGUI.png")).getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        lblPlayer1Name = new JLabel("Player 1");
        lblPlayer1Name.setForeground(new Color(255, 255, 255));
        lblPlayer1Name.setBounds(64, 98, 49, 16);
        contentPane.add(lblPlayer1Name);

        textPlayer1Name = new JTextField();
        textPlayer1Name.setBounds(125, 93, 130, 26);
        contentPane.add(textPlayer1Name);
        textPlayer1Name.setColumns(10);

        lblPlayer2Name = new JLabel("Player 2");
        lblPlayer2Name.setForeground(new Color(255, 255, 255));
        lblPlayer2Name.setBounds(64, 154, 49, 16);
        contentPane.add(lblPlayer2Name);

        textPlayer2Name = new JTextField();
        textPlayer2Name.setBounds(125, 149, 130, 26);
        contentPane.add(textPlayer2Name);
        textPlayer2Name.setColumns(10);

        lblEnterName = new JLabel("Enter Your Names");
        lblEnterName.setFont(new Font("Wawati SC", Font.BOLD, 22));
        lblEnterName.setBounds(64, 36, 310, 16);
        contentPane.add(lblEnterName);

        btnConfirm = new JButton("Confirm");
        btnConfirm.setBounds(96, 227, 117, 29);
        contentPane.add(btnConfirm);

        btnBack = new JButton("Back");
        btnBack.setBounds(96, 283, 117, 29);
        contentPane.add(btnBack);
        
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				show_boardGUI(textPlayer1Name.getText(), textPlayer2Name.getText());
			}
		});
		
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainGUI frm = new MainGUI();
                frm.show();
            }
        });
    }
    
    // show the boardGUI
    private void show_boardGUI(String player1, String player2) {
    	dispose();
		BoardGUI frm = new BoardGUI(null, true, player1, player2);
        frm.show();
    }
}
