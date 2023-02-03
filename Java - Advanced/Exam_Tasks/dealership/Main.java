package ExamPreparetion_01.dealership;

public class Main {

    public static void main(String[] args) {

        // Initialize the repository
        Dealership dealership = new Dealership("Autofest", 5);

// Initialize entity
        Car volvo = new Car("Volvo", "XC70", 2010);

// Print Car
        System.out.println(volvo); // Volvo XC70 (2010)

// Add Car
        dealership.add(volvo);

// Remove Car
        System.out.println(dealership.buy("Volvo", "XC90")); // false
        System.out.println(dealership.buy("Volvo", "XC70")); // true

        Car peugeot = new Car("Peugeot", "307", 2011);
        Car audi = new Car("Audi", "S4", 2005);

        dealership.add(peugeot);
        dealership.add(audi);

// Get Latest Car
        Car latestCar = dealership.getLatestCar();
        System.out.println(latestCar); // Peugeot 307 (2011)

// Get Car
        Car audiS4 = dealership.getCar("Audi", "S4");
        System.out.println(audiS4); // Audi S4 (2005)

// Count
        System.out.println(dealership.getCount()); // 2

// Get Statistics
        System.out.println(dealership.getStatistics());
// The cars are in a car ExamPreparetion_01.dealership Autofest:
// Peugeot 307 (2011)
// Audi S4 (2005)
    }
}
