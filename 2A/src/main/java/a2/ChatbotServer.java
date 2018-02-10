package a2;
// Michael, Whitehill

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Allow the Chatbot to be accessible over the network.
 * This class only handles one client at a time.  Multiple instances of ChatbotServer 
 * will be run on different ports with a port-based load balancer to handle multiple clients.
 * 
 * @author Michael Whitehill
 */
public class ChatbotServer {
	
	/**
	 * The instance of the {@link Chatbot}.
	 */
	private a2.Chatbot chatbot;

	/**
	 * The instance of the {@link ServerSocket}.
	 */
	private ServerSocket serversocket;

	/**
	 * Constructor for ChatbotServer.
	 * 
	 * @param chatbot The chatbot to use.
	 * @param serversocket The pre-configured ServerSocket to use.
	 */
	public ChatbotServer(a2.Chatbot chatbot, ServerSocket serversocket) {
		this.chatbot = chatbot;
		this.serversocket = serversocket;
	}
	
	/**
	 * Start the Chatbot server.  Does not return.
	 */
	public void startServer() {
		while(true) handleOneClient();
	}

	/**
	 * Handle interaction with a single client.  See assignment description.
	 */
	public void handleOneClient() {
		try {
			Socket s = serversocket.accept();
			BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter output = new PrintWriter(s.getOutputStream(), true);
			String inString = new String(" ");
			while (input.ready()){
			    inString = input.readLine();
				String response = "";
			    if (inString.equals("-1"))
			        break;
                try {
                    response = chatbot.getResponse(inString);
                } catch (AIException e) {
                    response = "Got AIException: " + e.getMessage();
                }
				output.println(response);
            }
		} catch (IOException ignored) {

		}
	}
}
