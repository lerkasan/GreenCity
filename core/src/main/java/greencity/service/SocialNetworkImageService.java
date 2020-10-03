package greencity.service;

import greencity.dto.PageableDto;
import greencity.dto.econews.AddEcoNewsDtoResponse;
import greencity.dto.socialnetwork.SocialNetworkImageRequestDTO;
import greencity.dto.socialnetwork.SocialNetworkImageResponseDTO;
import greencity.dto.socialnetwork.SocialNetworkResponseDTO;
import greencity.entity.SocialNetworkImage;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * SocialNetworkImageService interface.
 *
 * @author Mykola Lehkyi
 */
public interface SocialNetworkImageService {
    /**
     * Method creates or returns existed {@link SocialNetworkImage} by given url.
     * @param url a well-formed url
     * @return {@link SocialNetworkImage}
     */
    SocialNetworkImage getSocialNetworkImageByUrl(String url);

    /**
     * Find {@link SocialNetworkImage} for management by page .
     *
     * @param pageable a value with pageable configuration.
     * @return a dto of {@link PageableDto}.
     * @author Orest Mamchuk
     */
    PageableDto<SocialNetworkImageResponseDTO> findAll(Pageable pageable);

    /**
     * Method for getting User by search query.
     *
     * @param paging {@link Pageable}.
     * @param query  query to search,
     * @return PageableDto of {@link SocialNetworkResponseDTO} instances.
     */
    PageableDto<SocialNetworkImageResponseDTO> searchBy(Pageable paging, String query);

    /**
     * Method for deleting the {@link SocialNetworkImage} instance by its id.
     *
     * @param id - {@link SocialNetworkImage} instance id which will be deleted.
     */
    void delete(Long id);

    /**
     * Method deletes all {@link SocialNetworkImage} by list of ids.
     *
     * @param listId list of id {@link SocialNetworkImage}
     */
    void deleteAll(List<Long> listId);

    /**
     * Method for creating {@link SocialNetworkImage} instance.
     *
     * @param socialNetworkImageRequestDTO - dto with {@link SocialNetworkImage} title, text, image path.
     * @return {@link AddEcoNewsDtoResponse} instance.
     */
    SocialNetworkImageResponseDTO save(SocialNetworkImageRequestDTO socialNetworkImageRequestDTO, MultipartFile image);

    /**
     * Method for getting the {@link SocialNetworkImage} instance by its id.
     *
     * @param id {@link SocialNetworkImage} instance id.
     * @return {@link SocialNetworkImage} instance.
     */
    SocialNetworkImage findById(Long id);

    /**
     * Method for getting the {@link SocialNetworkImageResponseDTO} instance by its id.
     *
     * @param id {@link SocialNetworkImageResponseDTO} instance id.
     * @return {@link SocialNetworkImageResponseDTO} instance.
     */
    SocialNetworkImageResponseDTO findDtoById(Long id);

    /**
     * Method for updating {@link SocialNetworkImage} instance.
     *
     * @param socialNetworkImageResponseDTO - instance of {@link SocialNetworkImageResponseDTO}.
     */
    void update(SocialNetworkImageResponseDTO socialNetworkImageResponseDTO, MultipartFile multipartFile);
}
