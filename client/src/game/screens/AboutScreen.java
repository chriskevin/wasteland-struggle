package game.screens;

import game.Game;

public class AboutScreen extends Screen {
    private Game game;

    public AboutScreen(Game game) {
        this.game = game;
        initComponents();
        instructionsLabel.setVisible(false);
        storyTextArea.setText("No one knows when, but hundreds of years ago a great war broke out and humaity suffered greatly from the devastating nuclear warfare that followed.\nThe world as we know it is now embossed with death and mutation. Violence and anarchy rules the primitive individuals calling theselves earthlings.\nThe former known society, masscommunication and infrastructure has ceased to exist. Natualrecources as oil insÂ´nt been used anymore and the \ntechnology from the formal world har taken several steps back.\n\nYou are a mutant, a highwayman that doesenÂ´t belong to the the clans that rule the inhabited parts of centraleurope.\nYou have no moral or ethic ulterior motive but is driven by the most primitive needs, gathering food for the day and surviving the night.\nYou are severe mentally degraded compares to the people of the formal world. The only laws that rule your world is survival of the fittest.\n\nCentraleuropes inhabitants is unfortunate not only mutans, the great Behemoths, mutated from an unknoen source is the top of the foodchain in this barren land.\nThe Behemoths offspring, Rakh is the only thing that provides food to all mutants. The Rakh-egg is the highest currency in the new world.\nDifferent parts of these eggs provide the rawmaterial for food and even drugs. Rakh shells are used for equipment and some parts are even used for medicine.\n\nThe task that lies ahead is to gather as many Raks as possible, this is the only way to survive. But there is one catch, you are not alone in\nthe task of surviving. Your rivals wants the same Rakhs. The new world is not a fogiving one and you must use cunning, speed, bravery and have some luck \nto succeed in gathering Rakhs while defeating your sworn enemies.\n");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        instructionsLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        storyTextArea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(800, 600));

        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gui/buttons/back_btn0.png"))); // NOI18N
        backButton.setBorder(null);
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

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gui/buttons/story_btn0.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.setMaximumSize(new java.awt.Dimension(100, 23));
        jButton1.setMinimumSize(new java.awt.Dimension(100, 23));
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gui/buttons/story_btn1.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gui/buttons/howto_btn0.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setContentAreaFilled(false);
        jButton2.setMargin(new java.awt.Insets(2, 8, 2, 8));
        jButton2.setMaximumSize(new java.awt.Dimension(100, 23));
        jButton2.setMinimumSize(new java.awt.Dimension(100, 23));
        jButton2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gui/buttons/howto_btn1.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLayeredPane1.setPreferredSize(new java.awt.Dimension(600, 500));

        instructionsLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
        instructionsLabel.setForeground(new java.awt.Color(255, 255, 255));
        instructionsLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/instructions.jpg"))); // NOI18N
        instructionsLabel.setBounds(20, 20, 560, 460);
        jLayeredPane1.add(instructionsLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        storyTextArea.setBackground(new java.awt.Color(51, 51, 51));
        storyTextArea.setColumns(20);
        storyTextArea.setEditable(false);
        storyTextArea.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        storyTextArea.setForeground(new java.awt.Color(255, 255, 255));
        storyTextArea.setLineWrap(true);
        storyTextArea.setRows(5);
        storyTextArea.setWrapStyleWord(true);
        storyTextArea.setBorder(null);
        jScrollPane1.setViewportView(storyTextArea);

        jScrollPane1.setBounds(20, 20, 560, 460);
        jLayeredPane1.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jLabel1.setBounds(0, 0, 600, 500);
        jLayeredPane1.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(89, 89, 89))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        game.musicPlayer.playChannel2(9);
        game.gotoView(game.startScreen);
    }//GEN-LAST:event_backButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        storyTextArea.setVisible(true);
        instructionsLabel.setVisible(false);
        game.musicPlayer.playChannel2(9);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        instructionsLabel.setVisible(true);
        storyTextArea.setVisible(false);
        game.musicPlayer.playChannel2(9);
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JLabel instructionsLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea storyTextArea;
    // End of variables declaration//GEN-END:variables

}
