public class Main {
    
        public static void main(String[] args) {

            NodeList list = new NodeList();

            list.addNode(4);
            list.addNode(8);
            list.addNode(12);
            list.addNode(16);

            list.reverseList();

            list.printList();
        }
    
}
