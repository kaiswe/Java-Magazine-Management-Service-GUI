 package ict373_assignment2;

import static ict373_assignment2.FXMLMagazineEditController.supplementStage;
import java.util.ArrayList;
import java.io.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.*;

/**
 * MagazineService class that handles data creation, management, and storage for each magazine service.
 * 07/06/2022
 * @author Kai Sweeting
 */
public class MagazineService implements java.io.Serializable {
    
    private static final long serialVersionUID = 1L;
    
    protected static String magazineTitle; // String containing the MagazineService Name.
    protected static Double magazinePrice = 24.99; // Double containing the Monthly Subscription cost.
    protected Double supplementPrice; // Double containing specified Supplement Price.
    protected String supplementNews; // String containing the news section of specified Supplement.
    protected String supplementDeals; // String containing the deals section of specified Supplement.
    protected String supplementTitle; // String containing the title of the specified Supplement.
    
    protected static Double sportsPrice; // Double containing sports supplement subscription price.
    protected static Double techPrice; // Double containing technology supplement subscription price.
    protected static Double cookingPrice; // Double containing cooking supplement subscription price.
    protected static Double travelingPrice; // Double containing traveling supplement subscription price.
    
    
    protected static ArrayList<Double> supplementPrices = new ArrayList<>(); // ArrayList Double storage for all SupplementPrices.
    protected static ArrayList<String> sportsCustomers = new ArrayList<>(); // ArrayList String storage for all sports customer subscribers.
    protected static ArrayList<String> techCustomers = new ArrayList<>(); // ArrayList String storage for all technology customer subscribers.
    protected static ArrayList<String> cookingCustomers = new ArrayList<>(); // ArrayList String storage for all cooking customer subscribers.
    protected static ArrayList<String> travelingCustomers = new ArrayList<>(); // ArrayList String storage for all traveling customer subscribers.
    
    protected static ArrayList<Customer> deletedCustomers = new ArrayList<>(); // ArrayList String storage for all deleted customers.

    
    static ArrayList<Customer> customers = new ArrayList<>(); // ArrayList containing all customer's and their relevent information.
    static ArrayList<MagazineService> magazine = new ArrayList<>(); // ArrayList containing all available magazine supplements.
    
    static MagazineService sports; // MagazineService variable storing sports supplements
    static MagazineService tech; // MagazineService variable storing technology supplements
    static MagazineService cooking; // MagazineService variable storing cooking supplements
    static MagazineService traveling; // MagazineService variable storing traveling supplements
    
    static Stage customerStage; // GUI Stage representing the customer editing system
    
       

    /**
     * Default Magazine Class Constructor.
     */
    public MagazineService()
    {
    }
    
    /**
     * Saves the inputted magazine data into a new or existing file.
     */
    protected static void saveMagazineData()
    {
      FileChooser fc = new FileChooser(); //initialize file chooser variable
      fc.setInitialDirectory(new File("Magazines/")); //set the initial directory as a relative path within this project
      File file = fc.showSaveDialog(new Stage()); //create a new file on save state
      if(file != null){ //if the file is not empty
        try {
            FileOutputStream fileOut =
            new FileOutputStream(file); //create new file output stream
            ObjectOutputStream out = new ObjectOutputStream(fileOut); //set object outputs into file
            out.writeObject(customers); //write the customers arraylist data to file
            out.writeObject(magazine); //write the magazine arraylist data to file
            out.flush(); //flush all loose data
            out.close(); //close the object stream
            fileOut.close(); //close the file stream
                } catch (IOException x) {
                        System.out.println(x + " Error saving the Magazine Data to file.");
                }
        }
    }
    
