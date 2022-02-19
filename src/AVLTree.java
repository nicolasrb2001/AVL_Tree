import java.util.ArrayList;

class AVLTree {
    public static AVLTreeNode root;
    // TODO you may define additional variables here

    public AVLTree() {
        // TODO implement this
        root = null;
    }

    public static boolean inTree(int e) {
        // TODO implement this
        int comparison;
        AVLTreeNode node = root;
        while(true){
            if (node == null){
                return false;
            }

            if (e == node.element){
                return true;
            }else{
                //explore left
                if (e > node.element){
                    node = node.right;
                }else{
                    node = node.left;
                }
            }
        }

    }


    public void left_rotation(AVLTreeNode node, AVLTreeNode parent){
        System.out.println("left");
        AVLTreeNode nodetemporary = node.left.right;
        if(node == root){
            AVLTreeNode temp = root;
            root = root.left;
            root.right = temp;
            temp.left = null;

        }else{
            parent.left = node.left;
            parent.left.right = node;
            node.left =nodetemporary;
        }
    }

    public void right_rotation(AVLTreeNode node, AVLTreeNode parent){
        System.out.println("right");
        AVLTreeNode nodetemporary = node.right.left;
        if(node == root){
            AVLTreeNode temp = root;
            root = root.right;
            root.left = temp;
            temp.right = null;
        }else{
            parent.right = node.right;
            parent.right.left = node;
            node.right = nodetemporary;

        }
    }

    // left heavy but inserted in right subtree
    public void double_right_rotation(AVLTreeNode node, int insert, AVLTreeNode parent, Boolean side){
        System.out.println("double right");
        if(node == root){
            if(node.left.right.getBalance() == -1){
                AVLTreeNode nodetemporary = node.left.right.left;
                root = node.left.right;
                root.left = node.left;
                root.left.right = nodetemporary;
                node.left = null;
                root.right = node;
            }else{
                if(node.left.right.getBalance() == 0){
                    root = node.left.right;
                    root.left = node.left;
                    root.right = node;
                    node.left = null;
                    node.right = null;
                }else{
                    AVLTreeNode nodetemporary = node.left.right.right;
                    root = node.left.right;
                    root.left = node.left;
                    node.left = nodetemporary;
                    root.right = node;
                }
            }
        }else{
            if(node.left.right.getBalance() == -1){
                AVLTreeNode nodetemporary = node.left.right.left;
                if(!side){//left
                    parent.left = node.left.right;
                    parent.left.left = node.left;
                    parent.left.right = node;
                    parent.left.right.left = nodetemporary;
                    parent.left.left.right = null;
                }else{
                    parent.right = node.left.right;
                    parent.right.left = node.left;
                    parent.right.right = node;
                    parent.right.right.left = nodetemporary;
                    parent.right.left.right = null;
                }

            }else{
                if(node.left.right.getBalance() == 0){
                    if(!side){
                        parent.left = node.left.right;
                        parent.left.right = node;
                        parent.left.left = node.left;
                        parent.left.left.right = null;
                        parent.left.right.left = null;
                    }else{
                        parent.right = node.left.right;
                        parent.right.right = node;
                        parent.right.left = node.left;
                        parent.right.left.right = null;
                        parent.right.right.left = null;
                    }

                }else{
                    AVLTreeNode nodetemporary = node.left.right.right;
                    if(!side){//left
                        parent.left = node.left.right;
                        parent.left.left = node.left;
                        parent.left.left.right = null;
                        parent.left.right.left = nodetemporary;
                    }else{
                        parent.right = node.left.right;
                        parent.right.left = node.left;
                        parent.right.left.right = null;
                        parent.right.right.left = nodetemporary;
                    }
                }
            }
        }
    }

