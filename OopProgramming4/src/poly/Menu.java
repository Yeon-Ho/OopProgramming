package poly;

import java.util.Scanner;

public class Menu {
		
	
	Scanner sc = new Scanner(System.in);
	
	private Accounting accounting;
	private Sales sales;
	
	//사용자가 판매할 커피의 가짓수를 결정할 수 있다.
	private Coffee[] coffeeArr;
	
	
	public Menu() { 
		
		accounting = Accounting.getAccounting();
		
		
		System.out.println(" 잔고를 입력하세요 : ");
		accounting.setBalance(sc.nextInt()); //사용자에게 잔고를 입력받아서 accounting 객체에 세팅
		
		sales = new Sales();
		
		System.out.println(" ===================================== ");
		System.out.println("커피 종류의 가짓수를 입력하세요 : ");
		//사용자가 지정한 커피 종류의 수만큼 커피배열 생성
		coffeeArr = new Coffee[sc.nextInt()];
		
		System.out.println(" ===================================== ");
		
		//커피 객체를 생성해서 커피객체배열에 추가
		for(int i = 0 ; i<coffeeArr.length; i++) {
			
			int no = i + 1;
	
			System.out.println(no+ "번째 커피의 등급을 입력하세요 . 프리미엄 : p | 일반 n");
			Coffee coffee = new CoffeeFactory().createCoffee(sc.next());
			
			System.out.println(no + "번째 커피의 이름을 등록하세요 : ");
			coffee.setName(sc.next());
			
			System.out.println(no + "번째 커피의 판매가를 등록하세요 : ");
			coffee.setSalesPrice(sc.nextInt());
			
			System.out.println(no + "번째 커피의 매입가를 등록하세요 : ");
			coffee.setPurchasePrice(sc.nextInt());
			
			System.out.println(no + "번째 커피의 재고를 등록하세요 : ");
			coffee.setStock(sc.nextInt());
			
			System.out.println(no + "번째 커피의 안전재고 등록하세요 : ");
			coffee.setSafetyStock(sc.nextInt());
			
			System.out.println("=================================");
			coffeeArr[i] = coffee;
			
		}
		
	}
	
	public void showIndex() {
		do {
			
			System.out.println("\n =========== 메뉴 ===============");
			System.out.println("메뉴(0)");
			System.out.println("현황(1)");
			System.out.println("종료(2)");
			System.out.print("입력 : ");
			int intputMenu = sc.nextInt();
			
			switch(intputMenu) {
				case 0 : showMenu(); break;
				case 1 : showStats(); break;
				case 2 : System.out.println(" * 프로그램 종료 "); return;
				default : System.out.println(" * 잘못된 번호를 입력하였습니다.");
			}
			
		}while(true);
		
	}
	
	private void showMenu() {
		
		System.out.println(" \n * * * * * 음료 메뉴 ");
		for(int i = 0 ; i<coffeeArr.length; i++	) {
			System.out.println(coffeeArr[i].getName()+ "("+ i +")" );
		}
		
		System.out.println("\n 판매등록(0)");
		System.out.println(" 고객 환불 등록(1)");
		System.out.println(" 반품 등록(2)");
		System.out.print("입력 : ");
		int inputMenu = sc.nextInt();
		
		switch(inputMenu) {
			case 0 : showSales(); break;
			case 1 : showRefund(); break;
			case 2 : showReturn(); break;
			default : System.out.println(" * 잘못된 번호를 입력하였습니다.");
		}
		
		
	}

	
	private void showSales() {
		System.out.println("\n * 판매한 커피의 코드를 입력하세요 : ");
		int inputType = sc.nextInt();
		
		System.out.println(" * 주문량을 입력하세요 : ");
		int orderCnt = sc.nextInt();
		
		int payPrice = sales.sellProduct(coffeeArr[inputType], orderCnt);
		if(payPrice > 0 ) {
			System.out.println("\n 제풍명 : " + coffeeArr[inputType].getName()
					+ " \n 판매가 : "+ coffeeArr[inputType].getSalesPrice()
					+ " \n 구매갯수 : "+ orderCnt
					+ " \n 결제 금액 : "+ payPrice
					+ " \n 남은 재고 : "+ coffeeArr[inputType].getSafetyStock()
					);
		}else {
			System.out.println(" * 주문이 취소 되었습니다");
		}
		
	}
	
	private void showStats() {
		
		System.out.println("\n========================================");
		
		//모든 커피들의 재고와 판매량을 출력
		for(Coffee coffee : coffeeArr) {
			System.out.printf("%10s 재고 : %d | 판매량 : %d \n "
					 , coffee.getName() , coffee.getStock() , coffee.getSalesCount()
					 );
		}
		System.out.println(" * 잔고  :  "+ accounting.getBalance()
						+ " | 매출 : " +accounting.getSalesPrce() 
						+ " | 지출 : " +accounting.getExpences());
	}

	private void showRefund() {
		System.out.println("\n * 판매한 커피의 코드를 입력하세요 : ");
		int inputType = sc.nextInt();
		
		System.out.println(" * 주문량을 입력하세요 : ");
		int refundCnt = sc.nextInt();
		
		int refundPrice = sales.refundProduct(coffeeArr[inputType], refundCnt);
		
		if(refundPrice > 0 ) {
			System.out.println("\n 제풍명 : " + coffeeArr[inputType].getName()
					+ " \n 판매가 : "+ coffeeArr[inputType].getSalesPrice()
					+ " \n 구매갯수 : "+ refundCnt
					+ " \n 결제 금액 : "+ refundPrice
					+ " \n 남은 재고 : "+ coffeeArr[inputType].getStock()
					);
		}else {
			System.out.println(" * 환불이 취소 되었습니다");
		}
		
	}
	
	private void showReturn() {
		System.out.println("\n * 판매한 커피의 코드를 입력하세요 : ");
		int inputType = sc.nextInt();
		
		System.out.println(" * 주문량을 입력하세요 : ");
		int returnCnt = sc.nextInt();
		
		int retrunPrice = sales.returnProduct(coffeeArr[inputType], returnCnt);
		
		if(retrunPrice > 0 ) {
			System.out.println("\n 제풍명 : " + coffeeArr[inputType].getName()
					+ " \n 판매가 : "+ coffeeArr[inputType].getSalesPrice()
					+ " \n 구매갯수 : "+ returnCnt
					+ " \n 결제 금액 : "+ retrunPrice
					+ " \n 남은 재고 : "+ coffeeArr[inputType].getStock()
					);
		}else {
			System.out.println(" * 환불이 취소 되었습니다");
		}
		
	}
	

}

