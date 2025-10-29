package seedu.coursebook.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.coursebook.logic.commands.BirthdayCommand;
import seedu.coursebook.logic.commands.ListByCourseCommand;
import seedu.coursebook.logic.commands.SortCommand;
import seedu.coursebook.logic.parser.Prefix;
import seedu.coursebook.model.person.Person;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_NO_CONTACT_FOUND = "No such contact found";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_SINGLE_COURSE_ONLY =
                "Only one course can be listed at a time.\n" + ListByCourseCommand.MESSAGE_USAGE;
    public static final String MESSAGE_SINGLE_BDAY_ONLY =
            "Only one birthday can be added to a person.\n" + BirthdayCommand.MESSAGE_USAGE;
    public static final String MESSAGE_SINGLE_ORDER_ONLY =
                "Sort by only one order at a time.\n" + SortCommand.MESSAGE_USAGE;
    public static final String MESSAGE_NAME_ALPHA_ONLY = "Names must contain only alphabets";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code person} for display to the user.
     */
    public static String format(Person person) {
        final StringBuilder builder = new StringBuilder();
        builder.append(person.getName())
                .append("; Phone: ")
                .append(person.getPhone())
                .append("; Email: ")
                .append(person.getEmail())
                .append("; Address: ")
                .append(person.getAddress())
                .append("; Courses: ");
        person.getCourses().forEach(builder::append);

        builder.append("; Tags: ");
        person.getTags().forEach(builder::append);
        return builder.toString();
    }

}
