import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        final char[] charArray = "BINGO".toCharArray();
        final int[] intArray = {1,15,30,45,60,75};

        final String[] tempFourthStreamArray =
                {"46", "47", "48", "49", "50", "51", "52", "53", "54",
                        "55", "56", "57", "58", "59", "60"};

        List<Integer> tempFifthStreamList = List.of(
                61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71,
                72, 73, 74, 75);

        var firstStream = Stream.iterate(intArray[0], n -> n + 1)
                .limit(15)
                .map(n -> charArray[0] + "" + n + " ");

        var secondStream = Stream.iterate(intArray[1] + 1, n -> n <=  intArray[2], n -> n + 1)
                .map(n -> charArray[1] + "" + n + " ");

        var thirdStream = Stream.of(31, 32, 33 ,34, 35, 36,
                37, 38, 39, 40, 41, 42, 43, 44, 45)
                        .map(n -> charArray[2] + "" + n + " ");

        var fourthStream = Arrays.stream(tempFourthStreamArray)
                .map(item -> charArray[3] + "" + item + " ");

        var fifthStream = tempFifthStreamList.stream()
                .map(n -> charArray[4] + "" + n + " ");

        var concatStream1 = Stream.concat(firstStream, secondStream);
        var concatStream2 = Stream.concat(thirdStream, fourthStream);
        var concatStream3 = Stream.concat(concatStream1, concatStream2);
        var concatStream4 = Stream.concat(concatStream3, fifthStream);

        concatStream4.forEach(System.out::print);
    }
}