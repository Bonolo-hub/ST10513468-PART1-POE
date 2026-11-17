/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bonolompart1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MessageClass {
    String messageID;
    String recipientCell;
    String shortMessage;
    String messageHash;
    int numOfMess;
    static int totalMessSent = 0;
    
    // PART 2 Arrays
    static ArrayList<MessageClass> sentMessages = new ArrayList<>();
    static ArrayList<String> storeMessages = new ArrayList<>();
    static ArrayList<String> disregardedMessagesJson = new ArrayList<>();
    
    // PART 3 Arrays - Required by brief
    public static ArrayList<String> sentMessageArray = new ArrayList<>();
    public static ArrayList<String> disregardedMessages = new ArrayList<>();
    public static ArrayList<String> storedMessages = new ArrayList<>();
    public static ArrayList<String> messageHashes = new ArrayList<>();
    public static ArrayList<String> messageIDs = new ArrayList<>();
    public static ArrayList<String> storedRecipients = new ArrayList<>();

    public MessageClass(String messageID, int numOfMess, String recipient, String messageSent) {
        this.messageID = messageID;
        this.numOfMess = numOfMess;
        this.recipientCell = recipient;
        this.shortMessage = messageSent;
        this.messageHash = createMessageHash();
    }
    
    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    public boolean checkRecipientCell() {
        return recipientCell.startsWith("+27") && recipientCell.length() <= 13;
    }
    
     public String createMessageHash() {
        if(shortMessage == null || shortMessage.isEmpty()) return "No message";
        String[] words = shortMessage.trim().split("\\s+");
        String firstWord = words[0].replaceAll("[^a-zA-Z]", "");
        String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z]", "");
        String firstTwoID = messageID.length() >=2 ? messageID.substring(0, 2) : messageID;
        return (firstTwoID + ":" + numOfMess + ":" + firstWord + lastWord).toUpperCase();
    }
     
    public String SentMessage(int choice) {
        switch(choice){
            case 1:
                sentMessages.add(this);
                sentMessageArray.add(shortMessage);
                messageIDs.add(messageID);
                messageHashes.add(messageHash);
                totalMessSent++;
                return "Message successfully sent.";
            case 2:
                disregardedMessagesJson.add(shortMessage);
                disregardedMessages.add(shortMessage);
                messageIDs.add(messageID);
                messageHashes.add(messageHash);
                return "Message disregarded.";
            case 3:
                storeMessage();
                storedMessages.add(shortMessage);
                storedRecipients.add(recipientCell);
                messageIDs.add(messageID);
                messageHashes.add(messageHash);
                return "Message successfully stored.";
            default:
                return "Invalid choice.";
        }
    }
    
    public String printMessage() {
        return "MessageID: " + messageID + "\nMessageHash: " + messageHash + "\nRecipient: " + recipientCell + "\nMessage: " + shortMessage;
    }

    public String printMessages() {
        return "ID: " + messageID + "\nHash: " + messageHash + "\nTo: " + recipientCell + "\nMessage: " + shortMessage;
    }
    
    public static int returnTotalMessages() { return totalMessSent; }
    public String getMessageID(){ return messageID; }
    public String getMessageHash(){ return messageHash; }
    public String getRecipient(){ return recipientCell; }
    public String getMessage(){ return shortMessage; }

    public void storeMessage() {
        try{
            FileWriter file = new FileWriter("messages.json", true);
            String cleanMsg = shortMessage.replace("\"", "'").replace("\n", " ");
            String json = "{\"messageID\":\"" + messageID + "\",\"messageHash\":\"" + messageHash + "\",\"recipient\":\"" + recipientCell + "\",\"message\":\"" + cleanMsg + "\"}\n";
            file.write(json);
            file.close();
            storeMessages.add(json);
            // Also save to MessageManager file
            MessageManager.saveToJSON(messageID, recipientCell, shortMessage, messageHash);
        }catch(IOException e){
            System.out.println("Error storing message: " + e.getMessage());
        }
    }
}