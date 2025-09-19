import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class PGMenuRenewal{
    public static void main(String[] args) {
        Solution solution = new Solution();

        String[] orders = {"XYZ", "XWY", "WXA"};
        int[] course = {2,3,4};

        String[] answer = solution.solution(
            orders,
            course
        );
        System.out.println(Arrays.toString(answer));
    }

    private static class Solution{
        private HashMap<String, Integer> courseMap = new HashMap<>();
        private Set<Integer> courseLength;

        public String[] solution(String[] orders, int[] course) {
            courseLength = Arrays.stream(course)
                                .boxed()
                                .collect(Collectors.toSet());

            for(int i = 0; i < orders.length; i++){
                char[] orderCharArray = orders[i].toCharArray();
                Arrays.sort(orderCharArray);
                String order = new String(orderCharArray);
                calcComb(order, 0, "");
            }

            return findCombThan2();
        }  

        private void calcComb(String order, int idx, String orderComb){
            if(idx == order.length()){
                if(!courseLength.contains(orderComb.length())) return;
                courseMap.merge(orderComb, 1, Integer::sum);
                return;
            }
            calcComb(order, idx + 1, orderComb);
            calcComb(order, idx + 1, orderComb + order.charAt(idx));
        }

        private String[] findCombThan2(){
            int[] maxArr = new int[11];
            Map<Integer, List<String>> map = new HashMap<>();
            for(Integer num: courseLength){
                map.put(num, new ArrayList<>());
            }

            courseMap.entrySet()
                .stream()
                .filter(entry -> entry.getValue() >= 2)
                .forEach(entry -> {
                    String key = entry.getKey();
                    int value = entry.getValue();
                    int idx = key.length();

                    if (maxArr[idx] == value) map.get(idx).add(key);
                    else if(value > maxArr[idx]){
                        maxArr[idx] = value;
                        map.get(idx).clear();
                        map.get(idx).add(key);
                    }
                });    
            return map.entrySet()
                .stream()
                .flatMap(entry -> entry.getValue().stream())
                .sorted()
                .toArray(String[]::new);
        }
    }
}