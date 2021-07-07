import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.junit.Assert;
import org.junit.Test;

import com.airlines.core.enums.SeatType;
import com.airlines.core.model.Input;
import com.airlines.core.model.Seat;
import com.airlines.core.model.SeatManagementProperties;
import com.airlines.core.util.SeatBookingUtil;

public class SeatBookingUtilTest {

    private static List<List<Integer>> mockSeatMetadata = Arrays.asList(Arrays.asList(3, 2), Arrays.asList(4, 3));

    /**
     * {@link com.airlines.core.util.SeatBookingUtil #buildSeatManagementProperties(List)}
     */
    @Test
    public void buildSeatManagementProperties() {

        SeatManagementProperties expectedProperties = buildMockSeatManagementProperties();

        SeatManagementProperties actualProperties = SeatBookingUtil.buildSeatManagementProperties(mockSeatMetadata);

        Assert.assertEquals(expectedProperties.getBreadthWiseSeatTypes(), actualProperties.getBreadthWiseSeatTypes());
        Assert.assertEquals(expectedProperties.getSeatBatchMaxRowMap(), actualProperties.getSeatBatchMaxRowMap());
        Assert.assertEquals(expectedProperties.getMaxRowLength(), actualProperties.getMaxRowLength());

    }

    /**
     * {@link com.airlines.core.util.SeatBookingUtil #pioritize(SeatManagementProperties)}
     */
    @Test
    public void pioritize() {
        PriorityQueue<Seat> expectedSeatingQueue = buildMockpriorityQueue();
        SeatManagementProperties expectedProperties = buildMockSeatManagementProperties();
        PriorityQueue<Seat> acutalSeatingQueue = SeatBookingUtil.pioritize(expectedProperties);
        Assert.assertEquals(expectedSeatingQueue.toString(), acutalSeatingQueue.toString());
    }

    /**
     * {@link com.airlines.core.util.SeatBookingUtil #allocate(Input, PriorityQueue)}
     */
    @Test
    public void allocate() {
        Map<Integer, Seat> expectedSeatNumberSeatMap = buildMockSeatNumberSeatMap();
        Input input = buildMockInput();
        PriorityQueue<Seat> expectedSeatingQueue = buildMockpriorityQueue();
        Map<Integer, Seat> actualSeatNumberSeatMap = SeatBookingUtil.allocate(input, expectedSeatingQueue);
        Assert.assertEquals(expectedSeatNumberSeatMap.toString(), actualSeatNumberSeatMap.toString());
    }

    /**
     * {@link com.airlines.core.util.SeatBookingUtil #beautifyOutput(SeatManagementProperties, Map, int)}
     */
    @Test
    public void beautifyOutput() {
        String expectedOutput = "       1:window:6        2:center:        3:aisle:1        4:aisle:2        5:center:        6:center:       7:window:7\n"
                + "       8:window:8        9:center:       10:aisle:3       11:aisle:4       12:center:       13:center:      14:window:9\n"
                + "                                                          15:aisle:5       16:center:       17:center:     18:window:10\n"
                + "";
        Map<Integer, Seat> expectedSeatNumberSeatMap = buildMockSeatNumberSeatMap();
        SeatManagementProperties expectedProperties = buildMockSeatManagementProperties();
        String actualOutput = SeatBookingUtil.beautifyOutput(expectedProperties, expectedSeatNumberSeatMap,
                SeatBookingUtil.getCellLengthForBeautification(expectedSeatNumberSeatMap));
        Assert.assertEquals(expectedOutput, actualOutput);
    }

