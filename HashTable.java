/*Charlotte Van Houtven
CSC201 Fall 2020
Programming Assignment 2
September 28, 2020
 */

import java.lang.reflect.Array;
import java.util.ArrayList;


public class HashTable {

    private ArrayList<ArrayList<Freq>> ht; //In separate chaining, we have an arraylist of arraylists
    private int tableSize;
    private int collisions;
    private int steps;

    public HashTable(int arraySize)
    {
        this.collisions = 0;
        this.steps = 0;
        this.tableSize = arraySize;
        this.ht = new ArrayList<>(arraySize);
        for (int i = 0; i < arraySize; i++)  //Creating an empty arraylist in each spot
        {
            this.ht.add(new ArrayList<Freq>());
        }
    }

    public int getCollisions()
    {
        return this.collisions;
    }

    public int getSteps()
    {
        return this.steps;
    }



    public ArrayList<ArrayList<Freq>> getHt() {
        return this.ht;
    }

    public void setHt(ArrayList<ArrayList<Freq>> ht) {
        this.ht = ht;
    }

    public int getArraySize() {
        return this.tableSize;
    }

    public void setArraySize(int arraySize) {
        this.tableSize = arraySize;
    }

    public void count(RGB pixel)
    {

        int key = pixel.getR() + pixel.getG() + pixel.getB(); //key value is a sum of all 3
        int index = key % this.tableSize; //come back: which tableSize? this.tablesize?
        if (this.ht.get(index).isEmpty()) //If index empty
        {
            Freq newfreq = new Freq(pixel,1); //Freq is one because it was not in hashtable before
            ArrayList<Freq> innerlist = new ArrayList<>();
            innerlist.add(newfreq);
            ht.set(index,innerlist); //addto hashtable in correct index
        }

        else //If there is something in the spot it hashed to
        {
            ArrayList<Freq> temp = this.ht.get(index);
            boolean alreadyexists = false;
            for (int i = 0; i < temp.size(); i++) //Checking to make sure the color is not already there
            {
                this.steps = this.steps + 1; //this constitutes as a step
                int R = temp.get(i).getColor().getR();
                int G = temp.get(i).getColor().getG();
                int B = temp.get(i).getColor().getB();
                if (pixel.getR() == R && pixel.getG() == G && pixel.getB() == B) //If its already in the list, increment F
                {
                    Freq thisfreq = temp.get(i);
                    thisfreq.setF(thisfreq.getF() + 1);
                    alreadyexists = true;
                    break;
                }
            }
            if (alreadyexists == false) //If its not already in the hashtable
            {
                Freq newfreq = new Freq(pixel,1); //F is one because its new
                ht.get(index).add(newfreq);
                this.collisions = this.collisions + 1;
            }
        }
    }
}
