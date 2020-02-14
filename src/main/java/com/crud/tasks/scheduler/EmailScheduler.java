package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    private static final String SUBJECT = "One info email per day";
    private static final String ONETASKMESSAGE = "Currently you have 1 task in your database";

    @Autowired
    SimpleEmailService emailService;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    AdminConfig adminConfig;

    @Scheduled(cron = "0 0 10 * * *")
    public void sendInformationEmail(){
        emailService.send(prepareMail());
    }
    public Mail prepareMail(){
        long size = taskRepository.count();
        if (size!=1) {
            return new Mail(adminConfig.getAdminMail(),
                    SUBJECT,
                    "Currently you have: " + size + " tasks");
        }
        else{
            return new Mail(adminConfig.getAdminMail(),
                    SUBJECT,
                    ONETASKMESSAGE);
        }
    }
}
