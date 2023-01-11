package at.itkolleg.studentenverwaltung;

import at.itkolleg.studentenverwaltung.domain.Student;
import at.itkolleg.studentenverwaltung.repositories.DBAccessStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentenverwaltungApplication implements ApplicationRunner {

	@Autowired
	DBAccessStudent dbAccessStudent; //über Datenlayer auf Repository zugreifen (abkapseln!)

	public static void main(String[] args) {
		SpringApplication.run(StudentenverwaltungApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		this.dbAccessStudent.saveStudent(new Student("Selina", "6382"));
		this.dbAccessStudent.saveStudent(new Student("Berni", "6382"));
		this.dbAccessStudent.saveStudent(new Student("Michaela", "6370"));
		this.dbAccessStudent.saveStudent(new Student("Michaela2", "6382"));
	}
}
