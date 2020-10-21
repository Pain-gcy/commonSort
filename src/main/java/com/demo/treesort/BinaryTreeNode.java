package com.demo.treesort;

/**
 * @author guochunyuan
 * @create on  2020-10-21 9:22
 */
public class BinaryTreeNode {
    private int data;  //数据
    private BinaryTreeNode leftChild;  //左孩子
    private BinaryTreeNode rightChild; //右孩子


    public BinaryTreeNode() {
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public BinaryTreeNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BinaryTreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public BinaryTreeNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(BinaryTreeNode rightChild) {
        this.rightChild = rightChild;
    }
}
