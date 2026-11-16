package bai9;
import java.io.Serializable;

public class Item implements Serializable{
	private static final long seriaVersionUID = 1L;
	public String name;
	public String seller;
	public double startPrice;
	public double price;
	public String highesBidder;
	public String highestBidder;
	
	public Item(String name, String seller, double startPrice, double price, String highesBidder) {
		this.name = name;
		this.seller = seller;
		this.startPrice = startPrice;
		this.price = price;
		this.highesBidder = highesBidder;
	}
	@Override
	public String toString()
	{
		return name + " | Người bán: " +seller
				+ " | Giá khởi điểm: " +startPrice
				+ " | Giá hiện tại: " +price
				+ " | Dẫn đầu: " +(highesBidder == null ? "(chưa có)" : highesBidder);
	}

}
