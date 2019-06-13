package server;

import java.io.Serializable;

public enum ClientServerStatus implements Serializable
{
    CONNECTED,
    NOT_CONNECTED,
    NOT_EXIST,
    WRONG_USERNAME_OR_PASSWORD,
    WRONG_ARGUMENTS
}
