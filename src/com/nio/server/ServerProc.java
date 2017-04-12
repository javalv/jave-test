package com.nio.server;

import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class ServerProc {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Server server = new Server();
		final Scheduler scheduler = new Scheduler();
		try {
			Selector selector = Selector.open();
			server.setSelector(selector);
			server.startAccept(8888);
			scheduler.setSelector(selector);
			scheduler.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Thread serverSendThread = new Thread() {
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
						Set<SocketChannel> channels = scheduler.getChannels();
						String serverText = "hello client! welcome at "
								+ (new Date());
						ByteBuffer buf = ByteBuffer.wrap(serverText.getBytes());
						for (SocketChannel channel : channels) {
							scheduler.write(channel, buf.duplicate());
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		serverSendThread.start();
	}
}
