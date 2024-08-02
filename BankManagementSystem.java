import java.util.*;
abstract class Account
{
    protected int accountNumber;
    protected String accountHolder;
    protected double balance;

    public Account(int accountNumber, String accountHolder, double balance)
    {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public int getAccountNumber()
    {
        return accountNumber;
    }

    public String getAccountHolder()
    {
        return accountHolder;
    }

    public double getBalance()
    {
        return balance;
    }

    public void deposit(double amount)
    {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited INR" + amount + " into account " + accountNumber);
        }
        else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public boolean withdraw(double amount)
    {
        if (amount > 0 && amount <= balance)
        {
            balance -= amount;
            System.out.println("Withdrawn INR" + amount + " from account " + accountNumber);
            return true;
        }
        else
        {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
            return false;
        }
    }

    public abstract void displayAccountInfo();
}

class SavingsAccount extends Account
{
    private double interestRate;

    public SavingsAccount(int accountNumber, String accountHolder, double balance, double interestRate)
    {
        super(accountNumber, accountHolder, balance);
        this.interestRate = interestRate;
    }

    @Override
    public void displayAccountInfo()
    {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Type: Savings");
        System.out.println("Balance: INR" + balance);
        System.out.println("Interest Rate: " + interestRate);
    }
}

class CurrentAccount extends Account
{
    private double overdraftLimit;

