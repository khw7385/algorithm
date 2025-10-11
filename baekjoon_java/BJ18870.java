package baekjoon_java;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BJ18870 {
    private static int n;
    private static Integer[] arr;
    public static void main(String[] args) {
        init();
        String answer = solve();
        System.out.println(answer);
    }

    private static void init(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try{
            n = Integer.parseInt(br.readLine());

            arr = new Integer[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n ; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    private static String solve(){
        // 중복 제거
        HashSet<Integer> set = new HashSet<Integer>(Arrays.asList(arr));

        // 정렬
        List<Integer> sortList = new ArrayList<>(set);
        Collections.sort(sortList);

        // 값 - 인덱스 매핑
        HashMap<Integer, Integer> map = new HashMap<>();
        IntStream.range(0, sortList.size())
                .forEach(idx -> {
                    map.put(sortList.get(idx), idx);
                });
        
        return Arrays.asList(arr).stream()
                .map(v -> String.valueOf(map.get(v)))
                .collect(Collectors.joining(" "));
    }
}
