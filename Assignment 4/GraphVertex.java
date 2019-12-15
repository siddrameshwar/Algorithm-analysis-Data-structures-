/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4;

/**
 *
 * @author Siddrameshwar
 */
public class GraphVertex implements Cloneable {
    //Vertex Structure
    public int distance;
    public String name;
    public GraphVertex next;
    public GraphVertex parent;
    
    public GraphVertex(String name, GraphVertex next, int distance) {
        this.name = name;
        this.next = next;
        this.distance = distance;
        this.parent = null;
    }
    
    public int getDistance(){
        return distance;
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
    
    @Override
    public String toString() {
        return "Name is " + name + " distance is " + distance;
    }
}
