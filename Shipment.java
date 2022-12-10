/**
 * hold and intializes values from data about shipments
 *
 * @author Iris Carrigg
 * @version 10-24-2022
 */
public class Shipment
{
    //declare instance variables
    private String destination;
    private double weight;
    private int volume, id;

    /**
     * standard constructor for objects of class Shipment
     * initializes instance variables, creates dummy object with no data
     * @param none
     * @return none
     */
    public Shipment()
    {
        //initialize instance variables
        this.destination = "";
        this.weight = 0.0;
        this.volume = 0;
        this.id = 0;
    }

    /**
     * constructor for objects of class Shipment
     * intializes, sets instance variables
     * @param int, String, double, int
     * @return none
     */
    public Shipment(int id, String destination, double weight, int volume)
    {
        //initialize instance variables
        this.id = id;
        this.destination = destination;
        this.weight = weight;
        this.volume = volume;
    }

    /**
     * gets value of this.destination
     * @param none
     * @return String
     */
    public String getDestination()
    {
        return this.destination;
    }

    /**
     * gets value of this.weight
     * @param none
     * @return double
     */
    public double getWeight()
    {
        return this.weight;
    }
    
    /**
     * gets value of this.volume
     * @param none
     * @return int
     */
    public int getVolume()
    {
        return this.volume;
    }
    
    /**
     * gets value of this.id
     * @param none
     * @return int
     */
    public int getId()
    {
        return this.id;
    }
}
