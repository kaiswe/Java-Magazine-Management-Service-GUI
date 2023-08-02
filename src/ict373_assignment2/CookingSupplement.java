package ict373_assignment2;

/**
 * Class representing the Cooking supplement within the magazine, that instantiates and processes all object and relevant information regarding the magazine's Cooking supplement.
 * @author Kai Sweeting
 * 06/04/2022
 * CookingSupplement.java
 * Input: The Cooking supplement title, news, relevant deals, and its monthly additional price.
 * Output: A structured Cooking supplement within the magazine.
 */
public class CookingSupplement extends MagazineService implements java.io.Serializable  {
    
    private String cookingTitle; // A string containing the title of the Cooking supplement.
    private String cookingNews; // A string containing the news segment of the Cooking supplement.
    private String cookingDeals; // A string containing the deals segment of the Cooking supplement.
    private Double cookingPrice; // Double containing the monthly additional price for a Cooking supplement subscription.
    
    
    /**
     * Default Class Constructor.
     */
    public CookingSupplement()
    {
        cookingTitle = " ";
        cookingNews = " ";
        cookingDeals = " ";
        cookingPrice = null;
    }

    /**
     * Class Constructor for the Cooking Supplement.
     * @param title A string containing the title of the Cooking supplement.
     * @param news A string containing the news segment of the Cooking supplement.
     * @param deals A string containing the deals segment of the Cooking supplement.
     * @param price A double containing the price for a Cooking supplement subscription.
     */    
    public CookingSupplement(String title, String news, String deals, Double price)
    {
        this.cookingTitle = title;
        super.setSupplementTitle(title); // Call to super class to set the title.
        
        this.cookingNews = news;
        super.setSupplementNews(news); // Call to super class to set the news.
        
        this.cookingDeals = deals;
        super.setSupplementDeals(deals); // Call to super class to set the deals.
        
        this.cookingPrice = price;
        super.setSupplementPrice(price); // Call to super class to set the price.
    }
    
    @Override
    public String getSupplementTitle()
    {
        return cookingTitle;
    }
    
    @Override
    public  String getSupplementNews()
    {
        return cookingNews;
    }
    
    @Override
    public  String getSupplementDeals()
    {
        return cookingDeals;
    }
    
    @Override
    public  Double getSupplementPrice()
    {
        return cookingPrice;
    }
    
    
}

