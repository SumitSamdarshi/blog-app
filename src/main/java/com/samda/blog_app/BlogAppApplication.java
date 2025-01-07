package com.samda.blog_app;

import com.samda.blog_app.entities.Role;
import com.samda.blog_app.repositories.RoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class BlogAppApplication implements CommandLineRunner {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.passwordEncoder.encode("root"));

		try {

			if (!roleRepo.existsById(501)) {
				Role role1 = new Role();
				role1.setName("ADMIN_USER");
				role1.setId(501);
				roleRepo.save(role1);
			}

			if (!roleRepo.existsById(502)) {
				Role role2 = new Role();
				role2.setName("NORMAL_USER");
				role2.setId(502);
				roleRepo.save(role2);
			}

//			Role role = new Role();
//			role.setId(5);
//			role.setName("abc");
//
//			Role role2 = new Role();
//			role2.setId(6);
//			role2.setName("def");

//			List<Role> roles = List.of(role,role2);
//			List<Role> rolesResult	 = this.roleRepo.saveAll(roles);
//			rolesResult.forEach(r->{
//				System.out.println(r.getName());
//			});
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("role creation exception occured");
		}
	}
}
