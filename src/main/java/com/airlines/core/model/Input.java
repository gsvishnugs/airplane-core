package com.airlines.core.model;

import java.util.List;

/**
 * Input class holds the requested seat metadata and passenger details read from
 * input source.
 *
 */
public class Input {

    private List<List<Integer>> seatMetadata;

    private Integer numberOfPassengers;

    /**
     * @return the seatMetadata
     */
    public List<List<Integer>> getSeatMetadata() {
        return seatMetadata;
    }

    /**
     * @return the numberOfPassengers
     */
    public Integer getNumberOfPassengers() {
        return numberOfPassengers;
    }

    /**
     * @param seatMetadata the seatMetadata to set
     */
    public void setSeatMetadata(List<List<Integer>> seatMetadata) {
        this.seatMetadata = seatMetadata;
    }

    /**
     * @param numberOfPassengers the numberOfPassengers to set
     */
    public void setNumberOfPassengers(Integer numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

}
