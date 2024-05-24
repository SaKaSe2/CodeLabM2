package com.main;

import books.Book;
import books.HistoryBook;
import books.StoryBook;
import books.TextBook;
import data.Admin;
import data.Student;
import exception.custom.IllegalAdminAccess;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibrarySystem {
    private final List<Student> students;
    private final List<Book> books;
    private final Admin admin;

    public LibrarySystem() {
        this.students = new ArrayList<>();
        this.books = new ArrayList<>();
        this.admin = new Admin("admin", "admin");
        initializeBooks();
        initializeStudents();
    }

    private void initializeStudents() {
        students.add(new Student("Rikza", "202310370311265", "Fakultas 1", "Program 1"));
        students.add(new Student("Nabil", "202310370311266", "Fakultas 2", "Program 2"));
        students.add(new Student("Dito", "202310370311267", "Fakultas 3", "Program 3"));
    }

    private void initializeBooks() {
        books.add(new HistoryBook("388c-e681-9152", "Aljabar Linier", "Author 1", 4));
        books.add(new StoryBook("ed90-be30-5cdb", "Sistem Informasi", "Author 2", 0));
        books.add(new TextBook("d95e-0c4a-9523", "Matematika Diskrit", "Author 3", 5));
    }

    public void start() throws IllegalAdminAccess {
        Scanner scanner = new Scanner(System.in);
        boolean loggedIn = false;
        do {
            System.out.println("===== Welcome to Library System =====");
            System.out.println("1. Admin Login");
            System.out.println("2. Student Login");
            System.out.println("3. Exit");
            System.out.print("Pilih Nomer (1-3): ");

            if (scanner.hasNextInt()) {
                int option = scanner.nextInt();
                switch (option) {
                    case 1:
                        adminLogin();
                        loggedIn = true;
                        break;
                    case 2:
                        studentLogin();
                        loggedIn = true;
                        break;
                    case 3:
                        System.out.println("Keluar...");
                        return;
                    default:
                        System.out.println("Invalid pilihan. Please pilih diantara 1 - 3.");
                }
            } else {
                System.out.println("Invalid input. Please pilih nomor 1 - 3.");
                scanner.next();
            }
        } while (!loggedIn);
    }

    private void studentLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan NIM (input 99 untuk kembali): ");
        String nim = scanner.nextLine().trim();
        if ("99".equals(nim)) return;

        Student student = findStudentByNim(nim);
        if (student == null) {
            System.out.println("Student tidak ada.");
            return;
        }
        student.menuStudent(books);
    }

    private void adminLogin() throws IllegalAdminAccess {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter nama (admin): ");
        String username = scanner.nextLine();
        System.out.print("Enter password (admin): ");
        String password = scanner.nextLine();

        if (admin.isAdmin(username, password)) {
            admin.menuAdmin();
        } else {
            System.out.println("Invalid nama atau password.");
        }
    }

    private Student findStudentByNim(String nim) {
        for (Student student : students) {
            if (student.getNim().equals(nim)) {
                return student;
            }
        }
        return null;
    }

    public static void main(String[] args) throws IllegalAdminAccess {
        LibrarySystem librarySystem = new LibrarySystem();
        librarySystem.start();
    }
}