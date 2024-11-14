import java.io.IOException;
import java.io.FileWriter;


public class Ticket{
    int row;
    int seat;
    double price;
    Person person;

    public Ticket(int row, int seat, double price, Person person){
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    public Person getPerson(){
        return person;
    }

    public void setRow(int row){
        this.row=row;
    }

    public void setSeat(int seat){
        this.seat=seat;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getRow(){
        return row;
    }

    public int getSeat() {
        return seat;
    }

    public double getPrice() {
        return price;
    }

    void printTicketsInfo(){
        System.out.println("Row:"+(row+1));

        System.out.println("Seat: "+(seat+1));
        System.out.println("Price: £"+price);
        person.printPersonInfo();
    }

    void save(){
        try{
            FileWriter writer = new FileWriter(row + seat + ".txt");
            writer.append("Row:").append(row+"").append("\nSeat: ").append(String.valueOf(seat)).append("\nPrice: £").append(String.valueOf(price)).append("\nName: ").append(person.getName()).append("\nSurname: ").append(person.getSurname()).append("\nEmail: ").append(person.getEmail());
            writer.close();
        }
        catch (IOException e){
            e.printStackTrace();

        }
    }
}