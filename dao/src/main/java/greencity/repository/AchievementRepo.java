package greencity.repository;

import greencity.entity.Achievement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AchievementRepo extends JpaRepository<Achievement, Long> {

    @Query("SELECT DISTINCT a FROM Achievement a" +
            "  JOIN AchievementTranslation at on at.achievement.id = a.id "
            + "WHERE CONCAT(a.id,'') LIKE LOWER(CONCAT('%', :query, '%')) "
            + "OR LOWER(at.title) LIKE LOWER(CONCAT('%', :query, '%'))"
            + "OR LOWER(at.description) LIKE LOWER(CONCAT('%', :query, '%'))"
            + "OR LOWER(at.message) LIKE LOWER(CONCAT('%', :query, '%'))"
            + "OR LOWER(a.achievementCategory.name) LIKE LOWER(CONCAT('%', :query, '%'))"
            + "OR CONCAT(a.condition, ' ') LIKE LOWER(CONCAT('%', :query, '%'))")
    Page<Achievement> searchAchievementsBy(Pageable paging, String query);
}
