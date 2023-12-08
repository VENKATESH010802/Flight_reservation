public class Flight {
    String brand;
    int capacity;
    int booked;
    int available;
    String st_pt;
    String dt_pt;
    double time_dep;
    double time_ar;
    double price;
    int f_id;
    public void show(){
        System.out.println(f_id+"  "+brand+"    "+st_pt+"  "+time_dep+" ----> "+dt_pt+"  "+time_ar+"    ");
        System.out.println("Available seats "+available+"    "+"Ticket price "+price);
    }
}
