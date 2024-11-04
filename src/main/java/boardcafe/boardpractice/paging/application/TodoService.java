package boardcafe.boardpractice.paging.application;

import boardcafe.boardpractice.paging.application.request.TodoCreateServiceRequest;
import boardcafe.boardpractice.paging.application.response.TodoItem;
import boardcafe.boardpractice.paging.application.response.TodosResponse;
import boardcafe.boardpractice.paging.domain.Todo;
import boardcafe.boardpractice.paging.domain.repository.TodoRepository;
import boardcafe.boardpractice.paging.exception.TodoNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static boardcafe.boardpractice.common.exception.ErrorCode.NOT_FOUND_TODO_ID;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodosResponse getAllTodos() {
        final List<Todo> todos = todoRepository.findByDeletedFalse();
        return new TodosResponse(todos.stream()
                .map(TodoItem::of)
                .toList());
    }

    @Transactional
    public TodoItem save(TodoCreateServiceRequest request) {
        final Todo todo = todoRepository.save(new Todo(request.content()));
        return TodoItem.of(todo);
    }

    @Transactional
    public void updateTodoCompleted(final Long todoId) {
        final Todo todo = todoRepository.findByIdAndDeletedFalse(todoId)
                .orElseThrow(() -> new TodoNotFoundException(NOT_FOUND_TODO_ID));
        todo.updateCompleted();
    }

    @Transactional
    public void delete(final Long todoId) {
        final Todo todo = todoRepository.findByIdAndDeletedFalse(todoId)
                .orElseThrow(() -> new TodoNotFoundException(NOT_FOUND_TODO_ID));
        todo.removeTodo();
    }
}

