package se.wasteland.struggle.server;

import se.wasteland.struggle.data.GameState;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import javax.swing.JTextArea;

public class ServerClientConnect extends Observable  implements Runnable {
    /**
     * allConnected
     * serverPort
     * disConnectedPlayerCount
     * numberOfPlayers
     * playerCount
     * threadsToClose
     * handler
     * engine
     * gameState
     * serverIP
     * outputTextArea
     * serverSocket
     * serverThread
     */
    
    private boolean allConnected = false;
    private final int serverPort = 9010;
    private int disConnectedPlayerCount = 0;
    private int numberOfPlayers;
    private int playerCount;
    private int threadsToClose;

    private ClientHandler[] handler;
    private ServerEngine engine;
    private GameState gameState;
    private InetAddress serverIP;
    private JTextArea outputTextArea;
    private ServerSocket serverSocket;
    private Thread serverThread = new Thread(this);

    /**
     * This method sets the number of players.
     */
    public void setNumberOfPLayers(int in){
        numberOfPlayers = in;
        handler  = new ClientHandler[numberOfPlayers];
    }
    
    /**This is the constructor for this class.
     * It creates an instance of ServerEnginge
     * and GameState. Sets refernces between the two
     * and then creates an ServerSocket.
     */
    public void createServerSocket() {
       engine = new ServerEngine();
       gameState = new GameState();
       engine.setPlayTime(Integer.parseInt(ServerMainWindow.playTime.getSelectedItem().toString()));

       engine.setGameStateReference(gameState);

       try {
            serverSocket = new ServerSocket(serverPort);
            outputTextArea.append("Starting server on port " + serverPort + " with address " + getLocalAddress() + "\n");
       } catch(IOException e) {
            System.out.println(e);
            System.exit(1);
       }
    }

    /**
     * This method is used for retreving the local address
     * of th computer that the server is running on.
     * @return hostname
     */
    public String getLocalAddress(){
        String hostname = "";

        try {
            serverIP = InetAddress.getLocalHost();
            hostname = serverIP.toString() ;
        } catch (Exception e) {}

        return hostname;
    }

    /**
     * This is the main thread of this class.
     * It waits for incomming connections
     * on the serversocket. When an connection is astablished
     * it creates an ClientHandler instance, sets a reference for
     * the engine and adds an observer on this object.
     *
     * When the correct amount of players has been reached
     * the observers are notified of this and the engine thread is
     * started.
     */
   public void run() {
        createServerSocket();

       // kÃ¶r skicka och ta emot i en oÃ¤ndlig loop
        while(allConnected == false){
            System.out.println("Wating for clients");
            try {
               outputTextArea.append("Waiting for client to connect\n");
               Socket clientSocket = serverSocket.accept();
               handler[playerCount] = new ClientHandler(clientSocket, playerCount, gameState, this);
               handler[playerCount].setEngineReference(engine);
               handler[playerCount].setServerReference(this);
               addObserver(handler[playerCount]);
               playerCount++;
            } catch (Exception ex) {
               outputTextArea.append("Problem with client connection \n" + ex + "\n");
            }

            // Notify clients when all players are connected and start game.
            if (playerCount == numberOfPlayers) {
               threadsToClose = numberOfPlayers * 2;
               setChanged();
               notifyObservers();
               engine.startEngine();
               allConnected = true;
            }
        }
    }

   /**
    * This method connects the clients.
    */
    public void connectClients(){
        while(allConnected == false){
            System.out.println("Wating for clients");
            try {
               outputTextArea.append("Waiting for client to connect\n");
               Socket clientSocket = serverSocket.accept();
               handler[playerCount] = new ClientHandler(clientSocket, playerCount, gameState, this);
               handler[playerCount].setEngineReference(engine);
               handler[playerCount].setServerReference(this);
               addObserver(handler[playerCount]);
               playerCount++;
            } catch (Exception ex) {
               outputTextArea.append("Problem with client connection \n" + ex + "\n");
            }

            // Notify clients when all players are connected and start game.
            if (playerCount == numberOfPlayers) {
               setChanged();
               notifyObservers();
               engine.startEngine();
               allConnected = true;
            }
        }
    }

    /**
     * This method restarts the game session.
     */
    public void restartGame() {
        disConnectedPlayerCount++;

        if(threadsToClose == disConnectedPlayerCount){
            outputTextArea.append("Waiting for client to connect\n");
            for(int h = 0; h < numberOfPlayers; h++){
                handler[h].killRecieveThread();
                System.out.println(handler[h].getR());
                handler[h].killSendThread();
                System.out.println(handler[h].getS());
                handler[h] = null;
            }
            System.out.println("FOR over");
            disConnectedPlayerCount = 0;
            allConnected = false;
            engine.stopEngine();
            engine = null;
            gameState = null;
            playerCount = 0;
            disConnectedPlayerCount = 0;
            try {
                serverSocket.close();
            } catch (IOException ex) {
                System.out.println("ServerClientConnect restartGame " + ex);
            }
            serverSocket = null;
            createServerSocket();
            connectClients();
        }
    }
    
    /**
     * This method is used for the creation of
     * refernces between this class and the ServerMainWindow.
     * This makes it possible to print informaion in the serverwindow.
     * @param window
     */
    public void setOutputWindow(JTextArea window) {
        outputTextArea = window;
    }

    /**
     * This method is used for starting the main thread in this class.
     */
    public void startServer() {
        try {
            serverThread.start();
        } catch (IllegalThreadStateException ex) {
            outputTextArea.append("Could not start server\n " + ex);
        }
    }
}