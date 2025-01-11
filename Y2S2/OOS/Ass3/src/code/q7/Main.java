/*
    Create an abstract class "Publication"
        with data members: 'noOfPages', 'price', 'publisherName' etc
        with their accessor/modifier functions
    Now create two sub-classes "Book" and "Journal"
    
    Create a class Library that contains a list of Publications
    
    Write a main() function and create three Books and two Journals,
    add them to library and print the details of all publications.
*/

package q7;

import java.util.ArrayList;
import java.util.List;

abstract class Publication {
    private String publisherName;
    private int noOfPages;
    private double price;
    
    public Publication(String publisherName, int noOfPages, double price) {
        this.publisherName = publisherName;
        this.noOfPages = noOfPages;
        this.price = price;
    }

    public int getNoOfPages() {
        return noOfPages;
    }

    public void setNoOfPages(int noOfPages) {
        this.noOfPages = noOfPages;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public abstract void display();
}

class Book extends Publication {
    private String bookName;
    private String author;

    public Book(String publisherName, int noOfPages, double price, String bookName, String author) {
        super(publisherName, noOfPages, price);
        this.bookName = bookName;
        this.author = author;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public void display() {
        System.out.println("Book Details:-");
        System.out.println("Name of Book: " + getBookName());
        System.out.println("Author: " + getAuthor());
        System.out.println("Publisher: " + getPublisherName());
        System.out.println("No. of Pages: " + getNoOfPages());
        System.out.println("Price: $" + getPrice());
        System.out.println();
    }
}

class Journal extends Publication {
    private String journalName;

    public Journal(String publisherName, int noOfPages, double price, String journalName) {
        super(publisherName, noOfPages, price);
        this.journalName = journalName;
    }

    public String getJournalName() {
        return journalName;
    }

    public void setJournalName(String journalName) {
        this.journalName = journalName;
    }

    @Override
    public void display() {
        System.out.println("Journal Details:-");
        System.out.println("Journal Name: " + getJournalName());
        System.out.println("Publisher: " + getPublisherName());
        System.out.println("No. of Pages: " + getNoOfPages());
        System.out.println("Price: $" + getPrice());
        System.out.println();
    }
}

class Library {
    private String libraryName;
    private List<Publication> listOfPublications;

    public Library(String libraryName) {
        this.libraryName = libraryName;
        listOfPublications = new ArrayList<>();
    }

    public void add(Publication publication) {
        listOfPublications.add(publication);
    }

    public void display() {
        System.out.println("Details of all publications in " + libraryName + ": ");
        for (Publication publication : listOfPublications) {
            publication.display();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Library library = new Library("Dustin Library");

        Book b1 = new Book("ABC Publications", 300, 25.99, "Holocast", "George Lucas");
        Book b2 = new Book("Viking", 478, 19.99, "Moby-Duck", "Donovan Hohn");
        Book b3 = new Book("W. W. Norton & Company", 176, 32.50, "A Clockwork Orange", "Anthony Burgess");

        Journal j1 = new Journal("Popular Science", 50, 8.99, "Reverse Aging");
        Journal j2 = new Journal("Chemical Society", 60, 55.99, "Chemical Reviews");

        library.add(b1);
        library.add(b2);
        library.add(b3);
        library.add(j1);
        library.add(j2);

        library.display();
    }
}
