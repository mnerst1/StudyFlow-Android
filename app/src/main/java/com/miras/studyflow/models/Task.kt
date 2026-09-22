package com.miras.studyflow.models

data class Task(

    // Уникальный ID записи в базе данных.
    var id: Int = 0,

    // Название задания.
    var title: String,

    // Дополнительное описание.
    var description: String,

    // Категория:
    // Programming, University, Personal и т.д.
    var category: String,

    // LOW / MEDIUM / HIGH.
    var priority: String,

    // Дата выполнения.
    var deadline: String,

    // Выполнено задание или нет.
    var completed: Boolean = false
)