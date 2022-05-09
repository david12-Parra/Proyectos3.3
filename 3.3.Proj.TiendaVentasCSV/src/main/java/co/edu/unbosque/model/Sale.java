package co.edu.unbosque.model;

public class Sale {
	
	private String invoice;
	private String stockCode;
	private String description;
	private Integer quantity;
	private String date;
	private Double price;
	private Integer customerID;
	private String country;
	
	public Sale() {}
	
	public Sale(String number, String code, String report, Integer count, String time, Double buy, Integer id, String home) {
		invoice = number;
		stockCode = code;
		description = report;
		quantity = count;
		date = time;
		price = buy;
		customerID = id;
		country = home;
	}
	
	public String toString() {
		return "Invoice N°: " + invoice + "\n" + 
	           "Stock code: " + stockCode + "\n" +
			   "Description: " + description + "\n" +
	           "Quantity: " + quantity + "\n" +
			   "Date: " + date + "\n" +
	           "Price: " + price + "\n" + 
			   "Customer id: " + customerID + "\n" + 
	           "Country: " + country + "\n";
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getCustomerID() {
		return customerID;
	}

	public void setCustomerID(Integer customerID) {
		this.customerID = customerID;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}	
}