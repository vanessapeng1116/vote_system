package com.esun;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;

@SpringBootApplication
public class VotingSystemApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(VotingSystemApplication.class, args);
	}

	//自動開啟網頁
	@Override
	public void run(ApplicationArguments args) throws Exception {
		String url = "http://localhost:8080/";
		System.out.println("正在嘗試叫瀏覽器");

		String os = System.getProperty("os.name").toLowerCase();
		ProcessBuilder processBuilder = new ProcessBuilder();

		try {
			if (os.contains("mac")) {
				processBuilder.command("open", url);
				processBuilder.start();
				System.out.println("發送開啟網頁指令！");
			} else if (os.contains("win")) {
				processBuilder.command("cmd", "/c", "start", url);
				processBuilder.start();
			}
		} catch (IOException e) {
			System.err.println("自動開啟瀏覽器被權限阻擋，請手動輸入網址：" + url);
		}
	}
}