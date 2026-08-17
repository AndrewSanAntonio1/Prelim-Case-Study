import java.io.*;
import java.util.*;
public class Main {
    // Declaring and initilizing a variable to value
    // I applied DRY (Dont repeat your self);
    static Scanner scanner = new Scanner(System.in);
    static String[] firstname = {"Roel", "Dorie","Railee","Railynne","Raine"};
    static String[] lastname = {"Richard","Marie","Darrel","Dessirei","Dessirei"};
    static String[] accountNumbers = {"0123-4567-8901", "2345-6789-0123", "3456-7890-1234","4567-8901-2345","5678-9012-3456"};
    static String[] accountNames = {firstname[0] + lastname[0], firstname[1] + lastname[1], firstname[2] + lastname[2], firstname[3] + lastname[3], firstname[4] + lastname[4]};
    static double[] accountBalance = {5000,0,10000,2500,10000};
    static int[] pinNumber = {1111,2222,3333,4444,5555};
    static String name = "Richard";
    static char choice = ' ';
    static int lives = 3;
    static String accountNumber = "";
    static String pin = "";
    static int accountPin = 0;
    static char type = ' ';
    static double withdrawal = 0;
    static String fullname = "";
    static int currentAccountIndex = -1;
    static double deposit = 0;
    static double transferAmount = 0;
    static double transferReceived = 0;
    static String transferAccountNumber = "";
    public static void main(String[] args) {
        
        do {
            header(name);
            start();

            if (choice == 'Q' || choice == 'q') break;
            
            if (choice == 'S' || choice == 's') {
                boolean loggedIn = login();
                if (loggedIn) {
                    displayLogin();

                    typeOfTransaction();

                    while (type != 'C' && type != 'c') {

                        if (type == 'B' || type == 'b') {

                            displayBalance();

                        } else if (type == 'W' || type == 'w') {

                            if (withdraw()) {
                                displayWithdraw();
                            }

                        } else if (type == 'D' || type == 'd') {
                            if (deposit()) {
                                displayDeposit();
                            }

                        } else if (type == 'F' || type == 'f') {

                            if (transferFund()) {
                                displayTransfer();
                            }

                        } else {
                            System.out.println("Invalid transaction type.");
                        }

                        if (type != 'C' && type != 'c') {
                            typeOfTransaction();
                        }
                    }  
                }
            } else {
                System.out.println("Invalid choice!");
            }
            
        }while(choice != 'Q' && choice != 'q');
    }
    public static void header(String name) {
        String compliment = "";

        if (name.equals("Richard") || name.equals("Darrel")) {
            compliment = "Gwapo";
        } else {
            compliment = "Ganda";
        }
        System.out.println("==================================================");
        System.out.println("=\t\t\tRGBC\t\t\t =");
        System.out.printf("=\t%s %s Banking Corporation\t =\n",name,compliment);
        System.out.println("==================================================");
    }
    public static void start() {
        System.out.println("=\t\t\t\t\t\t =");
        System.out.println("=\t\tS -> Start Transaction\t\t =");
        System.out.println("=\t\tQ -> Quit\t\t\t =");
        System.out.println("=\t\t\t\t\t\t =");
        System.out.print("\t\tEnter your choice: ");
        try {
            choice = scanner.next().charAt(0);
            scanner.nextLine();

        } catch (NoSuchElementException e) {
            choice = 'Q';
        }

        System.out.println("==================================================\n");
    }
    public static boolean login() {
        lives = 3;
        currentAccountIndex = -1;

        while (lives > 0) {
            System.out.print("Enter your account number: ");
            accountNumber = scanner.nextLine();
            System.out.print("Enter your pin number: ");
            pin = scanner.nextLine();

            try {
                accountPin = Integer.parseInt(pin);

                for (int i = 0; i < accountNumbers.length; i++) {

                    if (accountNumber.equals(accountNumbers[i])
                            && accountPin == pinNumber[i]) {
                        currentAccountIndex = i;

                        System.out.println("Login Successfully!");
                        name = lastname[i];
                        fullname = accountNames[i];
                        return true;
                    }
                }

                lives--;
                System.out.println("Invalid account number or PIN.");
                System.out.println("Attempts remaining: " + lives);

            } catch (NumberFormatException e) {

                lives--;
                System.out.println("PIN must contain numbers only.");
                System.out.println("Attempts remaining: " + lives);
            }
        }

        System.out.println("Maximum attempts reached.");
        return false;
    }
    public static void displayLogin() {
        header(name);
        System.out.println("=\t\t\t\t\t\t =");
        System.out.println("=\t\t\tLogin\t\t\t =");
        System.out.println("=\t      Enter your account number:\t =");
        System.out.printf("=\t\t%s\t\t\t =\n",accountNumber);
        System.out.println("=\t      Enter your pin number:\t\t =");
        System.out.printf("=\t\t\t%s\t\t\t =\n",pin);
        System.out.println("=\t\t\t\t\t\t =");
        System.out.println("==================================================");                 
    }
    public static void typeOfTransaction() {
        header(name);

        System.out.println("=\t\t\t\t\t\t =");
        System.out.println("=\t\tSelect Type of Transaction\t =");
        System.out.println("=\t\t\t\t\t\t =");
        System.out.println("=\t\tB -> Balance Inquiry\t\t =");
        System.out.println("=\t\tW -> Withdrawal\t\t\t =");
        System.out.println("=\t\tD -> Deposit\t\t\t =");
        System.out.println("=\t\tF -> Transfer Fund\t\t =");
        System.out.println("=\t\tC -> Cancel\t\t\t =");
        System.out.println("=\t\t\t\t\t\t =");
        System.out.print("=\t\tEnter transaction type: \t =\n\t\t");

        try {
            type = scanner.next().charAt(0);
            scanner.nextLine();

        } catch (NoSuchElementException e) {
            type = 'C';
        }

        System.out.println("=\t\t\t\t\t\t =");
        System.out.println("==================================================\n");
    }
    

