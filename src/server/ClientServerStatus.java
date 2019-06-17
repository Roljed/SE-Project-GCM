package server;

import java.io.Serializable;


/**
 *  Status about client connection with server.
 *  Received from the server when client tries to make connection.
 *
 * @version 1
 * @author Yaad Nahshon
 */
public enum ClientServerStatus implements Serializable
{
    CONNECTED,
    NOT_CONNECTED,
    NOT_EXIST,
    WRONG_USERNAME_OR_PASSWORD,
    WRONG_ARGUMENTS
}
