package demoStream_Intro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

/*
 * What is Stream? 
 * http://java.amitph.com/2014/01/understanding-java-8-streams-api.html
 * Stream 是数据渠道, 用于操作数据源(集合, 数组)所生成的元素序列
 * 集合讲的是数据, 流讲的是计算
 * 注意:
 * 1.	Stream 自己不会储存元素
 * 2.	Stream 不会改变源数据, 相反, Stream 会返回一个持有结果的新Stream
 * 3.	Stream 的操作是延迟执行的. 这意味着他们会等到需要使用结果的时候才去执行
 */
public class Stream_Create {
	/*
	 * Stream 的操作步骤:
	 * 1.	创建Stream
	 * 		一个数据源(集合,或者数组), 获取一个流
	 * 2.	中间操作 intermediate operation
	 * 		一系列的流水线操作,对数据源的数据进行处理, 筛选,切片等等
	 * 3.	终止操作(终端操作) terminal operation
	 * 		执行中间操作(流水线操作),并产生结果.
	 */
	
	@Test
	public void test()
	{
		//1.		创建Stream
		// Method 1: 通过Collection提供的stream() 或者 parallelStream()方法, 串行流 vs 并行流
		List<String> list = new ArrayList<>();
		Stream<String> stream = list.stream();
		
		//Method 2: 通过Arrays 中的静态方法,获取数组流
		Employee[] emps =new Employee[10];
		Stream<Employee> stream2 = Arrays.stream(emps);
		
		//Method 3: 通过Stream类中的静态方法 of()
		Stream<String> stream3 = Stream.of("aa","bb","cc","dd");
		stream2 = Stream.of(emps);
		
		//Method 4: 创建无限流
		// 迭代 iterate
		Stream<Integer> stream4 = Stream.iterate(0, (x)->x+2);
		//现在, stream4就是一个无限流,但是运行,什么也不显示
		//只有做了终止操作才会运行之前两步操作
		//所以, 下面代码会打印出来无限数据流 stream4;  stream4.forEach((x)->System.out.println(x));
		//或者, 你可以使用方法引用;   stream4.forEach(System.out::println);
		// 你发现真的是个无限流.... 必须要手动停止才行, 太麻烦了.所以我们采取一些中间操作
		 stream4.limit(10)
				.forEach(System.out::println);
		 //我们现在打印了无限流中的前十个元素, 注意 forEach()的方法,里面传的是一个Consumer FunctionalInterface
		 
		 //生成
//		 Stream.generate(()->Math.random())
//		 		.forEach(System.out::println);
		 // 你会发现, 这依然是一个无限流.... 无限的随机数, 那我们还用limit(maxSize) 方法 取出前几个
//		 Stream.generate(()->Math.random())
//		 	.limit(10)
//	 		.forEach(System.out::println);
		 // 当然我们还可以加其他的过滤方法, 比如说, 只选择 大于0.9的
//		 Stream.generate(()->Math.random())
//		 	.filter((x)->x>0.9)
//		 	.limit(10)
//	 		.forEach(System.out::println);
		 // 换换中间操作的顺序, 产生的结果就会不同
		 Stream.generate(()->Math.random())
		 	.limit(10)
		 	.filter((x)->x>0.8)
	 		.forEach(System.out::println);
		
	}
	
}
