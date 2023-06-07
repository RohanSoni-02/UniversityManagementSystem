/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package secure;

import java.util.ArrayList;

/**
 *
 * @author elau
 */
public class SetupEmployeeDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // the database object to access the actual database
        EmployeeDB db = new EmployeeDB();
        UserDB u_db = new UserDB();
        StudentDB s_db = new StudentDB();
        TeacherDB t_db = new TeacherDB();
        CourseDB c_db = new CourseDB();
        EnrollmentDB e_db = new EnrollmentDB();

        // make sure no name conflicts
        try {
            db.destroyDBTable();
            u_db.destroyDBTable();
            s_db.destroyDBTable();
            t_db.destroyDBTable();
            c_db.destroyDBTable();
            e_db.destroyDBTable();
        } catch (Exception ex) {
        }

        // create the database table
        System.out.println("Create an empty database table Employee");
        db.createDBTable();

        System.out.println("Add several static records in the database table");

        // prepare data
        Employee emp001 = new Employee("00001", "Rohan Soni", "1234567890", "1 John Street, Hawthorn",
                "rohan@ums.com.au", "11111111", "ED-APP-ADMIN", "098-765432-1", 50000.0, true);
        Employee emp002 = new Employee("00002", "Rahul Soni", "2345678901", "02/02/2000",
                "Bachelor of Business admin", "22222222", "ED-APP-USERS", "Parvesh", 112.50, true);
        Employee emp003 = new Employee("00003", "Kiran Soni", "3456789012", "Female",
                "BAB", "33333333", "ED-APP-USERS", "Bachelor of Business admin/ bachelor of computer science", 75000.0, true);
        Employee emp004 = new Employee("00006", "Dave", "4567890123", "4 Pete Street, Hawthorn",
                "dave@secure.com.au", "44444444", "ED-APP-USERS", "321-098765-4", 100000.0, true);

        // prepare list
        ArrayList<Employee> empList = new ArrayList<>();
        empList.add(emp001);
        empList.add(emp002);
        empList.add(emp003);
        empList.add(emp004);

        // add data to db
        db.addRecords(empList);

        // create the user database table
        System.out.println("Create an empty database table User");
        u_db.createDBTable();

        System.out.println("Add several static records in the database table");

        // prepare data
        String plainPwd5 = "11111111";
        String pwd5 = SHA256Encoder.sha256(plainPwd5);
        User usr001 = new User("00001", "Rohan Soni", "1234567890",
                "rohan@ums.com.au", pwd5, "ED-APP-ADMIN", true);

        String plainPwd6 = "22222222";
        String pwd6 = SHA256Encoder.sha256(plainPwd6);
        User usr002 = new User("00002", "Rahul Soni", "2345678901",
                "rahul@ums.com.au", pwd6, "ED-APP-STUDENT", true);

        String plainPwd7 = "33333333";
        String pwd7 = SHA256Encoder.sha256(plainPwd7);
        User usr003 = new User("00003", "Kiran Soni", "2345178991",
                "kiran@ums.com.au", pwd7, "ED-APP-TEACHER", true);
        
        String plainPwd8 = "33333333";
        String pwd8 = SHA256Encoder.sha256(plainPwd7);
        User usr004 = new User("00004", "Test", "123456798",
                "test@ums.com.au", pwd8, "ED-APP-STUDENT", true);

        // prepare list
        ArrayList<User> usrList = new ArrayList<>();
        usrList.add(usr001);
        usrList.add(usr002);
        usrList.add(usr003);
        usrList.add(usr004);

        // add data to db
        u_db.addRecords(usrList);

        //----------------------------------------------------------------------
        // create the student database table   
        System.out.println("Create an empty database table Student");
        s_db.createDBTable();

        System.out.println("Add several static records in the database table");

        // prepare data
        Student stu001 = new Student("01", "00002", "Rahul Soni", "02/02/2000",
                "Hughesdale", "Parvesh", "Bachelor of Business Admin");

        // prepare list
        ArrayList<Student> stuList = new ArrayList<>();
        stuList.add(stu001);

        // add data to db
        s_db.addRecords(stuList);

        //----------------------------------------------------------------------
        // create the teacher database table   
        System.out.println("Create an empty database table Teacher");
        t_db.createDBTable();

        System.out.println("Add several static records in the database table");

        // prepare data
        Teacher tch001 = new Teacher("01", "00003", "Kiran Soni", "02/05/1974",
                "Chandigarh", "kiran@ums.com.au", "Bachelor of Business Admin/ bachelor of computer science", "Female", "BAB");

        // prepare list
        ArrayList<Teacher> tchList = new ArrayList<>();
        tchList.add(tch001);

        // add data to db
        t_db.addRecords(tchList);

        //----------------------------------------------------------------------
        // create the course database table   
        System.out.println("Create an empty database table Course");
        c_db.createDBTable();

        System.out.println("Add several static records in the database table");

        // prepare data
        Course c001 = new Course("01", "Creating secure and scalable", "Teaches you security and scalability",
                12);

        // prepare list
        ArrayList<Course> cList = new ArrayList<>();
        cList.add(c001);

        // add data to db
        c_db.addRecords(cList);

        //----------------------------------------------------------------------
        // create the enrollment database table   
        System.out.println("Create an empty database table Enrollment");
        e_db.createDBTable();

        System.out.println("Add several static records in the database table");

        // prepare data
        Enrollment e001 = new Enrollment("01", "00001", "01", 91.5);
        Enrollment e002 = new Enrollment("02", "00002", "02", 92.5);

        // prepare list
        ArrayList<Enrollment> eList = new ArrayList<>();
        eList.add(e001);
        eList.add(e002);

        // add data to db
        e_db.addRecords(eList);
    }
}
