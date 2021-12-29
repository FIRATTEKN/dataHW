package org.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PrimaryController implements Initializable {

    public VBox dataSets;
    public HBox forecasts1;
    public HBox forecats2;
    public TextField newDatasetField;
    public Structures.LinkedList databases = new Structures.LinkedList();
    public Label datasetNameField;
    public TextField numberofValueOfMonthField;
    public HBox demant1;
    public HBox demant2;
    public VBox leftbot;
    public VBox rightbot;

    public static double[] returnBestMethod(Dataset dataset){
        double[] dd = new double[24];
        for (int i = 0 ;i<dataset.size();i++){
            Dataset.Data temp = dataset.head;
            for (int j = 0 ;i>j ;j++){
                temp=temp.next;
            }
            dd[i]=temp.get_data();
        }



        falgo falgo = new falgo(dd);
        double[] exS = falgo.exponentialSmoothing(dd);
        double[] dexS = falgo.doubleExponentialSmoothing(dd);
        double[] regregS = falgo.regressionAnalysis(dd);
        double[] desea = falgo.deseasonalizedRegressionAnalysis(dd);

        int result = falgo.MSE(exS,dexS,desea,regregS);
        double[] bestMethod;

        if(result==0)
            bestMethod=exS;
        else if (result==1)
            bestMethod=dexS;
        else if (result==2)
            bestMethod=regregS;
        else
            bestMethod=desea;

        return bestMethod;

    }

    private void addDataset(String name){
        Dataset newDataset = new Dataset(name);
        databases.insert(newDataset);
        System.out.println(databases.size());

        refreshDatasetNames();
    }

    private void refreshDatasetNames(){
        dataSets.getChildren().clear();

        int datasetNumber = databases.size();
        for (int i = 0;i<datasetNumber;i++){
            Structures.LinkedList.Node temp = databases.head;
            for (int j = 0 ;i>j ;j++){
                temp=temp.next;
            }
            Button newBtn = new Button(temp.dataset.name);
            newBtn.setPrefWidth(175);

            newBtn.setOnMouseClicked(event -> {
                String nameOfDataset = newBtn.getText();
                Dataset currentDataset = databases.find_by_name(databases,nameOfDataset);

                datasetNameField.setText(currentDataset.name);
                btnAction();
            });


            dataSets.getChildren().add(newBtn);
        }
    }

    private void btnAction(){
        demant1.getChildren().clear();
        demant2.getChildren().clear();
        if (databases.find_by_name(databases,datasetNameField.getText()).size()!=0){

            for(int i = 0 ;i<12;i++){
                Dataset.Data temp2 = databases.find_by_name(databases,datasetNameField.getText()).head;
                for (int j = 0 ;i>j ;j++){
                    temp2=temp2.next;
                }
                VBox vBox = new VBox();
                TextField tf = new TextField(String.valueOf(temp2.get_data()));
                tf.setPrefWidth(71);
                vBox.getChildren().add(tf);
                demant1.getChildren().add(vBox);
            }
            for(int i = 12 ;i<24;i++){
                Dataset.Data temp2 = databases.find_by_name(databases,datasetNameField.getText()).head;
                for (int j = 0 ;i>j ;j++){
                    temp2=temp2.next;
                }
                VBox vBox = new VBox();
                TextField tf = new TextField(String.valueOf(temp2.get_data()));
                tf.setPrefWidth(71);
                vBox.getChildren().add(tf);
                demant2.getChildren().add(vBox);
            }
        }
    }

    void putDatasets(){
        Structures.LinkedList databases = new Structures.LinkedList();
        databases.size();

    }

    @FXML
    private void calculatebtn(){
        forecasts1.getChildren().clear();
        forecats2.getChildren().clear();

        double[] forecasstingValues = returnBestMethod(databases.find_by_name(databases,datasetNameField.getText()));
        for (int i = 0 ;i<12;i++){
            String valueS = String.valueOf(forecasstingValues[i]);
            if (valueS.length()>4)
                valueS = valueS.substring(0,5);
            Label newLabel = new Label(valueS);
            newLabel.setPrefWidth(71);
            newLabel.setPrefHeight(39);
            newLabel.setAlignment(Pos.CENTER);
            forecasts1.getChildren().add(newLabel);
        }
        for (int i = 12 ;i<24;i++){
            String valueS = String.valueOf(forecasstingValues[i]);
            if (valueS.length()>4)
                valueS = valueS.substring(0,5);
            Label newLabel = new Label(valueS);
            newLabel.setPrefWidth(71);
            newLabel.setPrefHeight(39);
            newLabel.setAlignment(Pos.CENTER);
            forecats2.getChildren().add(newLabel);
        }


        double[] dd = new double[24];
        for (int i = 0 ;i<databases.find_by_name(databases,datasetNameField.getText()).size();i++){
            Dataset.Data temp = databases.find_by_name(databases,datasetNameField.getText()).head;
            for (int j = 0 ;i>j ;j++){
                temp=temp.next;
            }
            dd[i]=temp.get_data();
        }



        falgo falgo = new falgo(dd);
        double[] exS = falgo.exponentialSmoothing(dd);
        for (double i:exS)
            System.out.println(i);
        double[] dexS = falgo.doubleExponentialSmoothing(dd);
        for (double i:dexS)
            System.out.println(i);
        double[] regregS = falgo.regressionAnalysis(dd);
        for (double i:regregS)
            System.out.println(i);
        double[] desea = falgo.deseasonalizedRegressionAnalysis(dd);
        for (double i:desea)
            System.out.println(i);

        double[] maxs = falgo.max_forecasts(exS,dexS,regregS,desea);
        for (double i:maxs)
            System.out.println(i);
        double[] mins = falgo.min_forecasts(exS,dexS,regregS,desea);
        for (double i:mins)
            System.out.println(i);

        for (int i = 0 ; i<maxs.length;i++){
            String value = String.valueOf(maxs[i]);
            if (value.length()>4)
                value=value.substring(0,5);
            Label lb  = new Label(value);
            lb.setPrefHeight(39);
            leftbot.getChildren().add(lb);
        }
        for (int i = 0 ; i<mins.length;i++){
            String value = String.valueOf(mins[i]);
            if (value.length()>4)
                value=value.substring(0,5);
            Label lb  = new Label(value);
            lb.setPrefHeight(39);
            rightbot.getChildren().add(lb);
        }
    }
    @FXML
    private void newDatasetBtn(){
        addDataset(newDatasetField.getText());
        newDatasetField.setText("");
    }
    @FXML
    private void daletDatasetBtn(){
        databases.delete(databases.find_by_name(databases,datasetNameField.getText()));
        refreshDatasetNames();
    }
    @FXML
    private void setforMounthsBtn(){
        demant1.getChildren().clear();
        demant2.getChildren().clear();
        int numberOfValueOfMonth = Integer.parseInt(numberofValueOfMonthField.getText())%10;
        for(int i = 0 ;i<12;i++){
            VBox vBox = new VBox();
            vBox.setPrefWidth(71);
            demant1.getChildren().add(vBox);
            for (int j = 0 ;j<numberOfValueOfMonth;j++){
                TextField tf = new TextField();
                tf.setPrefWidth(71);
                vBox.getChildren().add(tf);
            }
        }

        for(int i = 0 ;i<12;i++){
            VBox vBox = new VBox();
            vBox.setPrefWidth(71);
            demant2.getChildren().add(vBox);
            for (int j = 0 ;j<numberOfValueOfMonth;j++){
                TextField tf = new TextField();
                tf.setPrefWidth(71);
                vBox.getChildren().add(tf);
            }
        }

    }
    @FXML
    private void saveDemants(){
        Dataset currentDataset = databases.find_by_name(databases,datasetNameField.getText());
        currentDataset.destroy_dataset();
        int numberOfValoueOfMonth =((VBox) demant1.getChildren().get(0)).getChildren().size();

        for (int i = 0 ;i<12;i++){
            if (numberOfValoueOfMonth>1){
                int avg = 0;
                for (int j = 0 ;j<numberOfValoueOfMonth;j++){
                    avg+=Integer.parseInt(((TextField) ((VBox) demant1.getChildren().get(i)).getChildren().get(j)).getText());
                }
                currentDataset.insert(avg/numberOfValoueOfMonth);

            }else {
                currentDataset.insert(Integer.parseInt(((TextField) ((VBox) demant1.getChildren().get(i)).getChildren().get(0)).getText()));
            }

        }

        for (int i = 0 ;i<12;i++){
            if (numberOfValoueOfMonth>1){
                int avg = 0;
                for (int j = 0 ;j<numberOfValoueOfMonth;j++){
                    avg+=Integer.parseInt(((TextField) ((VBox) demant2.getChildren().get(i)).getChildren().get(j)).getText());
                }
                currentDataset.insert(avg/numberOfValoueOfMonth);

            }else {
                currentDataset.insert(Integer.parseInt(((TextField) ((VBox) demant2.getChildren().get(i)).getChildren().get(0)).getText()));
            }

        }



    }

    public void initialize(URL url, ResourceBundle resourceBundle) {

        Dataset dataset1 =  new Dataset("decoy_dataset");

        dataset1.insert(300);
        dataset1.insert(350);
        dataset1.insert(330);
        dataset1.insert(340);
        dataset1.insert(390);
        dataset1.insert(430);
        dataset1.insert(480);
        dataset1.insert(460);
        dataset1.insert(490);
        dataset1.insert(510);
        dataset1.insert(550);
        dataset1.insert(560);
        // Second year demand of first dataset begins here
        dataset1.insert(550);
        dataset1.insert(590);
        dataset1.insert(600);
        dataset1.insert(610);
        dataset1.insert(630);
        dataset1.insert(620);
        dataset1.insert(680);
        dataset1.insert(690);
        dataset1.insert(710);
        dataset1.insert(330); // Normally 730, intentionally changed it to multiple value for test
        dataset1.insert(740);
        dataset1.insert(770);






        Dataset dataset2 =  new Dataset("decoy_dataset2");

        dataset2.insert(1);
        dataset2.insert(2);
        dataset2.insert(3);
        dataset2.insert(4);
        dataset2.insert(5);
        dataset2.insert(6);
        dataset2.insert(7);
        dataset2.insert(8);
        dataset2.insert(9);
        dataset2.insert(10);
        dataset2.insert(11);
        dataset2.insert(12);
        // Second year demand of first dataset begins here
        dataset2.insert(13);
        dataset2.insert(14);
        dataset2.insert(15);
        dataset2.insert(16);
        dataset2.insert(17);
        dataset2.insert(18);
        dataset2.insert(19);
        dataset2.insert(20);
        dataset2.insert(21);
        dataset2.insert(22);
        dataset2.insert(23);
        dataset2.insert(24);

        databases.insert(dataset1);
        databases.insert(dataset2);

        refreshDatasetNames();


    }


}