    public CurrentAccount(int accountNumber, String accountHolder, double balance, double overdraftLimit)
    {
        super(accountNumber, accountHolder, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public boolean withdraw(double amount)
    {
        if (amount > 0 && (balance + overdraftLimit) >= amount)
        {
            balance -= amount;
            System.out.println("Withdrawn INR" + amount + " from account " + accountNumber);
            return true;
        }
        else
        {
            System.out.println("Invalid withdrawal amount or overdraft limit exceeded.");
            return false;
        }
    }

    @Override
    public void displayAccountInfo()
    {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Type: Current");
        System.out.println("Balance: INR" + balance);
        System.out.println("Overdraft Limit: INR" + overdraftLimit);
    }
}

abstract class InvestmentAccount extends Account
{
    public InvestmentAccount(int accountNumber, String accountHolder, double balance)
    {
        super(accountNumber, accountHolder, balance);
    }

    public abstract double calculateInterest();
}

class FixedDeposit extends InvestmentAccount
{
    private double interestRate;
    private int maturityPeriod;

    public FixedDeposit(int accountNumber, String accountHolder, double balance, double interestRate, int maturityPeriod)
    {
        super(accountNumber, accountHolder, balance);
        this.interestRate = interestRate;
        this.maturityPeriod = maturityPeriod;
    }

    @Override
    public double calculateInterest()
    {
        return (balance * interestRate * maturityPeriod) / 12; // Assuming monthly interest calculation
    }

    @Override
    public void displayAccountInfo()
    {
        //super.displayAccountInfo();
        FixedDeposit Fd=new FixedDeposit(accountNumber, accountHolder, balance, interestRate, maturityPeriod);
        Fd.displayAccountInfo();
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Type: Fixed Deposit");
        System.out.println("Balance: INR " + balance);
        System.out.println("Interest Rate: " + interestRate);
        System.out.println("Maturity Period: " + maturityPeriod + " months");
        System.out.println("Interest Earned: INR" + calculateInterest());
    }
}

class SystematicInvestmentPlan extends InvestmentAccount
{
    private double returnRate;
    private int investmentPeriod;

    public SystematicInvestmentPlan(int accountNumber, String accountHolder, double balance, double returnRate, int investmentPeriod)
    {
        super(accountNumber, accountHolder, balance);
        this.returnRate = returnRate;
        this.investmentPeriod = investmentPeriod;
    }

    @Override
    public double calculateInterest()
    {
        return (balance * returnRate * investmentPeriod) / 12; // Assuming monthly interest calculation
    }

    @Override
    public void displayAccountInfo()
    {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Type: SIP");
        System.out.println("Balance: INR" + balance);
        System.out.println("Return Rate: " + returnRate);
        System.out.println("Investment Period: " + investmentPeriod + " months");
        System.out.println("Interest Earned: INR" + calculateInterest());
    }
}


class Investment extends InvestmentAccount
{
    private double returnRate;
    private int investmentPeriod;

    public Investment(int accountNumber, String accountHolder, double balance, double returnRate, int investmentPeriod)
    {
        super(accountNumber, accountHolder, balance);
        this.returnRate = returnRate;
        this.investmentPeriod = investmentPeriod;
    }

    @Override
    public double calculateInterest()
    {
        return (balance * returnRate * investmentPeriod) / 12; // Assuming monthly interest calculation
    }

    @Override
    public void displayAccountInfo()
    {
        Investment I=new Investment(accountNumber, accountHolder, balance, returnRate, investmentPeriod);
        I.displayAccountInfo();
        //super.displayAccountInfo();
        System.out.println("Account Type: SIP");
        System.out.println("Return Rate: " + returnRate);
        System.out.println("Investment Period: " + investmentPeriod + " months");
        System.out.println("Interest Earned: INR" + calculateInterest());
    }
} 

public class BankManagementSystem
{
    private static final String BANK_NAME = "SBI";
    private static final int MAX_CLIENTS = 5;
    private static Account[] accounts = new Account[MAX_CLIENTS];
    private static int accountNumberCounter = 1001;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
    {
        int choice;

        do
        {
            System.out.println("\nMenu:");
            System.out.println("1. Create Savings Account");
            System.out.println("2. Create Current Account");
            System.out.println("3. Create Fixed Deposit (FD)");
            System.out.println("4. Create Systematic Investment Plan (SIP)");
            System.out.println("5. Deposit");
            System.out.println("6. Withdraw");
            System.out.println("7. Display Account Info");
            System.out.println("8. Display Bank Info");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            choice = getInputFromUser();

            switch (choice)
            {
                case 1:
                    createAccount("Savings");
                    break;
                case 2:
                    createAccount("Current");
                    break;
                case 3:
                    createFD();
                    break;
                case 4:
                    createSIP();
                    break;
                case 5:
                    performTransaction("deposit");
                    break;
                case 6:
                    performTransaction("withdraw");
                    break;
                case 7:
                    displayAccountInfo();
                    break;
                case 8:
                    displayBankInfo();
                    break;
                case 9:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } 
        while (choice != 9);
    }

    private static int getInputFromUser() 
    {
        try 
        {
            return scanner.nextInt();
        } 
        catch (InputMismatchException e) 
        {
            System.out.println("Invalid input. Please enter a valid integer.");
            scanner.next(); // Clear the invalid input from the scanner
            return getInputFromUser(); // Recursively call to get valid input
        }
    }

    private static void createAccount(String accountType) 
    {
        try 
        {
            // Your existing code for creating accounts goes here
            if (accountNumberCounter - 1001 < MAX_CLIENTS)
            {
                int newAccountNumber = accountNumberCounter++;
                Account account = null;
                if ("Savings".equalsIgnoreCase(accountType))
                {
                    account = createSavingsAccount(newAccountNumber);
                }
                else if ("Current".equalsIgnoreCase(accountType))
                {
                    account = createCurrentAccount(newAccountNumber);
                }
                else
                {
                    System.out.println("Invalid account type.");
                    return;
                }
                accounts[newAccountNumber - 1001] = account;
                System.out.println(account.getAccountNumber() + " Account created with account number: " + newAccountNumber);
            }
            else
            {
                System.out.println("Maximum number of clients reached.");
            }
        } 
        catch (Exception e) 
        {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }


    private static Account createSavingsAccount(int accountNumber)
    {
        System.out.print("Enter account holder's name: ");
        String accountHolder = scanner.next();
        System.out.print("Enter initial balance in INR: ");
        double initialBalance = getInputFromUser();
        double interestRate = 0.03; // Set the interest rate to 3%
        return new SavingsAccount(accountNumber, accountHolder, initialBalance, interestRate);
    }

    private static Account createCurrentAccount(int accountNumber)
    {
        System.out.print("Enter account holder's name: ");
        String accountHolder = scanner.next();
        System.out.print("Enter initial balance in ₹: ");
        double initialBalance = getInputFromUser();
        System.out.print("Enter overdraft limit in ₹: ");
        double overdraftLimit = getInputFromUser();
        return new CurrentAccount(accountNumber, accountHolder, initialBalance, overdraftLimit);
    }

    private static void createFD()
    {
        if (accountNumberCounter - 1001 < MAX_CLIENTS)
        {
            int newAccountNumber = accountNumberCounter++;
            System.out.print("Enter account holder's name: ");
            String accountHolder = scanner.next();
            System.out.print("Enter initial balance in INR: ");
            double initialBalance = getInputFromUser();
            System.out.print("Enter FD interest rate: ");
            double interestRate = getInputFromUser();
            System.out.print("Enter FD maturity period (months): ");
            int maturityPeriod = getInputFromUser();
            Account account = new FixedDeposit(newAccountNumber, accountHolder, initialBalance, interestRate, maturityPeriod);
            accounts[newAccountNumber - 1001] = account;
            System.out.println(account.getAccountNumber() + " Fixed Deposit (FD) created with account number: " + newAccountNumber);
        }
        else
        {
            System.out.println("Maximum number of clients reached.");
        }
    }

    private static void createSIP()
    {
        if (accountNumberCounter - 1001 < MAX_CLIENTS)
        {
            int newAccountNumber = accountNumberCounter++;
            System.out.print("Enter account holder's name: ");
            String accountHolder = scanner.next();
            System.out.print("Enter initial balance in INR: ");
            double initialBalance = getInputFromUser();
            System.out.print("Enter SIP return rate: ");
            double returnRate = getInputFromUser();
            System.out.print("Enter SIP investment period (months): ");
            int investmentPeriod = getInputFromUser();
            Account account = new SystematicInvestmentPlan(newAccountNumber, accountHolder, initialBalance, returnRate, investmentPeriod);
            accounts[newAccountNumber - 1001] = account;
            System.out.println(account.getAccountNumber() + " Systematic Investment Plan (SIP) created with account number: " + newAccountNumber);
        }
        else
        {
            System.out.println("Maximum number of clients reached.");
        }
    }

    private static void performTransaction(String transactionType)
    {
        System.out.print("Enter account number: ");
        int accountNumber = getInputFromUser();
        System.out.print("Enter " + transactionType + " amount in ₹: ");
        double amount = getInputFromUser();

        if ("deposit".equalsIgnoreCase(transactionType))
        {
            deposit(accountNumber, amount);
        }
        else if ("withdraw".equalsIgnoreCase(transactionType))
        {
            withdraw(accountNumber, amount);
        }
        else
        {
            System.out.println("Invalid transaction type.");
        }
    }

    private static void deposit(int accountNumber, double amount) 
    {
        Account account = findAccount(accountNumber);
        if (account != null) 
        {
            try 
            {
                account.deposit(amount);
            } 
            catch (IllegalArgumentException e) 
            {
                System.out.println("Invalid deposit amount: " + e.getMessage());
            }
        } 
        else 
        {
            System.out.println("Account not found.");
        }
    }

    private static void withdraw(int accountNumber, double amount) 
    {
        Account account = findAccount(accountNumber);
        if (account != null) 
        {
            try 
            {
                account.withdraw(amount);
            } 
            catch (IllegalArgumentException e) 
            {
                System.out.println("Invalid withdrawal amount: " + e.getMessage());
            }
        } 
        else 
        {
            System.out.println("Account not found.");
        }
    }

    private static void displayAccountInfo() 
    {
        System.out.print("Enter account number: ");
        int accountNumber = getInputFromUser();
        Account account = findAccount(accountNumber);
        if (account != null) 
        {
            account.displayAccountInfo();
        } 
        else 
        {
            System.out.println("Account not found.");
        }
    }

    private static void displayBankInfo()
    {
        System.out.println("Bank Name: " + BANK_NAME);
        System.out.println("Number of Clients: " + (accountNumberCounter - 1001));
    }

    private static Account findAccount(int accountNumber)
    {
        if (accountNumber >= 1001 && accountNumber <= (accountNumberCounter - 1))
        {
            return accounts[accountNumber - 1001];
        }
        return null;
    }
}
