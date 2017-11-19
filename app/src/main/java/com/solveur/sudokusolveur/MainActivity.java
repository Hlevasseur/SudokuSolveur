package com.solveur.sudokusolveur;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.solveur.sudokusolveur.tool.Resolver;

public class MainActivity extends AppCompatActivity {

    private EditText[][] grid;
    private Button btnResolve;
    private ProgressBar pbResolving;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pbResolving = (ProgressBar) findViewById(R.id.pbResolving);
        btnResolve = (Button) findViewById(R.id.btnResolveGrid);

        btnResolve.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pbResolving.setVisibility(View.VISIBLE);
                btnResolve.setVisibility(View.INVISIBLE);
                int[][] filledGrid = new int[9][9];
                for(int i=0;i<9;i++){
                    for(int j=0;j<9;j++){
                        int tmp = Integer.parseInt(grid[i][j].getText().toString());
                        if(tmp>0 && tmp<10){
                            filledGrid[i][j] = tmp;
                        }
                        else{
                            filledGrid[i][j] = 0;
                        }
                    }
                }
                filledGrid = Resolver.ResolveSudoku(filledGrid);
                for(int i=0;i<9;i++){
                    for(int j=0;j<9;j++){
                        grid[i][j].setText(( String.valueOf(filledGrid[i][j])));
                    }
                }
                pbResolving.setVisibility(View.INVISIBLE);
                btnResolve.setVisibility(View.VISIBLE);
            }
        });

        grid = new EditText[9][9];
        for(int i = 0;i<9;i++){
            for(int j = 0; j <9;j++){
                int id = getResources().getIdentifier("etCell"+i+j, "id", getPackageName());
                EditText tmp = (EditText) findViewById(id);
                tmp.setInputType(InputType.TYPE_CLASS_NUMBER);
                grid[i][j] = tmp;
            }
        }
    }
}
