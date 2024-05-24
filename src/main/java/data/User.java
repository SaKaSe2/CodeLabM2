package data;

import books.Book;
import util.iMenu;

import java.util.List;

public class User {
    private final String name;
    private final String nim;
    private final String faculty;
    private final String program;

    public User(String name, String nim, String faculty, String program) {
        this.name = name;
        this.nim = nim;
        this.faculty = faculty;
        this.program = program;
    }

    public String getName() {
        return name;
    }

    public String getNim() {
        return nim;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getProgram() {
        return program;
    }

    public void displayBooks(List<Book> bookList) {
        System.out.println("=============================================");
        System.out.println("List Buku Yang Tersedia di Perpustakaan:");
        System.out.println("=============================================");
        System.out.printf("| %-10s | %-30s |%n", "Book ID", "Title");
        System.out.println("=============================================");
        for (Book book : bookList) {
            System.out.printf("| %-10s | %-30s |%n", book.getBookId(), book.getTitle());
        }
        System.out.println("=============================================");
    }
}