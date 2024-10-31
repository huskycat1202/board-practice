package boardcafe.boardpractice.todo.presentation.request;

import boardcafe.boardpractice.todo.application.request.TodoCreateServiceRequest;
import jakarta.validation.constraints.NotBlank;

public record TodoCreateRequest(
        // not blank = 뭐라도 적어야함. 빈칸은 안된다!
        // not null = " " 허용 => 안돼!
        @NotBlank(message = "할 일을 입력 필수")
        String content
) {
    public TodoCreateServiceRequest toServiceRequest(){
        return new TodoCreateServiceRequest(content);
    }
}
