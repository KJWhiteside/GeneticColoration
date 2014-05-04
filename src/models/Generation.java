/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import enums.Enums.SelectFor;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Random;

/**
 *
 * @author kevinwhiteside
 */
public class Generation
{

    Random rand;
    public ArrayList<Genome> GenePool;
    public float TotalFitness;
    public float HighestFitness;
    public float CrossoverRate;
    public float MutationRate;
    public boolean SelectForRed;
    public boolean SelectForBlue;
    public boolean SelectForGreen;
    

    public Generation(ArrayList<Genome> genePool, float crossoverRate, float mutationRate)
    {
        rand = new Random();
        GenePool = genePool;
        CrossoverRate = crossoverRate;
        MutationRate = mutationRate;
        SelectForRed = false;
        SelectForBlue = false;
        SelectForGreen = false;
    }

    public void Advance(ArrayList<Genome> GenomeList)
    {
        GenePool = GenomeList;
        UpdateFitnessScores();

        ArrayList<Genome> NewGeneration = new ArrayList<>();

        while (NewGeneration.size() < GenePool.size())
        {
            // Fitness-biased-ly select a pair of Genomes.
            Genome mom = RouletteWheelSelection();
            Genome dad = RouletteWheelSelection();

            // Crossover.
            Genome bro = new Genome();
            Genome sis = new Genome();
            Crossover(mom.RedBits, dad.RedBits, bro.RedBits, sis.RedBits);
            Crossover(mom.BlueBits, dad.BlueBits, bro.BlueBits, sis.BlueBits);
            Crossover(mom.GreenBits, dad.GreenBits, bro.GreenBits, sis.GreenBits);

            // Mutation.
            Mutate(bro.RedBits);
            Mutate(bro.BlueBits);
            Mutate(bro.GreenBits);
            Mutate(sis.RedBits);
            Mutate(sis.BlueBits);
            Mutate(sis.GreenBits);

            // Set new RGB.
            bro.Red = Genome.BitSettoInt(bro.RedBits);
            bro.Blue = Genome.BitSettoInt(bro.BlueBits);
            bro.Green = Genome.BitSettoInt(bro.GreenBits);
            sis.Red = Genome.BitSettoInt(sis.RedBits);
            sis.Blue = Genome.BitSettoInt(sis.BlueBits);
            sis.Green = Genome.BitSettoInt(sis.GreenBits);

            // Add genomes to the new Generation.
            NewGeneration.add(bro);
            NewGeneration.add(sis);
        }

        GenomeList.clear();
        GenomeList.addAll(NewGeneration);

    }

    private void UpdateFitnessScores()
    {
        TotalFitness = 0.0f;
        HighestFitness = 0.0f;

        for (Genome g : GenePool)
        {
            g.SolveForRed = SelectForRed;
            g.SolveForBlue = SelectForBlue;
            g.SolveForGreen = SelectForGreen;
            
            float fitness = g.GetFitness();

            TotalFitness += fitness;
            if (fitness > HighestFitness)
            {
                HighestFitness = fitness;
            }

        }

    }

    private Genome RouletteWheelSelection()
    {
        float cutoff = rand.nextFloat() * TotalFitness;
        float counter = 0.0f;
        Genome selectedGenome = new Genome();

        for (Genome g : GenePool)
        {
            counter += g.GetFitness();

            if (counter >= cutoff)
            {
                selectedGenome = g;
                break;
            }

        }

        return selectedGenome;

    }

    private void Crossover(BitSet mom, BitSet dad, BitSet bro, BitSet sis)
    {
        if ((rand.nextFloat() <= CrossoverRate) || (mom == dad))
        {
            bro = (BitSet) dad.clone();
            sis = (BitSet) mom.clone();
        } 
        else
        {
            int splitPoint = rand.nextInt(8);

            for (int i = 0; i < splitPoint; ++i)
            {
                bro.set(i, dad.get(i));
                sis.set(i, mom.get(i));
            }

            for (int i = splitPoint; i < 8; ++i)
            {
                bro.set(i, mom.get(i));
                sis.set(i, dad.get(i));
            }

        }

    }

    private void Mutate(BitSet bits)
    {

        for (int i = 0; i < bits.length(); ++i)
        {
            //flip this bit?
            if (rand.nextFloat() <= MutationRate)
            {
                //flip the bit
                bits.flip(i);
            }
        }//next bit    }

    }

}
