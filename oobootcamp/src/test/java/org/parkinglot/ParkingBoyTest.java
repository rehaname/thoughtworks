package org.parkinglot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ParkingBoyTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void parkingboy_parks_in_parkinglots() {

        ParkingBoy parkingBoy = new ParkingBoy(new NormalSearch());

        ParkingLot pkgLotA = new ParkingLot(10);
        ParkingLot pkgLotB = new ParkingLot(10);
        ParkingLot pkgLotC = new ParkingLot(10);
        parkingBoy.addParkingLot(pkgLotA);
        parkingBoy.addParkingLot(pkgLotB);
        parkingBoy.addParkingLot(pkgLotC);

        Car car = new Car();
        Ticket ticket = parkingBoy.parkCar(car);
        assertTrue(parkingBoy.containsCar(car));
        assertTrue(car.checkAssignedTicket(ticket));
    }

    @Test
    public void parkingboy_parks_but_no_slots_available_in_parkinglots() {
        ParkingBoy parkingBoy = new ParkingBoy(new NormalSearch());

        ParkingLot pkgLotA = new ParkingLot(10);
        ParkingLot pkgLotB = new ParkingLot(10);
        ParkingLot pkgLotC = new ParkingLot(10);
        parkingBoy.addParkingLot(pkgLotA);
        parkingBoy.addParkingLot(pkgLotB);
        parkingBoy.addParkingLot(pkgLotC);

        for (int i = 0; i <= 29; i++) {
            parkingBoy.parkCar(new Car());
        }

        Car car = new Car();
        exception.expect(NoSuchElementException.class);
        parkingBoy.parkCar(car);
    }

    @Test
    public void pick_park_car_with_correct_ticket() {
        ParkingBoy parkingBoy = new ParkingBoy(new NormalSearch());

        ParkingLot pkgLotA = new ParkingLot(10);
        ParkingLot pkgLotB = new ParkingLot(10);
        ParkingLot pkgLotC = new ParkingLot(10);
        parkingBoy.addParkingLot(pkgLotA);
        parkingBoy.addParkingLot(pkgLotB);
        parkingBoy.addParkingLot(pkgLotC);

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
        ParkingBoy parkingBoy = new ParkingBoy(new NormalSearch());
        ParkingLot pkgLotA = new ParkingLot(10);
        ParkingLot pkgLotB = new ParkingLot(10);
        ParkingLot pkgLotC = new ParkingLot(10);
        parkingBoy.addParkingLot(pkgLotA);
        parkingBoy.addParkingLot(pkgLotB);
        parkingBoy.addParkingLot(pkgLotC);


        for (int i = 0; i <= 15; i++) {
            parkingBoy.parkCar(new Car());
        }
        Car car = new Car();
        Ticket ticket = parkingBoy.parkCar(car);
        assertTrue(parkingBoy.containsCar(car));
        assertTrue(car.checkAssignedTicket(ticket));
        assertNotEquals(car, parkingBoy.pickUpCar(new Ticket(100)));
    }


    @Test
    public void smartparkingboy_parks_in_parkinglots_with_most_available_slot_and_picksup_car() {
        ParkingBoy parkingBoy = new ParkingBoy(new SmartSearch());
        ParkingLot pkgLotA = new ParkingLot(10);
        ParkingLot pkgLotB = new ParkingLot(10);
        ParkingLot pkgLotC = new ParkingLot(10);
        parkingBoy.addParkingLot(pkgLotA);
        parkingBoy.addParkingLot(pkgLotB);
        parkingBoy.addParkingLot(pkgLotC);

        for (int i = 0; i <= 7; i++) {
            pkgLotA.getCars().add(new Car());
        }
        for (int i = 0; i <= 3; i++) {
            pkgLotB.getCars().add(new Car());
        }
        for (int i = 0; i <= 8; i++) {
            pkgLotC.getCars().add(new Car());
        }

        Car car = new Car();
        Ticket ticket = parkingBoy.parkCar(car);
        assertTrue(parkingBoy.containsCar(car));
        assertTrue(car.checkAssignedTicket(ticket));
        assertTrue(pkgLotB.getCars().contains(car));
        assertEquals(car, parkingBoy.pickUpCar(ticket));
    }


    @Test
    public void superparkingboy_parks_in_parkinglots_with_slot_that_has_best_rate_and_picksup_car() {
        ParkingBoy parkingBoy = new ParkingBoy(new SuperSearch());
        ParkingLot pkgLotA = new ParkingLot(15);
        ParkingLot pkgLotB = new ParkingLot(8);
        ParkingLot pkgLotC = new ParkingLot(15);
        parkingBoy.addParkingLot(pkgLotA);
        parkingBoy.addParkingLot(pkgLotB);
        parkingBoy.addParkingLot(pkgLotC);

        insertCarsInParkingLot(pkgLotA,11);
        insertCarsInParkingLot(pkgLotB,4);
        insertCarsInParkingLot(pkgLotC,14);

        Car car = new Car();
        Ticket ticket = parkingBoy.parkCar(car);
        assertTrue(parkingBoy.containsCar(car));
        assertTrue(car.checkAssignedTicket(ticket));
        assertTrue(pkgLotB.getCars().contains(car));
        assertEquals(car, parkingBoy.pickUpCar(ticket));
    }


    //TODO given there is a parking manager and one parking boy and there is  parking lot is available in parking boy, when car is parked and recieved ticket, then  parking boy parks the car
    @Test
    public void given_manager_and_boy_and_availableSlot_boy_will_park_the_car_then_recieves_ticket() throws Exception {
        ParkingManager parkingManager = new ParkingManager(new SmartSearch());
        ParkingLot pkgLotA = new ParkingLot(7);
        ParkingLot pkgLotB = new ParkingLot(7);
        ParkingLot pkgLotC = new ParkingLot(7);
        parkingManager.addParkingLot(pkgLotA);
        parkingManager.addParkingLot(pkgLotB);
        parkingManager.addParkingLot(pkgLotC);

        insertCarsInParkingLot(pkgLotA,5);
        insertCarsInParkingLot(pkgLotB,6);
        insertCarsInParkingLot(pkgLotC,3);

        ParkingBoy parkingBoy = new ParkingBoy(new SmartSearch());
        ParkingLot pkgLotD = new ParkingLot(15);
        ParkingLot pkgLotE = new ParkingLot(8);
        ParkingLot pkgLotF = new ParkingLot(15);
        parkingBoy.addParkingLot(pkgLotD);
        parkingBoy.addParkingLot(pkgLotE);
        parkingBoy.addParkingLot(pkgLotF);

        insertCarsInParkingLot(pkgLotD,11);
        insertCarsInParkingLot(pkgLotE,8);
        insertCarsInParkingLot(pkgLotF,15);

        parkingManager.addParkingBoy(parkingBoy);


        Car car = new Car();
        Ticket ticket = parkingManager.parkCar(car);

        assertTrue(parkingBoy.containsCar(car));
        assertTrue(car.checkAssignedTicket(ticket));
        assertTrue(pkgLotD.getCars().contains(car));
        assertEquals(car, parkingManager.pickUpCar(ticket));
    }



    //TODO given there is a parking manager and there is no parking boy and there is  parking lot  avaiable, when car is parked and recieved ticket, then  parking manager parks the car
    @Test
    public void given_manager_and_no_boy_and_availableSlot_manager_will_park_the_car_then_recieves_ticket() throws Exception {
        ParkingManager parkingManager = new ParkingManager(new NormalSearch());
        ParkingLot pkgLotA = new ParkingLot(7);
        ParkingLot pkgLotB = new ParkingLot(7);
        ParkingLot pkgLotC = new ParkingLot(7);
        parkingManager.addParkingLot(pkgLotA);
        parkingManager.addParkingLot(pkgLotB);
        parkingManager.addParkingLot(pkgLotC);

        insertCarsInParkingLot(pkgLotA,5);
        insertCarsInParkingLot(pkgLotB,6);
        insertCarsInParkingLot(pkgLotC,3);



        Car car = new Car();
        Ticket ticket = parkingManager.parkCar(car);

        assertTrue(parkingManager.containsCar(car));
        assertTrue(car.checkAssignedTicket(ticket));
        assertTrue(pkgLotA.getCars().contains(car));
        assertEquals(car, parkingManager.pickUpCar(ticket));
    }
    //TODO given there is a parking manager and there is no parking boy and there is no parking lot  avaiable, when car is parked and recieved ticket, then  Exception
    @Test
    public void given_manager_and_no_boy_and_no_availableSlot_will_cause_exception() throws Exception {
        ParkingManager parkingManager = new ParkingManager(new NormalSearch());
        ParkingLot pkgLotA = new ParkingLot(7);
        ParkingLot pkgLotB = new ParkingLot(7);
        ParkingLot pkgLotC = new ParkingLot(7);
        parkingManager.addParkingLot(pkgLotA);
        parkingManager.addParkingLot(pkgLotB);
        parkingManager.addParkingLot(pkgLotC);

        insertCarsInParkingLot(pkgLotA,7);
        insertCarsInParkingLot(pkgLotB,7);
        insertCarsInParkingLot(pkgLotC,7);



        Car car = new Car();
        exception.expect(NoSuchElementException.class);
        Ticket ticket = parkingManager.parkCar(car);
    }
    //TODO given there is a parking manager and one parking boy and there is no  parking lot is available then Exception
    @Test
    public void given_manager_and_boy_and_no_availableSlot_will_return_no_ticket()  throws Exception {
        ParkingManager parkingManager = new ParkingManager(new SmartSearch());
        ParkingLot pkgLotA = new ParkingLot(7);
        ParkingLot pkgLotB = new ParkingLot(7);
        ParkingLot pkgLotC = new ParkingLot(7);
        parkingManager.addParkingLot(pkgLotA);
        parkingManager.addParkingLot(pkgLotB);
        parkingManager.addParkingLot(pkgLotC);

        insertCarsInParkingLot(pkgLotA,7);
        insertCarsInParkingLot(pkgLotB,7);
        insertCarsInParkingLot(pkgLotC,7);

        ParkingBoy parkingBoy = new ParkingBoy(new SmartSearch());
        ParkingLot pkgLotD = new ParkingLot(15);
        ParkingLot pkgLotE = new ParkingLot(8);
        ParkingLot pkgLotF = new ParkingLot(15);
        parkingBoy.addParkingLot(pkgLotD);
        parkingBoy.addParkingLot(pkgLotE);
        parkingBoy.addParkingLot(pkgLotF);

        insertCarsInParkingLot(pkgLotD,15);
        insertCarsInParkingLot(pkgLotE,8);
        insertCarsInParkingLot(pkgLotF,15);

        parkingManager.addParkingBoy(parkingBoy);


        Car car = new Car();
        assertNull(parkingManager.parkCar(car));
    }

    private void insertCarsInParkingLot(ParkingLot pkgLotA, int limit) {
        for (int i = 1; i <= limit; i++) {
            pkgLotA.park(new Car());
        }
    }
}
