package com.airlines.core.model;

import com.airlines.core.enums.SeatType;

/**
 * Airplane seat representation and ready for prioritization by seat type
 *
 */
public class Seat implements Comparable<Seat> {

    int number;

    SeatType type;

    int passengerId;

    public Seat() {
    }

    public Seat(int number, SeatType type) {
        this.setNumber(number);
        this.setType(type);
    }

    public Seat(int number, SeatType type, int passengerId) {
        this.setNumber(number);
        this.setType(type);
        this.setPassengerId(passengerId);
    }

    /**
     * This method compares two seats by its seat type and in case of conflict
     * compares by seat number
     * 
     */
    @Override
    public int compareTo(Seat prev) {
        int priorityComparison = Integer.compare(prev.getType().getPriority(), this.getType().getPriority());
        if (priorityComparison == 0) {
            priorityComparison = Integer.compare(this.getNumber(), prev.getNumber());
        }
        return priorityComparison;
    }

    /**
     * @return the number
     */
    public int getNumber() {
        return number;
    }

    /**
     * @return the type
     */
    public SeatType getType() {
        return type;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * @param type the type to set
     */
    public void setType(SeatType type) {
        this.type = type;
    }

    /**
     * @return the passengerId
     */
    public int getPassengerId() {
        return passengerId;
    }

    /**
     * @param passengerId the passengerId to set
     */
    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    @Override
    public String toString() {
        String passengerId = this.getPassengerId() != 0 ? String.valueOf(this.getPassengerId()) : "";
        return this.getNumber() + ":" + this.getType() + ":" + passengerId;
    }

}