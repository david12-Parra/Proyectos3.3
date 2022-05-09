package co.edu.unbosque.model.persistence;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

import co.edu.unbosque.model.Sale;

public class StreamCsv implements Operacionable {
	
	public List <Sale> listSales(){
		List <Sale> list = new ArrayList <>();
		try (var stream = Files.lines(Paths.get("src/main/resources/Archives/data.csv"), Charset.defaultCharset())){
			list = stream.map(x -> x.split("[\\,]")).map(array -> {
				Sale sale = new Sale(array[0], array[1], array[2], array[3].length() != 0? Integer.parseInt(array[3]) : 0, array[4], 
						             Double.parseDouble(array[5]), array[6].length() != 0? Integer.parseInt(array[6]) : 0, array[7]);
				return sale;
			}).toList();
		} catch(Exception error) { error.printStackTrace(); }
		return list;
	}
	
	
	public List <Sale> listSalesDistinct(){
		return listSales().stream()
				          .distinct()
				          .toList();
	}
	
	
	public Double sumTotalSales() {
		return listSales().stream()
				          .flatMapToDouble(x -> DoubleStream.of(x.getPrice() * x.getQuantity()))
				          .sum();
	}
	
	
	public String findByInvoiceNo(String invoiceNo) {
		return listSales().parallelStream()
						  .filter(x -> x.getInvoice().compareTo(Optional.ofNullable(invoiceNo).orElse("")) == 0)
				          .map(x -> x.toString())
				          .findFirst().orElse("La factura ingresada no se encontro");
	}
	
	public String findByInvoiceNoList(String invoiceNo){
		return listSales().parallelStream()
						  .filter(x -> x.getInvoice().compareTo(Optional.ofNullable(invoiceNo).orElse("")) == 0)
						  .map(x -> x.toString())
						  .collect(Collectors.joining("\n"));
	}
	
	
	public Integer countByStockCode(String stockCode) {
		return listSales().stream()
				          .filter(x -> x.getStockCode().compareTo(Optional.ofNullable(stockCode).orElse("")) == 0)
				          .flatMapToInt(x -> IntStream.of(x.getQuantity()))
				          .sum();
	}
	
	
	public String avgMonthlySales(Boolean groupByCountry) {
		if(Optional.ofNullable(groupByCountry).orElse(true)) {
			return listSales().stream()
					          .collect(Collectors.groupingBy(x -> x.getCountry(), Collectors.averagingDouble(x -> x.getPrice() * x.getQuantity())))
					          .entrySet()
					          .stream()
					          .map(x -> x.getKey() + " = " + x.getValue())
					          .collect(Collectors.joining("\n"));    
		}else {
			return listSales().stream().map(x -> {
				String date [] = x.getDate().split("[\\/]");
				x.setDate(date[0]);
				return x;
			}).collect(Collectors.groupingBy(x -> x.getDate(), Collectors.averagingDouble(x -> x.getPrice() * x.getQuantity())))
			  .entrySet()
			  .stream()
			  .map(x -> x.getKey() + " = " + x.getValue())
			  .collect(Collectors.joining("\n"));
		}
	}
	
	
	public Map <String, Long> findPartiallyByDescription(String search) {
		search = Optional.ofNullable(search).orElse("NULLPOINTER");
		String searchfor = search.toUpperCase();
		return listSales().stream()
				  		  .filter(x -> x.getDescription().matches(".*(" + searchfor + ").*"))
				  		  .collect(Collectors.groupingBy(x -> x.getDescription(), Collectors.counting()));
	}
	
	
	public String findPartiallyByDescription(String search, Boolean order) {
		if(Optional.ofNullable(order).orElse(true)) {
			return findPartiallyByDescription(search).entrySet()
			                                  		 .stream()
			                                  		 .sorted((x,y) -> y.getValue().intValue() - x.getValue().intValue())
			                                  		 .map(x -> x.getKey() + " = " + x.getValue())
			                                  		 .collect(Collectors.joining("\n"));
		}else {
			return findPartiallyByDescription(search).entrySet()
													 .stream()
													 .map(x -> x.getKey() + " = " + x.getValue())
													 .collect(Collectors.joining("\n"));
		}
	}
	
	
	public String findPartiallyByDescription(String search, Integer initMonth, Integer endMonth) {
		var map = findPartiallyByDescription(search);
		var mapping = new TreeMap <String, Long> ();
		var list = listSales().stream()
				               .map(x -> {
				            	   String date [] = x.getDate().split("[\\/]");
				                   x.setDate(date[0]);
				                   return x;
				               }).toList();
		for(int i = 0; i < list.size(); i++) {
			int month = Integer.parseInt(list.get(i).getDate());
			if((map.containsKey(list.get(i).getDescription())) && (initMonth >= month && month <= endMonth)) 
				mapping.put(list.get(i).getDescription(), map.get(list.get(i).getDescription()));
		}
		return mapping.entrySet()
				      .stream()
				      .map(x -> x.getKey() + " = " + x.getValue())
				      .collect(Collectors.joining("\n"));
	}
}