    /**
     * Loads the selected saved magazine file data.
     */
    protected static void loadMagazineData()
    {
        FileChooser fc = new FileChooser(); //initialize new filechooser variable
        fc.setInitialDirectory(new File("Magazines/")); //set initial opening directory to a relative path within this project
        File file = fc.showOpenDialog(new Stage()); //show directory as new stage
        
        if(file != null) //if the file is not null
        {
             try {
                FileInputStream fileIn = new FileInputStream(file); //initialize new file input variable
                ObjectInputStream in = new ObjectInputStream(fileIn); //initialize new object input variable
                customers = (ArrayList<Customer>) in.readObject(); //read customer arraylist objects from file into customers arraylist
                magazine = (ArrayList<MagazineService>) in.readObject(); //read magazine arraylist objects from file into magazine arraylist
                in.close(); //close the object input stream
                fileIn.close(); //close the file input stream
                } catch (IOException | ClassNotFoundException x) {
                        System.out.println(x + " Error loading the saved Magazine Data.");
                }
        }
    }
    
    
    
    
    /**
     * Stores magazine title.
     * @param title
     */
    protected void setMagazineTitle(String title)
    {
        magazineTitle = title;
    }
    
    
    /**
     * Stores magazine's subscription price.
     * @param price
     */    
    protected void setMagazinePricing(Double price)
    {
        magazinePrice = price;
    }
    
    /**
     * Gets the magazine's title.
     * @return A string representing the magazine's title.
     */    
    protected static String getMagazineTitle()
    {
        return magazineTitle;
    }

    /**
     * Gets the magazine's subscription price.
     * @return A double representing the magazine's subscription price.
     */    
    protected Double getMagazinePricing()
    {
        return magazinePrice;
    }
    
    /**
     * Gets the customers ArrayList that are subscribed to the sports supplement.
     * @return returns the sportsCustomers ArrayList
     */
    protected ArrayList<String> getSportsCustomers()
    {
        return sportsCustomers;
    }
    
    /**
     * Gets the customers ArrayList that are subscribed to the technology supplement.
     * @return the techCustomers ArrayList
     */
    protected ArrayList<String> getTechCustomers()
    {
        return techCustomers;
    }
    
    /**
     * Gets the customers ArrayList that are subscribed to the cooking supplement.
     * @return the cookingCustomers ArrayList
     */
    protected ArrayList<String> getCookingCustomers()
    {
        return cookingCustomers;
    }
    
    /**
     * Gets the customers ArrayList that are subscribed to the traveling supplement.
     * @return the travelingCustomers ArrayList
     */   
    protected ArrayList<String> getTravelingCustomers()
    {
        return travelingCustomers;
    }
    
    
    /**
     * Identifies and links the customers subscribed to specific supplements.
     */
    protected void linkCustomerSupplements()
    {
       for(int i = 0; i < customers.size(); i++) //loop the customers arraylist
       {
           if(customers.get(i).getCustomerInterests().contains(magazine.get(0).getSupplementTitle())) //if customer's interests contains a sports supplement
           {
               sportsCustomers.add(customers.get(i).getName()); //add customer to subscribed customers arraylist
           }
           if(customers.get(i).getCustomerInterests().contains(magazine.get(1).getSupplementTitle())) //if customer's interests contains a technology supplement
           {
               techCustomers.add(customers.get(i).getName()); //add customer to subscribed customers arraylist
           }
           if(customers.get(i).getCustomerInterests().contains(magazine.get(2).getSupplementTitle())) //if customer's interests contains a cooking supplement
           {
               cookingCustomers.add(customers.get(i).getName()); //add customer to subscribed customers arraylist
           }
           if(customers.get(i).getCustomerInterests().contains(magazine.get(3).getSupplementTitle())) //if customer's interests contains a traveling supplement
           {
               travelingCustomers.add(customers.get(i).getName()); //add customer to subscribed customers arraylist
           }          
       }
    }
    
