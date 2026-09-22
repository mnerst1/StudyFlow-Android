package com.miras.studyflow.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.miras.studyflow.R
import com.miras.studyflow.models.Task


class TaskAdapter(

    private var tasks:
    MutableList<Task>,

    private val onCompletedChanged:
        (Task) -> Unit,

    private val onDelete:
        (Task) -> Unit

) : RecyclerView.Adapter<
        TaskAdapter.TaskViewHolder>() {


    class TaskViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(
        itemView
    ) {

        val title:
                TextView =
            itemView.findViewById(
                R.id.textTaskTitle
            )


        val category:
                TextView =
            itemView.findViewById(
                R.id.textTaskCategory
            )


        val priority:
                TextView =
            itemView.findViewById(
                R.id.textTaskPriority
            )


        val deadline:
                TextView =
            itemView.findViewById(
                R.id.textDeadline
            )


        val completed:
                CheckBox =
            itemView.findViewById(
                R.id.checkCompleted
            )


        val delete:
                Button =
            itemView.findViewById(
                R.id.buttonDelete
            )
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TaskViewHolder {


        val view =

            LayoutInflater
                .from(parent.context)
                .inflate(

                    R.layout.item_task,

                    parent,

                    false
                )


        return TaskViewHolder(
            view
        )
    }


    override fun onBindViewHolder(
        holder: TaskViewHolder,
        position: Int
    ) {

        val task =
            tasks[position]


        holder.title.text =
            task.title


        holder.category.text =
            "📚 ${task.category}"


        holder.priority.text =
            "Priority: ${task.priority}"


        holder.deadline.text =
            "📅 ${task.deadline}"


        // Цвет приоритета.

        when (
            task.priority
        ) {

            "HIGH" ->

                holder.priority
                    .setTextColor(
                        Color.parseColor(
                            "#F87171"
                        )
                    )


            "MEDIUM" ->

                holder.priority
                    .setTextColor(
                        Color.parseColor(
                            "#FACC15"
                        )
                    )


            else ->

                holder.priority
                    .setTextColor(
                        Color.parseColor(
                            "#4ADE80"
                        )
                    )
        }


        holder.completed
            .setOnCheckedChangeListener(
                null
            )


        holder.completed
            .isChecked =
            task.completed


        holder.completed
            .setOnCheckedChangeListener {

                    _,
                    checked ->


                task.completed =
                    checked


                onCompletedChanged(
                    task
                )
            }


        holder.delete
            .setOnClickListener {

                onDelete(
                    task
                )
            }
    }


    override fun getItemCount():
            Int {

        return tasks.size
    }


    fun updateTasks(
        newTasks:
        MutableList<Task>
    ) {

        tasks =
            newTasks

        notifyDataSetChanged()
    }
}