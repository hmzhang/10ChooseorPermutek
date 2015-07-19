package com.l649.richard.lotto649;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private Button submitbutton, deleteall;
    private EditText e1, e2, e3, e4, e5,e6, e7,e8,e9,e10, kvalue;
    private ListView list;
    private int[] lastnumbersarray;
    //private String[] combinations;
    private List<String> combinations;
    private ArrayAdapter<String> ArrayAdapter;
    private CheckBox permute,combine;
    private boolean isPermute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isPermute = false;
        submitbutton = (Button)findViewById(R.id.button);
        e1 = (EditText) findViewById(R.id.edit1);
        e2 = (EditText) findViewById(R.id.edit2);
        e3 = (EditText) findViewById(R.id.edit3);
        e4 = (EditText) findViewById(R.id.edit4);
        e5 = (EditText) findViewById(R.id.edit5);
        e6 = (EditText) findViewById(R.id.edit6);
        e7 = (EditText) findViewById(R.id.edit7);
        e8 = (EditText) findViewById(R.id.edit8);
        e9 = (EditText) findViewById(R.id.edit9);
        e10 = (EditText) findViewById(R.id.edit10);
        kvalue = (EditText) findViewById(R.id.kvalue);
        list = (ListView) findViewById(R.id.list);
        deleteall=(Button) findViewById(R.id.deleteall);
        combine = (CheckBox) findViewById(R.id.choosebox);
        permute = (CheckBox) findViewById(R.id.permutebox);

        combinations = new ArrayList<String>();
        ArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, combinations);
        list.setAdapter(ArrayAdapter);
        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayAdapter.clear();
                ArrayAdapter.notifyDataSetChanged();
                String x = "";
                int start = 0, end = 0, count = 1;
                ICombinatoricsVector<String> initialVector = Factory.createVector(new String[]{e1.getText().toString(), e2.getText().toString(), e3.getText().toString(),
                        e4.getText().toString(), e5.getText().toString(), e6.getText().toString(), e7.getText().toString(), e8.getText().toString(), e9.getText().toString(), e10.getText().toString()});
                // Create a simple combination generator to generate 3-combinations of the initial vector
                if(kvalue.getText().toString().matches("")){
                    Toast.makeText(getApplicationContext(), "Please enter a k-value.", Toast.LENGTH_SHORT);
                }else {
                    Generator<String> gen;
                    if(!isPermute) {
                        gen = Factory.createSimpleCombinationGenerator(initialVector, Integer.valueOf(kvalue.getText().toString()));

                    }
                    else{
                        gen=Factory.createPermutationWithRepetitionGenerator(initialVector, Integer.valueOf(kvalue.getText().toString()));
                    }
                    for (ICombinatoricsVector<String> combination : gen) {
                        x = combination.toString();
                        start = x.indexOf("[") + 1;
                        end = x.indexOf("]");
                        combinations.add(Integer.toString(count) + ". " + x.substring(start, end));
                        count++;
                    }
                    ArrayAdapter.notifyDataSetChanged();
                }
            }
        });
        deleteall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                e1.setText("");
                e2.setText("");
                e3.setText("");
                e4.setText("");
                e5.setText("");
                e6.setText("");
                e7.setText("");
                e8.setText("");
                e9.setText("");
                e10.setText("");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void checkedcheckbox(View v){
        switch(v.getId()){
            case R.id.choosebox:
                permute.setChecked(false);
                isPermute=false;
                break;
            case R.id.permutebox:
                combine.setChecked(false);
                isPermute=true;
                break;
            default:
                break;
        }

    }

}
