package sg.edu.rp.c346.id20037834.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert;
    Button btnGetTask;
    EditText etTask;
    EditText etDate;
    ListView lvTask;
    ArrayList<String> alTasks;
    ArrayAdapter aaTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTask = findViewById(R.id.etTaskName);
        etDate = findViewById(R.id.etDate);
        btnInsert = findViewById(R.id.btnInsert);
        btnGetTask = findViewById(R.id.btnSetTask);
        lvTask = findViewById(R.id.lvTask);
        alTasks = new ArrayList<String>();
        aaTasks = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,alTasks);
        lvTask.setAdapter(aaTasks);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);
                db.insertTask(etTask.getText().toString(), etDate.getText().toString());
            }
        });

        btnGetTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                ArrayList<Task> data = db.getTasks();
                db.close();

                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    alTasks.add(data.get(i).toString());
                }
                aaTasks.notifyDataSetChanged();
            }
        });
    }
}