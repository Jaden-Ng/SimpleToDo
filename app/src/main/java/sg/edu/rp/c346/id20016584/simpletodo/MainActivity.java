package sg.edu.rp.c346.id20016584.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etElement;
    Button btnadd, btnclear, btndel;
    ArrayList<String> arraytask;
    ArrayAdapter<String> adaptertask;
    ListView lvtask;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etElement=findViewById(R.id.editTextAdd);
        btnadd=findViewById(R.id.buttonadd);
        btnclear=findViewById(R.id.buttonclear);
        lvtask=findViewById(R.id.listviewTask);
        spinner=findViewById(R.id.spinner);
        btndel=findViewById(R.id.buttondel);

        arraytask=new ArrayList<String>();
        adaptertask=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arraytask);
        lvtask.setAdapter(adaptertask);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strtext= etElement.getText().toString();
                arraytask.add(strtext);
                adaptertask.notifyDataSetChanged();
            }
        });
        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arraytask.clear();
                adaptertask.notifyDataSetChanged();
            }
        });
        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adaptertask.getCount()!=0) {
                    if(Integer.parseInt(etElement.getText().toString())<=adaptertask.getCount()-1) {
                        int index = Integer.parseInt(etElement.getText().toString());
                        arraytask.remove(index);
                        adaptertask.notifyDataSetChanged();
                    }else{
                        Toast.makeText(MainActivity.this,"Wrong index number", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this,"You don't have any task to remove", Toast.LENGTH_LONG).show();
                }
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        etElement.setHint("Type in a new task here");
                        break;
                    case 1:
                        etElement.setHint("Type in the index of a task to be removed");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}