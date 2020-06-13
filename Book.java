/**
*
* Written by: Viktor Pavuk
* 
*/


/**
 * Importing java.util.Scanner for user input
 */
import java.util.Scanner;

/**
 * Our class
 */
public class Book {
	
	/**
	 * Declaring private variables and a static variable number that will track the number of objects of the class created
	 */
	private String title;
	private String name;
	private long ISBN;
	private double price;
	private static int number = 0;
	
	/**
	 * Standard constructor with no parameters, needed later for one of the search functions
	 */
	public Book() {
		title = "";
		name = "";
		ISBN = 0;
		price = 0;	
	}
	
	/**
	 * Standard constructor with 4 parameters, taken from the user input to create an array of objects of the class
	 */
	public Book(String title, String name, long ISBN, double price) {
		this.title = title;
		this.name = name;
		this.ISBN = ISBN;
		this.price = price;	
		number++;
	}
	
	/**
	 * simple accessor for the title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Simple accessor for the author name
	 */
	
	public String getName () {
		return name;
	}
	
	/**
	 * Simple accessor for the ISBN
	 */
	public long getISBN() {
		return ISBN;
	}
	
	/**
	 * Simple accessor for the price
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * Mutator to change the title variable
	 */
	public void setTitle (String title) {
		this.title = title;
	}
	
	/**
	 * Mutator to change the name variable(author)
	 */
	public void setName (String name) {
		this.name = name;
	}
	
	/**
	 * Mutator to change the ISBN variable
	 */
	public void setISBN (long ISBN) {
		this.ISBN = ISBN;
	}

	/**
	 * Mutator to change the price variable
	 */
	public void setPrice (double price) {
		this.price = price;
	}
	
	/**
	 * toString method that returns general information about the object
	 */
	public String toString() {
		return ("	Title: " + title + "\n	Author: " + name + "\n	ISBN: " + ISBN + "\n	Price:$ " + price);
	}
	
	/**
	 * Method that returns the number of objects created so far
	 */
	public static int findNumberOfCreatedBooks() {
		return number;
	}
	
	/**
	 * equals method that compares the price and ISBN of 2 objects and returns true if they are the same
	 */
	public boolean equals(Book b1, Book b2) {
		return (b1.ISBN == b2.ISBN && b1.price == b2.price);
	}
	
	/**
	 * Driver class
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/**
		 * Variables that will be used in the program, including final password, so that no one can change it
		 */
		final String pword = "password";
		String authorNew, titleNew;
		int menu, menu2, choice, n, fails = 0;
		int ISBNInput, ISBNNew, booksEntered = 0;
		double priceInput, priceNew;
		
		/**
		 * Welcome message
		 */
		System.out.println ("------------------------------");
		System.out.println("Welcome to Viktor's Book Store!");
		System.out.println ("------------------------------");
		
		
		/**
		 * Creating variable input to get the user's input
		 */
		Scanner input = new Scanner (System.in);
		
		/**
		 * Asking the user to enter data
		 */
		System.out.print ("Please enter the number of books your bookstore can contain: ");
		int maxBooks =  input.nextInt();

		/**
		 * Creating an array of the size maxBooks
		 */
		Book inventory[] = new Book[maxBooks];
		
		/**
		 * Setting all the objects in the array to 0 or "", used later for search 
		 */
		for (int i = 0; i < maxBooks; i++)
			inventory[i] = new Book();
		
		/**
		 * spaceLeft to see later if we can add any more books
		 */
		int spaceLeft = maxBooks;
		
