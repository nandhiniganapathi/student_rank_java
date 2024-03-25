package com.example.studentranking.models;


import java.util.ArrayList;
import java.util.List;


public class Students {
    private int rollNumber;
    private String name;
    private List<Subjects> subjects;

    public void addSubject(Subjects subject) {
        subjects.add(subject);
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Subjects> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subjects> subjects) {
        this.subjects = subjects;
    }

    public Students(int rollNumber, String name){
        this.rollNumber = rollNumber;
        this.name = name;
        this.subjects = new ArrayList<>();
    }

    public int getTotalMarks(){
        int totalMarks = 0;
        for(Subjects subject :subjects)
            totalMarks += subject.getMark();

        return totalMarks;
    }

    // Get marks in a specific subject
    public int getMarksInSubjects(SubjectEnum subjectEnum) {
        for (Subjects subjects : subjects) {
            if (subjects.getSubject() == subjectEnum) {
                return subjects.getMark();
            }
        }
        return 0; // Return 0 if marks not found (optional)
    }

}
