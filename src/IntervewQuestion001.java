
import java.io.*;
import java.util.*;
/**
You can play around, type and run code as needed. Once you click on View Playback in the Settings in the bottom right hand corner, you will be able to view the playback of this pad and what was typed during the interview.
--------------------------------------------------------
Design a parking lot using object-oriented principles

Goals:
- Your solution should be in Java - if you would like to use another language, please let the interviewer know.
- Boilerplate is provided. Feel free to change the code as you see fit

Assumptions:
- The parking lot can hold motorcycles, cars and vans
- The parking lot has motorcycle spots, car spots and large spots
- A motorcycle can park in any spot
- A car can park in a single compact spot, or a regular spot
- A van can park, but it will take up 3 regular spots
- These are just a few assumptions. Feel free to ask your interviewer about more assumptions as needed

Here are a few methods that you should be able to run:
- Tell us how many spots are remaining
- Tell us how many total spots are in the parking lot
- Tell us when the parking lot is full
- Tell us when the parking lot is empty
- Tell us when certain spots are full e.g. when all motorcycle spots are taken
- Tell us how many spots vans are taking up

 PROBLEMS:
 - Need to fix the counters, and have a listener that alerts when the parking lot
 is empty
Hey candidate! Welcome to your interview. I'll start off by giving you a Solution class. To run the code at any time, please hit the run button located in the top left corner.
*/
public class IntervewQuestion001 {
    // Given the follwing problem, come up with the solution
    /* This is an example from coderpad.io
    * I am not the author of this problem
    */
    public static void main(String[] args) {
        Solution sol = new Solution();
        // - Tell us how many total spots are in the parking lot
        System.out.println("There are a total of " + sol.getTotalSpots() + " spots");

        sol.addVehicles();
        // - Tell us how many spots are remaining
        System.out.println("There are " + sol.getNumberOfSpotsAvailable() + " spots available.");

        // - Tell us when the parking lot is full
        System.out.println("Is parking lot full... \n" + sol.isParkingLotFull());

        // - Tell us when the parking lot is empty
        // Need an alerter for when the parking lot is empty

        // - Tell us when certain spots are full e.g. when all motorcycle spots are taken
        System.out.println("Remaining number of Motorcycle Spots = " + sol.getTotalMotorcycleSpotsOpen());

        // - Tell us how many spots vans are taking up
        System.out.println("Total of Vans in the parking lot = " + sol.getTotalVans());

        // we have a parking lot and we are going to store motorcycles, cars and vans
        System.out.println("Total of Motorcycles in the parking lot = " + sol.getTotalMotorcycles());

        // the parking lot it is composed of motorcycle spots, compact and large spots
        int total_cars = (sol.getTotalVans() + sol.getTotalMotorcycles()) - sol.getTotalVehicles();
        System.out.println("The regular cars were added = " + total_cars);

    }
}


/**
 * The solution to the problem
 */
class Solution {
    private int LARGE_SPOTS = 10;
    private int MOTORCYCLE_SPOTS = 20;
    private int TOTAL_SPOTS = MOTORCYCLE_SPOTS + LARGE_SPOTS + 200;
    private ArrayList<Vehicle> totalVehicles = new ArrayList<Vehicle>();
    private ArrayList<Vehicle> totalVans = new ArrayList<Vehicle>();
    private ArrayList<Vehicle> totalMotorcycles = new ArrayList<Vehicle>();

    /*Returns the total of spots available*/
    public  int getNumberOfSpotsAvailable(){
        return TOTAL_SPOTS - getTotalOfSpotsTaken();
    }

    public int getTotalSpots(){return TOTAL_SPOTS;}
    public int getTotalVans(){return totalVans.size();}
    public int getTotalMotorcycles(){return totalMotorcycles.size();}
    public int getTotalVehicles(){return totalVehicles.size();}

    // Returns grand total of spots taken for motor, van and vics
    public int getTotalOfSpotsTaken(){
        return getTotalVans() + getTotalMotorcycles() + totalVehicles.size();
    }
    public int getTotalMotorcycleSpotsOpen()
    {
        return getTotalMotorcycles() - MOTORCYCLE_SPOTS;
    }
    public int getTotalVanSpotsOpen()
    {
        return getTotalVans() - LARGE_SPOTS;
    }

    public void printVehiclesAdded()
    {
        for(Vehicle v : totalVehicles)
        {
            System.out.println("vehicle added = " + v.getType());
        }
    }

    public void addVehicles(){
        Random r = new Random();
        int low = 10;
        int high = 200;
        int result = r.nextInt(high-low) + low;
        System.out.println("Total vehicles were added = " + result);
        //Predetermined selection of addition of Vehicles
        for (int i = 0; i <= result; i++)
        {
            // create a random car and add it to our parking lot inventory
            // fill up the large spaces first and motorcycle spots then move on
            Vehicle v = createRandomCar();
            //If it is a moto then take a moto space
            if(v.getType().equals("moto")){
                if(MOTORCYCLE_SPOTS >= 0)
                {
                    MOTORCYCLE_SPOTS --;
                    totalMotorcycles.add(v);
                }
            }
            // otherwise then take a LARGE SPOT for vans
            else if(v.getType().equals("van")){
                if(LARGE_SPOTS >= 0)
                {
                    LARGE_SPOTS --;
                    totalVans.add(v);
                }
                else if(TOTAL_SPOTS <= 2){
                    throw new IllegalArgumentException("Cant store more vans, no space left");
                }
                else
                {
                    totalVehicles.add(v);
                    totalVehicles.add(v);
                    totalVehicles.add(v);
                }
            }
            // otherwiseis a regular car or take 3 spots for larger vics
            else if(TOTAL_SPOTS >= 0)
            {
                totalVehicles.add(v);
            }
        }

    }

    public String isParkingLotFull()
    {
        if (getNumberOfSpotsAvailable() <= 0)
        {
            return "Yes is full, try again later";
        }
        else
            return "There is still space, come on up";
    }

    // Not exposed, only used internally to generate
    // a random car
    private Vehicle createRandomCar(){
        Random r = new Random();
        int res = r.nextInt(4-1)+1;

        switch(res){
            case 1:
                return Vehicle.CAR;
            case 2:
                return Vehicle.VAN;
            case 3:
                return Vehicle.MOTORCYCLE;
            default:
                throw new IllegalArgumentException("car out of bounds, doesnt exists");
        }
    }
    enum Vehicle{
        CAR("car",1),
        MOTORCYCLE("moto",1),
        VAN("van",3);
        private int spots;
        private String type;

        public int getSpots(){return this.spots;}
        public String getType(){return this.type;}

        private Vehicle(String type,int spot)
        {
            this.type = type;
            this.spots = spot;
        }
    }
}
