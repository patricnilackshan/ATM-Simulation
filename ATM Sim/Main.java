import java.util.*;

// Enum for payment methods
enum PaymentMethod {
    CASH, CHEQUE, BANK_TRANSFER
}

// Enum for currencies
enum Currency {
    USD, EUR, GBP, LKR
}

// Client class with properties such as id, name, nationality, occupation, address, age, gender, bank accounts, pin, and currency.
class Client {
    private String id;
    private String name;
    private String nationality;
    private String occupation;
    private String address;
    private int age;
    private String gender;
    private List<BankAccount> bankAccounts;
    public String pin; // The client's PIN number
    private Currency currency;

    public Client(String id, String name, String nationality, String occupation, String address, int age, String gender, List<BankAccount> bankAccounts, String pin, Currency currency) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
        this.occupation = occupation;
        this.address = address;
        this.age = age;
        this.gender = gender;
        this.bankAccounts = bankAccounts;
        this.pin = pin;
        this.currency=currency;
    }

    // Getter methods for all properties
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public String getPin() {
        return pin;
    }

    public Currency getCurrency() {
        return currency;
    }
}

// Abstract class for bank accounts with common properties and methods
abstract class BankAccount {
    private String accountNumber;
    private Branch branch;
    protected double balance;
    private String accType;
    protected List<Transaction> transactions;

    public BankAccount(String accountNumber, Branch branch, double balance,String accType) {
        this.accountNumber = accountNumber;
        this.branch = branch;
        this.balance = balance;
        this.accType=accType;
        this.transactions = new ArrayList<Transaction>();
    }

    // Getter methods for all properties
    public String getAccountNumber() {
        return accountNumber;
    }

    public Branch getBranch() {
        return branch;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountType() {
        return accType;
    }

    // Abstract methods for deposit and withdrawal
    public abstract void deposit(double amount);

    public abstract void withdraw(double amount);
}

// Savings account class, a subclass of BankAccount
class SavingsAccount extends BankAccount {
    private double interestRate;
    private String accNo;

    public SavingsAccount(String accountNumber, Branch branch, double balance, double interestRate) {
        super(accountNumber, branch, balance,"Savings");
        this.interestRate = interestRate;
        this.accNo=accountNumber;
    }

    public double getInterestRate() {
        return interestRate;
    }

    // Method to deposit money into the account
    @Override
    public void deposit(double amount) {
        balance += amount;
        Transaction transaction = new Transaction(UUID.randomUUID().toString(), accNo, new Date(), TransactionStatus.SUCCESS, amount);
        transactions.add(transaction);
    }

    // Method to withdraw money from the account
    @Override
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            Transaction transaction = new Transaction(UUID.randomUUID().toString(), accNo, new Date(), TransactionStatus.SUCCESS, -amount);
            transactions.add(transaction);
        } else {
            System.out.println("Insufficient balance.");
            Transaction transaction = new Transaction(UUID.randomUUID().toString(), accNo, new Date(), TransactionStatus.FAILURE, 0);
            transactions.add(transaction);
        }
    }
}

// Current account class, a subclass of BankAccount
class CurrentAccount extends BankAccount {
    private String accNo;
    public CurrentAccount(String accountNumber, Branch branch, double balance) {
        super(accountNumber, branch, balance,"Current");
        this.accNo=accountNumber;
    }

    // Method to deposit money into the account
    @Override
    public void deposit(double amount) {
        balance += amount;
        Transaction transaction = new Transaction(UUID.randomUUID().toString(), accNo, new Date(), TransactionStatus.SUCCESS, amount);
        transactions.add(transaction);
    }

    // Method to withdraw money from the account
    @Override
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            Transaction transaction = new Transaction(UUID.randomUUID().toString(), accNo, new Date(), TransactionStatus.SUCCESS, -amount);
            transactions.add(transaction);
        } else {
            System.out.println("Insufficient balance.");
            Transaction transaction = new Transaction(UUID.randomUUID().toString(), accNo, new Date(), TransactionStatus.FAILURE, 0);
            transactions.add(transaction);
        }
    }
}

class Loan {
    private double amount;
    private double interestRate;
    private int duration;
    private PaymentMethod paymentMethod;
    private BankAccount bankAccount;

    public Loan(double amount, double interestRate, int duration, PaymentMethod paymentMethod, BankAccount bankAccount) {
        this.amount = amount;
        this.interestRate = interestRate;
        this.duration = duration;
        this.paymentMethod = paymentMethod;
        this.bankAccount = bankAccount;
    }

    public double getAmount() {
        return amount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public int getDuration() {
        return duration;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }
}

class Branch {
    private String name;
    private String address;

