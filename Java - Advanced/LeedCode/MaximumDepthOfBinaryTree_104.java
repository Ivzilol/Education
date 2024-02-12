public class MaximumDepthOfBinaryTree_104 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        MaximumDepthOfBinaryTree_104 cl = new MaximumDepthOfBinaryTree_104();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        root.right.right.right = new TreeNode(8);
        cl.maxDepth(root);
    }


    public int maxDepth(TreeNode root) {
        int result;
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        result = Math.max(leftDepth, rightDepth) + 1;
        System.out.println(result);
        return result;
    }
}
