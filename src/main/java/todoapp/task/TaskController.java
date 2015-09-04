package todoapp.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1")
class TaskController {

    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.POST)
    public Task create(@RequestBody Task task) {
        return taskService.create(task);
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public Page<Task> findAll(@RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "20") int pageSize,
            @RequestParam(name = "title", required = false, defaultValue = "") String titlePattern,
            @RequestParam(required = false, defaultValue = "") Boolean completed) {
        return taskService.findAll(pageNumber, pageSize, completed, titlePattern);
    }

    @RequestMapping(value = "/tasks/{taskId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long taskId) {
        taskService.delete(taskId);
    }

    @RequestMapping(value = "/tasks/{taskId}", method = RequestMethod.PUT)
    public Task delete(@RequestBody Task task) {
        return taskService.update(task);
    }

    @RequestMapping(value = "/tasks/{taskId}", method = RequestMethod.GET)
    public Task findOne(@PathVariable Long taskId) {
        return taskService.findOne(taskId);
    }

}
