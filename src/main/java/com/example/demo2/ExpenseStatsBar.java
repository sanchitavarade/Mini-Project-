package com.example.demo2;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ExpenseStatsBar implements Initializable {


    private static int i=0;
    private Stage stage;
    private Scene scene;
    private Parent root;

    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    @FXML
    private Label noTrans;

    @FXML
    private BarChart<String, Number> barChart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbUrl = "jdbc:mysql://localhost:3306/Exp_Tracker"; // Update with your database URL
        dbUser = "root"; // Update with your database username
        dbPassword = "oracle"; // Update with your database password
        noTrans.setText("");


        // Establish the initial database connection in the initialize method
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            barChart.getData().clear();
            addDataToChart();
            addDataToChart();
            // Use the connection to query the database
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addDataToChart() {

        try {

            Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String barChartQuery = "SELECT user_id,category_name, SUM(amount) AS total_expense " +
                    "FROM Transactions " +
                    "GROUP BY category_name, user_id " +
                    "having user_id = "+AlertConnector.user;

            ResultSet barChartResult = statement.executeQuery(barChartQuery);

            XYChart.Series<String, Number> barChartSeries = new XYChart.Series<>();
            barChart.getData().clear();

            if(!barChartResult.next()){
                noTrans.setText("No Transaction To show");
                return;
            }
            barChartResult.beforeFirst();

            while (barChartResult.next()) {
                String category = barChartResult.getString("category_name");
                int totalExpense = barChartResult.getInt("total_expense");
                barChartSeries.getData().add(new XYChart.Data<>(category, totalExpense));
            }
            barChart.getData().add(barChartSeries);


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public void switchToLoginPage(ActionEvent event) throws IOException {         // to switch the scene to dashboard
        root = FXMLLoader.load(getClass().getResource("finalLoginPage.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void switchToSign(ActionEvent event) throws IOException{         // to switch the scene to dashboard
        root = FXMLLoader.load(getClass().getResource("finalsignup.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void switchToDashBoard(ActionEvent event) throws IOException{         // to switch the scene to dashboard
        root = FXMLLoader.load(getClass().getResource("finalDashboard.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void switchToPie(ActionEvent event) throws Exception {
        root = FXMLLoader.load(getClass().getResource("finalPieChart.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void switchToBar(ActionEvent event) throws Exception {
        root = FXMLLoader.load(getClass().getResource("finalBarChart.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void switchToTransaction(ActionEvent event) throws IOException{        // to switch the scene to transaction
        root = FXMLLoader.load(getClass().getResource("finalTransaction.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void switchToAddTrans(ActionEvent event) throws IOException{        // to switch the scene to add transaction
        root = FXMLLoader.load(getClass().getResource("finalAddTransaction.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void switchToBL(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("finalBorrow&Lend.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void switchToBudget(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("finalBudget.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void switchToSave(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("finalSavings.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}