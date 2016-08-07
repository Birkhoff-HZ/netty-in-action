package com.nio.channels;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileAccessChannels {
	public static void main(String[] args) throws Exception {
		RandomAccessFile file = new RandomAccessFile("/nio-data", "rw");
		System.out.println(file.length());
		FileChannel channel = file.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(100);
		int byteReads = channel.read(buf);
		while (byteReads != -1) {
			System.out.println("Read " + byteReads);
			buf.flip();
			while (buf.hasRemaining()) {
				System.out.print((char) buf.get());
			}
			buf.clear();
			byteReads = channel.read(buf);
		}
		file.close();

		buf.put((byte) 97);
		System.out.println(buf.get());
	}
}
