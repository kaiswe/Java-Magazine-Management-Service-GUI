package ict373_assignment2;

import java.util.ArrayList;


public abstract class Customer  implements java.io.Serializable {
    
    private static final long serialVersionUID = 1L; // static String that stores a consistent serializable saved state for different magazines.
    protected String customerName; // String containing the customer's full name.
    protected String customerEmail; // String containing the customer's email.
    protected String customerAddress; // String containing the customer's address.
    protected Integer supplementAmount;  // Integer containing the amount of interested/subscribed supplements.
    protected ArrayList<String> customerInterests = new ArrayList<>(); // String ArrayList containing all customer supplement interests/subscriptions.
    
    /**
     * Default Customer Class Constructor.
     */
    public Customer()
    {
        customerName = "";
        customerEmail = "";
        customerAddress = "";
        supplementAmount = null;
    }
    
    /**
     * Customer Class Constructor.
     * @param name A string containing the full name of a customer.
     * @param email A string containing a customer's email.
     * @param interest A string containing a customer's supplement interest/subscription.
     * @param address
     */
    public Customer(String name, String email, String address, String interest)
    {
        this.customerName = name;
        this.customerEmail = email;
        this.customerAddress = address;
        this.customerInterests.add(interest); // All string interests added into ArrayList.

    }
    
    /**
     * Customer Class Overload Constructor, overloaded to include an extra possible customer interest.
     * @param name A string containing the full name of a customer.
     * @param email A string containing a customer's email.
     * @param interest A string containing a customer's supplement interest.
     * @param secondInterest A string containing a customer's possible second supplement interest.
     * @param address
     */
    public Customer(String name, String email, String address, String interest, String secondInterest)
    {
        this(name, email, address, interest);
        this.customerInterests.add(secondInterest);

    }    
    
    
    /**
     * Gets the name of the customer.
     * @return The name of the customer.
     */
    public String getName()
    {
        return customerName;
    }
    
    /**
     * Sets the name of the customer.
     * @param name A string containing the name of a customer.
     */
    public void setName(String name)
    {
        customerName = name;
    }
    
    /**
     * Gets the email address of the customer.
     * @return The email address of a customer.
     */
    public String getEmail()
    {
        return customerEmail;
    }
    
    /**
     * Sets the email address of the customer.
     * @param email A string containing the email address of a customer.
     */
    public void setEmail(String email)
    {
        customerEmail = email;
    }
    
    /**
     * Gets the amount of supplements a customer is interested/subscribed to.
     * @return The amount of supplement subscriptions for a customer.
     */
    public Integer getSupplementAmount()
    {
        return supplementAmount;
    }
    
    /**
     * Sets the amount of supplement interests/subscriptions for a customer.
     * @param suppAmount An integer containing the combined amount of supplements a customer is interested/subscribed to.
     */
    public void setSupplementAmount(Integer suppAmount)
    {
        supplementAmount = suppAmount;
    }
    
    /**
     * Gets the customer's supplement interests/subscriptions.
     * @return A String ArrayList containing a customer's interests/subscriptions.
     */
    public ArrayList<String> getCustomerInterests()
    {
        return customerInterests;
    }
    
    /**
     * Sets the customer's supplement interests/current subscriptions.
     * @param interests A string containing a customer's supplement interests/subscriptions.
     */
    public void setCustomerInterests(String interests)
    {
        customerInterests.clear();
        customerInterests.add(interests);
    }

    /**
     * Gets the customer's current address.
     */    
    public String getCustomerAddress()
    {
        return customerAddress;
    }
    
    /**
     * Sets the customer's address.
     * @param address A string containing a customer's address.
     */    
    public void setCustomerAddress(String address)
    {
        customerAddress = address;
    }
    
    
}
