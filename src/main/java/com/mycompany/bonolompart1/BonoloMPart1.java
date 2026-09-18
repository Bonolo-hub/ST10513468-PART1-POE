/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bonolompart1;

import static com.mycompany.bonolompart1.LoginClass.loginUser;
import java.util.Scanner;

/**
 *
 * @author males
 */
public class BonoloMPart1 {

    public static void main(String[] args) {
        Scanner myInput = new Scanner(System.in);
        System.out.println("Hello World!");
        //Declarations of variables used to register
        String registeredUserName = "";
        String registeredPassword = "";
        String registeredNumber = "";
        String firstName = "";
        String surName = "";
        String userName = "";
        
        //Prompt the user to enter their first name and last name
        System.out.println("Please enter your first name: ");
        firstName = myInput.nextLine();
        System.out.println("Please enter your last name: ");
        surName = myInput.nextLine();
        
        //While loop for the Username
        while(!checkUserName(registeredUserName)){
            
            //Prompt the users
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
        //While loop for the Password
        while(!checkPasswordComplexity(registeredPassword)){
        
        //Prompt the user for the password
        System.out.println("Please enter your password");
        registeredPassword = myInput.nextLine();
        
        if(!checkPasswordComplexity(registeredPassword)){
        System.out.println("Password is not correctly formatted");
        System.out.println("Please ensure that your password: ");
        System.out.println("-contains atleast eight characters");
        System.out.println("-contains a capital letter");
        System.out.println("-contains a number");
        System.out.println("-contains a special characterr");
        }else{
        break;
        }
        }
        System.out.println("Password successfully captured");
        
        //While for Password
        while(!checkCellPhoneNumber(registeredNumber)){
            //Prompt the user
            System.out.println("Please enter your cellphone number starts with (+27...): ");
            registeredNumber = myInput.nextLine();

            if(!checkCellPhoneNumber(registeredNumber)){
                System.out.println("Cell phone number incorrectly formatted or does not contain international code");
            }else{
                break;
            }
        }
        
        LoginClass LoginClass = new LoginClass(registeredUserName, firstName, surName, registeredPassword, registeredNumber);
        System.out.println(LoginClass.registerUser());
        //Prompt the user to enter the details to login
        System.out.println("Please login:");
        System.out.println("Enter username: ");
        userName = myInput.nextLine();
        System.out.println("Enter password: ");
        String userPassword = myInput.nextLine();

        System.out.println((LoginClass.returnLoginStatus(registeredUserName, registeredPassword, userName, userPassword, firstName, surName)));
        
        myInput.close();
        while(loginUser(registeredUserName, userName, registeredPassword, userPassword)){
            
            if(loginUser(registeredUserName,registeredPassword,userName, userPassword)){
               System.out.println("Username or password incorrect, please try again.");
            }else{
                break;
            }
        }
    }
    //Method to check for the validity of the username
    public static boolean checkUserName(String registeredUserName){
        
        if(registeredUserName.length() <= 5 && registeredUserName.contains("_")){
            return true;
        } else{
            return false;
        }
    }
    //Mehtod to check for the valdidty of the password
    public static boolean checkPasswordComplexity(String registeredPassword){
        //Declarations for password check
        boolean containsCapitalLetter = false;
        boolean containsNumber = false;
        boolean containsSpecial = false;
        
        //If statement to check for the length of the password
        if(registeredPassword.length() < 8){
            return false;
        }

        //Loop to Check each character
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
        //if statement to show if conditions are met
        if(containsCapitalLetter && containsNumber && containsSpecial){
            return true;
        }else{
            return false;
        }
    }
    //Method to validate the cellphone number with the code
    public static boolean checkCellPhoneNumber(String registeredNumber){
        //if statement to shdd
        if(registeredNumber.startsWith("+27") && registeredNumber.length() == 12){
            return true;
        }else{
            return false;
        }
    }
    
}
