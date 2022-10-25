
public class AVLTree
{
    private Node root;

    class Node
    {
        int val;
        Node left;
        Node right;

        // Initializer
        public Node(int val)
        {
            this.val = val;
            this.left = null;
            this.right = null;
        }

        // Setters and Getters
        public int getVal()
        {
            return val;
        }

        public void setVal(int val)
        {
            this.val = val;
        }

        public Node getLeft()
        {
            return left;
        }

        public void setLeft(Node left)
        {
            this.left = left;
        }

        public Node getRight()
        {
            return right;
        }

        public void setRight(Node right)
        {
            this.right = right;
        }

        // Insert
        public void insert(int val)
        {
            if (val < this.val)
            {
                if (this.left == null)
                {
                    this.left = new Node(val);
                }
                else
                {
                    this.left.insert(val);
                }
            }
            else
            {
                if (this.right == null)
                {
                    this.right = new Node(val);
                }
                else
                {
                    this.right.insert(val);
                }
            }
        }

        // Delete
        public void delete(int val)
        {
            if (val < this.val)
            {
                if (this.left != null)
                {
                    this.left.delete(val);
                }
            }
            else if (val > this.val)
            {
                if (this.right != null)
                {
                    this.right.delete(val);
                }
            }
            else
            {
                if (this.left != null && this.right != null)
                {
                    this.val = this.right.min();
                    this.right.delete(this.val);
                }
                else if (this.left != null)
                {
                    this.val = this.left.val;
                    this.right = this.left.right;
                    this.left = this.left.left;
                }
                else if (this.right != null)
                {
                    this.val = this.right.val;
                    this.left = this.right.left;
                    this.right = this.right.right;
                }
                else
                {
                    // No children
                }
            }
        }

        public int min() {
            if (this.left == null)
            {
                return this.val;
            }
            else
            {
                return this.left.min();
            }
        }

        public int max() {
            if (this.right == null)
            {
                return this.val;
            }
            else
            {
                return this.right.max();
            }
        }

        public void inOrder()
        {
            if (this.left != null)
            {
                this.left.inOrder();
            }
            System.out.println(this.val);
            if (this.right != null)
            {
                this.right.inOrder();
            }
        }
    }

    public AVLTree()
    {
        this.root = null;
    }

    private int balance(Node start) {
        // Calaculates the balance of a node by subtracting the height of the left subtree from the height of the right subtree
        return height(start.right) - height(start.left);
    }

    private int height(Node start) {
        // Calculates the height of a node by recursively calling itself until it reaches a leaf node
        if (start == null) {
            return 0;
        }
        return 1 + Math.max(height(start.left), height(start.right));
    }

    private Node rotateLeft(Node start) {
        // Rotates a node to the left
        Node temp = start.right;
        start.right = temp.left;
        temp.left = start;
        return temp;
    }

    private Node rotateRight(Node start) {
        // Rotates a node to the right
        Node temp = start.left;
        start.left = temp.right;
        temp.right = start;
        return temp;
    }

    private Node balanceTree(Node start) {
        // Balances a tree by checking the balance of the node and rotating it if necessary
        if (balance(start) == 2) {
            if (balance(start.right) < 0) {
                start.right = rotateRight(start.right);
            }
            return rotateLeft(start);
        } else if (balance(start) == -2) {
            if (balance(start.left) > 0) {
                start.left = rotateLeft(start.left);
            }
            return rotateRight(start);
        }
        return start;
    }

    public void insert(int val) {
        // If root is null set that to new node
        if (this.root == null) {
            this.root = new Node(val);
        } else {
            // Inserts a node into the tree
            this.root.insert(val);

            // Balances the tree
            this.root = balanceTree(this.root);
        }
    }

    public void delete(int val) {
        // Deletes a node from the tree
        this.root.delete(val);

        // Balances the tree
        this.root = balanceTree(this.root);
    }

    public int min() {
        // Returns the minimum value in the tree
        return this.root.min();
    }

    public int max() {
        // Returns the maximum value in the tree
        return this.root.max();
    }

    public void traverse() {
        // Traverses the tree in order
        this.root.inOrder();
    }
}

