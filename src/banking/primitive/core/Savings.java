/*
  File: banking.primitive.core.Account.java	
  Author: William Wen
  Date:	April 9, 2017
  
  Description: this is the implementation of the Savings class.
*/
package banking.primitive.core;

/**
  Class: banking.primitive.core.Savings	

  Description: this class extends the abstract Account class with
               implementation specific to the logic for Savings accounts
*/
public class Savings extends Account {
	private static final long serialVersionUID = 111L;
	private int numWithdraws = 0;

	/**
	  Method: Savings
	  Inputs: name the name of the account being created
	  Returns:

	  Description: this constructor creates a savings account with the requested
	               name
	*/
	public Savings(String name) {
		super(name);
	}

	/**
	  Method: Savings
	  Inputs: name the name of the account being created
	          balance the balance of the account being created
	  Returns:

	  Description: this constructor creates a savings account with the requested
	               name and balance
	*/
	public Savings(String name, float balance) throws IllegalArgumentException {
		super(name, balance);
	}

	/**
	 * A deposit comes with a fee of 50 cents per deposit
	 */
	public boolean deposit(float amount) {
		if (getState() != State.CLOSED && amount > 0.0f) {
			balance = balance + amount - 0.50F;
			if (balance >= 0.0f) {
				setState(State.OPEN);
			}
		}
		return false;
	}

	/**
	 * A withdrawal. After 3 withdrawals a fee of $1 is added to each withdrawal.
	 * An account whose balance dips below 0 is in an OVERDRAWN state
	 */
	public boolean withdraw(float amount) {
		if (getState() == State.OPEN && amount > 0.0f) {
			balance = balance - amount;
			numWithdraws++;
			if (numWithdraws > 3)
				balance = balance - 1.0f;
			// KG BVA: should be < 0
			if (balance <= 0.0f) {
				setState(State.OVERDRAWN);
			}
			return true;
		}
		return false;
	}
	
	/**
	  Method: getType
	  Inputs: 
	  Returns: Type enum of "Checking" of this account type

	  Description: this method returns the enum type of this account
	*/
	public Type getType() {
		return Type.SAVINGS; 
	}

	/**
	  Method: toString
	  Inputs: 
	  Returns: the Type of the account along with its name and balance

	  Description: this constructor creates a savings account with the requested
	               name and balance
	*/
	public String toString() {
		return "Savings: " + getName() + ": " + getBalance();
	}
}
