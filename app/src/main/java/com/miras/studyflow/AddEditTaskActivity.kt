package com.miras.studyflow

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import com.miras.studyflow.database.DatabaseHelper
import com.miras.studyflow.models.Task
import com.miras.studyflow.utils.AppPreferences

import java.util.Calendar


class AddEditTaskActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {

        AppPreferences.applyTheme(this)
        AppPreferences.applyLanguage(this)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_add_edit_task)

        setupInsets()


        databaseHelper =
            DatabaseHelper(this)


        val buttonBack =
            findViewById<View>(
                R.id.buttonBack
            )


        val editTitle =
            findViewById<EditText>(
                R.id.editTitle
            )


        val editDescription =
            findViewById<EditText>(
                R.id.editDescription
            )


        val editDeadline =
            findViewById<EditText>(
                R.id.editDeadline
            )


        val spinnerCategory =
            findViewById<Spinner>(
                R.id.spinnerCategory
            )


        val spinnerPriority =
            findViewById<Spinner>(
                R.id.spinnerPriority
            )


        val buttonSave =
            findViewById<Button>(
                R.id.buttonSave
            )


        // Назад на предыдущий экран.
        buttonBack.setOnClickListener {

            animateClick(it)

            it.postDelayed(
                {
                    finish()
                },
                120
            )
        }


        val categories =
            arrayOf(
                "Programming",
                "University",
                "Homework",
                "Project",
                "Personal"
            )


        spinnerCategory.adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                categories
            )


        val priorities =
            arrayOf(
                "LOW",
                "MEDIUM",
                "HIGH"
            )


        spinnerPriority.adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                priorities
            )


        spinnerPriority.setSelection(1)


        // При нажатии на Deadline открываем календарь.
        editDeadline.setOnClickListener {

            showDatePicker(
                editDeadline
            )
        }


        buttonSave.setOnClickListener {

            val title =
                editTitle.text
                    .toString()
                    .trim()


            if (title.isEmpty()) {

                editTitle.error =
                    "Enter task title"

                return@setOnClickListener
            }


            val task =
                Task(
                    title = title,

                    description =
                        editDescription.text
                            .toString(),

                    category =
                        spinnerCategory
                            .selectedItem
                            .toString(),

                    priority =
                        spinnerPriority
                            .selectedItem
                            .toString(),

                    deadline =
                        editDeadline.text
                            .toString(),

                    completed = false
                )


            val result =
                databaseHelper.addTask(task)


            if (result != -1L) {

                Toast.makeText(
                    this,
                    "Task created!",
                    Toast.LENGTH_SHORT
                ).show()

                finish()
            }
        }


        runEntranceAnimation()
    }


    private fun setupInsets() {

        val root =
            findViewById<View>(
                R.id.rootAddTask
            )


        ViewCompat.setOnApplyWindowInsetsListener(
            root
        ) { view, insets ->

            val bars =
                insets.getInsets(
                    WindowInsetsCompat.Type.systemBars()
                )


            view.setPadding(
                bars.left,
                bars.top,
                bars.right,
                bars.bottom
            )

            insets
        }
    }


    private fun showDatePicker(
        editDeadline: EditText
    ) {

        val calendar =
            Calendar.getInstance()


        DatePickerDialog(
            this,

            { _, year, month, day ->

                val formattedDate =
                    "%02d.%02d.%04d".format(
                        day,
                        month + 1,
                        year
                    )


                editDeadline.setText(
                    formattedDate
                )
            },

            calendar.get(
                Calendar.YEAR
            ),

            calendar.get(
                Calendar.MONTH
            ),

            calendar.get(
                Calendar.DAY_OF_MONTH
            )

        ).show()
    }


    private fun animateClick(
        view: View
    ) {

        view.animate()
            .scaleX(0.90f)
            .scaleY(0.90f)
            .setDuration(80)
            .withEndAction {

                view.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(140)
                    .start()
            }
            .start()
    }


    private fun runEntranceAnimation() {

        val header =
            findViewById<View>(
                R.id.headerAddTask
            )


        val card =
            findViewById<View>(
                R.id.cardTaskForm
            )


        val save =
            findViewById<View>(
                R.id.buttonSave
            )


        val views =
            listOf(
                header,
                card,
                save
            )


        views.forEachIndexed {
                index,
                view ->

            view.alpha = 0f
            view.translationY = 30f


            view.animate()
                .alpha(1f)
                .translationY(0f)
                .setStartDelay(
                    index * 80L
                )
                .setDuration(400)
                .start()
        }
    }
}