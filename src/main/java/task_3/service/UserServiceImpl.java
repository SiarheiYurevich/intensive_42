package task_3.service;

import task_3.annotations.IntensiveComponent_AnastasiaYaromich;
import task_3.annotations.IntensiveInject_AnastasiaYaromich;
import task_3.annotations.IntensiveSingletonScope_AnastasiaYaromich;
import task_3.repository.UserRepository;

@IntensiveSingletonScope_AnastasiaYaromich
@IntensiveComponent_AnastasiaYaromich
public class UserServiceImpl implements UserService {

    @IntensiveInject_AnastasiaYaromich
    private UserRepository userRepository;

    @Override
    public void findById(String id) {

    }

    @Override
    public void findAll() {
        System.out.println("Hello");
    }
}
