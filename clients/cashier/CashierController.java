package clients.cashier;


/**
 * The Cashier Controller
 */

public class CashierController
{
  private CashierModel model = null;
  private CashierView  view  = null;

  /**
   * Constructor
   * @param model The model 
   * @param view  The view from which the interaction came
   */
  public CashierController( CashierModel model, CashierView view )
  {
    this.view  = view;
    this.model = model;
  }

  /**
   * Check interaction from view
   * @param pn The product number to be checked
   */
  public void doCheck( String pn) {
	  if (pn == null || pn.isEmpty()) {
	        System.out.println("Error: Product number is empty or null!");
	        return;
  }  
	System.out.println("Checking product with number: " + pn);
    model.doCheck(pn);
  }

   /**
   * Buy interaction from view
   */
  public void doBuy() {
	    System.out.println("Attempting to buy the selected product...");
    model.doBuy();
  }
  
   /**
   * Bought interaction from view
   */
  public void doBought()
  {
    model.doBought();
  }
}
