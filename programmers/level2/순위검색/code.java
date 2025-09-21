package programmers.level2.순위검색;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class code {
    public static void main(String[] args) {
        String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};

        Solution solution = new Solution();
        int[] answer = solution.solution(info, query);

        System.out.println(Arrays.toString(answer)) ;
    }

    static class Solution{
        private Map<String, List<Integer>> infoMap;
        
        public int[] solution(String[] info, String[] query) {
            initInfoMap();
            preprocessInfo(info);
            
            List<Integer> result = new ArrayList<>();

            for(String q: query){
                String[] qInfos = preprocessQuery(q);

                List<Integer> scores = infoMap.get(qInfos[0]);

                result.add(calcCountThanTarget(scores, Integer.parseInt(qInfos[1])));
            }
            return result.stream().mapToInt(Integer::intValue).toArray();   
        }
        
        private void initInfoMap(){
            infoMap = new HashMap<>();

            String[] languages = { "-", "java", "cpp", "python"};
            String[] jobs = {"-", "backend", "frontend"};
            String[] careers = {"-", "junior", "senior"};
            String[] soulFoods = {"-", "chicken", "pizza"};

            for(String language: languages){
                for(String job: jobs){
                    for(String career: careers){
                        for(String soulFood: soulFoods){
                            infoMap.put(language + job + career + soulFood, new ArrayList<>());
                        }
                    }
                }
            }
        }

        private void preprocessInfo(String[] infos){            
            String[] languages = {"-", "java"};
            String[] jobs = {"-", "backend"};
            String[] careers = {"-", "junior"};
            String[] soulFoods = {"-", "chicken"};

            for(int i = 0 ; i < infos.length; i++){                
                String[] infoArr = infos[i].split(" ");
                
                languages[1] = infoArr[0];
                jobs[1] = infoArr[1];
                careers[1] = infoArr[2];
                soulFoods[1] = infoArr[3];

                for(String language: languages){
                    for(String job: jobs){
                        for(String career: careers){
                            for(String soulFood: soulFoods){
                                infoMap.get(language + job + career + soulFood).add(Integer.parseInt(infoArr[4]));
                            }
                        }
                    }
                }
            }

            // 정렬
            for(Map.Entry<String, List<Integer>> entry: infoMap.entrySet()){
                Collections.sort(entry.getValue());
            }
        }
        
        private String[] preprocessQuery(String query){
            String[] queryArr = query.split(" ");
            
            String queryString = "";
            for(int i = 0; i < 7; i += 2){
                queryString += queryArr[i];
            }
            
            String[] result =  {queryString, queryArr[7]};
            return result;
        }

        private int calcCountThanTarget(List<Integer> scores, int target){
            return scores.size() - lower_bound(scores, target);
        }

        private int lower_bound(List<Integer> scores, int target){    
            int start = 0;
            int end = scores.size() - 1;

            while(start < end){
                int mid = (start + end) / 2;

                if(scores.get(mid) < target) start = mid + 1;
                else end = mid;
            }

            return start;
        }
    }
}
