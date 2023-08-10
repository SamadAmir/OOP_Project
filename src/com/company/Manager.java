package com.company;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
class Manager extends CostOperation {

    Scanner input = new Scanner(System.in);
    CostOperation cost = new CostOperation();
    FileOperation file = new FileOperation();
    PassWord pass = new PassWord();

    public void makeDailyLog() throws IOException {
        try {
            Log log = new Log();
            System.out.print("\nEnter the Date (dd/mm/yyyy): ");
            log.setDate(input.next());
            System.out.print("\nEnter the item: ");
            input.nextLine();
            log.setItem(input.nextLine());
            System.out.print("\nEnter Cost: Rs");
            log.setCost(input.nextInt());
            enterLog(log);
        }
        catch (Exception e)
        {
            System.out.println("Please Enter the Correct Information!!!");
        }
        }

    private void enterLog(Log log) throws IOException {
        try {

            int budget = 0, expense = 0;
            String budgetBookData = file.getBudgetData(log.getDate());
            if (budgetBookData == null) {
                System.out.print("\nThe budget has not been set yet, please set the budget: ");
                System.out.print("\nThe budget for the month is Rs. ");
                budget = input.nextInt();
                file.updateLogBook(budget, 0, log.getDate(), "nil", 0);
            } else {
                budget = cost.getBudget(budgetBookData);
                expense = cost.getExpense(budgetBookData);

            }
            System.out.print("\nLog added Successfully!");
            if (cost.budgetCheck(budget, expense, log.getCost()))
                System.out.println("\n!!!Your monthly budget has exceeded, Please take care !!!");

            file.updateLogBook(budget, (expense + log.getCost()), log.getDate(), log.getItem(), log.getCost());
        } catch (Exception e) {
            System.out.println("Please Enter the Correct Format");
        }
    }

    public void displayMonthExpense() throws IOException {
        try {
            String budgetdata;
            System.out.print("Enter month and year (MM/YYYY)");
            String date = input.next();
            date = "00/".concat(date);
            budgetdata = file.getBudgetData(date);
            int expense = cost.getExpense(budgetdata);
            int budget = cost.getBudget(budgetdata);
            System.out.print("\nThe expense done till date is: Rs." + expense);
            if (budget > expense)
                System.out.print("\nThe amount that can yet be spent is: Rs." + (budget - expense));
            else
                System.out.print("\nThe amount exceeding your budget is: Rs." + (expense - budget));
        } catch (NullPointerException e) {
            System.out.println("Ohhh!!! Invalid date, Please check the date");
        }

    }

    public void getDayExpenseDetails() {
        try {
            System.out.print("\nEnter the Date (dd/mm/yyyy): ");
            String date = input.next();
            getDetails(date);
        } catch (Exception e) {
            System.out.println("!!!!Enter a valid date !!!!");
        }
    }

    private void getDetails(String date) {
        try {
            ArrayList<Log> list1 = file.getLog(date);
            int n = list1.size();
            if (n == 0)
                System.out.print("\nNo entry has been made for the date entered! Please Check the date");
            else {
                System.out.println("\nThe details of the expense done on " + date + " :");
                for (int i = 0; i < n; i++) {
                    Log log = list1.get(i);
                    System.out.println((i + 1) + " : " + "\nExpense Item: " + log.getItem() + "\nExpenditure: Rs." + log.getCost());
                }
            }
        } catch (Exception e) {
            System.out.println("!!!Enter the Correct details!!");
        }
    }

    public void setBudget()  {
        try {
            if (pass.checkPassWord()) {
                System.out.print("\nResetting the budget may create error in the calculations\nDo you still want to continue? (y/n): ");
                char ch = input.next().charAt(0);
                if (ch == 'y' || ch == 'Y') {
                    System.out.print("\nEnter the month and the year(MM/YYYY)");
                    String date = input.next();
                    System.out.print("\nEnter the budget: Rs.");
                    int budget = input.nextInt();
                    file.resetBudget(date, budget);
                    System.out.print("\nThe Budget has been Reset Successfully!");
                }
            } else
                System.out.print("Enter the correct Format !!");

        } catch (Exception e) {
            System.out.println("Please Enter the Correct Format !!!!!");
        }
    }
    public void getMonthExpenseDetails() throws NumberFormatException {
        try {
            System.out.print("\nEnter the month and the year (MM/YYYY): ");
            String date = input.next();
            ArrayList<Log> list1 = file.getMonthLog(date);
            int n = list1.size();
            if (n == 0)
                System.out.print("\nNo entry has been made for the date entered! Please Check the date");
            else {
                System.out.println("\nThe details of the expense done on " + date + " :");
                for (int i = 1; i < n; i++) {
                    Log log = list1.get(i);
                    System.out.println((i) + " : " + "\nDate: " + log.getDate() + " : " + "\nExpense Item: " + log.getItem() + "\nExpenditure: Rs." + log.getCost());
                }
            }
        } catch (Exception e) {
            System.out.println("!!Enter the correct details!!");
        }
    }

    public void deleteMonthLog() {
        try {

            System.out.print("Are you sure that you want to delete a whole month's log forever? (Y/N): ");
            char choice = input.next().charAt(0);
            if (choice == 'y' || choice == 'Y') {
                System.out.print("\nEnter the month and the year (MM/YYYY): ");
                String date = input.next();
                file.deleteLog(date);
            }
        } catch (Exception e) {
            System.out.println("!! PLease Enter the correct details");
        }

    }
}

