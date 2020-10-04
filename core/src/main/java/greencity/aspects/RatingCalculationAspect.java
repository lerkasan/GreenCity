package greencity.aspects;

import greencity.annotations.RatingCalculation;
import greencity.annotations.RatingCalculationEnum;
import greencity.entity.RatingStatistics;
import greencity.entity.User;
import greencity.service.RatingStatisticsService;
import greencity.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * This aspect is used for User rating calculating.
 *
 * @author Taras Dovganyuk
 */
@Slf4j(topic = "ratingLog")
@Aspect
@Component
@AllArgsConstructor
@Builder
public class RatingCalculationAspect {
    private UserService userService;
    private RatingStatisticsService ratingStatisticsService;

    /**
     * This pointcut {@link Pointcut} is used for define annotation to processing.
     *
     * @param ratingCalculation is used for recognize methods to processing.
     */
    @Pointcut("@annotation(ratingCalculation)")
    public void myAnnotationPointcut(RatingCalculation ratingCalculation) {
        /*
         * Complete if needed.
         */
    }

    /**
     * This method is used for User {@link User} rating calculation and logging.
     *
     * @param ratingCalculation is used for recognize methods to processing.
     * @author Taras Dovganyuk
     */
    @AfterReturning(pointcut = "myAnnotationPointcut(ratingCalculation)",
        argNames = "ratingCalculation")
    private void ratingCalculation(RatingCalculation ratingCalculation) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(authentication.getName());
        RatingCalculationEnum rating = ratingCalculation.rating();

        user.setRating(user.getRating() + rating.getRatingPoints());
        userService.save(user);

        RatingStatistics ratingStatistics = RatingStatistics
            .builder()
            .rating(user.getRating())
            .ratingCalculationEnum(rating)
            .user(user)
            .pointsChanged(rating.getRatingPoints())
            .build();
        ratingStatisticsService.save(ratingStatistics);

        log.info("Event {}, UserId {}, points {}, current rating {}",
            rating, user.getId(), rating.getRatingPoints(), user.getRating());
    }
}
