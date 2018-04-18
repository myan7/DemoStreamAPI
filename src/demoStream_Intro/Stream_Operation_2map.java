package demoStream_Intro;

import java.util.Arrays;
import java.util.List;


import org.junit.jupiter.api.Test;
/*
 * http://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/
 * map operation 
 * map - 接收 lambda, 将元素转换成其他形式或者提取信息, 接收一个函数作为参数, 该函数会被应用倒每一个元素上,并将其映射成为一个新的元素
 * flatmap - 接收一个函数作为参数,将流中的每个值都换成另一个流, 然后把所有流, 连接成一个流
 * 		
 */
public class Stream_Operation_2map {

	List<String> list = Arrays.asList("aaa","bbb","ccc","ddd");
	// since this list is transferred from array, so it is immutable.
	List<Employee> employees = Arrays.asList(
			new Employee(1,33, "Ming Yan", 60000),
			new Employee(2,34, "Xinxin Lai", 30000),
			new Employee(3,34, "Zhen Yu", 40000),
			new Employee(4,32, "Jingcun Han", 50000),
			new Employee(5,34, "Yongbo Yun", 70000),
			new Employee(6,36, "Zhuang Wu", 120000),
			new Employee(7,28, "Yue Teng", 80000)
	);
	
	@Test 
	public void test1()
	{
		list.stream()
			.map((x)->x.toUpperCase())
			.forEach(System.out::println);
	}
	@Test
	public void test2()
	{
//		employees.stream()
//				.map((x)->x.getName())
//				.forEach(System.out::println);
		
		// also you can use method references
		// apply the intermediate operations to all the elements in the stream,
		// then return the result to the output
		employees.stream()
				.map(Employee::getName)
				.forEach(System.out::println);
	}
	@Test
	public void test3()
	{
		
	}
}
