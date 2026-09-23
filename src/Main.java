import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Variables & Data Types
        String memberName = "";
        String memberID = "";
        String loanPurpose = "";

        double shareCapital = 0.0;
        double loanAmount = 0.0;
        double interestRate = 0.0;
        double monthlyPayment = 0.0;
        double outstandingBalance = 0.0;

        int loanTermMonths = 0;
        int monthsOverdue = 0;

        boolean isEligible = false;
        boolean isOverdue = false;
        boolean hasExistingLoan = false;

        // Cumulative Metrics for Summary Reports
        double totalLoansReleased = 0.0;
        double totalCollections = 0.0;

        int choice = 0;

        // Main Program Loop
        while (choice != 8) {
            System.out.println("\n==================================================");
            System.out.println("                   COOPFUND SYSTEM                ");
            System.out.println("  Cooperative Lending & Loan Management System    ");
            System.out.println("==================================================");
            System.out.println("1. Register Member & Share Capital");
            System.out.println("2. File Loan Application");
            System.out.println("3. Evaluate Loan Eligibility");
            System.out.println("4. Compute Interest & Amortization");
            System.out.println("5. Record Loan Payment");
            System.out.println("6. Flag Overdue Account & Apply Surcharge");
            System.out.println("7. View Summary Report");
            System.out.println("8. Exit System");
            System.out.println("==================================================");
            System.out.print("Enter your choice (1-8): ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Clear buffer
            } else {
                System.out.println("Invalid input! Please enter a number between 1 and 8.");
                scanner.nextLine(); // Clear buffer
                continue;
            }

            // Switch Control Structure
            switch (choice) {

                case 1:
                    // Process 1: Register a member and record share capital contribution
                    System.out.println("\n--- [1] REGISTER MEMBER ---");

                    System.out.print("Enter Member ID: ");
                    memberID = scanner.nextLine();

                    System.out.print("Enter Member Name: ");
                    memberName = scanner.nextLine();

                    System.out.print("Enter Initial Share Capital Contribution (PHP): ");
                    shareCapital = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.println("Member successfully registered!");
                    System.out.println("ID: " + memberID
                            + " | Name: " + memberName
                            + " | Share Capital: PHP " + shareCapital);
                    break;

                case 2:
                    // Process 2: File a new loan application
                    System.out.println("\n--- [2] FILE LOAN APPLICATION ---");

                    if (memberID.isEmpty()) {
                        System.out.println("Error: Please register a member first (Option 1).");
                        break;
                    }

                    if (hasExistingLoan && outstandingBalance > 0) {
                        System.out.println(
                                "Error: Member already has an existing active loan with balance PHP "
                                        + outstandingBalance);
                        break;
                    }

                    System.out.print("Enter Desired Loan Amount (PHP): ");
                    loanAmount = scanner.nextDouble();

                    System.out.print("Enter Loan Term in Months (e.g., 6, 12, 24): ");
                    loanTermMonths = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter Loan Purpose: ");
                    loanPurpose = scanner.nextLine();

                    System.out.println(
                            "Loan application filed successfully for " + memberName + ".");
                    break;

                case 3:
                    // Process 3: Evaluate loan eligibility
                    System.out.println("\n--- [3] EVALUATE LOAN ELIGIBILITY ---");

                    if (loanAmount <= 0) {
                        System.out.println(
                                "Error: Please file a loan application first (Option 2).");
                        break;
                    }

                    // Loan amount cannot exceed 3x the share capital
                    // and member cannot have an existing unpaid loan
                    if (loanAmount <= (shareCapital * 3) && !hasExistingLoan) {
                        isEligible = true;

                        System.out.println("Loan Eligibility: APPROVED");
                        System.out.println("Member: " + memberName);
                        System.out.println("Loan Amount: PHP " + loanAmount);
                        System.out.println("Maximum Allowed: PHP " + (shareCapital * 3));
                    } else {
                        isEligible = false;

                        System.out.println("Loan Eligibility: NOT APPROVED");

                        if (loanAmount > (shareCapital * 3)) {
                            System.out.println(
                                    "Reason: Loan amount exceeds 3x the share capital.");
                        }

                        if (hasExistingLoan) {
                            System.out.println(
                                    "Reason: Member has an existing unpaid loan.");
                        }
                    }
                    break;

                default:
                    if (choice < 1 || choice > 8) {
                        System.out.println("Invalid choice! Please enter 1-8.");
                    }
                    break;
            }
        }

        System.out.println("\nThank you for using COOPFUND SYSTEM!");
        scanner.close();
    }
}