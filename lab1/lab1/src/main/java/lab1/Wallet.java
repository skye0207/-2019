package lab1;

public class Wallet {
	
	int[] money = {50, 20, 5, 5, 1, 1, 1};
	
	public boolean canGive(int givenMoney) {
		int leftMoney = givenMoney;
		for (int i = 0; i < this.money.length ; i++ ) {
			if(leftMoney > money[i]) {
				leftMoney -= money[i];
			} else if (leftMoney == money[i]) {
				return true;
			}
		}
		return false;
	}

}
