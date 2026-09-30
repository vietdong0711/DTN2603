public class Student {
    private static int COUNT = 1;

    private int id;
    private String name;
    private String clazz;

    public Student() {

    }

    public Student(String name, String clazz) {
        this.id = COUNT++;
        this.name = name;
        this.clazz = clazz;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", clazz='" + clazz + '\'' +
                '}';
    }
}
