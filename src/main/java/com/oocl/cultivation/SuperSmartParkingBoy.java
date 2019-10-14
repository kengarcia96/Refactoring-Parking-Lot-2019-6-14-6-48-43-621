package com.oocl.cultivation;

import java.util.Comparator;
import java.util.List;

public class SuperSmartParkingBoy extends ParkingBoy{


    public SuperSmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    @Override
    public ParkingTicket park(Car car) {
        ParkingLot parkingLot =
        getParkingLots().stream().filter(aParkingLot -> aParkingLot.getAvailableParkingPosition() > 0)
                .max(Comparator.comparing(aParkingLot -> aParkingLot.getAvailableParkingPosition() / aParkingLot.getCapacity()))
                .orElse(null);

        if (parkingLot == null) {
            setLastErrorMessage("Not enough position.");
            return null;
        }

        return parkingLot.addCar(car);
    }

}
