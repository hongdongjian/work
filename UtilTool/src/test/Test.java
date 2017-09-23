package test;

import dto.OtherStudent;
import dto.Student;
import dto.Teacher;
import util.AssignUtil;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class Test {
    public static void testAssign() {
        Student student = new Student();
        student.setAge(18);
        student.setFlag(true);
        student.setName("dj");
        student.setScore(100.0);
        Teacher teacher = new Teacher();
        teacher.setName("ddddd");
        student.setTeacher(teacher);

        OtherStudent otherStudent = new OtherStudent();
        otherStudent.setName("hh");
        Teacher teacher1 = new Teacher();
        teacher1.setName("jjjjj");
        otherStudent.setTeacher(teacher1);

        System.out.println("before:" + otherStudent.getName());

        try {
            Set<String> filter  = new HashSet<>();
            AssignUtil.assign(student,otherStudent, filter);

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("after:" + otherStudent.getName());
        System.out.println(otherStudent.getTeacher().getName());

        System.out.println(otherStudent.getTeacher().equals(student.getTeacher()));

    }

     public static void testCopy() {
        Student student = new Student();
        student.setAge(18);
        student.setFlag(true);
        student.setName("dj");
        student.setScore(100.0);
        try {
            Set<String> filter  = new HashSet<>();
//            filter.add("name");
            OtherStudent student2 = AssignUtil.copy(student,OtherStudent.class,filter);
            System.out.println(student2.getName());
            System.out.println(student2.getTeacher());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        testAssign();
        testCopy();
    }
}
