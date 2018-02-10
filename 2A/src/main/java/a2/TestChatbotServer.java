package a2;
// Michael, Whitehill

import a2.Chatbot;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;


public class TestChatbotServer {
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
    public void testBasicMessage(){
        try {

            InputStream inStream = new ByteArrayInputStream("Hello mockbot\n".getBytes());

            OutputStream outputStream = new ByteArrayOutputStream();
            when(mockSockDrawer.accept()).thenReturn(mockSock);
            when(mockSock.getInputStream()).thenReturn(inStream);
            when(mockSock.getOutputStream()).thenReturn(outputStream);
            when(mockBot.getResponse("Hello mockbot")).thenReturn("I_AM_MOCK-BOT");

            server.handleOneClient();

            assertEquals("I_AM_MOCK-BOT\n", outputStream.toString());

        } catch (IOException e) {
            fail();
        } catch (AIException e) {
            fail();
        }
    }

    @Test
    public void testEmptyMessage(){
        try{
            InputStream inStream = new ByteArrayInputStream("\n".getBytes());

            OutputStream outputStream = new ByteArrayOutputStream();
            when(mockSockDrawer.accept()).thenReturn(mockSock);
            when(mockSock.getInputStream()).thenReturn(inStream);
            when(mockSock.getOutputStream()).thenReturn(outputStream);
            when(mockBot.getResponse("")).thenReturn("I_AM_MOCK-BOT");

            server.handleOneClient();

            assertEquals("I_AM_MOCK-BOT\n", outputStream.toString());
        } catch (AIException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void  testNumericMessage(){
        try{
            InputStream inStream = new ByteArrayInputStream("300\n".getBytes());

            OutputStream outputStream = new ByteArrayOutputStream();
            when(mockSockDrawer.accept()).thenReturn(mockSock);
            when(mockSock.getInputStream()).thenReturn(inStream);
            when(mockSock.getOutputStream()).thenReturn(outputStream);
            when(mockBot.getResponse("300")).thenReturn("I_AM_MOCK-BOT");

            server.handleOneClient();

            assertEquals("I_AM_MOCK-BOT\n", outputStream.toString());
        } catch (AIException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMultipleMessages(){
        try{
            InputStream inStream = new ByteArrayInputStream("input1\ninput2\ninput3\n".getBytes());



            OutputStream outputStream = new ByteArrayOutputStream();
            when(mockSockDrawer.accept()).thenReturn(mockSock);
            when(mockSock.getInputStream()).thenReturn(inStream);
            when(mockSock.getOutputStream()).thenReturn(outputStream);
            when(mockBot.getResponse("input1")).thenReturn("I_AM_MOCK-BOT1");
            when(mockBot.getResponse("input2")).thenReturn("I_AM_MOCK-BOT2");
            when(mockBot.getResponse("input3")).thenReturn("I_AM_MOCK-BOT3");

            server.handleOneClient();

            assertEquals("I_AM_MOCK-BOT1\nI_AM_MOCK-BOT2\nI_AM_MOCK-BOT3\n", outputStream.toString());
        } catch (AIException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testOpenThenCloseConnections(){
        try{
            InputStream inStream = new ByteArrayInputStream("Hello Mockbot".getBytes());
            InputStream inStream1 = new ByteArrayInputStream("What's up Mockbot".getBytes());



            OutputStream outputStream = new ByteArrayOutputStream();
            when(mockSockDrawer.accept()).thenReturn(mockSock);
            when(mockSock.getInputStream()).thenReturn(inStream);
            when(mockSock.getOutputStream()).thenReturn(outputStream);
            when(mockBot.getResponse("Hello Mockbot")).thenReturn("I_AM_MOCK-BOT");


            server.handleOneClient();

            assertEquals("I_AM_MOCK-BOT\n", outputStream.toString());

            when(mockSock.getInputStream()).thenReturn(inStream1);
            when(mockBot.getResponse("What's up Mockbot")).thenReturn("mockBot.DESTROYHUMANS()");
            server.handleOneClient();
            assertEquals("I_AM_MOCK-BOT\nmockBot.DESTROYHUMANS()\n", outputStream.toString()); // Needed because the socket keeps old message
        } catch (AIException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testIOFailure() {
        try {
            when(mockSockDrawer.accept()).thenThrow(new IOException());
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void testAiFailure(){
        try {

            InputStream inStream = new ByteArrayInputStream("Call me back when you're a real man!\nI'm sorry I judged you\n".getBytes());

            OutputStream outputStream = new ByteArrayOutputStream();
            when(mockSockDrawer.accept()).thenReturn(mockSock);
            when(mockSock.getInputStream()).thenReturn(inStream);
            when(mockSock.getOutputStream()).thenReturn(outputStream);
            when(mockBot.getResponse("Call me back when you're a real man!")).thenThrow(new AIException("MOCK_BOT_IS_A_REAL_MAN"));
            when(mockBot.getResponse("I'm sorry I judged you")).thenReturn("It's okay");

            server.handleOneClient();

            assertEquals("Got AIException: MOCK_BOT_IS_A_REAL_MAN\nIt's okay\n", outputStream.toString());

        } catch (IOException e) {
            fail();
        } catch (AIException e) {
            fail();
        }
    }

}
