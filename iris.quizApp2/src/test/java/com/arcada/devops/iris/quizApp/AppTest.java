package com.arcada.devops.iris.quizApp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

public class AppTest
{

	App app;
	
	@BeforeEach
	public void setUp() throws Exception
	{
		app = new App();
	}
	@AfterEach //make sure it closes the app
	public void tearDown() throws Exception
	{
		
	}
	@Test
	public void testCreateServer()
	{
		System.out.println("Testing is done!");
		assertTrue(app.createServer());
	}
	
	@Test
	public void testFriends() 
	{
		 String[] friendsI = app.FBfriends("Iris");  // call the FBfriends method 
	     String[] Efriends = {"Ksenia", "Miranda"};
	     assertArrayEquals(Efriends, friendsI, "Friend test succeeded");
	}
	
	@Test
	public void testFriendsFailedTest() 
	{
		 String[] friendsI = app.FBfriends("Iris");  
	     String[] Efriends = {"Mille", "Malle"}; // wrong friends 
	     assertArrayEquals(Efriends, friendsI);
	}
	
	@Test
	public void testFriendsFalse() 
	{
		 String[] friendsIF = app.FBfriends("Iris"); 
	     String[] falseFriends = {"Mille", "Malle"};
	     assertFalse(Arrays.equals(friendsIF, falseFriends), "Test should pass if the friends array is incorrect");	
	}

}
