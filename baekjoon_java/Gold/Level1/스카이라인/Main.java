package baekjoon_java.Gold.Level1.스카이라인;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        List<Coordinate> coordinates = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());

            coordinates.add(new Coordinate(x1, h, 1));
            coordinates.add(new Coordinate(x2, h, -1));
        }

        Collections.sort(coordinates);

        TreeMap<Integer, Integer> map = new TreeMap<>(Collections.reverseOrder());
        map.put(0, 1);

        StringBuilder sb = new StringBuilder();
        int prevMaxHeight = 0;

        for (Coordinate coord : coordinates) {
            if (coord.type == 1) {
                map.put(coord.h, map.getOrDefault(coord.h, 0) + 1);
            } else {
                int count = map.get(coord.h);
                if (count == 1)
                    map.remove(coord.h);
                else
                    map.put(coord.h, count - 1);
            }

            int currentMaxHeight = map.firstKey();

            if (prevMaxHeight != currentMaxHeight) {
                sb.append(coord.x).append(" ").append(currentMaxHeight).append(" ");
                prevMaxHeight = currentMaxHeight;
            }
        }

        System.out.println(sb.toString().trim());
    }

    static class Coordinate implements Comparable<Coordinate> {
        int x, h, type;

        Coordinate(int x, int h, int type) {
            this.x = x;
            this.h = h;
            this.type = type;
        }

        @Override
        public int compareTo(Coordinate o) {
            if (this.x != o.x)
                return this.x - o.x;
            if (this.type != o.type)
                return o.type - this.type;
            if (this.type == 1)
                return o.h - this.h;
            return this.h - o.h;
        }
    }
}
