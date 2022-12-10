/**
 * manipulates data about shipments needing to be loaded into cargo 
 *
 * @author Iris Carrigg
 * @version 10-24-2022
 */

import java.util.ArrayList;

public class MailCoach
{
    //declare instance variables
    private String destination;
    private double weight;
    private int volume;
    private ArrayList<Shipment> cargo;

    /**
     * standard constructor for objects of class MailCoach
     * initializes instance variables, creates dummy object with no data
     * @param none
     * @return none
     */
    public MailCoach()
    {
        //initialize instance variables
        this.destination = "";
        this.weight = 0.0;
        this.volume = 0;
        this.cargo = new ArrayList<>();
    }

    /**
     * constructor for objects of class MailCoach
     * intializes, sets instance variables
     * @param String
     * @return none
     */
    public MailCoach(String destination)
    {
        //initialize instance variables
        this.destination = destination;
        this.weight = 0.0;
        this.volume = 0;
        this.cargo = new ArrayList<>();
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
     * gets value of this.volume
     * @param none
     * @return int
     */
    public int getVolume()
    {
        return this.volume;
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
     * gets value of this.cargo
     * @param none
     * @return ArrayList<Shipment>
     */
    public ArrayList<Shipment> getCargo()
    {
        return this.cargo;
    }

    /**
     * appends shipment from Shipment object to cargo
     * increments this.weight and this.volume by values accessed from Shipment object
     * @param Shipment object
     * @return void
     */
    public void addShipment(Shipment s)
    {
        this.weight += s.getWeight();
        this.volume += s.getVolume();
        this.cargo.add( s );
    }
}
