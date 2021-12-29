package org.example;

public class test2 {


    public void getDataset(Dataset dataset){

    }
    public static void main(String[] args) {
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


        Structures.LinkedList databases = new Structures.LinkedList();
        databases.insert(dataset1);
        System.out.println(databases.head.dataset.name);







    }

}
