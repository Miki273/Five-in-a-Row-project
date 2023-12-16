import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

@SuppressWarnings("serial")
public class MainGUI extends JFrame {

    private JPanel contentPane;
    private JLabel lblFive;
    private JButton btnStart;
    private JButton btnLoad;
    private JButton btnExit;

    public static void main(String[] args) {
    	MainGUI frame = new MainGUI();
        frame.show();
    }
    
    public MainGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 300, 400);

        // Use a JLabel as the content pane
        contentPane = new JPanel() {
            protected void paintComponent(java.awt.Graphics g) {
                super.paintComponent(g);
                Image image = new ImageIcon(getClass().getResource("/MainGUI.png")).getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        // Set layout to null for absolute positioning
        contentPane.setLayout(null);

        lblFive = new JLabel("Five in a Row");
        lblFive.setForeground(new Color(255, 255, 0));
        lblFive.setFont(new Font("Wawati SC", Font.BOLD, 25));
        lblFive.setBounds(78, 42, 179, 16);
        contentPane.add(lblFive);

        btnStart = new JButton("Start");
        btnStart.setBounds(96, 111, 117, 29);
        contentPane.add(btnStart);

        btnLoad = new JButton("Load");
        btnLoad.setBounds(96, 180, 117, 29);
        contentPane.add(btnLoad);

        btnExit = new JButton("Exit");
        btnExit.setBounds(96, 248, 117, 29);
        contentPane.add(btnExit);

        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	show_startGUI();
            }
        });
        
        btnLoad.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    		    load();
    	    }
        });
        
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    
    // show the startGUI
    private void show_startGUI() {
    	dispose();
        StartGUI frm = new StartGUI();
        frm.show();
    }
    
    // load the last saved board and show the boardGUI
    private void load() {
    	Stones stones = null;
	    boolean isBlack = true;
	    
	    try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("data.bin"))) {
	    	// read next color from the file
	    	isBlack = inputStream.readBoolean();
		    // read stones from the file
		    stones = (Stones)inputStream.readObject();
	    } catch (IOException | ClassNotFoundException | ClassCastException exc) {
	    	System.out.println(exc.getClass().getName());
	    }
  
	    if (stones != null) {
	    	dispose();
		    BoardGUI frm = new BoardGUI(stones, isBlack, "", "");
		    frm.show();
	    } else {
	    	JOptionPane.showMessageDialog(null, "Loading failed: no game progress records", "Alert", JOptionPane.ERROR_MESSAGE);
	    }
    }
}

