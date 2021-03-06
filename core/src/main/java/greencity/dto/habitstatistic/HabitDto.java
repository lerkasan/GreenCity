package greencity.dto.habitstatistic;

import greencity.dto.user.HabitDictionaryDto;
import java.time.ZonedDateTime;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class HabitDto {
    @NotNull
    private Long id;
    private String name;
    private Boolean status;
    private String description;
    private String habitName;
    private String habitItem;
    private ZonedDateTime createDate;
    private List<HabitStatisticDto> habitStatistics;
    private HabitDictionaryDto habitDictionary;
}
