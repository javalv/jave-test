package com.aio;

/**
 * ClassName:com.aio.AioServer
 * 描述: TODO
 * 日期:     2017/3/16
 *
 * @author lvfang
 * @version 1.0.0
 * @since 1.0
 */

import com.sun.xml.internal.ws.util.CompletedFuture;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.*;

/**
 * @author noname
 */
public class AioServer {
    public final static int PORT = 9888;
    private AsynchronousServerSocketChannel server;

    public AioServer() throws IOException {
        server = AsynchronousServerSocketChannel.open().bind(
                new InetSocketAddress(PORT));
    }

    public void startWithFuture() throws InterruptedException,
            ExecutionException, TimeoutException {
        System.out.println("Server listen on " + PORT);
        Future<AsynchronousSocketChannel> future = server.accept();
        AsynchronousSocketChannel socket = future.get();
        ByteBuffer readBuf = ByteBuffer.allocate(1024);
        readBuf.clear();
        socket.read(readBuf).get(100, TimeUnit.SECONDS);
        readBuf.flip();
        System.out.printf("received message:" + new String(readBuf.array()));
        System.out.println(Thread.currentThread().getName());

    }

    private static ConcurrentHashMap<AsynchronousSocketChannel, ByteBuffer> clients
            = new ConcurrentHashMap<>();

    public void startWithCompletionHandler() throws InterruptedException,
            ExecutionException, TimeoutException {

        System.out.println("Server listen on " + PORT);

        CompletionHandler<AsynchronousSocketChannel, Object> handler =
                new CompletionHandler<AsynchronousSocketChannel, Object>() {

                    @Override
                    public void completed(AsynchronousSocketChannel channel,
                                          Object attachment) {
                        System.out.println(Thread.currentThread().getName());
                        System.out.println("start");
                        try {

                            final ByteBuffer buffer = ByteBuffer.allocate(1024);
                            buffer.clear();
                            clients.put(channel, buffer);
                            server.accept(null, this);
                            while (true) {
                                try {
                                    channel.read(buffer).get(100, TimeUnit.SECONDS);
                                    buffer.flip();
                                    byte[] data = new byte[buffer.limit()];
                                    buffer.get(data);
                                    buffer.clear();
                                    System.out.println(channel + "received message: "
                                            + new String(data) + " " + Thread.currentThread());
                                    CompletableFuture.supplyAsync(() -> {
                                        doHandler(data);
                                        return null;
                                    });
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }

                        } catch (Exception e) {
                            System.out.println(e.toString());
                        } finally {

                        }

                        System.out.println("end");
                    }

                    @Override
                    public void failed(Throwable exc, Object attachment) {
                        System.out.println("failed: " + exc);
                    }
                };

        //注册事件和事件完成后的处理器
        server.accept(null, handler);
        // 主线程继续自己的行为

    }

    public void doHandler(byte[] datas) {
        System.out.println("=============" + new String(datas) + " " + Thread.currentThread().getName());
    }

    public static void main(String args[]) throws Exception {
        new AioServer().startWithCompletionHandler();
        System.in.read(new byte[1]);
    }
}

