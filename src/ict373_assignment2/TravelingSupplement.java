package ict373_assignment2;

/**
 * Class representing the Traveling supplement within the magazine, that instantiates and processes all object and relevant information regarding the magazine's Traveling supplement.
 * @author Kai Sweeting
 * 06/04/2022
 * TravelingSupplement.java
 * Input: The Traveling supplement title, news, relevant deals, and its monthly additional price.
 * Output: A structured Traveling supplement within the magazine.
 */
public class TravelingSupplement extends MagazineService implements java.io.Serializable  {
    
    private String travelingTitle; // String containing the title of the traveling supplement.
    private String travelingNews; // String containing the news segment of the traveling supplement.
    private String travelingDeals; // String containing the deals segment of the traveling supplment.
    private Double travelingPrice; // Double containing the monthly additional price for a traveling supplement subscription.
    
    /**
     * Default Class Constructor.
     */
    public TravelingSupplement()
    {
        travelingTitle = " ";
        travelingNews = " ";
        travelingDeals = " ";
        travelingPrice = null;
    }
    
    /**
     * Class Constructor for the Traveling Supplement.
     * @param title A string containing the title of the Traveling supplement.
     * @param news A string containing the news segment of the Traveling supplement.
     * @param deals A string containing the deals segment of the Traveling supplement.
     * @param price A double containing the price for a Traveling supplement subscription.
     */
    public TravelingSupplement(String title, String news, String deals, Double price)
    {
        travelingTitle = title;
        super.setSupplementTitle(title); // Call to super class to set the title.
        
        travelingNews = news;
        super.setSupplementNews(news); // Call to super class to set the news.
        
        travelingDeals = deals;
        super.setSupplementDeals(deals); // Call to super class to set the deals.
        
        travelingPrice = price;
        super.setSupplementPrice(price); // Call to super class to set the price.
    }
    
    @Override
    public String getSupplementTitle()
    {
        return travelingTitle;
    }
    
    @Override
    public  String getSupplementNews()
    {
        return travelingNews;
    }
    
    @Override
    public  String getSupplementDeals()
    {
        return travelingDeals;
    }
    
    @Override
    public  Double getSupplementPrice()
    {
        return travelingPrice;
    }
}
    
    
