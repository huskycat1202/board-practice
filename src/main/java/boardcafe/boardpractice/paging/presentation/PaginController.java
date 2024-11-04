package boardcafe.boardpractice.paging.presentation;

import boardcafe.boardpractice.paging.application.TodoService;
import boardcafe.boardpractice.paging.application.response.TodoItem;
import boardcafe.boardpractice.paging.application.response.TodosResponse;
import boardcafe.boardpractice.paging.presentation.request.TodoCreateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequiredArgsConstructor
@RestController
public class PagingController {

    private final TodoService todoService;

    @GetMapping("/{{domain}}/articles/paging/offset?size={size}&page={page}")
    public ResponseEntity<TodosResponse> getByOffset(@PathVariable size, @PathVariable page) {
        return ResponseEntity.ok().body(todoService.getAllTodos());
    }

    @GetMapping("/{{domain}}/articles/paging/cursor?size={size}&cursorId={id}")
    public ResponseEntity<TodosResponse> getByCursor(@PathVariable size, @PathVariable id) {
        return ResponseEntity.ok().body(todoService.getAllTodos());
    }

    @PostMapping("/articles/make")
    public ResponseEntity<TodoItem> makeArticles() {
        final TodoItem todoItem = todoService.save(todoCreateRequest.toServiceRequest());
        return ResponseEntity.created(URI.create("/todos/" + todoItem.id())).body(todoItem);
    }

}
