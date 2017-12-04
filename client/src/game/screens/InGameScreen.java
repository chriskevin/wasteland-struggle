package game.screens;

import game.Game;
import game.HUD;
import game.InGameMenu;
import game.Key;
import game.client.ClientCommunication;
import game.client.ClientData;
import game.client.GameView;
import game.data.GameState;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

public class InGameScreen extends Screen {

    private ClientCommunication clientComm;
    //private Debug debug;
    private Game game;
    public GameView gameView;
    //private Engine engine;
    private InGameMenu inGameMenu;
    private JLayeredPane layeredPane;
    private HUD hud;
    //private Overlay overlay;
    public PausedScreen pause;

    public InGameScreen(Game game) {
        this.game = game;
        initComponents();
    }

    @Override
    public void catchKey(Key key) {
        int keyCode = key.getKeyCode();
        String state = key.getState();

        if (keyCode == KeyEvent.VK_ESCAPE && state.equals("released")) {
            if (inGameMenu.isVisible() == false) {
                inGameMenu.setVisible(true);
            } else {
                inGameMenu.setVisible(false);
            }

        } else if (keyCode == KeyEvent.VK_1) {
            if (state.equals("pressed")) {
                hud.setVisible(true);
            } else if (state.equals("released")) {
                hud.setVisible(false);
            }

        } else if (keyCode == KeyEvent.VK_P) {
            if (state.equals("pressed")) {
                if (gameView != null) {
                    if (clientComm != null) {
                        ClientData clientData = new ClientData();
                        clientData.setKey(key);
                        clientComm.setClientData(clientData);
                        clientComm.sendObject();
                    }
                }
            }

        } else {
            if (gameView != null) {
                if (clientComm != null) {
                    ClientData clientData = new ClientData();
                    clientData.setKey(key);
                    clientComm.setClientData(clientData);
                    clientComm.sendObject();
                }
            }
        }
    }

    private void initComponents() {
        layeredPane = new JLayeredPane();
        hud = new HUD();
        inGameMenu = new InGameMenu(this);
        pause = new PausedScreen();

        hud.setVisible(false);
        inGameMenu.setVisible(false);
        pause.setVisible(false);

        setPreferredSize(new Dimension(800, 600));
        setLayout(null);

        layeredPane.add(hud, new Integer(1));
        layeredPane.add(inGameMenu, new Integer(2));
        layeredPane.add(pause, new Integer(3));
        add(layeredPane);

        layeredPane.setBounds(0, 0, 800, 600);
    }

    public void readGameState(GameState gameState) {
        if (gameState.pause == 0) {
            pause.setVisible(false);

            if (!gameState.gameOver) {
                gameView.readGameState(gameState);
            } else {
                game.musicPlayer.playChannel2(10);
                JOptionPane.showMessageDialog(gameView, "Game Over \n Player 1: " + gameState.playerScore[0] + " Rakhs"
                        + "\n Player 2: " + gameState.playerScore[1] + " Rakhs"
                        + "\n Player 3: " + gameState.playerScore[2] + " Rakhs"
                        + "\n Player 4: " + gameState.playerScore[3] + " Rakhs");
                quitGame();
            }
        } else if (gameState.pause == 1) {
            pause.setVisible(true);
        }
    }

    public void setClientComm(ClientCommunication clientComm) {
        this.clientComm = clientComm;
    }

    public void startGame() {
        if (clientComm != null) {
            gameView = new GameView();
            gameView.setHUD(hud);
            layeredPane.add(gameView, JLayeredPane.DEFAULT_LAYER);
        }
    }

    public void quitGame() {
        clientComm.closeSocket();
        clientComm.deleteClientCom();
        gameView = null;
        game.disconnect();
        game.musicPlayer.stopChannel1();
        game.musicPlayer.playChannel1(1, true);
        game.gotoView(game.startScreen);
    }
}
