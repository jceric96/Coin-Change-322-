import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class CoinChange {
    // dfs
    int min = Integer.MAX_VALUE;

    public int coinChangeI(int[] coins, int amount) {
        Arrays.sort(coins);
        if (helper(coins, amount, coins.length - 1, 0)) {
            return min;
        }
        return -1;
    }

    private boolean helper(int[] coins, int rest, int current, int temp) {
        if (rest == 0) {
            if (temp < min) {
                min = temp;
            }
            return true;
        }
        if (current < 0 || rest < 0)
            return false;
        if (temp >= min)
            return false;
        boolean result = false;
        for (int i = rest / coins[current]; i >= 0; i--) {
            if (helper(coins, rest - i * coins[current], current - 1, temp + i)) {
                result = true;
            }
        }
        return result;
    }

    // BFS
    public int coinChangeII(int[] coins, int amount) {
        if (amount == 0)
            return 0;
        Queue<Integer> queue = new LinkedList<>();
        // amount is from 1 and visited array from 0
        // if we want to get visited[amount]
        // we should set visited length as amount + 1
        boolean[] visited = new boolean[amount + 1];

        queue.offer(amount);
        visited[amount] = true;
        int currLevel = 1;

        while (!queue.isEmpty()) {
            System.out.println(queue);
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int current = queue.poll();
                for (int coin : coins) {
                    int rest = current - coin;
                    if (rest == 0) {
                        return currLevel;
                    } else if (rest > 0 && !visited[rest]) {
                        queue.add(rest);
                        visited[rest] = true;
                    }
                }
            }
            currLevel++;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] coins = new int[] { 1, 2, 5 };
        CoinChange cc = new CoinChange();
        System.out.println(cc.coinChangeII(coins, 11));
    }
}
