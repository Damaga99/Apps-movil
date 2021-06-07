package es.uniovi.imovil.fragmentosmaterialresuelta;

public class Course {
	
	private String name;
	private String teacher;
	private String description;
	
	public Course(String name, String teacher, String description) {
		
		if (name == null || teacher == null || name.isEmpty() || teacher.isEmpty() || description == null || description.isEmpty()) {
			throw new IllegalArgumentException();
		}
		
		this.name = name;
		this.teacher = teacher;
		this.description = description;
	}

	public String getName() {
		
		return name;
	}

	public String getTeacher() {
		
		return teacher;
	}

	public String getDescription() {
		return description;
	}
}
