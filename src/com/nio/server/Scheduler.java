package com.nio.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Scheduler extends Thread {
	private Selector selector = null;
	private ConcurrentHashMap<SocketChannel, ConcurrentLinkedQueue<ByteBuffer>> channelWriteQueue = 
		new ConcurrentHashMap<SocketChannel, ConcurrentLinkedQueue<ByteBuffer>>();
	private ConcurrentHashMap<SocketChannel, Integer> remainEvents = 
		new ConcurrentHashMap<SocketChannel, Integer>();
	private Map<SocketChannel, Object> channels = new ConcurrentHashMap<SocketChannel, Object>();

	public Set<SocketChannel> getChannels() {
		return channels.keySet();
	}

	public void read(SocketChannel channel) {
		this.updateInterest(channel, SelectionKey.OP_READ);
	}

	public void write(SocketChannel channel, ByteBuffer buf) {
		ConcurrentLinkedQueue<ByteBuffer> queue = channelWriteQueue
				.get(channel);
		if (queue == null) {
			queue = new ConcurrentLinkedQueue<>();
			channelWriteQueue.put(channel, queue);
		}
		queue.offer(buf);
		this.updateInterest(channel, SelectionKey.OP_WRITE);
	}

	private void updateInterest(SocketChannel channel, int op) {
		int remainEvent = op;
		if (remainEvents.containsKey(channel)) {
			remainEvent |= remainEvents.get(channel);
		}
		SelectionKey key = channel.keyFor(selector);
		if (key == null) {
			try {
				channel.configureBlocking(false);
				key = channel.register(selector, remainEvent);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			key.interestOps(remainEvent);
		}
		selector.wakeup();
	}

	public void run() {
		for (;;) {
			try {
				int rc = selector.select();
				System.out.println("rc " + rc);
				if (rc == 0) {
					continue;
				}
				Iterator<SelectionKey> keyIterator = selector.selectedKeys()
						.iterator();
				while (keyIterator.hasNext()) {
					SelectionKey key = keyIterator.next();
					if (key.isValid())
						handle(key);
					keyIterator.remove();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void channelClose(SocketChannel socketChannel) {
		try {
			socketChannel.register(selector, 0);
		} catch (ClosedChannelException e) {
			e.printStackTrace();
		}
		channels.remove(socketChannel);
		channelWriteQueue.remove(socketChannel);
		remainEvents.remove(socketChannel);
		try {
			socketChannel.socket().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	SocketChannel socketChannelTemp = null;

	private void handle(SelectionKey key) {
		int m_readyEvents = key.readyOps();
		int remianEvents = key.interestOps();
		SocketChannel socketChannel = null;
		
		System.out.println(m_readyEvents);
		if ((m_readyEvents & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
			ServerSocketChannel serverChanel = (ServerSocketChannel) key
					.channel();
			try {
				socketChannel = serverChanel.accept();
				socketChannelTemp = socketChannel;
				socketChannel.configureBlocking(false);
				remianEvents = SelectionKey.OP_READ;
				try {
					socketChannel.register(selector, remianEvents);
					remainEvents.put(socketChannel, remianEvents);
				} catch (ClosedChannelException e) {
					// e.printStackTrace();
					this.channelClose(socketChannel);
				}
				channels.put(socketChannel, new Object());
			} catch (IOException e) {
				// e.printStackTrace();
				this.channelClose(socketChannel);
			}
		}
		if ((m_readyEvents & SelectionKey.OP_READ) != 0) {
			remianEvents &= ~(SelectionKey.OP_ACCEPT | SelectionKey.OP_READ);
			ByteBuffer readBuf = ByteBuffer.allocate(1024);
			try {
				socketChannel = (SocketChannel) key.channel();
				System.out.println(socketChannel.equals(socketChannelTemp));
				int n = socketChannel.read(readBuf);
				readBuf.flip();
				if (readBuf.limit() >= 0) {
					try {
//						socketChannel.register(selector, remianEvents);
						key.interestOps(remianEvents);
						remainEvents.put(socketChannel, remianEvents);
					} catch (Exception e) {
						// e.printStackTrace();
						this.channelClose(socketChannel);
					}
					this.onReadCompleted(socketChannel, readBuf);
				}
				if (n < 0) {
					this.channelClose(socketChannel);
				}
			} catch (IOException e) {
				// e.printStackTrace();
				this.channelClose(socketChannel);
			}

		}
		if ((m_readyEvents & (SelectionKey.OP_CONNECT | SelectionKey.OP_WRITE)) != 0) {
			socketChannel = (SocketChannel) key.channel();
			if (channelWriteQueue.containsKey(socketChannel)) {
				ConcurrentLinkedQueue<ByteBuffer> queue = channelWriteQueue
						.get(socketChannel);
				ByteBuffer buf = queue.peek();
				if (buf != null) {
					try {
						int length = buf.limit() - buf.position();
						int n = socketChannel.write(buf);
						if (n == length) {
							queue.poll();
							this.onWriteCompleted(socketChannel, buf
									.duplicate());
						}
					} catch (IOException e) {
						// e.printStackTrace();
						this.channelClose(socketChannel);
					}
				}
				if (queue.isEmpty()) {
					remianEvents &= ~(SelectionKey.OP_CONNECT | SelectionKey.OP_WRITE);
					try {
						socketChannel.register(selector, remianEvents);
						remainEvents.put(socketChannel, remianEvents);
					} catch (ClosedChannelException e) {
						// e.printStackTrace();
						this.channelClose(socketChannel);
					}
				}
			} else {
				remianEvents &= ~(SelectionKey.OP_CONNECT | SelectionKey.OP_WRITE);
				try {
					socketChannel.register(selector, remianEvents);
					remainEvents.put(socketChannel, remianEvents);
				} catch (ClosedChannelException e) {
					// e.printStackTrace();
					this.channelClose(socketChannel);
				}
			}

		}

	}

	private void onReadCompleted(SocketChannel channel, ByteBuffer buf) {
		byte[] data = new byte[buf.limit()];
		buf.get(data);
		System.out.println(new String(data));
		this.read(channel);
	}

	private void onWriteCompleted(SocketChannel channel, ByteBuffer buf) {

	}

	public Selector getSelector() {
		return selector;
	}

	public void setSelector(Selector selector) {
		this.selector = selector;
	}
}
