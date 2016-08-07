package com.manning.nettyinaction.chapter2.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NioServer {
	public static void main(String[] args) throws IOException {
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.socket().bind(new InetSocketAddress(9999));
		Selector selector = Selector.open();
		while (true) {
			SocketChannel socketChannel = serverSocketChannel.accept();
			serverSocketChannel.configureBlocking(false);
			if (socketChannel != null) {
				socketChannel.configureBlocking(false);
				SelectionKey key = socketChannel.register(selector, SelectionKey.OP_READ);
			}
		}
	}
}
