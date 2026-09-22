package com.miras.studyflow.utils

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat


object AppPreferences {

    private const val PREFS_NAME =
        "studyflow_preferences"

    private const val KEY_LANGUAGE =
        "language"

    private const val KEY_THEME =
        "theme"


    // ---------------------------------------------
    // LANGUAGE
    // ---------------------------------------------

    fun saveLanguage(
        context: Context,
        language: String
    ) {

        context
            .getSharedPreferences(
                PREFS_NAME,
                Context.MODE_PRIVATE
            )
            .edit()
            .putString(
                KEY_LANGUAGE,
                language
            )
            .apply()
    }


    fun getLanguage(
        context: Context
    ): String {

        return context
            .getSharedPreferences(
                PREFS_NAME,
                Context.MODE_PRIVATE
            )
            .getString(
                KEY_LANGUAGE,
                "en"
            ) ?: "en"
    }


    fun applyLanguage(
        context: Context
    ) {

        val language =
            getLanguage(context)


        val localeList =
            LocaleListCompat
                .forLanguageTags(
                    language
                )


        AppCompatDelegate
            .setApplicationLocales(
                localeList
            )
    }


    fun setLanguage(
        context: Context,
        language: String
    ) {

        saveLanguage(
            context,
            language
        )


        val localeList =
            LocaleListCompat
                .forLanguageTags(
                    language
                )


        AppCompatDelegate
            .setApplicationLocales(
                localeList
            )
    }


    // ---------------------------------------------
    // THEME
    // ---------------------------------------------

    fun saveTheme(
        context: Context,
        theme: String
    ) {

        context
            .getSharedPreferences(
                PREFS_NAME,
                Context.MODE_PRIVATE
            )
            .edit()
            .putString(
                KEY_THEME,
                theme
            )
            .apply()
    }


    fun getTheme(
        context: Context
    ): String {

        return context
            .getSharedPreferences(
                PREFS_NAME,
                Context.MODE_PRIVATE
            )
            .getString(
                KEY_THEME,
                "system"
            ) ?: "system"
    }


    fun applyTheme(
        context: Context
    ) {

        when (
            getTheme(context)
        ) {

            "light" ->

                AppCompatDelegate
                    .setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_NO
                    )


            "dark" ->

                AppCompatDelegate
                    .setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_YES
                    )


            else ->

                AppCompatDelegate
                    .setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                    )
        }
    }


    fun setTheme(
        context: Context,
        theme: String
    ) {

        saveTheme(
            context,
            theme
        )


        applyTheme(context)
    }
}