package com.manning.nettyinaction.chapter1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Listing 1.1 of <i>Netty in Action</i>
 *
 * @author <a href="mailto:norman.maurer@googlemail.com">Norman Maurer</a>
 */
public abstract class BlockingIoExample {
	
	static volatile boolean start = false;
	
	public void serve(int portNumber) throws IOException {
		ServerSocket serverSocket = new ServerSocket(portNumber);
		start = true;
		while (true) {
			Socket clientSocket = serverSocket.accept();
			clientSocket.setKeepAlive(true);
			clientSocket.setSoTimeout(1000);
			System.out.println(clientSocket + "server");
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			String request, response;
			if ((request = in.readLine()) != null) {
				if ("done".equals(request)) {
					break;
				}
				response = processRequest(request);
				out.println(response);
				out.flush();
			}
		}
	}

	protected abstract String processRequest(String request);
}
