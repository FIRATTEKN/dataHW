package org.example;

public class test {

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




        System.out.println("----------------------------");
        System.out.println("[NORMAL ORDER]\n");
        dataset1.print();

        System.out.println("----------------------------");
        System.out.println("\n[REVERSE ORDER]\n");
        dataset1.print_reverse();

        System.out.println("----------------------------");
        System.out.println("MAX Sales: " + dataset1.max_sales(dataset1));
        System.out.println("MIN Sales: " + dataset1.min_sales(dataset1));

        System.out.println("----------------------------");
        System.out.println("Matched queries: " + dataset1.search(510).size());
        System.out.println("Index of matched query: " + dataset1.search(510).head.value);

        System.out.println("----------------------------");
        System.out.println(dataset1.replace_by_index( 8, 999));
        System.out.println(dataset1.replace_first(340, 666));
        System.out.println(dataset1.replace_all(330, 777));
        dataset1.print();

        System.out.println("----------------------------");
        Structures.LinkedList databases = new Structures.LinkedList();
        databases.insert(dataset1);
        databases.insert(dataset2);
        databases.list_stored_datasets();




        System.out.println("----------------------------");
        databases.delete(dataset1);
        databases.list_stored_datasets();

        System.out.println("----------------------------");
        dataset1.destroy_dataset();
        dataset2.destroy_dataset();
        dataset1.print();



        // [TODO] Data Class constructor can be overloaded into calculation mean of multiple values per month.

        // [TODO] For "List all datasets" task, we might need a storage for added datasets.
    }
}
