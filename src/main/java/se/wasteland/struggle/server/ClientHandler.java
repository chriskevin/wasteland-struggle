package se.wasteland.struggle.server;

import se.wasteland.struggle.data.GameState;

import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

public class ClientHandler implements Observer {
    private RecieveThread reciever;
    private SendThread sender;
    private ServerClientConnect server;
    private ServerEngine engine;


    /**
     * This is the contructor for the ClientHandler class.
     * It takes a socket, playerId and a GameState reference as parameters.
     * @param clientSocket
     * @param playerID
     * @param gameState
     */
    public ClientHandler(Socket clientSocket, int playerID, GameState gameState, ServerClientConnect server) {
        reciever = new RecieveThread(clientSocket, playerID, this);
        sender = new SendThread(clientSocket, gameState, this, playerID);
        this.server = server;
    }


    /**
     * This method is used for the creation of
     * reference between this class and the ServerEnginge class.
     * @param engine
     */
    public void setEngineReference(ServerEngine engine) {
        reciever.setEngineReference(engine);
    }

    /**
     * This method is used for the creation of references between this class
     * and the ServerClientConnectClass.
     * @param server
     */
    public void setServerReference(ServerClientConnect server){
        reciever.setServerRefernce(server);
    }

    /**
     * This method starts the sender and reciever threads
     * when the object it's observing changes state.
     *
     * @param o
     * @param arg
     */
    public void update(Observable o, Object arg) {
        System.out.println("Client update Sender is:   " + sender.getState().toString());
        System.out.println("Client update Reciever is:   " + reciever.getState().toString());
        if(sender.getState().toString().equalsIgnoreCase("NEW"))
        sender.start();
        if(reciever.getState().toString().equalsIgnoreCase("NEW"))
        reciever.start();
        System.out.println("Client handler: UPDATED");
    }


    /**
     * This method is used for killing the reciecer thread
     */
    public void killRecieve(){
        reciever.setRun();
    }

    /**
     * This method is used for killing the sender thread
     */
    public void killSend(){
        sender.setRun();
    }
    /**
     * This method is used for killing the clienthandler
     */
    public void killHandler(){
        System.out.println("KILLHANDLER");
        server.restartGame();
    }

    /**
     * This method is used for killing the send thread
     */
    public void killSendThread(){
//        try {
            System.out.println("BEFORE SENDTHREAD KILLED");
            sender.interrupt();
            System.out.println("AFTER SENDTHREAD KILLED");
//        } catch (InterruptedException ex) {
//            System.out.println("Killsend failed");
//        }
    }

    /**
     * This methid checks the state of the reciver thread
     * @return
     */
    public String getR(){
        return reciever.getState().toString();
    }

    /**
     * This method is used for checking the status of the send thread.
     * @return
     */
    public String getS(){
        return sender.getState().toString();
    }

    /**
     * This method is used för killing the reciever thread
     */
    public void killRecieveThread(){
//        try {
            System.out.println("BEFORE RECIEVETHREAD KILLED");
            reciever.interrupt();
            System.out.println("AFTER RECIEVETHREAD KILLED");
//        } catch (InterruptedException ex) {
//           System.out.println("KillRecieve failed");
//        }
    }

}