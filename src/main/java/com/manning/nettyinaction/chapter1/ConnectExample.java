package com.manning.nettyinaction.chapter1;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

/**
 * Listing 1.3 and 1.4 of <i>Netty in Action</i>
 *
 * @author <a href="mailto:norman.maurer@googlemail.com">Norman Maurer</a>
 */
public class ConnectExample {

	public static void connect(Channel channel) {
		// Does not block
		ChannelFuture future1 = channel.connect(new InetSocketAddress("192.168.0.1", 25));
		future1.addListener((ChannelFuture future) -> {
			if (future.isSuccess()) {
				ByteBuf buffer = Unpooled.copiedBuffer("Hello", Charset.defaultCharset());
				ChannelFuture wf = future.channel().writeAndFlush(buffer);
				// ...
			} else {
				Throwable cause = future.cause();
				cause.printStackTrace();
			}
		});
	}
	public static void main(String[] args) {
		
	}
}
