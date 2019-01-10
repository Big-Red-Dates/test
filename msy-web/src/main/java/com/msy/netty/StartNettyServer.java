package com.msy.netty;

import java.nio.charset.Charset;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class StartNettyServer implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		ServerBootstrap b = new ServerBootstrap();
		b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
				.childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
//							@Override
//							public void channelActive(ChannelHandlerContext ctx) throws Exception {
//								final ByteBuf bb = ctx.alloc().buffer(4);
//			                    bb.writeInt(2);
//			            		ctx.writeAndFlush(bb);
//							}
							
							@Override
							public void channelRead(ChannelHandlerContext ctx, Object msg) {
								ByteBuf m = (ByteBuf) msg;
								Charset utf8 = Charset.forName("UTF-8");
								System.out.println("<==>"+ctx);
								String data = m.toString(utf8);
								System.out.println("所有传来的数据" + data);
								try {
									if (NettyChannelMap.get(data) == null) {
										NettyChannelMap.add(data, (SocketChannel) ctx.channel());
										System.out.println("投影仪传来的id" + data);
										System.out.println("map的大小" + NettyChannelMap.size());
									}
//									else {
//										String[] pidAndstatus = data.split("##");
//										String pid = pidAndstatus[0];
//										String status = pidAndstatus[1];
//										final Channel channel = NettyChannelMap.get(pid);
//										System.out.println(channel);
//
//										if (channel != null) {
//											final ByteBuf bb = ctx.alloc().buffer(4);
//											bb.writeInt(Integer.parseInt(status));
//											final ChannelFuture channelFuture = channel.writeAndFlush(bb);
//											channelFuture.addListener(new ChannelFutureListener() {
//												@Override
//												public void operationComplete(ChannelFuture future) {
//													assert channelFuture == future;
//													NettyChannelMap.remove((SocketChannel) channel);
//													System.out.println("map大小" + NettyChannelMap.size());
//												}
//											});
//										}

//									}

								} finally {
									m.release();
								}
							}
						});
					}
				}).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);

		// Bind and start to accept incoming connections.
		b.bind(9000).syncUninterruptibly();

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
