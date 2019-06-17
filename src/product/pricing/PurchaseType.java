package product.pricing;

import java.io.Serializable;

/**
 * 2 types of purchases available to Members by the company
 *
 * @version 1
 * @author Avi Ayeli
*/

public enum PurchaseType implements Serializable
{
	ONE_TIME_PURCHASE,
	SUBSCRIPTION
}