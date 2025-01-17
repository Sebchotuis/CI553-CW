package catalogue;

import java.util.Collections;

/**
 * A BetterBasket class that extends Basket and prevents duplicate products.
 *
 * @author Your Name
 * @version 1.0
 */
public class BetterBasket extends Basket 
{
   

	@Override
    public boolean add( Product pr ) 
	{
       
        for (Product prInList: this) {
            if (prInList.getProductNum().equals(pr.getProductNum())) {
            	int quantity = pr.getQuantity()+prInList.getQuantity();
            	prInList.setQuantity(quantity);
                System.out.println("Product already in basket: " + pr.getDescription());
                return (true); 
               
            }
        }
       
         super.add(pr);
         Collections.sort(this);
         return (true); 
    }
}

