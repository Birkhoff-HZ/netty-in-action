package com.manning.nettyinaction.chapter1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class BlockingIOServer extends BlockingIoExample {

	@Override
	protected String processRequest(String request) {
		System.out.println(request);
		return request + " processed...";
	}

	volatile boolean start = false;

	public static void main(String[] args) throws IOException, InterruptedException {
		Thread server = new Thread(() -> {
			try {
				BlockingIOServer server1 = new BlockingIOServer();
				server1.serve(8888);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		Thread client = new Thread(() -> {
			try {
				Socket socket = new Socket("localhost", 8888);
				socket.setKeepAlive(true);
				socket.setSoTimeout(1000);
				while (true) {
					System.out.println("Client " + socket);
					PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
					BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					System.out.println(socket.isOutputShutdown());
					System.out.println(socket.isInputShutdown());
					System.out.println(socket.isClosed());
					out.println("hello from client");
					System.out.println(in.readLine());
					Thread.sleep(1000);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		server.start();
		client.start();
		server.join();
		client.join();
	}

}
