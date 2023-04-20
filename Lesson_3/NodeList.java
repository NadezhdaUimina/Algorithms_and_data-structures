public class NodeList {
    private Node head;
    private Node tail;

    class Node {
        private int value;
        private Node nextNode;
        private Node previosNode;
    }

    // Добавление элемента в конец двухсвязного списка
    public void addNode(int value) {

        Node node = new Node();
        node.value = value;

        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.nextNode = node;
            node.previosNode = tail;
            tail = node;
        }
    }

    // разворот двухсвязного списка
    public void reverseList() {

        Node currentNode = head;

        while (currentNode != null) {
            Node next = currentNode.nextNode;
            Node previous = currentNode.previosNode;
            currentNode.previosNode = next;
            currentNode.nextNode = previous;

            if (previous == null) {
                tail = currentNode;
            }
            if (next == null) {
                head = currentNode;
            }
            currentNode = next;
        }
    }

    // вывод в консоль списк
    public void printList() {

        Node current = head;

        while (current != null) {
            System.out.println(current.value + " ");
            current = current.nextNode;
        }
    }
}
