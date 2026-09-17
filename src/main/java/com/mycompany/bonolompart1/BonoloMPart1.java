/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bonolompart1;

import java.util.Scanner;

/**
 *
 * @author males
 */
public class BonoloMPart1 {

    public static void main(String[] args) {
        Scanner myInput = new Scanner(System.in);
        System.out.println("Hello World!");
        //Declarations
        String userName = "";
        String userPass = "";
        String userCell = "";
        
        //While loop for the Usernamess
        while(!checkUserName(userName)){
            
            //Prompt the users
            System.out.println("Please enter your username: ");
            userName = myInput.nextLine();
            
            if(!checkUserName(userName)){
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
        while(!checkPasswordComplexity(userPass)){
        
        //Prompt the user for the password
        System.out.println("Please enter your password");
        userPass = myInput.nextLine();
        //donkeys
        
        if(!checkPasswordComplexity(userPass)){
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
        
        //
        while(!checkCellPhoneNumber(userCell)){
            //Prompt the user
            System.out.println("Please enter your cellphone number starts with (+27...): ");
            userCell = myInput.nextLine();

            if(!checkCellPhoneNumber(userCell)){
                System.out.println("Cell phone number incorrectly formatted or does not contain international code");
            }else{
                break;
            }
        }
        System.out.println("Cell phone number successfully added");
    }
    //Method to check for the validity of the username
    public static boolean checkUserName(String userName){
        
        if(userName.length() <= 5 && userName.contains("_")){
            return true;
        } else{
            return false;
        }
    }
    //Mehtod to check for the valdidty of the password
    public static boolean checkPasswordComplexity(String userPass){
        //Declarations for password check
        boolean containsCapitalLetter = false;
        boolean containsNumber = false;
        boolean containsSpecial = false;
        
        //If statement to check for the length of the password
        if(userPass.length() < 8){
            return false;
        }

        //Loop to Check each character
        for(int i = 0; i < userPass.length(); i++){
            char character = userPass.charAt(i);
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
    public static boolean checkCellPhoneNumber(String userCell){
        //if statement to shdd
        if(userCell.startsWith("+27") && userCell.length() == 12){
            return true;
        }else{
            return false;
        }
    }
}
