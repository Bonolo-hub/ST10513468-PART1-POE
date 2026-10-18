/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bonolompart1;

/**
 *
 * @author males
 */
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList; // FIX 1: you were missing imports

public class MessageClass {
    //Declarations
    String messageID;
    String recipientCell;
    String shortMessage;
    String messageHash;
    int numOfMess;
    static int totalMessSent = 0; // FIX 2: you were missing ; here
    
    //ArrayLists for storing, disregarding and sending
    static ArrayList<MessageClass> sentMessages = new ArrayList<>();
    static ArrayList<String> storeMessages = new ArrayList<>();
    static ArrayList<String> disregardedMessagesJson = new ArrayList<>();

    //Constructors
    public MessageClass(String messageID, int numOfMess, String recipient, String messageSent) {
        this.messageID = messageID;
        this.numOfMess = numOfMess;
        this.recipientCell = recipient;
        this.shortMessage = messageSent;
        this.messageHash = createMessageHash();
    }
    
    //Method to check Message ID is not more than 10 characters
    public boolean checkMessageID() {
        if(messageID.length() <= 10) {
            return true;
        } else {
            return false;
        }
    }

    //Method to check recipient cell starts with +27 and is less than or equal to 13
    public boolean checkRecipientCell() {
        if (recipientCell.startsWith("+27") && recipientCell.length() <= 13) {
            return true;
        } else {
            return false;
        }
    }
    
    //Method to create Message Hash
     public String createMessageHash() {
        if(shortMessage == null || shortMessage.isEmpty()){
            return "No message";
        }
        String[] words = shortMessage.split(" ");
        String firstWord = words[0].replaceAll("[^a-zA-Z]", "");
        String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z]", "");
        
        String firstTwoID = messageID.substring(0, 2);
        return (firstTwoID + ":" + numOfMess + ":" + firstWord + lastWord).toUpperCase();
    }
     
    //Method to handle Send / Disregard / Store
    public String SentMessage(int choice) {
        switch(choice){
            case 1:
                sentMessages.add(this);
                totalMessSent++;
                return "Message successfully sent.";
            case 2:
                disregardedMessagesJson.add(shortMessage);
                return "Press 0 to delete the message.";
            case 3:
                storeMessage();
                return "Message successfully stored.";
            default:
                return "Invalid choice.";
        }
    }
    
    //Method to print single message
    public String printMessage() {
        return "MessageID: " + messageID + "\nMessageHash: " + messageHash + "\nRecipient: " + recipientCell + "\nMessage: " + shortMessage;
    }

    //Method to print message
    public String printMessages() {
        return "ID: " + messageID + "\nHash: " + messageHash + "\nTo: " + recipientCell + "\nMessage: " + shortMessage;
    }
    
    //Method to return total
    public static int returnTotalMessages() {
        return totalMessSent;
    }

    //Method with JSON which i researched
    public void storeMessage() {
        try{
            FileWriter file = new FileWriter("storedMessages.json", true);
            String json = "{\"MessageID\":\"" + messageID + "\",\"MessageHash\":\"" + messageHash + "\",\"Recipient\":\"" + recipientCell + "\",\"Message\":\"" + shortMessage + "\"},\n";
            file.write(json);
            file.close();
            storeMessages.add(json);
        }catch(IOException e){
            System.out.println("Error storing message: " + e.getMessage());
        }
    }
}