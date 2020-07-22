package poly;

public class PremiumCoffee extends Coffee {
	

	private int packagingCost = 2000;
	
	public PremiumCoffee() {}
	
	public PremiumCoffee(Accounting accounting) {
		super.accounting = accounting;
	}
	
	@Override
	protected int deductStock(int coffeeCnt , int price) {
		
		//매입가 + 포장비용 , 프리미엄커피 재고 확보를 위한 비용
		int totalCost = purchasePrice + packagingCost;
		
		//안전재고 확인
		secureSafetyStock();
		
		if(stock > coffeeCnt) {
			stock -= coffeeCnt;
			return price * coffeeCnt;
		}else if(addStock(coffeeCnt , totalCost) >  0 ) {
			stock -= coffeeCnt;
			return price * coffeeCnt;
		}else {
			return 0;
		}
		
		
	}
	
	
	//안전재고 확보
	@Override
	protected void secureSafetyStock() {
		
		int totalCost = purchasePrice + packagingCost;
		
		if( stock < safetyStock){
			System.out.println("안전재고가 부족해 재고를 확보합니다.");
			int needCnt = safetyStock * 2;
			addStock(needCnt , totalCost);
		}
		
	}
	
	
	
}
