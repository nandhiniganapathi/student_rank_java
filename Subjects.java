package com.example.studentranking.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Subjects {
    private SubjectEnum subject;
    private int mark;
    private Rank rank;

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public SubjectEnum getSubject() {
        return subject;
    }

    public void setSubject(SubjectEnum subject) {
        this.subject = subject;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public Subjects(SubjectEnum subject, int mark){
        this.subject = subject;
        this.mark = mark;
    }

    public static int getSubjectRank(List<Students> students, SubjectEnum subjectEnum) {
        List<Students> sortedStudents = new ArrayList<>(students);
        Collections.sort(sortedStudents, Comparator.comparingInt((Students s) -> s.getMarksInSubjects(subjectEnum)).reversed());

        int rank = 1;
        int prevMarks = Integer.MIN_VALUE;
        for (Students student : sortedStudents) {
            int marks  = student.getTotalMarks();
            if (student.getMarksInSubjects(subjectEnum) > 0) {
                return rank;
            }

            if(marks == prevMarks){
                --rank;
            }
            if(marks != prevMarks) {
                rank++;
            }
            prevMarks = marks;
        }
        return 0; // Subject marks not found for any student
    }

}
