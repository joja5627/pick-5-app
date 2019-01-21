package io.pick5.email.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import io.pick5.domain.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmailServiceImpl implements EmailService {
	
	@Autowired
    private MailSender mailSender;

	@Value("${email.from}")
	private String from;
	
	public void buildAndSendEmail(User user){
		
	     SimpleMailMessage msg = new SimpleMailMessage();
	     
	     msg.setFrom(from);
	     msg.setTo(user.getEmail());
	     msg.setSubject("test subject");
	     msg.setText("spring email integration test");
	     
	     mailSender.send(msg);
	   
	      
	}
    
 
	
 
}
