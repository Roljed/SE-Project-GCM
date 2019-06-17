package user;

import java.io.Serializable;

/**
 * User access permission to company's App capabilities
 *
 * @version 1
 * @author Yaad Nahshon
 *
 */
public enum Permission implements Serializable
{
    USER,
    MEMBER,
    WORKER,
    CONTENT_WORKER,
    COMPANY_MANAGER,
    CONTENT_MANAGER,
}
