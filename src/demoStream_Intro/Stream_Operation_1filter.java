package demoStream_Intro;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;



/*
 * // 中间操作
 * 多个中间操作可以连接起来形成一个流水线,除非流水线上触发终止操作, 否则中间操作不会执行任何处理!
 * 而在终止操作时一次性全部处理,成为 “惰性求值”.
 * 中间操作有多种可选项, 	筛选(filter,limit,skip,distinct),  notice that distinct() removes duplicate based on hashCode() and equals()
 * 						映射(map), 
 * 						排序(sort, sorted), 
 * 						查找(allMatch, findFirst)等
 * 
 * The most interesting part to know about the intermediate operations is that they are lazy. 
 * The intermediate operations will not be invoked until the terminal operation is invoked. 
 * This is very important when we are processing larger data streams. 
 * The process only on demand principle drastically improves the performance. 
 * The laziness of the intermediate operations help to invoke these operation in one pass. 
 * Now, if you are not clear with single pass, please wait until we dive into more details about Java 8 Streams during our subsequent discussions. 
 */
public class Stream_Operation_1filter {

	List<Employee> employees = Arrays.asList(
			new Employee(1,33, "Ming Yan", 60000),
			new Employee(2,34, "Xinxin Lai", 30000),
			new Employee(3,34, "Zhen Yu", 40000),
			new Employee(4,32, "Jingcun Han", 50000),
			new Employee(5,34, "Yongbo Yun", 70000),
			new Employee(5,34, "Yongbo Yun", 70000),
			new Employee(5,34, "Yongbo Yun", 70000),
			new Employee(5,34, "Yongbo Yun", 70000),
			new Employee(6,36, "Zhuang Wu", 120000),
			new Employee(7,28, "Yue Teng", 80000)
	);
	/*
	 * Intermediate Operations:
	 * filter(Predicate<? super String> predicate), receive a Lambda Expression, and exclude certain values from the stream
	 * limit(maxSize), cut the stream according to the maxSize
	 * skip(n), Returns a stream consisting of the remaining elements of this stream after discarding the first n elements of the stream. 
	 * 			If this stream contains fewer than n elements then an empty stream will be returned.
				opposite to limit
	 * distinct(), use hashCode() and equals() methods to exclude repeated elements
	 */
	@Test
	public void test()
	{
		Stream.of("aa","bb","cc","dd","aa")
				.filter((x)->x.length()==2)
				.distinct()
				.skip(2)
				.forEach(System.out::println);;
		
	}
	
	// internal iteration
	@Test
	public void test2()
	{
//		https://howtodoinjava.com/java-8/java-8-tutorial-internal-vs-external-iteration/
		

//		instead of controlling the iteration, client let it handle by library and only provide the code which must be executed for all/some of data elements.
		Stream<Employee> streamEmp = employees.stream()
		.filter((x)->
		{   // Notice the result, Stream API complete the iteration for us, we don't need to iterate to filter
			System.out.println("Intermediate operation will not be executed until terminal operation");
			// this is called internal iteration
			return x.getSalary()>30000;
		});
		// without this terminal operation, the intermediate operations will not be executed 
		//The laziness of the intermediate operations help to invoke these operation in one pass. 
		streamEmp.forEach(System.out::println);

	}
	
	// external iteration
	public void test3()
	{
		Iterator<Employee> it  = employees.iterator();
		while(it.hasNext())
		{
			System.out.println(it.next());
		}
	}
	
	// limit operation
	@Test
	public void test4()
	{
		employees.stream()
				.filter((x)->{
					System.out.println("test iterator");
					return x.getAge()<33;
					})
				.limit(2)
				.forEach(System.out::println);
		
		System.out.println("--------------------------");
		System.out.println("Instead of using println, you can get it and return the result");
		Employee em = test5();
		System.out.println(em);
	}
	
	@Test
	public Employee test5()
	{
		return employees.stream().filter((e)->e.getAge()>35)
				.findFirst().get();
	}
	
	//skip
	@Test
	public void test6()
	{
		employees.stream().skip(3).forEach(System.out::println);
		// the opposite to limit(maxSize), limit is to retrieve the first maxSize data
		// skip(n) is to skip the first n records 
	}
	
	
	//distinct
	@Test
	public void test7()
	{
		employees.stream()
				.filter((x)->x.getSalary()>50000)
				.distinct()
				.forEach(System.out::println);
		// notice if there are still duplicate records when using distinct()
		// if there are, probably because you forgot to override the hashCode() and equals() methods in Employee class.
	}
	
}
