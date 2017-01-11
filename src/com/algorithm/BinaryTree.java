package com.algorithm;
/**
 * Copyright © 2017 damai.cn  All rights reserved.
 * 北京红马传媒文化发展有限公司 版权所有 大麦网
 * <p>
 * 声明: 对未经许可擅自反编译、修改和使用本源码者，本公司保留追究其法律责任的权利.
 */

/**
 * ClassName:com.algorithm.BinaryTree 
 * 描述: TODO  
 * 日期:     2017/1/11 
 * @author lvfang
 * @version 1.0.0
 * @since 1.0
 */
public class BinaryTree {

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        int[] a = { 3, 9, 6, 5, 2, 1, 7 };
        Node node = tree.createTree(a);

        tree.traverse(node);

        System.out.println(tree.find(0,node));
    }

    //遍历二叉树
    void traverse(Node rootNode){
        print(rootNode);
    }
    void print(Node node){
        if(node.left != null){
            print(node.left);
        }
        System.out.print(node.getValue() + ",");
        if(node.right != null){
            print(node.right);
        }
    }

    //查找节点
    private boolean find(int value,Node node){
        if(node.getValue() == value){
            return true;
        }
        if(value < node.getValue() && node.left != null){
            return find(value,node.left);
        }else if(value > node.getValue() && node.right != null) {
            return find(value,node.right);
        }

        return false;
    }


    //生产一颗二叉树
    Node createTree(int[] a){
        int root = a[0];
        Node rootNode = new Node(root);
        for(int i=1;i<a.length;i++){
            setValue(a[i],rootNode);
        }
        return rootNode;
    }

    private void setValue(int value,Node node){

        if(value < node.getValue()){
            if(node.left == null){
                node.left = new Node(value);
            }else {
                setValue(value,node.left);
            }
        } else{
            if(node.right == null){
                node.right = new Node(value);
            }else {
                setValue(value,node.right);
            }
        }


    }

    class Node{
        int value;
        Node left;
        Node right;

        Node(int value){
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }
    }

}


