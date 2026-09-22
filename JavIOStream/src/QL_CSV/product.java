package QL_CSV;

public class product {
	private final  String code;
	private final String name;
	private final double untiPrice;
	private final int quantity;
	
	public product(String code , String name, double untiPrice, int quantity) {
		if(code == null || code.isBlank()) {
			throw new IllegalArgumentException("Mã không được rỗng");
		}
		if(name == null || name.isBlank()) {
			throw new IllegalArgumentException("Tên không được rỗng");
		}
		if(untiPrice <= 0 || quantity < 0) {
			throw new IllegalArgumentException("Giá hoặc số lượng không hợp lệ");
		}
		this.code = code;
		this.name = name;
		this.untiPrice = untiPrice;
		this.quantity = quantity;
	}
	public double inventoryValue() {
		return untiPrice * quantity;
	}
	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	public double getUntiPrice() {
		return untiPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	@Override
	public String toString() {
		return "%s - %s : %,.0f VND".formatted(code,name,inventoryValue());
	}

}