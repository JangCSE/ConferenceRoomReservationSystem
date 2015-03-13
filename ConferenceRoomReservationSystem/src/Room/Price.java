package Room;

import java.io.Serializable;

public class Price implements Serializable{

	private int pricePerDay = 0;
	
	public Price() {
		setPricePerDay(10000);
	}

	public Price(int pricePerHalf) {
		setPricePerDay(pricePerHalf);
	}

	public int getPricePerHalf() {
		return pricePerDay;
	}

	public void setPricePerDay(int pricePerDay) {
		if (pricePerDay < 10000) {
			System.err.println("Too short pricePerDay......");
		} else {
			this.pricePerDay = pricePerDay;
		}
	}

}