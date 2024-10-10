package com.quizstats.quizspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class QuizSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizSpringApplication.class, args);
		runPythonScript();

		System.out.println("Application running");
	}

	private static void runPythonScript() {
		ProcessBuilder processBuilder = new ProcessBuilder("python3", resolvePythonScriptPath());
		processBuilder.redirectErrorStream(true);
		try {
			processBuilder.start();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	private static String resolvePythonScriptPath(){
   		File file = new File("db_initialization/copy_data.py");
   		return file.getAbsolutePath();
	}
}

