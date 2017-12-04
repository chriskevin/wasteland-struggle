package se.wasteland.struggle;

import se.wasteland.struggle.screens.InGameScreen;

import javax.swing.JPanel;

public class InGameMenu extends JPanel {
    private InGameScreen inGameScreen;

    public InGameMenu(InGameScreen inGameScreen) {
        this.inGameScreen = inGameScreen;
        initComponents();

        setBounds(0, 0, 800, 600);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainMenuButton = new javax.swing.JButton();
        quitGameButton = new javax.swing.JButton();

        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(800, 600));
        setLayout(null);

        mainMenuButton.setText("Main Menu");
        mainMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainMenuButtonActionPerformed(evt);
            }
        });
        add(mainMenuButton);
        mainMenuButton.setBounds(10, 10, 230, 23);

        quitGameButton.setText("Quit Game");
        quitGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitGameButtonActionPerformed(evt);
            }
        });
        add(quitGameButton);
        quitGameButton.setBounds(10, 40, 230, 23);
    }// </editor-fold>//GEN-END:initComponents

    private void quitGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitGameButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_quitGameButtonActionPerformed

    private void mainMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainMenuButtonActionPerformed
         inGameScreen.quitGame();
    }//GEN-LAST:event_mainMenuButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton mainMenuButton;
    private javax.swing.JButton quitGameButton;
    // End of variables declaration//GEN-END:variables

}
