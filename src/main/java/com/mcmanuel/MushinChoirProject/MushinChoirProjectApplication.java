package com.mcmanuel.MushinChoirProject;

import com.mcmanuel.MushinChoirProject.entity.Grade;
import com.mcmanuel.MushinChoirProject.repository.GradeRepository;
import com.mcmanuel.MushinChoirProject.service.intf.GradeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableAsync
@EnableWebSecurity
@EnableMethodSecurity
public class MushinChoirProjectApplication {
//	private final GradeService gradeService;

//    public MushinChoirProjectApplication(GradeService gradeService) {
//        this.gradeService = gradeService;
//    }

    public static void main(String[] args) {
		SpringApplication.run(MushinChoirProjectApplication.class, args);
	}
//
//	@Bean
//	public CommandLineRunner commandLineRunner(){
//		return args -> {
//			if(gradeService.getGradeById("PRELIM").isEmpty()){
//				Grade grade = new Grade("PRELIM");
//				gradeService.addGrade(grade);
//				gradeService.addGrade(new Grade("GRADE 1"));
//				gradeService.addGrade(new Grade("GRADE 2"));
//				gradeService.addGrade(new Grade("GRADE 3"));
//				gradeService.addGrade(new Grade("GRADE 4"));
//				gradeService.addGrade(new Grade("GRADE 5"));
//			}
//			System.out.println("grades created");
//		};
//	}
}
