package sg.edu.np.mad.mad_recyclerview;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {
    public DataModel.TaskList taskList;
    //Constructor
    public Adapter(DataModel.TaskList tList) {
        this.taskList = tList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder viewHolder;
        View item = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.layout_list,
                parent,
                false);
        viewHolder = new ViewHolder(item);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        DataModel.Task task = taskList.getTaskAtIndex(position);
        holder.Tasks.setText(task.GetTaskDesc());
        holder.TaskCheck.setChecked(task.GetTaskCheck());
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String descOfSelectedItem = taskList.getTaskAtIndex(position).GetTaskDesc();
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Delete");
                builder.setMessage(Html.fromHtml(
                        "<div style='text-align: center'>Are you sure you want to delete<br /><b>" + descOfSelectedItem + "</b>?</div>"
                ));
                builder.setIcon(android.R.drawable.ic_menu_delete); //Search on this, its useful.
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        taskList.removeTask(position);
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("No",null);

                builder.create().show();
            }
        };
        holder.Tasks.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return taskList.countTasks();
    }
}
