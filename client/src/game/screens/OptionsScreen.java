package game.screens;

import game.Game;

public class OptionsScreen extends Screen {
    private Game game;

    public OptionsScreen(Game game) {
        this.game = game;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        controlsButton = new javax.swing.JButton();
        soundButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();

        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(800, 600));

        controlsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gui/buttons/controls_btn0.png"))); // NOI18N
        controlsButton.setBorderPainted(false);
        controlsButton.setContentAreaFilled(false);
        controlsButton.setMaximumSize(new java.awt.Dimension(100, 23));
        controlsButton.setMinimumSize(new java.awt.Dimension(100, 23));
        controlsButton.setPreferredSize(new java.awt.Dimension(100, 23));
        controlsButton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gui/buttons/controls_btn1.png"))); // NOI18N
        controlsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                controlsButtonActionPerformed(evt);
            }
        });

        soundButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gui/buttons/sound_btn0.png"))); // NOI18N
        soundButton.setBorderPainted(false);
        soundButton.setContentAreaFilled(false);
        soundButton.setMaximumSize(new java.awt.Dimension(100, 23));
        soundButton.setMinimumSize(new java.awt.Dimension(100, 23));
        soundButton.setPreferredSize(new java.awt.Dimension(100, 23));
        soundButton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gui/buttons/sound_btn1.png"))); // NOI18N
        soundButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soundButtonActionPerformed(evt);
            }
        });

        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gui/buttons/back_btn0.png"))); // NOI18N
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setMaximumSize(new java.awt.Dimension(100, 23));
        backButton.setMinimumSize(new java.awt.Dimension(100, 23));
        backButton.setPreferredSize(new java.awt.Dimension(100, 23));
        backButton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gui/buttons/back_btn1.png"))); // NOI18N
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(backButton, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(soundButton, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(controlsButton, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                .addContainerGap(690, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(controlsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(soundButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(508, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        game.musicPlayer.playChannel2(9);
        game.gotoView(game.startScreen);
    }//GEN-LAST:event_backButtonActionPerformed

    private void controlsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_controlsButtonActionPerformed
        game.musicPlayer.playChannel2(9);
        game.gotoView(game.controlsSettingsScreen);
    }//GEN-LAST:event_controlsButtonActionPerformed

    private void soundButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soundButtonActionPerformed
        game.musicPlayer.playChannel2(9);
        game.gotoView(game.soundSettingsScreen);
    }//GEN-LAST:event_soundButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton controlsButton;
    private javax.swing.JButton soundButton;
    // End of variables declaration//GEN-END:variables

}
