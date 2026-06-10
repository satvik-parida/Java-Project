class Passenger {

    private String name;
    private String passengerId;

    public Passenger(String name, String passengerId) {
        this.name = name;
        this.passengerId = passengerId;
    }

    public String getName() {
        return name;
    }

    public String getPassengerId() {
        return passengerId;
    }

    @Override
    public String toString() {
        return name + " (" + passengerId + ")";
    }
}

class Flight {

    private String flightNumber;
    private final int MAX_SEATS;
    private Passenger[] seats;
    private int bookedCount;

    public Flight(String flightNumber, int maxSeats) {
        this.flightNumber = flightNumber;
        this.MAX_SEATS = maxSeats;
        this.seats = new Passenger[MAX_SEATS];
        this.bookedCount = 0;

        System.out.println("Flight " + flightNumber + " created with " + MAX_SEATS + " seats.");
    }

    public boolean bookSeat(Passenger p) {

        if (bookedCount == MAX_SEATS) {
            System.out.println("Flight Full! Booking failed for Passenger: " + p.getName());
            return false;
        }

        for (int i = 0; i < MAX_SEATS; i++) {
            if (seats[i] != null && seats[i].getPassengerId().equals(p.getPassengerId())) {
                System.out.println("Duplicate booking! " + p + " is already booked.");
                return false;
            }
        }

        for (int i = 0; i < MAX_SEATS; i++) {
            if (seats[i] == null) {
                seats[i] = p;
                bookedCount++;
                System.out.println("Passenger " + p + " booked successfully.");
                return true;
            }
        }

        return false;
    }

    public void displayStatus() {

        System.out.println("\\nSeat Status for Flight " + flightNumber + ":");

        for (int i = 0; i < MAX_SEATS; i++) {

            if (seats[i] == null) {
                System.out.println("Seat " + (i + 1) + ": Empty");
            } else {
                System.out.println("Seat " + (i + 1) + ": " + seats[i]);
            }
        }
    }

    public int getAvailableSeats() {
        return MAX_SEATS - bookedCount;
    }

    public String getFlightNumber() {
        return flightNumber;
    }
}

public class FlightReservationSystem {

    public static void main(String[] args) {

        Flight flight = new Flight("AI202", 5);

        Passenger p1 = new Passenger("Aman", "P001");
        Passenger p2 = new Passenger("Rahul", "P002");
        Passenger p3 = new Passenger("Priya", "P003");
        Passenger p4 = new Passenger("Sneha", "P004");
        Passenger p5 = new Passenger("Arjun", "P005");
        Passenger p6 = new Passenger("Riya", "P006");
        Passenger p1Dup = new Passenger("Aman", "P001");

        flight.bookSeat(p1);
        flight.bookSeat(p2);
        flight.bookSeat(p3);
        flight.bookSeat(p4);
        flight.bookSeat(p5);

        flight.bookSeat(p1Dup);
        flight.bookSeat(p6);

        flight.displayStatus();

        System.out.println("Available seats on " + flight.getFlightNumber() + ": " + flight.getAvailableSeats());
    }
}
