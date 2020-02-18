
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Siddrameshwar Kadagad
 *         2021491485
 */
public class BstTraverseDelete {
    public static void main(String[] args) {
        System.out.println("Assignment by Siddrameshwar Kadagad");
        TreeNode root = initialiseTree();
        System.out.println("In order traversal of BST before deleting node");
        inorderTraversal(root);
        System.out.println();System.out.println("Preorder traversal");        
        preorderTraversal(root);
        System.out.println();System.out.println("Deleting node 100");
        root = deleteNode(root, 100);
        System.out.println("In order traversal of BST after deleting node");
        inorderTraversal(root);
        System.out.println();System.out.println("Preorder traversal");        
        preorderTraversal(root);
    }
   
    public static void inorderTraversal(TreeNode root) {
        if(root == null)
            return;
        inorderTraversal(root.left);
        System.out.print( root.val + " ");
        inorderTraversal(root.right);                    
    }
    
    public static TreeNode deleteNode(TreeNode root, int key) {
        if(root == null)
            return null;
        if(root.val == key) {
            if(root.left == null)
                return root.right;
            TreeNode node = root.left;
            if(node.right == null){
                node.right = root.right;
                root = node;
                return root;
            }               
            
            while(node.right.right != null)
                node = node.right;
            TreeNode temp = node.right;
            node.right = node.right.left;
            temp.left = root.left;
            temp.right = root.right;
            root = temp;
            return root;
        } else {
            if(key > root.val)
                root.right = deleteNode(root.right, key);                
            else 
                root.left = deleteNode(root.left, key);                
        }   
        return root;
    }
    
    public static void preorderTraversal(TreeNode root) {
        if(root == null)
            return;
        System.out.print( root.val + " ");
        preorderTraversal(root.left);        
        preorderTraversal(root.right);                    
    }
    public static TreeNode initialiseTree() {
        TreeNode root = new TreeNode(100);
        addNodeToBst(root, 50);
        addNodeToBst(root, 200);
        addNodeToBst(root, 150);
        addNodeToBst(root, 300);
        addNodeToBst(root, 25);
        addNodeToBst(root, 75);
        addNodeToBst(root, 12);
        addNodeToBst(root, 37);
        addNodeToBst(root, 125);
        addNodeToBst(root, 175);
        addNodeToBst(root, 250);
        addNodeToBst(root, 320);
        addNodeToBst(root, 67);
        addNodeToBst(root, 87);
        addNodeToBst(root, 94);
        addNodeToBst(root, 89);
        addNodeToBst(root, 92);
        addNodeToBst(root, 88);  
        return root;
    }
    
    public static TreeNode addNodeToBst(TreeNode root, int val) {
        if(root == null) {
            TreeNode node = new TreeNode(val);
            return node;
        } 
        if(root.val < val)
            root.right = addNodeToBst(root.right, val);
        else 
            root.left = addNodeToBst(root.left, val);        
        return root;        
    }
}
