package com.nio.channels;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketChannels {
	public static void main(String[] args) throws IOException {
		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.connect(new InetSocketAddress("http://jenkov.com", 80));

		ByteBuffer buf = ByteBuffer.allocate(48);

		int bytesRead = socketChannel.read(buf);

		System.out.println();
	}
}
