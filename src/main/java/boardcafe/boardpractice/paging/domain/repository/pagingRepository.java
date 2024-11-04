package boardcafe.boardpractice.paging.domain.repository;

import boardcafe.boardpractice.paging.domain.Article;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;

public interface pagingRepository extends JpaRepository<Article, Long> {
    @Query("SELECT a FROM Article a WHERE a.id > :id ORDER BY a.id asc")
    Slice<Article> findMoreArticle(Long id, Pageable pageable);
}
