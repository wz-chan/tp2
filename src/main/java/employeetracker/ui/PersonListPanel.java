package employeetracker.ui;

import java.util.logging.Logger;

import employeetracker.commons.core.LogsCenter;
import employeetracker.model.employee.Employee;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Employee> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Employee> employeeList) {
        super(FXML);
        personListView.setItems(employeeList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Employee} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Employee> {
        @Override
        protected void updateItem(Employee employee, boolean empty) {
            super.updateItem(employee, empty);

            if (empty || employee == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(employee, getIndex() + 1).getRoot());
            }
        }
    }

}
