import lombok.Data;

@Data
class Student {
    public Student(String name, String school, int age, String sex){
        this.name = name;
        this.school = school;
        this.age = age;
        this.sex = sex;
    }
    private String name;
    private String school;
    private int age;
    private String sex;

}
