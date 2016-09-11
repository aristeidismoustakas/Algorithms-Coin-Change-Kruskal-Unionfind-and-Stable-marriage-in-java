//package <set your package name>;

import integrationproject.algorithms.Algorithms;
import integrationproject.model.BlackAnt;
import integrationproject.model.RedAnt;
import integrationproject.utils.InputHandler;
import integrationproject.utils.Visualize;
import java.util.ArrayList;
import integrationproject.model.Edge;
import java.util.Collections;

/**
 *
 * @author Aristeidis Moustakas 
 * aem 2380
 * email moustakas@csd.auth.gr
*/

 
public class IP_2380 extends Algorithms {

    public static void main(String[] args) {
        checkParameters(args);
        
        //create Lists of Red and Black Ants
        int flag = Integer.parseInt(args[1]);
        ArrayList<RedAnt> redAnts = new ArrayList<>();
        ArrayList<BlackAnt> blackAnts = new ArrayList<>();
        if (flag == 0) {
            InputHandler.createRandomInput(args[0], Integer.parseInt(args[2]));
        }
        InputHandler.readInput(args[0], redAnts, blackAnts);

        
        IP_2380 algs = new IP_2380();
        
        //debugging options
        boolean visualizeMST =false;
        boolean visualizeSM = false;
        boolean printCC = false;
        boolean evaluateResults = true;

        if(visualizeMST){
            int[][] mst = algs.findMST(redAnts, blackAnts);
            if (mst != null) {
                Visualize sd = new Visualize(redAnts, blackAnts, mst, null, "Minimum Spanning Tree");
                sd.drawInitialPoints();
            }
        }

        if(visualizeSM){
            int[][] matchings = algs.findStableMarriage(redAnts, blackAnts);
            if (matchings != null) {
                Visualize sd = new Visualize(redAnts, blackAnts, null, matchings, "Stable Marriage");
                sd.drawInitialPoints();
            }
        }

        if(printCC){
            int[] coinChange = algs.coinChange(redAnts.get(0), blackAnts.get(0)); 
            System.out.println("Capacity: " + redAnts.get(0).getCapacity());
            for(int i = 0; i < blackAnts.get(0).getObjects().length; i++){
                System.out.println(blackAnts.get(0).getObjects()[i] + ": " + coinChange[i]);
            }
        }
        
        if(evaluateResults){
            System.out.println("\nEvaluation Results");
            algs.evaluateAll(redAnts, blackAnts);
        }
    }
/**
 * In this function I have a for-loop with 3 nested for-loops in which  all the edges created from each ant to all the others ants.
 * More specifically, between two ants is created only one edge because edges are bidirectional. Last but not least it's important to say that for 
 * each black ant, when i want to include it in one edge ,I put in the edge for this, not the id that it has in the arraylist but this id plus 
 * the population of black ants.That's because  when I have a edge with 2 ants I have to understand if the ant is  black or red.
 * Finally, this function calls the function runKruskal.
 * 
 * @param redAnts The red ants population
 * @param blackAnts The black ants population
 * @return A 2d array int[red_population+black_population-1][4]. In each row there is the ant ID and an identifier whether it is from red (0) or black population (1).
 * 
 * 
 */
    @Override
    public int[][] findMST(ArrayList<RedAnt> redAnts, ArrayList<BlackAnt> blackAnts) {
       ArrayList<Edge> edges=new ArrayList<>();
       int pop=redAnts.size(); //The size of red ants population which is the same with black ants population.
        for(int i=0;i<pop;i++) 
        {                       
            for(int j=i+1;j<pop;j++)  
            {
                edges.add(new Edge(i, j, redAnts.get(i).getDistanceFrom(redAnts.get(j))));
            }
            for(int j=0;j<pop;j++)
            {
                edges.add(new Edge(i, j+pop, redAnts.get(i).getDistanceFrom(blackAnts.get(j))));
            }
            for(int j=i+1;j<pop;j++)
            {
                edges.add(new Edge(i+pop, j+pop, blackAnts.get(i).getDistanceFrom(blackAnts.get(j))));
            }    
            
            
        }
        Kruskal_Unionfind k=new Kruskal_Unionfind();
        return k.runKruskal(edges,redAnts.size());

    }

    /**
     * This function calculates an arraylist of preferences for each red ant, sorts it and put it in an other arraylist type's "preferences"(a red
     * ant prefer the closer black ants).At last this function returns the array which return function runStableMarriage.
     * 
     * @param redAnts The red ants population.
     * @param blackAnts The black ants population.
     * @return A 2d array int[red_population][2]. The 2nd dimension is of size of 2 as it expects values in the form 
     * [ant_id_1,ant_id_2] where ant_id is the id of the ant. 
     * This means that ant_id_1 is matched with ant_id_2. ant_id_1 should be a red ant!
     */
    @Override
    public int[][] findStableMarriage(ArrayList<RedAnt> redAnts, ArrayList<BlackAnt> blackAnts) {
        int pop=redAnts.size(); //The size of red ants population which is equal with black ants population.
        Stable_marriage stable_marriage=new Stable_marriage();
        ArrayList<Preferences> preferences=new ArrayList<>();
        int[] redSpouse=new int[pop];          //An arraylist with the spouse of each red ant.
        int[] blackSpouse=new int[pop];        //An arraylist with the spouse of each black ant.
        double[] blackDistance=new double[pop];//An arraylist with the distance from each black ant to its spouse.
        for(int i=0;i<pop;i++)                  //This for-loop creates the arraylist with the preferences for each red ant.
        { 
            ArrayList<Spouse> pref1=new ArrayList<>();
            for(int j=0;j<pop;j++)
            {
                pref1.add(new Spouse(j, redAnts.get(i).getDistanceFrom(blackAnts.get(j))));
            }
            Collections.sort(pref1);
            preferences.add(new Preferences(pref1));
            blackDistance[i]=-1;
            blackSpouse[i]=-1;
            redSpouse[i]=-1;
        }
        return stable_marriage.runStableMarriage(preferences,redSpouse,blackSpouse,blackDistance);
    }
/**
 * This function call the function runCoinChange with arguments which are the amount we want to complete and one array with 
 * the weights of the seeds.
 * @param redAnt a red ant.
 * @param blackAnt  a black ant.
 * @return a 2d array with the final number of seeds we need from each type of seeds.
 */
    @Override
    public int[] coinChange(RedAnt redAnt, BlackAnt blackAnt) {
        int amount=redAnt.getCapacity();
        int [] coins=blackAnt.getObjects();
        Coin_change c=new Coin_change();
        return c.runCoinChange(amount, coins);
        
    }
    
    private static void checkParameters(String[] args) {
        if (args.length == 0 || args.length < 2 || (args[1].equals("0") && args.length < 3)) {
            if (args.length > 0 && args[1].equals("0") && args.length < 3) {
                System.out.println("3rd argument is mandatory. Represents the population of the Ants");
            }
            System.out.println("Usage:");
            System.out.println("1st argument: name of filename");
            System.out.println("2nd argument: 0 create random file, 1 input file is given as input");
            System.out.println("3rd argument: number of ants to create (optional if 1 is given in the 2nd argument)");
            System.exit(-1);
        }
    }
    
}
