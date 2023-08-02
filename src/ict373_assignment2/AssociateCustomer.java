package ict373_assignment2;
/**
 * A Class representing an associate customer, which instantiates and processes all relevant information for an associate customer.
 * @author Kai Sweeting
 * 06/04/2022
 * AssociateCustomer.java
 * Input: Associate customer's personal and magazine relevant information.
 * Output: All associate customer details and their linked paying customers.
 */
public class AssociateCustomer extends PayingCustomer  implements java.io.Serializable {
    
    private String payee; // String containing the paying customer linked with specific associate customer.
    protected String customerStatus;
    
    /**
     * Default Class Constructor.
     */
    public AssociateCustomer()
    {
        payee = "";
    }
    
    /**
     * Class Constructor for an associate customer.
     * @param name A string containing the associate customers name.
     * @param email A string containing the associate customer's email address.
     * @param interest A string containing the associate customer's supplement interests/subscriptions.
     * @param address
     * @param payment A string containing the payment method used by linked paying customer.
     * @param cardNumber A string containing the card number used by linked paying customer.
     */
    public AssociateCustomer(String name, String email, String address, String interest, String payment, String cardNumber)
    {
        super(name, email, address, interest, payment, cardNumber);

    }
    
    /**
     * Overload Class Constructor for an associate customer.
     * @param name A string containing the associate customers name.
     * @param email A string containing the associate customer's email address.
     * @param interest A string containing the associate customer's supplement interest/subscription.
     * @param secondInterest A String containing the associate customer's second supplement interest/subscription.
     * @param address
     * @param payment A string containing the payment method used by linked paying customer.
     * @param cardNumber A string containing the card number used by linked paying customer.
     */    
    public AssociateCustomer(String name, String email, String address, String interest, String secondInterest, String payment, String cardNumber)
    {
        super(name, email, address, interest, payment, cardNumber);
        super.setCustomerInterests(secondInterest);

    }

    /**
     * Second Overload Class Constructor for an associate customer.
     * @param name A string containing the associate customers name.
     * @param email A string containing the associate customer's email address.
     * @param interest A string containing the associate customer's supplement interest/subscription.
     * @param secondInterest A String containing the associate customer's second supplement interest/subscription.
     * @param thirdInterest A string containing the associate customer's third supplement interest/subscription.
     * @param address
     * @param payment A string containing the payment method used by linked paying customer.
     * @param cardNumber A string containing the card number used by linked paying customer.
     */
    public AssociateCustomer(String name, String email, String address, String interest, String secondInterest, String thirdInterest, String payment, String cardNumber)
    {
        super(name, email, address, interest, payment, cardNumber);
        super.setCustomerInterests(thirdInterest);
    }
    
    /**
     * Gets the associate customer's linked paying customer.
     * @return A string containing a paying customer linked to specified associate customer.
     */
    public String getPayee()
    {
        return payee;
    }
    
    /**
     * Sets the paying customer to specified associate customer.
     * @param payee A string containing the name of a paying customer.
     */
    public void setPayee(String payee)
    {
        this.payee = payee;
    }
    
        /**
     * Gets the subscription status of the customer
     * @return 
     */
    public String getCustomerStatus()
    {
        customerStatus = "Associate Customer";
        return customerStatus;
    }
    
    

}
