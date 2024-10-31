package boardcafe.boardpractice.todo.application;

import boardcafe.boardpractice.todo.exception.TodoNotFoundException;
import boardcafe.boardpractice.todo.domain.Todo;
import boardcafe.boardpractice.todo.domain.repository.TodoRepository;
import boardcafe.boardpractice.todo.application.request.TodoCreateServiceRequest;
import boardcafe.boardpractice.todo.application.response.TodoItem;
import boardcafe.boardpractice.todo.application.response.TodosResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static boardcafe.boardpractice.common.exception.ErrorCode.*;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodosResponse getAllTodos(){
        final List<Todo> todos = todoRepository.findByDeletedFalse();
        return new TodosResponse(todos.stream()
                .map(TodoItem::of)
                .toList());
    }

    @Transactional
    public TodoItem save(TodoCreateServiceRequest request){
        final Todo todo = todoRepository.save(new Todo(request.content()));
        return TodoItem.of(todo);
    }

    @Transactional
    public void updateTodoCompleted(final Long id){
        final Todo todo = todoRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new TodoNotFoundException(NOT_FOUND_TODO_ID));
        todo.updateCompleted();
    }

    @Transactional
    public void delete(final Long id){
        final Todo todo = todoRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new TodoNotFoundException(NOT_FOUND_TODO_ID));
        todo.removeTodo();
    }
}
