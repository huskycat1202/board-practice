package boardcafe.boardpractice.paging.exception;

import boardcafe.boardpractice.common.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ArticleNotFoundException extends RuntimeException {

    private final ErrorCode errorCode;

    public ArticleNotFoundException(final ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
