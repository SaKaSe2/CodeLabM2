package data;

import books.Book;
import util.iMenu;
import exception.custom.IllegalAdminAccess;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Admin extends User implements iMenu {
    private final List<Student> studentList;
    private static List<Book> bookList;

    public Admin(String admin, String s) {
        super("Admin", "admin", "Admin", "Admin");
        this.studentList = new ArrayList<>();
        this.bookList = new ArrayList<>();
        // Sample books added for demonstration
        bookList.add(new Book("388c-e681-9152", "Title 1", "Author 1", "Sejarah", 4));
        bookList.add(new Book("ed90-be30-5cdb", "Title 2", "Author 2", "Cerita", 0));
        bookList.add(new Book("d95e-0c4a-9523", "Title 3", "Author 3", "Novel", 2));
    }

    public static List<Book> getBookList() {
        return bookList;
    }

    public boolean isAdmin(String username, String password) throws IllegalAdminAccess {
        if ("admin".equals(username) && "admin".equals(password)) {
            return true;
        } else {
            throw new IllegalAdminAccess("Invalid credentials");
        }
    }


    public void addStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student NIM: ");
        String nim = scanner.nextLine();
        System.out.print("Enter student faculty: ");
        String faculty = scanner.nextLine();
        System.out.print("Enter student program: ");
        String program = scanner.nextLine();

        studentList.add(new Student(name, nim, faculty, program));
        System.out.println("Student successfully registered.");
    }

    public void addBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Pilih kategori buku:");
        System.out.println("1. Cerita Buku");
        System.out.println("2. Histori Buku");
        System.out.println("3. Tulisan Buku");
        System.out.print("Pilih kategori (1-3): ");
        int categoryChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        String category;
        switch (categoryChoice) {
            case 1:
                category = "Story";
                break;
            case 2:
                category = "History";
                break;
            case 3:
                category = "Text";
                break;
            default:
                System.out.println("Invalid kategori pilihan. Buku tidak ditambahkan.");
                return;
        }

        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter the stock: ");
        int stock = scanner.nextInt();

        String bookId = generateId();

        // Menggunakan ArrayList untuk menambahkan buku baru
        bookList.add(new Book(bookId, title, author, category, stock));
        System.out.println("Buku sukses ditambahkan kedalam Pinjaman.");
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

    public void displayStudents() {
        System.out.println("List siswa yang terdaftar:");
        for (Student student : studentList) {
            System.out.println(student.getName() + " - " + student.getNim());
        }
    }

    public String generateId() {
        // Implement generating unique ID functionality here
        return "GeneratedID";
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        // Mungkin meminta username dan password untuk login sebagai admin
        System.out.print("Enter Nama (admin): ");
        String username = scanner.nextLine();
        System.out.print("Enter password (admin: ");
        String password = scanner.nextLine();

        try {
            if (!isAdmin(username, password)) {
                System.out.println("Akses tidak sah.");
                return;
            }

            do {
                System.out.println("===== Admin Menu =====");
                System.out.println("1. Tambahkan siswa");
                System.out.println("2. Tambahkan buku");
                System.out.println("3. Tampilkan daftar siswa");
                System.out.println("4. Tampilkan daftar buku yang tersedia");
                System.out.println("5. Logout");
                System.out.print("Pilih diantara (1-5): ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // membersihkan buffer scanner jika ada

                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        addBook();
                        break;
                    case 3:
                        displayStudents();
                        break;
                    case 4:
                        displayBooks(bookList);
                        break;
                    case 5:
                        System.out.println("Keluar...");
                        return;
                    default:
                        System.out.println("Invalid pilihan. Silakan pilih antara 1 sampai 5.");
                }
            } while (true);
        } catch (InputMismatchException e) {
            System.out.println("Input harus berupa angka.");
            scanner.next(); // membersihkan buffer scanner
        } catch (IllegalAdminAccess e) {
            System.out.println("Error: " + e.getMessage());
            return; // Jika terjadi IllegalAdminAccess, logout atau keluar dari metode menu()
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
            scanner.nextLine(); // membersihkan buffer scanner
        }
    }


    public void menuAdmin() {
        menu();
    }
}
