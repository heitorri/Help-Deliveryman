package br.com.vegait.helpdeliveryman.util

import java.text.SimpleDateFormat
import java.util.*

private const val DAY_MONTH = "d 'de' MMMM"

fun Date.getFormattedDateDayMonth(): String {
    val formatter = SimpleDateFormat(DAY_MONTH, Locale.getDefault())
    return formatter.format(this)
}