    /**
     * Creates the billing history of each paying customer.
     */
    protected void createBillingHistory()
    {
        for(int i = 0; i < customers.size(); i++) // Iterate through customers ArrayList to select correct customers
        {
            String name = customers.get(i).getName(); // Store customer's name into a new String
            Integer suppSubs = customers.get(i).getSupplementAmount(); // Store customer's amount of supplement subs
            if(customers.get(i).getClass() == PayingCustomer.class) // If customer is of the PayingCustomer class
            {
                int customerSubs = ((PayingCustomer)customers.get(i)).getCustomerSubscriptions(); // Get paying customer total magazine subscriptions 
                Double combinedSupplements = 0.0; // Initialize Double variable to store combined Supplement amount 
                Double totalCost = PayingCustomer.calculateCustomerPayment(magazinePrice, suppSubs, customerSubs, name, combinedSupplements);
                ((PayingCustomer)customers.get(i)).setCustomerMonthlyBill(totalCost);
            }
        }
    }
    
    
    /**
     * Gets supplement's monthly subscription prices.
     */    
    protected void createSupplementPriceList()
    {
        for(int i = 0; i < magazine.size(); i++) // Iterate through magazine ArrayList of supplements
        {
            if(magazine.get(i).getClass() == SportsSupplement.class) // If supplement at this index within the magazine ArrayList is a Sports Supplement
            {
                sportsPrice = magazine.get(i).getSupplementPrice(); // Store supplement price into variable
                supplementPrices.add(sportsPrice); // Add variable into ArrayList of magazine supplement prices
            }
            if(magazine.get(i).getClass() == TechnologySupplement.class) // If supplement at this index  within the magazine ArrayList is a Technology Supplement
            {
                techPrice = magazine.get(i).getSupplementPrice(); // Store supplement price into variable
                supplementPrices.add(techPrice); // Add variable into ArrayList of magazine 
            }
            if(magazine.get(i).getClass() == CookingSupplement.class) // If supplement at this index  within the magazine ArrayList is a Cooking Supplement
            {
                cookingPrice = magazine.get(i).getSupplementPrice(); // Store supplement price into variable
                supplementPrices.add(cookingPrice); // Add variable into ArrayList of magazine                
            }
            if(magazine.get(i).getClass() == TravelingSupplement.class) // If supplement at this index  within the magazine ArrayList is a Traveling Supplement
            {
                travelingPrice = magazine.get(i).getSupplementPrice(); // Store supplement price into variable
                supplementPrices.add(travelingPrice); // Add variable into ArrayList of magazine 
            }            
        }

    }
    
    /**
     * Gets the title of the magazine supplement.
     * @return The title of the supplement.
     */    
    protected String getSupplementTitle()
    {
        return supplementTitle;
    }
    
    /**
     * Sets the title of the magazine supplement.
     * @param title the name of the magazine supplement.
     */   
    protected void setSupplementTitle(String title)
    {
        this.supplementTitle = title;
    }
    
    /**
     * Gets the price of the magazine supplement.
     * @return The price of the magazine supplement.
     */
    protected Double getSupplementPrice()
    {
        return supplementPrice;
    }
    
    /**
     * Sets the price of the magazine supplement.
     * @param price the price of the magazine supplement.
     */
    protected void setSupplementPrice(Double price)
    {
        this.supplementPrice = price;
    }
    
    /**
     * Gets the news segment of the magazine supplement.
     * @return The news segment of the magazine supplement.
     */
    protected String getSupplementNews()
    {
        return supplementNews;
    }
    
    /**
     * Sets what the news is for the magazine supplement.
     * @param news the news of the magazine supplement.
     */
    protected void setSupplementNews(String news)
    {
        this.supplementNews = news;
    }
    
    /**
     * Gets the deals segment of the magazine supplement.
     * @return The deals segment of the magazine supplement.
     */    
    protected String getSupplementDeals()
    {
        return supplementDeals;
    }
    
