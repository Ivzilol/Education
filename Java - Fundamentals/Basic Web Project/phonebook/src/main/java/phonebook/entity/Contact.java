package phonebook.entity;

public class Contact {

    private String name;

    private String number;

    public Contact(String name, String number){
        this.name = name;
        this.number = number;
    }
    public Contact(){}

    public String getName(){
        return this.name;
    }
    public String getNumber(){
        return this.number;
    }
    public void setName(String newName){
        this.name = newName;
    }
    public void setNumber(String newNumber){
        this.number = newNumber;
    }
}
