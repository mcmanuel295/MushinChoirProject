package com.mcmanuel.MushinChoirProject.model;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED;


@Service
@Async
public class EmailService {
    private JavaMailSender mailSender;
    private SpringTemplateEngine templateEngine;

    public void sendEmail(
            String to,
            String name,
            String headerName,
            String subject,
            String confirmationUrl,
            String activationCode
    ) throws MessagingException {

        String templateName;

        if (headerName == null) {
            templateName= "comfirm-email";
        }
        else {
            templateName=headerName.toLowerCase();
        }

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(
                mimeMessage,
                MULTIPART_MODE_MIXED,
                UTF_8.name()
        );

        helper.setFrom("contact@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);

        Map<String,Object> properties = new HashMap<>();
        properties.put("name",name);
        properties.put("configurationUrl", confirmationUrl);
        properties.put("activation_url",activationCode);


        Context context =new Context();
        context.setVariables(properties);

        String template =templateEngine.process(templateName,context);
        helper.setText(template,true);

        mailSender.send(mimeMessage);
    }
}
