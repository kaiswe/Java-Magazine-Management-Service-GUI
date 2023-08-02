package ict373_assignment2;

import static ict373_assignment2.MagazineService.magazine;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import static ict373_assignment2.FXMLMagazineEditController.supplementStage;
import static ict373_assignment2.MagazineService.customerStage;

/**
 * FXML Controller class for the supplement editing system.
 * 07/06/2022
 * @author Kai
 */
public class FXMLSupplementEditController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField supplementTitle;
    @FXML
    private TextArea supplementNews;
    @FXML
    private TextArea supplementDeals;
    @FXML
    private TextField supplementPrice;
    @FXML
    private TextField magazinePrice;
    @FXML
    private ListView<String> allSupplements;
    
    
    Stage prevStage;
    

    /**
     * initialize method on attached FXML/scene execution.
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        for(int i=0; i< magazine.size(); i++){ //loop through magazine arraylist
            
        allSupplements.getItems().add(magazine.get(i).getSupplementTitle()); //display all stored supplements
        
        }
        
        supplementNews.setWrapText(true); 
        supplementDeals.setWrapText(true);
        
    }
    
    /**
     * Sets the GUI to previously viewed stage.
     * @param stage 
     */
    public void setPreviousStage(Stage stage) {
    this.prevStage = stage;
}
    
    /**
     * Shows the supplements details within selected area on GUI.
     */
    @FXML
    private void showSupplementDetails()
    {
        try {
        for(int i=0; i<magazine.size(); i++) //loop through the magazine arraylist
        {
            if(allSupplements.getSelectionModel().getSelectedItem().equals(magazine.get(i).getSupplementTitle()))
            {
                supplementTitle.setText(magazine.get(i).getSupplementTitle());
                supplementNews.setText(magazine.get(i).getSupplementNews());
                supplementDeals.setText(magazine.get(i).getSupplementDeals());
                supplementPrice.setText(magazine.get(i).getSupplementPrice().toString());
            }
            }
            }   catch(Exception x)
                {
                    System.out.println("A " + x + " Occurred, please select a magazine supplement before pressing the edit button." );
                }
    }
    
    /**
     * Saves the inputted data of the supplement.
     */
    @FXML
    private void saveSupplementDetails()
    {
        for(int i=0; i<magazine.size(); i++) //loop through the magazine arraylist
        {
            if(allSupplements.getSelectionModel().getSelectedItem().equals(magazine.get(i).getSupplementTitle()))
            {
                try {
                magazine.get(i).setMagazineTitle(supplementTitle.getText());
                magazine.get(i).setSupplementNews(supplementNews.getText());
                magazine.get(i).setSupplementDeals(supplementDeals.getText());
                magazine.get(i).setSupplementPrice(Double.parseDouble(supplementPrice.getText()));
                } catch (NullPointerException x)
                {
                    System.out.println("Error, please enter all required inputs.");
                }
                
            }
        }
        MagazineService.saveMagazineData();
        
    }
    
    /**
     * Resets all the supplement inputs for the user.
     */
    @FXML
    private void resetAllInputs()
    {
        supplementTitle.clear();
        supplementNews.clear();
        supplementDeals.clear();
        supplementPrice.clear();
        
    }
    
    /**
     * Sets the GUI stage to the customer editing system.
     * @throws IOException 
     */
    @FXML
    private void editMagazineCustomers() throws IOException
   {
       FXMLMagazineEditController mec = new FXMLMagazineEditController();
       mec.setPreviousStage(supplementStage);
       BorderPane myPane = null;
       myPane = FXMLLoader.load(getClass().getResource("FXMLMagazineEdit.fxml"));
       Scene scene = new Scene(myPane);
       customerStage.setScene(scene);
       customerStage.setResizable(false);
       customerStage.setTitle("Edit Magazine Customers");
       supplementStage.close();
       customerStage.show();
   }
    
    /**
     * Deletes the selected supplement.
     */
    @FXML
    private void deleteSupplement()
    {
        for(int i=0; i<magazine.size(); i++)
        {
            if(allSupplements.getSelectionModel().getSelectedItem().equals(magazine.get(i).getSupplementTitle()))
            {
                try {
                    magazine.get(i);
                } catch(Exception x)
                {
                    System.out.println(x + " There was an error deleting this supplement, please try again.");
                }
            }
        }
        refreshSupplementDetails();
        supplementStage.close();

    }
    
    /**
     * Refreshes the supplements details asynchronously.
     */
    @FXML
    private void refreshSupplementDetails()
    {
           for(int i = 0; i < magazine.size(); i++){
                allSupplements.getItems().add(magazine.get(i).getSupplementTitle());
           }
    }
}
