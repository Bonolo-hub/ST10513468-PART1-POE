/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.bonolompart1;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class MessegeClassTest {

    // Reset static counters before each test so total doesn't keep growing
    @Before
    public void setUp() {
        MessageClass.sentMessages.clear();
        MessageClass.storeMessages.clear();
        MessageClass.disregardedMessagesJson.clear();
        MessageClass.totalMessSent = 0;
    }

    // ===== POE PAGE 14 TEST DATA =====
    // Test Data Message 1 - Should be valid
    String id1 = "0012345678";
    int num1 = 0;
    String recip1 = "+27718693002";
    String msg1 = "Hi Mike, can you join us for dinner tonight?";

    // Test Data Message 2 - Should be invalid recipient
    String id2 = "0012345679";
    int num2 = 1;
    String recip2 = "08575975889";
    String msg2 = "Hi Keegan, did you receive the payment?";

    @Test
    public void testCheckRecipientCell_Success() {
        MessageClass message = new MessageClass(id1, num1, recip1, msg1);
        assertTrue(message.checkRecipientCell());
    }

    @Test
    public void testCheckRecipientCell_Failure() {
        MessageClass message = new MessageClass(id2, num2, recip2, msg2);
        assertFalse(message.checkRecipientCell());
    }

    @Test
    public void testCheckMessageID_Success() {
        // 10 chars - should be true
        MessageClass message = new MessageClass("0012345678", 0, recip1, msg1);
        assertTrue(message.checkMessageID());
    }

    @Test
    public void testCheckMessageID_Failure() {
        // 15 chars - should be false
        MessageClass message = new MessageClass("001234567890123", 0, recip1, msg1);
        assertFalse(message.checkMessageID());
    }

    @Test
    public void testCreateMessageHash_Page15() {
        // From Part 3 Page 15: should produce 00:0:HITONIGHT
        // Your method: first 2 of ID + ":" + num + ":" + firstWord+lastWord uppercase
        // So we use ID starting with 00 and num 0
        MessageClass message = new MessageClass("0012345678", 0, recip1, msg1);
        String expected = "00:0:HITONIGHT";
        String actual = message.createMessageHash();
        assertEquals(expected, actual);
    }

    @Test
    public void testCreateMessageHash_Message2() {
        MessageClass message = new MessageClass("0012345679", 1, recip2, msg2);
        // firstWord=Hi, lastWord=payment, ID 00, num 1 -> 00:1:HIPAYMENT
        String hash = message.createMessageHash();
        assertEquals("00:1:HIPAYMENT", hash);
    }

    @Test
    public void testSentMessage_Option1_Send() {
        MessageClass message = new MessageClass(id1, num1, recip1, msg1);
        String result = message.SentMessage(1);
        assertEquals("Message successfully sent.", result);
        assertEquals(1, MessageClass.returnTotalMessages());
    }

    @Test
    public void testSentMessage_Option2_Disregard() {
        MessageClass message = new MessageClass(id2, num2, recip2, msg2);
        String result = message.SentMessage(2);
        assertEquals("Press 0 to delete the message.", result);
    }

    @Test
    public void testSentMessage_Option3_Store() {
        MessageClass message = new MessageClass(id1, num1, recip1, msg1);
        String result = message.SentMessage(3);
        assertEquals("Message successfully stored.", result);
    }

    @Test
    public void testPrintMessage_NotNull() {
        MessageClass message = new MessageClass(id1, num1, recip1, msg1);
        assertNotNull(message.printMessage());
        assertTrue(message.printMessage().contains(recip1));
    }

    @Test
    public void testReturnTotalMessages() {
        MessageClass m1 = new MessageClass(id1, 0, recip1, msg1);
        m1.SentMessage(1);
        MessageClass m2 = new MessageClass(id2, 1, recip1, "Another message");
        m2.SentMessage(1);
        assertEquals(2, MessageClass.returnTotalMessages());
    }
}