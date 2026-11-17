/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bonolompart1;

import java.io.*;
import java.util.*;

public class MessageManager {
    public static String JSON_FILE = "messages.json";

    public static void saveToJSON(String id, String recipient, String msg, String hash) {
        // Already handled in MessageClass, but keep for safety
    }

    public static List<String> loadMessages() {
        List<String> list = new ArrayList<>();
        File f = new File(JSON_FILE);
        if (!f.exists()) return list;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) list.add(line);
        } catch (Exception e) {}
        return list;
    }

    // a) Display sender and recipient of all stored
    public static void displaySenderAndRecipient() {
        List<String> lines = loadMessages();
        if (lines.isEmpty()) { System.out.println("No stored messages."); return; }
        System.out.println("\n--- a) Sender and Recipient of all Stored Messages ---");
        for (String line : lines) {
            try {
                String rec = line.split("\"recipient\":\"")[1].split("\",\"message\"")[0];
                System.out.println("Sender: Bonolo | Recipient: " + rec);
            } catch (Exception e) {}
        }
    }

    // b) Display longest stored message
    public static void displayLongest() {
        List<String> lines = loadMessages();
        if (lines.isEmpty()) { System.out.println("No stored messages."); return; }
        String longest = "";
        for (String line : lines) {
            try {
                String msg = line.split("\"message\":\"")[1].split("\"}")[0];
                if (msg.length() > longest.length()) longest = msg;
            } catch (Exception e) {}
        }
        System.out.println("\n--- b) Longest Stored Message ---");
        System.out.println(longest);
    }

    // c) Search for message ID
    public static void searchByMessageID(String id) {
        List<String> lines = loadMessages();
        for (String line : lines) {
            if (line.contains("\"messageID\":\""+id+"\"")) {
                String rec = line.split("\"recipient\":\"")[1].split("\",\"message\"")[0];
                String msg = line.split("\"message\":\"")[1].split("\"}")[0];
                System.out.println("\n--- c) Search by ID ---");
                System.out.println("Recipient: " + rec);
                System.out.println("Message: " + msg);
                return;
            }
        }
        System.out.println("Message ID not found: " + id);
    }

    // d) Search for all messages for recipient
    public static void searchByRecipient(String recipient) {
        List<String> lines = loadMessages();
        System.out.println("\n--- d) Messages for " + recipient + " ---");
        boolean found = false;
        for (String line : lines) {
            if (line.contains("\"recipient\":\""+recipient+"\"")) {
                String msg = line.split("\"message\":\"")[1].split("\"}")[0];
                System.out.println("- " + msg);
                found = true;
            }
        }
        if (!found) System.out.println("No messages for that recipient.");
    }

    // e) Delete by hash
    public static void deleteByHash(String hash) {
        File input = new File(JSON_FILE);
        File temp = new File("temp.json");
        boolean deleted = false;
        try (BufferedReader br = new BufferedReader(new FileReader(input));
             BufferedWriter bw = new BufferedWriter(new FileWriter(temp))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("\"messageHash\":\""+hash+"\"")) { deleted = true; continue; }
                bw.write(line + "\n");
            }
        } catch (Exception e) {}
        input.delete();
        temp.renameTo(input);
        System.out.println(deleted ? "Message with hash " + hash + " DELETED." : "Hash not found.");
    }

    // f) Full report
    public static void displayFullReport() {
        List<String> lines = loadMessages();
        System.out.println("\n--- f) FULL REPORT ---");
        if (lines.isEmpty()) { System.out.println("No stored messages."); return; }
        for (int i=0; i<lines.size(); i++) {
            System.out.println((i+1) + ". " + lines.get(i));
        }
    }
    
    // For Unit Tests - populate with test data from brief
    public static void populateTestData() {
        MessageClass.sentMessageArray.clear();
        MessageClass.storedMessages.clear();
        MessageClass.disregardedMessages.clear();
        
        // Test Data from brief
        MessageClass.sentMessageArray.add("Did you get the cake?"); // +27834557896 Sent
        MessageClass.storedMessages.add("Where are you? You are late! I have asked you to be on time."); // Stored
        MessageClass.disregardedMessages.add("Yohooo, I am at your gate."); // Disregard
        MessageClass.sentMessageArray.add("It is dinner time !"); // Sent
        MessageClass.storedMessages.add("Ok, I am leaving without you."); // Stored
    }
}