    public static boolean withdraw() {
        while (true) {
            try {
                System.out.println("\n=========================================\n");
                System.out.print("Enter withdrawal amount: ");
                withdrawal = Double.parseDouble(scanner.nextLine());
                System.out.println("\n=========================================\n");

                if (!Double.isFinite(withdrawal)) {
                    System.out.println("Invalid amount. Please enter a valid number.");
                    continue;
                }

                // Minimum withdrawal
                if (withdrawal < 100) {
                    System.out.println("\n=========================================\n");
                    System.out.println("Withdrawn amount should not be below 100 pesos.");
                    System.out.println("\n=========================================\n");
                    continue;
                }

                // Must be in 100 denominations
                if (withdrawal % 100 != 0) {
                    System.out.println("\n=========================================\n");
                    System.out.println("Invalid amount. Please enter an amount in 100 denominations.");
                    System.out.println("\n=========================================\n");
                    continue;
                }

                // Check balance
                if (withdrawal > accountBalance[currentAccountIndex]) {
                    System.out.println("\n=========================================\n");
                    System.out.println("Insufficient balance.");
                    System.out.println("\n=========================================\n");
                    continue;
                }

                // Deduct withdrawal
                accountBalance[currentAccountIndex] -= withdrawal;

                System.out.println("\n=========================================\n");
                System.out.println("Withdrawal successful!");
                System.out.println("Withdrawn amount: ₱" + withdrawal);
                System.out.println("\n=========================================\n");
                return true;

            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Please enter numbers only.\n");
            }
        }
    }

