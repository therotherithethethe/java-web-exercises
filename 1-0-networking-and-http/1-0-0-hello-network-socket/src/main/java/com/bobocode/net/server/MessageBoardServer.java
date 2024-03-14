package com.bobocode.net.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static com.bobocode.net.server.ServerUtil.*;

/**
 * {@link MessageBoardServer} is a server application that allows clients to connect via socket and print a message.
 * It opens a server socket on a given port and waits for a client to connect using a infinite loop. Once client is
 * connected this app reads a message from the connected socket input stream and prints it to the console.
 * <p>
 * It uses only one thread. So if multiple clients will try to connect at the same time, they will be waiting and will
 * be processed one by one.
 */
public class MessageBoardServer {
    public static final int PORT = 8899;

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = ServerUtil.createServerSocket(PORT)) {
            System.out.println("Server started. Listening on port " + PORT);
            while (true) {
                try (Socket clientSocket = ServerUtil.acceptClientSocket(serverSocket)) {
                    String message = ServerUtil.readMessageFromSocket(clientSocket);
                    ServerUtil.printMessage(clientSocket, message);
                }
            }
        }
    }
}
