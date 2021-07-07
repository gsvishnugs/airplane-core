package com.airlines.core.enums;

/**
 * Seat type and priorities
 *
 */
public enum SeatType {

    window(1), center(0), aisle(2);

    private Integer priority;

    SeatType(Integer priority) {
        this.priority = priority;
    }

    /**
     * @return the priority
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

}