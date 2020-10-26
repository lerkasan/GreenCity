package greencity.constant;

public final class ErrorMessage {
    public static final String CUSTOM_GOAL_NOT_FOUND_BY_ID = "Custom goal with such id does not exist.";
    public static final String GOAL_NOT_FOUND_BY_ID = "Goal with such id does not exist ";
    public static final String GOAL_NOT_FOUND_BY_LANGUAGE_CODE = "There is no goal for such language.";
    public static final String GOAL_WRONG_ID = "Goal with this id not found : ";
    public static final String WRONG_PARAMETER = "You must choose one goal id  custom or simple. ";
    public static final String HABIT_STATISTIC_ALREADY_EXISTS = "Habit statistic already exists by such date";
    public static final String HABIT_ASSIGN_NOT_FOUND_BY_ID = "Habit assign does not exist by this id : ";
    public static final String HABIT_STATISTIC_NOT_FOUND_BY_ID = "The Habit statistic does not exist by this id : ";
    public static final String HABIT_NOT_FOUND_BY_ID = "The Habit does not exist by this id : ";
    public static final String WRONG_DATE = "Can't create habit statistic for such date";
    public static final String HABIT_TRANSLATION_NOT_FOUND = "HabitTranslation not found";
    public static final String END_TIME_LATE_THAN_START_TIME = "End time have to be late than start time";
    public static final String BREAK_TIME_NOT_FOUND_BY_ID = "The opening hours does not exist by this id: ";
    public static final String CLOSE_TIME_LATE_THAN_OPEN_TIME = "Close time have to be late than open time";
    public static final String WRONG_BREAK_TIME = "Working hours have to contain break with right time";
    public static final String OPEN_HOURS_NOT_FOUND_BY_ID = "The opening hours does not exist by this id: ";
    public static final String INVALID_LANGUAGE_CODE = "Given language code is not supported.";
    public static final String ADVICE_NOT_FOUND_BY_ID = "The name does not exist by this id: ";
    public static final String ADVICE_NOT_FOUND_BY_NAME = "The name does not exist by this name: ";
    public static final String ADVICE_NOT_UPDATED = "Advice not updated ";
    public static final String ADVICE_NOT_DELETED = "Advice not deleted ";

    public static final String CATEGORY_NOT_FOUND_BY_ID = "The category does not exist by this id: ";
    public static final String CATEGORY_NOT_FOUND_BY_NAME = "The category does not exist by this name: ";
    public static final String CATEGORY_ALREADY_EXISTS_BY_THIS_NAME = "Category by this name already exists.";
    public static final String CANNOT_ADD_PARENT_CATEGORY = "The category cannot be added to no-parent category";
    public static final String NOT_SAVE_DELETION = "This is can't be deleted";

