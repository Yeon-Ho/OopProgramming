package poly;

public class Accounting {
	

	private int balance; // 잔고
	private int salesPrce; // 매출
	private int expences;//지출
	
	//기본 생성자
	private Accounting() { }
	private static Accounting accounting = null;
	
	public static Accounting getAccounting() {
		
		if(accounting == null) {
			accounting = new Accounting();
		}
		return accounting;
		
	}
	
	
	
	//Getter , Setter
	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getSalesPrce() {
		return salesPrce;
	}

	public void setSalesPrce(int salesPrce) {
		this.salesPrce = salesPrce;
	}

	public int getExpences() {
		return expences;
	}

	public void setExpences(int expences) {
		this.expences = expences;
	}
	
	// 매출등록을 담당하는 메소드
	public int registerSales(int payPrice) {
		
		balance += payPrice;
		salesPrce += payPrice;
		
		return balance;
	}
	
	//지출등록을 담당하는 메소드
	public boolean registerExpences(int expencesPrice) {
		
		if(balance > expencesPrice) {
			balance -= expencesPrice;
			expences += expencesPrice;
			return true;
		}else {
			return false;
		}
		
	}
	

	
	
	
	
}
