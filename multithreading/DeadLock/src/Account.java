
public class Account {

	private int balance = 0;
	
	public Account(int balance) {
		this.balance = balance;
	}
	
	public void deposit(int amount) {
		this.balance += amount;
	}
	
	public void widthdraw(int amount) {
		this.balance -= amount;
	}
	
	public int getBalance() {
		return this.balance;
	}
	
	public static void transfer(Account acc1, Account acc2, int amount) {
		acc1.widthdraw(amount);
		acc2.deposit(amount);
	}
	
}
