package user;

import java.io.Serializable;

public enum Permission implements Serializable
{
    USER,
    MEMBER,
    WORKER,
    CONTENT_WORKER,
    COMPANY_MANAGER,
    CONTENT_MANAGER,
    EDITOR,
}
