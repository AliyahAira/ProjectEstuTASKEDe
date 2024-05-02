class AVLNode {
    Task task;
    AVLNode left;
    AVLNode right;
    int height;

    public AVLNode(Task task) {
        this.task = task;
        this.left = null;
        this.right = null;
        this.height = 1;
    }
}