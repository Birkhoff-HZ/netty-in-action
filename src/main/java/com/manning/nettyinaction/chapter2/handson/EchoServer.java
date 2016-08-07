package com.manning.nettyinaction.chapter2.handson;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class EchoServer {

	int port = 8888;

	public void start() throws Exception {
		final EchoServerHandler echoServerHandler = new EchoServerHandler();
		final EventLoopGroup group = new NioEventLoopGroup();
		final ServerBootstrap serverBootstrap = new ServerBootstrap();
		try {
			serverBootstrap
					.group(group)
					.channel(NioServerSocketChannel.class)
					.localAddress(new InetSocketAddress(port))
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(echoServerHandler);
						}
					});
			ChannelFuture f = serverBootstrap.bind().sync();
			f.channel().closeFuture().sync();
		} finally {
			group.shutdownGracefully().sync();
		}
	}
	
	public static void main(String[] args) throws Exception {
		new EchoServer().start();
	}
}
