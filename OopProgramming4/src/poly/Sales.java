package poly;

public class Sales {
	
	
	private Accounting accounting = Accounting.getAccounting();
	
	public Sales() {}
	
	
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
	
	// 환불등록
	public int refundProduct(Coffee coffee , int fefundCnt) {
		
		int refundPrice = coffee.registerRefund(fefundCnt);
		
		return refundPrice;
	}
	
	public int returnProduct(Coffee coffee , int returnCnt) {
		
		int returnPrice = coffee.registerReturn(returnCnt);
		if(returnPrice > 0) {
			accounting.registerSales(returnPrice);
		}
		return returnPrice;
	}
	
	
	
}
