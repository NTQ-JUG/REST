package todoapp.task;

import org.springframework.data.domain.Page;

public interface TaskService {

    Task create(Task task);

    Page<Task> findAll(int pageNumber, int pageSize, Boolean completed, String titlePattern);

    void delete(Long id);

    Task update(Task task);

    Task findOne(Long id);
}
