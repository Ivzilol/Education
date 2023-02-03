package ExamPreparetion_01.sanctuary;

public class Main {
    public static void main(String[] args) {
        // Initialize the repository
        Habitat park = new Habitat(10);

// Initialize entity
        Elephant firstElephant = new Elephant("Bobby", 10, "Thailand Zoo");
// Print Elephant
        System.out.println(firstElephant); //Bobby 10 - Thailand Zoo

// Add Elephant
        park.add(firstElephant);

// Remove Elephant
        System.out.println(park.remove("Bobby")); //true
        System.out.println(park.remove("Lola")); //false
        Elephant secondElephant = new Elephant("Bibi", 5, "Private Zoo");
        Elephant thirdElephant = new Elephant("Lola", 7, "National Circus of Thailand");
        park.add(secondElephant);
        park.add(thirdElephant);

// Get Oldest Elephant
        Elephant oldest = park.getOldestElephant();
        System.out.println(oldest); //Lola 7 - National Circus of Thailand

        Elephant elephant = park.getElephant("Private Zoo");
        System.out.println(elephant); //Bibi 5 - Private Zoo

// All elephants in the park
        System.out.println(park.getAllElephants()); //2

// Information Report
        System.out.println(park.getReport());

//Saved elephants in the park:
//Bibi came from: Private Zoo
//Lola came from: National Circus of Thailand

    }
}
