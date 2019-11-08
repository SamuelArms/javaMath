/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmllinegraph;

import java.util.NoSuchElementException;

/**
 *
 * @author samuel
 */
public class LinkedList {
    
    protected StringNode head;
    
    public  LinkedList() {
        head = new StringNode();
    }
    
    //add a new node to the head of the list
    public void addFirst(double element) {
        // make variable head point to new node
        head = new StringNode(element,head);
    }
    
    public void addLast(double element) {
        StringNode tail;
        tail = head;
        while (tail.getNext() != null) {
            tail = tail.getNext();
            }
        //insert new node at end of list
        tail.setNext( new StringNode(element,null));
    }

    //add a new node after position of curnode
   public void addMid(double element, double entryafter) {
        StringNode curnode;
        curnode = head;
        //go to last node and remember previous node at all times
        while (curnode != null && curnode.getElement() != entryafter) {
            curnode = curnode.getNext();
            }
        //if first occurrence of element entryafter was located then insert new node
        if (curnode != null) {
            StringNode newnode = new StringNode(element,curnode.getNext());
            curnode.setNext(newnode);
            }
    }

    public boolean isEmpty() {
        return  head == null;
    }

    public void removeFirst() {
        StringNode oldhead;
        oldhead = head;
        //remove first node from linked list
        if (head != null) {
           head = head.getNext();
           oldhead.setNext(null);
           }
        else {
           throw new NoSuchElementException();
           }
    }

    public void removeLast() {
        StringNode temp, previous;
        temp = head;
        previous = temp;
        //go to last node and remember previous node at all times
        while (temp != null && temp.getNext() != null) {
            previous = temp;
            temp = temp.getNext();
            }
        if (previous != null) {
           //remove last node
           previous.setNext(null);
           }
        else {
           throw new NoSuchElementException();
           }
    }

    //very similar to removeLast except we are looking for element i
    public void removeMid(double element) {
        StringNode temp, previous;
        temp = head.getNext();
        previous = null;
        //go to node containing element and rermember previous node at all times
        while (temp.getElement() != element && temp.getNext() != null) {
            previous = temp;
            temp = temp.getNext();
            }
        if (previous != null && temp.getNext() != null) {
           //not first or last node so we can remove node defined by temp. 
           // set the previous node to that after temp
           previous.setNext(temp.getNext());
           temp.setNext(null);
        }
        else {
           throw new NoSuchElementException();
           }
    }
    
    public static void printList(LinkedList thelist) {
        StringNode temp;
        if(thelist.isEmpty())
            System.out.println("List is empty");
        else {
            temp = thelist.head;
            while (temp != null) {
               System.out.print(temp.getElement()+" ");
               temp = temp.getNext();
            }
            System.out.println();
        }
    }
    
    public double getFirstElement(){
        return head.getElement();
    }
    
}
