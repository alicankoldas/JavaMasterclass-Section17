package dev.lpa;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        Course pymc = new Course("PYMC", "Python Masterclass");
        Course jmc = new Course("JMC", "Java Masterclass");
//        Student tim = new Student("AU", 2019, 30, "M",
//                true, jmc, pymc);
//        System.out.println(tim);
//
//        tim.watchLecture("JMC", 10, 5, 2019);
//        tim.watchLecture("PYMC", 7, 7, 2020);
//        System.out.println(tim);

        List<Student> students = Stream.generate(() -> Student.getRandomStudent(jmc, pymc))
                .limit(1000)
                .collect(Collectors.toList());

        long numberOfFemale = students.stream().
                filter(student -> student.getGender().equals("F")).count();

        System.out.println("Number of Female: %d".formatted(numberOfFemale));

        long numberOfMale = students.stream().
                filter(student ->  student.getGender().equals("M")).count();

        System.out.println("Number of Male: %d".formatted(numberOfMale));

        List<String> countryCodes = students.stream().
                map(student -> student.getCountryCode()).
                distinct().
                collect(Collectors.toList());

        System.out.println("\nStudent's distinct country codes:");

        for(String code : countryCodes){
            System.out.print(code + " ");
        }

        System.out.println("\n\nFirst five students information:");

        students.stream().limit(5).
                forEach(System.out::println);

        var studentAgeStatistics = students.stream().mapToInt(Student::getAge)
                .summaryStatistics();

        System.out.println("\nStudent Age Statistics:");

        String ageStatisticInfo = """
                Youngest Student Age: %d
                Oldest Student Age: %d
                Average Age of Students: %.2f
                """.formatted(studentAgeStatistics.getMin(), studentAgeStatistics.getMax(),
                studentAgeStatistics.getAverage());

        System.out.println(ageStatisticInfo);

        boolean isEnrolledMoreThanSevenAndActive =
                students.stream().allMatch(student -> student.getMonthsSinceActive() == 0
                && student.getYearsSinceEnrolled() > 7);

        System.out.println(
                "Is there any student enrolled more than 7 years and still active ? %s \n".
                        formatted(isEnrolledMoreThanSevenAndActive ? "Yes" : "No"));

        long studentLessThan30 = students.stream().
                mapToInt(Student::getAge).filter(i -> i > 60).count();
        long studentBetween30And60 = students.stream().
                mapToInt(Student::getAge).filter(i -> i < 30).count();
        long studentMoreThan60 = students.stream().
                mapToInt(Student::getAge).filter(i -> i >= 30 && i <= 60).count();

        System.out.println("There are %d student(s) more than 60 years old".formatted(studentMoreThan60));
        System.out.println("There are %d student(s) less than 30 years old".formatted(studentLessThan30));
        System.out.println("There are %d student(s) between 30 and 60 years old".formatted(studentBetween30And60));
    }
}