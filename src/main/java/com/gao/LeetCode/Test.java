package com.gao.LeetCode;

/**
 *
 */
/**
 * @author jueying:
 * @version 创建时间：2018-10-23 下午01:26:47
 * 类说明
 */
/**
 * @author jueying
 *
 */
public class Test {

    int top=-1;//栈顶指针
    int size=0;//栈大小
    static Node headNode;

    int i=0;
    class Node{
        private Node next;//指针

        private Integer data;//数据域

        @Override
        public String toString() {
            return "Node{" +
                    "next=" + next +
                    ", data=" + data +
                    '}';
        }
    }


    //循环列表
    public void recicle(Node node,int data){
        if(i<=10){
            Node newNode=new Node();//创建新的结点
            newNode.data=new Integer(i);//设置数据域
            newNode.next=null;
            node.next=newNode;
            recicle(newNode,++i);
        }else{
            node.next=headNode;
        }

    }


    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Test test=new Test();
        headNode=test.new Node();//头指针
        headNode.data=null;
        new Test().recicle(headNode,0);//循环链表
        System.out.println("创建后的循环链表是：");
        System.out.println(headNode);
        int k=0;
        while(headNode.next!=null&&k<=23){
            ++k;
            headNode=headNode.next;
            if(headNode.data!=null)
                System.out.print(headNode.data+" ");
        }
    }

}
