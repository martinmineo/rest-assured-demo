import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"status", "data"})
public class Employee {
    private String name;
    private int salary;
    private int age;

    public Employee(String name, int salary, int age) {
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("name")
    public String getName() {
        return this.name;
    }

    @JsonProperty("salary")
    public void setSalary(int salary) {
        this.salary = salary;
    }

    @JsonProperty("salary")
    public int getSalary() {
        return this.salary;
    }

    @JsonProperty("age")
    public void setAge(int age) {
        this.age = age;
    }

    @JsonProperty("age")
    public int getAge() {
        return this.age;
    }
}
