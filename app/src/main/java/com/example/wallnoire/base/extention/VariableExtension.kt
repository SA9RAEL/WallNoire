package com.example.wallnoire.base.extention

fun Boolean?.orFalse(): Boolean = this ?: false
fun String?.orEmpty(): String = this ?: ""