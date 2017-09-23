package Util;

import Dto.Student;

import java.util.ArrayList;
import java.util.List;

//工厂
public class SampleJRDataSourceFactory {
    public static List<Student> createBeanCollection() {
        List<Student> students = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            Student student = new Student();
            student.setUsername("name" + i);
            if (i%2 == 0)
                student.setSex("男");
            else
                student.setSex("女");
            student.setAge(i + 10);
            student.setAddress("ZheJian NingBo " + i + "号");
            student.setPhone("1785888888" + i);
            students.add(student);
        }
        return students;
    }
}
