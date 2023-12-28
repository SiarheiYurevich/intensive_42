package task_3;

import task_3.context.IntensiveContext_AnnotationConfig_AnastasiaYaromich;
import task_3.service.UserService;

public class ApplicationRunner {
    public static void main(String[] args) {
        IntensiveContext_AnnotationConfig_AnastasiaYaromich context =  new IntensiveContext_AnnotationConfig_AnastasiaYaromich("task_3");
        UserService userService = context.getObject(UserService.class);
        userService.findAll();
    }
}