package com.miras.studyflow


import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import com.miras.studyflow.utils.AppPreferences


class SettingsActivity :
    AppCompatActivity() {


    private var firstLanguageSelection =
        true


    private var firstThemeSelection =
        true


    override fun onCreate(
        savedInstanceState: Bundle?
    ) {

        AppPreferences.applyTheme(this)

        AppPreferences.applyLanguage(this)


        super.onCreate(
            savedInstanceState
        )


        setContentView(
            R.layout.activity_settings
        )


        setupInsets()


        setupLanguage()


        setupTheme()
    }


    private fun setupInsets() {

        val root =
            findViewById<View>(
                R.id.rootSettings
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


    private fun setupLanguage() {

        val spinner =
            findViewById<Spinner>(
                R.id.spinnerLanguage
            )


        val languages =
            arrayOf(

                "🇬🇧 English",

                "🇰🇿 Қазақша",

                "🇷🇺 Русский"
            )


        spinner.adapter =
            ArrayAdapter(

                this,

                android.R.layout
                    .simple_spinner_dropdown_item,

                languages
            )


        when (
            AppPreferences
                .getLanguage(this)
        ) {

            "kk" ->
                spinner.setSelection(1)


            "ru" ->
                spinner.setSelection(2)


            else ->
                spinner.setSelection(0)
        }


        spinner.onItemSelectedListener =

            object :
                AdapterView
                .OnItemSelectedListener {


                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                    if (
                        firstLanguageSelection
                    ) {

                        firstLanguageSelection =
                            false

                        return
                    }


                    val language =
                        when (
                            position
                        ) {

                            1 -> "kk"

                            2 -> "ru"

                            else -> "en"
                        }


                    AppPreferences
                        .setLanguage(
                            this@SettingsActivity,
                            language
                        )
                }


                override fun onNothingSelected(
                    parent: AdapterView<*>?
                ) {
                }
            }
    }


    private fun setupTheme() {

        val spinner =
            findViewById<Spinner>(
                R.id.spinnerTheme
            )


        val themes =
            arrayOf(

                getString(
                    R.string.system_theme
                ),

                getString(
                    R.string.light_theme
                ),

                getString(
                    R.string.dark_theme
                )
            )


        spinner.adapter =
            ArrayAdapter(

                this,

                android.R.layout
                    .simple_spinner_dropdown_item,

                themes
            )


        when (
            AppPreferences
                .getTheme(this)
        ) {

            "light" ->
                spinner.setSelection(1)


            "dark" ->
                spinner.setSelection(2)


            else ->
                spinner.setSelection(0)
        }


        spinner.onItemSelectedListener =

            object :
                AdapterView
                .OnItemSelectedListener {


                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                    if (
                        firstThemeSelection
                    ) {

                        firstThemeSelection =
                            false

                        return
                    }


                    val theme =
                        when (
                            position
                        ) {

                            1 ->
                                "light"

                            2 ->
                                "dark"

                            else ->
                                "system"
                        }


                    AppPreferences
                        .setTheme(
                            this@SettingsActivity,
                            theme
                        )
                }


                override fun onNothingSelected(
                    parent: AdapterView<*>?
                ) {
                }
            }
    }
}