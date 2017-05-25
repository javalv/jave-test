package com.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadTest {

	public static void main(String[] args) {
		ExecutorService threadPool = Executors.newFixedThreadPool(100);
		List<Future> list = new ArrayList<>();
		for(int j=1;j<=100;j++){

			Future<Integer> futures = threadPool.submit(new CallbackTest(j));
			list.add(futures);

		}

		List<Long> list1 = CallbackTest.list;

		list.stream().forEach((f)->{
			try {
				f.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		});

		System.out.println(list1.size() + ":" + list1);

		Long[] T = {};
		Long[] array = list1.toArray(T);
		Arrays.sort(array);
		System.out.println("avg:" + array[array.length/2]);
		System.out.println("90%:" + array[(int) (array.length * 0.9)]);

	}
}
