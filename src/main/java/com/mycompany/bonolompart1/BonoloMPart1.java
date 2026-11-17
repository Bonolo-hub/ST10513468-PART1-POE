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
            }else break;
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
            }else break;
        }
        System.out.println("Password successfully captured");
        
        while(!checkCellPhoneNumber(registeredNumber)){
            System.out.println("Please enter your cellphone number starts with (+27...): ");
            registeredNumber = myInput.nextLine();
            if(!checkCellPhoneNumber(registeredNumber)){
                System.out.println("Cell phone number incorrectly formatted or does not contain international code");
            }else break;
        }
        
        LoginClass myLogin = new LoginClass(registeredUserName, firstName, surName, registeredPassword, registeredNumber);
        System.out.println(myLogin.registerUser());
        
        System.out.println("Please login: ");
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
        
        if(!loginUser(registeredUserName, registeredPassword, userName, userPassword)){
            System.out.println("Login failed");
            myInput.close();
            return;
        }

        System.out.println("Welcome to QuickChat!");
        
        boolean mainRunning = true;
        while(mainRunning) {
            System.out.println("\n========== QUICKCHAT MAIN MENU ==========");
            System.out.println("1) Send Messages");
            System.out.println("2) Show Recently Sent Messages");
            System.out.println("3) Stored Messages Menu");
            System.out.println("4) Quit");
            System.out.print("Choose: ");
            int mainChoice = myInput.nextInt();
            myInput.nextLine();

            if(mainChoice == 1) {
                System.out.println("How many messages would you like to send?");
                int total = myInput.nextInt();
                myInput.nextLine(); 

                for(int i = 0; i < total; i++){
                    System.out.println("\n--- Message " + (i+1) + " of " + total + " ---");
                    System.out.print("Please enter a message ID (max 10 chars): ");
                    String id = myInput.nextLine();
                    System.out.print("Enter recipient cell (+27): ");
                    String recip = myInput.nextLine();
                    System.out.print("Enter your message (max 250 chars): ");
                    String msg = myInput.nextLine();
                    
                    MessageClass myMsg = new MessageClass(id, i+1, recip, msg);
                    
                    if(!myMsg.checkMessageID()){
                        System.out.println("Message ID is too long. Must be <=10 chars.");
                        i--; continue;
                    }
                    if(!myMsg.checkRecipientCell()){
                        System.out.println("Cell phone number incorrectly formatted.");
                        i--; continue;
                    }
                    if(msg.length() > 250){
                        System.out.println("Message exceeds 250 characters.");
                        i--; continue;
                    }
                    
                    System.out.println("\nMessage Details:");
                    System.out.println(myMsg.printMessage());
                    
                    System.out.println("What do you want to do?");
                    System.out.println("1) Send Message");
                    System.out.println("2) Disregard Message");
                    System.out.println("3) Store Message");
                    System.out.print("Choice: ");
                    int choice = myInput.nextInt();
                    myInput.nextLine();
                    
                    System.out.println(myMsg.SentMessage(choice));
                }
                System.out.println("\nTotal messages sent: " + MessageClass.returnTotalMessages());

            } else if(mainChoice == 2) {
                if(MessageClass.sentMessages.size() > 0){
                    System.out.println("\nAll sent messages:");
                    for(MessageClass m : MessageClass.sentMessages){
                        System.out.println(m.printMessages());
                        System.out.println("--------------");
                    }
                } else System.out.println("No messages were sent.");
            } else if(mainChoice == 3) {
                boolean reportMenu = true;
                while(reportMenu) {
                    System.out.println("\n===== STORED MESSAGES REPORT =====");
                    System.out.println("a) Display sender and recipient of all stored messages");
                    System.out.println("b) Display longest stored message");
                    System.out.println("c) Search for message ID");
                    System.out.println("d) Search for messages by recipient");
                    System.out.println("e) Delete message using hash");
                    System.out.println("f) Display full report");
                    System.out.println("g) Back to Main Menu");
                    System.out.print("Choice: ");
                    String c = myInput.nextLine().toLowerCase();
                    
                    switch(c) {
                        case "a": MessageManager.displaySenderAndRecipient(); break;
                        case "b": MessageManager.displayLongest(); break;
                        case "c": 
                            System.out.print("Enter Message ID: ");
                            String sid = myInput.nextLine();
                            MessageManager.searchByMessageID(sid); break;
                        case "d":
                            System.out.print("Enter Recipient (+27838884567): ");
                            String rec = myInput.nextLine();
                            MessageManager.searchByRecipient(rec); break;
                        case "e":
                            System.out.print("Enter Hash to delete: ");
                            String hash = myInput.nextLine();
                            MessageManager.deleteByHash(hash); break;
                        case "f": MessageManager.displayFullReport(); break;
                        case "g": reportMenu = false; break;
                        default: System.out.println("Invalid choice"); break;
                    }
                }
            } else if(mainChoice == 4) {
                mainRunning = false;
                System.out.println("Goodbye!");
            }
        }
        myInput.close();
    }
    
    public static boolean checkUserName(String s){ return s.length() <= 5 && s.contains("_"); }
    public static boolean checkPasswordComplexity(String s){
        if(s.length() < 8) return false;
        boolean cap=false,num=false,spec=false;
        for(char c : s.toCharArray()){
            if(Character.isUpperCase(c)) cap=true;
            else if(Character.isDigit(c)) num=true;
            else if(!Character.isLetterOrDigit(c)) spec=true;
        }
        return cap && num && spec;
    }
    public static boolean checkCellPhoneNumber(String s){ return s.startsWith("+27") && s.length() == 12; }
    public static boolean loginUser(String regU, String regP, String u, String p){ return regU.equals(u) && regP.equals(p); }
}