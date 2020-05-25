package com.cds;

import com.cds.core.Command;
import com.cds.core.CommandFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.Scanner;

@SpringBootApplication
public class ComponentDependencySystemApplication implements CommandLineRunner {

	@Autowired
	ApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(ComponentDependencySystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		File file = null;
		if(!StringUtils.isEmpty(args)){
			for(String arg : args){
				file = new File(arg);
			}
		}else{
			Resource resource = new ClassPathResource("input.txt");
			file = resource.getFile();
		}

		try {
		Scanner scanner = new Scanner(file);
			while (scanner.hasNext()) {
				String line = scanner.nextLine();
				System.out.println(line);
				String[] arguments = line.split("\\s+");
			try {
					Command command = CommandFactory.getInstance(context, arguments[0]);
					if(null != command) command.execute(arguments);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
		catch (Exception e){

		}

	}
}
