import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

class BakeShop {
    private LinkedList<Cake> cakes;
    private LinkedList<String> pastries;
    private LinkedList<Cookie> cookies;
    private Queue<Receipt> orderQueue;
    private int customerIdCounter;

    public BakeShop() {
        cakes = new LinkedList<>();
        pastries = new LinkedList<>();
        cookies = new LinkedList<>();
        orderQueue = new LinkedList<>();
        customerIdCounter = 1;
    }

    public void addCake(Cake cake) {
        cakes.add(cake);
    }

    public void addPastry(String pastry) {
        pastries.add(pastry);
    }

    public void addCookie(Cookie cookie) {
        cookies.add(cookie);
    }

    public void enqueueOrder(Receipt receipt) {
        orderQueue.offer(receipt);
    }

    public int generateCustomerId() {
        return customerIdCounter++;
    }

    public void displayMenu() {
        System.out.println("Welcome to Maha's Bakery");
        System.out.println("Menu Options:");
        System.out.println("\n1. Make your Cake");
        System.out.println("2. Homemade Pastries");
        System.out.println("3. Fresh Cookies");
        System.out.println("4. Display Cart and Order receipt");
        System.out.println("5. Next Order");
        System.out.println("6. Do you want to remove an order");
        System.out.println("7. Exit\n");
    }

    public void customizeCake() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Customize your Cake to your own liking!");
        System.out.print("Enter size (small, medium, large): ");
        String size = scanner.nextLine();
        System.out.print("Enter flavor: ");
        String flavor = scanner.nextLine();
        System.out.print("Enter toppings (comma-separated): ");
        String toppingsString = scanner.nextLine();
        List<String> toppings = new LinkedList<>(List.of(toppingsString.split(",")));
        System.out.print("Enter fruits (comma-separated): ");
        String fruitsString = scanner.nextLine();
        List<String> fruits = new LinkedList<>(List.of(fruitsString.split(",")));

        Cake cake = new Cake(size, flavor, toppings, fruits);
        addCake(cake);

