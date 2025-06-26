package programmers;

public class IntactingBuilding {
    public int solution(int[][] board, int[][] skill) {
        int[][] accSum = new int[board.length + 1][board[0].length + 1];
        
        for(int i = 0 ; i < skill.length; i++){
            int type = skill[i][0];
            int attackDir = (type == 1 ? -1 : 1);
            int r1 = skill[i][1], r2 = skill[i][3], c1 = skill[i][2], c2 = skill[i][4];
            int degree = skill[i][5];
            
            accSum[r1][c1] += degree * attackDir;
            accSum[r2 + 1][c2 + 1] += degree * attackDir;
            accSum[r1][c2 + 1] -= degree * attackDir;
            accSum[r2 + 1][c1] -= degree * attackDir;
        }
        
        for(int i = 0; i < accSum.length; i++){
            for(int j = 1; j < accSum[0].length; j++)
                accSum[i][j] += accSum[i][j - 1];
        }
        
        for(int i = 0; i < accSum[0].length; i++){
            for(int j = 1; j < accSum.length; j++){
                accSum[j][i] += accSum[j - 1][i];
            }
        }
        
        int result = 0;
        for(int i = 0 ; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] + accSum[i][j] > 0) result++;
            }
        }
        
        return result;
    }
}
