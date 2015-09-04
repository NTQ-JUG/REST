package todoapp.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = TaskException.class)
    public Task create(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task findOne(Long id) {
        return checkTaskExists(taskRepository.findOne(id));
    }

    public Iterable<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = TaskException.class)
    public void delete(Long id) {
        if (!taskRepository.exists(id)) {
            throw new TaskException(404, "Task not found", TaskException.class.getName());
        }
        taskRepository.delete(id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = TaskException.class)
    public Task update(Task task) {
        checkTaskExists(task);
        Task existingTask = taskRepository.findOne(task.getId());
        checkTaskExists(existingTask);
        existingTask.update(task);
        return taskRepository.save(task);
    }

    private Task checkTaskExists(Task task) {
        if (task == null || task.getId() == null) {
            throw new TaskException(404, "Task not found", TaskException.class.getName());
        }

        return task;
    }

    @Override
    public Page<Task> findAll(int pageNumber, int pageSize, Boolean completed, String titlePattern) {

        if (completed != null && !titlePattern.isEmpty()) {
            return taskRepository.findAll(TaskSpecification.titleContainsPatternAndCompleted(titlePattern, completed),
                    new PageRequest(pageNumber, pageSize));
        }
        if (completed == null && !titlePattern.isEmpty()) {
            return taskRepository.findAll(TaskSpecification.titleContains(titlePattern),
                    new PageRequest(pageNumber, pageSize));
        }

        if (completed != null && titlePattern.isEmpty()) {
            return taskRepository.findAll(TaskSpecification.isCompleted(completed),
                    new PageRequest(pageNumber, pageSize));
        }

        return taskRepository.findAll(new PageRequest(pageNumber, pageSize));
    }
}
