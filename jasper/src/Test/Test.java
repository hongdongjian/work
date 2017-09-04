package Test;

import Dto.Student;
import Util.Print;
import Util.SampleJRDataSourceFactory;

import java.util.List;

//调用编译
public class Test {
    public static void main(String[] args) {
        List<Student> studentList = SampleJRDataSourceFactory.createBeanCollection();
        for (Student student : studentList) {
            System.out.println(student.getUsername());
        }
        Print.print(Print.Type.pdf,"G:\\");
        Print.print(Print.Type.docx,"G:\\");
        Print.print(Print.Type.xlsx,"G:\\");
    }
}
