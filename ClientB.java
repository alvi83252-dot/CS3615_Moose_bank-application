import java.io.*;
import java.net.*;

public class ClientB {
    public static void main(String[] args) throws IOException {

        // Set up the socket, in and out variables

        Socket ClientBSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        int ClientBSocketNumber = 4545;
        String ClientBServerName = "localhost";
        String ClientBClientID = "ClientB";

        try {
            ClientBSocket = new Socket(ClientBServerName, ClientBSocketNumber);
            out = new PrintWriter(ClientBSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(ClientBSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: localhost ");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: "+ ClientBSocketNumber);
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String fromServer;

        System.out.println("Initialised " + ClientBClientID + " client and IO connections");


        while (true) {
            System.out.println("\nClientB Menu");
            System.out.println("1. Add_money");
            System.out.println("2. Subtract_money");
            System.out.println("3. Transfer_money to A");
            System.out.println("4. Transfer_money to C");
            System.out.println("5. Exit");

            String choice = stdIn.readLine();

            if (choice.equals("1")) {
                System.out.println("Enter amount to add: ");
                String amount = stdIn.readLine();
                out.println("Add_money B " + amount);

                fromServer = in.readLine();
                System.out.println(ClientBClientID + " received: " + fromServer + " from MooseBankServer");
            } else if (choice.equals("2")) {
                System.out.println("Enter amount to subtract: ");
                String amount = stdIn.readLine();
                out.println("Subtract_money B " + amount);

                fromServer = in.readLine();
                System.out.println(ClientBClientID + " received: " + fromServer + " from MooseBankServer");
            } else if (choice.equals("3")) {
                System.out.println("Enter amount to transfer to A: ");
                String amount = stdIn.readLine();
                out.println("Transfer_money B A " + amount);

                fromServer = in.readLine();
                System.out.println(ClientBClientID + " received: " + fromServer + " from MooseBankServer");
            } else if (choice.equals("4")) {
                System.out.println("Enter amount to transfer to C: ");
                String amount = stdIn.readLine();
                out.println("Transfer_money B C " + amount);

                fromServer = in.readLine();
                System.out.println(ClientBClientID + " received: " + fromServer + " from MooseBankServer");
            } else if (choice.equals("5")) {
                out.close();
                in.close();
                ClientBSocket.close();
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
