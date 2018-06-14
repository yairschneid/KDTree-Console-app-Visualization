import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class KDTree {
    public static  Node root;
    public KDTree(){
        this.root = null;
    }

    public boolean find(int[] id, int it){
        Node current = root;
        ArrayList<Node> route = new ArrayList<>();
        while(current!=null){
            if(CompareID(current.data, id)){
                route.add(current);
                System.out.println("the route to the node is: ");
                for (Node n: route){
                    PrintData(n);
                }
                return true;
            }else if(current.data[it]>id[it]){
                it = (it+1)%4;
                route.add(current);
                current = current.left;
            }else{
                it = (it+1)%4;
                route.add(current);
                current = current.right;
            }
        }
        return false;
    }

    private boolean CompareID(int[] data, int[] id) {
        for (int i=0;i<4; i++){
            if (data[i] != id[i])
                return false;
        }
        return true;
    }


    public void insert(int[] id, int node_number, int it){
        Node newNode = new Node(id, node_number);
        if(root==null){
            root = newNode;
            return;
        }
        Node current = root;
        Node parent = null;
        while(true){
            parent = current;
            if(id[it]<current.data[it]){
                it = (it+1)%4;
                current = current.left;
                if(current==null){
                    parent.left = newNode;
                    return;
                }
            }else{
                it = (it+1)%4;
                current = current.right;
                if(current==null){
                    parent.right = newNode;
                    return;
                }
            }
        }
    }

    public void searchRange(int[] min, int[] max, Node current){
        if (current != null){
            if (min[0] <= current.data[0] && max[0] >= current.data[0]){
                if (CompareRange(min, max, current) == true){
                    PrintData(current);
                    searchRange(min, max, current.left);
                    searchRange(min, max, current.right);
                }
                else{
                    searchRange(min, max, current.left);
                    searchRange(min, max, current.right);
                }
            }
            if(min[0] > current.data[0]){
                searchRange(min, max, current.right);
            }
            else if(max[0] < current.data[0]){
                searchRange(min, max, current.left);
            }
        }

    }

    private boolean CompareRange(int[] min, int[] max, Node current) {
        for (int i=0; i<4; i++){
            if (current.data[i] < min[i] || current.data[i] > max[i]){
                return false;
            }
        }
        return true;
    }

    public void display(Node root){
        if(root!=null){
            display(root.left);
            PrintData(root);
            display(root.right);
        }
    }

    public void PrintData(Node node){
        for (int i=0; i<4; i++){
            System.out.print(""+node.data[i]+", ");
        }
        System.out.println("  ");
    }

    public static void PrintData1(Node node){
        System.out.print(node.node_number);
    }

    public static void PrintData2(Node node){
        for (int i = 0; i < 4; i++){
            System.out.print(""+node.data[i]+", ");
        }
        System.out.println("");
    }

    public static void main(String arg[]){
        KDTree b = new KDTree();
        //b.insert(new int[]{4,3,2,1},0, 0);b.insert(new int[]{4,4,4,4},1,0);
        //b.insert(new int[]{1,2,3,4},2,0);b.insert(new int[]{1,1,1,1},3,0);b.insert(new int[]{2,2,2,2},4,0);
        //System.out.println("Original Tree : ");
        //b.display(b.root);
        //System.out.println("");
        //System.out.println("Check whether Node with value 2,2,2,2 exists : " + b.find(new int[]{1,1,1,1},0));
        //b.searchRange(new int[]{1,1,1,1}, new int[]{3,3,3,3}, root);
        //b.printNode(root);
        int node_number = 0;

        System.out.println("To insert Press 1, To search for a node Press 2, to search for a range press 3, to random fill press 4, to read from a file press 5, to exit press 6");
        Scanner str = new Scanner(System.in);
        String num = str.nextLine();
        int choice = Integer.parseInt(num);
        while (choice < 1 || choice > 7){
            System.out.println("Wrong input, Please try again");
            System.out.println("To insert Press 1, To search for a node Press 2, to search for a range press 3, to random fill press 4, to read from a file press 5, to exit press 6");
            str = new Scanner(System.in);
            num = str.nextLine();
            choice = Integer.parseInt(num);
        }
        while (choice >= 1 && choice <= 7){
            switch (choice){
                case 1:
                    System.out.println("Please enter 4 integers with space between them");
                    Scanner input = new Scanner(System.in);
                    String line = input.nextLine();
                    String[] splited = line.split("\\s+");
                    int[] key = new int[4];
                    for (int i=0; i < 4; i++){
                        key[i] = Integer.parseInt(splited[i]);
                    }

                    Node tmp = new Node(key, node_number);
                    if (b.find(tmp.data, 0) == true)
                        System.out.println("node already exist");
                    else{
                        b.insert(tmp.data, tmp.node_number, 0);
                        node_number++;
                        b.printNode(root);
                    }
                    break;
                case 2:
                    System.out.println("Please enter 4 integers with space between them");
                    input = new Scanner(System.in);
                    line = input.nextLine();
                    splited = line.split("\\s+");
                    key = new int[4];
                    for (int i=0; i < 4; i++){
                        key[i] = Integer.parseInt(splited[i]);
                    }
                    System.out.println("Check whether Node with value "+line+" exists : " + b.find(key,0));
                    break;
                case 3:
                    System.out.println("Please enter 4 integers with space between them for the minimum");
                    input = new Scanner(System.in);
                    line = input.nextLine();
                    splited = line.split("\\s+");
                    key = new int[4];
                    for (int i=0; i < 4; i++){
                        key[i] = Integer.parseInt(splited[i]);
                    }
                    int[] min = key;
                    System.out.println("Please enter 4 integers with space between them for the maximum");
                    input = new Scanner(System.in);
                    line = input.nextLine();
                    splited = line.split("\\s+");
                    key = new int[4];
                    for (int i=0; i < 4; i++){
                        key[i] = Integer.parseInt(splited[i]);
                    }
                    int[] max = key;
                    b.searchRange(min, max, root);
                    break;
                case 4:
                    System.out.println("How many nodes will you want to add to the tree?");
                    str = new Scanner(System.in);
                    num = str.nextLine();
                    choice = Integer.parseInt(num);
                    int size = choice;
                    System.out.println("please write a top boundary for the random numbers:");
                    Scanner str1 = new Scanner(System.in);
                    String num1 = str1.nextLine();
                    int choice1 = Integer.parseInt(num1);
                    int bound = choice1;
                    Random rand = new Random();
                    for (int i=0; i < size; i++){
                        key = new int[]{rand.nextInt(bound), rand.nextInt(bound), rand.nextInt(bound), rand.nextInt(bound),};
                        b.insert(key, node_number, 0);
                        node_number++;
                    }
                    b.printNode(root);
                    break;
                case 6:
                    System.out.println("Bye Bye");
                    System.exit(0);
                    break;
                case 5:
                    System.out.println("enter file path to read");
                    Scanner path = new Scanner(System.in);
                    String path1 = path.nextLine();
                    List<String> nodes = b.readFile(path1);
                    for (int i=0; i<nodes.size(); i++){
                        splited = nodes.get(i).split("\\s+");
                        key = new int[4];
                        for (int j=0; j < 4; j++){
                            key[j] = Integer.parseInt(splited[j]);
                        }
                        b.insert(key,node_number,0);
                        node_number++;
                    }
                    b.printNode(root);
                    break;
            }
            System.out.println("To insert Press 1, To search for a node Press 2, to search for a range press 3, to random fill press 4, to read from a file press 5, to exit press 6");
            str = new Scanner(System.in);
            num = str.nextLine();
            choice = Integer.parseInt(num);
            while (choice < 1 || choice > 7){
                System.out.println("Wrong input, Please try again");
                System.out.println("To insert Press 1, To search for a node Press 2, to search for a range press 3, to random fill press 4, to read from a file press 5, to exit press 6");
                str = new Scanner(System.in);
                num = str.nextLine();
                choice = Integer.parseInt(num);
            }
        }
    }

    public static void printNode(Node root) {
        int maxLevel = maxLevel(root);
        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
        PrintTable(Collections.singletonList(root));

    }

    private static void PrintTable(List<Node> nodes) {
        if (nodes.isEmpty() || isAllElementsNull(nodes))
            return;

        List<Node> newNodes = new ArrayList<>();
        for (Node node : nodes) {
            if (node != null) {
                System.out.print(""+node.node_number+": ");
                PrintData2(node);
                newNodes.add(node.left);
                newNodes.add(node.right);

            } else {
                newNodes.add(null);
                newNodes.add(null);
            }
        }
        PrintTable(newNodes);
    }

    private static void printNodeInternal(List<Node> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        printWhitespaces(firstSpaces);

        List<Node> newNodes = new ArrayList<>();
        for (Node node : nodes) {
            if (node != null) {
                PrintData1(node);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).left != null)
                    System.out.print("/");
                else
                    printWhitespaces(1);

                printWhitespaces(i + i - 1);

                if (nodes.get(j).right != null)
                    System.out.print("\\");
                else
                    printWhitespaces(1);

                printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static <T extends Comparable<?>> int maxLevel(Node node) {
        if (node == null)
            return 0;

        return Math.max(maxLevel(node.left), maxLevel(node.right)) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }

    private List<String> readFile(String filename)
    {
        List<String> records = new ArrayList<String>();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null)
            {
                records.add(line);
            }
            reader.close();
            return records;
        }
        catch (Exception e)
        {
            System.err.format("Exception occurred trying to read '%s'.", filename);
            e.printStackTrace();
            return null;
        }
    }
}


class Node{
    int[] data;
    int node_number;
    Node left;
    Node right;
    public Node(int[] data, int node_number){
        this.data = data;
        this.node_number = node_number;
        left = null;
        right = null;
    }
}