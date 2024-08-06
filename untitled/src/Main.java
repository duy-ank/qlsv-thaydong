import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<Student> studentList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("1. Nhập thông tin sinh viên");
            System.out.println("2. In danh sách sinh viên");
            System.out.println("3. Xóa sinh viên theo mã");
            System.out.println("4. Sắp xếp sinh viên theo điểm giảm dần");
            System.out.println("5. Tìm kiếm sinh viên theo mã hoặc tên");
            System.out.println("6. Lọc sinh viên theo điểm >= X");
            System.out.println("0. Thoát");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    input();
                    break;
                case 2:
                    output();
                    break;
                case 3:
                    System.out.print("Nhập mã sinh viên để xóa: ");
                    String code = scanner.nextLine();
                    removeByCode(code);
                    break;
                case 4:
                    sortByGradeDesc();
                    break;
                case 5:
                    System.out.print("Nhập mã hoặc tên sinh viên để tìm kiếm: ");
                    String keyword = scanner.nextLine();
                    Student foundStudent = findByCodeOrName(keyword);
                    if (foundStudent != null) {
                        System.out.println(foundStudent);
                    } else {
                        System.out.println("Không tìm thấy sinh viên.");
                    }
                    break;
                case 6:
                    System.out.print("Nhập điểm X: ");
                    float grade = scanner.nextFloat();
                    scanner.nextLine(); // Consume newline
                    List<Student> filteredStudents = filterByGrade(grade);
                    for (Student student : filteredStudents) {
                        System.out.println(student);
                    }
                    break;
                case 0:
                    System.out.println("Thoát chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (choice != 0);
    }

    // Nhập mới 1 sinh viên
    public static void input() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nhập vào thông tin sinh viên:");
        System.out.print("Nhập mã sv: ");
        String code = scanner.nextLine();
        System.out.print("Nhập tên sv: ");
        String name = scanner.nextLine();
        System.out.print("Nhập điểm: ");
        float grade = scanner.nextFloat();

        Student student = new Student(code, name, grade);
        studentList.add(student);
    }

    // In danh sách sinh viên
    public static void output() {
        for (Student student : studentList) {
            System.out.println(student.toString());
        }
    }

    // Xóa sinh viên theo mã
    public static void removeByCode(String code) {
        studentList.removeIf(student -> student.getCode().equals(code));
    }

    // Sắp xếp sinh viên theo điểm giảm dần
    public static void sortByGradeDesc() {
        Collections.sort(studentList, Comparator.comparingDouble(Student::getGrade).reversed());
        System.out.println("Danh sách sinh viên đã được sắp xếp theo điểm giảm dần.");
    }

    // Tìm kiếm sinh viên theo mã hoặc tên
    public static Student findByCodeOrName(String keyword) {
        for (Student student : studentList) {
            if (student.getCode().equals(keyword) || student.getName().equalsIgnoreCase(keyword)) {
                return student;
            }
        }
        return null;
    }

    // Lọc sinh viên theo điểm >= X
    public static List<Student> filterByGrade(float x) {
        List<Student> result = new ArrayList<>();
        for (Student student : studentList) {
            if (student.getGrade() >= x) {
                result.add(student);
            }
        }
        return result;
    }
}


