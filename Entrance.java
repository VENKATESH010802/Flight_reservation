import java.util.InputMismatchException;
import java.util.Scanner;

public class Entrance {
    Scanner p=new Scanner(System.in);
    int current_id;
    //Changed line 8 to 13 from static to normal.
    int ID=1;
    int FD=1;
    int ticket_count=1;
    People[] user=new People[100];
    Flight[] fly=new Flight[100];
    Ticket[] tick=new Ticket[100];
    Admin admin=new Admin();

    public void enter(){
        System.out.println("Do you have log-in details (yes/no): ");
        String s=p.nextLine();
        switch (s){
            case "yes":
                existing_user();
                break;
            case "no":
                new_user();
                break;
            case "Admin":
            {
                Admin admin=new Admin();
                System.out.println("Enter the password: ");
                String s1=p.nextLine();
                if(s1.equals(admin.pass))
                    admin.admin_options();
                else {
                    System.out.println("Wrong password");
                    enter();
                }
            }
            break;
            default: {
                System.out.println("Make sure you type yes or no at the input field");
                enter();
            }
            break;
        }
    }
    public void new_user(){
        try {
            System.out.println("Enter your name: ");
            String name = p.nextLine();
            System.out.println("Enter your phone number: ");
            String num = p.nextLine();
            System.out.println("Enter your mail ID: ");
            String mail = p.nextLine();
            System.out.println("Enter your new password: ");
            String pass1 = p.nextLine();
            System.out.println("Confirm your password: ");
            String pass2 = p.nextLine();
            if (pass1.equals(pass2)) {
                System.out.println(name + " user ID is " + ID);
                user[ID] = new People();
                current_id = ID;
                if (user[ID].add(name, num, mail, pass1, ID)) {
                    ID++;
                    user_opt(current_id);
                }
            } else {
                System.out.println("Enter your new password: ");
                pass1 = p.nextLine();
                System.out.println("Confirm your password: ");
                pass2 = p.nextLine();
                if (pass1.equals(pass2)) {
                    System.out.println(name + " user ID is " + ID);
                    current_id = ID;
                    user[ID] = new People();
                    if (user[ID].add(name, num, mail, pass1, ID)) {
                        user_opt(current_id);
                        ID++;
                    }
                } else
                    new_user();
            }
        }
        catch (InputMismatchException E){
            System.out.println("Use correct format to enter your choice.");
            new_user();
        }
    }
    public void existing_user(){
        try {
            System.out.println("Enter your user_ID: ");
            int k = p.nextInt();
            p.nextLine();
            System.out.println("Enter the password " + user[k].getName());
            String password = p.nextLine();
            if (password.equals(user[k].getPass()))
                user_opt(k);
            else
                existing_user();
        }
        catch (InputMismatchException E){
            //System.out.println(E.getMessage());
            System.out.println("Make sure you enter number input where it needed and word input where-ever needed.");
            p.nextLine();
            existing_user();
        }
        catch (NullPointerException E){
            System.out.println("User_ID cannot be found try to create a new account");
            enter();
        }
    }
    public void user_opt(int id){
        try {
            System.out.println("Enter the options you like to continue:");
            System.out.println("1.Book Flight 2.Cancel Flight 3.View Flight 4.Booked Tickets 5. Total amount spent on tickets 6.Log out");
            int choice = p.nextInt();
            switch (choice) {
                case 1:
                    book_flight(id);
                    break;
                case 2: {
                    cancel_flight(id);
                    user_opt(id);
                }
                break;
                case 3: {
                    view_flight();
                    user_opt(id);
                }
                break;
                case 4: {
                    try {
                        user[id].view_ticket(tick, user[id].getName());
                        user_opt(id);
                    }
                    catch (NullPointerException E) {
                        System.out.println("Something have gone wrong contact admin.");
                        enter();
                    }
                }
                break;
                case 5:
                {
                    System.out.println("Total amount spent on tickets is: "+user[id].getSpend());
                    user_opt(id);
                }
                break;
                case 6: {
                    p.nextLine();
                    enter();
                }
                break;
                default: {
                    System.out.println("Wrong option");
                    user_opt(id);
                }
                break;
            }
        }
        catch(InputMismatchException E){
           // System.out.println(E.getMessage());
            System.out.println("Input number if you already tried word else vice-versa.");
            user_opt(id);
        }
    }
    public void book_flight(int user_id){
        try {
            System.out.println("The flights available are: ");
            view_flight();
            System.out.println("1.Continue 2.Cancel");
            int k = p.nextInt();
            switch (k) {
                case 1: {
                    System.out.println("Enter the Flight ID to book: ");
                    int f = p.nextInt();
                    System.out.println("Enter the number of tickets to be booked: ");
                    int q = p.nextInt();
                    if (fly[f].available >= q) {
                        p.nextLine();
                        System.out.println("conform ticket booking by entering yes, else enter no: ");
                        String g = p.nextLine();
                        System.out.println(user_id);
                        if (g.equals("yes") || g.equals("Yes")) {
                            fly[f].booked = fly[f].booked + q;
                            fly[f].available = fly[f].available - q;
                            System.out.println("The amount to be paid is : " + (q * fly[f].price));
                            user[user_id].tickets(ticket_count,(q * fly[f].price));
                            add_ticket(user[user_id].getName(), user[user_id].getPhone(), user[user_id].getMail(), fly[f].brand, fly[f].st_pt, fly[f].dt_pt, fly[f].price, fly[f].time_ar, fly[f].time_dep, q, f);
                            user_opt(user_id);
                        } else
                            book_flight(user_id);
                    }
                }
                break;
                case 2:
                    user_opt(user_id);
                    break;
                default: {
                    System.out.println("Wrong option");
                    book_flight(user_id);
                }
                break;
            }
        }
        catch (InputMismatchException E){
            //System.out.println(E.getMessage());
            System.out.println("Make sure you enter correct format of data.");
            book_flight(user_id);
        }
        catch (NullPointerException E){
            System.out.println("Something unexpected have happened contact admin.");
            enter();
        }
    }
    public void view_flight(){
        if(FD<2)
            System.out.println("No flights available right now");
        else {
            try {
                System.out.println("The available FLights are: ");
                for (int i = 1; i < FD; i++) {
                    fly[i].show();
                }
            }
            catch (NullPointerException E){
                System.out.println("Something unexpected have happened contact admin.");
                enter();
            }
        }
    }
    public void view_tickets_admin(){
        if(ticket_count<2)
            System.out.println("No tickets have been booked.");
        else{
            try {
                System.out.println("The tickets booked are: ");
                for (int i = 1; i < ticket_count; i++) {
                    tick[i].admin_display(i);
                }
            }
            catch (NullPointerException E){
                System.out.println("Something unexpected have happened contact admin.");
                enter();
            }
        }
    }
    public void add(String brand,int cap,int book,String st,String dt,double td,double ta,double pri){
        fly[FD]=new Flight();
        fly[FD].brand=brand;
        fly[FD].capacity=cap;
        fly[FD].booked=book;
        fly[FD].st_pt=st;
        fly[FD].dt_pt=dt;
        fly[FD].time_dep=td;
        fly[FD].time_ar=ta;
        fly[FD].price=pri;
        fly[FD].f_id=FD;
        fly[FD].available=cap-book;
        System.out.println("Flight added with flight ID "+FD);
        FD++;
    }
    public void modify(int flight_id){
        try {
            if (fly[flight_id] != null) {
                System.out.println("Enter which parameter to change: ");
                System.out.println("1.Flight name 2.Price 3.time 4.cancel");
                int x = p.nextInt();
                switch (x) {
                    case 1: {
                        p.nextLine();
                        System.out.println("Enter the new name for flight " + fly[flight_id].brand);
                        String g = p.nextLine();
                        fly[flight_id].brand = g;
                        System.out.println("Brand updated successfully to " + fly[flight_id].brand);
                        admin.admin_options();
                    }
                    break;
                    case 2: {
                        System.out.println("Enter the new price of flight " + fly[flight_id].brand + " The old price is " + fly[flight_id].price);
                        double pri = p.nextDouble();
                        fly[flight_id].price = pri;
                        System.out.println("Price updated successfully to " + fly[flight_id].price);
                        admin.admin_options();
                    }
                    break;
                    case 3: {
                        System.out.println("The plane starts at: " + fly[flight_id].time_dep);
                        System.out.println("The plane lands at: " + fly[flight_id].time_ar);
                        System.out.println("Enter the start time: ");
                        double s = p.nextDouble();
                        fly[flight_id].time_dep = s;
                        System.out.println("Enter the landing time: ");
                        double d = p.nextDouble();
                        fly[flight_id].time_ar = d;
                        System.out.println("The plane new starts at: " + fly[flight_id].time_dep);
                        System.out.println("The plane new lands at: " + fly[flight_id].time_ar);
                        admin.admin_options();
                    }
                    break;
                    case 4:
                        admin.admin_options();
                        break;
                    default: {
                        System.out.println("Wrong option");
                        modify(flight_id);
                    }
                    break;
                }
            } else {
                System.out.println("Flight data not found.");
                admin.admin_options();
            }
        }
        catch(InputMismatchException E){
            //System.out.println(E.getMessage());
            System.out.println("Enter respective data type in respective fields.");
            modify(flight_id);
        }
        catch (NullPointerException E){
            System.out.println("Something unexpected have happened contact admin.");
            enter();
        }
    }
    public void cancel_flight(int id){
        user[id].view_ticket(tick,user[id].getName());
        try {
            System.out.println("Enter the ticket id to cancel: ");
            int ticket_id = p.nextInt();
            int q = tick[ticket_id].quantity;
            fly[tick[ticket_id].flight_num].available = fly[tick[ticket_id].flight_num].available + q;
            fly[tick[ticket_id].flight_num].booked = fly[tick[ticket_id].flight_num].booked - q;
            tick[ticket_id].quantity = 0;
            user[id].setSpend(q*fly[tick[ticket_id].flight_num].price);
            System.out.println("Ticket cancelled");
        }
        catch (InputMismatchException E){
            System.out.println(E.getMessage());
        }
        catch (NullPointerException E){
            System.out.println("Something unexpected have happened contact admin.");
            enter();
        }
    }
    public void add_ticket(String user_name,String ph_no,String mail,String flight_name,String start_point,String end_point,double amount,double time_end,double time_start,int quantity,int fl_no){
        tick[ticket_count]=new Ticket();
        tick[ticket_count].user_name=user_name;
        tick[ticket_count].ph_no=ph_no;
        tick[ticket_count].mail=mail;
        tick[ticket_count].flight_num=fl_no;
        tick[ticket_count].flight_name=flight_name;
        tick[ticket_count].start_point=start_point;
        tick[ticket_count].end_point=end_point;
        tick[ticket_count].amount=amount;
        tick[ticket_count].time_start=time_start;
        tick[ticket_count].time_end=time_end;
        tick[ticket_count].quantity=quantity;
        System.out.println("Ticket alotted with ID: "+ticket_count);
        ticket_count++;

    }
}
