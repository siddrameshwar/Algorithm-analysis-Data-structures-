/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs5343.assignment.pkg5;

/**
 *
 * @author Siddrameshwar
 */
public class GraphVertex {
    GraphVertex next;
    String name;
    Boolean visited;
    Boolean done;
    
    GraphVertex(String name) {
        this.name = name;
        visited = false;
        done = false;
        next = null;
    }
    
    GraphVertex(String name, GraphVertex next) {
        this.name = name;
        visited = false;
        done = false;
        this.next = next;
    }   
}
