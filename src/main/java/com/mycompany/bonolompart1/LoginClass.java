/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bonolompart1;

import java.util.Scanner;


/**
 *
 * @author males
 */
public class LoginClass {
    Scanner myInput = new Scanner(System.in);
    //Declaration
    String usersName;
    String usersFirst;
    String userSurname;
    String userPassword;
    String userNumber;

public LoginClass (String registeredUserName, String firstName, String surName, String registeredPassword, String registeredNumber){
    usersFirst = firstName;
    usersName = registeredUserName; 
    userSurname = surName;
    userPassword = registeredPassword;
    userNumber = registeredNumber;
}
//Method to check the validity of the username
public static boolean checkUserName(String userName){
        
    if(userName.length() <= 5 && userName.contains("_")){
        return true;
    } else{
        return false;
      }
    

 }
//Method to check the validity of the password
public static boolean checkPasswordComplexity(String userPassword){
        //Declarations for password check
        boolean containsCapitalLetter = false;
        boolean containsNumber = false;
        boolean containsSpecial = false;
        
        //If statement to check for the length of the password
        if(userPassword.length() < 8){
            return false;
        }

        //Loop to Check each character
        for(int i = 0; i < userPassword.length(); i++){
            char character = userPassword.charAt(i);
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
//Method to check the validity of the cellphobe number
public static boolean checkCellPhoneNumber(String userCellNum){
        //if statement to shdd
        if(userCellNum.startsWith("+27") && userCellNum.length() == 12){
            return true;
        }else{
            return false;
        }
    }

public String registerUser(){
        if(!checkUserName(usersName)){
            return ("Username is not correctly formatted");
        }else if(!checkPasswordComplexity(userPassword)){
            return ("The password does not meet the complexity requirements");
        }else{
        return ("The two above conditions have been met, and the user has been successfully registered");
        }
    }
//Method to check if the details entered by the user when registering are the same as the ones entered when logining in
public static boolean loginUser(String registeredUserName, String registeredPassword, String usersName, String userPassword){

        if(registeredUserName.equals(usersName) && registeredPassword.equals(userPassword)){
            return true;
        }else{
            return false;
        }
    }
//This method is show the successful login status if the user enters the correct username and password by returning the welcome message
//But it also shows the failed status if the user enters the wrong password and usernamwe by returning the message to try again
public static String returnLoginStatus(String registeredUserName, String registeredPassword, String usersName, String userPassword, String firstName, String surName){
    if(loginUser(registeredUserName, registeredPassword, usersName, userPassword)){
        return "Welcome " + firstName + ", " + surName + " it is great to see you again.";
    }else{
        return "Username or password incorrect, please try again.";
    }
}

}