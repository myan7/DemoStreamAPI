package com.youtube.java8.streamAPI;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


//from Youtube Java 8 Stream API Joe James
public class JavaStreams {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		//	1.	IntegerStream
		IntStream.range(1, 10)
				.forEach(System.out::print);
		System.out.println();
		
		//	2.	IntegerStream with skip
		IntStream.range(1, 10)
		.skip(5)
		.forEach(System.out::println);
		System.out.println();
		
		//	3.	IntegerStream with sum
		System.out.println(
				IntStream.range(1, 10)
						.sum()
		);
		System.out.println();
	
		//	4.	Stream.of, sorted and findFirst
		Stream.of("Aval","Aneri","Alberto")
				.sorted()
				.findFirst()
				.ifPresent(System.out::println);
		System.out.println();
		
		//	5.	Stream from Array, sort, filter and print
		String[] names = {"Al", "Ankit", "Ming", "Stephen", "Damon","Shakira","Amanda","Hans","Smantha"};
		Arrays.stream(names)   //Same as Stream.of(names)
				.filter(x->x.startsWith("S"))
				.sorted()
				.forEach(System.out::println);
		System.out.println();
		
		//	6.	avaerage of squares of an int array
		Arrays.stream(new int[] {2,4,6,8,10})
				.map(x->x*x)
				.average()
				.ifPresent(System.out::println);
		System.out.println();
		
		//	7.	Stream from List, filter and print
		List<String> people = Arrays.asList("Al", "Ankit", "Ming", "Stephen", "Damon","Shakira","Amanda","Hans","Smantha","Susan");
		
		people.stream()
				.map(String::toLowerCase)
				.filter(x-> x.startsWith("a"))
				.forEach(System.out::println);
		System.out.println();
		
		//	8.	stream rows from a text file, sort, filter and print
//		Stream<String> bands = Files.lines(Paths.get("bands.txt"));
//		bands.sorted()
//			.filter(x->x.length()>13)
//			.forEach(System.out::println);
//		bands.close();
//		System.out.println();
		
		//	9.	Stream rows from text file and save to list
		List<String> bands2 = Files.lines(Paths.get("bands.txt"))
								.filter(x->x.contains("Did"))
								.collect(Collectors.toList());
		bands2.forEach(System.out::println);
		System.out.println();
		
		//	10.	Stream rows from csv file and count
		Stream<String> rows1 = Files.lines(Paths.get("data.txt"));
		int rowCount = (int)rows1
				.map(x->x.split(","))
				.filter(x-> x.length == 3)
				.count();
		System.out.println("Rows count "+ rowCount);
		rows1.close();
				
		System.out.println();
		
		//	11.	Stream rows from csv file, parse data from rows
		Stream<String> rows2 = Files.lines(Paths.get("data.txt"));
		rows2.map(x->x.split(","))
			.filter(x -> x.length ==3)   // filter out bad rows
			.filter(x->Integer.parseInt(x[1])>15)  // filter any row with value less than 15
			.forEach(x->System.out.println(x[0]+" "+x[1]+" "+x[2]));
		rows2.close();
		System.out.println();
		
		//	12.	Stream rows from csv file, and fields in the HashMap
		Stream<String> rows3 = Files.lines(Paths.get("data.txt"));
		Map<String, Integer> map = new HashMap<>();
		map = rows3.map(x->x.split(","))
					.filter(x -> x.length ==3)
					.filter(x -> Integer.parseInt(x[1])>15)
					.collect(Collectors.toMap(x->x[0], x->Integer.parseInt(x[1])));
		rows3.close();
		for(String key: map.keySet())
		{
			System.out.println(key+ " ---------- " + map.get(key));
		}
			System.out.println();
			
		//	13.	Reduction - sum
			double total = Stream.of(7.3, 1.5, 4.8)
							.reduce(0.0, (Double a, Double b)->a+b);
		System.out.println("Total = "+ total);
		System.out.println();
		
		//	14.	Reduction - summary statistics
		// only work for integer
		IntSummaryStatistics summary = IntStream.of(7,2,19,88,73,4,10)
										.summaryStatistics();
		System.out.println(summary);
		
		
	}
	

}
