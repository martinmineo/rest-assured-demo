import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import static io.restassured.RestAssured.given;

public class DummyEmployeeApiTest {
    private RequestSpecification requestSpec;


    @BeforeClass
    public void createRequestSpec() {
        requestSpec = new RequestSpecBuilder().setBaseUri("https://dummy.restapiexample.com").build();
    }

    @Test
    public void requestAllEmployees_checkStatusCode_numberOfEmployees() {
        given().
            spec(requestSpec).
        when().
            get("api/v1/employees").
        then().
            assertThat().
            statusCode(200).
            body("data.size()", is(24));
    }

    @DataProvider (name = "employeesData")
    public Object[][] employeesData() {
        return new Object[][] {
                { "1", "Tiger Nixon", 320800},
                { "2", "Garrett Winters", 170750},
                { "3", "Ashton Cox", 86000},
        };
    }

    @Test (dataProvider = "employeesData")
    public void requestEmployee_checkStatusCode_employeeName(String id, String name, int salary) {
        given().
            spec(requestSpec).
        when().
            get("api/v1/employee/" + id).
        then().
            assertThat().
            statusCode(200).
            body("data.employee_name", equalTo(name)).
            body("data.employee_salary", equalTo(salary));
    }

    @Test
    public void createNewEmployee() {
        Faker faker = new Faker();

        String name = faker.name().fullName();
        int salary = faker.number().numberBetween(10000, 500000);
        int age = faker.number().numberBetween(20, 90);

        Employee employee = new Employee(name, salary, age);

        given().
            spec(requestSpec).
            contentType(ContentType.JSON).
            body(employee).
        when().
            post("api/v1/create").
        then().
            assertThat().
                statusCode(200);
    }
}