    // right heavy but inserted in left subtree
    public void double_left_rotation(AVLTreeNode node, int insert, AVLTreeNode parent, Boolean side){
        System.out.println("double left");
        if(node == root){
            if(node.right.left.getBalance() == -1){
                AVLTreeNode nodetemporary = node.right.left.left;
                root = node.right.left;
                root.right = node.right;
                root.right.left = nodetemporary;
                node.right = null;
                root.left = node;
            }else{
                if(node.right.left.getBalance() == 0){
                    root = node.right.left;
                    root.right = node.right;
                    root.left = node;
                    node.right = null;
                    node.left = null;
                }else{
                    AVLTreeNode nodetemporary = node.right.left.right;
                    root = node.right.left;
                    root.right = node.right;
                    node.right = nodetemporary;
                    root.left = node;
                }
            }
        }else{
            if(node.right.left.getBalance() == -1){
                AVLTreeNode nodetemporary = node.right.left.left;
                if(!side){//left
                    parent.left = node.right.left;
                    parent.left.right = node.right;
                    parent.left.left = node;
                    parent.left.left.right = nodetemporary;
                    parent.left.right.left = null;
                }else{
                    parent.right = node.right.left;
                    parent.right.right = node.right;
                    parent.right.left = node;
                    parent.right.left.right = nodetemporary;
                    parent.right.right.left = null;
                }

            }else{
                if(node.right.left.getBalance() == 0){
                    if(!side){
                        parent.left = node.right.left;
                        parent.left.left = node;
                        parent.left.right = node.right;
                        parent.left.right.left = null;
                        parent.left.left.right = null;
                    }else{
                        parent.right = node.right.left;
                        parent.right.left = node;
                        parent.right.right = node.right;
                        parent.right.right.left = null;
                        parent.right.left.right = null;
                    }

                }else{
                    AVLTreeNode nodetemporary = node.right.left.right;
                    if(!side){//left
                        parent.left = node.right.left;
                        parent.left.right = node.right;
                        parent.left.right.left = null;
                        parent.left.left.right = nodetemporary;
                    }else{
                        parent.right = node.right.left;
                        parent.right.right = node.right;
                        parent.right.right.left = null;
                        parent.right.left.right = nodetemporary;
                    }
                }
            }
        }
    }
    public void insert(int e) {
        // TODO implement this
        AVLTreeNode node = null;
        if(root != null){
            node = root;
        }else{
            root = new AVLTreeNode(e);
            return;
        }
        while(true){
            if(e> node.element){
                if(node.right == null){
                    node.right = new AVLTreeNode(e);
                    break;
                }
                node = node.right;
            }else{
                if(node.left == null){
                    node.left = new AVLTreeNode(e);
                    break;

                }
                node = node.left;
            }
        }
        update_height(root, e, false);
        update_balance(root, e, root, false, false);
    }

    public void update_height(AVLTreeNode node, int insert, Boolean checkall){
        if(checkall){
            if(node.left != null & node.right != null){
                update_height(node.right, insert, true);
                update_height(node.left, insert, true);
                node.height = node.getChildHeight()+1;
                return;
            }else{
                if(node.right != null){
                    update_height(node.right, insert, true);
                }
                if(node.left != null){
                    update_height(node.left, insert, true);
                }
                node.height = node.getChildHeight() +1;
                return;
            }
        }else{
            if(insert == node.element){
                node.height = 1;
                return;
            }
            if(insert< node.element){
                update_height(node.left, insert, false);
            }else{
                update_height(node.right, insert, false);
            }
        }
        node.height = node.getChildHeight() +1;

    }

    public boolean update_balance(AVLTreeNode node, int insert, AVLTreeNode parent, Boolean stop, Boolean side){
        if(insert == node.element){
            return false;
        }
        if(insert< node.element){

            stop = update_balance(node.left, insert, node, stop, false);
        }else{
            stop = update_balance(node.right, insert, node,stop, true);
        }
        if(stop){
            return true;
        }
        node.balance = node.getBalance();
        if(node.balance>1){
            if(node.right.getBalance()==-1){
                //double left
                double_left_rotation(node, insert, parent, side);

            }else{
                //right rotation
                right_rotation(node, parent);
            }
            breadth(root);
            update_height(root, insert, true);

            return true;
        }else{
            if(node.balance < -1){
                if(node.left.getBalance()==1){
                    //double right rotation

                    double_right_rotation(node, insert, parent, side);
                }else{
                    //left rotation
                    left_rotation(node,parent);
                }
                breadth(root);
                update_height(root, insert, true);

                return true;
            }
        }
        return false;
    }
    public static void breadth(AVLTreeNode n){
        ArrayList<AVLTreeNode> nodes = new ArrayList<>();
        ArrayList<AVLTreeNode> explore = new ArrayList<>();
        explore.add(n);
        while(true){
            for(AVLTreeNode y: explore){
                System.out.print(y.element + "  ");
                if(y.left != null){
                    nodes.add(y.left);
                }
                if(y.right != null){
                    nodes.add(y.right);
                }
                
            }
            System.out.println(" ");
            explore.clear();
            if(nodes.isEmpty()){
                break;
            }
            explore.addAll(nodes);
            nodes.clear();
        }
    }
    public static void main(String[] args){
        AVLTree tree = new AVLTree();
        tree.insert(10);
        tree.insert(11);
        tree.insert(12);
        tree.insert(15);
        tree.insert(3);
        tree.insert(5);
        tree.insert(14);
        tree.insert(7);
        tree.insert(8);
        tree.insert(6);
        tree.insert(9);
        tree.insert(30);
        tree.insert(19);
        tree.insert(13);
        tree.insert(17);
        tree.insert(16);
        tree.insert(40);
        tree.insert(35);
        tree.insert(36);

    }

}
