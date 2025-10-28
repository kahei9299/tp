package seedu.coursebook.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.coursebook.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.coursebook.logic.commands.CommandTestUtil.VALID_COURSE_CS2106;
import static seedu.coursebook.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.coursebook.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.coursebook.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.coursebook.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.coursebook.testutil.Assert.assertThrows;
import static seedu.coursebook.testutil.TypicalPersons.ALICE;
import static seedu.coursebook.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.coursebook.testutil.PersonBuilder;

public class PersonTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));
    }

    @Test
    public void isSamePersonAsNull() {

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));
    }

    @Test
    public void isSamePersonWithDiffAttributes() {

        // same name, all other attributes different -> returns true
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSamePerson(editedAlice));
    }

    @Test
    public void isSamePersonWithSamePhoneNumberButDiffName() {
        // different name, all other attributes same -> same phone number, email -> return true
        Person editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertTrue(ALICE.isSamePerson(editedAlice));
    }

    @Test
    public void isSamePersonWithSameNameCapitalised() {

        // name differs in case, all other attributes same -> returns true (names are auto-capitalized)
        Person editedBob = new PersonBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertTrue(BOB.isSamePerson(editedBob));
    }

    @Test
    public void isSamePersonWithTrailingSpace() {
        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        Person editedBob = new PersonBuilder(BOB).withName(nameWithTrailingSpaces)
                .withEmail("Bob@gmail.com").withPhone("98127831").build();
        assertFalse(BOB.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new PersonBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));

        // different courses -> returns false
        editedAlice = new PersonBuilder(ALICE).withCourses(VALID_COURSE_CS2106).build();
        assertFalse(ALICE.equals(editedAlice));

        // different favourite status -> returns false
        editedAlice = new PersonBuilder(ALICE).withFavourite(true).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = Person.class.getCanonicalName() + "{name=" + ALICE.getName() + ", phone=" + ALICE.getPhone()
                + ", email=" + ALICE.getEmail() + ", address=" + ALICE.getAddress() + ", tags=" + ALICE.getTags()
                + ", courses=" + ALICE.getCourses() + ", birthday=" + ALICE.getBirthday()
                + ", isFavourite=" + ALICE.isFavourite() + "}";
        assertEquals(expected, ALICE.toString());
    }
}
