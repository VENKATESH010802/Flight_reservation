import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin {
    String pass="1234";
    Scanner p=new Scanner(System.in);
    public void admin_options() {
        try {
            System.out.println("Enter the option preferred to continue: ");
            System.out.println("1. View flights 2.View tickets 3.Add flights 4.modify flight 5.Logout 6.Terminate");
            int x = p.nextInt();
            Entrance e = new Entrance();
            switch (x) {
                case 1:
                    e.view_flight();
                    admin_options();
                    break;
                case 2:
                    e.view_tickets_admin();
                    admin_options();
                    break;
                case 3: {
                    p.nextLine();
                    System.out.println("Enter the flight brand: ");
                    String brand = p.nextLine();
                    System.out.println("Enter the flight capacity: ");
                    int cap = p.nextInt();
                    System.out.println("Enter the number of seats already booked: ");
                    int booked = p.nextInt();
                    p.nextLine();
                    System.out.println("Enter the starting point: ");
                    String st = p.nextLine();
                    System.out.println("Enter the starting time: ");
                    double sti = p.nextDouble();
                    p.nextLine();
                    System.out.println("Enter the destination address: ");
                    String dt = p.nextLine();
                    System.out.println("Enter the destination time: ");
                    double dti = p.nextDouble();
                    System.out.println("Enter the ticket price: ");
                    double price = p.nextDouble();
                    e.add(brand, cap, booked, st, dt, sti, dti, price);
                    admin_options();
                }
                break;
                case 4: {
                    System.out.println("Enter the flight ID to modify: ");
                    int id = p.nextInt();
                    e.modify(id);
                    admin_options();
                }
                break;
                case 5:
                    e.enter();
                    break;
                case 6:
                    System.out.println("Session Ended Successfully..");
                    break;
                default: {
                    System.out.println("Wrong option");
                    admin_options();
                }
                break;
            }
        }
        catch (InputMismatchException E){
            //System.out.println(E.getMessage());
            System.out.println("Make sure you enter right data type at right fields.");
            admin_options();
        }
        catch (NullPointerException E){
            System.out.println("Something unexpected have happened try to re-run the program or contact developer.");
            admin_options();
        }
    }
}