    public static void displayBalance() {
        header(name);

        System.out.println("=\t\t\t\t\t\t =");
        System.out.println("=\t\tBalance Inquiry\t\t\t =");
        System.out.println("=\t\t\t\t\t\t =");
        System.out.printf("=\t\tAccount #: \t%s   =\n", accountNumber);
        System.out.printf("=\t\tAccount Name: \t%s\t =\n", fullname);
        System.out.printf("=\t\tBalance:  \t%.2f\t =\n", accountBalance[currentAccountIndex]);
        System.out.println("=\t\t\t\t\t\t =");
        System.out.println("=\t\tPress X to Exit\t\t\t =");
        System.out.println("=\t\t\t\t\t\t =");
        System.out.println("==================================================");

        char press;

        while (true) {
            System.out.print("Press X to Exit: ");
            press = scanner.next().charAt(0);
            scanner.nextLine();

            if (press == 'X' || press == 'x') {
                return;
            }
            
            System.out.println("Invalid choice. Please press X to exit.");
        }
    }
    public static void displayWithdraw() {
        header(name);

        System.out.println("=\t\t\t\t\t\t =");
        System.out.println("=\t\tWITHDRAWAL\t\t\t =");
        System.out.println("=\t\t\t\t\t\t =");
        System.out.println("=\tEnter amount to be withdrawn\t\t =");
        System.out.printf("=\t\t\t%.0f\t\t\t =\n", withdrawal);
        System.out.println("=\t\t\t\t\t\t =");
        System.out.println("=\t\tPress X to Exit\t\t\t =");
        System.out.println("=\t\t\t\t\t\t =");
        System.out.println("==================================================");

        char press;

        while (true) {
            System.out.print("Press X to Exit: ");
            press = scanner.next().charAt(0);
            scanner.nextLine();

            if (press == 'X' || press == 'x') {
                return;
            }

            System.out.println("Invalid choice. Please press X to exit.");
        }
    }
    public static boolean deposit() {
        while (true) {
            try {
                System.out.print("Enter amount to be deposited: ");
                deposit = Double.parseDouble(scanner.nextLine());

                if (!Double.isFinite(deposit)) {
                    System.out.println("Invalid amount. Please enter a valid number.");
                    continue;
                }

                if (deposit < 100) {
                    System.out.println("Deposited amount should not be lower than 100 pesos.");
                    continue;
                }

                accountBalance[currentAccountIndex] += deposit;

                System.out.println("Deposit successful!");
                return true;

            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Please enter numbers only.");
            }
        }
    }
    public static void displayDeposit() {
        header(name);

        System.out.println("=\t\t\t\t\t\t =");
        System.out.println("=\t\t\tDEPOSIT\t\t\t =");
        System.out.println("=\t\t\t\t\t\t =");
        System.out.println("=\tEnter amount to be deposited\t\t =");
        System.out.printf("=\t\t%.2f\t\t\t\t =\n", deposit);
        System.out.println("=\t\t\t\t\t\t =");
        System.out.println("=\t\tPress X to Exit\t\t\t =");
        System.out.println("=\t\t\t\t\t\t =");
        System.out.println("==================================================");

        char press;

        while (true) {
            System.out.print("Press X to exit: ");
            press = scanner.next().charAt(0);
            scanner.nextLine();

            if (press == 'X' || press == 'x') {
                return;
            }

            System.out.println("Invalid choice. Please press X to exit.");
        }
    }
    public static boolean transferFund() {

        while (true) {

            System.out.print("Enter account number to transfer to: ");
            transferAccountNumber = scanner.nextLine();

            int recipientIndex = -1;

            // Check if account exists
            for (int i = 0; i < accountNumbers.length; i++) {
                if (transferAccountNumber.equals(accountNumbers[i])) {
                    recipientIndex = i;
                    break;
                }
            }

            if (recipientIndex == -1) {
                System.out.println("Account number does not exist.");
                continue;
            }

            // Don't allow transfer to own account
            if (recipientIndex == currentAccountIndex) {
                System.out.println("You cannot transfer funds to your own account.");
                continue;
            }

            try {
                System.out.print("Enter transfer amount: ");
                transferAmount = Double.parseDouble(scanner.nextLine());

                if (!Double.isFinite(transferAmount)) {
                    System.out.println("Invalid amount.");
                    continue;
                }

                // Minimum transfer amount
                if (transferAmount < 1000) {
                    System.out.println("Transfer amount must be 1000 pesos or higher.");
                    continue;
                }

                // Check if sender has enough balance
                if (transferAmount > accountBalance[currentAccountIndex]) {
                    System.out.println("Insufficient funds.");
                    continue;
                }

                // ₱25 fee for every ₱1000
                double fee = (transferAmount / 1000) * 25;

                // Recipient receives amount minus fee
                transferReceived = transferAmount - fee;

                // Deduct full transfer amount from sender
                accountBalance[currentAccountIndex] -= transferAmount;

                // Add received amount to recipient
                accountBalance[recipientIndex] += transferReceived;

                System.out.println("Fund transfer successful!");
                return true;

            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Please enter numbers only.");
            }
        }
    }
    public static void displayTransfer() {
        header(name);

        System.out.println("=\t\t\t\t\t\t =");
        System.out.println("=\t\tFUND TRANSFER\t\t\t =");
        System.out.println("=\t\t\t\t\t\t =");
        System.out.println("=\tTransfer to <enter account #>:\t\t =");
        System.out.printf("=\t\t%s\t\t\t =\n", transferAccountNumber);
        System.out.println("=\t\tAmount:\t\t\t\t =");
        System.out.printf("=\t\t%.0f\t\t\t\t =\n", transferAmount);
        System.out.println("=\t\t\t\t\t\t =");
        System.out.println("=\t\tPress X to Exit\t\t\t =");
        System.out.println("=\t\t\t\t\t\t =");
        System.out.println("==================================================");

        char press;

        while (true) {
            System.out.print("Press X to Exit: ");
            press = scanner.next().charAt(0);
            scanner.nextLine();

            if (press == 'X' || press == 'x') {
                return;
            }

            System.out.println("Invalid choice. Please press X to exit.");
        }
    }
}
