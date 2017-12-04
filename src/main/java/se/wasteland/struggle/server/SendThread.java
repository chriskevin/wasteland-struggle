package se.wasteland.struggle.server;

import se.wasteland.struggle.data.GameState;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SendThread extends Thread {
    /**
     * gameOver     Game over or not.
     * run          If its running.
     * playerID     The clients ID.
     * handler      Reference to the handler.
     * gameState    Reference to the game state.
     * outStream    The sending stream.
     */

    private boolean run = true;
    private int playerID;
    
    private ClientHandler handler;
    private GameState gameState;
    private ObjectOutputStream outStream;

    /**
     * This is the contructor for the SendThread class.
     * It takes an clientsocket and a GameState class reference
     * as parameters.
     *
     * The constructor creates an outputstream for sending
     * messages.
     * @param clientSocket
     * @param gameState
     */
    public SendThread(Socket clientSocket, GameState gameState, ClientHandler handler, int playerID) {
        this.gameState = gameState;
        this.handler = handler;
        this.playerID = playerID;
        try {
            outStream = new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (IOException ex) {
            System.out.println("StrÃ¶mskapare Klienthanterare " + ex);
        }
    }
    
    /**
     * This method prevents the thread from running?
     */
    public void setRun(){
        run = false;
    }

    @Override
    @SuppressWarnings("static-access")
    /**
     * This is the main thread of this class.
     * It runs the sendGameState() method and sleeps
     * for 15 ms.
     */
    public void run() {
        System.out.println("SendThread: start Run");
        while (run == true) {
            sendGameState();
            try {
                this.sleep(12);
            } catch (InterruptedException ex) {
                System.out.println("Sleep failed:  "  + ex);
            }

        }
        handler.killHandler();
    }


    /**
     * This method is used for sending gamestate
     * objects to the clients and notify everyone that is
     * listening.
     */
    public synchronized void sendGameState() {
        //System.out.println("Send");
        //System.out.println("PLAYERID=" + playerID);
        try {
            outStream.reset();
            gameState.playerID = playerID;
            outStream.writeObject(gameState);
            outStream.flush();
            notifyAll();
        } catch (IOException e) {
            System.out.println("Sendthread client handler " + e);
            handler.killSend();
        }
    }
}
