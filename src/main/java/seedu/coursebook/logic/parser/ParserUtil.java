package seedu.coursebook.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.coursebook.commons.core.index.Index;
import seedu.coursebook.commons.util.StringUtil;
import seedu.coursebook.logic.parser.exceptions.ParseException;
import seedu.coursebook.model.course.Course;
import seedu.coursebook.model.course.CourseColor;
import seedu.coursebook.model.person.Address;
import seedu.coursebook.model.person.Birthday;
import seedu.coursebook.model.person.Email;
import seedu.coursebook.model.person.Name;
import seedu.coursebook.model.person.Phone;
import seedu.coursebook.model.tag.Tag;


/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_NEGATIVE_INDEX = "Index cannot be a negative integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();

        // Check for negative integers first
        if (trimmedIndex.matches("-\\d+")) {
            throw new ParseException(MESSAGE_NEGATIVE_INDEX);
        }

        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        try {
            return Index.fromOneBased(Integer.parseInt(trimmedIndex));
        } catch (NumberFormatException nfe) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String} into a {@link Birthday} object after validating its format.
     * The input string is trimmed and checked against the expected date format.
     *
     * @param birthday The string representation of the birthday to parse.
     * @return A {@link Birthday} object representing the parsed date.
     * @throws ParseException If the input does not conform to the expected birthday format.
     */
    public static Birthday parseBirthday(String birthday) throws ParseException {
        requireNonNull(birthday);
        String trimmedBirthday = birthday.trim();
        if (!Birthday.isValidDate(trimmedBirthday)) {
            throw new ParseException(Birthday.MESSAGE_CONSTRAINTS);
        }
        return new Birthday(trimmedBirthday);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String course} into a {@code Course}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code course} is invalid.
     */
    public static Course parseCourse(String course) throws ParseException {
        requireNonNull(course);
        String trimmedCourse = course.trim();
        if (!Course.isValidCourseCode(trimmedCourse)) {
            throw new ParseException(Course.MESSAGE_CONSTRAINTS);
        }
        return new Course(trimmedCourse);
    }

    /**
     * Parses {@code Collection<String> courses} into a {@code Set<Course>}.
     */
    public static Set<Course> parseCourses(Collection<String> courses) throws ParseException {
        requireNonNull(courses);
        final Set<Course> courseSet = new HashSet<>();
        for (String token : courses) {
            if (token == null) {
                continue;
            }
            String trimmed = token.trim();
            if (trimmed.isEmpty()) {
                continue;
            }
            String[] parts = trimmed.split(",", 2);
            String code = parts[0].trim();
            if (!Course.isValidCourseCode(code)) {
                throw new ParseException(Course.MESSAGE_CONSTRAINTS);
            }
            CourseColor color = null;
            if (parts.length == 2) {
                String colorName = parts[1].trim();
                try {
                    color = CourseColor.fromName(colorName);
                } catch (IllegalArgumentException ex) {
                    throw new ParseException("Invalid course color: " + colorName);
                }
            }
            courseSet.add(new Course(code, color));
        }
        return courseSet;
    }

    /**
     * Parses and validates {@code String order} as either "asc" or "desc".
     */
    public static String parseOrder(String order) throws ParseException {
        requireNonNull(order);
        String trimmedOrder = order.trim();

        if (!trimmedOrder.equals("asc") && !trimmedOrder.equals("desc")) {
            throw new ParseException("Invalid sort order. Use only 'asc' or 'desc'.");
        }
        return trimmedOrder;
    }
}
