package com.airlines.core.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import com.airlines.core.enums.SeatType;
import com.airlines.core.model.Input;
import com.airlines.core.model.Seat;
import com.airlines.core.model.SeatManagementProperties;

/**
 * Functions that helps prioritized seat booking
 *
 */
public class SeatBookingUtil {

    public static int SPACER_LENGTH = 5;

    /**
     * Extract properties that would enhance prioritization and iterating over seats
     * 
     * @param seatMetadata
     * @return
     */
    public static SeatManagementProperties buildSeatManagementProperties(List<List<Integer>> seatMetadata) {
        List<SeatType> breadthWiseSeatTypes = new ArrayList<SeatType>();
        Map<Integer, Integer> seatBatchMaxRowMap = new HashMap<Integer, Integer>();
        int breadth = 0;
        for (List<Integer> seatGroupMetadata : seatMetadata) {
            for (int rowIdx = 0; rowIdx < seatGroupMetadata.get(0); rowIdx++) {
                if (rowIdx == 0 || rowIdx == seatGroupMetadata.get(0) - 1) {
                    breadthWiseSeatTypes.add(SeatType.aisle);
                } else {
                    breadthWiseSeatTypes.add(SeatType.center);
                }
                seatBatchMaxRowMap.put(breadth++, seatGroupMetadata.get(1));
            }
        }
        breadthWiseSeatTypes.set(0, SeatType.window);
        breadthWiseSeatTypes.set(breadthWiseSeatTypes.size() - 1, SeatType.window);

        int maxRowLength = 0;
        for (List<Integer> seatGroupMetadata : seatMetadata) {
            maxRowLength = maxRowLength < seatGroupMetadata.get(0) ? seatGroupMetadata.get(1) : maxRowLength;
        }

        SeatManagementProperties properties = new SeatManagementProperties();
        properties.setBreadthWiseSeatTypes(breadthWiseSeatTypes);
        properties.setSeatBatchMaxRowMap(seatBatchMaxRowMap);
        properties.setMaxRowLength(maxRowLength);
        return properties;
    }

    /**
     * Prioritize seats by seat type. Aisle takes precedence over Window which in
     * turn takes precedence over Center seats.
     * 
     * @param properties
     * @return
     */
    public static PriorityQueue<Seat> pioritize(SeatManagementProperties properties) {
        PriorityQueue<Seat> seatingQueue = new PriorityQueue<Seat>();
        int seatNumber = 1;
        for (int rowIdx = 0; rowIdx < properties.getMaxRowLength(); rowIdx++) {
            for (int colIdx = 0; colIdx < properties.getBreadthWiseSeatTypes().size(); colIdx++) {
                if (properties.getSeatBatchMaxRowMap().get(colIdx) > rowIdx) {
                    Seat seat = new Seat(seatNumber++, properties.getBreadthWiseSeatTypes().get(colIdx));
                    seatingQueue.add(seat);
                }
            }
        }
        return seatingQueue;
    }

    /**
     * Mark seats that are allocated to passengers and add all seats to a map of
     * seatNumber -> Seat for ease of output beautification.
     * 
     * @param input
     * @param seatingQueue
     * @return
     */
    public static Map<Integer, Seat> allocate(Input input, PriorityQueue<Seat> seatingQueue) {
        Map<Integer, Seat> seatNumberSeatMap = new HashMap<Integer, Seat>();
        int numPassengers = input.getNumberOfPassengers();
        for (int i = 0; i < numPassengers; i++) {
            Seat allocatedSeat = seatingQueue.poll();
            if (allocatedSeat != null) {
                // Allocate seat
                allocatedSeat.setPassengerId(i + 1);
                seatNumberSeatMap.put(Integer.valueOf(allocatedSeat.getNumber()), allocatedSeat);
            }
        }
        // Dump all unallocated seats
        while (seatingQueue.peek() != null) {
            Seat unallocatedSeat = seatingQueue.poll();
            seatNumberSeatMap.put(Integer.valueOf(unallocatedSeat.getNumber()), unallocatedSeat);
        }
        return seatNumberSeatMap;
    }

    /**
     * Find the largest sized cell in the output
     * 
     * @param seatNumberSeatMap
     * @return
     */
    public static int getCellLengthForBeautification(Map<Integer, Seat> seatNumberSeatMap) {
        return seatNumberSeatMap.values().stream().map(value -> value.toString().length()).max(Integer::compare).get()
                + SPACER_LENGTH;
    }

    /**
     * Builds the output string
     * 
     * @param properties
     * @param seatNumberSeatMap
     * @param maxCellLength
     * @return
     */
    public static String beautifyOutput(SeatManagementProperties properties, Map<Integer, Seat> seatNumberSeatMap,
            int maxCellLength) {
        StringBuffer sb = new StringBuffer();
        int seatNumber = 1;
        for (int rowIdx = 0; rowIdx < properties.getMaxRowLength(); rowIdx++) {
            for (int colIdx = 0; colIdx < properties.getBreadthWiseSeatTypes().size(); colIdx++) {
                if (properties.getSeatBatchMaxRowMap().get(colIdx) > rowIdx) {
                    Seat printableSeat = seatNumberSeatMap.get(seatNumber++);
                    sb.append(String.format("%1$" + maxCellLength + "s", printableSeat));
                } else {
                    sb.append(String.format("%1$" + maxCellLength + "s", ""));
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
