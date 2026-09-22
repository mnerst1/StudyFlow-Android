package com.miras.studyflow


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import com.google.android.material.button.MaterialButton

import com.miras.studyflow.database.DatabaseHelper
import com.miras.studyflow.utils.AppPreferences


class MainActivity :
    AppCompatActivity() {


    private lateinit var databaseHelper:
            DatabaseHelper


    private lateinit var textTotalNumber:
            TextView


    private lateinit var textCompletedNumber:
            TextView


    override fun onCreate(
        savedInstanceState: Bundle?
    ) {

        AppPreferences.applyTheme(this)

        AppPreferences.applyLanguage(this)


        super.onCreate(
            savedInstanceState
        )


        setContentView(
            R.layout.activity_main
        )


        setupInsets()


        databaseHelper =
            DatabaseHelper(this)


        textTotalNumber =
            findViewById(
                R.id.textTotalNumber
            )


        textCompletedNumber =
            findViewById(
                R.id.textCompletedNumber
            )


        val buttonTasks =
            findViewById<MaterialButton>(
                R.id.buttonTasks
            )


        val buttonAddTask =
            findViewById<MaterialButton>(
                R.id.buttonAddTask
            )


        val buttonSettings =
            findViewById<MaterialButton>(
                R.id.buttonSettings
            )


        buttonTasks.setOnClickListener {

            animateClick(it)


            startActivity(

                Intent(
                    this,
                    TasksActivity::class.java
                )
            )
        }


        buttonAddTask.setOnClickListener {

            animateClick(it)


            startActivity(

                Intent(
                    this,
                    AddEditTaskActivity::class.java
                )
            )
        }


        buttonSettings.setOnClickListener {

            animateClick(it)


            startActivity(

                Intent(
                    this,
                    SettingsActivity::class.java
                )
            )
        }


        runEntranceAnimation()
    }


    private fun setupInsets() {

        val root =
            findViewById<View>(
                R.id.rootMain
            )


        ViewCompat
            .setOnApplyWindowInsetsListener(
                root
            ) {

                    view,
                    insets ->


                val bars =
                    insets.getInsets(
                        WindowInsetsCompat
                            .Type
                            .systemBars()
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


    override fun onResume() {

        super.onResume()

        updateStatistics()
    }


    private fun updateStatistics() {

        val total =
            databaseHelper
                .getTotalTaskCount()


        val completed =
            databaseHelper
                .getCompletedTaskCount()


        textTotalNumber.text =
            total.toString()


        textCompletedNumber.text =
            completed.toString()
    }


    // Небольшая анимация кнопки.

    private fun animateClick(
        view: View
    ) {

        view.animate()

            .scaleX(0.96f)

            .scaleY(0.96f)

            .setDuration(90)

            .withEndAction {

                view.animate()

                    .scaleX(1f)

                    .scaleY(1f)

                    .setDuration(130)

                    .start()
            }

            .start()
    }


    // Анимация появления элементов.

    private fun runEntranceAnimation() {

        val title =
            findViewById<View>(
                R.id.textTitle
            )


        val subtitle =
            findViewById<View>(
                R.id.textSubtitle
            )


        val statistics =
            findViewById<View>(
                R.id.statisticsContainer
            )


        val tasks =
            findViewById<View>(
                R.id.buttonTasks
            )


        val add =
            findViewById<View>(
                R.id.buttonAddTask
            )


        val settings =
            findViewById<View>(
                R.id.buttonSettings
            )


        val views =
            listOf(

                title,

                subtitle,

                statistics,

                tasks,

                add,

                settings
            )


        views.forEachIndexed {

                index,
                view ->


            view.alpha =
                0f


            view.translationY =
                35f


            view.animate()

                .alpha(1f)

                .translationY(0f)

                .setStartDelay(
                    index * 70L
                )

                .setDuration(420)

                .start()
        }
    }
}