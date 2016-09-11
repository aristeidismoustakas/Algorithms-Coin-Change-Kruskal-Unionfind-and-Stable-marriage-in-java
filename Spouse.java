

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
 * This class implements the meaning of spouse. Each object of this class has two fields,an ID and a distance which are the
 * data of a red ant's spouse.
 * 
 */
public class Spouse implements Comparable{
    private int id;
    private double distance;
   
    
    public Spouse(int id,double dist)
    {
        this.id=id;
        distance=dist;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * @param distance the distance to set
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * 
     * @param o an object that we compare with the object who calls this funtion.
     * @return the object who is closer (has the smaller distance).   
     */
    @Override
    public int compareTo(Object o) {
        Spouse a=(Spouse) o;
        if(this.distance>a.distance)
            return 1;
        if(this.distance<a.distance)
            return -1;
        return 0;
                    
    }
}
