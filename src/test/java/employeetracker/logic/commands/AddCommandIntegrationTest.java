package employeetracker.logic.commands;

import static employeetracker.logic.commands.CommandTestUtil.assertCommandFailure;
import static employeetracker.logic.commands.CommandTestUtil.assertCommandSuccess;
import static employeetracker.testutil.TypicalPersons.getTypicalEmployeeTracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import employeetracker.model.Model;
import employeetracker.model.ModelManager;
import employeetracker.model.UserPrefs;
import employeetracker.model.employee.Employee;
import employeetracker.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalEmployeeTracker(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Employee validEmployee = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getEmployeeTracker(), new UserPrefs());
        expectedModel.addPerson(validEmployee);

        assertCommandSuccess(new AddCommand(validEmployee), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validEmployee), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Employee employeeInList = model.getEmployeeTracker().getPersonList().get(0);
        assertCommandFailure(new AddCommand(employeeInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
