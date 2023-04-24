public class RedBlackTree {

    public Node root; // элемент с которого начинаем работу

    // Добавление новых нод
    public boolean add(int value) {
        if (root != null) {
            boolean result = addNode(root, value);
            root = rebalnce(root);
            return result;
        } else {
            root = new Node();
            root.color = Color.BLACK;
            root.value = value;
            return true;
        }
    }

    // Добавление новых нод
    private boolean addNode(Node node, int value) {
        if (node.value == value) {
            return false; // ноды должны быть уникальными, если значение уже есть возвращаем false
        } else {
            if (node.value > value) {
                if (node.leftChild != null) {
                    // используется рекурсивные вызовы
                    boolean result = addNode(node.leftChild, value);
                    node.leftChild = rebalnce(node.leftChild);
                    return result;
                } else {
                    // если ноды не существует, то создаём ноду
                    node.leftChild = new Node();
                    node.leftChild.color = Color.RED;
                    node.leftChild.value = value;
                    return true;
                }
            } else {
                // для правой ноды
                if (node.rightChild != null) {
                    boolean result = addNode(node.rightChild, value);
                    node.rightChild = rebalnce(node.rightChild);
                    return result;
                } else {
                    node.rightChild = new Node();
                    node.rightChild.color = Color.RED;
                    node.rightChild.value = value;
                    return true;
                }
            }
        }
    }

    // ребалансировкa
    private Node rebalnce(Node node) {
        Node result = node;
        boolean needRebalance;
        do {
            needRebalance = false;
            // Если правый красный, а левый чёрный, то правый поворот
            if (result.rightChild != null && result.rightChild.color == Color.RED &&
                    (result.leftChild == null || result.leftChild.color == Color.BLACK)) {
                needRebalance = true;
                result = rightSwap(result);
            }
            // Если левый ребёнок красный и у него есть ещё свой ребёнок и он тоже красный,
            // левый поворот
            if (result.leftChild != null && result.leftChild.color == Color.RED &&
                    result.leftChild.leftChild == null || result.leftChild.leftChild.color == Color.RED) {
                needRebalance = true;
                result = leftSwap(result);
            }
            // Если и правый и левый имеют красный цвет, производим смену цвета
            if (result.leftChild != null && result.leftChild.color == Color.RED &&
                    result.rightChild != null && result.rightChild.color == Color.RED) {
                needRebalance = true;
                result = leftSwap(result);
            }
        } while (needRebalance);
        return result;
    }

    // Правостороний поворот
    private Node rightSwap(Node node) {
        Node rightChild = node.rightChild;
        Node betweenChild = rightChild.leftChild; // промежуточный элемент для обмена
        rightChild.leftChild = node;
        node.rightChild = betweenChild;
        rightChild.color = node.color;
        node.color = Color.RED;
        return rightChild;
    }

    // Левосторонний поворот
    private Node leftSwap(Node node) {
        Node leftChild = node.leftChild;
        Node betweenChild = leftChild.rightChild;
        leftChild.rightChild = node;
        node.leftChild = betweenChild;
        leftChild.color = node.color;
        node.color = Color.RED;
        return leftChild;
    }

    // сменa цвета ноды, дети становятся чёрными, а сама ноды становится красной
    private void colorSwap(Node node) {
        node.rightChild.color = Color.BLACK;
        node.leftChild.color = Color.BLACK;
        node.color = Color.RED;
    }

    // печать бинарного дерева
    // public void printTree(Node node, String prefix, boolean last) {
    //     if (node == null) {
    //         return;
    //     }

    //     System.out.print(prefix);
    //     System.out.print(last ? "├──" : "└──");
    //     System.out.print(node.color == Color.RED ? ANSI_RED : ANSI_BLACK);
    //     System.out.print(node.value);
    //     System.out.println(ANSI_RESET);

    //     printTree(node.leftChild, prefix + (last ? "│ " : " "), true);
    //     printTree(node.rightChild, prefix + (last ? "│ " : " "), false);
    // }

    // private static final String ANSI_RED = "\u001B[31m";
    // private static final String ANSI_BLACK = "\u001B[30m";
    // private static final String ANSI_RESET = "\u001B[0m";

    private class Node {

        private int value;
        private Color color;
        private Node leftChild;
        private Node rightChild;

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", color=" + color +
                    '}';
        }
    }

    private enum Color {
        RED, BLACK
    }

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();

        // tree.add(1);
        // tree.add(3);
        // tree.add(2);
        // tree.printTree(tree.root, "", false);
    }
}