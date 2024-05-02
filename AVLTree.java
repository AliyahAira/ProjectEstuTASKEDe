import java.util.*;

public class AVLTree {
    private AVLNode root;

    private int height(AVLNode node) {
        if (node == null)
            return 0;
        return node.height;
    }

    private int getBalance(AVLNode node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    private AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    private AVLNode insert(AVLNode node, Task task) {
        if (node == null)
            return new AVLNode(task);

        if (task.name.compareTo(node.task.name) < 0)
            node.left = insert(node.left, task);
        else if (task.name.compareTo(node.task.name) > 0)
            node.right = insert(node.right, task);
        else
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && task.name.compareTo(node.left.task.name) < 0)
            return rightRotate(node);

        if (balance < -1 && task.name.compareTo(node.right.task.name) > 0)
            return leftRotate(node);

        if (balance > 1 && task.name.compareTo(node.left.task.name) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && task.name.compareTo(node.right.task.name) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    public void addTask(Task task) {
        root = insert(root, task);
    }

    private void inorderTraversal(AVLNode root) {
        if (root != null) {
            inorderTraversal(root.left);
            System.out.println(root.task);
            inorderTraversal(root.right);
        }
    }

    public void displayTasks() {
        System.out.println("\nTasks in the AVL tree:");
        inorderTraversal(root);
    }

    public void updateTaskProgress(String taskName, String progress) {
        updateTaskProgress(root, taskName, progress);
    }

    private void updateTaskProgress(AVLNode root, String taskName, String progress) {
        if (root == null)
            return;

        if (taskName.compareTo(root.task.name) < 0)
            updateTaskProgress(root.left, taskName, progress);
        else if (taskName.compareTo(root.task.name) > 0)
            updateTaskProgress(root.right, taskName, progress);
        else {
            root.task.setProgress(progress);
            System.out.println("Progress updated for task: " + root.task.name);
        }
    }

    public void sortTasksByDeadline() {
        System.out.println("\nTasks sorted by deadline:");
        sortTasksByDeadline(root);
    }

    private void sortTasksByDeadline(AVLNode root) {
        if (root != null) {
            sortTasksByDeadline(root.left);
            System.out.println(root.task.name + " - Deadline: " + root.task.deadline);
            sortTasksByDeadline(root.right);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AVLTree tree = new AVLTree();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Task");
            System.out.println("2. Update Task Progress");
            System.out.println("3. Sort Tasks by Deadline");
            System.out.println("4. Display Tasks");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter your activity: ");
                    String name = scanner.nextLine();
                    System.out.print("Difficulty (Easy, Medium, Hard): ");
                    String difficulty = scanner.nextLine();
                    System.out.print("Deadline (1-29 for days, 30 for a month): ");
                    int deadline = scanner.nextInt();
                    scanner.nextLine();
                    Task task = new Task(name, difficulty, deadline);
                    tree.addTask(task);
                    break;

                case 2:
                    System.out.print("Enter the task to record progress: ");
                    String taskName = scanner.nextLine();
                    System.out.print("Progress: ");
                    String progress = scanner.nextLine();
                    tree.updateTaskProgress(taskName, progress);
                    break;

                case 3:
                    tree.sortTasksByDeadline();
                    break;

                case 4:
                    tree.displayTasks();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        }
    }
}