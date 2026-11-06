import java.net.*;
import java.io.*;

public class MooseBankServer {
  public static void main(String[] args) throws IOException {

	ServerSocket MooseBankServerSocket = null;
    boolean listening = true;
    String MooseBankServerName = "MooseBankServer";
    int MooseBankServerNumber = 4545;
        
    MooseBankActionState ourSharedActionStateObject = new MooseBankActionState();
        
    // Make the server socket

    try {
      MooseBankServerSocket = new ServerSocket(MooseBankServerNumber);
    } catch (IOException e) {
      System.err.println("Could not start " + MooseBankServerName + " specified port.");
      System.exit(-1);
    }
    System.out.println(MooseBankServerName + " started");

     // Listen for connections and handle them in separate threads
     System.out.println("Account A balance: 1000");  
     System.out.println("Account B balance: 1000");  
     System.out.println("Account C balance: 1000");  

    while (listening) {
      new MooseBankThread(MooseBankServerSocket.accept(), "MooseBankThread1", ourSharedActionStateObject).start();
      new MooseBankThread(MooseBankServerSocket.accept(), "MooseBankThread2", ourSharedActionStateObject).start();
      new MooseBankThread(MooseBankServerSocket.accept(), "MooseBankThread3", ourSharedActionStateObject).start();

      System.out.println("New " + MooseBankServerName + " thread started.");
  }

  MooseBankServerSocket.close();
  }
}