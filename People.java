public class People {
    private String name;
    private String mail;
    private String pass;
    private String phone;
    int user_id;
    private double spend;
    static int ticket=1;
    int[] my_ticket_num=new int[20];
    public boolean add(String name,String phone,String mail,String pass,int user_id){
        this.name=name;
        this.mail=mail;
        this.pass=pass;
        this.phone=phone;
        this.user_id=user_id;
        return true;
    }
    public String getName(){
        return name;
    }
    public String getPass(){
        return pass;
    }
    public String getPhone(){
        return phone;
    }
    public String getMail(){
        return mail;
    }
    public void tickets(int num,double price){
        my_ticket_num[ticket]=num;
        spend +=price;
        System.out.println("Tickets saved to my account.");
        ticket++;
    }
    public void view_ticket(Ticket[] t,String n){
        System.out.println("Tickets Booked by "+n);
        for(int i=1;i<ticket;i++)
            if(t[i].user_name.equals(n))
             t[i].display(i);
    }
    public double getSpend(){
        return spend;
    }
    public void setSpend(double a){
        if((spend-a)>0)
            spend-=a;
        else
            spend=0;
    }
}
