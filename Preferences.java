
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Aristeidis Moustakas 
 * aem 2380
 * email moustakas@csd.auth.gr  
 */

/**
 * This class has a field which is an arraylist of objects "spouse" and it contains all the spouses that an ant prefers from the most 
 * desirable black ant to less. 
 */
public class Preferences {
    ArrayList<Spouse> preferences; //The arraylist with ant's preferences.

    public Preferences(ArrayList<Spouse> pref) 
    {
        preferences=pref;
    }
}
