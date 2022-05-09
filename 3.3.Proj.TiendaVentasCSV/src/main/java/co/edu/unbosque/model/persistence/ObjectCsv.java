package co.edu.unbosque.model.persistence;
import java.io.FileReader;
import java.util.*;
import com.opencsv.CSVReader;
import co.edu.unbosque.model.Sale;

public class ObjectCsv implements Operacionable {

	
	public List<?> listSales() {
		List <Sale> list = new ArrayList <>();
		try (CSVReader reader = new CSVReader(new FileReader("src/main/resources/Archives/data.csv"))){
			String array [];
			while((array = reader.readNext()) != null) {
				Sale sale = new Sale(array[0], array[1], array[2], array[3].length() != 0? Integer.parseInt(array[3]) : 0, array[4], 
			                         Double.parseDouble(array[5]), array[6].length() != 0? Integer.parseInt(array[6]) : 0, array[7]);
				list.add(sale);
			}
		} catch(Exception exception) {}
		return list;
	}

	@Override
	public List<?> listSalesDistinct() {
		var list = new ArrayList <Sale> ();
		
		return list;
	}

	@Override
	public Double sumTotalSales() {
		return null;
	}

	@Override
	public String findByInvoiceNo(String invoiceNo) {
		return null;
	}

	@Override
	public String findByInvoiceNoList(String invoiceNo) {
		return null;
	}

	@Override
	public Integer countByStockCode(String stockCode) {
		return null;
	}

	@Override
	public String avgMonthlySales(Boolean groupByCountry) {
		return null;
	}

	@Override
	public Map<?, ?> findPartiallyByDescription(String search) {
		return null;
	}

	@Override
	public String findPartiallyByDescription(String search, Boolean order) {
		return null;
	}

	@Override
	public String findPartiallyByDescription(String search, Integer initMont, Integer endMonth) {
		return null;
	}	
}