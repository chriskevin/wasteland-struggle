package game.server;

import game.client.ClientData;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class RecieveThread extends Thread {
    /**
     * playerID     The clients ID.
     * inStream     The recieving stream.
     * handler      The handler.
     * engine       Reference to the game engine.
     * server       Reference to the server.
     * run          If its running?
     */

    private int playerID;
    private ObjectInputStream inStream;
    private ClientHandler handler;
    private ServerEngine engine;
    private ServerClientConnect server;
    private boolean run = true;

    /**This is the constructor for this class. It takes
     * an socket and a playerId as parameters and
     * starts an instream to be able to recive objects.
     * @param clientSocket
     * @param playerID
     */
    public RecieveThread(Socket clientSocket, int playerID, ClientHandler handler) {
        this.playerID = playerID;
        this.handler = handler;

        try {
            inStream = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException ex) {
            System.out.println("StrÃ¶m skapare Klienthanterare " + ex);
        }
    }


    /**
     * This method reads the instream for objects
     * and forwards these objects to the ClientData class.
     */
    public void recieveClientInput() {
            try {
                //System.out.println("Recieve");
                ClientData clientData = (ClientData) inStream.readObject();
                clientData.setPlayerID(playerID);
                engine.readClientData(clientData);
            } catch (ClassNotFoundException ex) {
                System.out.println("class client handler " + ex);
            } catch (IOException ex) {
                System.out.println("Recievethread client handler " + ex);
                handler.killRecieve();
            }
    }

    /**
     * This method prevents the thread from running?
     */
    public void setRun(){
        run = false;
    }
    
    @Override
    /**This is the main thread of this class
     * where the recieveClientInput() method
     * is being run.
     */
    public void run() {
        System.out.println("RecieveThread: start Run");
        while(run == true){
            recieveClientInput();
        }
        handler.killHandler();
    }

    /**This method sets the nesserary refernces between this
     * class and the ServerEnginge class.
     * @param engine
     */
    public void setEngineReference(ServerEngine engine) {
        this.engine = engine;
    }

    /**
     * This method sets a reference to the ServerClientConnect.
     * @param server
     */
    void setServerRefernce(ServerClientConnect server) {
        this.server = server;
    }
}
