/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import views.GameWindow;

/**
 *
 * @author kevinwhiteside
 */
public class Main
{
        /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new GameWindow().setVisible(true);
            }
        });
    }

}
