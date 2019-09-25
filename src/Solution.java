import java.util.Arrays;

class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums =  {1, 5, 9, 11, 34};
        int target = 12;
        int[] flag = solution.twoSum(nums,target);
        System.out.println(Arrays.toString(flag));

    }

    public int[] twoSum(int[] nums, int target) {


        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[] { i, j };
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

}

