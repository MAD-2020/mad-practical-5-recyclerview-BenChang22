package sg.edu.np.mad.mad_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.CheckedInputStream;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Adapter adapter;
    private List<DataModel> dataModels;
    private TextView TaskDesc;
    private Button TaskAdd;
    private CheckBox check;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataModel.TaskList taskList = new DataModel.TaskList();
        taskList.addTask("Buy milk");
        taskList.addTask("Send postage");
        taskList.addTask("Buy Android development book");

        TaskDesc = (EditText) findViewById(R.id.taskDesc);
        TaskAdd = (Button) findViewById(R.id.taskAdd);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        TaskAdd.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                String newTaskDescription = TaskDesc.getText().toString();

                TaskDesc.setText("");

                if (newTaskDescription.length() > 0) {
                   adapter.taskList.addTask(newTaskDescription);
                    adapter.notifyDataSetChanged();
                   showNewEntry(recyclerView, adapter.taskList);

                }
            }
        });


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataModels = new ArrayList<>();


        recyclerView.setAdapter(adapter);


        adapter = new Adapter(taskList);
        LinearLayoutManager taskLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(taskLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }




    /**
     * Upon calling this method, the keyboard will retract
     * and the recyclerview will scroll to the last item
     *  @param rv RecyclerView for scrolling to
     * @param taskList tasklist that was passed into RecyclerView
     */
    private void showNewEntry(RecyclerView rv, DataModel.TaskList taskList){
        //scroll to the last item of the recyclerview
        rv.scrollToPosition(taskList.countTasks() - 1);

        //auto hide keyboard after entry
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(rv.getWindowToken(), 0);
    }
}
