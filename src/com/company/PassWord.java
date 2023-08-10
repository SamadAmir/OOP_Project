package com.company;
import java.io.IOException;
import java.util.Scanner;

    public class PassWord {

        FileOperation file = new FileOperation();
        Scanner input = new Scanner(System.in);

        public boolean checkPassWord() throws IOException{

            System.out.print("\nEnter the password: ");
            String pass = input.next();
            checkUntilPasswordIsCorrect(pass);

            return true;

        }

        public void changePassWord() throws IOException{

            System.out.print("\nEnter the old Password: ");
            String oldPass = input.next();

            if(checkUntilPasswordIsCorrect(oldPass)){
                System.out.print("\nEnter the new Password: ");
                String newPass = input.next();
                file.setPassWord(newPass);
                System.out.print("\nThe password has been changed successfully!");
            }
        }

        private String getPassWord() throws IOException{
            String pass = file.getPassWord();
            return pass;
        }

        private boolean checkUntilPasswordIsCorrect(String oldPass) throws IOException {
            // While loop will execute till the time the password entered is the old password
            while(oldPass.equals(getPassWord()) == false){
                System.out.print("The entered password is incorrect! \n\nRe-enter the password: ");
                oldPass = input.next();
                // if the password entered is equal to the old password from the file, then the if statement will break the loop.
                if(oldPass.equals(getPassWord()))
                    break;
            }
            return true;
        }
    }

