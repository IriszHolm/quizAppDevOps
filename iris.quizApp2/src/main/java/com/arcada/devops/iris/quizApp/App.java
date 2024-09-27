package com.arcada.devops.iris.quizApp;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public final class App 
{
    private List<String[]> friends;
    
    public App() {

    	friends = new ArrayList<>();
        friends.add(new String[]{"Iris", "Ksenia", "Miranda"});
        friends.add(new String[]{"Ksenia", "Lorita", "Iris", "Anna"});
    }
    
	private static int count = 0;
	
	
    public static final void main(final  String[] args )
    {
    	App app = new App();
    	Scanner scanner = new Scanner(System.in);
    	
    	app.createServer();
    	
    	
    	 System.out.println("Enter the name of the person to get their friends: ");
         String name = scanner.nextLine();  // Get user input from the terminal

         String[] result = app.FBfriends(name);

          if (result.length > 0) {
              System.out.println(name + "'s friends: " + String.join(", ", result));
          } else {
              System.out.println("No friends found for " + name);
          }

          scanner.close();  // Close the scanner to avoid resource leaks
    	
    	
    	
    }//main closes
    
    public boolean createServer()
    {
    	try
    	{
    		final HttpServer server = HttpServer.create(new InetSocketAddress(8083), 0);
            server.createContext("/", new DemoHandler());
            server.setExecutor(null); // creates a default executor
            server.start();
            System.out.println("Hello. Your server is running. Please open the following link from browser: http://localhost:8083/");
            
            return true;
    	}
    	catch(final IOException exception)
    	{
    		exception.printStackTrace();
    		return false;
    	}
    }
    
    private static final class DemoHandler implements HttpHandler 
    {
        @Override
        public final void handle(final HttpExchange t) throws IOException 
        {
            final String response = "<h1> Welcome. This page was called "+ (++count)+" times </h1>.";
            t.sendResponseHeaders(200, response.length());
            final OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }//handle method closes
    }//Inner class closes
    
    
    public String[] FBfriends(String name) {
    	
    	for (String[] listofFriends : friends) {
    		if (listofFriends[0].equalsIgnoreCase(name)) {
                return Arrays.copyOfRange(listofFriends, 1, listofFriends.length);
    		}
    	}
    	return new String[]{};
    	}
    	
    
    
    
}//class App closes

