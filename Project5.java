/**
 * Reads .txt file containing mail coach shipment info
 * manipulates read data 
 * prints report on bulk shipments to be sent the next week
 *
 * @author Iris Carrigg
 * @version 10-24-2022
 */
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Project5 {
    //DATE AND TIME YOU FIRST START WORKING ON THIS ASSIGNMENT (date AND time): <--------------------
    //ANSWER:  10/24/2022 9:00AM              <--------------------

    //DO NOT ALTER THE MAIN METHOD
    public static void main( String[] args ) {
        //test your entity classes, comment out when you passed all tests
        //testShipment();
        //testMailCoach();
        readData( "week1.txt" );
        //readData reads the input file into an array list
        //it fills the array list with Shipment objects
        ArrayList< Shipment > allMail = readData( "week1.txt" );

        //sortData goes through the raw data contained in all mail and populates
        //an array list for the destination with MailCoach objects
        ArrayList< MailCoach > stolat = sortData( "Stolat", allMail );
        ArrayList< MailCoach > quirm = sortData( "Quirm", allMail );
        ArrayList< MailCoach > pseudopolis = sortData( "Pseudopolis", allMail );
        ArrayList< MailCoach > borogravia = sortData( "Borogravia", allMail );
        ArrayList< MailCoach > ueberwald = sortData( "Ueberwald", allMail );
        ArrayList< MailCoach > krull = sortData( "Krull", allMail );

        //printReport prints the mail coach data dor each destination in turn
        printReport( stolat );
        printReport( quirm );
        printReport( pseudopolis );
        printReport( borogravia );
        printReport( ueberwald );
        printReport( krull );
    }//DO NOT ALTER THE MAIN METHOD
    public static void testShipment() { //DO NOT ALTER THIS METHOD
        Shipment s1 = new Shipment();
        assert s1.getDestination().equals( "" ) && s1.getVolume() == 0 && 
        s1.getId() == 0 && Math.abs( s1.getWeight() - 0 ) < 0.001 : "Shipment standard constructor not working";
        Shipment s2 = new Shipment( 44, "Stolat", 10.5, 13 );
        assert s2.getDestination().equals( "Stolat" ) : "Shipment second costructor destination not set correctly";
        assert s2.getVolume() == 13 : "Shipment second costructor volume not set correctly";
        assert s2.getId() == 44 : "Shipment second costructor id not set correctly";
        assert Math.abs( s2.getWeight() - 10.5 ) < 0.001 : "Shipment second costructor weight not set correctly";
        System.out.println( "Shipment all tests passed" );
    } //DO NOT ALTER THIS METHOD
    public static void testMailCoach() { //DO NOT ALTER THIS METHOD
        MailCoach mc1 = new MailCoach();
        assert mc1.getDestination().equals( "" ) && mc1.getVolume() == 0 && 
        Math.abs( mc1.getWeight() - 0 ) < 0.001 && mc1.getCargo() != null : "MailCoach standard constructor not working";
        MailCoach mc2 = new MailCoach( "Stolat" );  
        assert mc2.getDestination().equals( "Stolat" ) && mc2.getVolume() == 0 && 
        Math.abs( mc2.getWeight() - 0 ) < 0.001 && mc2.getCargo() != null : "MailCoach second constructor not working";
        Shipment s1 = new Shipment( 12, "Stolat", 106.7, 45 );
        Shipment s2 = new Shipment( 44, "Stolat", 10.5, 13 );
        mc2.addShipment( s1 );
        mc2.addShipment( s2 );
        assert mc2.getVolume() == 58 : "MailCoach addShipment not working";
        assert Math.abs( mc2.getWeight() - 117.2 ) < 0.001 : "MailCoach addShipment not working";
        assert mc2.getCargo().get( 0 ) == s1 : "MailCoach addShipment not working";
        assert mc2.getCargo().get( 1 ) == s2 : "MailCoach addShipment not working";
        System.out.println( "MailCoach all tests passed" );
    }//DO NOT ALTER THIS METHOD

    /**
     * Reads data from .txt file into ArrayList<>
     * @param String
     * @return ArrayList<Shipment>
     */
    public static ArrayList< Shipment > readData( String fileName ) {
        //initialize input stream
        Scanner in = null;

        //try to open files
        try
        {
            //create a new File object to represent the file with the name from above
            //TRY to create a new stream attached to that file
            in = new Scanner( new File( fileName ) );
        }
        catch ( FileNotFoundException e ){
            //if the file is not found, a FileNotFoundException will be thrown
            System.out.println( "File not found" );
        }

        //create ArrayList
        ArrayList< Shipment > s = new ArrayList< Shipment >();

        //initialize local variables
        int id = 0,volume = 0, i;
        double weight = 0.0;
        String idAsString = "", volumeAsString = "", destination = "";
        
        //read file into ArrayList
        while( in.hasNext() )
        {
            weight = in.nextDouble();

            in.next();

            //volume type conversion
            volumeAsString = in.next().trim().replace( "(","" ).replace( ")","" );
            volume = Integer.parseInt( volumeAsString );

            in.next();

            destination = in.next();

            in.next();
            in.next();

            //id type conversion
            idAsString = in.next().trim().replace( "#", "" );
            id = Integer.parseInt( idAsString );

            //append Shipment object to s
            s.add( new Shipment( id, destination, weight, volume ) );
        }

        in.close(); //close in stream
        return s;
    }

    /**
     * Sorts data from ArrayList to determine which mail coaches to add shipment onto
     * @param String ArrayList<Shipment>
     * @return ArrayList<MailCoach>
     */
    public static ArrayList< MailCoach > sortData( String destination, ArrayList< Shipment > allMail ) {
        //initialize local variables
        int i, j;
        int volume = 100, weight = 500;
        int doesItFitCounter = 0; //a counter to identify empty mail coaches 

        //create ArrayList of type MailCoach
        ArrayList< MailCoach > m = new ArrayList< MailCoach >();
        m.add( new MailCoach( destination ) ); //append MailCoach() object to m

        //loop through allMail
        for( i = 0; i < allMail.size(); i++ )
        {
            //check if object destination matches destination needing to be printed
            if( allMail.get(i).getDestination().equals( destination ) )
            {
                //intialize local variables
                doesItFitCounter = m.size();
                
                //loop through allMail and m to compare values
                for( j = 0; j < m.size(); j++ )
                {
                    //if there is room on the mail coach, add shipment
                    if( (( m.get(j).getVolume() + allMail.get(i).getVolume()) <= volume ) && (( m.get(j).getWeight() + allMail.get(i).getWeight()) <= weight ) )
                    {
                        m.get(j).addShipment( allMail.get(i) );
                        break;
                    }
                    else
                        doesItFitCounter--;
                    
                }
                
                //if there was no room on any mail coach, create new object to add shipment to
                if(doesItFitCounter == 0)
                    {
                        m.add( new MailCoach( destination ) );
                        m.get(j).addShipment( allMail.get(i) );
                    }
            }
        }
        
        return m;
    }

    /**
     * Prints a report on shipments to be sent out the next week
     * @param ArrayList<MailCoach>
     * @return void
     */
    public static void printReport( ArrayList< MailCoach > mc ) {
        //initialize local variables
        int i, j = 1, k;
        int noShipmentsCounter = 0; 

        System.out.printf("Next week's mail coaches to %s:%n", mc.get(0).getDestination());
        
        //loop through mc 
        for(i = 0; i < mc.size(); i++)
        {
            //initialize local variables
            noShipmentsCounter += 1;
            
            //check if mailCoach is empty
            if( mc.get(i).getVolume() == 0 && mc.get(i).getWeight() == 0 )
                noShipmentsCounter--; 
            
            else
            {
                //check if mail coach should be dispatched
                if( mc.get(i).getVolume() >= 50 || mc.get(i).getWeight() >= 250 )
                {
                    System.out.printf( "    DISPATCH: coach %d (shipments", j );
                    j++;
                }
                
                //check if mail coach should be placed on hold
                else if( (mc.get(i).getVolume() < 50) && (mc.get(i).getWeight() < 250) )
                {
                    System.out.printf( "    HOLD:     coach %d (shipments", j );
                    j++;
                }
                
                //print shipments to mail coach
                for( k = 0; k < mc.get(i).getCargo().size(); k++ )
                {
                    System.out.printf( " #%d", mc.get(i).getCargo().get(k).getId() );
                    
                    if(k == mc.get(i).getCargo().size() - 1)
                        System.out.printf( ")%n" );
                }
            }
            
            //check if there were no shipments for the week
            if( noShipmentsCounter == 0)
                System.out.printf( "    NO SHIPMENTS%n" );
            
            //print new line when all coaches are printed for the week
            if( i == mc.size() - 1 )
                System.out.printf( "%n" );
        }
    }
}
