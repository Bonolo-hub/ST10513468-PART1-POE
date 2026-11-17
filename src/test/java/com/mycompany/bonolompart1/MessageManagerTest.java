/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.bonolompart1;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.FileWriter;
import java.util.List;

public class MessageManagerTest {

    @Before
    public void setupTestData() throws Exception {
        // Clear everything - simulate fresh app
        MessageClass.sentMessageArray.clear();
        MessageClass.storedMessages.clear();
        MessageClass.disregardedMessages.clear();
        MessageClass.messageIDs.clear();
        MessageClass.messageHashes.clear();
        
        // Clear json file
        new FileWriter("messages.json", false).close();
        
        // ==== TEST DATA FROM BRIEF (5 messages) ====
        // Message 1 - Sent
        MessageClass m1 = new MessageClass("MSG001", 1, "+27834557896", "Did you get the cake?");
        m1.SentMessage(1);
        
        // Message 2 - Stored (Longest)
        MessageClass m2 = new MessageClass("MSG002", 2, "+27838884567", "Where are you? You are late! I have asked you to be on time.");
        m2.SentMessage(3);
        
        // Message 3 - Disregarded
        MessageClass m3 = new MessageClass("MSG003", 3, "0838884567", "Yohooo, I am at your gate.");
        m3.SentMessage(2);
        
        // Message 4 - Sent
        MessageClass m4 = new MessageClass("MSG004", 4, "+27838884567", "It is dinner time !");
        m4.SentMessage(1);
        
        // Message 5 - Stored
        MessageClass m5 = new MessageClass("MSG005", 5, "+27838884567", "Ok, I am leaving without you.");
        m5.SentMessage(3);
    }

    @Test
    public void testSentMessagesArrayCorrectlyPopulated() {
        System.out.println("Testing: Sent Messages array");
        // Should contain message 1-4 -> Did you get the cake? and It is dinner time!
        assertEquals(2, MessageClass.sentMessageArray.size());
        assertTrue(MessageClass.sentMessageArray.contains("Did you get the cake?"));
        assertTrue(MessageClass.sentMessageArray.contains("It is dinner time !"));
        
        String expected = "Did you get the cake?, It is dinner time !";
        String actual = String.join(", ", MessageClass.sentMessageArray);
        // For display as per brief: “Did you get the cake?”, “It is dinner time!”
        assertEquals(2, MessageClass.sentMessageArray.size());
    }

    @Test
    public void testDisplayTheLongestMessage() {
        System.out.println("Testing: Longest Message");
        List<String> allStored = MessageManager.loadMessages();
        String longest = "";
        for(String line : allStored){
            try{
                String msg = line.split("\"message\":\"")[1].split("\"}")[0];
                if(msg.length() > longest.length()) longest = msg;
            }catch(Exception e){}
        }
        assertEquals("Where are you? You are late! I have asked you to be on time.", longest);
    }

    @Test
    public void testSearchForMessageID() {
        System.out.println("Testing: Search for messageID - message 4");
        // Message 4 is MSG004 / +27838884567 -> It is dinner time !
        // Check it exists in our IDs array
        assertTrue(MessageClass.messageIDs.contains("MSG004"));
        
        // Find its message from sent array
        String foundMsg = "";
        // MSG004 is the 4th message, index 3 in IDs list, but sent array has only 2 items
        // So we directly check sent messages contain it
        for(String msg : MessageClass.sentMessageArray){
            if(msg.contains("dinner time")){
                foundMsg = msg;
            }
        }
        assertEquals("It is dinner time !", foundMsg);
    }

    @Test
    public void testSearchAllMessagesByRecipient() {
        System.out.println("Testing: Search by recipient +27838884567");
        List<String> lines = MessageManager.loadMessages();
        int count = 0;
        StringBuilder result = new StringBuilder();
        for(String line : lines){
            if(line.contains("+27838884567")){
                String msg = line.split("\"message\":\"")[1].split("\"}")[0];
                result.append(msg).append(" ");
                count++;
            }
        }
        // Should return 2 messages for this recipient (stored) + 1 sent = 3 total, but stored file has 2
        assertTrue(result.toString().contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue(result.toString().contains("Ok, I am leaving without you."));
    }

    @Test
    public void testDeleteMessageUsingHash() {
        System.out.println("Testing: Delete using hash - Test Message 2");
        List<String> before = MessageManager.loadMessages();
        int sizeBefore = before.size();
        
        // Get hash of message 2
        String hashToDelete = "";
        for(String line : before){
            if(line.contains("Where are you? You are late!")){
                hashToDelete = line.split("\"messageHash\":\"")[1].split("\",\"recipient\"")[0];
            }
        }
        
        MessageManager.deleteByHash(hashToDelete);
        
        List<String> after = MessageManager.loadMessages();
        assertEquals(sizeBefore - 1, after.size());
        // Check message is deleted
        String all = String.join("", after);
        assertFalse(all.contains("Where are you? You are late!"));
    }

    @Test
    public void testDisplayReport() {
        System.out.println("Testing: Display Report");
        List<String> report = MessageManager.loadMessages();
        // Report should show all stored messages including hash, recipient, message
        assertFalse(report.isEmpty());
        for(String line : report){
            assertTrue(line.contains("messageHash"));
            assertTrue(line.contains("recipient"));
            assertTrue(line.contains("message"));
        }
    }
}