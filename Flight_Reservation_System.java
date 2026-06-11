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

        // Check if flight is full
        if (bookedCount == MAX_SEATS) {
            System.out.println("Flight Full! Booking failed for Passenger: " + p.getName());
            return false;
        }

        // Check duplicate booking
        for (int i = 0; i < MAX_SEATS; i++) {
            if (seats[i] != null &&
                seats[i].getPassengerId().equals(p.getPassengerId())) {

                System.out.println("Duplicate booking! " + p + " is already booked.");
                return false;
            }
        }

        // Book seat
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

        System.out.println("\nSeat Status for Flight " + flightNumber + ":");

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

public class Flight_Reservation_System {

    public static void main(String[] args) {

        // Create flight
        Flight flight = new Flight("AI202", 5);

        // Create passengers
        Passenger p1 = new Passenger("Aman", "P001");
        Passenger p2 = new Passenger("Rahul", "P002");
        Passenger p3 = new Passenger("Priya", "P003");
        Passenger p4 = new Passenger("Sneha", "P004");
        Passenger p5 = new Passenger("Arjun", "P005");
        Passenger p6 = new Passenger("Riya", "P006");

        // Duplicate passenger
        Passenger p1Dup = new Passenger("Aman", "P001");

        // Book seats
        flight.bookSeat(p1);
        flight.bookSeat(p2);
        flight.bookSeat(p3);
        flight.bookSeat(p4);
        flight.bookSeat(p5);

        // Test duplicate booking
        flight.bookSeat(p1Dup);

        // Test full flight booking
        flight.bookSeat(p6);

        // Display seat status
        flight.displayStatus();

        // Display available seats
        System.out.println(
            "\nAvailable seats on "
            + flight.getFlightNumber()
            + ": "
            + flight.getAvailableSeats()
        );
    }
}
