package org.parkinglot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ParkingBoyTest {

    @Test
    public void parkingboy_parks_in_parkinglots() {
        ParkingBoy parkingBoy = new NormalParkingBoy(3);
        Car car = new Car();
        Ticket ticket = parkingBoy.parkCar(car);
        assertTrue(parkingBoy.containsCar(car));
        assertTrue(car.checkAssignedTicket(ticket));
    }

    @Test
    public void parkingboy_parks_but_no_slots_available_in_parkinglots() {
        ParkingBoy parkingBoy = new NormalParkingBoy(3);
        for (int i = 0; i <= 30; i++) {
            parkingBoy.parkCar(new Car());
        }

        Car car = new Car();
        Ticket ticket = parkingBoy.parkCar(car);
        assertFalse(parkingBoy.containsCar(car));
        assertFalse(car.checkAssignedTicket(ticket));
    }

    @Test
    public void pick_park_car_with_correct_ticket() {
        ParkingBoy parkingBoy = new NormalParkingBoy(3);
        for (int i = 0; i <= 15; i++) {
            parkingBoy.parkCar(new Car());
        }
        Car car = new Car();
        Ticket ticket = parkingBoy.parkCar(car);
        assertTrue(parkingBoy.containsCar(car));
        assertTrue(car.checkAssignedTicket(ticket));
        assertEquals(car, parkingBoy.pickUpCar(ticket));
    }

    @Test
    public void pick_park_car_with_incorrect_ticket() {
        ParkingBoy parkingBoy = new NormalParkingBoy(3);
        for (int i = 0; i <= 15; i++) {
            parkingBoy.parkCar(new Car());
        }
        Car car = new Car();
        Ticket ticket = parkingBoy.parkCar(car);
        assertTrue(parkingBoy.containsCar(car));
        assertTrue(car.checkAssignedTicket(ticket));
        assertNotEquals(car, parkingBoy.pickUpCar(new Ticket(100)));
    }



    //park with slots avaible and return ticket
    // park with no slots available
    //pick up car with correct ticket
    //pick up car with incorrect ticket

    @Test
    public void smartparkingboy_parks_in_parkinglots_with_most_available_slot() {
        ParkingBoy parkingBoy = new SmartParkingBoy(3);
        Car car = new Car();
        Ticket ticket = parkingBoy.parkCar(car);
        assertTrue(parkingBoy.containsCar(car));
        assertTrue(car.checkAssignedTicket(ticket));
    }



}
