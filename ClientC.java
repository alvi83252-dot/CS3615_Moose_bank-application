import java.io.*;
import java.net.*;

public class ClientC {
    public static void main(String[] args) throws IOException {

        // Set up the socket, in and out variables

        Socket ClientCSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        int ClientCSocketNumber = 4545;
        String ClientCServerName = "localhost";
        String ClientCClientID = "ClientC";

        try {
            ClientCSocket = new Socket(ClientCServerName, ClientCSocketNumber);
            out = new PrintWriter(ClientCSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(ClientCSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: localhost ");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: "+ ClientCSocketNumber);
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String fromServer;

        System.out.println("Initialised " + ClientCClientID + " client and IO connections");
        
        // This is modified as it's the client that speaks first

       while (true) {
            System.out.println("\nClientC Menu");
            System.out.println("1. Add_money");
            System.out.println("2. Subtract_money");
            System.out.println("3. Transfer_money to A");
            System.out.println("4. Transfer_money to B");
            System.out.println("5. Exit");

            String choice = stdIn.readLine();

            if (choice.equals("1")) {
                System.out.println("Enter amount to add: ");
                String amount = stdIn.readLine();
                out.println("Add_money C " + amount);

                fromServer = in.readLine();
                System.out.println(ClientCClientID + " received: " + fromServer + " from MooseBankServer");
            } else if (choice.equals("2")) {
                System.out.println("Enter amount to subtract: ");
                String amount = stdIn.readLine();
                out.println("Subtract_money C " + amount);

                fromServer = in.readLine();
                System.out.println(ClientCClientID + " received: " + fromServer + " from MooseBankServer");
            } else if (choice.equals("3")) {
                System.out.println("Enter amount to transfer to A: ");
                String amount = stdIn.readLine();
                out.println("Transfer_money C A " + amount);

                fromServer = in.readLine();
                System.out.println(ClientCClientID + " received: " + fromServer + " from MooseBankServer");
            } else if (choice.equals("4")) {
                System.out.println("Enter amount to transfer to B: ");
                String amount = stdIn.readLine();
                out.println("Transfer_money C B " + amount);

                fromServer = in.readLine();
                System.out.println(ClientCClientID + " received: " + fromServer + " from MooseBankServer");
            } else if (choice.equals("5")) {
                out.close();
                in.close();
                ClientCSocket.close();
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

