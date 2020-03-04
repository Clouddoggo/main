package seedu.jelphabot.storage;

import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import seedu.jelphabot.commons.exceptions.IllegalValueException;
import seedu.jelphabot.model.task.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.jelphabot.storage.JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.jelphabot.testutil.Assert.assertThrows;
import static seedu.jelphabot.testutil.TypicalTasks.BOOK_REPORT;

// TODO add tests for priority fields
public class JsonAdaptedTaskTest {
    private static final String INVALID_DESCRIPTION = "∫3L L1M";
    private static final String INVALID_PRIORITY = "INVALID";
    private static final String INVALID_STATUS = "INVALID";
    private static final String INVALID_MODULE_CODE = "2103T";
    private static final String INVALID_TAG = "#rabbit!!";
    private static final String INVALID_DATETIME = "03-19/1999 00:00";

    private static final String VALID_DESCRIPTION = BOOK_REPORT.getDescription().toString();
    private static final String VALID_MODULE_CODE = BOOK_REPORT.getModuleCode().toString();
    private static final String VALID_DATETIME = BOOK_REPORT.getDateTime().toString();
    private static final Priority VALID_PRIORITY = BOOK_REPORT.getPriority();
    private static final Status VALID_STATUS = BOOK_REPORT.getStatus();
    private static final List<JsonAdaptedTag> VALID_TAGS = BOOK_REPORT.getTags().stream()
                                                               .map(JsonAdaptedTag::new)
                                                               .collect(Collectors.toList());

    @Test
    public void toModelType_validTaskDetails_returnsPerson() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(BOOK_REPORT);
        assertEquals(BOOK_REPORT, task.toModelType());
    }

    // @Test // name should never be invalid
    // public void toModelType_invalidDescription_throwsIllegalValueException() {
    //     JsonAdaptedTask person =
    //             new JsonAdaptedTask(INVALID_DESCRIPTION, VALID_STATUS, VALID_DATETIME, VALID_MODULE_CODE,
    //             VALID_PRIORITY, VALID_TAGS);
    //     String expectedMessage = Description.MESSAGE_CONSTRAINTS;
    //     assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    // }

    @Test //done
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedTask person = new JsonAdaptedTask(
                null,
                VALID_STATUS,
                VALID_DATETIME,
                VALID_MODULE_CODE,
                VALID_PRIORITY,
                VALID_TAGS
            );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test //done
    public void toModelType_invalidModuleCode_throwsIllegalValueException() {
        JsonAdaptedTask person =
            new JsonAdaptedTask(VALID_DESCRIPTION, VALID_STATUS, VALID_DATETIME, INVALID_MODULE_CODE, VALID_PRIORITY,
                VALID_TAGS
            );
        String expectedMessage = ModuleCode.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test //done
    public void toModelType_nullModuleCode_throwsIllegalValueException() {
        JsonAdaptedTask person = new JsonAdaptedTask(
                VALID_DESCRIPTION,
                VALID_STATUS,
                VALID_DATETIME,
                null,
                VALID_PRIORITY,
                VALID_TAGS
            );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ModuleCode.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidDateTime_throwsIllegalValueException() {
        JsonAdaptedTask person =
            new JsonAdaptedTask(VALID_DESCRIPTION, VALID_STATUS, INVALID_DATETIME, VALID_MODULE_CODE, VALID_PRIORITY,
                VALID_TAGS
            );
        String expectedMessage = DateTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullDateTime_throwsIllegalValueException() {
        JsonAdaptedTask person = new JsonAdaptedTask(
            VALID_DESCRIPTION,
            VALID_STATUS,
            null,
            INVALID_MODULE_CODE,
            VALID_PRIORITY,
            VALID_TAGS
        );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, DateTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedTask person =
            new JsonAdaptedTask(VALID_DESCRIPTION, VALID_STATUS, VALID_DATETIME, VALID_MODULE_CODE, VALID_PRIORITY,
                invalidTags
            );
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_nullTags_throwsIllegalValueException() {
        JsonAdaptedTask person =
            new JsonAdaptedTask(VALID_DESCRIPTION, VALID_STATUS, VALID_DATETIME, INVALID_MODULE_CODE, VALID_PRIORITY,
                null
            );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Tags.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }


}
