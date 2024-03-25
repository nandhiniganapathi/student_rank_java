package com.example.studentranking;

import com.example.studentranking.models.Students;
import com.example.studentranking.models.SubjectEnum;
import com.example.studentranking.models.Subjects;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;


@SpringBootApplication
public class StudentRankingApplication {
    private static boolean hasDuplicateRollNumber(List<Students> students, int rollNumber) {
        for(Students student : students){
            if(student.getRollNumber()==rollNumber){
                return true;// if there is a duplicate
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SpringApplication.run(StudentRankingApplication.class, args);


        Scanner scanner = new Scanner(System.in);
        List<Students> students = new ArrayList<>();

        // Input number of students
        System.out.print("Enter the number of students: ");
        int numStudents = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Input student details and marks for 6 subjects
        for (int i = 0; i < numStudents; i++) {
            System.out.println(" Enter details for student " + (i + 1) + ":");

            System.out.print("Enter student name: ");
            String name = scanner.nextLine();
            boolean duplicateRoll;
            int rollNumber;
            do {
                System.out.print("Enter roll number: ");
                rollNumber = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                duplicateRoll = hasDuplicateRollNumber(students, rollNumber);
                if (duplicateRoll) {
                    System.out.println("Roll number already exists. Please enter a different roll number.");

                }
            } while (duplicateRoll);


            Students student = new Students(rollNumber, name);

            for (SubjectEnum subjectEnum : SubjectEnum.values()) {
                System.out.print("Enter marks for " + subjectEnum.name() + ": ");
                int marks = scanner.nextInt();
                scanner.nextLine();// Consume newline
                Subjects subject = new Subjects(subjectEnum, marks);
                student.addSubject(subject);
            }

            students.add(student);
        }
        boolean validInput = false;
        int option = 0;
        while (!validInput) {
            System.out.println("Choose ranking option: ");
            System.out.println("1. Rank by Total Marks");
            System.out.println("2. Rank by Subject Marks");
            System.out.println("3. Exit");

            System.out.print("Enter your choice (1 or 2 or 3): ");
            option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (option == 1 || option == 2 || option ==3) {
                validInput = true; // Valid input received, exit the loop
            } else {
                System.out.println("Invalid option. Please enter 1 or 2.");
            }
        }
        int exitNumber;

            if (option == 1) {

                    // Calculate ranks based on total marks
                    Collections.sort(students, Comparator.comparingInt(Students::getTotalMarks).reversed());

                    // Sort students based on marks in a specific subject (e.g., Math)


                    // Display ranked list of students
                    System.out.println("Rank List of Students:");
                    System.out.println("--------------------------------------------------------");
                    System.out.printf("%-10s %-15s %-10s %-10s\n", "Rank", "Name", "Roll No.", "Total Marks");
                    System.out.println("--------------------------------------------------------");
                    int rank = 1;
                    int previousMarks = Integer.MAX_VALUE;
                    for (int i = 0; i < numStudents; i++) {
                        Students student = students.get(i);
                        int currentMarks =  student.getTotalMarks();
                        if (currentMarks != previousMarks) {
                            rank = i + 1;
                        }
                        System.out.printf("%-10d %-15s %-10d %-10d\n", rank, student.getName(),
                                student.getRollNumber(), student.getTotalMarks());
                        previousMarks = currentMarks;
                    }
                    System.out.println("--------------------------------------------------------");

                }

                else if (option == 2) {
                boolean exit = false;

                while (!exit) {
                    System.out.println("Please enter the subject (e.g., MATH or SCIENCE :");
                    String subName = scanner.nextLine().toUpperCase();

                    SubjectEnum subjectToRank;
                    try {
                        subjectToRank = SubjectEnum.valueOf(subName);
                    } catch (Exception e) {
                        System.out.println("Invalid subject name!");
                        scanner.close();
                        return;
                    }

                    SubjectEnum subjectToSort = SubjectEnum.valueOf(subName);
                    Collections.sort(students, Comparator.comparingInt((Students s) -> s.getMarksInSubjects(subjectToSort)).reversed());

                    // Display ranked list of students
                    System.out.println("\nRanked List of Students (Sorted by " + subjectToSort.name() + " marks):");
                    System.out.println("--------------------------------------------------------");
                    System.out.printf("%-10s %-15s %-10s %-10s\n", "Rank", "Name", "Roll No.", subjectToSort.name() + " Marks");
                    System.out.println("--------------------------------------------------------");
                    int rank = 1;
                    int previousMarks = Integer.MAX_VALUE;
                    for (int i = 0; i < numStudents; i++) {
                        Students student = students.get(i);
                        int marksInSubject = student.getMarksInSubjects(subjectToSort);
                        int currentMark = marksInSubject;
                        if (currentMark != previousMarks) {
                            rank = i + 1;
                        }
                        System.out.printf("%-10d %-15s %-10d %-10d\n", rank, student.getName(),
                                student.getRollNumber(), marksInSubject);
                        previousMarks = currentMark;
                    }
                    System.out.println("--------------------------------------------------------");
                    System.out.println("Enter 1 to continue or enter 2 to exit: ");
                    exitNumber = scanner.nextInt();
                    if(exitNumber ==1){
                        exit = false;
                        subName = scanner.nextLine().toUpperCase();
                    }
                    else if(exitNumber==2){
                        exit = true;
                        scanner.close();
                    }

                }
            }
    }
}




