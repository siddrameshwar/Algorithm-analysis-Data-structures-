/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs5343.assignment.pkg5;

import java.util.Stack;

/**
 *
 * @author Siddrameshwar
 */
public class CS5343Assignment5 {

    /**
     * Assignment by Siddrameshwar Kadagad
     *  UTD id - 2021491485
     *  netid - sxk190071
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Assignment by Siddrameshwar Kadagad\n");
        
        System.out.println("Topological order of a graph A. Graph A doesn't have cycles");
        GraphVertexList graphA = InitializeGraph();        
        printGraph(graphA);
        topologicalOrdering(graphA, graphA.gV);
        
        System.out.println("Topological order of a graph B - which has a cycle");        
        GraphVertexList graphB = InitializeCyclicGraph();        
        printGraph(graphB);
        topologicalOrdering(graphB, graphB.gV);
    }
    
    public static void topologicalOrdering(GraphVertexList graph, GraphVertex currVertex){
        Stack<String> stack = new Stack<>();
        Boolean cycleFound = false;
        GraphVertexList firstVertexList = graph;
        System.out.println("");
        System.out.println("Order of vertices for which 'done' is set to true");
        
        while(graph != null) {
            if(!graph.gV.visited){
                cycleFound = topologicalOrdering(graph, graph.gV, stack, firstVertexList);
                if(cycleFound){
                    System.out.println("\nPrinting Topological Order of vertices till the cycle was found ");
                    break;
                }
                    
            }            
            graph = graph.next;
        }        
        String cVer;  
        if(!cycleFound)
            System.out.println("\n\nPrinting Topological Order");
        while(!stack.empty()){
            cVer = stack.pop();
            System.out.print( cVer + " ");
        }
        System.out.println("\n");
        
    }
    
    public static Boolean topologicalOrdering(GraphVertexList graph, GraphVertex currVertex, Stack<String> stack, GraphVertexList fullGraph) {
        //System.out.println(" Here ");
        currVertex.visited = true;
        GraphVertex connectedV = currVertex.next;
        GraphVertex connectedcorV;
        if(connectedV == null) {
            currVertex.done = true;
            System.out.print(currVertex.name + " ");
            stack.add(currVertex.name);
            return false;
        }
        while(connectedV != null) {
            connectedcorV = findVertexInAdjacencyList(fullGraph, connectedV.name);
            if(connectedcorV.visited == true && connectedcorV.done == false){
                System.out.println("Cycle found in the graph");
                return true;
            }
            if(connectedcorV.visited == true && connectedcorV.done == true){
                
            } else {
                Boolean cycleFound = topologicalOrdering(graph, connectedcorV, stack, fullGraph);
                if(cycleFound)
                    return true;
            }            
            connectedV = connectedV.next;
        }
        
        currVertex.done = true;
        stack.add(currVertex.name);
        System.out.print(currVertex.name + " ");
        return false;
    }
    
    public static GraphVertex findVertexInAdjacencyList(GraphVertexList graph, String name ){
        while(!graph.gV.name.equals(name)) {
            graph = graph.next;
        }
        return graph.gV;
    }
    
    
    public static GraphVertexList InitializeGraph(){
        GraphVertex C = new GraphVertex("C");
        GraphVertex CA = new GraphVertex("A");        
        GraphVertex CB = new GraphVertex("B");
        GraphVertex CG = new GraphVertex("G");
        GraphVertex CF = new GraphVertex("F");
        C.next = CA;
        CA.next = CB;
        CB.next = CG;
        CG.next = CF;
        GraphVertexList clist = new GraphVertexList(C);
        
        GraphVertex A = new GraphVertex("A");
        GraphVertex AD = new GraphVertex("D");        
        GraphVertex AE = new GraphVertex("E");
        A.next = AD;
        AD.next = AE;
        GraphVertexList alist = new GraphVertexList(A);
        clist.next = alist;
        
        GraphVertex B = new GraphVertex("B");
        GraphVertex BE = new GraphVertex("E"); 
        B.next = BE;
        GraphVertexList blist = new GraphVertexList(B);
        alist.next = blist;
        
        GraphVertex G = new GraphVertex("G");
        GraphVertexList glist = new GraphVertexList(G);
        blist.next = glist;
        
        GraphVertex F = new GraphVertex("F");
        GraphVertex FH = new GraphVertex("H");
        GraphVertex FI = new GraphVertex("I"); 
        F.next = FH;
        FH.next = FI;
        GraphVertexList flist = new GraphVertexList(F);
        glist.next = flist;
        
        GraphVertex D = new GraphVertex("D");
        GraphVertex DE = new GraphVertex("E");        
        GraphVertex DF = new GraphVertex("F");
        D.next = DE;
        DE.next = DF;
        GraphVertexList dlist = new GraphVertexList(D);
        flist.next = dlist;
        
        GraphVertex E = new GraphVertex("E");
        GraphVertex EF = new GraphVertex("F"); 
        GraphVertex EJ = new GraphVertex("J"); 
        E.next = EF;
        EF.next = EJ;
        GraphVertexList elist = new GraphVertexList(E);
        dlist.next = elist;
        
        GraphVertex H = new GraphVertex("H");
        GraphVertexList hlist = new GraphVertexList(H);
        elist.next = hlist;
        
        GraphVertex I = new GraphVertex("I");
        GraphVertex IH = new GraphVertex("H"); 
        GraphVertex IJ = new GraphVertex("J"); 
        I.next = IH;
        IH.next = IJ;
        GraphVertexList ilist = new GraphVertexList(I);
        hlist.next = ilist;
        
        GraphVertex J = new GraphVertex("J");
        GraphVertexList jlist = new GraphVertexList(J);
        ilist.next = jlist;
        
        GraphVertex Z = new GraphVertex("Z");
        GraphVertex ZJ = new GraphVertex("J");
        Z.next = ZJ;
        GraphVertexList zlist = new GraphVertexList(Z);        
        jlist.next = zlist;
        
        return clist;
    }
    
    public static GraphVertexList InitializeCyclicGraph(){
        GraphVertex C = new GraphVertex("C");
        GraphVertex CA = new GraphVertex("A");        
        GraphVertex CB = new GraphVertex("B");
        GraphVertex CG = new GraphVertex("G");
        GraphVertex CF = new GraphVertex("F");
        C.next = CA;
        CA.next = CB;
        CB.next = CG;
        CG.next = CF;
        GraphVertexList clist = new GraphVertexList(C);
        
        GraphVertex A = new GraphVertex("A");
        GraphVertex AD = new GraphVertex("D");        
        GraphVertex AE = new GraphVertex("E");
        A.next = AD;
        AD.next = AE;
        GraphVertexList alist = new GraphVertexList(A);
        clist.next = alist;
        
        GraphVertex B = new GraphVertex("B");
        GraphVertex BE = new GraphVertex("E"); 
        B.next = BE;
        GraphVertexList blist = new GraphVertexList(B);
        alist.next = blist;
        
        GraphVertex G = new GraphVertex("G");
        GraphVertexList glist = new GraphVertexList(G);
        blist.next = glist;
        
        GraphVertex F = new GraphVertex("F");
        GraphVertex FH = new GraphVertex("H");
        GraphVertex FI = new GraphVertex("I"); 
        F.next = FH;
        FH.next = FI;
        GraphVertexList flist = new GraphVertexList(F);
        glist.next = flist;
        
        GraphVertex D = new GraphVertex("D");
        GraphVertex DE = new GraphVertex("E");        
        GraphVertex DF = new GraphVertex("F");
        D.next = DE;
        DE.next = DF;
        GraphVertexList dlist = new GraphVertexList(D);
        flist.next = dlist;
        
        GraphVertex E = new GraphVertex("E");
        GraphVertex EF = new GraphVertex("F"); 
        GraphVertex EJ = new GraphVertex("J"); 
        E.next = EF;
        EF.next = EJ;
        GraphVertexList elist = new GraphVertexList(E);
        dlist.next = elist;
        
        GraphVertex H = new GraphVertex("H");
        GraphVertexList hlist = new GraphVertexList(H);
        elist.next = hlist;
        
        GraphVertex I = new GraphVertex("I");
        GraphVertex IE = new GraphVertex("E"); 
        GraphVertex IH = new GraphVertex("H"); 
        GraphVertex IJ = new GraphVertex("J"); 
        I.next = IE;
        IE.next = IH;
        IH.next = IJ;
        GraphVertexList ilist = new GraphVertexList(I);
        hlist.next = ilist;
        
        GraphVertex J = new GraphVertex("J");
        GraphVertexList jlist = new GraphVertexList(J);
        ilist.next = jlist;
        
        return clist;
    }
    
    public static void printGraph(GraphVertexList graphList){
        //Printing the Given Graph
        while(graphList != null){
            if(graphList.gV != null){
                GraphVertex vertex = graphList.gV;
                System.out.println("Edges from vertex " + vertex.name );
                GraphVertex iter = vertex.next;
                while(iter != null) {
                    System.out.println("\tto vertex " + iter.name );
                    iter = iter.next;
                }                
            }
            graphList = graphList.next;
        }
    }
}