        System.out.println("\n-------YOUR CAKE HAS BEEN ADDED TO CART SUCCESSFULLY!-------");
        System.out.println();
    }

    public void customizePastries() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Customize Pastries");
        System.out.print("Pastry Flavor: ");
        String pastry = scanner.nextLine();

        addPastry(pastry);

        System.out.println("\n-------YOUR PASTRY HAS BEEN ADDED TO CART SUCCESSFULLY!-------");
        System.out.println();
    }

    public void customizeCookies() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your Preferred Cookie Details");
        System.out.print("Cookie Flavor: ");
        String cookieName = scanner.nextLine();
        System.out.print("Enter cookie quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Choose cookie type (1 for Sugar-Free, 2 for Normal): ");
        int typeChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        boolean sugarFree = typeChoice == 1;

        Cookie cookie = new Cookie(cookieName, quantity, sugarFree);
        addCookie(cookie);

        System.out.println("\n-------YOUR COOKIES HAVE BEEN ADDED TO CART SUCCESSFULLY!-------");
        System.out.println();
    }

    public void displayCart() {
        System.out.println("-----YOUR CURRENT ORDER-----");
        System.out.println("Cakes:");
        for (Cake cake : cakes) {
            System.out.println(cake);
        }
        System.out.println("\nPastries:");
        for (String pastry : pastries) {
            System.out.println(pastry);
        }
        System.out.println("\nCookies:");
        for (Cookie cookie : cookies) {
            System.out.println(cookie);
        }
        System.out.println("\n-----------------------------");
    }

    public void removeOrder() {  
        if (cakes.isEmpty() && pastries.isEmpty() && cookies.isEmpty()) {
            System.out.println("No items to remove from the order!");
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Select the item to remove from the order:");
            System.out.println("1. Cake");
            System.out.println("2. Pastry");
            System.out.println("3. Cookie");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    if (!cakes.isEmpty()) {
                        cakes.removeLast();
                        System.out.println("Last added cake removed from the order.");
                    } else {
                        System.out.println("No cakes in the order to remove!");
                    }
                    break;
                case 2:
                    if (!pastries.isEmpty()) {
                        pastries.removeLast();
                        System.out.println("Last added pastry removed from the order.");
                    } else {
                        System.out.println("No pastries in the order to remove!");
                    }
                    break;
                case 3:
                    if (!cookies.isEmpty()) {
                        cookies.removeLast();
                        System.out.println("Last added cookie removed from the order.");
                    } else {
                        System.out.println("No cookies in the order to remove!");
                    }
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    public void processOrders() {
        while (!orderQueue.isEmpty()) {
            Receipt receipt = orderQueue.poll();
            int customerId = receipt.getCustomerId();
            Date date = receipt.getOrderDate();
            double totalAmount = receipt.calculateTotalAmountWithTax();

            System.out.println("\nOrder Receipt for Customer ID: " + customerId);
            System.out.println("Order Date: " + date);
            System.out.println("Total Amount (with tax): $" + totalAmount);
            System.out.println("----------------------------------------");
        }
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            displayMenu();
            System.out.print("Enter your choice (1-7): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    customizeCake();
                    break;
                case 2:
                    customizePastries();
                    break;
                case 3:
                    customizeCookies();
                    break;
                case 4:
                    displayCart();
                    if (!cakes.isEmpty() || !pastries.isEmpty() || !cookies.isEmpty()) {
                        int customerId = generateCustomerId();
                        Receipt receipt = new Receipt(customerId, new Date(), cakes, pastries, cookies);
                        enqueueOrder(receipt);
                        System.out.println("Order placed successfully! Your Customer ID is: " + customerId);
                        cakes.clear();
                        pastries.clear();
                        cookies.clear();
                    }
                    break;
                case 5:
                    processOrders();
                    break;
                case 6:
                    removeOrder();
                    break;
                case 7:
                    System.out.println("Thank you for visiting Maha's Bakery! Have a great day!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    class Receipt {
        private int customerId;
        private Date orderDate;
        private List<Cake> cakes;
        private List<String> pastries;
        private List<Cookie> cookies;

        public Receipt(int customerId, Date orderDate, List<Cake> cakes, List<String> pastries, List<Cookie> cookies) {
            this.customerId = customerId;
            this.orderDate = orderDate;
            this.cakes = cakes;
            this.pastries = pastries;
            this.cookies = cookies;
        }

        public int getCustomerId() {
            return customerId;
        }

        public Date getOrderDate() {
            return orderDate;
        }

        public double calculateTotalAmountWithTax() {
            double totalAmount = calculateTotalAmount();
            double taxAmount = totalAmount * 0.1; // Assuming tax rate of 10%
            return totalAmount + taxAmount;
        }

        private double calculateTotalAmount() {
            double totalAmount = 0.0;
            for (Cake cake : cakes) {
                totalAmount += cake.getPrice();
            }
            for (String pastry : pastries) {
                // Assuming each pastry costs $2
                totalAmount += 400;
            }
            for (Cookie cookie : cookies) {
                totalAmount += cookie.getPrice();
            }
            return totalAmount;
        }
    }

    class Cake {
        private String size;
        private String flavor;
        private List<String> toppings;
        private List<String> fruits;

        public Cake(String size, String flavor, List<String> toppings, List<String> fruits) {
            this.size = size;
            this.flavor = flavor;
            this.toppings = toppings;
            this.fruits = fruits;
        }

        public double getPrice() {
            double basePrice;
            switch (size) {
                case "small":
                    basePrice = 500;
                    break;
                case "medium":
                    basePrice = 700;
                    break;
                case "large":
                    basePrice = 1000;
                    break;
                default:
                    basePrice = 0.0;
            }
            return basePrice + (toppings.size() * 100) + (fruits.size() * 205);
        }

        @Override
        public String toString() {
            return "Cake - Size: " + size + ", Flavor: " + flavor + ", Toppings: " + toppings + ", Fruits: " + fruits;
        }
    }

    class Cookie {
        private String name;
        private int quantity;
        private boolean sugarFree;

        public Cookie(String name, int quantity, boolean sugarFree) {
            this.name = name;
            this.quantity = quantity;
            this.sugarFree = sugarFree;
        }

        public double getPrice() {
            return (sugarFree ? 455 : 300) * quantity;
        }

        @Override
        public String toString() {
            return "Cookie - Name: " + name + ", Quantity: " + quantity + ", Sugar-Free: " + sugarFree;
        }
    }

}