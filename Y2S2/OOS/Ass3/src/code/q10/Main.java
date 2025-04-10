/*
    A bookshop with inventory of books that are being sold at the shop
    The list includes details such as author, title, publisher, cost and stock position
    Whenever a customer wants a book, the sales person inputs the title and author
        and the system searches the list and displays whether it is available or not
    If it is not, an appropriate message is displayed
    If it is, then the system displays the book details and details
        and requests for the number of copies required
    If the required copies are available, the total cost of the requested copies is displayed,
        otherwise the message "requires copies not in stock" is displayed
    Design a system using a class called "Book" with suitable member methods and constructors.
*/
package q10;
 
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
 
class Book {
    private String author;
    private String title;
    private String publisher;
    private double cost;
 
    public Book(String author, String title, String publisher, double cost) {
        this.author = author;
        this.title = title;
        this.publisher = publisher;
        this.cost = cost;
    }
 
    public String getAuthor() {
        return author;
    }
 
    public void setAuthor(String author) {
        this.author = author;
    }
 
    public String getTitle() {
        return title;
    }
 
    public void setTitle(String title) {
        this.title = title;
    }
 
    public String getPublisher() {
        return publisher;
    }
 
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
 
    public double getCost() {
        return cost;
    }
 
    public void setCost(double cost) {
        this.cost = cost;
    }
 
    public void printDetails() {
        System.out.println("Book Details:- ");
        System.out.println("Author: " + getAuthor());
        System.out.println("Title: " + getTitle());
        System.out.println("Publisher: " + getPublisher());
        System.out.println("Cost: $" + String.format("%.2f", getCost()));
        // System.out.println("");
    }
}
 
class BookShop {
    private Map<Book, Integer> inventoryOfBooks = new HashMap<>();

    public BookShop() {}
 
    public BookShop(Map<Book, Integer> inventoryOfBooks) {
        this.inventoryOfBooks = inventoryOfBooks;
    }
 
    public void addBook(Book book, int quantity) {
        int count = inventoryOfBooks.containsKey(book) ? inventoryOfBooks.get(book) : 0;
        inventoryOfBooks.put(book, count + quantity);
    }

    // private void removeBook(Book book, int requiredCopies) {
    //     int count = inventoryOfBooks.containsKey(book) ? inventoryOfBooks.get(book) : 0;
    //     inventoryOfBooks.put(book, count - requiredCopies);
    // }
 
    private Book checkIfBookIsAvailable(String author, String title) {
        for(Map.Entry<Book, Integer> entry : inventoryOfBooks.entrySet()) {
            Book currentBook = entry.getKey(); 
            if(currentBook.getAuthor().equals(author) && currentBook.getTitle().equals(title)) {
                return currentBook;
            }
        }
        return null;
    }
 
    public void getBook(String author, String title) {
        int requiredCopiesOfBook = 0;
        int quantity = 0;
        double totalCost = 0;
        Book currentBook = checkIfBookIsAvailable(author, title);
        if(currentBook == null || inventoryOfBooks.get(currentBook) == 0) {
            System.out.println("Book not available.");
            return;
        }

        quantity = inventoryOfBooks.get(currentBook);
        currentBook.printDetails();
        System.out.println("Quantity: " + quantity);

        Scanner sc = new Scanner(System.in);
        System.out.println("Number of copies required: ");
        requiredCopiesOfBook = Integer.parseInt(sc.next());
        //sc.close();
 
        if(requiredCopiesOfBook > quantity) {
            System.out.println("Required copies not in stock");
            return;
        }

        totalCost = requiredCopiesOfBook * currentBook.getCost();
        inventoryOfBooks.put(currentBook, quantity - requiredCopiesOfBook);
 
        System.out.println("Total cost: " + String.format("%.2f", totalCost));
    }
}
 
public class Main {
    public static String[] getBookInfo() {
        String[] info = new String[2];
        Scanner sc = new Scanner(System.in);
	    System.out.println("Enter author name: ");
        info[0] = sc.nextLine();
	    System.out.println("Enter book name: ");
	    info[1] = sc.nextLine();
        sc.close();
        return info;
    }

    public static void main(String[] args) {
        Book b1 = new Book("Adolf Hitler", "Mein Kampf", "JUSL Inc.", 19.99);
        b1.printDetails();
        System.out.println();

        BookShop bs1 = new BookShop();
        bs1.addBook(b1, 10);

        String[] bookInfo1 = getBookInfo();
	
        bs1.getBook(bookInfo1[0], bookInfo1[1]);
        bs1.getBook(bookInfo1[0], bookInfo1[1]);
    }
}