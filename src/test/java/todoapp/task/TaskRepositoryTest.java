package todoapp.task;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import todoapp.core.RepositoryTest;

public class TaskRepositoryTest extends RepositoryTest {

    @Autowired
    TaskRepository repo;

    @Test
    public void findWithSpec() {
        Task task = new Task();
        task.setTitle("test");
        Task task1 = new Task();
        task1.setTitle("test");
        repo.save(task);
        repo.save(task1);
        
        List<Task> tasks = repo.findAll(TaskSpecification.titleContains("t"));
        System.out.println(tasks);
    }
    
    
}