    /**
     * Sets what the deals are for the magazine supplement.
     * @param deals the deals involved with the topic of this magazine supplement.
     */
    protected void setSupplementDeals(String deals)
    {
        this.supplementDeals = deals;
    }
    
    
    /**
     * Links associate customers with their paying customer.
     */
    public void linkAssociateCustomers()
    {
        int counter = 1; // Initialize integer counter variable.
        
        int payingCustomerSupps; // Initialize integer variable to store paying customer's subscribed supplements.
        
        int associateCustomerSupps; // Initialize integer variable to store associate customer's subscribed supplements.
        
        String payingCustomerPaymentMethod; // Initialize string variable to store paying customer's payment method.
        
        for(int i = 0; i < customers.size(); i ++) // Iterate through ArrayList of customers.
        {
            if(customers.get(i).getClass() == PayingCustomer.class) // If customer at specific index is a paying customer,
            {
                for(int j = i + 1; j < customers.size(); j ++) // Iterate through inner loop that is one ahead of outer loop, so elements within the same ArrayList can be compared. Where inner loop represents associate customer identification.
                {
                    if(((PayingCustomer)customers.get(i)).getCustomerCardNumber().matches((((PayingCustomer)customers.get(j))).getCustomerCardNumber())) // If a paying customer's card number matches an associates(technically a paying customer) card number,
                    {
                        
                        
                        counter++; // Increment counter.

                        ((PayingCustomer)customers.get(i)).setCustomerSubscriptions(counter); // Set the amount of baseline subscriptions for paying customer based on the counter that increments on every new linked associate customer identification.
                        ((PayingCustomer)customers.get(i)).setLinkedAssociatesSupplements(((PayingCustomer)customers.get(j)).getCustomerInterests()); // Set the linked associate customers interests,
                        
                        payingCustomerSupps = ((PayingCustomer)customers.get(i)).getSupplementAmount(); // Get the amount of supplements the paying customer is subscribed to,
                        payingCustomerPaymentMethod = ((PayingCustomer)customers.get(i)).getPaymentMethod(); // Get the payment method of the paying customer,
                        associateCustomerSupps = ((AssociateCustomer)customers.get(j)).getSupplementAmount(); // Get the associate customers supplmenet subscriptions.
                        
                        ((PayingCustomer)customers.get(i)).setSupplementAmount(payingCustomerSupps + associateCustomerSupps); // Set the paying customers supplement amount to equal a combined total of both the linked associate's supplements and theirs.
                        ((PayingCustomer)customers.get(i)).setCustomersLinkedAssociates(customers.get(j).getName()); // Get the name of the identified linked associate, and set it as the paying customers linked associate.
                        ((AssociateCustomer)customers.get(j)).setPayee(customers.get(i).getName()); // Set the payee of the associate customer,
                        ((AssociateCustomer)customers.get(j)).setPaymentMethod(payingCustomerPaymentMethod); // Set the payment method of the associate, to that of their linked paying customer.
                        
                    }
                    
                }
                counter = 1; // Set counter to equal one again outside loop, since it represents baseline magazine subscription, it will always be 1 on start.
            }
        }
    }
    
    /**
     * Calculates combined total of interested/subscribed supplements.
     */
    protected void addSupplementAmount()
    {
        int counter = 0; // Initialze counter integer variable.
            for(int i = 0; i < customers.size(); i++) // Iterate through customer ArrayList
        {
            for(String interest : customers.get(i).getCustomerInterests()) // For every string containing a customer's supplement interests/subscriptions, for every customer within the ArrayList,
            {
                    counter++; // Increment the counter.
                customers.get(i).setSupplementAmount(counter);
                // Set the counter to equal the amount of supplements said customer is interested/subscribed to.
            }
            counter=0; // Reset the counter on loop exit, ready to determine next customer's supplement amount.
        }
        
    }
    
    /**
     * Changes the GUI stage view to the customer editing system.
     */
    protected  void editMagazineCustomers()
    {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLMagazineEdit.fxml"));
            customerStage = new Stage();
            Scene scene = new Scene(root);
            customerStage.setResizable(false);
            customerStage.setTitle("Magazine Editing System");
            customerStage.setScene(scene);
            customerStage.show();
        } catch (IOException ex) {
            
        }

    }
    
    /**
     * Changes the view to the supplement editing system.
     */
    protected void editMagazineSupplements()
    {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLSupplementEdit.fxml"));
            supplementStage = new Stage();
            Scene scene = new Scene(root);
            supplementStage.setResizable(false);
            supplementStage.setTitle("Magazine Editing System");
            supplementStage.setScene(scene);
            supplementStage.show();
        } catch (IOException ex) {
            
        }

    }

}
