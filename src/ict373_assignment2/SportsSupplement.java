package ict373_assignment2;

/**
 * Class representing the sports supplement within the magazine, that instantiates and processes all relevant information regarding the magazine's sports supplement.
 * @author Kai Sweeting
 * 06/4/2022
 * Input: The Sports supplement title, news, relevant deals, and its monthly additional price.
 * Output: A structured Sports supplement within the magazine.
 */
public class SportsSupplement extends MagazineService implements java.io.Serializable  {
    
    private String sportsTitle; // String containing the title of the Sports supplement.
    private String sportsNews; // String containing the news segment of the Sports supplement.
    private String sportsDeals; // String containing the deals segment of the Sports supplement.
    private Double sportsPrice; // Double containing the monthly additional price for a Sports supplement subscription.
    
    
    /**
     * Default Class Constructor.
     */
    public SportsSupplement()
    {
        sportsTitle = " ";
        sportsNews = " ";
        sportsDeals = " ";
        sportsPrice = null;
    }
    
    /**
     * Class Constructor for the Sports supplement.
     * @param title A string containing the title of the Sports supplement.
     * @param news A string containing the news segment of the Sports supplement.
     * @param deals A string containing the deals segment of the Sports supplement.
     * @param price A double containing the price for a sports supplement subscription.
     */
    public SportsSupplement(String title, String news, String deals, Double price)
    {
        sportsTitle = title;
        super.setSupplementTitle(title); // Call to super class to set the title
        
        sportsNews = news;
        super.setSupplementNews(news); // Call to super class to set the the news
        
        sportsDeals = deals;
        super.setSupplementDeals(deals); // Call to super class to set the the deals        
        
        sportsPrice = price;
        super.setSupplementPrice(price); // Call to super class to set the price
    }
    

    @Override
    public String getSupplementTitle()
    {
        return sportsTitle;
    }
    
    @Override
    public  String getSupplementNews()
    {
        return sportsNews;
    }
    
    @Override
    public  String getSupplementDeals()
    {
        return sportsDeals;
    }
    
    @Override
    public  Double getSupplementPrice()
    {
        return sportsPrice;
    }

    
}
