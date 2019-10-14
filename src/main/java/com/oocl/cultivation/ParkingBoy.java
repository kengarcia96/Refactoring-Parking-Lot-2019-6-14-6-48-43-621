package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {

    private List<ParkingLot> parkingLots = new ArrayList<>();
    private ParkingLot parkingLot;

    private String lastErrorMessage;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
        parkingLots.add(parkingLot);

    }

    public void setParkingLot(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
        this.parkingLot = parkingLot;
    }


    public ParkingTicket park(Car car) {

        ParkingTicket ticket;
        ParkingLot parkingLotAvailable = parkingLots.stream().filter(pLot -> pLot.getAvailableParkingPosition()>0).findFirst().orElse(this.parkingLot);

        if(parkingLotAvailable == null){
            ticket = null;
        }

        else{
             ticket = parkingLotAvailable.addCar(car);
        }

        if(ticket == null){
            lastErrorMessage = "Not enough position.";
            return null;
        }

        return ticket;

    }



    public Car fetch(ParkingTicket ticket) {
        Car carsGot = parkingLot.fetchCar(ticket);

        if (ticket == null){
            lastErrorMessage = "Please provide your parking ticket.";
            return null;
        }

        else if (carsGot == null) {
            lastErrorMessage = "Unrecognized parking ticket";
            return null;
        }
        else {
            return carsGot;
        }

    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setLastErrorMessage(String lastErrorMessage) {
        this.lastErrorMessage = lastErrorMessage;
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }






}
