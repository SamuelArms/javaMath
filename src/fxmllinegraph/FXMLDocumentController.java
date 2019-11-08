/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmllinegraph;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

/**
 * 8
 *
 * @author samuel
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private ComboBox functionCombo;
    @FXML
    private ComboBox startingPointCombo;
    @FXML
    private ComboBox decimalCombo;
    @FXML
    private RadioButton userDefined;
    @FXML
    private RadioButton preDefined;
    @FXML
    private RadioButton rootView;
    @FXML
    private RadioButton funcView;
    @FXML
    private TextField userStartingPoint;
    @FXML
    private TextField userDecimal;
    @FXML
    private CheckBox newtonRapsion;
    @FXML
    private CheckBox secant;
    @FXML
    private CheckBox Bisection;
    @FXML
    private CheckBox falsePosition;
    @FXML
    private NumberAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private LineChart<Double, Double> chart;
    @FXML
    private TableView<TableLine> resultTable;

    public TableColumn<TableLine, Double> xold1Column = new TableColumn<>("xold1");
    public TableColumn<TableLine, Double> xold2Column = new TableColumn<>("xold2");
    public TableColumn<TableLine, Double> fxold1Column = new TableColumn<>("fxold1");
    public TableColumn<TableLine, Double> fxold2Column = new TableColumn<>("fxold2");
    public TableColumn<TableLine, Double> fdasholdColumn = new TableColumn<>("f'x");
    public TableColumn<TableLine, Double> xnewColumn = new TableColumn<>("xnew");
    public TableColumn<TableLine, Double> diffColumn = new TableColumn<>("diff");
    public TableColumn<TableLine, Double> stepColumn = new TableColumn<>("step");

    static public ObservableList<TableLine> secantTableLines = FXCollections.observableArrayList();
    static public ObservableList<TableLine> bisectionTableLines = FXCollections.observableArrayList();
    static public ObservableList<TableLine> newtonTableLines = FXCollections.observableArrayList();
    static public ObservableList<TableLine> falsePositionTableLines = FXCollections.observableArrayList();

    public Double userSubmittedStartingPoint;
    public Double userSubmittedDecimnal;
    public Double decimalPointSize;
    public Double startingPoint;
    public Double secondSecant;
    public Double secondBisection;
    public Double secondFalse;
    public String tempSecant;
    public String tempBisection;
    public String tempFalse;
    public double[] secantResults;
    public double[] bisectionResults;
    public LinkedList newtonResults;
    public LinkedList falsePositionResults;

    public boolean secantPast;
    public boolean bisectionPast;
    public boolean newtonPast;
    public boolean falsePositionPast;
    public boolean secantTable;
    public boolean bisectionTable;
    public boolean newtonTable;
    public boolean falsePositionTable;

    public Double xAxiscounter = 0.0;
    public XYChart.Series secantSeries = new XYChart.Series();
    public XYChart.Series bisectionSeries = new XYChart.Series();
    public XYChart.Series newtonSeries = new XYChart.Series();
    public XYChart.Series falsePositionSeries = new XYChart.Series();
    public XYChart.Series firstfuncSeries = new XYChart.Series();
    public XYChart.Series secondFunctSeries = new XYChart.Series();
    public XYChart.Series ThirdFuncSeries = new XYChart.Series();

    public Double x = 0.0;

    public String dec;
    public double decimal;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTable();
        funcGraph();
        preDefinedHandling();
    }

    public void setTable() {

        //xold1 Colum
        xold1Column.setMaxWidth(400);
        xold1Column.setMinWidth(200);
        xold1Column.setCellValueFactory(new PropertyValueFactory<>("xold1"));
        //xold2 Colum
        xold2Column.setMaxWidth(400);
        xold2Column.setMinWidth(200);
        xold2Column.setCellValueFactory(new PropertyValueFactory<>("xold2"));
        //fxold1 Colum
        fxold1Column.setMaxWidth(400);
        fxold1Column.setMinWidth(200);
        fxold1Column.setCellValueFactory(new PropertyValueFactory<>("fxold1"));
        //fxold2 Colum
        fxold2Column.setMaxWidth(400);
        fxold2Column.setMinWidth(200);
        fxold2Column.setCellValueFactory(new PropertyValueFactory<>("fxold2"));
        //fdashold Colum
        fdasholdColumn.setMaxWidth(400);
        fdasholdColumn.setMinWidth(200);
        fdasholdColumn.setCellValueFactory(new PropertyValueFactory<>("fdashold"));
        //xnew Colum
        xnewColumn.setMaxWidth(400);
        xnewColumn.setMinWidth(200);
        xnewColumn.setCellValueFactory(new PropertyValueFactory<>("xnew"));
        //diff Colum
        diffColumn.setMaxWidth(400);
        diffColumn.setMinWidth(200);
        diffColumn.setCellValueFactory(new PropertyValueFactory<>("diff"));
        //step Colum
        stepColumn.setMaxWidth(80);
        stepColumn.setMinWidth(80);
        stepColumn.setCellValueFactory(new PropertyValueFactory<>("step"));

        resultTable.getColumns().addAll(stepColumn, xold1Column, xold2Column, fxold1Column, fxold2Column, fdasholdColumn, xnewColumn, diffColumn);

    }

    public void updateTable() {

        resultTable.getColumns().clear();
        resultTable.getItems().clear();

        secantTableLines = SecantMethod.getSecantLines();
        newtonTableLines = NewtonRaphson.getNewtonLines();
        bisectionTableLines = BisectionMethod.getBisectionLines();
        falsePositionTableLines = FalsePosition.getFalsePositionLines();

        if (secantTable) {

            resultTable.getItems().addAll(secantTableLines);

        }

        if (newtonTable) {
            resultTable.getItems().addAll(newtonTableLines);
        }

        if (bisectionTable) {
            resultTable.getItems().addAll(bisectionTableLines);
        }

        if (falsePositionTable) {
            resultTable.getItems().addAll(falsePositionTableLines);
        }

        resultTable.getColumns().addAll(stepColumn, xold1Column, xold2Column, fxold1Column, fxold2Column, fdasholdColumn, xnewColumn, diffColumn);

    }

    public void funcGraph() {
        chart.getData().clear();
        yAxis.setLabel("f(x)");
        xAxis.setLabel("x");
        chart.setTitle("Function plotted");
        firstfuncSeries.setName("First Function");
        secondFunctSeries.setName("Second Function");
        ThirdFuncSeries.setName("Third Function");
        updateFuncView();
    }

    public void rootGraph() {
        chart.getData().clear();
        yAxis.setAutoRanging(true);
        xAxis.setLabel("Iteration");
        yAxis.setLabel("Root Approximation");
        chart.setTitle("Root Graph");
        secantSeries.setName("Secant Series");
        bisectionSeries.setName("Bisection Series");
        newtonSeries.setName("Newton-Raphson Series");
        falsePositionSeries.setName("False Position Series");
        updateRootGraph();
    }

    public void updateRootGraph() {
        if (secantPast) {
            secantGraphHandling();
            secantPast = false;
        }
        if (bisectionPast) {
            bisectionGraphHandling();
            bisectionPast = false;
        }
        if (newtonPast) {
            netwonGraphHandling();
            newtonPast = false;
        }
        if (falsePositionPast) {
            falsePositionGraphHandling();
            falsePositionPast = false;
        }

    }

    public void secantGraphHandling() {
        secantSeries.getData().clear();
        xAxiscounter = 0.0;
        chart.getData().add(secantSeries);
        for (int i = 0; i < secantResults.length; i++) {
            if (secantResults[i] != 0) {
                secantSeries.getData().add(new XYChart.Data<>(xAxiscounter, secantResults[i]));
                xAxiscounter++;
            }
        }
    }

    public void bisectionGraphHandling() {
        bisectionSeries.getData().clear();
        yAxis.setAutoRanging(true);
        xAxiscounter = 0.0;
        chart.getData().add(bisectionSeries);
        for (int i = 0; i < bisectionResults.length; i++) {
            if (bisectionResults[i] != 0) {
                bisectionSeries.getData().add(new XYChart.Data<>(xAxiscounter, bisectionResults[i]));
                xAxiscounter++;
            }
        }
    }

    public void netwonGraphHandling() {
        yAxis.setAutoRanging(true);
        xAxiscounter = 0.0;
        chart.getData().add(newtonSeries);
        while (!newtonResults.isEmpty()) {
            newtonSeries.getData().add(new XYChart.Data<>(xAxiscounter, newtonResults.getFirstElement()));
            newtonResults.removeFirst();
            xAxiscounter++;
        }
    }

    public void falsePositionGraphHandling() {
        yAxis.setAutoRanging(true);
        xAxiscounter = 0.0;
        chart.getData().add(falsePositionSeries);
        while (!falsePositionResults.isEmpty()) {
            falsePositionSeries.getData().add(new XYChart.Data<>(xAxiscounter, falsePositionResults.getFirstElement()));
            falsePositionResults.removeFirst();
            xAxiscounter++;
        }
    }

    public void updateFuncView() {
        chart.getData().clear();
        if (functionCombo.getSelectionModel().getSelectedIndex() == 0) {
            chart.getData().clear();
            yAxis.setAutoRanging(false);
            yAxis.setUpperBound(10);
            yAxis.setLowerBound(-125);
            yAxis.setTickUnit(5);
            for (int i = -10; i != 11; i++) {
                firstfuncSeries.getData().add(new XYChart.Data<>(i, i - i * i));
            }
            chart.getData().add(firstfuncSeries);
        }

        if (functionCombo.getSelectionModel().getSelectedIndex() == 1) {
            chart.getData().clear();
            yAxis.setAutoRanging(false);
            yAxis.setUpperBound(6);
            yAxis.setLowerBound(-2);
            yAxis.setTickUnit(0.1);
            for (int i = -5; i != 21; i++) {
                secondFunctSeries.getData().add(new XYChart.Data<>(i, Math.log(i + 1) + 1));
            }
            chart.getData().add(secondFunctSeries);
        }

        if (functionCombo.getSelectionModel().getSelectedIndex() == 2) {
            chart.getData().clear();
            yAxis.setAutoRanging(false);
            yAxis.setUpperBound(25000);
            yAxis.setLowerBound(-2500);
            yAxis.setTickUnit(1000);
            for (int i = -10; i != 11; i++) {
                ThirdFuncSeries.getData().add(new XYChart.Data<>(i, Math.exp(i) - 3 * i));
            }
            chart.getData().add(ThirdFuncSeries);
        }
    }

    public void submitHandling() {

        resultTable.getColumns().clear();
        resultTable.getItems().clear();

        secantPast = false;
        newtonPast = false;
        falsePositionPast = false;
        bisectionPast = false;

        chart.getData().clear();
        secantSeries.getData().clear();
        newtonSeries.getData().clear();
        falsePositionSeries.getData().clear();
        bisectionSeries.getData().clear();

        if (preDefined.isSelected()) {
            if (newtonRapsion.isSelected()) {
                newtonRapsionHandling();
            }
            if (secant.isSelected()) {
                secantHandling();
            }
            if (Bisection.isSelected()) {
                BisectionHandling();
            }
            if (falsePosition.isSelected()) {
                falsePositionHandling();
            }

        }

        //Testing the users input
        if (userDefined.isSelected()) {

            if (testUserStarting()) {
                System.out.println("user entered correct type");
            } else {
                AlertBox.display("Wrong Data Type", "Please enter an Interger or a double only");
            }

            if (testUserDecimal()) {
                System.out.println("user entered correct decimal");
            } else {
                AlertBox.display("Wrong Data Type", "Please enter a decimal like this 0.0001");
            }

            if (testUserDecimal() & testUserStarting()) {
                if (newtonRapsion.isSelected()) {
                    newtonRapsionHandling();
                }
                if (secant.isSelected()) {
                    secantHandling();
                }
                if (Bisection.isSelected()) {
                    BisectionHandling();
                }
                if (falsePosition.isSelected()) {
                    falsePositionHandling();
                }
            }
        }
        resultTable.getItems().clear();
        updateTable();
    }

    public void loadStartingPoints() {
        yAxis.setAutoRanging(true);
        //System.out.println("loadStartingPoints");
        if (functionCombo.getSelectionModel().getSelectedItem().equals("x-x^2")) {
            startingPointCombo.getItems().clear();
            startingPointCombo.getItems().addAll(
                    "x= -1",
                    "x= 0.5",
                    "x= 0.50001",
                    "x= 0.49999"
            );
        }

        if (functionCombo.getSelectionModel().getSelectedItem().equals("ln(x+1)+1")) {
            startingPointCombo.getItems().clear();
            startingPointCombo.getItems().addAll(
                    "x= 0",
                    "x= -1",
                    "x= -1.0e-10"
            );
        }

        if (functionCombo.getSelectionModel().getSelectedItem().equals("e^x-3x")) {
            startingPointCombo.getItems().clear();
            startingPointCombo.getItems().addAll(
                    "x= 1.09",
                    "x= 1.1",
                    "x= 1.11"
            );
        }
        if (funcView.isSelected()) {
            updateFuncView();
        }
    }

    public void newtonRapsionHandling() {

        if (functionCombo.getSelectionModel().getSelectedItem().equals("x-x^2")) {
            if (userDefined.isSelected()) {
                startingPoint = Double.parseDouble(userStartingPoint.getText());
                decimal = Double.parseDouble(userDecimal.getText());
            } else {
                startingPoint = getFirstFunctionStart();
                decimal = getDecimalPlaces();
            }
            newtonResults = NewtonRaphson.NewtonRaphsonFunctionOne(startingPoint, decimal);
        }

        if (functionCombo.getSelectionModel().getSelectedItem().equals("ln(x+1)+1")) {
            if (userDefined.isSelected()) {
                startingPoint = Double.parseDouble(userStartingPoint.getText());
                decimal = Double.parseDouble(userDecimal.getText());
            } else {
                startingPoint = getSecondFunctionStart();
                decimal = getDecimalPlaces();
            }
            newtonResults = NewtonRaphson.NewtonRaphsonFunctionTwo(startingPoint, decimal);
        }

        if (functionCombo.getSelectionModel().getSelectedItem().equals("e^x-3x")) {
            if (userDefined.isSelected()) {
                startingPoint = Double.parseDouble(userStartingPoint.getText());
                decimal = Double.parseDouble(userDecimal.getText());
            } else {
                startingPoint = getThirdFunctionStart();
                decimal = getDecimalPlaces();
            }
            newtonResults = NewtonRaphson.NewtonRaphsonFunctionThree(startingPoint, decimal);
        }
        if (!newtonResults.isEmpty()) {
            newtonPast = true;
            newtonTable = true;
            if (rootView.isSelected()) {
                updateRootGraph();
            }
        }
    }

    public void secantHandling() {
        tempSecant = secondChoice("Secant");
        secondSecant = Double.parseDouble(tempSecant);

        if (functionCombo.getSelectionModel().getSelectedItem().equals("x-x^2")) {
            if (userDefined.isSelected()) {
                startingPoint = Double.parseDouble(userStartingPoint.getText());
                decimal = Double.parseDouble(userDecimal.getText());
            } else {
                startingPoint = getFirstFunctionStart();
                decimal = getDecimalPlaces();
            }
            secantResults = SecantMethod.SecantFunctionOne(startingPoint, secondSecant, decimal);

        }

        if (functionCombo.getSelectionModel().getSelectedItem().equals("ln(x+1)+1")) {
            if (userDefined.isSelected()) {
                startingPoint = Double.parseDouble(userStartingPoint.getText());
                decimal = Double.parseDouble(userDecimal.getText());
            } else {
                startingPoint = getSecondFunctionStart();
                decimal = getDecimalPlaces();
            }
            secantResults = SecantMethod.SecantFunctionTwo(startingPoint, secondSecant, decimal);

        }

        if (functionCombo.getSelectionModel().getSelectedItem().equals("e^x-3x")) {
            if (userDefined.isSelected()) {
                startingPoint = Double.parseDouble(userStartingPoint.getText());
                decimal = Double.parseDouble(userDecimal.getText());
            } else {
                startingPoint = getThirdFunctionStart();
                decimal = getDecimalPlaces();
            }
            secantResults = SecantMethod.SecantFunctionThree(startingPoint, secondSecant, decimal);
        }

        secantPast = true;
        secantTable = true;
        if (rootView.isSelected()) {
            updateRootGraph();
        }
    }

    public void BisectionHandling() {
        tempBisection = secondChoice("Bisection");
        secondBisection = Double.parseDouble(tempBisection);

        if (functionCombo.getSelectionModel().getSelectedItem().equals("x-x^2")) {
            if (userDefined.isSelected()) {
                startingPoint = Double.parseDouble(userStartingPoint.getText());
                decimal = Double.parseDouble(userDecimal.getText());
            } else {
                startingPoint = getFirstFunctionStart();
                decimal = getDecimalPlaces();
            }
            bisectionResults = BisectionMethod.bisectFunctionOne(startingPoint, secondBisection, decimal);
        }

        if (functionCombo.getSelectionModel().getSelectedItem().equals("ln(x+1)+1")) {
            if (userDefined.isSelected()) {
                startingPoint = Double.parseDouble(userStartingPoint.getText());
                decimal = Double.parseDouble(userDecimal.getText());
            } else {
                startingPoint = getSecondFunctionStart();
                decimal = getDecimalPlaces();
            }
            bisectionResults = BisectionMethod.bisectFunctionTwo(startingPoint, secondBisection, decimal);
        }

        if (functionCombo.getSelectionModel().getSelectedItem().equals("e^x-3x")) {
            if (userDefined.isSelected()) {
                startingPoint = Double.parseDouble(userStartingPoint.getText());
                decimal = Double.parseDouble(userDecimal.getText());
            } else {
                startingPoint = getThirdFunctionStart();
                decimal = getDecimalPlaces();
            }
            bisectionResults = BisectionMethod.bisectFunctionThree(startingPoint, secondBisection, decimal);
        }

        bisectionPast = true;
        bisectionTable = true;
        if (rootView.isSelected()) {
            updateRootGraph();
        }
    }

    public void falsePositionHandling() {
        tempFalse = secondChoice("False Position");
        secondFalse = Double.parseDouble(tempFalse);

        if (functionCombo.getSelectionModel().getSelectedItem().equals("x-x^2")) {
            if (userDefined.isSelected()) {
                startingPoint = Double.parseDouble(userStartingPoint.getText());
                decimal = Double.parseDouble(userDecimal.getText());
            } else {
                startingPoint = getFirstFunctionStart();
                decimal = getDecimalPlaces();
            }
            falsePositionResults = FalsePosition.falsePositionFunctionOne(startingPoint, secondFalse, decimal);
        }

        if (functionCombo.getSelectionModel().getSelectedItem().equals("ln(x+1)+1")) {
            if (userDefined.isSelected()) {
                startingPoint = Double.parseDouble(userStartingPoint.getText());
                decimal = Double.parseDouble(userDecimal.getText());
            } else {
                startingPoint = getSecondFunctionStart();
                decimal = getDecimalPlaces();
            }
            falsePositionResults = FalsePosition.falsePositionFunctionTwo(startingPoint, secondBisection, decimal);
        }
        if (functionCombo.getSelectionModel().getSelectedItem().equals("e^x-3x")) {
            if (userDefined.isSelected()) {
                startingPoint = Double.parseDouble(userStartingPoint.getText());
                decimal = Double.parseDouble(userDecimal.getText());
            } else {
                startingPoint = getThirdFunctionStart();
                decimal = getDecimalPlaces();
            }
            falsePositionResults = FalsePosition.falsePositionFunctionThree(startingPoint, secondBisection, decimal);
        }
        falsePositionPast = true;
        falsePositionTable = true;
        if (rootView.isSelected()) {
            updateRootGraph();
        }
    }

    public void userDefinedHandling() {
        startingPointCombo.getSelectionModel().clearSelection();
        decimalCombo.getSelectionModel().clearSelection();
        userDecimal.setEditable(true);
        userStartingPoint.setEditable(true);
        userDecimal.setDisable(false);
        userStartingPoint.setDisable(false);
        startingPointCombo.setDisable(true);
        decimalCombo.setDisable(true);

    }

    public void preDefinedHandling() {
        userDecimal.clear();
        userStartingPoint.clear();
        userDecimal.setEditable(false);
        userStartingPoint.setEditable(false);
        userDecimal.setDisable(true);
        userStartingPoint.setDisable(true);
        startingPointCombo.setDisable(false);
        decimalCombo.setDisable(false);

    }

    public String secondChoice(String titleMethod) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(titleMethod + " Starting point");
        dialog.setHeaderText("Please enter second starting point");
        dialog.setContentText("Second starting point: ");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String secondValue = result.get();
            return secondValue;
        } else {
            return "";
        }
    }

    public double getDecimalPlaces() {
        if (decimalCombo.getSelectionModel().getSelectedItem().equals("0.1")) {
            return 0.1;
        }
        if (decimalCombo.getSelectionModel().getSelectedItem().equals("0.01")) {
            return 0.01;
        }
        if (decimalCombo.getSelectionModel().getSelectedItem().equals("0.001")) {
            return 0.001;
        }
        if (decimalCombo.getSelectionModel().getSelectedItem().equals("0.0001")) {
            return 0.0001;
        }
        if (decimalCombo.getSelectionModel().getSelectedItem().equals("0.00001")) {
            return 0.00001;
        }
        return 0;
    }

    public double getFirstFunctionStart() {
        if (startingPointCombo.getSelectionModel().getSelectedItem().equals("x= -1")) {
            startingPoint = -1.0;
        }
        if (startingPointCombo.getSelectionModel().getSelectedItem().equals("x= 0.5")) {
            startingPoint = 0.5;
        }
        if (startingPointCombo.getSelectionModel().getSelectedItem().equals("x= 0.50001")) {
            startingPoint = 0.50001;
        }
        if (startingPointCombo.getSelectionModel().getSelectedItem().equals("x= 0.49999")) {
            startingPoint = 0.49999;
        }
        return startingPoint;
    }

    public double getSecondFunctionStart() {
        if (functionCombo.getSelectionModel().getSelectedItem().equals("ln(x+1)+1")) {
            if (startingPointCombo.getSelectionModel().getSelectedItem().equals("x= 0")) {
                startingPoint = 0.0;
            }
            if (startingPointCombo.getSelectionModel().getSelectedItem().equals("x= -1")) {
                startingPoint = -1.0;
            }
            if (startingPointCombo.getSelectionModel().getSelectedItem().equals("x= -1.0e-10")) {
                startingPoint = -1.0e-10;
            }
        }
        return startingPoint;
    }

    public double getThirdFunctionStart() {
        if (startingPointCombo.getSelectionModel().getSelectedItem().equals("x= 1.09")) {
            startingPoint = 1.09;
        }
        if (startingPointCombo.getSelectionModel().getSelectedItem().equals("x= 1.1")) {
            startingPoint = 1.1;
        }
        if (startingPointCombo.getSelectionModel().getSelectedItem().equals("x= 1.11")) {
            startingPoint = 1.11;
        }
        return startingPoint;

    }

    public boolean testUserStarting() {
        try {
            userSubmittedStartingPoint.parseDouble(userStartingPoint.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean testUserDecimal() {
        try {
            userSubmittedDecimnal.parseDouble(userDecimal.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
