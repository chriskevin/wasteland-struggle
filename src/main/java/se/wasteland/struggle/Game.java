package se.wasteland.struggle;

import se.wasteland.struggle.client.ClientCommunication;
import se.wasteland.struggle.client.GameView;
import se.wasteland.struggle.exception.ExceptionHandler;
import se.wasteland.struggle.screens.*;
import se.wasteland.struggle.sound.Player;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Game extends JFrame implements Observer {
    /**
     * screens        A list with references to all the screens
     * mainPanel      The panel which contains all screens
     * aboutScreen    The about screen
     * engine         The single player engine (Not yet implemented)
     * gameView       The graphical representation of a multiplayer game
     * joinGameScreen The join game screen
     * optionsScreen  The options screen
     * startScreen    The start screen
     */
    private JLabel backgroundLabel;
    private JLayeredPane mainLayeredPane;
    private JPanel mainPanel;
    private Screen currentView;

    public ClientCommunication clientComm;
    private ExceptionHandler exceptionHandler;
    //public Settings settings;

    private ArrayList<Screen> screens = new ArrayList();

    public AboutScreen aboutScreen;
    public ControlsSettingsScreen controlsSettingsScreen;
    //public Engine engine;
    public GameView gameView;
    public InGameScreen inGameScreen;
    public JoinGameScreen joinGameScreen;
    public OptionsScreen optionsScreen;
    public StartScreen startScreen;
    public SoundSettingsScreen soundSettingsScreen;
    
    public Player musicPlayer;

    /**
     * The class constructor which initiates the game and GUI.
     */
    public Game() {
        initComponents();
        exceptionHandler = new ExceptionHandler();
        musicPlayer = new Player();

        exceptionHandler.addObserver(this);
        musicPlayer.playChannel1(1, true);
    }

    /**
     * Internal KeyAdapter class which creates a new Key object
     * and calls the passKey method.
     */
    public KeyListener keyListener = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent evt) {
            passKey(new Key(evt.getKeyCode(), "pressed"));
        }

        @Override
        public void keyReleased(KeyEvent evt) {
            passKey(new Key(evt.getKeyCode(), "released"));
        }
    };

    /**
     * This method changes the view by hiding and showing the screens.
     * @param screen The screen which is to be shown.
     */
    private void changeView(Screen screen) {
        currentView = screen;
        for (int i = 0; i < screens.size(); i++) {
            if (!screens.get(i).equals(screen))
                screens.get(i).setVisible(false);
        }
        screen.setVisible(true);
    }

    /**
     * This method creates a connection to the game server.
     * @param ipAddress
     */
    public void createConnection(String ipAddress) {
        try {
            clientComm = new ClientCommunication(ipAddress);
            clientComm.addObserver(this);
            clientComm.createSocket();
            clientComm.createStreams();
            clientComm.connectToServer();
        } catch (ClassNotFoundException ex) {
            exceptionHandler.addException(ex);
        } catch (IOException ex) {
            exceptionHandler.addException(ex);
            clientComm = null;
        } catch (NullPointerException ex) {
            exceptionHandler.addException(ex);
            clientComm = null;
        }
    }

    /**
     * This method closes the connection to the gameserver
     * and resets all fields containing relations.
     */
    public void disconnect() {
        clientComm = null;
        mainPanel.remove(inGameScreen);
        screens.remove(inGameScreen);
        inGameScreen = null;
        inGameScreen = new InGameScreen(this);
        screens.add(inGameScreen);
        mainPanel.add(inGameScreen);
    }

    /**
     * This method jumps to another view and calls the
     * changeView method.
     * @param view The view to go to.
     */
    public void gotoView(Screen screen) {
        changeView(screen);
    }

    private void initComponents() {
        // Create base components
        mainPanel       = new JPanel();
        mainLayeredPane = new JLayeredPane();
        backgroundLabel = new JLabel();

        // Create screens
        aboutScreen            = new AboutScreen(this);
        controlsSettingsScreen = new ControlsSettingsScreen(this);
        inGameScreen           = new InGameScreen(this);
        joinGameScreen         = new JoinGameScreen(this);
        optionsScreen          = new OptionsScreen(this);
        startScreen            = new StartScreen(this);
        soundSettingsScreen    = new SoundSettingsScreen(this);
        currentView            = startScreen;

        // Set components properties
        backgroundLabel.setIcon(new ImageIcon(getClass().getResource("/images/startscreen.jpg")));
        backgroundLabel.setBounds(0, 0, 800, 600);
        mainLayeredPane.setBounds(0, 0, 800, 600);
        mainPanel.setBounds(0, 0, 800, 600);
        mainPanel.setOpaque(false);
        mainPanel.setFocusable(true);
        mainPanel.addKeyListener(keyListener);

        // Set screens visibility
        aboutScreen.setVisible(false);
        controlsSettingsScreen.setVisible(false);
        inGameScreen.setVisible(false);
        joinGameScreen.setVisible(false);
        optionsScreen.setVisible(false);
        soundSettingsScreen.setVisible(false);

        // Add base components
        add(mainLayeredPane);
        mainLayeredPane.add(backgroundLabel, new Integer(0));
        mainLayeredPane.add(mainPanel, new Integer(1));

        // Add screen references to list
        screens.add(aboutScreen);
        screens.add(controlsSettingsScreen);
        screens.add(inGameScreen);
        screens.add(joinGameScreen);
        screens.add(optionsScreen);
        screens.add(startScreen);
        screens.add(soundSettingsScreen);

        // Add screens to container
        mainPanel.add(startScreen);
        mainPanel.add(joinGameScreen);
        mainPanel.add(optionsScreen);
        mainPanel.add(aboutScreen);
        mainPanel.add(controlsSettingsScreen);
        mainPanel.add(soundSettingsScreen);
        mainPanel.add(inGameScreen);

        // Set frame properties
        getContentPane().setBackground(Color.BLACK);
        setTitle("Mutants");
        setSize(800, 600);
        //setExtendedState(MAXIMIZED_BOTH);
        //setUndecorated(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * This method passes a Key object to the current view
     * whenever a keyboard button is pressed or released.
     * @param key The key that was pressed or released
     */
    private void passKey(Key key) {
        currentView.catchKey(key);
    }

    /**
     * This method is called whenever an exception is added
     * to the ExceptionHandler or if the ClientCommunicator signals
     * to start the game.
     * @param o
     * @param arg
     */
    public void update(Observable o, Object arg) {
        if (o instanceof ExceptionHandler) {
            Exception ex = exceptionHandler.getLatest();
            currentView.catchException(ex);
        } else if (o instanceof ClientCommunication) {
            if (clientComm.isActive()) {
                inGameScreen.setClientComm(clientComm);
                inGameScreen.startGame();
                clientComm.setGameViewReference(inGameScreen);
                musicPlayer.stopChannel1();
                musicPlayer.playChannel1(2, true);
                gotoView(inGameScreen);
            } else {
                disconnect();
            }
        }
    }

    public static void main(String args[]) {
        new Game();
    }
}
