/*
 Assignment by Siddrameshwar Kadagad
UTD ID - 2021491485
sxk190071
 */

package assignment4;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 *
 * @author Siddrameshwar
 */
public class Assignment4 {
    public static void main(String[] args) throws CloneNotSupportedException {
        System.out.println("Assignment by Siddrameshwar Kadagad");
        
        // Declaring Comparator for GraphVertex using Java 8 Lambda expression, 
        // so that Priority is set based on the Graph distance
        Comparator<GraphVertex> compareUsingVertexDist = Comparator.comparing(GraphVertex::getDistance);
        PriorityQueue<GraphVertex> priorityQueue = new PriorityQueue<>(compareUsingVertexDist);        
        HashMap<String, Object> hm;  
        
        //Initializing Graph Using the 'initilisingGraph' method
        GraphVertexList graph = initilisingGraph();
        System.out.println();
        System.out.println("Input Graph");
        printGraph(graph);
        priorityQueue = addVerticesToPriorityQueue(graph, priorityQueue);
        hm = loadHashMap(graph);
        GraphVertexList shortestPathTree = dijkstras(graph, priorityQueue, hm);
        System.out.println();
        System.out.println("Shortest Path Tree");
        printTree(shortestPathTree);
    }
    
    public static HashMap<String, Object> loadHashMap(GraphVertexList graph){
        HashMap<String, Object> result = new HashMap<>();
        while(graph != null) {
            result.put(graph.gV.name, graph.gV);
            graph = graph.next;
        }
        return result;
    }
    
    public static GraphVertexList initilisingGraph(){
        //Vertex A and all edges from A
        GraphVertex A = new GraphVertex("A", null, 0);
        GraphVertex AB = new GraphVertex("B", null, 9);        
        GraphVertex AD = new GraphVertex("D", null, 14);
        GraphVertex AE = new GraphVertex("E", null, 15);
        GraphVertex AI = new GraphVertex("I", null, 13);
        A.next = AB;
        AB.next = AD;
        AD.next = AE;
        AE.next = AI;
        GraphVertexList alist = new GraphVertexList(A, null);
        
        //Vertex B and all edges from B
        GraphVertex B = new GraphVertex("B", null, Integer.MAX_VALUE);
        GraphVertex BC = new GraphVertex("C", null, 24);
        B.next = BC;
        GraphVertexList blist = new GraphVertexList(B, null);
        alist.next = blist;
        
        //Vertex C and all edges from C
        GraphVertex C = new GraphVertex("C", null, Integer.MAX_VALUE);
        GraphVertex CF = new GraphVertex("F", null, 2);
        GraphVertex CH = new GraphVertex("H", null, 19);        
        C.next = CF;
        CF.next = CH;
        GraphVertexList clist = new GraphVertexList(C, null);
        blist.next = clist;
        
        //Vertex D and all edges from D
        GraphVertex D = new GraphVertex("D", null, Integer.MAX_VALUE);
        GraphVertex DC = new GraphVertex("C", null, 18);
        GraphVertex DE = new GraphVertex("E", null, 5);
        GraphVertex DF = new GraphVertex("F", null, 30);
        D.next = DC;
        DC.next = DE;
        DE.next = DF;
        GraphVertexList dlist = new GraphVertexList(D, null);
        clist.next = dlist;
        
        //Vertex E and all edges from E
        GraphVertex E = new GraphVertex("E", null, Integer.MAX_VALUE);
        GraphVertex EF = new GraphVertex("F", null, 20);
        GraphVertex EH = new GraphVertex("H", null, 44);
        GraphVertex EI = new GraphVertex("I", null, 44);
        GraphVertex EJ = new GraphVertex("J", null, 99);
        E.next = EF;
        EF.next = EH;
        EH.next = EI;
        EI.next = EJ;
        GraphVertexList elist = new GraphVertexList(E, null);
        dlist.next = elist;
        
        //Vertex F and all edges from F
        GraphVertex F = new GraphVertex("F", null, Integer.MAX_VALUE);
        GraphVertex FG= new GraphVertex("G", null, 11);
        GraphVertex FH = new GraphVertex("H", null, 16);
        F.next = FG;
        FG.next = FH;
        GraphVertexList flist = new GraphVertexList(F, null);
        elist.next = flist;
       
        //Vertex G and all edges from G
        GraphVertex G = new GraphVertex("G", null, Integer.MAX_VALUE);
        GraphVertex GC = new GraphVertex("C", null, 6);
        GraphVertex GH = new GraphVertex("H", null, 6);
        G.next = GC;
        GC.next = GH;
        GraphVertexList glist = new GraphVertexList(G, null);
        flist.next = glist;
        
        //Vertex H and all edges from H
        GraphVertex H = new GraphVertex("H", null, Integer.MAX_VALUE);
        GraphVertex HJ = new GraphVertex("J", null, 16);
        H.next = HJ;
        GraphVertexList hlist = new GraphVertexList(H, null);
        glist.next = hlist;
        
        //Vertex I and all edges from I
        GraphVertex I = new GraphVertex("I", null, Integer.MAX_VALUE);
        GraphVertex IJ = new GraphVertex("J", null, 33);
        I.next = IJ;
        GraphVertexList ilist = new GraphVertexList(I, null);
        hlist.next = ilist;
        
        //Vertex I and all edges from I
        GraphVertex J = new GraphVertex("J", null, Integer.MAX_VALUE);
        GraphVertexList jlist = new GraphVertexList(J, null);
        ilist.next = jlist;
        
        return alist;
    }
    
