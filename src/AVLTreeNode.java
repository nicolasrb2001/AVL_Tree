class AVLTreeNode {
    public int element;

    // You may use any of the variable below to keep track of balance or height for re-balancing:
    public int height;
    public int balance;

    public AVLTreeNode left = null;
    public AVLTreeNode right = null;

    AVLTreeNode(int e) {
        element = e;
        height = 1;
        balance = 0;
    }

    public int getRightHeight() {
        if (right == null) {
            return 0;
        } else {
            return right.height;
        }
    }

    public int getLeftHeight() {
        if (left == null) {
            return 0;
        } else {
            return left.height;
        }
    }

    public int getBalance() {
        return getRightHeight() - getLeftHeight();
    }

    public int getChildHeight() {
        return Math.max(getRightHeight(), getLeftHeight());
    }

}
