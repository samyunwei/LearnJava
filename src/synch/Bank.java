package synch;
import java.util.concurrent.locks.*;

public class Bank {
	private  final double[] accounts;
	private Lock banckLock;
	private Condition sufficientFunds;
	
	public Bank(int n,double initialBalance)
	{
		accounts = new double[n];
		for(int i = 0;i< accounts.length;i++)
		{
			accounts[i] = initialBalance;
		}
		banckLock = new ReentrantLock();
		sufficientFunds = banckLock.newCondition();
	}
	
	
	public void transfer(int from ,int to,double amount) throws InterruptedException
	{
		banckLock.lock();
		try
		{
			while(accounts[from] < amount)
			{
				sufficientFunds.await();
			}
			System.out.print(Thread.currentThread());
			accounts[from] -= amount;
			System.out.printf("%10.2f from %d to %d",amount,from,to);
			accounts[to] += amount;
			System.out.printf("Total Balance:%10.2f%n",getTotalBalance());
			sufficientFunds.signalAll();
		}
		finally
		{
			banckLock.unlock();
		}
		
		
	}
	
	public double getTotalBalance()
	{
		banckLock.lock();
		try {
			double sum = 0;
			for (double a : accounts) {
				sum += a;
			}
			return sum;
		} finally {
			banckLock.unlock();
		}
	}
	
	public int size()
	{
		return accounts.length;
	}
	
}
