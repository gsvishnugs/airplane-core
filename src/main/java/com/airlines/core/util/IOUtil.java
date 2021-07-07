package com.airlines.core.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.airlines.core.model.Input;

public class IOUtil {

    public static Input getInputValues() throws IOException {
        Input input = new Input();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));) {
            System.out.println("Input array: ");
            String line = bufferedReader.readLine();
            List<List<Integer>> seatMetadata = Stream.of(line.trim().replaceAll(" ", "").split("\\],")).map(i -> {
                return Stream.of(i.trim().replaceAll("[\\[|\\]| ]", "").split(","))
                        .map(String::trim)
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
            }).collect(Collectors.toList());
            input.setSeatMetadata(seatMetadata);

            System.out.println("Number of passengers: ");
            Integer numPassengers = Integer.parseInt(bufferedReader.readLine().trim());
            input.setNumberOfPassengers(numPassengers);

            System.out.println("Output cells contain result in the format <Seat Number>:<Seat type>:<Passenger ID>");
        }
        return input;
    }

    public static void printOutputValues(String output) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));) {
            bufferedWriter.write(output);
        }
    }
}
