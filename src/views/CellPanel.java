/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.Color;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author kevinwhiteside
 */
public class CellPanel extends JPanel
{

    private Color cellColor = Color.lightGray;

    public CellPanel(float r, float g, float b)
    {
        cellColor = new Color(r, g, b);

        this.setBorder(new LineBorder(Color.BLACK));
        this.setBackground(cellColor);
    }

}
