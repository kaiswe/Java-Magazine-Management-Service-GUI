package ict373_assignment2;
import java.util.ArrayList;
import static ict373_assignment2.MagazineService.customers;
import static ict373_assignment2.MagazineService.magazine;


/**
 * Class representing a paying customer, which instantiates and processes all paying customer objects, their monthly subscription costs, and linked associate customer(s).
 * @author Kai Sweeting
 * 06/04/2022
 * PayingCustomer.java
 * Input: Paying customer's personal information, including name, email, supplement interests/subscriptions, payment method, and card number.
 * Input: A paying customer is only identified as one because they pay for an associate customers subscription, thus it is assumed that a paying customer has a valid payment method and card number.
 * Output: A paying customer's personal information stored in the system, with inclusion of their linked associate customer's, and the combined total amount they are being charged each month.
 */
public class PayingCustomer extends Customer  implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    protected String paymentMethod; //String containing the identified payment method
    protected Integer amountOfExtraSubs; //Integer containing linked associate customer magazine subscriptions
    protected String cardNumber; //String containing the card number of the registered payment method
    protected ArrayList<String> associateList = new ArrayList<>(); //ArrayList containing associate customers of paying customers
    protected ArrayList<String> associateInterests = new ArrayList<>(); //ArrayList containing the interests of linked associate customers
    
    protected static ArrayList<String> customerBillingInfo = new ArrayList<>(); //ArrayList containing the billing info for all the paying customers.
    protected String customerStatus; //String containing the current status of the customer.
    protected Double customerBill; //Double containing the customer's bill.
    
    
    /**
     * Default Class Constructor.
     */
    public PayingCustomer()
    {
        paymentMethod = "";
        amountOfExtraSubs = null;
        cardNumber = "";
        customerBill = null;
    }
    
    /**
     * Class Constructor representing a paying customer.
     * @param name A string containing the paying customer's name.
     * @param email A string containing the paying customer's email address.
     * @param interest A string containing a supplement interest/subscription for the paying customer.
     * @param payment A string containing the payment method for the paying customer.
     * @param cardNumber A string containing the card number for the paying customer.
     * @param address
     */
    public PayingCustomer(String name, String email, String address, String interest, String payment,  String cardNumber)
    {
        super(name, email, address, interest);
        paymentMethod = payment;
        this.cardNumber = cardNumber;
    }
    
    /**
     * Overload Class Constructor for a paying customer.
     * @param name A string containing the paying customer's name.
     * @param email A string containing the paying customer's email address.
     * @param interest A string containing a supplement interest/subscription for the paying customer.
     * @param secondInterest A string containing a second supplement interest/subscription for the paying customer.
     * @param payment A string containing the payment method for the paying customer.
     * @param cardNumber A string containing the card number for the paying customer.
     * @param address
     */
    public PayingCustomer(String name, String email, String address, String interest, String secondInterest, String payment,  String cardNumber)
    {
        this(name, email, address, interest, payment, cardNumber); 
        super.setCustomerInterests(secondInterest);
        
    }    
    
    
    /**
     * Second Overload Class Constructor for a paying customer.
     * @param name A string containing the paying customer's name.
     * @param email A string containing the paying customer's email address.
     * @param interest A string containing a supplement interest/subscription for the paying customer.
     * @param secondInterest A string containing a second supplement interest/subscription for the paying customer.
     * @param thirdInterest A string containing a third supplement interest/subscription for the paying customer.
     * @param payment A string containing the payment method for the paying customer.
     * @param cardNumber A string containing the card number for the paying customer.
     * @param address
     */
    public PayingCustomer(String name, String email, String address, String interest, String secondInterest, String thirdInterest, String payment,  String cardNumber)
    {
        this(name, email, address, interest, secondInterest, payment, cardNumber);
        super.setCustomerInterests(thirdInterest);
        
    }
    
    /**
     * Calculates paying customer's monthly subscription fee.
     * Inclusion within paying customer's supplement amount and combined supplement pricing is that of their Linked associate customer(s) too.
     * @param monthly A double containing the magazine's baseline monthly subscription price.
     * @param suppAmount An integer containing the amount of supplements the paying customer is paying for.
     * @param amountOfSubs An integer containing the amount of baseline magazine subscriptions for the paying customer.
     * @param name A string containing the paying customer's full name.
     * @param combinedSupplements A double containing the combined price of paying customers supplement subscriptions.
     * @return the total, combined cost of each supplement and magazine subscription a paying customer is billed each month.
     */
    protected static Double calculateCustomerPayment(Double monthly, Integer suppAmount, Integer amountOfSubs, String name, Double combinedSupplements)
    {
        for(int i = 0; i < customers.size(); i++) // Iterate through customers ArrayList
        {
            /*
             * If a customer at the specified index is of the PayingCustomer class,
             * and their name equals the same name passed into the method on method call.
             */
            if(customers.get(i).getClass() == PayingCustomer.class && customers.get(i).getName().equals(name))
            {
                if(customers.get(i).getCustomerInterests().contains(magazine.get(0).getSupplementTitle())) // If paying customer is subscribed to a sports supplement,
                {
                    combinedSupplements += calculateSportsPayment(name); // Add calculated total cost of sports supplements and current combined supplements, and store within Double variable.
                }
                if(customers.get(i).getCustomerInterests().contains(magazine.get(1).getSupplementTitle())) // If paying customer is subscribed to a technology supplement,
                {
                    combinedSupplements += calculateTechPayment(name); // Add calculated total cost of technology supplements and current combined supplements, and store within Double variable.
                }
                
                if(customers.get(i).getCustomerInterests().contains(magazine.get(2).getSupplementTitle())) // If paying customer is subscribed to a cooking supplement,
                {
                    combinedSupplements += calculateCookingPayment(name); // Add calculated total cost of cooking supplements and current combined supplements, and store within Double variable.
                }
                
                if(customers.get(i).getCustomerInterests().contains(magazine.get(3).getSupplementTitle())) // If paying customer is subscribed to a traveling supplement,
                {
                    combinedSupplements += calculateTravelingPayment(name);  // Add calculated total cost of traveling supplements and current combined supplements, and store within Double variable.
                }
            }
            
        }
        /*
         * Multiply the monthly cost of the baseline magazine subscription by the amount of magazine subscriptions to paying customer is billed,
         * add resulting number to the calculated price of combined associate and paying customer supplement subscriptions,
         * return the total monthly subscription cost.
         */
        Double totalCost = (monthly * amountOfSubs) + combinedSupplements;
        return totalCost;
        
    }
    
    /**
     * Calculates the total cost of a paying customer, and their relative associate customer's, sport supplement subscriptions.
     * @param name A string containing the name of the paying customer.
     * @return a Double variable containing a combined total cost for paying customer's monthly sports supplement subscriptions.
     */
    protected static Double calculateSportsPayment(String name)
    {
        int sportsCounter = 0; // Initialize a counter variable
        
        Double combinedSubs = 0.0; // Initialize a double variable to hold combined total sports supplement subscriptions cost.
        
        for(int i = 0; i < customers.size(); i++) // Iterate through customer ArrayList
        {
            if(customers.get(i).getClass() == PayingCustomer.class && customers.get(i).getName().equals(name)) // If customer is of PayingCustomer class, and their name equals that of passed name variable on method initialization,
            {
                if(customers.get(i).getCustomerInterests().contains(magazine.get(0).getSupplementTitle())) // If paying customer is subscribed to the sports supplement,
                {
                    combinedSubs += MagazineService.sportsPrice; //Add sports supplement sub price with current combined supplements total, and store into Double variable.
                    sportsCounter++; // Increment counter.
                    
                    if(((PayingCustomer)customers.get(i)).getLinkedAssociatesSupplements().contains(magazine.get(0).getSupplementTitle())) // If paying customer's linked associate(s) are subscribed to the sports supplement,
                    {
                        combinedSubs += MagazineService.sportsPrice; // Add sports supplement sub price with current combined supplements total, and store into Double variable.
                        sportsCounter++; // Increment counter.
                    }
                    /**
                     * Output breakdown of paying customer subscriptions for sports supplement,
                     * counter represents the total amount of sports supplement subscriptions,
                     * ending with the combined total cost.
                     */
                    String supplementBill = sportsCounter + "x " + magazine.get(0).getSupplementTitle() + " Supplement subscriptions " + " Totaling: $" + combinedSubs;
                    customerBillingInfo.add(supplementBill);
                }
            }
        }
        return combinedSubs;
    }
    
    
    /**
     * Calculates the total cost of a paying customer, and their relative associate customer's, technology supplement subscriptions.
     * @param name A string containing the name of the paying customer.
     * @return a Double variable containing a combined total cost for paying customer's monthly technology supplement subscriptions.
     */
    private static Double calculateTechPayment(String name)
    {
        int techCounter = 0; // Initialize a counter variable.
        Double combinedSubs = 0.0; // Initialize a double variable to hold combined total technology supplement subscriptions cost.
        for(int i = 0; i < customers.size(); i++) // Iterate through customer ArrayList
        {
            if(customers.get(i).getClass() == PayingCustomer.class && customers.get(i).getName().equals(name)) // If customer is of PayingCustomer class, and their name equals that of passed name variable on method initialization,
            {
                if(customers.get(i).getCustomerInterests().contains(magazine.get(1).getSupplementTitle())) // If paying customer is subscribed to the technology supplement,
                {
                    combinedSubs += MagazineService.techPrice; // Add technology supplement sub price into Double variable.
                    techCounter++; // Increment counter.
                    
                    if(((PayingCustomer)customers.get(i)).getLinkedAssociatesSupplements().contains(magazine.get(1).getSupplementTitle())) // If paying customer's linked associate(s) are subscribed to the technology supplement,
                    {
                        combinedSubs += MagazineService.techPrice; // Add technology supplement sub price into Double variable.
                        techCounter++; // Increment counter.
                    }
                    
                    /*
                     * Output breakdown of paying customer subscriptions for technology supplements,
                     * counter represents the total amount of technology supplement subscriptions,
                     * ending statement with the combined total cost.
                     */
                    String supplementBill = techCounter + "x " + magazine.get(1).getSupplementTitle() + " Supplement subscriptions " + " Totaling: $" + combinedSubs;
                    customerBillingInfo.add(supplementBill);

                }
            }
        }
        return combinedSubs;
    }
    
    
    /**
     * Calculates the total cost of a paying customer, and their relative associate customer's, cooking supplement subscriptions.
     * @param name A string containing the name of the paying customer.
     * @return  a Double variable containing a combined total cost for paying customer's monthly cooking supplement subscriptions.
     */
    private static Double calculateCookingPayment(String name)
    {
        int cookingCounter = 0; // Initialize a counter variable.
        Double combinedSubs = 0.0; // Initialize a double variable to hold combined total cooking supplement subscriptions cost.
        for(int i = 0; i < customers.size(); i++) // Iterate through customer ArrayList
        {
            if(customers.get(i).getClass() == PayingCustomer.class && customers.get(i).getName().equals(name)) // If customer is of PayingCustomer class, and their name equals that of passed name variable on method initialization,
            {
                if(customers.get(i).getCustomerInterests().contains(magazine.get(2).getSupplementTitle())) // If paying customer is subscribed to the cooking supplement,
                {
                    combinedSubs += MagazineService.cookingPrice; // Add cooking supplement sub price into Double variable.
                    cookingCounter++;// Increment counter.
                    
                    if(((PayingCustomer)customers.get(i)).getLinkedAssociatesSupplements().contains(magazine.get(2).getSupplementTitle())) // If paying customer's linked associate(s) are subscribed to the cooking supplement,
                    {
                        combinedSubs += MagazineService.cookingPrice; // Add cooking supplement sub price into Double variable.
                        cookingCounter++;// Increment counter.
                    }
                    
                    /*
                     * Output breakdown of paying customer subscriptions for cooking supplements,
                     * counter represents the total amount of cooking supplement subscriptions,
                     * ending statement with the combined total cost.
                     */
                    String supplementBill = cookingCounter + "x " + magazine.get(2).getSupplementTitle() + " Supplement subscriptions " + " Totaling: $" + combinedSubs;
                    customerBillingInfo.add(supplementBill);
                }
            }
        }
        return combinedSubs;
    }
    
    
    /**
     * Calculates the total cost of a paying customer, and their relative associate customer's, traveling supplement subscriptions.
     * @param name A string containing the name of the paying customer.
     * @return a Double variable containing a combined total cost for paying customer's monthly traveling supplement subscriptions.
     */
    private static Double calculateTravelingPayment(String name)
    {
        int travelingCounter = 0; // Initialize a counter variable.
        Double combinedSubs = 0.0; // Initialize a double variable to hold combined total traveling supplement subscriptions cost.
        for(int i = 0; i < customers.size(); i++) // Iterate through customer ArrayList
        {
            if(customers.get(i).getClass() == PayingCustomer.class && customers.get(i).getName().equals(name))  // If customer is of PayingCustomer class, and their name equals that of passed name variable on method initialization,
            {
                if(customers.get(i).getCustomerInterests().contains(magazine.get(3).getSupplementTitle())) // If paying customer is subscribed to the traveling supplement,
                {
                    combinedSubs += MagazineService.travelingPrice; // Add traveling supplement sub price into Double variable.
                    travelingCounter++; // Increment counter.
                    
                    if(((PayingCustomer)customers.get(i)).getLinkedAssociatesSupplements().contains(magazine.get(3).getSupplementTitle()))
                    {
                        combinedSubs += MagazineService.travelingPrice; // Add traveling supplement sub price into Double variable.
                        travelingCounter++; // Increment counter.
                    }
                    
                    /*
                     * Output breakdown of paying customer subscriptions for traveling supplements,
                     * counter represents the total amount of traveling supplement subscriptions,
                     * ending statement with the combined total cost.
                     */
                    String supplementBill = travelingCounter + "x " + magazine.get(3).getSupplementTitle() + " Supplement subscriptions " + " Totaling: $" + combinedSubs;
                    customerBillingInfo.add(supplementBill);
                }
            }
        }
        return combinedSubs;
    }    
    

    
    /**
     * Gets payment method of paying customer.
     * @return A string representing the payment method used by the paying customer for themselves and their associate customer(s).
     */
    public String getPaymentMethod()
    {
        return paymentMethod;
    }
    
    
    /**
     * Sets the paying customer's payment method.
     * @param payment the payment method used by the paying customer for themselves and their associate customer(s).
     */
    public void setPaymentMethod(String payment)
    {
        this.paymentMethod = payment;
    }
    
    /**
     * Gets the paying customer's linked associate customer(s) baseline magazine subscriptions.
     * @return An integer representing the amount of extra baseline magazine subscriptions being added onto the paying customer's bill.
     */
    public Integer getCustomerSubscriptions()
    {
        return amountOfExtraSubs;
    }
    
    /**
     * Sets the amount of extra baseline magazine subscriptions being added onto the paying customer's bill.
     * @param subs a total of each paying customer's linked associate baseline magazine subscriptions.
     */
    public void setCustomerSubscriptions(Integer subs)
    {
        this.amountOfExtraSubs = subs;
    }
    
    /**
     * Gets a list of associate customers linked with paying customers.
     * @return A String ArrayList of associate customer names.
     */
    public ArrayList<String> getCustomersLinkedAssociates()
    {
        return associateList;
    }
    
    /**
     * Sets the supplement subscriptions of associate customers.
     * @param interests A string ArrayList containing all the supplement subscriptions of linked associate customers.
     */
    public void setLinkedAssociatesSupplements(ArrayList<String> interests)
    {
        this.associateInterests = interests;
    }
    
    /**
     * Gets the supplement subscriptions of linked associate customers.
     * @return A string ArrayList of associate customers supplement subscriptions.
     */
    public ArrayList<String> getLinkedAssociatesSupplements()
    {
        return associateInterests;
    }
    
    /**
     * Sets the connection between the associate and paying customer.
     * @param linkedCust A string containing a linked associate customer.
     */
    public void setCustomersLinkedAssociates(String linkedCust)
    {
        associateList.clear();
        associateList.add(linkedCust);
    }
    
    /**
     * Gets the paying customers card number.
     * @return A string containing the paying customer's card number.
     */
    public String getCustomerCardNumber()
    {
        return cardNumber;
    }
    
    /**
     * Sets the paying customers card number.
     * @param cardNum A string containing the card number for a paying customer.
     */
    public void setCustomerCardNumber(String cardNum)
    {
        this.cardNumber = cardNum;
    }
    
    /**
     * Gets the subscription status of the customer
     * @return 
     */
    public String getCustomerStatus()
    {
        customerStatus = "Paying Customer";
        return customerStatus;
    }
    
    /**
     * Sets the customer's monthly total bill.
     * @param cost A double containing the total monthly cost.
     */
    public void setCustomerMonthlyBill(Double cost)
    {
        customerBill = cost;
    }
    
    /**
     * Gets the monthly bill of the customer.
     * @return 
     */    
    public Double getCustomerMonthlyBill()
    {
        return customerBill;
    }
}
