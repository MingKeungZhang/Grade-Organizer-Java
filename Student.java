/**
 * Create the students class. The student class hold the names of each student
 */
public class Student 
{
	//To hold the first and last name of student
	protected String firstName, lastName;
	
	/**
	 * The default constructor. It does nothing
	 */
	public Student() 
	{
	}
	
	/**
	 * The constructor. Initialized the variables
	 * @param firstName the first name
	 * @param lastName the last name
	 */
	public Student(String firstName, String lastName)
	{
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * print the student's name
	 */
    public String toString()
    {
		String string = firstName + " " + lastName;
		return string;
    }
}
