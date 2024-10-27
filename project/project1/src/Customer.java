public class Customer {
        private String name;
        private String email;
        private String address;
        private String phoneNumber;

        public Customer(String name, String email, String address, String phoneNumber) {
            this.name = name;
            this.email = email;
            this.address = address;
            this.phoneNumber = phoneNumber;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getAddress() {
            return address;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String setName(String name) {
            this.name = name;
            return name;
        }

        public String setEmail(String email) {
            this.email = email;
            return email;
        }

        public String setAddress(String address) {
            this.address = address;
            return address;
        }

        public String setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return phoneNumber;
        }

        private static int customerIdCounter = 0;
        private int customerId;

        public Customer() {
            this.customerId = generateCustomerId();
        }

        private static int generateCustomerId() {
            return ++customerIdCounter;
        }

        public int getCustomerId() {
            return customerId;

        }
    }
