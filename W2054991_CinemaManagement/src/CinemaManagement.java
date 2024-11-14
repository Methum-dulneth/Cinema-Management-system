import jdk.jfr.Period;

import javax.security.auth.kerberos.KerberosTicket;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CinemaManagement {
    public static int[][] seats = new int[3][16];
    public static Ticket[][] tickets = new Ticket[3][16];
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Welcome to The London Lumiere");
        System.out.println();
        int option;
        do {
            try {
                getOption();
                System.out.println("------------------------------------------------");
                System.out.print("Please select an option :");
                String temp = String.valueOf(input.nextInt());
                option = Integer.parseInt(temp);
                switch (option) {
                    case 1:
                        buy_ticket();
                        break;
                    case 2:
                        cancel_Ticket();
                        break;
                    case 3:
                        print_seating_area();
                        break;
                    case 4:
                        find_first_available();
                        break;
                    case 5:
                        printTicketInfo();
                        break;
                    case 6:
                        search_ticket();
                        break;
                    case 7:
                        sort_ticket();
                        break;
                    case 8:
                        exit();
                        break;
                    default:
                        System.out.println("ERROR!! \nEnter only number between 1-8");
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR!! \nEnter only number between 1-8");
            }
        } while (true);
    }
    private static void search_ticket() {
        System.out.print("Enter Row number (1-3) :");
        int seatRow = input.nextInt();

        if (seatRow < 0 || seatRow > 3) {
            System.out.println("Invalid seat number.");
            return;
        }
        System.out.print("Enter seat number (1-16) :");
        int seatNum = input.nextInt();
        if (seatNum < 0 || seatNum > 16) {
            System.out.println("Invalid seat number.");
        } else if (seats[seatRow - 1][seatNum - 1] == 0) {
            System.out.println("This seat is available.");
        } else {

            tickets[seatRow - 1][seatNum - 1].printTicketsInfo();
        }
    }
    private static void printTicketInfo() {
        double total = 0;
        for (int i = 0; i < tickets.length; i++) {
            for (int j = 0; j < tickets[i].length; j++) {
                if (tickets[i][j] != null) {
                    tickets[i][j].printTicketsInfo();
                    total += tickets[i][j].getPrice();
                }
            }
        }
        System.out.println("Toatal price = " + total);
    }

    private static void find_first_available() {
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j] == 0) {
                    System.out.println("First available seat: Row " + (i + 1) + ", Seat " + (j + 1));
                    return;
                }
            }
        }
        System.out.println("No seats available.");
    }


    public static void getOption() {
        System.out.println("------------------------------------------------");
        System.out.println("    1)  Buy a ticket :");
        System.out.println("    2)  Cancel ticket :");
        System.out.println("    3)  See seating plan :");
        System.out.println("    4)  Find first seat available :");
        System.out.println("    5)  Print ticket information and total price :");
        System.out.println("    6)  Search ticket :");
        System.out.println("    7)  Sort ticket by price :");
        System.out.println("    8)  Exit :");
        System.out.println("------------------------------------------------");
    }
    public static void buy_ticket() {
        System.out.print("Enter Row number (1-3) :");
        int seatRow = input.nextInt();
        double seatPrice;
        switch (seatRow) {
            case 1:
                seatPrice = 12;
                break;
            case 2:
                seatPrice = 10;
                break;
            case 3:
                seatPrice = 8;
                break;
            default:
                System.out.println("Invalid row.");
                return;
        }
        System.out.print("Enter seat number (1-16) :");
        int seatNum = input.nextInt();
        if (seatNum < 0 || seatNum > 16 ) {
            System.out.println("Invalid seat number.");
        } else if (seats[seatRow-1][seatNum-1] == 1) {
            System.out.println("This seat is not available.");
        } else {
            seats[seatRow-1][seatNum-1] = 1;
            System.out.print("Enter your name : ");
            String name = input.next();
            System.out.print("Enter your surname : ");
            String surname = input.next();
            System.out.print("Enter your email : ");
            String email = input.next();
            Ticket ticket = new Ticket(seatRow-1,seatNum-1,seatPrice,
                    new Person(name,surname,email));
            tickets[seatRow-1][seatNum-1]= ticket;
            System.out.println("The seat has been booked.");
            System.out.println("Seat booked: Row " + seatRow + ",Seat " + seatNum);
            System.out.println("Seat price = $" + seatPrice);
        }
    }

    public static void cancel_Ticket() {

        System.out.print("Enter Row number (1-3) :");
        int seatRow = input.nextInt();

        if (seatRow < 0 || seatRow > 3 ) {
            System.out.println("Invalid seat number.");
            return;
        }
        System.out.print("Enter seat number (1-16) :");
        int seatNum = input.nextInt();
        if (seatNum < 0 || seatNum > 16 ) {
            System.out.println("Invalid seat number.");
        } else if (seats[seatRow-1][seatNum-1] == 0) {
            System.out.println("This seat is already available.");
        } else {
            seats[seatRow-1][seatNum-1] = 0;
            tickets[seatRow-1][seatNum-1]= null;
            System.out.println("The seat has been canceled.");
        }
    }

    public static void print_seating_area() {
        System.out.println("Seating Plan");
        System.out.println();
        System.out.println("****************************************");
        System.out.println("*                SCREEN                *");
        System.out.println("****************************************");
        System.out.println();
        for (int i = 0; i < seats.length; i++) {
            System.out.print("Row " + (i + 1) + ": ");
            for (int j = 0; j < seats[i].length; j++) {

                if (j == 8) System.out.print("  ");
                System.out.print((seats[i][j] == 0 ? "0" : "x") + " ");
            }
            System.out.println();
        }
    }
    public static void sort_ticket(){
        Ticket[] ticket=new Ticket[48];
        int count = 0;
        for (int i=0 ;i<tickets.length;i++){
            for(Ticket t:tickets[i]){
                if(t!=null){
                ticket[count] =t; count++;
                }
            }
        }
        for (int i=0 ;i<ticket.length-1;i++ ){
            for (int j=0 ;j<ticket.length-1;j++){
                if (ticket[j+1]!=null && ticket[j].price>ticket[j+1].price){
                    Ticket temp=ticket[j+1];
                    ticket[j+1]= ticket[j];
                    ticket[j]=temp;
                }
            }
        }
        for(Ticket t:ticket){
           if (t!=null){
               t.printTicketsInfo();
           }
        }
    }
    public static void exit(){
        System.out.println("Exiting from Programme!!! \nHave a Nice Day.......");
        System.exit(0);
    }
}