package io.pick5.email.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.EntityResponse;

import io.pick5.domain.User;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SmtpEmailServiceImpl implements SmtpEmailService {

	
	@Value("${routes.base.user.app}")
	private static String USER_APP_PATH;
	
	
	@Autowired
    public JavaMailSender sender;
 
   
	
	public EntityResponse<Flux<User>> getCurrentUsers(){
		
		
		
		
		  MimeMessage message = sender.createMimeMessage();
	      MimeMessageHelper helper = new MimeMessageHelper(message);

	      try {
	          helper.setTo("joja5627@gmail.com");
	          helper.setText("code");
	          helper.setSubject("Confirmation Code");
	      } catch (MessagingException e) {
	          e.printStackTrace();
	         
	      }
	      sender.send(message);
		
		return null;
		
		
		
		
	}
	
	
	
	
	
	
}
