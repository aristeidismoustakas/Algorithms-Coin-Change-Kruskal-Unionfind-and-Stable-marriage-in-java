/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * @author Aristeidis Moustakas 
 * aem 2380
 * email moustakas@csd.auth.gr    
 */
/**
 * This class solves the problem of stable marriage.
 */
public class Stable_marriage {
    
    public Stable_marriage(){
        
    }
  
    /**
     * This function uses an arraylist "notMarried" which includes the position of all red ants which are not married.
     * A random red ant proposes to its most prefered black ant to marry it. If the black ant isn't married, accept, otherwise if it's 
     * married, selects the closer red ant between the ant with which is already married and the ant which proposing to it. 
     * 
     * @param All_preferences the red ants preferences .
     * @param redSpouse the spouse of each red ant.
     * @param blackSpouse the spouse of each black ant.
     * @param blackDistance the distance of each black ant from its spouse.
     * @return an array with all couples of red and black ants.
     * 

     */
    public int [][] runStableMarriage(ArrayList<Preferences> All_preferences,int[] redSpouse,int[] blackSpouse,double[] blackDistance)
    {
        int pop=redSpouse.length;
        int[] ex_wifes=new int[pop];
        ArrayList<Integer> notMarried=new ArrayList<>();
        for(int i=0;i<pop;i++)
        {
            notMarried.add(i);
        }
        for(int p=0;p<pop;p++)
        {
            ex_wifes[p]=-1;
        }
        while(!notMarried.isEmpty())   //This while-loop run while there are red ants which are not married.
        {
            int i;
            Random r=new Random();
            int k=r.nextInt(notMarried.size());   //We choose randomly a no married red ant.
            i=notMarried.get(k);
            boolean f=true;        
           int j=ex_wifes[i]; //We find the ex-wife of the red ant we have selected and if it hasn't any spouse previously, then the value in array "ex-wifes" is -1.
            while(f)   //This while-loop run while the red ant find a black ant to marry.
            {
                j++;
                int want=All_preferences.get(i).preferences.get(j).getId();
                if(blackDistance[want]>All_preferences.get(i).preferences.get(j).getDistance()||blackDistance[want]==-1)
                {           //We check if the black ant want this red ant or the red ant with which is already married.
                    f=false;        //The black ant choose the closer red ant to itself.
                    blackDistance[want]=All_preferences.get(i).preferences.get(j).getDistance();
                    if(blackSpouse[want]!=-1)
                    {
                        notMarried.add(blackSpouse[want]);
                    }
                    blackSpouse[want]=i;
                    redSpouse[i]=want;
                    notMarried.remove(k);
                    ex_wifes[i]=j;
                }
                
            }
            
        }
        int[][] res=new int[pop][2];            //We create the array with the couples to return.
        for(int i=0;i<pop;i++)
        {
            res[i][0]=i;
            res[i][1]=redSpouse[i];
        }
        return res;
        
    }
   
}

    
    

    
        
        
        
        
        

