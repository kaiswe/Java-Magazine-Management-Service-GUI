package ict373_assignment2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.*;
import javafx.fxml.*;
import static ict373_assignment2.MagazineService.customers;
import static ict373_assignment2.MagazineService.deletedCustomers;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import static ict373_assignment2.MagazineService.customerStage;
/**
 * FXML Controller class for editing the customer magazine service.
 * @author Kai Sweeting
 * 07/06/2022
 */
public class FXMLMagazineEditController implements Initializable {

    @FXML
    private TextField customerName;
    @FXML
    private TextField customerEmail;
    @FXML
    private TextField customerAddress;
    @FXML
    private TextField customerInterests;
    @FXML
    private TextField customerPaymentMethod;
    @FXML
    private TextField customerCardNum;    
    @FXML
    private ListView<String> allCustomers;
    @FXML
    private TextField linkedAssociateCustomers;
    
    MagazineService ms = new MagazineService();
    Stage prevStage;
    
    static Stage supplementStage;
    

    /**
     * initialize method on attached FXML/scene execution.
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        for(int i=0; i< customers.size(); i++){ //loop through customers arraylist
            
        allCustomers.getItems().add(customers.get(i).getName()); //add all stored customer names to listview
        
        }
    }
    
    /**
     * Sets the GUI to previously opened stage.
     * @param stage 
     */
    public void setPreviousStage(Stage stage) {
    this.prevStage = stage;
}
    
    /**
     * Shows all the selected customers stored details.
     */
    @FXML
    private void showCustomerDetails()
    {
        try {
        for(int i=0; i<customers.size(); i++){ //loop through the customers arraylist
            if(allCustomers.getSelectionModel().getSelectedItem().equals(customers.get(i).getName())) //if selected item is a customer's name stored in the arraylist
            {
                customerName.setText(customers.get(i).getName());
                customerEmail.setText(customers.get(i).getEmail());
                customerAddress.setText(customers.get(i).getCustomerAddress());
                customerInterests.setText(customers.get(i).getCustomerInterests().toString());
                
                }
            if(customers.get(i).getClass().equals(PayingCustomer.class)) //if the class of the selected customer is a paying customer
            {
                customerPaymentMethod.setText(((PayingCustomer)customers.get(i)).getPaymentMethod());
                customerCardNum.setText(((PayingCustomer)customers.get(i)).getCustomerCardNumber());
                linkedAssociateCustomers.setText(((PayingCustomer)customers.get(i)).getCustomersLinkedAssociates().toString());
            }
            
            
            if(customers.get(i).getClass().equals(AssociateCustomer.class)) //if the class of the selected customer is an associate customer
            {
                linkedAssociateCustomers.setDisable(true);
                linkedAssociateCustomers.clear();
            }
            else
                linkedAssociateCustomers.setDisable(false);
            }
        } catch(Exception x)
        {
            System.out.println("Error" + x);
            
        }
    }
    
    /**
     * Saves the inputted data of the customer.
     */
    @FXML
    private void saveCustomerDetails()
    {
        try{
            for(int i=0; i<customers.size(); i++)
            {
                if(allCustomers.getSelectionModel().getSelectedItem().equals(customers.get(i).getName()) && customers.get(i) != null)
                {
                    customers.get(i).setName(customerName.getText());
                    customers.get(i).setEmail(customerEmail.getText());
                    customers.get(i).setCustomerAddress(customerAddress.getText());
                    customers.get(i).setCustomerInterests(customerInterests.getText().trim().replaceAll("[\\[\\]]", "").replaceAll("[,]", ""));
                    if(customers.get(i).getClass().equals(PayingCustomer.class))
                    {
                        ((PayingCustomer)customers.get(i)).setPaymentMethod(customerPaymentMethod.getText());
                        ((PayingCustomer)customers.get(i)).setCustomerCardNumber(customerCardNum.getText());

                    }
                }
            
            }
        }catch(Exception x)
        {
            System.out.println("Error" + x);
        }
        ms.addSupplementAmount(); 
        ms.linkCustomerSupplements();
        ms.createSupplementPriceList();
        ms.createBillingHistory();
        MagazineService.saveMagazineData();
        
    }
    
    /**
     * Resets and clears all the data currently inputted into the text fields.
     */
    @FXML
    private void resetAllInputs()
    {
        customerName.clear();
        customerEmail.clear();
        customerAddress.clear();
        customerInterests.clear();
        customerPaymentMethod.clear();
        customerCardNum.clear();
        linkedAssociateCustomers.clear();
        
    }
    
    /**
     * Changes GUI to edit stored magazine supplements.
     * @throws IOException 
     */
    @FXML
    private void editMagazineSupplements() throws IOException
   {
       FXMLMagazineEditController mec = new FXMLMagazineEditController();
       mec.setPreviousStage(customerStage);
       supplementStage = new Stage();
       supplementStage.setTitle("Edit Magazine Supplements");
       BorderPane myPane = null;
       myPane = FXMLLoader.load(getClass().getResource("FXMLSupplementEdit.fxml"));
       Scene scene = new Scene(myPane);
       supplementStage.setScene(scene);
       supplementStage.setResizable(false);
       customerStage.close();

       supplementStage.show();
   }
    
    /**
     * delete the selected customer from the magazine service.
     */
    @FXML
    protected void deleteCustomer()
    {
        for(int i=0; i<customers.size(); i++)
        {
            if(allCustomers.getSelectionModel().getSelectedItem().equals(customers.get(i).getName()))
            {
                try {
                    customers.remove(i);
                    deletedCustomers.add(customers.get(i));
                } catch(Exception x)
                {
                    System.out.println(x + " There was an error deleting this customer, please try again.");
                }
            }
        }
        refreshMagazineDetails();
        customerStage.close();

    }
    
    /**
     * Refreshes the details of the magazine asynchronously.
     */
    @FXML
    private void refreshMagazineDetails()
    {
           for(int i = 0; i < customers.size(); i++){
                allCustomers.getItems().add(customers.get(i).getName());
           }
        ms.addSupplementAmount();
        ms.linkCustomerSupplements();
        ms.createBillingHistory();
    }
    
    
}
