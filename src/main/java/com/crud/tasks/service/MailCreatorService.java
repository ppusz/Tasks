package com.crud.tasks.service;

import com.crud.tasks.configs.AdminConfig;
import com.crud.tasks.configs.ApplicationConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private ApplicationConfig applicationConfig;

    @Autowired
    private TaskRepository taskRepository;

    public String build(String message, Mail.Type mailType) {
        switch (mailType) {
            case TRELLO_CARD_EMAIL:
                return buildTrelloCardEmail(message);
            case DAILY_INFORMATION_EMAIL :
                return buildDailyInformationEmail(message);
            default:
                return message;
        }
    }

    public String buildDailyInformationEmail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye_message","Thank you for using Tasks app!");
        context.setVariable("application_data", applicationConfig);
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("tasks", taskRepository.findAll());
        return templateEngine.process("mail/daily-information-mail", context);
    }

    public String buildTrelloCardEmail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye_message","Thank you for using Tasks app!");
        context.setVariable("application_data", applicationConfig);
        context.setVariable("show_button", true);
        context.setVariable("is_friend", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}
