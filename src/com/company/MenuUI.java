package com.company;
import java.io.IOException;
import java.util.Scanner;
public class MenuUI {
    public static void main(String[] args) throws IOException {

        System.out.println("\t" + "========" + "\t" + "==========" + "\t" + "======" + "\n");
        System.out.println("\t " + "EXPENSE" + "\t" + "MANAGEMENT" + "\t" + "SYSTEM" +"\n");
        System.out.println("\t" + "========" + "\t" + "==========" + "\t" + "======");

        Scanner input = new Scanner(System.in);
        int ch;
        char userChoice = 0;
        PassWord pass = new PassWord();

        if(pass.checkPassWord() == true	){
            do{
                System.out.print("\n Options available are: \n1. Make an Entry \n2. Get the expenses done for a particular date \n3. Get the log for a particular month \n4. Check Expense done for a particular month  \n5. Set budget \n6. Delete a particular month's log \n7. Change Password \n Enter your choice: ");
                ch = input.nextInt();
                Manager manage = new Manager();
                switch(ch)
                {
                    case 1:
                        manage.makeDailyLog();
                        break;
                    case 2:
                        manage.getDayExpenseDetails();
                        break;
                    case 3:
                        manage.getMonthExpenseDetails();
                        break;
                    case 4:
                        manage.displayMonthExpense();
                        break;
                    case 5:
                        manage.setBudget();
                        break;
                    case 6:
                        manage.deleteMonthLog();
                        break;
                    case 7:
                        pass.changePassWord();
                    default:
                        System.out.println("Enter a Correct choice...!!!!");
                }
                System.out.print("\nAnything Else?(y/n): ");
                userChoice  = input.next().charAt(0);
            }while(userChoice == 'y' || userChoice == 'Y' );
        }

       if(userChoice == 'n' || userChoice == 'N')
        {
            System.out.println("\t" + "========" + "\t" + "=========="+"\n");
            System.out.println("\t " + "THE" + "\t" + "END" +"\n");
            System.out.println("\t" + "========" + "\t" + "==========");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
       else if(userChoice != 'Y' || userChoice !='y' || userChoice !='N' || userChoice == 'n')
       {
           System.out.println("Enter a correct choice");
       }
        else
            System.out.print("The entered password is incorrect! ");
        input.close();


    }

}

