package server;// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.io.*;
import java.sql.SQLException;
import java.util.List;

import ocsf.server.*;
import product.City;
import product.DigitalMap;
import product.Tour;
import product.content.Site;
import product.pricing.Purchase;
import user.member.MemberCard;
import user.member.SignInForm;
import chat.common.ChatIF;

/**
 * This class overrides some of the methods in the abstract
 * superclass in order to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */
public class EchoServer extends AbstractServer
{
    //Class variables *************************************************

    /**
     * The default port to listen on.
     */
    final public static int DEFAULT_PORT =5555;

    /**
     * The interface type variable. It allows the implementation of
     * the display method in the client.
     */
    ChatIF serverUI;


    //Constructors ****************************************************

    /**
     * Constructs an instance of the echo server.
     *
     * @param port The port number to connect on.
     */
    public EchoServer(int port)
    {
        super(port);
    }

    /**
     * Constructs an instance of the echo server.
     *
     * @param port The port number to connect on.
     * @param serverUI The interface type variable.
     */
    public EchoServer(int port, ChatIF serverUI) throws IOException
    {
        super(port);
        this.serverUI = serverUI;
    }


    //Instance methods ************************************************

    /**
     * This method handles any messages received from the client.
     *
     * @param msg The src.command.message received from the client.
     * @param client The connection from which the src.command.message originated.
     */
    public void handleMessageFromClient (Object msg, ConnectionToClient client)
    {
        if (msg instanceof SignInForm)
        {
            try {
                client.sendToClient(ConnectionToDatabase.SignIn(((SignInForm) msg).getUserName(),((SignInForm) msg).getPassword()));
            } catch (IOException e) {
                System.out.println("Error! EchoServer --> handleMessageFromClient --> SignInForm --> IOException");
                e.printStackTrace();
            } catch (SQLException e) {
                System.out.println("Error! EchoServer --> handleMessageFromClient --> SignInForm --> SQLException");
                e.printStackTrace();
            }
        }

        if (msg instanceof MemberCard)
        {
            try {
                client.sendToClient(ConnectionToDatabase.SignUp(((MemberCard) msg).getMemberIDByString(),((MemberCard) msg).getPermissionByString(),
                        ((MemberCard) msg).getUserName(),((MemberCard) msg).getPassword(),((MemberCard) msg).getPersonalName(),((MemberCard) msg).getEmail(),
                        ((MemberCard) msg).getPhoneNumberByString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (msg instanceof List<?>)
        {
            MemberCard memberCard=(MemberCard) ((List<?>) msg).get(0);
            ConnectionToDatabase.UpdateClient(memberCard.getMemberIDByString(),memberCard.getUserName(),memberCard.getPersonalName(),
                    memberCard.getPassword(),memberCard.getPhoneNumberByString(),memberCard.getEmail(),memberCard.getPermissionByString());
        }

        if (msg instanceof Site)
        {
            ConnectionToDatabase.UpdateContent(((Site) msg).getContendIDToString(),((Site) msg).getSiteName(),((Site) msg).getLocationCoordinateToString(),
                    ((Site) msg).getContentDurationToString(), ((Site) msg).getSiteTypeToString(), ((Site) msg).getSiteDescription()
                    , ((Site) msg). isSiteAccessibility());
        }

        if (msg instanceof DigitalMap)
        {
            ConnectionToDatabase.AddContentToMap(((DigitalMap) msg).getDigitalMapIDToString(),((DigitalMap) msg).getDigitalMapVersionToString(),
                    ((DigitalMap) msg).getDigitalMapDescription(),((DigitalMap) msg).getDigitalMapContents(),((DigitalMap) msg).getDigitalMapCost().getPriceToString(),
                    ((DigitalMap) msg).getDigitalMapCost().getLastApproval(),((DigitalMap) msg).getDigitalMapCost().getLastModifiedDateToString());
        }

        if (msg instanceof City)
        {
            ConnectionToDatabase.AddContentToCity(((City) msg).getCityIDToString(),((City) msg).getCityName(),((City) msg).getCityPriceToString(),
                    ((City) msg).getVersionToString(),((City) msg).getUpdateVersionDateToString(),((City) msg).getCityMaps(),
                    ((City) msg).getCityTours());
        }

        if (msg instanceof Tour)
        {
            ConnectionToDatabase.UpdateTour(((Tour)msg).getTourIDToString(),((Tour)msg).getTourName(),((Tour)msg).getTourDescription(),
                    ((Tour)msg).getTourTotalDurationToString(),	((Tour)msg).getTourSequence());
        }

        if (msg instanceof String) {
            String[] message = ((String) msg).split(" ");
            if (message[0].equals("userID")) {
                try {
                    client.sendToClient(ConnectionToDatabase.ReturnMemberCardByID(message[1]));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (message[0].equals("#SignOut")) {
                try {
                    client.sendToClient(ConnectionToDatabase.SignOut(message[1]));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (message[0].equals("#Search")) {
                if (message[1].equals("product")) {
                    try {
                        client.sendToClient(ConnectionToDatabase.SearchByID(message[2], message[3]));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if (message[1].equals("city")) {
                    try {
                        client.sendToClient(ConnectionToDatabase.SearchByCityName(message[3]));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                else if (message[1].equals("content")) {
                    try {
                        client.sendToClient(ConnectionToDatabase.SearchBySite(message[3]));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if (message[1].equals("description")) {
                    try {
                        client.sendToClient(ConnectionToDatabase.SearchByDescription(message[3]));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        if (msg instanceof Integer)
        {
            try {
                client.sendToClient(ConnectionToDatabase.SearchByCityID(msg.toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (msg instanceof Purchase)
        {
            try {
                client.sendToClient(ConnectionToDatabase.AddPurchase(((Purchase) msg).getPurchaseIDByString(),((Purchase) msg).getDateOfPurchaseByString(),
                        ((Purchase) msg).getCostByString(),((Purchase) msg).getPurchaseTypeInString(),((Purchase) msg).getPurchasedCityIDByString(),
                        ((Purchase) msg).getPurchasedMapIDByString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method handles all data coming from the UI
     *
     * @param message The src.command.message from the UI
     */
    public void handleMessageFromServerUI(String message)
    {
        if (message.charAt(0) == '#')
        {
            runCommand(message);
        }
        else
        {
            // send src.command.message to clients
            serverUI.display(message);
            this.sendToAllClients("SERVER MSG> " + message);
        }
    }

    /**
     * This method executes server commands.
     *
     * @param message String from the server console.
     */
    private void runCommand(String message)
    {
        // run commands
        // a series of if statements

        if (message.equalsIgnoreCase("#quit"))
        {
            quit();
        }
        else if (message.equalsIgnoreCase("#stop"))
        {
            stopListening();
        }
        else if (message.equalsIgnoreCase("#close"))
        {
            try
            {
                close();
            }
            catch(IOException e) {}
        }
        else if (message.toLowerCase().startsWith("#setport"))
        {
            if (getNumberOfClients() == 0 && !isListening())
            {
                // If there are no connected clients and we are not
                // listening for new ones, assume server closed.
                // A more exact way to determine this was not obvious and
                // time was limited.
                int newPort = Integer.parseInt(message.substring(9));
                setPort(newPort);
                //error checking should be added
                serverUI.display
                        ("Server port changed to " + getPort());
            }
            else
            {
                serverUI.display
                        ("The server is not closed. Port cannot be changed.");
            }
        }
        else if (message.equalsIgnoreCase("#start"))
        {
            if (!isListening())
            {
                try
                {
                    listen();
                }
                catch(Exception ex)
                {
                    serverUI.display("Error - Could not listen for clients!");
                }
            }
            else
            {
                serverUI.display
                        ("The server is already listening for clients.");
            }
        }
        else if (message.equalsIgnoreCase("#getport"))
        {
            serverUI.display("Currently port: " + Integer.toString(getPort()));
        }
    }

    /**
     * This method overrides the one in the superclass.  Called
     * when the server starts listening for connections.
     */
    protected void serverStarted()
    {
        System.out.println
                ("Server listening for connections on port " + getPort());
    }

    /**
     * This method overrides the one in the superclass.  Called
     * when the server stops listening for connections.
     */
    protected void serverStopped()
    {
        System.out.println
                ("Server has stopped listening for connections.");
    }

    /**
     * Run when new clients are connected. Implemented by Benjamin Bergman,
     * Oct 22, 2009.
     *
     * @param client the connection connected to the client
     */
    protected void clientConnected(ConnectionToClient client)
    {
        // display on server and clients that the client has connected.
        String msg = "A Client has connected";
        System.out.println(msg);
        this.sendToAllClients(msg);
    }

    /**
     * Run when clients disconnect. Implemented by Benjamin Bergman,
     * Oct 22, 2009
     *
     * @param client the connection with the client
     */
    synchronized protected void clientDisconnected(
            ConnectionToClient client)
    {
        // display on server and clients when a user disconnects
        String msg = client.getInfo("loginID").toString() + " has disconnected";

        System.out.println(msg);
        this.sendToAllClients(msg);
    }

    /**
     * Run when a client suddenly disconnects. Implemented by Benjamin
     * Bergman, Oct 22, 2009
     *
     * @param client the client that raised the exception
     * @param exception the exception thrown
     */
    synchronized protected void clientException(
            ConnectionToClient client, Throwable exception)
    {
        String msg = client.getInfo("loginID").toString() + " has disconnected";

        System.out.println(msg);
        this.sendToAllClients(msg);
    }

    /**
     * This method terminates the server.
     */
    public void quit()
    {
        try
        {
            close();
        }
        catch(IOException e)
        {
        }
        System.exit(0);
    }


    //Class methods ***************************************************

    /**
     * This method is responsible for the creation of
     * the server instance (there is no UI in this phase).
     *
     * @param args The port number to listen on. Defaults to 5555
     *          if no argument is entered.
     */
    public static void main(String[] args)
    {
        int port = 0; //Port to listen on

        try
        {
            port = Integer.parseInt(args[0]); //Get port from command line
        }
        catch(Throwable t)
        {
            port = DEFAULT_PORT; //Set port to 5555
        }

        EchoServer sv = new EchoServer(port);

        try
        {
            sv.listen(); //Start listening for connections
        }
        catch (Exception ex)
        {
            System.out.println("ERROR - Could not listen for clients!");
        }
    }
}
//End of EchoServer class
