/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import integrationproject.model.Edge;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

/**
 * @author Aristeidis Moustakas 
 * aem 2380
 * email moustakas@csd.auth.gr
*/
/** 
 * This class implements the algorithm of Kruskal for the finding of MST using union-find structure.
 */
public class Kruskal_Unionfind {
    
    
    int[] aa;
    int num;
    public Kruskal_Unionfind()
    {
       
    }
    /**
     * This function implements Kruskal's algorithm. More specifically is sorting the arraylist in ascending order.Then it calls 
     * for each edge the function Union-find and if this function return true, adding this edge in a set("selected",which finally will have 
     * all the edges which constitutes the minimun spanning tree).
     * 
     * @param edges an arraylist with all the edges between the ants.
     * @param num is the size of red ants population which is equal with the black ants population. 
     * @return A 2d array int[red_population+black_population-1][4]. In each row there is the ant ID 
     * and an identifier whether it is from red (0) or black population (1).
     */
    public int [][] runKruskal(ArrayList<Edge> edges,int num)
     {  
         aa=new int[2*num];       // I create an array for each ant.
         this.num=num;
        HashSet<Edge> selected=new HashSet<>(); 
        Collections.sort(edges, new Comparator<Edge>() {
            @Override
            public int compare(Edge edge1, Edge edge2) {
                return edge1.compare(edge1, edge2);
            }
        });
        
        for(int i=0;i<2*num;i++)
        {
            aa[i]=-1;                   // I put in each position of array "aa" the value -1. 
           
        }
        int counter=0;
       for(int i=0;(i<edges.size())&&(counter<2*num-1);i++)// In this for-loop I call the function union-find for each edge and 
        {                                                   //and if this function return true, adding this edge in a set "selected".
            if(Unionfind(i,edges))                          //The loop will stop when all items of the array "aa" will have made union or 
            {                                               //when we will have checked all the edges.    
                selected.add(edges.get(i));
                counter++;
            }

        }
        ArrayList<Edge> e=new ArrayList<>(selected);
        int q=e.size();
         int[][] res=new int[q][4];
         int x;
         int y;
        for(int i=0;i<q;i++) //I create the array which I will return.
        {
            int colorX=0;
            int colorY=0;
            x=e.get(i).getFrom();
            y=e.get(i).getTo();
            if(x>=num)
            {
                x=x-num;
                colorX=1;
            }
            if(y>=num)
            {
                y=y-num;
                colorY=1;
            }
            
            res[i][0]=x;
            res[i][1]=colorX;
            res[i][2]=y;                
            res[i][3]=colorY;
        }
        return res;
         
    }
    
    /**
     * This  function  implements the union-find structure. More specifically we have the array "aa" which at start 
     * has in all position the value -1. When we call "union-find",it checks if two vertexes have the same "representative" in the sets in which they belong.
     * When this happens, it means that we have "cycle" , so this edge doesn't constitute in MST. Otherwise, link the two 
     * sets in the array  "aa" and this means that this edge constitute in MST.More specically i put the "representative" of the smallest
     * set to point to the "representative" of the other set(union).
     * 
     * @param i The position in the arraylist "edges" of the edge that we want to check.  
     * @param edges the arraylist with all edges.
     * @return if the edge in position "i" constitutes in MST.
     * 
     */
      public boolean Unionfind(int i,ArrayList<Edge> edges)
    {       boolean flag=false;
             int x=edges.get(i).getFrom();
            int y=edges.get(i).getTo();
                while(aa[x]>=0){  //  it finds the "representative" of the set in which becomes the first vertex of the edge.
                    x=aa[x];}
                while(aa[y]>=0){  //  it finds the "representative" of the set in which becomes the second vertex of the edge.
                    y=aa[y];}
          if((!(x==y)|| (aa[x]==-1 && aa[y]==-1)))   //Check the two vertexes if they are having the same "representative".
           {                                          //If they are having different "representatives" I make union them,otherwise  i ignore the 2 different sets.
            flag=true;                                
           if(aa[x]<aa[y])
                {
                    aa[x]+=aa[y];
                    aa[y]=x;
                }
                else
                    if(aa[x]>aa[y])
                    {
                    aa[y]+=aa[x];
                    aa[x]=y;
                    }
                else
                    {
                     if(x>y)
                     {     
                         aa[x]+=aa[y];
                         aa[y]=x;
                     }else
                     {
                         aa[y]+=aa[x];
                         aa[x]=y;
                     }
                    }
           }
         return flag;
    }        
}
