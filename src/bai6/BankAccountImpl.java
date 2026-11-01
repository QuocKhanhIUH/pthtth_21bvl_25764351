package bai6;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class BankAccountImpl extends UnicastRemoteObject implements BankAccount {
	private double balance;

	public  BankAccountImpl(double initiaBalance) throws RemoteException {
		super();
		this.balance = initiaBalance;
		// TODO Auto-generated constructor stub
	}

	@Override
	public synchronized double getBalance() throws RemoteException {
		// TODO Auto-generated method stub
		return balance;
	}

	@Override
	public synchronized void deposit(double amount) throws RemoteException {
		// TODO Auto-generated method stub
		if(amount > 0) balance += amount;
		
	}

	@Override
	public synchronized boolean withdraw(double amount) throws RemoteException {
		// TODO Auto-generated method stub
		if(amount <= 0 || amount > balance) return false;
		balance -= amount;
		return true;
	}

}