    private static Map<Integer, Seat> buildMockSeatNumberSeatMap() {
        Map<Integer, Seat> seatNumberSeatMap = new HashMap<Integer, Seat>();
        seatNumberSeatMap.put(1, new Seat(1, SeatType.window, 6));
        seatNumberSeatMap.put(2, new Seat(2, SeatType.center, 0));
        seatNumberSeatMap.put(3, new Seat(3, SeatType.aisle, 1));

        seatNumberSeatMap.put(4, new Seat(4, SeatType.aisle, 2));
        seatNumberSeatMap.put(5, new Seat(5, SeatType.center, 0));
        seatNumberSeatMap.put(6, new Seat(6, SeatType.center, 0));
        seatNumberSeatMap.put(7, new Seat(7, SeatType.window, 7));

        seatNumberSeatMap.put(8, new Seat(8, SeatType.window, 8));
        seatNumberSeatMap.put(9, new Seat(9, SeatType.center, 0));
        seatNumberSeatMap.put(10, new Seat(10, SeatType.aisle, 3));

        seatNumberSeatMap.put(11, new Seat(11, SeatType.aisle, 4));
        seatNumberSeatMap.put(12, new Seat(12, SeatType.center, 0));
        seatNumberSeatMap.put(13, new Seat(13, SeatType.center, 0));
        seatNumberSeatMap.put(14, new Seat(14, SeatType.window, 9));

        seatNumberSeatMap.put(15, new Seat(15, SeatType.aisle, 5));
        seatNumberSeatMap.put(16, new Seat(16, SeatType.center, 0));
        seatNumberSeatMap.put(17, new Seat(17, SeatType.center, 0));
        seatNumberSeatMap.put(18, new Seat(18, SeatType.window, 10));
        return seatNumberSeatMap;
    }

    private static Input buildMockInput() {
        Input input = new Input();
        input.setNumberOfPassengers(10);
        input.setSeatMetadata(mockSeatMetadata);
        return input;
    }

    private static PriorityQueue<Seat> buildMockpriorityQueue() {
        PriorityQueue<Seat> seatingQueue = new PriorityQueue<Seat>();
        seatingQueue.add(new Seat(1, SeatType.window));
        seatingQueue.add(new Seat(2, SeatType.center));
        seatingQueue.add(new Seat(3, SeatType.aisle));

        seatingQueue.add(new Seat(4, SeatType.aisle));
        seatingQueue.add(new Seat(5, SeatType.center));
        seatingQueue.add(new Seat(6, SeatType.center));
        seatingQueue.add(new Seat(7, SeatType.window));

        seatingQueue.add(new Seat(8, SeatType.window));
        seatingQueue.add(new Seat(9, SeatType.center));
        seatingQueue.add(new Seat(10, SeatType.aisle));

        seatingQueue.add(new Seat(11, SeatType.aisle));
        seatingQueue.add(new Seat(12, SeatType.center));
        seatingQueue.add(new Seat(13, SeatType.center));
        seatingQueue.add(new Seat(14, SeatType.window));

        seatingQueue.add(new Seat(15, SeatType.aisle));
        seatingQueue.add(new Seat(16, SeatType.center));
        seatingQueue.add(new Seat(17, SeatType.center));
        seatingQueue.add(new Seat(18, SeatType.window));
        return seatingQueue;
    }

    private static SeatManagementProperties buildMockSeatManagementProperties() {
        SeatManagementProperties properties = new SeatManagementProperties();

        properties.setBreadthWiseSeatTypes(Arrays.asList(SeatType.window, SeatType.center, SeatType.aisle,
                SeatType.aisle, SeatType.center, SeatType.center, SeatType.window));

        properties.setMaxRowLength(3);

        Map<Integer, Integer> seatBatchMaxRowMap = new HashMap<Integer, Integer>();
        seatBatchMaxRowMap.put(0, 2);
        seatBatchMaxRowMap.put(1, 2);
        seatBatchMaxRowMap.put(2, 2);
        seatBatchMaxRowMap.put(3, 3);
        seatBatchMaxRowMap.put(4, 3);
        seatBatchMaxRowMap.put(5, 3);
        seatBatchMaxRowMap.put(6, 3);
        properties.setSeatBatchMaxRowMap(seatBatchMaxRowMap);
        return properties;
    }
}
