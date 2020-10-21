package com.demo.treesort;

/**
 * @author guochunyuan
 * @create on  2020-10-21 9:35
 */
public class Test {

    public static void main(String[] args) {
        BinaryTreeNode rightBinaryTreeNode1 = new BinaryTreeNode();
        rightBinaryTreeNode1.setData(2);
        BinaryTreeNode rightBinaryTreeNode2 = new BinaryTreeNode();
        rightBinaryTreeNode2.setData(22);
        rightBinaryTreeNode2.setRightChild(rightBinaryTreeNode1);
        BinaryTreeNode leftBinaryTreeNode1 = new BinaryTreeNode();
        leftBinaryTreeNode1.setData(21);
        BinaryTreeNode leftBinaryTreeNode2 = new BinaryTreeNode();
        leftBinaryTreeNode2.setData(25);
        leftBinaryTreeNode2.setLeftChild(leftBinaryTreeNode1);
        BinaryTreeNode binaryTreeNode = new BinaryTreeNode();
        binaryTreeNode.setData(50);
        binaryTreeNode.setLeftChild(leftBinaryTreeNode2);
        binaryTreeNode.setRightChild(rightBinaryTreeNode2);

        BinaryTree binaryTree = new BinaryTree(binaryTreeNode);

//        binaryTree.PreOrder(binaryTreeNode);
        BinaryTreeNode parent = binaryTree.getParent(binaryTreeNode, leftBinaryTreeNode1);
//        System.out.println(parent.getData());

        System.out.println(binaryTree.size());
        System.out.println(binaryTree.heigh());
    }
}
