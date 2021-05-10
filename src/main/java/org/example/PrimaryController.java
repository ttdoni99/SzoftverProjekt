package org.example;

import Database.DB;
import Database.Loader;
import Modells.Expense;
import Modells.Income;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrimaryController {

    private static Logger logger = LoggerFactory.getLogger("PrimaryController.class");
    @FXML
    ChoiceBox expenseOrIncome;
    @FXML
    ComboBox nameField;
    @FXML
    DatePicker dateDatePicker;
    @FXML
    Spinner moneySpinner;
    @FXML
    Button addButton;
    @FXML
    Label expensesSumLabel;
    @FXML
    ListView listOfRecentlyAdded;
    @FXML
    Button editButton;
    @FXML
    Label balanceLabel;
    @FXML
    Button pieChartOpener;
    @FXML
    Label labelOfWarnMessage;
    @FXML
    Label incomesSumLabel;


    public void initialize() {
        Loader.loadExpenseTable();
        Loader.loadIncomeTable();
        update();
        setTheListOfTheComboBox();
    }

    @FXML
    public void addElementToList() {
        try {

            if (expenseOrIncome.getValue().toString().equals("Kiadás")) {
                createInstanceOfExpense();
            } else {
                createInstanceOfIncome();
            }
            update();
            labelOfWarnMessage.setVisible(false);

        } catch (NullPointerException e) {
            logger.error("Name field was empty {}", e.getMessage());
            warnMessage(e.getLocalizedMessage());
        } catch (VerifyError e) {
            logger.error("A field was empty.");
            warnMessage(e.getLocalizedMessage());
        } catch (Exception e) {
            logger.error("Something went wrong {}", e.getMessage());
            warnMessage(e.getLocalizedMessage());
        }
    }

    private void createInstanceOfExpense() {

        Expense tmp = new Expense(nameField.getValue().toString(),
                (Integer) moneySpinner.getValue(), dateDatePicker.getValue());
        Loader.storage.addExpense(tmp);
        addItemToComboBox(nameField.getValue().toString());
        DB.uploadEntityToDatabase(tmp);
        listOfRecentlyAdded.getItems().add(Loader.storage.getExpenses().
                get(Loader.storage.getExpenses().size() - 1).toString());
        logger.trace("User added a new Expense to the list");
    }


    private void createInstanceOfIncome() {

        Income tmp = new Income(nameField.getValue().toString(),
                (Integer) moneySpinner.getValue(), dateDatePicker.getValue());
        Loader.storage.addIncome(tmp);
        DB.uploadEntityToDatabase(tmp);
        addItemToComboBox(nameField.getValue().toString());
        listOfRecentlyAdded.getItems().add(Loader.storage.getIncomes().
                get(Loader.storage.getIncomes().size() - 1).toString());
        logger.trace("User added a new Income to the list");
    }

    private void update() {

        incomesSumLabel.setText(Loader.storage.getSumOfIncomes().toString() + " Ft");
        expensesSumLabel.setText(Loader.storage.getSumOfExpenses().toString() + " Ft");

        if (Loader.storage.getBalance() > 0)
            balanceLabel.setTextFill(Paint.valueOf("#12c548"));
        else
            balanceLabel.setTextFill(Paint.valueOf("#dc143c"));

        balanceLabel.setText(Loader.storage.getBalance().toString() + " Ft");

    }

    private void warnMessage(String s) {
        if (!labelOfWarnMessage.isVisible())
            labelOfWarnMessage.setVisible(true);

        labelOfWarnMessage.setText(s);
    }

    private void addItemToComboBox(String s) {

        if (!nameField.getItems().contains(s))
            nameField.getItems().add(s);
    }

    private void setTheListOfTheComboBox() {
        try {
            ObservableList<String> fieldList = FXCollections.
                    observableArrayList(Loader.storage.getDistinctNames());
            nameField.setItems(fieldList);
        } catch (Exception e) {
            logger.error("Something went wrong during list nameField list assignment {}", e.getMessage());
        }
    }

    @FXML
    public void openEditWindow() {
        logger.trace("Function called openEditWindow");
        try {
            Parent part = FXMLLoader.load(App.class.getResource("editwindow.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(part);
            stage.setScene(scene);
            stage.setTitle("Szerkesztés");
            stage.show();
            stage.setResizable(false);
            stage.setOnCloseRequest(windowEvent -> {
                update();
                setTheListOfTheComboBox();
            });
            logger.trace("User opened the EditWindow");
        } catch (Exception e) {
            logger.error("Error when trying to open new EditWindow: {}", e.getMessage());
        }
    }

    @FXML
    public void openPieChartWindow() {
        try {
            Parent part = FXMLLoader.load(App.class.getResource("piechart.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(part);
            stage.setScene(scene);
            stage.setTitle("Megoszlás");
            stage.setResizable(false);
            stage.sizeToScene();
            stage.show();
            stage.setOnCloseRequest(windowEvent -> {
                update();
                listOfRecentlyAdded.getItems().clear();
            });
            logger.trace("Function called  openPieChartWindow");
        } catch (Exception e) {
            logger.error("Error when trying to PieChartWindow: ", e);
        }
    }
}
