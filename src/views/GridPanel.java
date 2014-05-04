/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import models.Genome;

/**
 *
 * @author kevinwhiteside
 */
public class GridPanel extends JPanel
{

    public static final int DEFAULT_ROWS = 10;
    public static final int DEFAULT_COLS = 10;

    public GridPanel()
    {

        setLayout(new GridLayout(DEFAULT_ROWS, DEFAULT_COLS));

        for (int i = 0; i < DEFAULT_ROWS * DEFAULT_COLS; ++i)
        {
            Genome c = new Genome();
            CellPanel cell = new CellPanel((c.Red / 256.0f), (c.Green / 256.0f), (c.Blue / 256.0f));
            this.add(cell);
        }

        this.validate();
        this.repaint();

    }

    public GridPanel(ArrayList<Genome> cList)
    {
        setLayout(new GridLayout(DEFAULT_ROWS, DEFAULT_COLS));

        for (int i = 0; i < DEFAULT_ROWS * DEFAULT_COLS; ++i)
        {
            Genome c = new Genome();
            CellPanel cell = new CellPanel((c.Red / 256.0f), (c.Green / 256.0f), (c.Blue / 256.0f));
            cList.add(c);
            this.add(cell);
        }

        this.validate();
        this.repaint();

    }

    public void resetCells(int rows, int cols, ArrayList<Genome> cList)
    {
        this.removeAll();
        cList.clear();

        setLayout(new GridLayout(rows, cols));

        for (int i = 0; i < rows * cols; ++i)
        {
            Genome c = new Genome();
            CellPanel cell = new CellPanel((c.Red / 256.0f), (c.Green / 256.0f), (c.Blue / 256.0f));
            cList.add(c);
            this.add(cell);
        }

        this.validate();
        this.repaint();
    }

    public void updateCells(int rows, int cols, ArrayList<Genome> cList)
    {
        this.removeAll();

        setLayout(new GridLayout(rows, cols));

        for (Genome c : cList)
        {
            CellPanel cell = new CellPanel((c.Red / 256.0f), (c.Green / 256.0f), (c.Blue / 256.0f));
            this.add(cell);
        }

        this.validate();
        this.repaint();

    }

}
