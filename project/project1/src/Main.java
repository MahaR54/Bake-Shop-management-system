import java.util.Scanner;

class Main{
    public static void main(String[] args) {
        BakeShop bakeShop = new BakeShop();
        bakeShop.start();
        Scanner scanner = new Scanner(System.in);
        Customer customer = new Customer();
        System.out.println("Customer's ID:");
        customer.getCustomerId();
        System.out.print("Name:");
        String name= scanner.nextLine();
        System.out.print("Address:");
        String address= scanner.nextLine();
        System.out.print("Email:");
        String email= scanner.nextLine();
        System.out.print("Phone number:");
        String phno= scanner.nextLine();
        customer.setAddress(name);
        customer.setEmail(email);
        customer.setName(name);
        customer.setPhoneNumber(phno);
    }
}
