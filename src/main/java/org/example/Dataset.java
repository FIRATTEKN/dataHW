package org.example;

public class Dataset {

    public class Data {
        // Node definition will be made in this class
        private int data_value;
        Data next;
        Data previous;

        public Data(int data_value){
            this.data_value = data_value;
            next = null;
            previous = null;
        }

        public int get_data(){
            return this.data_value;
        }

        public int set_data(int new_data){
            this.data_value = new_data;
            return this.data_value;
        }

    }

    String name;
    Data head;
    Data tail;

    String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    // TODO: Dataset dimension can be calculated by dataset.size()/12, I am a little confused about where to use this.
    public Dataset(String dataset_name){
        this.name = dataset_name;
        this.head = null;
        this.tail = null;
    }

    // [BASIC FUNCTIONALITY] ----------------------------------------------------------

    public boolean isEmpty(){
        return head == null;
    }

    public int size() {
        int index = 0;
        Data tmp = head;

        if(tmp == null){
            return 0;
        }

        while (tmp != null){
            tmp = tmp.next;
            index++;
        }

        // Check if entered dataset values are enough to complete year(s).
        // Added for homework demand "Number of record for each month should be equal"
        if (index % 12 != 0){
            System.out.println("Size of dataset items are not sufficient for forecast.");
            return -1;
        }

        return index;
    }

    public void insert(int data_value){

        Data inserted_data = new Data(data_value);

        // Check if linked list is empty
        if(head == null){
            head = inserted_data;
            tail = inserted_data;

            // Make sure there are no unnecessary and unused pointers
            head.previous = null;
            tail.next = null;
        }
        // Add new nodes to end of the linked list
        else {
            tail.next = inserted_data;
            inserted_data.previous = tail;
            tail = inserted_data;   // Tail updated
        }
    }

    // [WARNING] This function might require modification for UI.
    public void destroy_dataset(){
        this.head = null;
        System.out.println("Dataset \""+ this.name +"\" removed");
    }

    // [MAX-MIN SALES] ---------------------------------------------------------------
    public int max_sales(Dataset x){

        Data tmp = x.head;
        int max = x.head.get_data();

        while (tmp != null){

            if (tmp.get_data() > max){
                max = tmp.get_data();
            }

            tmp = tmp.next;
        }

        return max;
    }

    public int min_sales(Dataset x){
        Data tmp = x.head;
        int min = x.head.get_data();

        while (tmp != null){

            if (tmp.get_data() < min){
                min = tmp.get_data();
            }

            tmp = tmp.next;
        }

        return min;
    }

    // [SEARCH AND REPLACE] ----------------------------------------------------------
    public Structures.LinkedList search(int query){
        Data tmp = head;
        int count = 0;
        int index = 0;

        // I am using default single  linked list to store indexes of matched queries.
        Structures.LinkedList found = new Structures.LinkedList();

        if (isEmpty()){
            return null;
        }

        while (tmp != null){
            if(tmp.get_data() == query){
                count++;
                found.insert(index);
            }
            tmp = tmp.next;
            index++;
        }


        return found;
    }

    public boolean replace_by_index(int index, int new_value){

        Data tmp = head;
        int i = 0;

        while (tmp != null && index <= this.size()){
            if(i == index){
                tmp.set_data(new_value);
                return true;
            }

            tmp = tmp.next;
            i++;
        }
        // If data not found, this function will return false.
        return false;
    }

    public boolean replace_first(int query, int new_value){

        Structures.LinkedList found =  search(query);

        // I have checked if dataset is null inside search function.
        if (found == null) {
            return false;
        }

        int first_found_index = found.head.value;

        return replace_by_index(first_found_index, new_value);
    }

    public boolean replace_all(int query, int new_value){

        Structures.LinkedList found = search(query);

        // I have checked if dataset is null inside search function.
        if (found == null) {
            return false;
        }

        // Iterate inside "found" to get indexes and call replace by index for each of them.
        Structures.LinkedList.Node tmp = found.head;

        while (tmp != null){
            replace_by_index(tmp.value, new_value);
            tmp = tmp.next;
        }

        return true;
    }

    // [PRINT DATABASE] --------------------------------------------------------------

    // [WARNING] This function might require modification for UI.
    public void print(){

        if(this.isEmpty()){
            return;
        }

        // Head node copied
        Data data = this.head;
        int index = 0;

        while(index < this.size()){
            System.out.println(months[index % 12] + ": " + data.get_data());
            data = data.next;
            ++index;
        }
    }

    // [WARNING] This function might require modification for UI.
    public void print_reverse(){
        if(this.isEmpty()){
            return;
        }

        // Head node copied
        Data data = this.tail;
        int index = 0;

        while(index < size()){
            System.out.println(months[(months.length - 1) - (index % 12)] + ": " + data.get_data());
            data = data.previous;
            index++;
        }

    }
}