    public static final String TAGS_NOT_FOUND = "Tips & Tricks should have at least one valid tag";
    public static final String DUPLICATED_TAG = "Tips & Tricks tags should be unique";
    public static final String INVALID_NUM_OF_TAGS =
        "Invalid tags. You must have less than " + ServiceValidationConstants.MAX_AMOUNT_OF_TAGS + " tags";
    public static final String FACT_OF_THE_DAY_NOT_FOUND = "The fact of the day not found: ";
    public static final String FACT_OF_THE_DAY_NOT_DELETED = "The fact of the day does not deleted by id: ";
    public static final String FACT_OF_THE_DAY_NOT_UPDATED = "The fact of the day does not updated by id: ";
    public static final String NEWS_SUBSCRIBER_EXIST = "Subscriber with this email address exists in the database.";
    public static final String NEWS_SUBSCRIBER_BY_EMAIL_NOT_FOUND =
        "Subscriber with this email address not found in the database.";
    public static final String INVALID_UNSUBSCRIBE_TOKEN = "Invalid unsubscribe token";
    public static final String LOCATION_NOT_FOUND_BY_ID = "The location does not exist by this id: ";
    public static final String NO_STATUS_FOR_SUCH_HABIT_ASSIGN = "There is no status for such habit for current user";
    public static final String USER_HAS_NO_STATUS_FOR_SUCH_HABIT =
        "There is no status for such habit for current user";
    public static final String HABIT_HAS_BEEN_ALREADY_ENROLLED = "You can enroll habit only once a day";
    public static final String HABIT_IS_NOT_ENROLLED = "Habit is not enrolled";
    public static final String HABIT_HAS_BEEN_ALREADY_IN_THAT_DAY = "Habit has been enrolled";
    public static final String STATUS_OF_HABIT_ASSIGN_NOT_DELETED = "Status of user habit wasn't deleted by id: ";
    public static final String STATUS_OF_HABIT_ASSIGN_NOT_UPDATED = "Status of user habit wasn't updated by id: ";
    public static final String HABIT_ASSIGN_NOT_UPDATED_BY_ID = "Habit assign does not exist by this id : ";
    public static final String HABIT_ASSIGN_NOT_FOUND_WITH_SUCH_USER_ID_AND_HABIT_ID_AND_DATE =
        "There is no habit assign for such habit, user and date";
    public static final String HABIT_ASSIGN_SUSPENDED_STATUS_NOT_UPDATED =
        "Habit assign suspended status wasn't updated because there is no habit assign with such user "
            + "and habit with id : ";
    public static final String HABIT_ASSIGN_NOT_FOUND_WITH_SUCH_USER_ID_AND_HABIT_ID =
        "There is no habit assign for such habit and user";
    public static final String USER_ALREADY_HAS_ASSIGNED_HABIT = "Current user already has assigned habit with id: ";
    public static final String USER_SUSPENDED_ASSIGNED_HABIT_FOR_CURRENT_DAY_ALREADY =
        "User already assigned and suspended this habit for today with id: ";
    public static final String HABIT_FACT_NOT_FOUND_BY_ID = "The habitfact does not exist by id: ";
    public static final String HABIT_FACT_NOT_UPDATED_BY_ID = "The habitfact does not updated by id: ";
    public static final String HABIT_FACT_NOT_DELETED_BY_ID = "The habitfact does not deleted by id: ";
    public static final String SPECIFICATION_VALUE_NOT_FOUND_BY_ID =
        "The specification value does not exist by this id: ";
    public static final String SPECIFICATION_NOT_FOUND_BY_NAME = "The specification does not exist by this name: ";
    public static final String LOCATION_IS_PRESENT = "Location is present.";
    public static final String PHOTO_IS_PRESENT = "Photo is present.";
    public static final String DISCOUNT_NOT_FOUND_BY_ID = "The discount does not exist by this id: ";
    public static final String FILE_NOT_SAVED = "File hasn't been saved";
    public static final String USER_NOT_FOUND_BY_ID = "The user does not exist by this id: ";
    public static final String USER_NOT_FOUND_BY_EMAIL = "The user does not exist by this email: ";
    public static final String USER_HAS_NO_GOALS = "This user hasn't selected any goals yet";
    public static final String USER_HAS_NO_AVAILABLE_GOALS = "This user is tracking all available goals";
    public static final String USER_GOAL_NOT_FOUND = "UserGoal(s) with this id not found: ";
    public static final String USER_HAS_NO_SUCH_GOAL = "This user has no goal with id: ";
    public static final String USER_CANT_UPDATE_HIMSELF = "User can't update yourself";
    public static final String IMPOSSIBLE_UPDATE_USER_STATUS = "Impossible to update status of admin or moderator";
    public static final String USER_HAS_NO_AVAILABLE_HABITS =
            "This user is tracking all available habits";
    public static final String PROFILE_PICTURE_NOT_FOUND_BY_ID = "Profile picture not found by id : ";
    public static final String IMAGE_EXISTS = "Image should be download, PNG or JPEG ";
    public static final String OWN_USER_ID = "You can not perform actions with your own id : ";
    public static final String USER_FRIENDS_LIST = "You don't have a friend with this id : ";
    public static final String FRIEND_EXISTS = "Friend with this id has already been added : ";
    public static final String NOT_FOUND_ANY_FRIENDS = "Not found any friends by id: ";
    public static final String CUSTOM_GOAL_WHERE_NOT_SAVED = "This CustomGoal(s) already exist(s): ";
    public static final String CUSTOM_GOAL_NOT_FOUND = "The user doesn't have any custom goal.";
    public static final String USER_HAS_NO_PERMISSION = "Current user has no permission for this action";
    public static final String ECO_NEWS_NOT_FOUND_BY_ID = "Eco news doesn't exist by this id: ";
    public static final String ECO_NEWS_NOT_FOUND = "Eco news haven't been found";
    public static final String ECO_NEWS_NOT_SAVED = "Eco news haven't been saved because of constraint violation";
    public static final String USER_CANNOT_ADD_MORE_THAN_5_SOCIAL_NETWORK_LINKS =
            "User cannot add more than 5 social network links";
    public static final String INVALID_URI = "The string could not be parsed as a URI reference.";
    public static final String MALFORMED_URL = "Malformed URL. The string could not be parsed.";
    public static final String USER_CANNOT_ADD_SAME_SOCIAL_NETWORK_LINKS =
            "User cannot add the same social network links";
    public static final String SOCIAL_NETWORK_IMAGE_NOT_SAVED = "Social network image hasn't been saved";
    public static final String SOCIAL_NETWORK_IMAGE_FOUND_BY_ID = "Social network image doesn't exist by this id: ";
    public static final String BAD_DEFAULT_SOCIAL_NETWORK_IMAGE_PATH =
        "Bad default social network image host path (Row in database doesn't exists)";
    public static final String PLACE_NOT_FOUND_BY_ID = "The place does not exist by this id: ";
    public static final String PLACE_STATUS_NOT_DIFFERENT = "Place with id: %d already has this status: %s";
    public static final String COMMENT_NOT_FOUND_EXCEPTION = "The comment with entered id doesn't exist";
    public static final String CANNOT_REPLY_THE_REPLY = "Can not make a reply to a reply";
    public static final String NOT_A_CURRENT_USER = "You can't perform actions with the data of other user";

    private ErrorMessage() {
    }
}