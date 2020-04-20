package com.sell.modules.sys.entity;

/**
 * @author linyuc
 * @date 2020/3/10 13:55
 */
public class SimpleList<E> {

    private class Node{
        public E e;
        public Node next;
        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }
        public Node(E e){
            this(e, null);
        }
        public Node(){
            this(null,null);
        }
    }
    private Node head;
    private int size;
    public SimpleList(){
        head = null;
        size = 0;
    }
    public int getSize(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    //在链表头添加新的元素e
    public void addSize(E e){
        Node node = new Node(e);
        node.next = head;
        head = node;
        size ++;
    }
    public void add(int index, E e){
        if(index < 0 || index > size){
            throw new IllegalArgumentException("add failed. Illegal index");
        }
        if(index == 0){
            addSize(e);
        }else{
            Node prev = head;
            for(int i = 0;i < index -1;i++){
                prev = prev.next;
            }
            Node node = new Node(e);
            node.next = prev.next;
            prev.next  = node;
           //prev.next = new Node(e,prev.next);
            size++;
        }
    }
    public void addLast(E e){
        add(size,e);
    }
//    public E get(int index){
//        Node cur = dummyHead.next;
//        for(int i = 0;i<index;i++){
//            cur = cur.next;
//        }
//        return cur.e;
//    }

}
