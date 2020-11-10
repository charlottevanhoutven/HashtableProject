/*Charlotte Van Houtven
CSC201 Fall 2020
Programming Assignment 2
September 28, 2020
 */

public class Freq implements Comparable<Freq>{

    private RGB color;
    private int f;

    public Freq(RGB c, int f) {
        this.color = c;
        this.f = f;
    }
    public Freq()
    {
        this.color = new RGB();
        this.f = 0;
    }



    public RGB getColor() {
        return this.color;
    }

    public void setColor(RGB color) {
        this.color = color;
    }

    public int getF() {
        return this.f;
    }

    public void setF(int f) {
        this.f = f;
    }

    //This method is used by the sort call in Main. It sorts the Freq objects by F 
    @Override
    public int compareTo(Freq fq) {
        if (this.f > fq.getF())
        {
            return -1;
        }
        else if (this.f == fq.getF())
        {
            return 0;
        }
        else
        {
            return 1;
        }
    }



}
