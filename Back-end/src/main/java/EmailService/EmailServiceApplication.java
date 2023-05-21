package EmailService;

import Controller.ServerControllers.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = ContactsPageController.class)
@ComponentScan(basePackageClasses = DraftPageController.class)
@ComponentScan(basePackageClasses = InboxPageController.class)
@ComponentScan(basePackageClasses = SentPageController.class)
@ComponentScan(basePackageClasses = LoginPageController.class)
@ComponentScan(basePackageClasses = SendEmailPageController.class)
@ComponentScan(basePackageClasses = TrashPageController.class)
public class EmailServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailServiceApplication.class, args);
	}

}
