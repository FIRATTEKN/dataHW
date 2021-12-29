package org.example;

public class Structures {

    public static class LinkedList{
        public static class Node {
            Node next;
            int value;
            Dataset dataset;

            // This node type was needed in Dataset -> search()
            Node(int value){
                this.next = null;
                this.value = value;
            }

            // This node type defined to store databases
            Node(Dataset dataset){
                this.next = null;
                this.dataset = dataset;
                String name = dataset.name;
            }

        }
        // Default single linked list. Needed this for search function in Dataset class
        // Yes, array wasn't enough.
        // We can store all added databases inside this single linked list
        // To not wrie another linked list implementation I overloaded the Node and the functions.
        Node head;

        LinkedList(){
            this.head = null;
        }

        boolean isEmpty(){
            return head == null;
        }

        int size(){
            int count = 0;
            Node tmp = head;

            if (isEmpty())
                return 0;

            while (tmp != null){
                count++;
                tmp = tmp.next;
            }

            return count;
        }

        Dataset find_by_name(LinkedList list_of_databases, String database_name){

            Node tmp = list_of_databases.head;

            while (tmp != null){
                if(tmp.dataset.name.equals(database_name)){
                    return tmp.dataset;
                }
                tmp = tmp.next;
            }

            return null;
        }
        // ------------------------------------
        // Insert

        void insert(int value){
            Node new_node = new Node(value);
            Node tmp = head;

            if (isEmpty()){
                head = new_node;
            }
            else{
                while(tmp.next != null){
                    tmp = tmp.next;
                }
                tmp.next = new_node;
            }

        }

        void insert(Dataset dataset){
            Node new_dataset = new Node(dataset);
            Node tmp = head;

            if (isEmpty()){
                head = new_dataset;
            }
            else{
                while(tmp.next != null){
                    tmp = tmp.next;
                }
                tmp.next = new_dataset;
            }

        }

        void insert_by_name(LinkedList list_of_databases, String dataset_name){
            insert(find_by_name(list_of_databases, dataset_name));
        }

        // ------------------------------------
        // Delete

        void delete(int value){
            Node tmp = head;
            Node previous = null;

            // If list is empty
            if (isEmpty()){
                return;
            }
            // If head node holds the value
            else if (tmp != null && tmp.value == value){
                head = tmp.next;
                return;
            }

            // Iterate untill the key
            while (tmp != null && tmp.value != value){
                previous = tmp;
                tmp = tmp.next;
            }

            previous.next = tmp.next;

        }

        void delete(Dataset dataset){

            Node tmp = head;
            Node previous = null;

            // If list is empty
            if (isEmpty()){
                return;
            }
            // If head node holds the value
            else if (tmp != null && tmp.dataset == dataset){
                head = tmp.next;
                return;
            }

            // Iterate untill the key
            while (tmp != null && tmp.dataset != dataset){
                previous = tmp;
                tmp = tmp.next;
            }

            previous.next = tmp.next;


        }

        void delete_by_name(LinkedList list_of_databases, String dataset_name){
            this.delete(find_by_name(list_of_databases, dataset_name));
        }
        // ------------------------------------
        // List

        void list_stored_datasets(){
            if(this.isEmpty()){
                return;
            }
            // Head node copied
            Node node = this.head;
            int index = 0;

            while(index < this.size()){
                System.out.println(node.dataset.name);
                node = node.next;
                ++index;
            }
        }

    }


}
