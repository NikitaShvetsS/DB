package org.example;

import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        try {
            LessonDao lessonDao = new LessonDao();

            Homework hw1 = new Homework(1, "PE", "Exercises");
            Lesson lesson1 = new Lesson("Lesson 1", hw1);
            lessonDao.addLesson(lesson1);

            Homework hw2 = new Homework(2, "Math", "Equations");
            Lesson lesson2 = new Lesson("Lesson 2", hw2);
            lessonDao.addLesson(lesson2);

            List<Lesson> allLessons = lessonDao.getAllLessons();
            for (Lesson lesson : allLessons) {
                System.out.println(lesson);
            }

            lessonDao.deleteLesson(1);

            List<Lesson> updatedLessons = lessonDao.getAllLessons();
            for (Lesson lesson : updatedLessons) {
                System.out.println(lesson);
            }

            Lesson retrievedLesson = lessonDao.getLessonById(2);
            System.out.println("Retrieved lesson by ID: " + retrievedLesson);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

