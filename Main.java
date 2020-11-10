/*Charlotte Van Houtven
CSC201 Fall 2020
Programming Assignment 2
September 28, 2020
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    //The main method reads in a raw image file and send each pixel to be hashed into a hashtable
    //It prints out test cases and most frequent 256 colors

    public static void main(String[] args) throws IOException {


        long startTime = System.nanoTime(); //start stopwatch
        //command-line arguments
        String fileName = args[0];
        int h = Integer.parseInt(args[1]);
        int w = Integer.parseInt(args[2]);
        int arraySize = Integer.parseInt(args[3]);

        //Declare hashtable here
        HashTable hash = new HashTable(arraySize);

        //read in file and send pixel to count method
        //hashes each pixel into hashtable
        try {
            InputStream is = new FileInputStream("src/Ollie200x200.raw"); // create data input stream
            DataInputStream input = new DataInputStream(is);
            for (int i = 0; i < h; i++)
                for (int j = 0; j < w; j++) {
                    RGB pixel = new RGB();
                    pixel.setR(input.readUnsignedByte());
                    pixel.setG(input.readUnsignedByte());
                    pixel.setB(input.readUnsignedByte());
                    hash.count(pixel);
                }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //End stopwatch
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;

        //Printing out each test case
        System.out.println("TEST CASE_________________________________");
        System.out.println("Method Implemented: Separate Chaining");
        System.out.println("File Name: " + fileName);
        System.out.println("Initial Table Size: " + arraySize);
        System.out.println("Number Collisions: " + hash.getCollisions());
        System.out.println("Number Steps Taken Searching: " + hash.getSteps());

        //Printing out top 100 lengths of arraylists to see how tablesize is affected
        int k = 0;
        for (int i = 0; i < (hash.getHt().size() - 1); i++) { //iterating through the hashtable and getting sizes of inner lists
            if (hash.getHt().get(i).isEmpty()) { //We dont have to print out empty indexes
                //nothing
            } else {
                ArrayList<Freq> inner = hash.getHt().get(i);
                System.out.print(inner.size() + " ");
                k = k + 1;
            }
            if (k % 10 == 0) {
                System.out.println();
            }
            if (k > 100) { //we only need first 100
                break;
            }

        }
        System.out.println("Execution Time: " + timeElapsed / 1000000 + "ms");

        //We're putting all Freq objects in the same arraylist to sort
        ArrayList<Freq> total = new ArrayList<Freq>();
        for (int p = 0; p < hash.getArraySize(); p++) {
            for (int i = 0; i < hash.getHt().size(); i++) { //Inner arraylists
                ArrayList<Freq> temp = hash.getHt().get(i);
                for (int j = 0; j < temp.size(); j++) {
                    Freq thisfreq = temp.get(j);
                    total.add(thisfreq);
                }
            }
        }
        //Using compareTo function in Freq object class
        Collections.sort(total);

        //Printing out sorted frequencies
        for (int i = 0; i < 256; i++)
        {
            System.out.print("R: " + total.get(i).getColor().getR() + " ");
            System.out.print("G: " + total.get(i).getColor().getG() + " ");
            System.out.print("B: " + total.get(i).getColor().getB() + " ");
            System.out.println("Frequency: " + total.get(i).getF());
        }

    }
}

