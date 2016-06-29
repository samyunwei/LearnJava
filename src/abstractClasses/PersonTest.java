package abstractClasses;

public class PersonTest {

	public static void main(String[] args) {
		Person[] people = new Person[2];
		
		people[0] = new Employee("testEmployee", 50000, 1989, 10, 1);
		people[1] = new Student("testStudent", "CS");
		
		for(Person p : people)
		{
			System.out.printf("%s : %s\n", p.getName(),p.GetDescription());
		}

	}

}
