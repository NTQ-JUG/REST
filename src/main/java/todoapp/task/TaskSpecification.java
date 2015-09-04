package todoapp.task;

import org.springframework.data.jpa.domain.Specification;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

final public class TaskSpecification {

    private TaskSpecification() {
    }

    public static Specification<Task> hasTitle(final String title) {
        return (root, query, cb) -> {
            return cb.equal(root.get("title"), title);
        };
    }

    public static Specification<Task> titleContains(final String pattern) {
        return (root, query, cb) -> {
            return cb.like(root.get("title"), "%" + pattern + "%");
        };
    }

    public static Specification<Task> isCompleted(final Boolean completed) {
        return (root, query, cb) -> {
            return cb.equal(root.get("completed"), completed);
        };
    }

    public static Specification<Task> titleContainsPatternAndCompleted(final String pattern, final Boolean completed) {
        Preconditions.checkArgument(Strings.isNullOrEmpty(pattern), "Pattern should not be null");
        Preconditions.checkNotNull(completed, "Completed should not be null");
        return (root, query, cb) -> {
            return cb.and(cb.equal(root.get("completed"), completed), cb.like(root.get("title"), "%" + pattern + "%"));
        };
    }
}
