package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LessonDao {

    public void addLesson(Lesson lesson) throws SQLException {
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO lesson (name, homework_id) VALUES (?, ?)")) {

            preparedStatement.setString(1, lesson.getName());
            preparedStatement.setInt(2, lesson.getHomework().getId());

            preparedStatement.executeUpdate();
        }
    }

    public void deleteLesson(int lessonId) throws SQLException {
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM lesson WHERE id = ?")) {

            preparedStatement.setInt(1, lessonId);

            preparedStatement.executeUpdate();
        }
    }

    public List<Lesson> getAllLessons() throws SQLException {
        List<Lesson> lessons = new ArrayList<>();

        try (Connection connection = DataBaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT lesson.id AS lesson_id, lesson.name AS lesson_name, " +
                             "homework.id AS homework_id, homework.name AS homework_name, " +
                             "homework.description AS homework_description " +
                             "FROM lesson " +
                             "INNER JOIN homework ON lesson.homework_id = homework.id")) {

            while (resultSet.next()) {
                int lessonId = resultSet.getInt("lesson_id");
                String lessonName = resultSet.getString("lesson_name");

                int homeworkId = resultSet.getInt("homework_id");
                String homeworkName = resultSet.getString("homework_name");
                String homeworkDescription = resultSet.getString("homework_description");

                Homework homework = new Homework(homeworkId, homeworkName, homeworkDescription);
                Lesson lesson = new Lesson(lessonName, homework);

                lessons.add(lesson);
            }
        }

        return lessons;
    }
    public Lesson getLessonById(int lessonId) throws SQLException {
        Lesson lesson = null;

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT lesson.id AS lesson_id, lesson.name AS lesson_name, " +
                             "homework.id AS homework_id, homework.name AS homework_name, " +
                             "homework.description AS homework_description " +
                             "FROM lesson " +
                             "INNER JOIN homework ON lesson.homework_id = homework.id " +
                             "WHERE lesson.id = ?")) {

            preparedStatement.setInt(1, lessonId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int retrievedLessonId = resultSet.getInt("lesson_id");
                    String lessonName = resultSet.getString("lesson_name");

                    int homeworkId = resultSet.getInt("homework_id");
                    String homeworkName = resultSet.getString("homework_name");
                    String homeworkDescription = resultSet.getString("homework_description");

                    Homework homework = new Homework(homeworkId, homeworkName, homeworkDescription);
                    lesson = new Lesson(lessonName, homework);
                }
            }
        }

        return lesson;

        }
}