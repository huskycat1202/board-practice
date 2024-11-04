package boardcafe.boardpractice.paging.application.response;

import boardcafe.boardpractice.paging.domain.Todo;

public record ArticleItem(Long id, String content, boolean completed) {

    public static TodoItem of(Todo todo) {
        return new TodoItem(todo.getId(), todo.getContent(), todo.getCompleted());
    }
}
