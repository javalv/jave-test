package com.test;

import java.awt.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.Callable;

public class CallbackTest implements Callable<Integer>{
	private int num;
	public CallbackTest(int num){
		this.num = num;
	}

	static Vector<Long> list = new Vector<>();

	@Override
	public Integer call() throws Exception {
		try {
			long start = System.currentTimeMillis();
			Thread.sleep(new Random().nextInt(100));
			System.out.println(Thread.currentThread().getName() + " "+ new Timestamp(System.currentTimeMillis()) +  " [num:" +this.num + "]" );
			list.add(System.currentTimeMillis() - start);
		} catch (Exception e) {
		}
		return num;
	}
}
