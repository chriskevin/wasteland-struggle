package se.wasteland.struggle.screens;

import se.wasteland.struggle.Game;

import java.awt.Image;

public class StartScreen extends Screen {
    private Game game;
    private Image background;

    public StartScreen(Game game) {
        this.game = game;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        newButton = new javax.swing.JButton();
        optionsButton = new javax.swing.JButton();
        aboutButton = new javax.swing.JButton();
        quitButton = new javax.swing.JButton();

        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(800, 600));

        newButton.setBackground(new java.awt.Color(102, 102, 102));
        newButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gui/buttons/new_btn0.png"))); // NOI18N
        newButton.setBorder(null);
        newButton.setContentAreaFilled(false);
        newButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        newButton.setMaximumSize(new java.awt.Dimension(100, 23));
        newButton.setMinimumSize(new java.awt.Dimension(100, 23));
        newButton.setOpaque(false);
        newButton.setPreferredSize(new java.awt.Dimension(100, 23));
        newButton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gui/buttons/new_btn1.png"))); // NOI18N
        newButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newButtonActionPerformed(evt);
            }
        });

        optionsButton.setBackground(new java.awt.Color(102, 102, 102));
        optionsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gui/buttons/options_btn0.png"))); // NOI18N
        optionsButton.setBorder(null);
        optionsButton.setContentAreaFilled(false);
        optionsButton.setMaximumSize(new java.awt.Dimension(100, 23));
        optionsButton.setMinimumSize(new java.awt.Dimension(100, 23));
        optionsButton.setOpaque(false);
        optionsButton.setPreferredSize(new java.awt.Dimension(100, 23));
        optionsButton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gui/buttons/options_btn1.png"))); // NOI18N
        optionsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionsButtonActionPerformed(evt);
            }
        });

        aboutButton.setBackground(new java.awt.Color(102, 102, 102));
        aboutButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gui/buttons/about_btn0.png"))); // NOI18N
        aboutButton.setBorder(null);
        aboutButton.setContentAreaFilled(false);
        aboutButton.setMaximumSize(new java.awt.Dimension(100, 23));
        aboutButton.setMinimumSize(new java.awt.Dimension(100, 23));
        aboutButton.setOpaque(false);
        aboutButton.setPreferredSize(new java.awt.Dimension(100, 23));
        aboutButton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gui/buttons/about_btn1.png"))); // NOI18N
        aboutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutButtonActionPerformed(evt);
            }
        });

        quitButton.setBackground(new java.awt.Color(102, 102, 102));
        quitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gui/buttons/quit_btn0.png"))); // NOI18N
        quitButton.setBorder(null);
        quitButton.setContentAreaFilled(false);
        quitButton.setMaximumSize(new java.awt.Dimension(100, 23));
        quitButton.setMinimumSize(new java.awt.Dimension(100, 23));
        quitButton.setOpaque(false);
        quitButton.setPreferredSize(new java.awt.Dimension(100, 23));
        quitButton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gui/buttons/quit_btn1.png"))); // NOI18N
        quitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(newButton, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(optionsButton, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(aboutButton, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(quitButton, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                .addGap(740, 740, 740))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(newButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(optionsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(aboutButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(quitButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(479, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void quitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_quitButtonActionPerformed

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed
        game.musicPlayer.playChannel2(9);
        game.gotoView(game.joinGameScreen);
    }//GEN-LAST:event_newButtonActionPerformed

    private void optionsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionsButtonActionPerformed
        game.musicPlayer.playChannel2(9);
        game.gotoView(game.optionsScreen);
    }//GEN-LAST:event_optionsButtonActionPerformed

    private void aboutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutButtonActionPerformed
        game.musicPlayer.playChannel2(9);
        game.gotoView(game.aboutScreen);
    }//GEN-LAST:event_aboutButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aboutButton;
    private javax.swing.JButton newButton;
    private javax.swing.JButton optionsButton;
    private javax.swing.JButton quitButton;
    // End of variables declaration//GEN-END:variables

}
