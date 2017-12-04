/*
 * This class is complete. Don't need any changes.
 */
package se.wasteland.struggle.client;

import se.wasteland.struggle.data.GameState;
import se.wasteland.struggle.screens.InGameScreen;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;

public class ClientCommunication extends Observable implements Runnable {

    /**
     * port         Server port
     * start        control variable
     * clientData   Reference to the ClientData object, that is sent.
     * host         the host ip -address
     * inStream     the ObjectinStream used to recieve the server Data
     * outStream    The objectoutStream used to send the client Data to the server
     * socket       the client Socket
     * iPaddress    The static address used to set the host
     * clientThread the thread that is started when communication is set.
     * gameOver     control variable
     * gamePaused   control variable
     * active       control variable
     * inGame       reference to the inGameScreen
     */
    private int port = 9010;
    private int start = 0;
    private ClientData clientData;
    private InetAddress host;
    private ObjectInputStream inStream = null;
    private ObjectOutputStream outStream = null;
    private Socket socket;
    private String ipAddress = "193.10.234.42";
    private Thread clientThread;
    private boolean gameOver = false;
    private int gamePaused = 0;
    private boolean active = false;
    private InGameScreen inGame;

    /**
     * The empty contstructor
     */
    public ClientCommunication() {
        try {
            host = InetAddress.getByName(ipAddress);
            clientThread = new Thread(this);
        } catch (UnknownHostException ex) {
        }
    }

    /**
     * Contructor for the ClientCommunicaton class.
     * @param ipAddress The Address of the server
     */
    public ClientCommunication(String ipAddress) {
        this.ipAddress = ipAddress;
        clientData = new ClientData();

        try {
            host = InetAddress.getByName(ipAddress);
            clientThread = new Thread(this);
        } catch (UnknownHostException ex) {
        }
    }

    /**
     * this method closes the socket and sets the gameOver control boolean to true.
     */
    public void closeSocket() {
        try {
            socket.close();
        } catch (IOException ex) {
        }
        gameOver = true;
        clientThread.interrupt();
    }

    /**
     * This method is used for the creation of the socket the client
     * uses for communication with the server.
     */
    public void createSocket() throws IOException {
        socket = new Socket(host, port);
    }

    /**
     * This method is used for creation of the in
     * and outstreams used for communication with the server.
     */
    public void createStreams() throws IOException, NullPointerException {
        outStream = new ObjectOutputStream(socket.getOutputStream());
        inStream = new ObjectInputStream(socket.getInputStream());
    }

    /**
     * This method is used for connecting the client to the server.
     * After this method has recieved it's second inpacket
     * it starts the clientthread and joinGameScreen.start();
     */
    public void connectToServer() throws ClassNotFoundException, IOException {
        outStream.writeObject(clientData);
        outStream.flush();

        do {
            inStream.readObject();
            start++;
        } while (start < 2);
        System.out.println("****START****");
        active = true;
        clientThread.start();
        setChanged();
        notifyObservers();
    }

    /**
     * This method checks if the communicator is active
     * @return
     */
    public boolean isActive() {
        return active;
    }

    /**
     * This method is used by the client
     * to recive gamestate objects from the gameserver,
     * and forwards it to gameView.
     */
    public void receiveObject() {
        try {
            GameState gameState = (GameState) inStream.readObject();
            inGame.readGameState(gameState);
        } catch (ClassNotFoundException ex) {
            System.out.println("RecieveObjcet Casting: " + ex);
        } catch (NullPointerException ex) {
            System.out.println("NullPointerException Broken Object: " + ex);
        } catch (IOException ex) {
            System.out.println("IOException Broken Object: " + ex);
        }
    }

    /**
     * The thread run method.
     */
    public void run() {
        while (gameOver == false) {
            receiveObject();
        }
    }

    /**
     * sendObject anvÃƒÂ¤nds fÃƒÂ¶r att sÃƒÂ¤nda
     * klientdata till servern.
     * @param in
     */
    public void sendObject() {
        try {
            outStream.writeObject(clientData);
            outStream.flush();
        } catch (IOException ex) {
            System.out.println("sendObject(): " + ex);
        }
    }

    /**
     * This method is used for the creation
     * of references between this class and
     * the ClientData class.
     * @param gameView
     */
    public void setClientData(ClientData clientData) {
        this.clientData = clientData;
    }

    /**
     * This method is used for the creation of references
     * between this class and GameView class.
     * @param gameView
     */
    public void setGameViewReference(InGameScreen inGame) {
        this.inGame = inGame;
    }

    /**
     * Closes the client communication.
     */
    public void deleteClientCom() {
        clientData = null;
        setChanged();
        notifyObservers();
    }
}
