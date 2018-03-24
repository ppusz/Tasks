package com.crud.tasks.scheduler;

import com.crud.tasks.configs.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.MailCreatorService;
import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmailScheduler {

    private static final String SUBJECT = "Tasks: once a day mail";

    @Autowired
    private SimpleEmailService simpleEmailService;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private AdminConfig adminConfig;

    @Scheduled(cron = "0 0 10 * * *")
    //@Scheduled(fixedDelay = 10000)
    public void sendInformationEmail() {
        long size = taskRepository.count();
        List<String> taskTitles = taskRepository.findAll().stream()
                .map(task -> task.getTitle())
                .collect(Collectors.toList());
        simpleEmailService.send(
                new Mail(
                        Mail.Type.DAILY_INFORMATION_EMAIL,
                        adminConfig.getAdminMail(),
                        "",
                        SUBJECT,
                        "Currently in database you got: " + size + " task" + (size == 1L ? "" : "s") + "."));
    }
}
