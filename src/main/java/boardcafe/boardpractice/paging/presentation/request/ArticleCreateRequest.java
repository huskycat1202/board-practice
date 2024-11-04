package boardcafe.boardpractice.paging.presentation.request;

import boardcafe.boardpractice.paging.application.request.TodoCreateServiceRequest;
import boardcafe.boardpractice.paging.application.response.ArticleItem;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record ArticleCreateRequest(List<ArticleItem> articles) {
}
