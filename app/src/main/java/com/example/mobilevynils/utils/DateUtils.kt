package com.example.mobilevynils.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    private val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    private val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    fun formatDate(dateStr: String?): String? {
        if (dateStr == null) {
            return null
        }

        try {
            val date = inputFormat.parse(dateStr)
            return outputFormat.format(date!!)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }
}