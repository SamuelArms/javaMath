/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmllinegraph;

/**
 *
 * @author samuel
 */
public class StringNode {
  // Instance variables:
  private double element;
  private StringNode next;
  /** Creates a node with null references to its element and next node. */
  public StringNode() {
    this(0.0, null);
  }
  /** Creates a node with the given element and next node. */
  public StringNode(double e, StringNode n) {
    element = e;
    next = n;
  }
  // Accessor methods:
  public double getElement() {
    return element; 
  }
  public StringNode getNext() { 
    return next;
  }
  // Modifier methods:
  public void setElement(double newElem) { 
    element = newElem; 
  }
  public void setNext(StringNode newNext) {
    next = newNext; 
  }
}