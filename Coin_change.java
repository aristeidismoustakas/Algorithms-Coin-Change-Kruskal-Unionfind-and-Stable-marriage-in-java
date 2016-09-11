/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.HashSet;

/**
 * @author Aristeidis Moustakas 
 * aem 2380
 * email moustakas@csd.auth.gr
 */

/**
 * This class solves the coin-change problem.
 */
public class Coin_change {
    
    
        int amount; // The amount we want to complete.
        int[] seeds;    //The weight of each seeds we have.
        int[] total_seeds;  // The total numbers of seeds we need to complete the amount in each position of the array. e.x. in position 7 we have the amount of seeds that we need to complete the amount 7.
        int[] prev_seed;    // The last seed we use to complete the amount in the same position of array "total_seeds".
        int[] sel;          //An array with total number of each seeds we need to complete the amount.

    public Coin_change() {
    }
    /**
     * 
     * This function calculates the minimun number of seeds that we need to complete a quantity (amount) and in this specific instance the value from
     * variable "amount". It uses an array(total_seeds) from whom every position depicts a quantity.So, for each position, we check all
     * seeds and we find the most advantageous last seed. This happens by checking the difference of each seed from the quantity and finding 
     * the advantageous solution for this smaller quantity(this value has already calculated).
     *    
     * @param amount The quantity we want to complete.
     * @param seeds The weight of each seed that we have.
     * @return an 2d array which is calculated by the function "findWhichSeeds".
     * 
     *  
     */
    public int [] runCoinChange(int amount,int[] seeds)
    {   
        this.amount=amount;
        this.seeds=seeds;
        total_seeds=new int[amount+1]; //The total numbers of seeds we need to complete the amount in each position. e.x. in position 7 we have the amount of seeds we need to complete the amount 7.
        prev_seed=new int[amount+1];  // The last seed we use to complete the amount.
        total_seeds[0]=0;
        prev_seed[0]=0;
        sel=new int[seeds.length];

        for(int i=1;i<=amount;i++)   //This for-loop calculates the smallest number of seeds that we need to complete every amount 
        {                           //from 0 to the value of variable "amount".
            total_seeds[i]=-1;
            prev_seed[i]=-1;
            int f;
            int c;
            int sum;
            for(int j=0;j<seeds.length;j++)     //this for-loop finds who is the last and most advantageous seed for the amount i.
            {
                if(seeds[j]<=i)                 //the seed should be smaller than amount i.
                {
                    c=seeds[j];
                    f=i;
                    f=f-c;
                    sum=total_seeds[f]+1;                
                    if(sum<total_seeds[i]||total_seeds[i]==-1)  //If a seed is more advantageous, we save it.
                    {
                        total_seeds[i]=sum;
                        prev_seed[i]=c;
                    }
                }
            }        
        }
        return findWhichSeeds();
    }
    
    
    /**
     * 
     * @return a 2d array with the final number of seeds that we need for each type of seed.
     */
    public int [] findWhichSeeds()
    {
        int i=amount;
        int t;
        boolean flag;
        while(total_seeds[i]!=0)  //the final condition to stop is the amount  0.
        {
            flag=true;
            t=0;
            while(flag)            //We count the quantity of each type of seed.
            {                
                if(prev_seed[i]==seeds[t])      
                {
                    sel[t]++;
                    flag=false;
                }
                t++;
            }
            i-=prev_seed[i];
        }
        return sel;
        
    }
    
    
}