		/**
		 * Main menu, we can return here with continue;
		 */
		while (true) {
			
			/**
			 * Stops the program if the user failed to enter the password 12 times in total
			 */
			if (fails >= 12) {
				System.out.println("Program detected suspicous activities and will terminate immediately!");
				System.exit(0);
			}
			
			/**
			 * Main options for the user to choose from, in a more readable format
			 */
			System.out.println();
			System.out.print("What do you want to do?\n " +
					"	1. Enter new books(password required)\n" +
					"	2. Change information of a book(password required)\n" +
					"	3. Display all books by a specific author\n" +
					"	4. Display all books under a certain a price\n" +
					"	5. Checkout and see the total price of the books\n" +
					"	6. Quit\n" +
					"Please enter your choice > ");
			
			menu =  input.nextInt();
			
			/**
			 * If the input is out of our acceptable range, prompt again
			 */
			if (menu > 6 || menu < 1)
				continue;
			
			/**
			 * Main code for the enter new books
			 */
			if (menu == 1) {
				System.out.print("Please enter your password: ");
				String pwordCheck =  input.next();
				
				/**
				 * if the password if wrong
				 */ 
				if (pwordCheck.equals(pword) == false) {
					fails++;
					System.out.print("Please enter your password: ");
					String pwordCheck2 =  input.next();
					if (pwordCheck2.equals(pword) == false) {
						fails++;
						System.out.print("Please enter your password: ");
						String pwordCheck3 =  input.next();
						if (pwordCheck3.equals(pword) == false) {
							fails++;
							continue;
						}
					}
				}
				
				/**
				 * if they entered the password correctly
				 */ 
				
				System.out.print("How many book do you want to enter? ");
				int books =  input.nextInt();
				
				/**
				 * If no more space left, send a message
				 */
				if (spaceLeft == 0) {
					System.out.println("Sorry, but there is no more space left for your books");
					continue;
				}
				
				/**
				 * If not enough space, but still enough for at least 1 book, send a message
				 */
				if (books > spaceLeft)
					System.out.println("Sorry, but there is only space for another " + spaceLeft + " book(s)");
				
				
				/**
				 * Taking input from the user for every book in the array that the user wants to 
				 */
				for (int i = 0; i < Math.min(books, spaceLeft); i++) {
					
					/**
					 * Simply to let the scanner read next input, since this one will be skipped
					 */ 
						String empty =  input.nextLine();
					
						/**
						 * Taking input for the title of the book
						 */ 
						System.out.print ("Please enter the title of the book #" + (i + 1) + ": ");
						String titleBook =  input.nextLine();
						
						/**
						 * Taking input for the name of the author
						 */
						System.out.print ("Please enter the name of the author of the book #" + (i + 1) + ": ");
						String authorBook = input.nextLine();

						
						/**
						 * Taking input from the user, making sure it's within the limits
						 */
						while (true) {
							System.out.print ("Please enter the ISBN of the book #" + (i + 1) + "(Enter 0 to skip): ");
							int ISBNBook = input.nextInt();
							if (ISBNBook <= 0)
								continue;
							ISBNInput = ISBNBook;
							break;
							}
						
						/**
						 * Taking input from the user, making sure it's within the limits
						 */
						while (true) {
							System.out.print ("Please enter the price of the book #" + (i + 1) + ": ");
							Double priceBook = input.nextDouble();
							if (priceBook < 0)
								continue;
							priceInput = priceBook;
							break;
							}
						
						/**
						 * Given all the data, we create the object book, store it in the array, and do it for every single book, and we keep track of space left for the books
						 */
						Book book = new Book(titleBook, authorBook, ISBNInput, priceInput);
						inventory[booksEntered] = book;
						booksEntered++;
						spaceLeft--;
					}
				continue;
				}
			
			/**
			 * If the user chooses the option 2 in the main menu, again checking the password, but with different conditions
			 */
			if (menu == 2) {
				System.out.print("Please enter your password: ");
				String pwordCheck =  input.next();
				
				/**
				 * if the password if wrong 3 times, go back to the main menu, total fails are counted still. If correct, go to the next step
				 */
				if (pwordCheck.equals(pword) == false) {
					System.out.print("Please enter your password: ");
					String pwordCheck2 =  input.next();
					if (pwordCheck2.equals(pword) == false) {
						System.out.print("Please enter your password: ");
						String pwordCheck3 =  input.next();
						if (pwordCheck3.equals(pword) == false) {
							continue;
						}
					}
				}
				
				/**
				 * if the user entered the password correctly, we go to the next step, that is, asking which book he wants to change
				 */ 
				while (true) {
					System.out.print("Please enter the book number you want to change: ");
					n =  input.nextInt();
					
					/**
					 * If the book doesn't exist, let the user know and let him re-enter,  or go back to the main menu
					 */
					if (n > inventory.length  || (n <= inventory.length && inventory[n-1].getISBN() == 0)) {
						while (true) {
							System.out.print("Sorry, this book doesn't exist\n " +
									"	1. Re-enter the book number\n" +
									"	2. Back to main menu\n" +
									"Please enter your choice > ");
							choice =  input.nextInt();
							if (choice > 2 || menu < 1) 
								continue;
							else break;
							}
						if (choice == 1)
							continue;
						else
							break;
						}
					
					/**
					 * If the book exists, let the user choose which parameter he/she wants to change, and change it
					 */
					else {
						while (true) {
							System.out.println("	Book#: " + n + " \n" + inventory[n-1].toString());
							System.out.print("What information would you like to change?\n " +
									"	1. Author\n" +
									"	2. Title\n" +
									"	3. ISBN\n" +
									"	4. Price\n" +
									"	5. Quit\n" +
									"Please enter your choice > ");
							menu2 =  input.nextInt();
							
							/**
							 * If the input is not in the acceptable range, ask again
							 */
							if (menu > 5 || menu < 1)
								continue;
							
							
							/**
							 * For the next input from 1 to 5, use the mutator methods to modify the values of the book objects, nothing special
							 */
							if (menu2 == 1) {
								System.out.print("Please enter the new author of the book: ");
								
								// Simply to let the scanner read next input, since this one will be skipped
								String empty =  input.nextLine();
								
								authorNew =  input.nextLine();
								inventory[n-1].setName(authorNew);
								continue;
							}
								
							if (menu2 == 2) {
								System.out.print("Please enter the new title of the book: ");
								
								// Simply to let the scanner read next input, since this one will be skipped
								String empty =  input.nextLine();
								
								titleNew =  input.nextLine();
								inventory[n-1].setTitle(titleNew);
								continue;
							}
							
							if (menu2 == 3) {
								System.out.print("Please enter the new ISBN of the book: ");

								ISBNNew =  input.nextInt();
								inventory[n-1].setISBN(ISBNNew);
								continue;
							}
							
							if (menu2 == 4) {
								System.out.print("Please enter the new price of the book: ");
								priceNew =  input.nextDouble();
								inventory[n-1].setPrice(priceNew);
								continue;
							}
							
							if (menu2 == 5) {
								break;
							}
							
							break;
							}
						}

					}
				
				/**
				 * This is the end of the menu if the user entered an unacceptable book number
				 */
				if (choice == 2)
					continue;
				break;



			}
			
			/**
			 * Search function that compares the author name of every object in the array to the input and outputs the results they are equal
			 */
			if (menu == 3) {
				System.out.print("Please enter the author's name: ");
				
				/**
				 * Simply to let the scanner read next input, since this one will be skipped
				 */ 
				String empty =  input.nextLine();
				
				String authorCheck =  input.nextLine();
				
				for (int i = 0; i < inventory.length; i++) {
					if (inventory[i].name.equals(authorCheck)) {
						System.out.println("	Book#: " + (i + 1) + " \n" + inventory[i].toString());
						System.out.println();
						}
					else 
						System.out.println("No results found");
					}
					continue;
				}
			
			/**
			 * Search function that compares the price on every object in the array and outputs the info about it if its price is equal or lower than the input
			 */
			if (menu == 4) {
				System.out.print("Please enter the maximum price: ");
				
				double priceCheck =  input.nextDouble();
				
				for (int i = 0, j = 0; i < inventory.length; i++) {
					if (inventory[i].price <= priceCheck && inventory[i].price != 0) {
						System.out.println("	Book#: " + (i + 1) + " \n" + inventory[i].toString());
						System.out.println();
						j++;
						}
					else if (j == 0)
						System.out.println("No results found");
					}
					continue;
				}
			
			/**
			 * Checkout and see the total price
			 */
			if (menu == 5) {
				double totalPrice = 0;
				for (int i = 0; i < inventory.length; i++) {
					if (inventory[i].title != "" && inventory[i].name != "") {
						System.out.println("	Book#: " + (i + 1) + " \n" + inventory[i].toString());
						System.out.println();
						totalPrice += inventory[i].price;
						}

					}
				System.out.println("Total price of all the books is: $" + totalPrice);
				System.out.println();
				continue;

			}

			/**
			 * Simply, if the input is 6 in the main menu, output a good buy message and close the program
			 */
			if (menu == 6) {
				System.out.println("Thank you for using Viktor's Book Store! Have a good day!");
				System.exit(0);
			}
		}
		input.close();
	}
	

}
