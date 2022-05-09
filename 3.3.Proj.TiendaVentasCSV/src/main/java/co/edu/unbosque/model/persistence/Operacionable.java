package co.edu.unbosque.model.persistence;
import java.util.*;

public interface Operacionable {
	
	public abstract List <?> listSales();
	public abstract List <?> listSalesDistinct();
	public abstract Double sumTotalSales();
	public abstract String findByInvoiceNo(String invoiceNo);
	public abstract String findByInvoiceNoList(String invoiceNo);
	public abstract Integer countByStockCode(String stockCode);
	public abstract String avgMonthlySales(Boolean groupByCountry);
	public abstract Map <?, ?> findPartiallyByDescription(String search);
	public abstract String findPartiallyByDescription(String search, Boolean order);
	public abstract String findPartiallyByDescription(String search, Integer initMont, Integer endMonth);
}
