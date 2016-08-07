package com.nio.channels;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class Selectors {
	public static void main(String[] args) throws IOException {
		Selector selector = Selector.open();

		SocketChannel c1 = SocketChannel.open();
		SocketChannel c2 = SocketChannel.open();

		c1.configureBlocking(false);
		c2.configureBlocking(false);

		c1.register(selector, SelectionKey.OP_READ);
		c2.register(selector, SelectionKey.OP_READ);

		new Thread(() -> {
			System.out.println(Thread.currentThread());
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			selector.wakeup();
		}).start();
		System.out.println("Start selecting");
		selector.select();
		System.out.println("out");

		Set<SelectionKey> selectedKeys = selector.selectedKeys();
		System.out.println(selectedKeys);
	}
}
