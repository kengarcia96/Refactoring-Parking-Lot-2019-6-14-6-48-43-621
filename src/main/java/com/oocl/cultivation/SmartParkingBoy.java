package com.oocl.cultivation;

import java.util.List;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    @Override
    public ParkingTicket park(Car car){
        ParkingLot parkingLot = getParkingLots().stream().reduce(((parking1, parking2) -> parking1.countCars() <= parking1.countCars() ? parking1 : parking2)).orElse(null);

        if (parkingLot == null) {
            setLastErrorMessage("Not enough position.");
            return null;
        }
        return parkingLot.addCar(car);
    }
}

