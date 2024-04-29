//package day0416;
//
//import java.util.HashSet;
//import java.util.Set;
//
//public class Employee {
//    private String name;
//    private String id;
//    private String department;
//
//    public Employee(String name, String id, String department) {
//        this.name = name;
//        this.id = id;
//        this.department = department;
//    }
//
//
//    public class EmployeeManagerDemo {
//        public static void main(String[] args) {
//            EmployeeManager manager = new EmployeeManager();
//            manager.addEmployee(new Employee("홍길동", "E01", "HR"));
//            manager.addEmployee(new Employee("김철수", "E02", "Marketing"));
//            manager.addEmployee(new Employee("홍길동", "E01", "HR")); // 중복 추가 시도
//
//            manager.findEmployee("E01");
//            manager.removeEmployee(new Employee("홍길동", "E01", "HR"));
//            manager.findEmployee("E01");
//        }
//    }
//}