    public static PriorityQueue<GraphVertex> addVerticesToPriorityQueue(GraphVertexList graphList, PriorityQueue<GraphVertex> queue) {
        System.out.println("Adding vertices to PriorityQueue");
        while(graphList != null){    
            queue.add(graphList.gV);
            graphList = graphList.next;
        }
        return queue;
    }
    
    public static GraphVertexList dijkstras(GraphVertexList graphList, PriorityQueue<GraphVertex> priorityQueue, HashMap<String, Object> hm) throws CloneNotSupportedException {
        HashMap<Object, Object> hm2 = new HashMap<>();
        GraphVertex curVertex;
        GraphVertex curVertexClone;
        GraphVertex curVertexClone2;
        GraphVertexList result = new GraphVertexList();
        Boolean firstNode = true;
        GraphVertexList curList;
        GraphVertexList prevList = null;
        GraphVertex curCloneParent;
        while(!priorityQueue.isEmpty()) {
            curVertex = priorityQueue.poll();
            GraphVertex iter = curVertex.next;
            curVertexClone = (GraphVertex) curVertex.clone();
            curVertexClone2 = (GraphVertex) curVertex.clone();
            curVertexClone.next = null;
            curVertexClone.parent = null;
            curVertexClone2.next = null;
            curVertexClone2.parent = null;
            hm2.put(curVertex, curVertexClone);
            if(firstNode) {
                result = new GraphVertexList(curVertexClone, null);
                prevList = result;
                firstNode = false;
            } else {
                curList = new GraphVertexList(curVertexClone, null);                
                curCloneParent = (GraphVertex) hm2.get(curVertex.parent);
                curVertexClone2.next = curCloneParent.next;
                curCloneParent.next = curVertexClone2;                
                prevList.next = curList;
                prevList = curList;
            }
            while(iter != null){
                GraphVertex gV = (GraphVertex) hm.get(iter.name);
                if(curVertex.distance  + iter.distance < gV.distance) {
                    gV.distance = curVertex.distance  + iter.distance;
                    gV.parent = curVertex;
                    priorityQueue.remove(gV);
                    priorityQueue.add(gV);
                }
                iter = iter.next;
            }
        }       
        return result;        
    }
    
    public static void printGraph(GraphVertexList graphList){
        //Printing the Given Graph
        while(graphList != null){
            if(graphList.gV != null){
                GraphVertex vertex = graphList.gV;
                System.out.println("Edges from vertex " + vertex.name );
                GraphVertex iter = vertex.next;
                while(iter != null) {
                    System.out.println("\tto vertex " + iter.name + " and distance is " + iter.distance);
                    iter = iter.next;
                }                
            }
            graphList = graphList.next;
        }
    }
    
    public static void printTree(GraphVertexList graphList){
        //Printing the Shortest path tree
        while(graphList != null){
            if(graphList.gV != null){
                GraphVertex vertex = graphList.gV;
                System.out.println("Edges from vertex " + vertex.name );
                GraphVertex iter = vertex.next;
                while(iter != null) {
                    System.out.println("\tto vertex " + iter.name + ". Shortest distance to this vertex is " + iter.distance);
                    iter = iter.next;
                }
                System.out.println();                
            }
            graphList = graphList.next;
        }
    }    
}
