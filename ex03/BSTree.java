import java.util.*;

public class BSTree {
    private Node root;
    private int size;
	
    public BSTree(){root = null; size = 0;}

    public Node root(){return root;}
    public boolean isEmpty(){return root == null;}
    public int size(){return size;}

    public void insert(String s){
	if (isEmpty()){
	    root = new Node(s,null,null,null);
	    size = 1;
	}
	else insert(s,root);
    }
    //
    // insert the string s into a tree
    // (1) if the tree is empty
    //     then create a new node with s as element, with no left, right or parent, and set the root to be that node
    //          set the size of the tree to be 1 ... otherwise
    // (2) insert the string s into the tree rooted on the node root ... see below
    //

    private void insert(String s,Node node){
      if(s.compareTo(node.getElement()) < 0 && node.hasLeft()){ insert(s, node.getLeft()); }
      else if(s.compareTo(node.getElement()) < 0 && !node.hasLeft()){ node.setLeft( new Node(s,null,null,node)); }
      else if(s.compareTo(node.getElement()) > 0 && node.hasRight()){ insert(s, node.getRight()); }
      else if(s.compareTo(node.getElement()) > 0 && !node.hasRight()){ node.setRight( new Node(s,null,null,node)); }
    }


    public boolean isPresent(String s){return find(s,root)!= null;}
    //
    // s is present if we can find a node that contains s
    //

    private Node find(String s,Node node){
    
    if(node == null) return null;
    else { 
      if(s.compareTo(node.getElement()) < 0) return find(s, node.getLeft());
      else if(s.compareTo(node.getElement()) > 0) return find(s, node.getRight());
      else return node;
    
    }
    }
    //
    // given a node and a string s, deliver a Node with element equal to s or deliver null
    // (0) if the node is null return null
    // (1) if s is less than the element in the node
    //     then search for s is in the tree rooted at the left child ... otherwise
    // (2) if s is greater than the element in the node
    //     then search for s is in the tree rooted at the right child ... otherwise
    // (3) it's not greater, it's not less, so it must be equal ... return the node!
    //
    // NOTE: although private, we can use find when we delete a node (see below)
    //


    public int height(){return height(root);}

    private int height(Node node){
    
    int hL;
    int hR;
    if (node == null) return -1;
    else
      hL = height(node.getLeft());
      hR = height(node.getRight());
      return 1 + ((hL > hR) ? hL : hR);
    }

    public void preorder(){preorder(root);}
    
    private void preorder(Node node){
    
    if (node != null) System.out.println(node.getElement());
    //else
    
    }
    //
    // if the node isn't null
    // print out the contents of the nodes in preorder
    // de
    // where preorder is the node followed by
    // the preorder of the left followed by preorder of right
    //

    public void inorder(){inorder(root);}
    
    private void inorder(Node node){}
    //
    // if the node isn't null
    // inorder is inorder of left, followed by the node 
    // followed by inorder of right
    //
    
    public void postorder(){postorder(root);}
    
    private void postorder(Node node){}
    //
    // if the node isn't null
    // postorder is postorder of the left, followed by postorder right
    // followed by the node
    //

    public void bfs(){bfs(root);}

    private void bfs(Node node){}
    //
    // breadth first search visits all nodes at depth 0, then all at depth 1
    // then all at depth 2, then ...
    // HINT: use a LinkedList as a queue, call it Q
    //       add the node to the Q
    //       while the Q isn't empty
    //       begin remove the first node from the Q
    //             print it
    //             enqueue the nodes left and right children
    //       end
    //

    public void dfs(){dfs(root);}

    private void dfs(Node node){}
    //
    // depth first search is sames as breadth first search but uses a stack!
    //
	

    public void delete(String s){
	Node node = find(s,root);
	if (node == null) return;
	if (node.isRoot() && node.isLeaf()) root = null;
	else if (node.isRoot() && !node.hasLeft() && node.hasRight()){root = node.getRight(); root.setParent(null);}
	else if (node.isRoot() && node.hasLeft() && !node.hasRight()){root = node.getLeft(); root.setParent(null);}
	else delete(node);
	size--;
    }
    //
    // (0) find the node in the tree, rooted at node root,that contains s
    // (1) if not found then nothing to delete ... done!
    // (2) if the node is the root and the root is a leaf, make the tree empty ... otherwise
    // (3) if the node is the root and the root has a right child and no left child
    //     then make the right child the root
    //          make the parent of the root null
    // (4) if the node is the root and the root has a left child and no right child
    //     then make the left child the new root of the tree ... otherwise
    // (5) delete the node using the steps in method delete(node) below
    // (6) Regardless, in cases (1) to (5), once done decrement the size counter
    //

    private void delete(Node node){
	if (node.isInternal()){
	    Node minNode = getMin(node.getRight());
	    node.setElement(minNode.getElement());
	    deleteNotInternal(minNode);
	}
	else deleteNotInternal(node);
    }
    //
    // (1)   if the node is internal, i.e. has a left and right child
    // (1.1) then find the smallest node in the right subtree, call this minNode
    // (1.2) replace the contents of the node with the contents of the minNode
    //       NOTE: this preserves inorder property
    // (1.3) minNode is NOT internal, therfore deleteNotInternal(minNode)
    // (2)   if node is not internal, the deleteNotInternal(node)
    //

    private Node getMin(Node node){
	if (node.hasLeft()) return getMin(node.getLeft());
	else return node;
    }
    //
    // deliver the node with smallest element in the subtree rooted on node
    // (1) if node has a left child
    //     then find the smallest node in the tree rooted on the left child ... otherwise
    // (2) node has no left child and is therefore the smallest child. 
    //     Deliver that node as a result
    //

    private void deleteNotInternal(Node node){
	Node v = node.getParent();
	if (node.isRightChild()  && !node.hasLeft()) v.setRight(node.getRight());
	else if (node.isRightChild() && !node.hasRight()) v.setRight(node.getRight());
	else if (node.isLeftChild() && !node.hasLeft()) v.setLeft(node.getRight());
	else if (node.isLeftChild() && ! node.hasRight()) v.setLeft(node.getLeft());
    }
    // (0) the node is a leaf or has one child
    // (1) get the parent of the current node to be deleted, and call it v
    // (2) if the node is a right child and has no left child of its own
    //     then v's right child is now the current node's right child ... otherwise
    // (3) if the node is a right child and has no right child of its own
    //     then v's right child becomes the current node's left child ... otherwise
    // (4) if the node is a left child and has no left child of its own
    //     then v's left child becomes the current node's right child ... otherwise
    // (5) the node is a left child and has no right child of its own
    //     consequently v's left child becomes the current node's left child 
    //

    public String toString(){return traverse(root);}

    private String traverse(Node node){
	if (node == null) return "-";
	else {
	    String s = "(";
	    s = s + traverse(node.getLeft()) + ",";
	    s = s + node.getElement();
	    s = s + "," + traverse(node.getRight());
	    s = s + ")";
	    return s;
	}
    }
    //
    // a traversal delivering tree in bracket notation
    //

}
