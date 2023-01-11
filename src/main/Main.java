package main;

import java.util.*;
import java.util.regex.Pattern;

import static main.DataHub.requestParam;

public class Main {
    public static void main(String[] args) {

        /**
         * expected result:
         * [0, 35, 90, 60, 15, 75, 95, 0, 0, 0, -15, 25, 105, -5]
         * ------------------------------------------------------
         * [0, 35, 90, 60, 15, 75, 95, 0, 0]
         */

        System.out.println(hCase02(requestParam));
    }

    private static final Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public static List<Integer> buildIntegerArray(String strNum) {
        int param = Integer.parseInt(strNum);

        List<Integer> result = new ArrayList<>();
        result.add(param);

        return result;
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }

    private static <T> List<List<T>> partition(List<T> input) {
        List<List<T>> lists = new ArrayList<>();
        for (int i = 0; i < input.size(); i += 3) {
            lists.add(input.subList(i, Math.min(input.size(), i + 3)));
        }
        System.out.println(lists);
        return lists;
    }

    private static List<Integer> hCase02(String[][] request) {
        List<Integer> response = new ArrayList<>();

        Arrays.stream(requestParam).forEach(item -> {
            List<Integer> collectedNumericItem =
                    Arrays.stream(item).toList().stream()
                            .filter(Objects::nonNull)
                            .filter(Main::isNumeric)
                            .map(Main::buildIntegerArray)
                            .flatMap(Collection::stream)
                            .toList();

            response.addAll(collectedNumericItem);
        });

        System.out.println(response);

        System.out.println("--------------------------------------------------------------");

        List<Integer> response2 = new ArrayList<>();
        partition(response).forEach(x -> {
            if (x.size() == 3) {
                Integer count = x.stream()
                        .reduce(0, Integer::sum);

                if (count > 90)
                    response2.addAll(x);
            }
        });

        return response2;
    }
}