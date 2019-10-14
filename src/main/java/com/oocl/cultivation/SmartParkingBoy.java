package com.oocl.cultivation;

public class SmartParkingBoy extends ParkingBoy {

    private static final String NOT_ENOUGH_POSITION = "Not enough position.";

    public SmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    @Override
    public ParkingTicket park(Car car){
        ParkingLot parkingLot = getParkingLots().stream().reduce(((parking1, parking2) -> parking1.countCars() <= parking1.countCars() ? parking1 : parking2)).orElse(null);

        if (parkingLot == null) {
            setLastErrorMessage(NOT_ENOUGH_POSITION);
            return null;
        }
        return parkingLot.addCar(car);
    }
}

