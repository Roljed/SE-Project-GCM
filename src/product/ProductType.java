package product;

import java.io.Serializable;


/**
 * Only this products are produced and sold by this company
 *
 * @version 1
 * @author Yaad Nahshon
 */
public enum ProductType implements Serializable
{
    CONTENT,
    DIGITAL_MAP,
    CITY,
    TOUR
}
