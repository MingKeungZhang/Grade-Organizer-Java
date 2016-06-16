/**
 * Create the StudentGrade class. extends the student class and implements comparable
 */
public class StudentGrade extends Student implements Comparable<StudentGrade>
{
	//Hold the student's grade
	protected int grade;

	/**
	 * The default constructor. Does nothing
	 */
	public StudentGrade()
	{
	}
	
	/**
	 * The constructor. Initialized the variables
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param grade the grade
	 */
	public StudentGrade(String firstName, String lastName, int grade)
	{
		super(firstName, lastName);
		this.grade = grade;
	}

	/**
	 * Used to compare the students.
	 */
	public int compareTo(StudentGrade student) {
		int result = 0;
		int grade2 = student.grade;
		if(grade < grade2)
		{
			result = -1;
		}
		else if(grade > grade2)
		{
			result = 1;
		}
		return result;
	}
	
	/**
	 * print the student information
	 */
    public String toString()
    {
		String string = String.format("%-40s %s\n", firstName + " " + lastName, grade);
		return string;
    }
}

