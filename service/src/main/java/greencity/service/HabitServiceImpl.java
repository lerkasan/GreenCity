package greencity.service;

import greencity.constant.ErrorMessage;
import greencity.dto.PageableDto;
import greencity.dto.habit.HabitDto;
import greencity.dto.habit.HabitManagementDto;
import greencity.dto.habit.HabitVO;
import greencity.dto.habittranslation.HabitTranslationManagementDto;
import greencity.entity.Habit;
import greencity.entity.HabitTranslation;
import greencity.entity.Language;
import greencity.exception.exceptions.NotFoundException;
import greencity.exception.exceptions.WrongIdException;
import greencity.repository.HabitRepo;
import greencity.repository.HabitTranslationRepo;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * Implementation of {@link HabitService}.
 *
 * @author Kovaliv Taras
 */
@Service
@AllArgsConstructor
public class HabitServiceImpl implements HabitService {
    private final HabitTranslationRepo habitTranslationRepo;
    private final LanguageService languageService;
    private final FileService fileService;
    private final HabitRepo habitRepo;
    private final ModelMapper modelMapper;
    private final HabitAssignService habitAssignService;
    private final HabitFactService habitFactService;
    private final AdviceService adviceService;

    /**
     * {@inheritDoc}
     */
    @Override
    public HabitDto getByIdAndLanguageCode(Long id, String languageCode) {
        Habit habit = habitRepo.findById(id)
            .orElseThrow(() -> new WrongIdException(ErrorMessage.HABIT_NOT_FOUND_BY_ID + id));
        HabitTranslation habitTranslation = habitTranslationRepo.findByHabitAndLanguageCode(habit, languageCode)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.HABIT_TRANSLATION_NOT_FOUND));
        return modelMapper.map(habitTranslation, HabitDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HabitManagementDto getById(Long id) {
        Habit habit = habitRepo.findById(id)
            .orElseThrow(() -> new WrongIdException(ErrorMessage.HABIT_NOT_FOUND_BY_ID + id));
        return modelMapper.map(habit, HabitManagementDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageableDto<HabitManagementDto> getAllHabitsDto(Pageable pageable) {
        Page<Habit> habits = habitRepo.findAll(pageable);
        List<HabitManagementDto> habitDtos = habitRepo.findAll()
            .stream()
            .map(habit -> modelMapper.map(habit, HabitManagementDto.class))
            .collect(Collectors.toList());
        return new PageableDto<>(
            habitDtos,
            habits.getTotalElements(),
            habits.getPageable().getPageNumber(),
            habits.getTotalPages());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageableDto<HabitDto> getAllHabitsByLanguageCode(Pageable pageable, String language) {
        Page<HabitTranslation> pages =
            habitTranslationRepo.findAllByLanguageCode(pageable, language);
        List<HabitDto> habitTranslationDtos =
            pages.stream()
                .map(habitTranslation -> modelMapper.map(habitTranslation, HabitDto.class))
                .collect(Collectors.toList());
        return new PageableDto<>(habitTranslationDtos, pages.getTotalElements(),
            pages.getPageable().getPageNumber(),
            pages.getTotalPages());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public HabitManagementDto saveHabitAndTranslations(HabitManagementDto habitManagementDto, MultipartFile image) {
        Habit habit = buildHabitWithTranslations(habitManagementDto);
        uploadImageForHabit(habitManagementDto, image, habit);

        habitRepo.save(habit);
        habitTranslationRepo.saveAll(habit.getHabitTranslations());
        return modelMapper.map(habit, HabitManagementDto.class);
    }

    /**
     * Method builds {@link Habit} with {@link HabitManagementDto} fields.
     *
     * @param habitManagementDto {@link HabitManagementDto} instance.
     * @return {@link Habit}.
     */
    private Habit buildHabitWithTranslations(HabitManagementDto habitManagementDto) {
        Habit habit = Habit.builder()
            .habitTranslations(
                habitManagementDto.getHabitTranslations().stream()
                    .map(translationDto -> HabitTranslation.builder()
                        .description(translationDto.getDescription())
                        .habitItem(translationDto.getHabitItem())
                        .name(translationDto.getName())
                        .language(modelMapper.map(
                            languageService.findByCode(translationDto.getLanguageCode()),
                            Language.class)).build())
                    .collect(Collectors.toList())
            ).build();
        habit.getHabitTranslations().forEach(habitTranslation -> {
            habitTranslation.setHabit(habit);
        });
        return habit;
    }

    /**
     * Method sets new image path for {@link Habit}.
     *
     * @param habitManagementDto {@link HabitManagementDto} instance.
     * @param image              {@link MultipartFile} image.
     * @param habit              {@link Habit} instance.
     */
    private void uploadImageForHabit(HabitManagementDto habitManagementDto, MultipartFile image, Habit habit) {
        if (!habitManagementDto.getImage().isEmpty()) {
            image = fileService.convertToMultipartImage(habitManagementDto.getImage());
        }
        if (image != null) {
            habit.setImage(fileService.upload(image).toString());
        } else {
            habit.setImage("");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(HabitManagementDto habitManagementDto, MultipartFile image) {
        Habit habit = habitRepo.findById(habitManagementDto.getId())
            .orElseThrow(() -> new WrongIdException(ErrorMessage.HABIT_NOT_FOUND_BY_ID));

        habit.getHabitTranslations()
            .forEach(ht -> {
                HabitTranslationManagementDto htmd = habitManagementDto.getHabitTranslations().stream()
                    .filter(e -> e.getLanguageCode().equals(ht.getLanguage().getCode())).findFirst()
                    .orElseThrow(RuntimeException::new);

                ht.setDescription(htmd.getDescription());
                ht.setHabitItem(htmd.getHabitItem());
                ht.setName(htmd.getName());
            });
        habit.setImage(habitManagementDto.getImage());

        uploadImageForHabit(habitManagementDto, image, habit);

        habitRepo.save(habit);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void delete(Long id) {
        Habit habit = habitRepo.findById(id)
            .orElseThrow(() -> new WrongIdException(ErrorMessage.HABIT_NOT_FOUND_BY_ID));
        HabitVO habitVO = modelMapper.map(habit, HabitVO.class);

        habitTranslationRepo.deleteAllByHabit(habit);
        habitFactService.deleteAllByHabit(habitVO);
        adviceService.deleteAllByHabit(habitVO);
        habitAssignService.deleteAllHabitAssignsByHabit(habitVO);
        habitRepo.delete(habit);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteAll(List<Long> listId) {
        listId.forEach(this::delete);
    }
}