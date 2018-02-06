package a2;

import a2.Chatbot;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;


public class TestServer {
    @Mock
    public Chatbot mockBot;

    @Mock
    public ServerSocket mockSockDrawer;

    @Mock
    public Socket mockSock;

    public ChatbotServer server;

    @Before
    public void setup(){
        mockBot = Mockito.mock(Chatbot.class);
        mockSockDrawer = Mockito.mock(ServerSocket.class);
        mockSock = Mockito.mock(Socket.class);
        server = new ChatbotServer(mockBot, mockSockDrawer);
    }

    @Test
    public void testOutput(){
        try {
            when(mockSockDrawer.accept()).thenReturn(mockSock);
            InputStream inStream = new ByteArrayInputStream("Test\n".getBytes());


            when(mockSock.getInputStream()).thenReturn(inStream);

            OutputStream outputStream = new ByteArrayOutputStream();
            when(mockSock.getOutputStream()).thenReturn(outputStream);


            server.handleOneClient();

            assertEquals("Test\n", outputStream.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
