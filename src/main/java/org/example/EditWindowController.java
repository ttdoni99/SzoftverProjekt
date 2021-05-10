package org.example;

import Database.DB;
import Database.Loader;
import Modells.Expense;
import Modells.Income;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateStringConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

public class EditWindowController {
    private static Logger logger = LoggerFactory.getLogger("EditWindowController.class");

    @FXML
    private TableView<Expense> expTableInfo;

    @FXML
    private TableView<Income> incTableInfo;
    @FXML
    private TableColumn<Income, Integer> colIncId;

    @FXML
    private TableColumn<Income, Integer> colIncAmount;

    @FXML
    private TableColumn<Expense, LocalDate> colExpDate;

    @FXML
    private TableColumn<Expense, Integer> colExpAmount;

    @FXML
    private TableColumn<Expense, Expense> colExpDelete;

    @FXML
    private TableColumn<Income, Income> colIncDelete;

    @FXML
    private TableColumn<Income, String> colIncName;

    @FXML
    private TableColumn<Expense, String> colExpName;

    @FXML
    private TableColumn<Income, LocalDate> colIncDate;

    @FXML
    private TableColumn<Expense, Integer> colExpId;

    @FXML
    Button listUpdaterButton;

    @FXML
    Label errorMessage;

    public void initialize(){
        updateLists();
    }

