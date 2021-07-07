package com.airlines.core.model;

import java.util.List;
import java.util.Map;

import com.airlines.core.enums.SeatType;

public class SeatManagementProperties {

    private int maxRowLength;

    private List<SeatType> breadthWiseSeatTypes;

    private Map<Integer, Integer> seatBatchMaxRowMap;

    /**
     * @return the maxRowLength
     */
    public int getMaxRowLength() {
        return maxRowLength;
    }

    /**
     * @return the breadthWiseSeatTypes
     */
    public List<SeatType> getBreadthWiseSeatTypes() {
        return breadthWiseSeatTypes;
    }

    /**
     * @return the seatBatchMaxRowMap
     */
    public Map<Integer, Integer> getSeatBatchMaxRowMap() {
        return seatBatchMaxRowMap;
    }

    /**
     * @param maxRowLength the maxRowLength to set
     */
    public void setMaxRowLength(int maxRowLength) {
        this.maxRowLength = maxRowLength;
    }

    /**
     * @param breadthWiseSeatTypes the breadthWiseSeatTypes to set
     */
    public void setBreadthWiseSeatTypes(List<SeatType> breadthWiseSeatTypes) {
        this.breadthWiseSeatTypes = breadthWiseSeatTypes;
    }

    /**
     * @param seatBatchMaxRowMap the seatBatchMaxRowMap to set
     */
    public void setSeatBatchMaxRowMap(Map<Integer, Integer> seatBatchMaxRowMap) {
        this.seatBatchMaxRowMap = seatBatchMaxRowMap;
    }

}
