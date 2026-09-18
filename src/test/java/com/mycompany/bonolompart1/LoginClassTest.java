/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.bonolompart1;

import org.junit.Test;
import static org.junit.Assert.*;

public class LoginClassTest {

    // Valid data from your POE
    String validUsername = "kyl_1";
    String validPassword = "Ch&&sec@ke99!";
    String validCell = "+27838968976";
    String firstName = "Kyle";
    String surname = "Smith";

    @Test
    public void testUsernameCorrectlyFormatted_assertEquals() {
        String expected = "Username successfully captured.";
        // If your LoginClass has getUsernameMessage use it, else check boolean
        boolean result = LoginClass.checkUserName(validUsername);
        assertEquals(true, result);
    }

    @Test
    public void testUsernameIncorrectlyFormatted_assertEquals() {
        boolean result = LoginClass.checkUserName("kyle!!!!!!!");
        assertEquals(false, result);
    }

    @Test
    public void testPasswordMeetsComplexity_assertEquals() {
        boolean result = LoginClass.checkPasswordComplexity(validPassword);
        assertEquals(true, result);
    }

    @Test
    public void testPasswordDoesNotMeetComplexity_assertEquals() {
        boolean result = LoginClass.checkPasswordComplexity("password");
        assertEquals(false, result);
    }

    @Test
    public void testCellCorrectlyFormatted_assertEquals() {
        boolean result = LoginClass.checkCellPhoneNumber(validCell);
        assertEquals(true, result);
    }

    @Test
    public void testCellIncorrectlyFormatted_assertEquals() {
        boolean result = LoginClass.checkCellPhoneNumber("08966553");
        assertEquals(false, result);
    }

    @Test
    public void testLoginSuccessful_assertTrue() {
        LoginClass instance = new LoginClass(validUsername, firstName, surname, validPassword, validCell);
        // Your loginUser takes 4 params: registeredUser, registeredPass, enteredUser, enteredPass
        boolean result = instance.loginUser(validUsername, validPassword, validUsername, validPassword);
        assertTrue(result);
    }

    @Test
    public void testLoginFailed_assertFalse() {
        LoginClass instance = new LoginClass(validUsername, firstName, surname, validPassword, validCell);
        boolean result = instance.loginUser(validUsername, validPassword, validUsername, "wrongPass");
        assertFalse(result);
    }

    @Test
    public void testWelcomeMessage_assertEquals() {
    String expected = "Welcome " + firstName + ", " + surname + " it is great to see you again.";
    String actual = LoginClass.returnLoginStatus(validUsername, validPassword, validUsername, validPassword, firstName, surname);
    assertEquals(expected, actual);
    }

    @Test
    public void testUsernameCorrectlyFormatted_assertTrue() {
        assertTrue(LoginClass.checkUserName("kyl_1"));
    }

    @Test
    public void testUsernameIncorrectlyFormatted_assertFalse() {
        assertFalse(LoginClass.checkUserName("kyle!!!!!!!"));
    }

    @Test
    public void testPasswordMeetsComplexity_assertTrue() {
        assertTrue(LoginClass.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testPasswordDoesNotMeetComplexity_assertFalse() {
        assertFalse(LoginClass.checkPasswordComplexity("password"));
    }

    @Test
    public void testCellCorrectlyFormatted_assertTrue() {
        assertTrue(LoginClass.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCellIncorrectlyFormatted_assertFalse() {
        assertFalse(LoginClass.checkCellPhoneNumber("08966553"));
    }
}