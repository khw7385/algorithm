package baekjoon_java.소셜네트워킹어플리케이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Arrays;

public class Main{
    private static int testNum;
    private static TestInput[] testInputs; 
    private static TestResult[] testResults;

    public static void main(String[] args) {
        readInput();

        for(int i = 0; i < testNum; i++){
            solve(i, testInputs[i]);
            print(i, testResults[i]);
        }
    }
    
    private static void readInput(){

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            testNum = Integer.parseInt(br.readLine());

            testInputs = new TestInput[testNum];
            testResults = new TestResult[testNum];
            
            for(int i = 0 ; i < testNum; i++){
                int userNum = Integer.parseInt(br.readLine());

                int relationNum = Integer.parseInt(br.readLine());

                int[][] relations = new int[relationNum][2];
    
                for(int j = 0; j < relationNum; j++){
                    String[] relationInputs = br.readLine().split(" ");
    
                    relations[j][0] = Integer.parseInt(relationInputs[0]);
                    relations[j][1] = Integer.parseInt(relationInputs[1]);
                }
    
                int targetNum = Integer.parseInt(br.readLine());
                int[][] targets = new int[targetNum][2];
                
                for(int j = 0 ; j < targetNum; j++){
                    String[] targetInput = br.readLine().split(" ");
                    targets[j][0] = Integer.parseInt(targetInput[0]);
                    targets[j][1] = Integer.parseInt(targetInput[1]);
                }
                testInputs[i] = new TestInput(userNum, relationNum, relations, targetNum, targets);
            }

        }catch(IOException e){
            System.out.println("입력 처리 중 에러 발생!");
        }
    }

    private static void solve(int testIdx, TestInput testInput){
        // union-find 
        int[] roots = new int[testInput.userNum];
        Arrays.fill(roots, -1);

        for(int i = 0 ; i < testInput.relationNum; i++){
            int[] relation = testInput.relations[i];
            union(roots, relation[0], relation[1]);
        }

        int[] results = new int[testInput.targetNum];

        for(int i = 0; i < testInput.targetNum; i++){
            int[] target = testInput.targets[i];

            if(find(roots, target[0]) == find(roots, target[1])) results[i] = 1;
            else results[i] = 0;
        }

        testResults[testIdx] = new TestResult(results);
    }

    private static int find(int[] roots, int user){
        if(roots[user] < 0) return user;
        return roots[user] = find(roots, roots[user]);
    }

    private static void union(int[] roots, int user1, int user2){
        int root1 = find(roots, user1);
        int root2 = find(roots, user2);

        if(root1 == root2) return;

        int rank1 = roots[root1];
        int rank2 = roots[root2];

        if(rank1 > rank2){
            int temp = root1;
            root1 = root2;
            root2 = temp;
        }

        if(rank1 == rank2) roots[root1]--;

        roots[root2] = root1;
    }


    private static void print(int testIdx, TestResult testResult){
        System.out.println(String.format("Scenario %d", testIdx));

        Arrays.stream(testResult.results)
            .forEach(result -> System.out.println(result));

        System.out.println("");
    }
    

    static class TestInput{
        int userNum;
        int relationNum;
        int targetNum;
        int[][] relations;
        int[][] targets;

        public TestInput(int userNum, int relationNum, int[][] relations, int targetNum, int[][] targets) {
            this.userNum = userNum;
            this.relationNum = relationNum;
            this.targetNum = targetNum;

            this.relations = new int[relationNum][2];
            this.targets = new int[targetNum][2];

            for(int i = 0; i < relationNum; i++){
                this.relations[i] = Arrays.copyOf(relations[i], 2);
            }

            for(int i = 0 ; i <  targetNum; i++){
                this.targets[i] = Arrays.copyOf(targets[i], 2);
            }
        }
    }

    static class TestResult{
        int[] results;

        public TestResult(int[] results){
            this.results = Arrays.copyOf(results, results.length);
        }
    }

}