package com.nio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Date;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			final Socket socket = new Socket();
			socket.connect(new InetSocketAddress("127.0.0.1", 8888));
			Thread writeThread = new Thread() {
				public void run() {
					try {
						while (true) {
							
							socket.getOutputStream().write(
									new Date().toString().getBytes());
//							socket.close();
							Thread.sleep(1000);
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			};
			writeThread.start();
			Thread readThread = new Thread() {
				public void run() {
					try {
						while (true) {
//							Thread.sleep(1000);
							byte buf[] = new byte[1024];
							socket.getInputStream().read(buf);
//							socket.close();
							System.out.println(new String(buf));
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			};
			readThread.start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
