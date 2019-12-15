package assignment4;

/**
 *
 * @author Siddrameshwar
 */
public class GraphVertexList {
    // List of Graph Vertices. Similar to List of Lists
    public GraphVertex gV;
    public GraphVertexList next;
    
    public GraphVertexList(GraphVertex gV, GraphVertexList next){
        this.gV = gV;
        this.next = next;
    }
    
    public GraphVertexList() {
        this.gV = null;
        this.next = null;
    }    
}
