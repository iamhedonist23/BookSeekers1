package in.hedonist.com.booksharing.Helper;

public class User {

    private int id;
    private String fullname;
    private String email;
    private String mobileno;
    private String semester;
    private String college;
    private String department;

    public User(int id, String jsonString, String userJsonString, String string, String fullname, String email, String mobileno) {
        this.fullname = fullname;
        this.email = email;
        this.mobileno = mobileno;
        this.semester=semester;
        this.college=college;
        this.department=department;
        this.id=id;


    }

    public User(String string, String string1, String string2, String string3, String string4, String string5) {


    }

    public int getID(){
        return id;
    }


    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileno() {
        return mobileno;
    }
    public String getCollege() {
        return college;
    }
    public String getSemester() {
        return semester;
    }
    public String getDepartment() {
        return department;
    }
}
