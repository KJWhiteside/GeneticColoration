/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Random;

/**
 *
 * @author kevinwhiteside
 */
public class Genome
{

    public int Red;
    public int Green;
    public int Blue;

    public BitSet RedBits;
    public BitSet BlueBits;
    public BitSet GreenBits;
    
    public boolean SolveForRed;
    public boolean SolveForBlue;
    public boolean SolveForGreen;

    public Genome()
    {
        Random rand = new Random();

        // Java 'Color' class takes 3 floats, from 0 to 1.
        Red = rand.nextInt(256);
        Green = rand.nextInt(256);
        Blue = rand.nextInt(256);

        RedBits = IntToBitSet(Red);
        BlueBits = IntToBitSet(Blue);
        GreenBits = IntToBitSet(Green);

    }

    public Genome(ArrayList<String> Chromosomes)
    {
        Red = Integer.parseInt(Chromosomes.get(0), 2);
        Blue = Integer.parseInt(Chromosomes.get(1), 2);
        Green = Integer.parseInt(Chromosomes.get(2), 2);

        RedBits = IntToBitSet(Red);
        BlueBits = IntToBitSet(Blue);
        GreenBits = IntToBitSet(Green);
    }

    public float GetFitness()
    {
        int redDiff = (SolveForRed) ? (255 - Red) : Red;
        int blueDiff = (SolveForBlue) ? (255 - Blue) : Blue;
        int greenDiff = (SolveForGreen) ? (255 - Green) : Green;

        return 1.0f / ((float)redDiff + (float)blueDiff + (float)greenDiff + 1.0f);
    }


    static BitSet IntToBitSet(int i)
    {
        BitSet bs = new BitSet(Integer.SIZE);
        for (int k = 0; k < Integer.SIZE; k++)
        {
            if ((i & (1 << k)) != 0)
            {
                bs.set(k);
            }
        }
        return bs;
    }

    static int BitSettoInt(BitSet bs)
    {
        int i = 0;
        for (int pos = -1; (pos = bs.nextSetBit(pos + 1)) != -1;)
        {
            i |= (1 << pos);
        }
        return i;
    }
}
