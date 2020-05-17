package by.prokhorenko.threads.entity;

public enum TruckCapacityType {
    SMALL(25),
    MIDDLE(50),
    LARGE(100);

    private int capacity;
     TruckCapacityType(int capacity){
         this.capacity = capacity;
     }

     public int getCapacity(){
         return capacity;
     }
}
