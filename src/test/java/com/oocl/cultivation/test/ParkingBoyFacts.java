package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyFacts {
    @Test
    void should_park_car_to_parking_lot_by_parking_boy() {
        // given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        // when
        ParkingTicket parkingTicket = parkingBoy.park(car);

        // then
        assertNotNull(parkingTicket);
    }

    @Test
    void should_fetch_car_to_parking_lot_by_parking_boy() {
        // given
        Car car1 = new Car();
        Car car2 = new Car();

        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        // when
        ParkingTicket parkingTicket1 = parkingBoy.park(car1);
        ParkingTicket parkingTicket2 = parkingBoy.park(car2);
        Car carGet = parkingBoy.fetch(parkingTicket1);

        // then
        assertEquals(car1, carGet);
    }

    @Test
    void should_return_no_car_when_no_ticket_is_given() {
        // given

        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        // when
        Car carGet = parkingBoy.fetch(null);

        // then
        assertNull(carGet);
    }

    @Test
    void should_return_no_car_when_wrong_ticket_is_given() {
        // given;

        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        // when

        Car carGet = parkingBoy.fetch(new ParkingTicket());

        // then
        assertNull(carGet);
    }

    @Test
        void should_return_no_car_when_ticket_is_used() {
        // given;
        Car car1 = new Car();

        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        // when
        ParkingTicket parkingTicket1 = parkingBoy.park(car1);
        Car carGet = parkingBoy.fetch(parkingTicket1);
        Car carGet2 = parkingBoy.fetch(parkingTicket1);

        // then
        assertNotNull(carGet);
        assertNull(carGet2);
    }

    @Test
    void should_return_null_when_capacity_exceed_limit() {
        // given;

        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        // when

        for(int i = 0; i < 10; i++ ){
            Car car = new Car();
            ParkingTicket parkingTicket = parkingBoy.park(car);
        }

        Car car1 = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car1);

        assertNull(parkingTicket);
    }

    @Test
    void should_get_error_message_when_no_ticket_is_given() {
        // given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        // when
        Car carGet = parkingBoy.fetch(null);

        // then
        assertNull(carGet);
        assertEquals(parkingBoy.getLastErrorMessage(),"Please provide your parking ticket.");
    }

    @Test
    void should_get_error_message_when_wrong_ticket_is_given() {
        // given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        // when
        Car carGet = parkingBoy.fetch(new ParkingTicket());

        // then
        assertNull(carGet);
        assertEquals(parkingBoy.getLastErrorMessage(),"Unrecognized parking ticket");
    }

    @Test
    void should_get_error_message_when_parkingLot_is_all_occupied() {
        // given;

        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        // when

        for(int i = 0; i < 10; i++ ){
            Car car = new Car();
            ParkingTicket parkingTicket = parkingBoy.park(car);
        }

        Car newCar = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(newCar);

        assertNull(parkingTicket);
        assertEquals(parkingBoy.getLastErrorMessage(),"Not enough position.");
    }

    @Test
    void should_return_a_ticket_even_if_there_is_multiple_parkinglot() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();

        ParkingBoy parkingBoy = new ParkingBoy(firstParkingLot);
        parkingBoy.setParkingLot(secondParkingLot);

        ParkingTicket parkingTicket = new ParkingTicket();

        //when

        for(int i = 0; i < 10; i++){
            Car car = new Car();
            parkingTicket = parkingBoy.park(car);
        }

        //then
        assertNotNull(parkingTicket);
    }


    @Test
    void should_return_car_count_when_smart_parking_boy_park_to_lesser_parking_lot() {
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();

        ParkingBoy parkingBoy1 = new ParkingBoy(parkingLot1);
        ParkingBoy parkingBoy2 = new ParkingBoy(parkingLot2);


        for(int i = 0; i < 4; i++){
            Car car = new Car();
            parkingBoy1.park(car);
        }

        for(int i = 0; i < 2; i++){
            Car car = new Car();
            parkingBoy2.park(car);
        }


        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot2);
        smartParkingBoy.setParkingLot(parkingLot2);
        smartParkingBoy.park(new Car());

        assertEquals(4, parkingLot1.countCars());
        assertEquals(3, parkingLot2.countCars());
    }

    @Test
    void should_return_car_count_when_super_smart_parking_boy_park_in_bigger_parking_lot() {
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();

        ParkingBoy parkingBoy1 = new ParkingBoy(parkingLot1);
        ParkingBoy parkingBoy2 = new ParkingBoy(parkingLot2);


        for(int i = 0; i <= 8; i++){
            parkingBoy1.park(new Car());
        }

        for(int i = 0; i < 5; i++){
            parkingBoy2.park(new Car());
        }

        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLot1);
        superSmartParkingBoy.setParkingLot(parkingLot2);

        superSmartParkingBoy.park(new Car());

        assertEquals(9, parkingLot1.countCars());
        assertEquals(5, parkingLot2.countCars());
    }

}
