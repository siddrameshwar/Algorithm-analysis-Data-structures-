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
public class GraphVertexList {
    GraphVertexList next;
    GraphVertex gV;
    
    GraphVertexList(GraphVertex vertex){
        this.gV = vertex;
        next = null;
    }
}
