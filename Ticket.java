public class Ticket {
    double amount;
    int quantity;
    int flight_num;
    String user_name;
    String ph_no;
    String mail;
    String flight_name;
    double time_start;
    double time_end;
    String start_point;
    String end_point;
    public void display(int id){
        System.out.println("Ticket ID:  "+id+"   No.Of.Tickets Booked: "+quantity);
        System.out.println("Passenger name: "+user_name+" Phone number: "+ph_no);
        System.out.println("Airline Name: "+flight_name);
        System.out.println("From : "+start_point+" To :"+end_point);
        System.out.println(" Start time: "+time_start+" End time: "+time_end);
        System.out.println("Price paid per ticket: "+amount);
        System.out.println("Total price paid: "+(amount*quantity));
        System.out.println("Mail ID: "+mail);
    }
    public void admin_display(int id){
        System.out.println("Ticket ID:  "+id+"   No.Of.Tickets Booked: "+quantity);
        System.out.println("Passenger name: "+user_name);
        System.out.println("Airline Name: "+flight_name);
        System.out.println("From : "+start_point+" To :"+end_point);
        System.out.println(" Start time: "+time_start+" End time: "+time_end);
        System.out.println("Price paid per ticket: "+amount);
        System.out.println("Total price paid: "+(amount*quantity));
        System.out.println("Mail ID: "+mail);
    }
}

