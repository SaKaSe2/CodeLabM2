package data;

import books.Book;
import util.iMenu;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Student extends User implements iMenu { // Mengimplementasikan iMenu
    private final List<Book> borrowedBooks;

    public Student(String name, String nim, String faculty, String program) {
        super(name, nim, faculty, program);
        this.borrowedBooks = new ArrayList<>();
    }

    @Override
    public void displayBooks(List<Book> bookList) {
        System.out.println("=============================================");
        System.out.println("List Buku yang Tersedia di Perpustakaan:");
        System.out.println("=============================================");
        System.out.printf("| %-10s | %-30s | %-15s | %-13s | %-7s |%n", "Book ID", "Title", "Author", "Kategori", "Stock");
        System.out.println("=============================================");
        for (Book book : bookList) {
            System.out.printf("| %-10s | %-30s | %-15s | %-13s | %-7d |%n", book.getBookId(), book.getTitle(), book.getAuthor(), book.getCategory(), book.getStock());
        }
        System.out.println("=============================================");
    }

    public void borrowBook(List<Book> bookList) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan ID Buku untuk Meminjam (input 99 untuk kembali): ");
        String bookId = scanner.next();

        if ("99".equals(bookId)) {
            return;
        }

        for (Book book : bookList) {
            if (book.getBookId().equals(bookId)) {
                if (book.getStock() > 0) {
                    borrowedBooks.add(book);
                    book.setStock(book.getStock() - 1);
                    System.out.println("Buku berhasil di pinjam.");
                } else {
                    System.out.println("Buku tidak ada di stock.");
                }
                return;
            }
        }
        System.out.println("Buku tidak ditemukan.");
    }

    public void returnBook() {
        Scanner scanner = new Scanner(System.in);
        if (borrowedBooks.isEmpty()) {
            System.out.println("Tidak Ada Buku Yang Dipinjam.");
        } else {
            System.out.println("List buku yang di pinjam:");
            int i = 1;
            for (Book book : borrowedBooks) {
                System.out.println(i + ". " + book.getTitle());
                i++;
            }
            System.out.print("Masukkan nomer buku, untuk mengembalikan buku: ");
            int returnChoice = scanner.nextInt();
            if (returnChoice > 0 && returnChoice <= borrowedBooks.size()) {
                Book book = borrowedBooks.remove(returnChoice - 1);
                book.setStock(book.getStock() + 1);
                System.out.println("Buku berhasil dikembalikan.");
            } else {
                System.out.println("Invalid pilihan.");
            }
        }
    }

    @Override
    public void menu() {
        Scanner scanner = new Scanner(System.in);
        do {
            try {
                System.out.println("===== Student Menu =====");
                System.out.println("1. Tampilkan daftar buku");
                System.out.println("2. Pinjam buku");
                System.out.println("3. Kembalikan buku");
                System.out.println("4. Logout");
                System.out.print("Pilih diantara (1-4): ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        displayBooks(Admin.getBookList());
                        break;
                    case 2:
                        borrowBook(Admin.getBookList());
                        break;
                    case 3:
                        returnBook();
                        break;
                    case 4:
                        System.out.println("Keluar...");
                        return;
                    default:
                        System.out.println("Invalid pilihan. please pilih antara 1 sampai 4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input harus berupa angka.");
                scanner.next(); // clear the scanner buffer
            } catch (Exception e) {
                System.out.println("Terjadi kesalahan: " + e.getMessage());
            }
        } while (true);
    }


    public void menuStudent(List<Book> books) {
        menu();
    }
}
