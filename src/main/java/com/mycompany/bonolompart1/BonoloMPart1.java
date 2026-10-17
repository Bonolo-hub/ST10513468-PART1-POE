/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bonolompart1;

/**
 *
 * @author males
 */

import java.util.Scanner;

public class BonoloMPart1 {

    public static void main(String[] args) {
        Scanner myInput = new Scanner(System.in);
        
        // ===== PART 1 - REGISTRATION =====
        String registeredUserName = "";
        String registeredPassword = "";
        String registeredNumber = "";
        String firstName = "";
        String surName = "";
        
        System.out.println("Please enter your first name: ");
        firstName = myInput.nextLine();
        System.out.println("Please enter your last name: ");
        surName = myInput.nextLine();
        
        while(!checkUserName(registeredUserName)){
            System.out.println("Please enter your username: ");
            registeredUserName = myInput.nextLine();
            if(!checkUserName(registeredUserName)){
                System.out.println("Username is not correctly formatted");
                System.out.println("Please ensure that your username: ");
                System.out.println("-contains an underscore");
                System.out.println("-is no more than five characters in length");
            }else{
                break;
            }
        }
        System.out.println("Username successfully captured");
        
        while(!checkPasswordComplexity(registeredPassword)){
            System.out.println("Please enter your password");
            registeredPassword = myInput.nextLine();
            if(!checkPasswordComplexity(registeredPassword)){
                System.out.println("Password is not correctly formatted");
                System.out.println("Please ensure that your password: ");
                System.out.println("-contains atleast eight characters");
                System.out.println("-contains a capital letter");
                System.out.println("-contains a number");
                System.out.println("-contains a special character");
            }else{
                break;
            }
        }
        System.out.println("Password successfully captured");
        
        while(!checkCellPhoneNumber(registeredNumber)){
            System.out.println("Please enter your cellphone number starts with (+27...): ");
            registeredNumber = myInput.nextLine();
            if(!checkCellPhoneNumber(registeredNumber)){
                System.out.println("Cell phone number incorrectly formatted or does not contain international code");
            }else{
                break;
            }
        }
        
        LoginClass myLogin = new LoginClass(registeredUserName, firstName, surName, registeredPassword, registeredNumber);
        System.out.println(myLogin.registerUser());
        
        // ===== LOGIN =====
        System.out.println("\n--- Please login: ---");
        System.out.println("Enter username: ");
        String userName = myInput.nextLine();
        System.out.println("Enter password: ");
        String userPassword = myInput.nextLine();

        if(!loginUser(registeredUserName, registeredPassword, userName, userPassword)){
            System.out.println("Username or password incorrect, please try again.");
            System.out.println("Enter username: ");
            userName = myInput.nextLine();
            System.out.println("Enter password: ");
            userPassword = myInput.nextLine();
        }
        
        System.out.println(myLogin.returnLoginStatus(registeredUserName, registeredPassword, userName, userPassword, firstName, surName));
        
        // Check if login failed - stop if failed
        if(!loginUser(registeredUserName, registeredPassword, userName, userPassword)){
            System.out.println("Login failed. Exiting.");
            myInput.close();
            return;
        }

        // ===== PART 2 - QUICKCHAT =====
        System.out.println("\nWelcome to QuickChat!");
        
        System.out.println("How many messages do you want to send?");
        int total = myInput.nextInt();
        myInput.nextLine(); // clear enter

        for(int i = 0; i < total; i++){
            System.out.println("\n--- Message " + (i+1) + " of " + total + " ---");
            
            System.out.print("Enter Message ID (max 10 chars, e.g. 0012345678): ");
            String id = myInput.nextLine();
            
            System.out.print("Enter recipient cell (+27... max 13 chars): ");
            String recip = myInput.nextLine();
            
            System.out.print("Enter your message (max 250 chars): ");
            String msg = myInput.nextLine();
            
            MessageClass myMsg = new MessageClass(id, i+1, recip, msg);
            
            if(!myMsg.checkMessageID()){
                System.out.println("Message ID is too long. Must be <=10 chars.");
                i--; 
                continue;
            }
            if(!myMsg.checkRecipientCell()){
                System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
                i--;
                continue;
            }
            if(msg.length() > 250){
                System.out.println("Please enter a message of less than 250 characters. Your message is " + (msg.length()-250) + " chars too long.");
                i--;
                continue;
            }
            
            System.out.println("\nMessage Details:");
            System.out.println(myMsg.printMessage());
            
            System.out.println("\nWhat do you want to do?");
            System.out.println("1) Send Message");
            System.out.println("2) Disregard Message");
            System.out.println("3) Store Message");
            System.out.print("Choice: ");
            int choice = myInput.nextInt();
            myInput.nextLine();
            
            System.out.println(myMsg.SentMessage(choice));
        }

        System.out.println("\n--- Summary ---");
        System.out.println("Total messages sent: " + MessageClass.returnTotalMessages());
        
        if(MessageClass.sentMessages.size() > 0){
            System.out.println("\nAll sent messages:");
            for(MessageClass m : MessageClass.sentMessages){
                System.out.println(m.printMessages());
                System.out.println("--------------");
            }
        } else {
            System.out.println("No messages were sent.");
        }
        
        myInput.close();
    }
    
    // Helper methods
    public static boolean checkUserName(String registeredUserName){
        if(registeredUserName.length() <= 5 && registeredUserName.contains("_")){
            return true;
        } else{
            return false;
        }
    }
    
    public static boolean checkPasswordComplexity(String registeredPassword){
        boolean containsCapitalLetter = false;
        boolean containsNumber = false;
        boolean containsSpecial = false;
        
        if(registeredPassword.length() < 8){
            return false;
        }

        for(int i = 0; i < registeredPassword.length(); i++){
            char character = registeredPassword.charAt(i);
            if(Character.isUpperCase(character)){
                containsCapitalLetter = true;
            }else if(Character.isDigit(character)){
                containsNumber = true;
            }else if(!Character.isLetterOrDigit(character)){
                containsSpecial = true;
            }
        }
        return containsCapitalLetter && containsNumber && containsSpecial;
    }
    
    public static boolean checkCellPhoneNumber(String registeredNumber){
        if(registeredNumber.startsWith("+27") && registeredNumber.length() == 12){
            return true;
        }else{
            return false;
        }
    }

    public static boolean loginUser(String registeredUserName, String registeredPassword, String userName, String userPassword){
        return registeredUserName.equals(userName) && registeredPassword.equals(userPassword);
    }
}
