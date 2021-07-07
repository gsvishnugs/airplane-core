package com.airlines.core;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import com.airlines.core.model.Input;
import com.airlines.core.model.Seat;
import com.airlines.core.model.SeatManagementProperties;
import com.airlines.core.util.IOUtil;
import com.airlines.core.util.SeatBookingUtil;

public class App {

    public static void main(String[] args) throws IOException {

        Input input = IOUtil.getInputValues();

        List<List<Integer>> seatMetadata = input.getSeatMetadata();

        SeatManagementProperties properties = SeatBookingUtil.buildSeatManagementProperties(seatMetadata);

        PriorityQueue<Seat> seatingQueue = SeatBookingUtil.pioritize(properties);

        Map<Integer, Seat> seatNumberSeatMap = SeatBookingUtil.allocate(input, seatingQueue);

        int maxCellLength = SeatBookingUtil.getCellLengthForBeautification(seatNumberSeatMap);

        String beautifiedOutput = SeatBookingUtil.beautifyOutput(properties, seatNumberSeatMap, maxCellLength);

        IOUtil.printOutputValues(beautifiedOutput);

    }
}
