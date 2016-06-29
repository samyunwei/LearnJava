package abstractClasses;

public class Student extends Person {

	private String major;
	
	public Student(String n,String m)
	{
		super(n);
		major = m;
	}

	public String GetDescription() {
		return String.format("a student majoring in %s",major);
	}
	
}
