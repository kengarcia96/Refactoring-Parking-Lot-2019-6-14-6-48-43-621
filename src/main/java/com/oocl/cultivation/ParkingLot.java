package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private int capacity;
    private Map<ParkingTicket, Car> cars = new HashMap<>();

    public ParkingLot() {
        this(10);
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public ParkingTicket addCar(Car car) {

        if(getAvailableParkingPosition() == 0){
            return null;
        }

        else{
            ParkingTicket parkingTicket = new ParkingTicket();
            cars.put(parkingTicket,car);
            return parkingTicket;
        }

    }

    public Car fetchCar(ParkingTicket ticket) {
        Car car = cars.get(ticket);
        cars.remove(ticket);

        return car;
    }



    public int getAvailableParkingPosition() {
        return cars.size() - capacity;
    }

    public int countCars() {
        return cars.size();
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
