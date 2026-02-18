package baekjoon_java.Gold.Level3.텀프로젝트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            int testCase = Integer.parseInt(br.readLine());

            for (int i = 0; i < testCase; i++) {
                int studentNum = Integer.parseInt(br.readLine());

                int[] studentChoices = new int[studentNum + 1];

                String studentChoicesStr = br.readLine();
                StringTokenizer st = new StringTokenizer(studentChoicesStr);

                for (int j = 1; j <= studentNum; j++) {
                    studentChoices[j] = Integer.parseInt(st.nextToken());
                }

                int result = solve(studentNum, studentChoices);
                System.out.println(result);
            }
        } catch (IOException e) {
        }
    }

    private static int[] state;

    private static int NOT_VISITED = 0;
    private static int VISITED = 1;
    private static int NOT_CYLCE = 2;
    private static int CYCLE = 3;

    private static int solve(int studentNum, int[] studentChoices) {
        state = new int[studentChoices.length + 1];

        for (int i = 1; i <= studentNum; i++) {
            if (state[i] == NOT_VISITED)
                run(i, studentChoices);
        }

        int result = 0;
        for (int i = 1; i < state.length; i++) {
            if (state[i] == NOT_CYLCE)
                result++;
        }

        return result;

    }

    private static void run(int student, int[] choices) {
        int cur = student;

        while (true) {
            state[cur] = VISITED;
            cur = choices[cur];

            if (state[cur] == NOT_CYLCE || state[cur] == CYCLE) {
                cur = student;

                while (state[cur] == VISITED) {
                    state[cur] = NOT_CYLCE;
                    cur = choices[cur];
                }

                return;
            }

            if (state[cur] == VISITED && cur != student) {
                while (state[cur] != CYCLE) {
                    state[cur] = CYCLE;
                    cur = choices[cur];
                }

                cur = student;

                while (state[cur] != CYCLE) {
                    state[cur] = NOT_CYLCE;
                    cur = choices[cur];
                }

                return;
            }

            if (state[cur] == VISITED && cur == student) {
                while (state[cur] != CYCLE) {
                    state[cur] = CYCLE;
                    cur = choices[cur];
                }
                return;
            }
        }
    }
}