    public Branch(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}

enum TransactionStatus {
    SUCCESS, FAILURE, CANCELLATION
}

class Transaction {
    private String id;
    private String bankAccountId;
    private Date date;
    private TransactionStatus status;
    private double amount;

    public Transaction(String id, String bankAccountId, Date date, TransactionStatus status, double amount) {
        this.id = id;
        this.bankAccountId = bankAccountId;
        this.date = date;
        this.status= status;
        this.amount = amount;
    }
    public String getId() {
        return id;
    }
    
    public String getBankAccountId() {
        return bankAccountId;
    }
    
    public Date getDate() {
        return date;
    }
    
    public TransactionStatus getStatus() {
        return status;
    }
    
    public double getAmount() {
        return amount;
    }
}

//ATM Simulation
class ATM {
    public Map<String, Client> clients;
    
    public ATM(Map<String, Client> clients) {
        this.clients = clients;
    }
    
    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome!");
        System.out.println("Please enter your Client ID:");
        String id = scanner.nextLine();
        Client client = clients.get(id);
        if (client == null) {
            System.out.println("Invalid Client ID.");
            return;
        }
        System.out.println("Welcome "+client.getName());
        System.out.println("Please enter your PIN:");
        String pin = scanner.nextLine();
        if (client.pin==pin.intern()){
            List<BankAccount> accounts = client.getBankAccounts();
            System.out.println("Select an account to proceed:");
            for (int i = 0; i < accounts.size(); i++) {
                BankAccount account = accounts.get(i);
                System.out.println((i+1) + ". " + account.getAccountNumber() + " (" + client.getCurrency() + ")"+" "+account.getAccountType());
            }
            int accountIndex = scanner.nextInt() - 1;
            scanner.nextLine();
            BankAccount account = accounts.get(accountIndex);
            boolean exit = false;
            while (!exit) {
                System.out.println("Main Menu:");
                System.out.println("1. View Balance");
                System.out.println("2. Withdraw Money");
                System.out.println("3. Deposit Money");
                System.out.println("4. Exit");
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        System.out.println("Balance: " + account.getBalance() + " " + client.getCurrency());
                        break;
                    case 2:
                        System.out.println("Enter amount to withdraw:");
                        double withdrawAmount = scanner.nextDouble();
                        scanner.nextLine();
                        double temp_balance=account.getBalance();
                        account.withdraw(withdrawAmount);
                        if (temp_balance==account.getBalance()){
                            System.out.println(withdrawAmount+" "+client.getCurrency()+" withdrawal failed.");
                            System.out.println("Balance: " + account.getBalance() + " " + client.getCurrency());
                        }
                        else{
                            System.out.println(withdrawAmount+" "+client.getCurrency()+" withdrawn successfully.");
                            System.out.println("Balance: " + account.getBalance() + " " + client.getCurrency());
                        }
                        break;
                    case 3:
                        System.out.println("Enter amount to deposit:");
                        double depositAmount = scanner.nextDouble();
                        scanner.nextLine();
                        account.deposit(depositAmount);
                        System.out.println(depositAmount+" "+client.getCurrency()+" deposited successfully.");
                        System.out.println("Balance: " + account.getBalance() + " " + client.getCurrency());
                        break;
                    case 4:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        }
        else{
            System.out.println("Invalid PIN");
        }
    }
}

public class Main{
    public static void main(String[] args) {
        //Declaring users, accounts
        Map<String, Client> clients = new HashMap<>();
        List<BankAccount> client1Accounts = new ArrayList<>();
        clients.put("1111", new Client("1111", "Patric", "LK", "Engineer", "Address1", 22, "Male", client1Accounts, "1234",Currency.LKR));
        client1Accounts.add(new SavingsAccount("123456", new Branch("Branch1", "Address2"), 5000, 0.07));
        client1Accounts.add(new CurrentAccount("654321", new Branch("Branch2", "Address3"), 500));
        List<BankAccount> client2Accounts = new ArrayList<>();
        clients.put("2222", new Client("2222", "Nilackshan", "US", "Scientist", "Address4", 35, "Male", client2Accounts, "1234",Currency.USD));
        client2Accounts.add(new SavingsAccount("789012", new Branch("Branch3", "Address5"), 2000, 0.06));
        ATM atm = new ATM(clients);
        atm.start();
    }
}

/*In the  real world ATM, we do not need to enter the Client ID as it will automatically process by the chip.
**But here in this code, we are explicitly asking the Client ID.
**#################
**Client 1
**Client ID : 1111 
**PIN       : 1234
**#################
**Client ID : 2222 
**PIN       : 1234
*/