package ict373_assignment2;
import static ict373_assignment2.MagazineService.customers;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import static ict373_assignment2.MagazineService.magazine;
import static ict373_assignment2.PayingCustomer.customerBillingInfo;


/**
 * Main controller class that handles the business logic of the main GUI
 * 07/06/2022
 * @author Kai Sweeting
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    protected ListView<String> magazineSupplements; //ListView for displaying all the magazines supplements
    @FXML
    protected ListView<String> magazineCustomers; //Listview for displaying all the magazines customers
    @FXML
    protected ListView<String> magazineInfoPanel; //Listview for dispalying the selected magazines supplement or customer's stored details
    
    MagazineService ms = new MagazineService();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    /**
     * Loads the selected magazine into the GUI.
     */
    @FXML
    protected void loadMagazine()
    {
        magazineInfoPanel.getItems().clear(); 
        magazineCustomers.getItems().clear();
        magazineSupplements.getItems().clear();
        
        MagazineService.loadMagazineData(); //Load the selected magazine data
        ms.addSupplementAmount(); 
        ms.linkAssociateCustomers();
        ms.linkCustomerSupplements();
        ms.createSupplementPriceList();
        ms.createBillingHistory();
        
        for(int i=0; i< magazine.size(); i++){ //loop the size of the magazine arraylist
            
        magazineSupplements.getItems().add(magazine.get(i).getSupplementTitle()); //display all the magazines supplements into the GUI

        }
        
        for(int i=0; i< customers.size(); i++){ //loop the size of the customers arraylist
            
        magazineCustomers.getItems().add(customers.get(i).getName()); //display all the magazines customers into the GUI
        }
    }
    
    
    /**
     * Displays all selected magazine supplement or customer details.
     */
    @FXML
    private void viewAllMagazineDetails() {
        magazineInfoPanel.getItems().clear(); //clear listbox of previously stored items
    try{
        if(magazineSupplements.getSelectionModel().getSelectedItem() == null){ //if a supplement is not selected
        for(int i=0; i<customers.size(); i++){ //loop through customers arraylist
            
            if(magazineCustomers.getSelectionModel().getSelectedItem().equals(customers.get(i).getName())){ //if a customer is selected equals a name in the arraylist
                magazineInfoPanel.getItems().add("Name: " + customers.get(i).getName()); 
                magazineInfoPanel.getItems().add("Email: " + customers.get(i).getEmail());
                magazineInfoPanel.getItems().add("Supplement Interests: " + customers.get(i).getCustomerInterests().toString());
                magazineInfoPanel.getItems().add("Amount of Supplement Subscriptions: " + customers.get(i).getSupplementAmount());
                magazineInfoPanel.getItems().add("Address: " + customers.get(i).getCustomerAddress());
            if(customers.get(i).getClass().equals(PayingCustomer.class)) //if the selected customer is a paying customer
                {
                    magazineInfoPanel.getItems().add("Payment Method: " + ((PayingCustomer)customers.get(i)).getPaymentMethod());
                    magazineInfoPanel.getItems().add("Magazine Subscriptions: " + ((PayingCustomer)customers.get(i)).getCustomerSubscriptions());
                    magazineInfoPanel.getItems().add("Listed Associate customer(s): " + ((PayingCustomer)customers.get(i)).getCustomersLinkedAssociates());
                    magazineInfoPanel.getItems().add("Listed Associate Supplement Subscription(s): " + ((PayingCustomer)customers.get(i)).getLinkedAssociatesSupplements());
                    magazineInfoPanel.getItems().add("Card Number: " + ((PayingCustomer)customers.get(i)).getCustomerCardNumber());
                    magazineInfoPanel.getItems().add("Customer Status: " + ((PayingCustomer)customers.get(i)).getCustomerStatus());
                    Double totalCost = ((PayingCustomer)customers.get(i)).getCustomerMonthlyBill(); //calculate total monthly bill for customer
                    magazineInfoPanel.getItems().add("Billing History: ");
                    for(String interest : customers.get(i).getCustomerInterests()) //for each interest in a customer's interests
                    {
                        for(int j = 0; j <= customers.get(i).getCustomerInterests().size(); j++)
                        {
                        if(customerBillingInfo.get(j) != null && customerBillingInfo.get(j).contains(interest))
                        {
                            magazineInfoPanel.getItems().add(customerBillingInfo.get(j));
                        }
                        }
                        
                    }
                    magazineInfoPanel.getItems().add("$" + Math.round(totalCost) + " total monthly fee.");

                }
             if(customers.get(i).getClass().equals(AssociateCustomer.class)){ //if customer is an associate customer
                magazineInfoPanel.getItems().add("Customer Status: " + ((AssociateCustomer)customers.get(i)).getCustomerStatus());

             }
            
            }
            
        }
        
        }
        else //if the selected item is not a customer
            for(int j=0; j<magazine.size(); j++){ //loop through magazine arraylist
                
            if(magazineSupplements.getSelectionModel().getSelectedItem().equals(magazine.get(j).getSupplementTitle())){ //if selected listbox item equals a supplement title stored in the arraylist
                
                magazineInfoPanel.getItems().add("Supplement Title: " + magazine.get(j).getSupplementTitle());
                magazineInfoPanel.getItems().add("Supplement News: " + magazine.get(j).getSupplementNews());
                magazineInfoPanel.getItems().add("Supplement Deals: " + magazine.get(j).getSupplementDeals());
                magazineInfoPanel.getItems().add("Supplement Price: " + magazine.get(j).getSupplementPrice());
                
                if(magazine.get(j).getClass().equals(SportsSupplement.class))
                {
                    magazineInfoPanel.getItems().add("Subscribed Customers: " + magazine.get(j).getSportsCustomers().toString());
                }
                if(magazine.get(j).getClass().equals(TechnologySupplement.class))
                {
                    magazineInfoPanel.getItems().add("Subscribed Customers: " + magazine.get(j).getTechCustomers().toString());
                }
                if(magazine.get(j).getClass().equals(CookingSupplement.class))
                {
                    magazineInfoPanel.getItems().add("Subscribed Customers: " + magazine.get(j).getCookingCustomers().toString());
                }
                if(magazine.get(j).getClass().equals(TravelingSupplement.class))
                {
                    magazineInfoPanel.getItems().add("Subscribed Customers: " + magazine.get(j).getTravelingCustomers().toString());
                }    
                
                }
           }
        } catch(Exception x)
          {
              System.out.println(x + " Error, a customer or supplement selection is required for the view option.");
          }
    }
    
    /**
     * Loads editing service based on supplement or customer selection.
     */
    @FXML 
    private void editMagazine(){
        MagazineService ms = new MagazineService();
        
        try{
        if(magazineCustomers.getSelectionModel().getSelectedItem() == null){
        for(int i=0; i<magazine.size(); i++){
            if(magazineSupplements.getSelectionModel().getSelectedItem().equals(magazine.get(i).getSupplementTitle())){
                ms.editMagazineSupplements();
            }
        }
        }
        else
            for(int j=0; j<customers.size(); j++){
                if(magazineCustomers.getSelectionModel().getSelectedItem().equals(customers.get(j).getName()))
                {
                    ms.editMagazineCustomers();
                }
            }
        }catch(Exception x){
            System.out.println(x + " Error loading selected customer or supplement.");
        }
    }
    
    /**
     * Deselects the current supplement selection.
     */
    @FXML
    private void resetSupplementSelection(){

                magazineSupplements.getSelectionModel().clearSelection();
            
    }
    
    /**
     * Deselects the current customer selection.
     */
    @FXML
    private void resetCustomerSelection(){

               magazineCustomers.getSelectionModel().clearSelection();
            
    }
    
    /**
     * Refreshes and updates the magazine details asynchronously.
     */
    @FXML
    private void refreshMagazineDetails()
    {
        if(magazineSupplements.getSelectionModel().getSelectedItem() == null){
            magazineCustomers.getItems().clear();
           for(int i = 0; i < customers.size(); i++){
                magazineCustomers.getItems().add(customers.get(i).getName());
           }
        }
        else
            if(magazineCustomers.getSelectionModel().getSelectedItem() == null){
           magazineSupplements.getItems().clear();
            for(int j = 0; j < magazine.size(); j++){
                magazineSupplements.getItems().add(magazine.get(j).getSupplementTitle());
                
           }
        }
        ms.addSupplementAmount();
        ms.linkAssociateCustomers();
        ms.linkCustomerSupplements();
        ms.createBillingHistory();
    }
    
}