    private void initExpColumns(){
        colExpId.setCellValueFactory(new PropertyValueFactory<Expense,Integer>("primaryKey"));
        colExpName.setCellValueFactory(new PropertyValueFactory<Expense,String>("name"));
        colExpDate.setCellValueFactory(new PropertyValueFactory<Expense,LocalDate>("dayOfAdd"));
        colExpAmount.setCellValueFactory(new PropertyValueFactory<Expense,Integer>("amount"));
        colExpDelete.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));

        editableExpCols();
    }

    private void initIncColumns(){
        colIncId.setCellValueFactory(new PropertyValueFactory<Income,Integer>("primaryKey"));
        colIncName.setCellValueFactory(new PropertyValueFactory<Income,String>("name"));
        colIncDate.setCellValueFactory(new PropertyValueFactory<Income,LocalDate>("dayOfAdd"));
        colIncAmount.setCellValueFactory(new PropertyValueFactory<Income,Integer>("amount"));
        colIncDelete.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));

        editableIncCols();
    }

    private void editableExpCols(){
        colExpName.setCellFactory(TextFieldTableCell.forTableColumn());
        colExpName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Expense, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Expense, String> expStringCellEditEvent) {
                try {
                    Expense tmp = expStringCellEditEvent.getTableView().getItems().
                            get(expStringCellEditEvent.getTablePosition().getRow());
                    tmp.setName(expStringCellEditEvent.getNewValue());

                    DB.commitChange(tmp);
                    logger.trace("User changed the name field. New value: {}", tmp.getName());
                    errorMessage.setVisible(false);
                }catch (Exception e){
                    sendErrorMessage(e.getLocalizedMessage());
                    logger.error("Something went wrong: {}", e.getMessage());
                }
            }
        });

        colExpDate.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
        colExpDate.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Expense, LocalDate>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Expense, LocalDate> expLocalDateCellEditEvent) {
                try {
                    Expense tmp = expLocalDateCellEditEvent.getTableView().getItems().
                            get(expLocalDateCellEditEvent.getTablePosition().getRow());
                    tmp.setDayOfAdd(expLocalDateCellEditEvent.getNewValue());

                    DB.commitChange(tmp);
                    logger.trace("User changed the dayOfAdd field. New value: {}", tmp.getDayOfAdd());
                    errorMessage.setVisible(false);
                }catch (Exception e){
                    sendErrorMessage(e.getLocalizedMessage());
                    logger.error("Something went wrong: {}", e.getMessage());
                }
            }
        });

        colExpAmount.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colExpAmount.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Expense, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Expense, Integer> expIntegerCellEditEvent) {
                try {
                    Expense tmp = expIntegerCellEditEvent.getTableView().getItems().
                            get(expIntegerCellEditEvent.getTablePosition().getRow());
                    tmp.setAmount(expIntegerCellEditEvent.getNewValue());

                    DB.commitChange(tmp);
                    logger.trace("User changed the dayOfAdd field. New value: {}", tmp.getAmount());
                    errorMessage.setVisible(false);
                }catch (Exception e){
                    sendErrorMessage(e.getLocalizedMessage());
                    logger.error("Something went wrong: {}", e.getMessage());
                }
            }
        });

        colExpDelete.setCellFactory(param -> new TableCell<Expense,Expense>(){
            private final Button deleteButton = new Button("Töröl");

            @Override
            protected void updateItem(Expense expense, boolean empty){
                super.updateItem(expense,empty);

                if(expense == null){
                    setGraphic(null);
                    return;
                }
                setGraphic(deleteButton);
                deleteButton.setOnAction(actionEvent -> deleteFromExp(getTableView(),expense)
                );
            }
        });

        expTableInfo.setEditable(true);

    }

    private void editableIncCols(){
        colIncName.setCellFactory(TextFieldTableCell.forTableColumn());
        colIncName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Income, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Income, String> incStringCellEditEvent) {
                try {
                    Income tmp = incStringCellEditEvent.getTableView().getItems().
                            get(incStringCellEditEvent.getTablePosition().getRow());
                    tmp.setName(incStringCellEditEvent.getNewValue());

                    DB.commitChange(tmp);
                    logger.trace("User changed the name field. New value: {}", tmp.getName());
                    errorMessage.setVisible(false);
                }catch (Exception e){
                    sendErrorMessage(e.getLocalizedMessage());
                    logger.error("Something went wrong: {}", e.getMessage());
                }
            }
        });

        colIncDate.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
        colIncDate.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Income, LocalDate>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Income, LocalDate> incLocalDateCellEditEvent) {
                try {
                    Income tmp = incLocalDateCellEditEvent.getTableView().getItems().
                            get(incLocalDateCellEditEvent.getTablePosition().getRow());
                    tmp.setDayOfAdd(incLocalDateCellEditEvent.getNewValue());

                    DB.commitChange(tmp);

                    logger.trace("User changed the dayOfAdd field. New value: {}", tmp.getDayOfAdd());
                    errorMessage.setVisible(false);
                }catch (Exception e){
                    sendErrorMessage(e.getLocalizedMessage());
                    logger.error("Something went wrong: {}", e.getMessage());
                }

            }
        });

        colIncAmount.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colIncAmount.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Income, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Income, Integer> incStringCellEditEvent) {
                try {
                    Income tmp = incStringCellEditEvent.getTableView().getItems().
                            get(incStringCellEditEvent.getTablePosition().getRow());
                    tmp.setAmount(incStringCellEditEvent.getNewValue());

                    DB.commitChange(tmp);
                    logger.trace("User changed the amount field. new value: {}", tmp.getAmount());
                    errorMessage.setVisible(false);
                }catch (Exception e){
                    sendErrorMessage(e.getLocalizedMessage());
                    logger.error("Something went wrong: {}", e.getMessage());
                }
            }
        });

        colIncDelete.setCellFactory(param -> new TableCell<Income,Income>(){
            private final Button deleteButton = new Button("Töröl");

            @Override
            protected void updateItem(Income income, boolean empty){
                super.updateItem(income,empty);

                if(income == null){
                    setGraphic(null);
                    return;
                }
                setGraphic(deleteButton);
                deleteButton.setOnAction(actionEvent -> deleteFromInc(getTableView(),income)
                );
            }
        });

        incTableInfo.setEditable(true);
    }

    void updateExpList(){
        ObservableList<Expense> expTableData = FXCollections.observableArrayList(Loader.storage.getExpenses());

        try{
            expTableInfo.setItems(expTableData);
            logger.trace("New element was added to the Exp list {}", expTableInfo);

        }catch (Exception e){logger.error("Unknown error: {} ", e.getMessage());}
    }

    void updateIncList(){
        ObservableList<Income> incTableData = FXCollections.observableArrayList(Loader.storage.getIncomes());

        try{
            incTableInfo.setItems(incTableData);
            logger.trace("List was updated {}", expTableInfo);

        }catch (Exception e){logger.error("Unknown error: ", e);}
    }

    private void deleteFromExp(TableView tableView,Expense expense){
        try {
            tableView.getItems().remove(expense);
            DB.removeEntity(expense);
            Loader.storage.removeExpense(expense);
            logger.trace("successfully deleted {}", expense );
        }catch (Exception e){
            logger.error("Cannot delete element {}", e.getMessage());
        }
    }

    private void deleteFromInc(TableView tableView,Income income) {
        try {
            tableView.getItems().remove(income);
            DB.removeEntity(income);
            Loader.storage.removeIncome(income);
            logger.trace("successfully deleted {}", income);
        }catch (Exception e) {
            logger.error("Cannot delete element {}", income , e);
        }
    }

    @FXML
    public void updateLists(){
        try {
            updateExpList();
            initExpColumns();
            updateIncList();
            initIncColumns();
        }catch(Exception e){
            logger.error("Something went wrong during the update of the tables {}", e.getMessage());
        }
    }

    private void sendErrorMessage(String s){

        if(!errorMessage.isVisible()){
            errorMessage.setVisible(true);
        }
        errorMessage.setText(s);
    }
}
