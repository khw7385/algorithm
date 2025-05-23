    package samsung;

    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.StringTokenizer;

    public class SS1208 {
        private static int[] counts;

        public static void main(String[] args)throws IOException{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            for(int i = 0; i < 10; i++){
                int dumpNum = Integer.parseInt(br.readLine());

                counts = new int[101];
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < 100; j++){
                    counts[Integer.parseInt(st.nextToken())]++;
                }
                solve(i, dumpNum);
            }
        }

        private static void solve(int testIdx, int dumpNum){
            int high = 100;
            int low = 1;

            for(int i = 0 ; i < dumpNum; i++){
                while(counts[high] == 0) high--;
                while(counts[low] == 0) low++;
                if(high - low <= 1) break;
                
                counts[high]--;
                counts[high - 1]++;
                counts[low]--;
                counts[low + 1]++;
            }

            while (counts[high] == 0) high--;
            while (counts[low] == 0) low++;
            System.out.println(String.format("#%d %d", testIdx + 1, high - low));
        }

    }
