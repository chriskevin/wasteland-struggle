package game;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HUD extends JPanel {
    public JLabel player1Score;
    public JLabel player2Score;
    public JLabel player3Score;
    public JLabel player4Score;
    
    public HUD() {
        initComponents();
        
        setOpaque(false);
        setBounds(0, 0, 800, 100);
    }

    private void initComponents() {
        player1Score = new JLabel();
        player2Score = new JLabel();
        player3Score = new JLabel();
        player4Score = new JLabel();

        player1Score.setForeground(Color.RED);
        player2Score.setForeground(Color.GREEN);
        player3Score.setForeground(Color.BLUE);
        player4Score.setForeground(Color.YELLOW);

        Font scoreFont = new Font("Arial", Font.BOLD, 16);
        player1Score.setFont(scoreFont);
        player2Score.setFont(scoreFont);
        player3Score.setFont(scoreFont);
        player4Score.setFont(scoreFont);

        player1Score.setText("Player1 ");
        player2Score.setText("Player2 ");
        player3Score.setText("Player3 ");
        player4Score.setText("Player4 ");

        add(player1Score);
        add(player2Score);
        add(player3Score);
        add(player4Score);
    }
}
