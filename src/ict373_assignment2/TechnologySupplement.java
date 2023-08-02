package ict373_assignment2;

/**
 * Class representing the Technology supplement within the magazine, that instantiates and processes all object and relevant information regarding the magazine's Technology supplement.
 * @author Kai Sweeting
 * 06/04/2022
 * TechnologySupplement.java
 * Input: The Technology supplement title, news, relevant deals, and its monthly additional price.
 * Output: A structured Technology supplement within the magazine.
 */
public class TechnologySupplement extends MagazineService  implements java.io.Serializable {
    
    private String techTitle; // A string containing the title of the Technology supplement.
    private String techNews; // A string containing the news segment of the Technology supplement.
    private String techDeals; // A string containg the deals segment of the Technology supplement.
    private Double techPrice; // Double containing the monthly additional price for a Technology supplement subscription.
    
    /**
     * Default Class Constructor.
     */
    public TechnologySupplement()
    {
        techTitle = " ";
        techNews = " ";
        techDeals = " ";
        techPrice = null;
    }
    
    /**
     * Class Constructor for the Technology Supplement.
     * @param title A string containing the title of the Technology supplement.
     * @param news A string containing the news segment of the Technology supplement.
     * @param deals A string containing the deals segment of the Technology supplement.
     * @param price A double containing the price for a Technology supplement subscription.
     */  
    public TechnologySupplement(String title, String news, String deals, Double price)
    {
        techTitle = title;
        super.setSupplementTitle(title); // Call to super class to set the title.
        
        techNews = news;
        super.setSupplementNews(news); // Call to super class to set the news.
        
        techDeals = deals;
        super.setSupplementDeals(deals); // Call to super class to set the deals.
        
        techPrice = price;
        super.setSupplementPrice(price); // Call to super class to set the price.
    }
   
    @Override
    public String getSupplementTitle()
    {
        return techTitle;
    }
    
    @Override
    public  String getSupplementNews()
    {
        return techNews;
    }
    
    @Override
    public  String getSupplementDeals()
    {
        return techDeals;
    }
    
    @Override
    public  Double getSupplementPrice()
    {
        return techPrice;
    }
    
    
}
