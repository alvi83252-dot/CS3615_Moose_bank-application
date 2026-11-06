import java.io.*;
import java.net.*;

public class ClientA {
    public static void main(String[] args) throws IOException {

        // Set up the socket, in and out variables

        Socket ClientASocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        int ClientASocketNumber = 4545;
        String ClientAServerName = "localhost";
        String ClientAClientID = "ClientA";

        try {
            ClientASocket = new Socket(ClientAServerName, ClientASocketNumber);
            out = new PrintWriter(ClientASocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(ClientASocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: localhost ");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: "+ ClientASocketNumber);
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

        String fromServer;

        System.out.println("Initialised "+ ClientAClientID + " client and IO connections");

        while (true) {
            System.out.println("\nClientA Menu");
            System.out.println("1. Add_money");
            System.out.println("2. Subtract_money");
            System.out.println("3. Transfer_money to B");
            System.out.println("4. Transfer_money to C");
            System.out.println("5. Exit");

            String choice = stdIn.readLine();

            if (choice.equals("1")) {
                System.out.println("Enter amount to add: ");
                String amount = stdIn.readLine();
                out.println("Add_money A " + amount);

                fromServer = in.readLine();
                System.out.println(ClientAClientID + " received: " + fromServer + " from MooseBankServer");
            } else if (choice.equals("2")) {
                System.out.println("Enter amount to subtract: ");
                String amount = stdIn.readLine();
                out.println("Subtract_money A " + amount);

                fromServer = in.readLine();
                System.out.println(ClientAClientID + " received: " + fromServer + " from MooseBankServer");
            } else if (choice.equals("3")) {
                System.out.println("Enter amount to transfer to B: ");
                String amount = stdIn.readLine();
                out.println("Transfer_money A B " + amount);

                fromServer = in.readLine();
                System.out.println(ClientAClientID + " received: " + fromServer + " from MooseBankServer");
            } else if (choice.equals("4")) {
                System.out.println("Enter amount to transfer to C: ");
                String amount = stdIn.readLine();
                out.println("Transfer_money A C " + amount);

                fromServer = in.readLine();
                System.out.println(ClientAClientID + " received: " + fromServer + " from MooseBankServer");
            } else if (choice.equals("5")) {
                out.close();
                in.close();
                ClientASocket.close();
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
