package capsule;

public class Sales {
	
	private Accounting accounting;
	
	public Sales() {}
	
	public Sales(Accounting accounting) {
		this.accounting = accounting;
	}
	
	// 제품 판매
	public int sellProduct(Coffee coffee , int salesCnt) {
		
		//커피 객체에 판매를 등록하고 결제금액을 반환받는다.
		int payPrice = coffee.registerSales(salesCnt);
	
		//결제금액이 0보다 크면 결제가 정상적으로 진행
		if(payPrice > 0) {
			//계좌 객체에 매출을 등록한다.
			accounting.registerSales(payPrice);
		}
		//결제금액을 반환
		return payPrice;
	}
	
	
}
