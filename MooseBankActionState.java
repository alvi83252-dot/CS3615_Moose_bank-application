public class MooseBankActionState{
	
	private double accountA;
	private double accountB;
	private double accountC;
	
	private boolean accessing=false; // true a thread has a lock, false otherwise
	private int threadsWaiting=0; // number of waiting writers

// Constructor	
	
	MooseBankActionState() {
		accountA = 1000;
		accountB = 1000;
		accountC = 1000;
	}

//Attempt to aquire a lock
	
	  public synchronized void acquireLock() throws InterruptedException{
	        Thread me = Thread.currentThread(); // get a ref to the current thread
	        System.out.println(me.getName()+" is attempting to acquire a lock!");	
	        ++threadsWaiting;
		    while (accessing) {  // while someone else is accessing or threadsWaiting > 0
		      System.out.println(me.getName()+" waiting to get a lock as someone else is accessing...");
		      //wait for the lock to be released - see releaseLock() below
		      wait();
		    }
		    // nobody has got a lock so get one
		    --threadsWaiting;
		    accessing = true;
		    System.out.println(me.getName()+" got a lock!"); 
		  }

		  // Releases a lock to when a thread is finished
		  
		  public synchronized void releaseLock() {
			  //release the lock and tell everyone
		      accessing = false;
		      notifyAll();
		      Thread me = Thread.currentThread(); // get a ref to the current thread
		      System.out.println(me.getName()+" released a lock!");
		  }
	
	
    /* The processInput method */

	public synchronized String processInput(String myThreadName, String theInput) {
		String theOutput = null;

		String[] commandParts = theInput.split(" ");

		try {
			if (commandParts[0].equalsIgnoreCase("Add_money")) {

				String account = commandParts[1];
				double value = Double.parseDouble(commandParts[2]);

				addMoney(account, value);
				theOutput = "Add_money completed " + displayBalances();
			}
			else if (commandParts[0].equalsIgnoreCase("Subtract_money")) {
				String account = commandParts[1];
				double value = Double.parseDouble(commandParts[2]);
				subtractMoney(account, value);
				theOutput = "Subtract_money completed " + displayBalances();
		}
		else if (commandParts[0].equalsIgnoreCase("Transfer_money")) {
			
				String fromAccount = commandParts[1];
				String toAccount = commandParts[2];
				double value = Double.parseDouble(commandParts[3]);
				transferMoney(fromAccount, toAccount, value);
				theOutput = "Transfer_money completed " + displayBalances();
			}
			else {
				theOutput = "Invalid request";
			}
		} catch (Exception e) {
			theOutput = "Command error " + e.getMessage();
		}

		System.out.println(theOutput);
		return theOutput;
	}

	private void addMoney(String account, double value) {
		if (account.equalsIgnoreCase("A")) {
			accountA += value;
		} else if (account.equalsIgnoreCase("B")) {
			accountB += value;
		} else if (account.equalsIgnoreCase("C")) {
			accountC += value;
		}
	}

	private void subtractMoney(String account, double value) {
		if (account.equalsIgnoreCase("A")) {
			accountA = accountA - value;
		} else if (account.equalsIgnoreCase("B")) {
			accountB = accountB - value;
		} else if (account.equalsIgnoreCase("C")) {
			accountC = accountC - value;
		}
	}

	private void transferMoney(String fromAccount, String toAccount, double value) {
		subtractMoney(fromAccount, value);
		addMoney(toAccount, value);
	}

	private String displayBalances() {
		return "A = " + accountA + ", B = " + accountB + ", C = " + accountC;
	}
}

