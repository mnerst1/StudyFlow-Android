package com.miras.studyflow

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.miras.studyflow.adapters.TaskAdapter
import com.miras.studyflow.database.DatabaseHelper
import com.miras.studyflow.models.Task
import com.miras.studyflow.utils.AppPreferences


class TasksActivity : AppCompatActivity() {

    private lateinit var database:
            DatabaseHelper

    private lateinit var adapter:
            TaskAdapter

    private lateinit var emptyState:
            View

    private lateinit var recycler:
            RecyclerView


    private var allTasks =
        mutableListOf<Task>()


    override fun onCreate(
        savedInstanceState: Bundle?
    ) {

        AppPreferences.applyTheme(this)
        AppPreferences.applyLanguage(this)

        super.onCreate(savedInstanceState)

        setContentView(
            R.layout.activity_tasks
        )


        setupInsets()


        database =
            DatabaseHelper(this)


        recycler =
            findViewById(
                R.id.recyclerTasks
            )


        emptyState =
            findViewById(
                R.id.emptyState
            )


        val search =
            findViewById<EditText>(
                R.id.editSearch
            )


        val buttonBack =
            findViewById<View>(
                R.id.buttonBack
            )


        buttonBack.setOnClickListener {

            animateClick(it)

            it.postDelayed(
                {
                    finish()
                },
                120
            )
        }


        recycler.layoutManager =
            LinearLayoutManager(this)


        adapter =
            TaskAdapter(

                mutableListOf(),

                onCompletedChanged = {

                    database.updateTask(it)
                },

                onDelete = {

                    database.deleteTask(
                        it.id
                    )

                    loadTasks()
                }
            )


        recycler.adapter =
            adapter


        search.addTextChangedListener(

            object : TextWatcher {

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }


                override fun onTextChanged(
                    s: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) {

                    filterTasks(
                        s.toString()
                    )
                }


                override fun afterTextChanged(
                    s: Editable?
                ) {
                }
            }
        )


        runEntranceAnimation()
    }


    private fun setupInsets() {

        val root =
            findViewById<View>(
                R.id.rootTasks
            )


        ViewCompat.setOnApplyWindowInsetsListener(
            root
        ) { view, insets ->

            val bars =
                insets.getInsets(
                    WindowInsetsCompat.Type.systemBars()
                )


            view.setPadding(
                bars.left + dp(20),
                bars.top,
                bars.right + dp(20),
                bars.bottom
            )


            insets
        }
    }


    override fun onResume() {

        super.onResume()

        loadTasks()
    }


    private fun loadTasks() {

        allTasks =
            database.getAllTasks()


        adapter.updateTasks(
            allTasks
        )


        updateEmptyState(
            allTasks.isEmpty()
        )
    }


    private fun filterTasks(
        query: String
    ) {

        val filtered =
            allTasks.filter {

                it.title.contains(
                    query,
                    ignoreCase = true
                )

                        ||

                        it.category.contains(
                            query,
                            ignoreCase = true
                        )
            }


        adapter.updateTasks(
            filtered.toMutableList()
        )


        updateEmptyState(
            filtered.isEmpty()
        )
    }


    private fun updateEmptyState(
        empty: Boolean
    ) {

        emptyState.visibility =
            if (empty)
                View.VISIBLE
            else
                View.GONE


        recycler.visibility =
            if (empty)
                View.GONE
            else
                View.VISIBLE
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
                R.id.headerTasks
            )


        val search =
            findViewById<View>(
                R.id.searchContainer
            )


        header.alpha = 0f
        header.translationY = -20f


        header.animate()
            .alpha(1f)
            .translationY(0f)
            .setDuration(350)
            .start()


        search.alpha = 0f
        search.translationY = 20f


        search.animate()
            .alpha(1f)
            .translationY(0f)
            .setStartDelay(80)
            .setDuration(400)
            .start()
    }


    private fun dp(
        value: Int
    ): Int {

        return (
                value *
                        resources.displayMetrics.density
                ).toInt()
    }
}