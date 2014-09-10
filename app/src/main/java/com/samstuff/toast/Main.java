/***********************************************************************\
 * Copyright (C) 2014 Samuel Colon                                      *
 *                                                                      *
 * This program is free software: you can redistribute it and/or modify *
 * it under the terms of the GNU General Public License as published by *
 * the Free Software Foundation, either version 3 of the License, or    *
 * (at your option) any later version.                                  *
 *                                                                      *
 * This program is distributed in the hope that it will be useful,      *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of       *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the        *
 * GNU General Public License for more details..                        *
 *                                                                      *
 * You should have received a copy of the GNU General Public License    *
 \**********************************************************************/


package com.samstuff.toast;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;


/** This application gives a user a random compliment in the form of a toast */
public class Main extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList comps = new ArrayList<String>();
        BufferedReader read;

        /** Try and read the text of compliments located in src/main/assets */
        try {
            read = new BufferedReader( new InputStreamReader( getAssets().open("comps.txt") ));

            do {
                /** Add to the array list */
                comps.add(read.readLine());
            } while (read.readLine() != null);

            read.close();
        } catch (FileNotFoundException e) {
            Log.d("Input", "File not found exception triggered");
        } catch (IOException ex) {
            Log.d("Input", "IOException raised");

        }

        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            // choose a compliment at random and toast that bitch!
            @Override
            public void onClick(View v) {
                Random r = new Random();

                /** Display toast of random compliment on button click */
                try {
                    Toast.makeText(Main.this, (String) comps.get(r.nextInt(comps.size())),
                            Toast.LENGTH_SHORT).show();

                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                    Log.d("random", "There goes that out of bounds");
                } catch (Exception err) {
                    err.printStackTrace();
                }
            }
        }); //end anonymous class
    } // end onCreate
} // end class