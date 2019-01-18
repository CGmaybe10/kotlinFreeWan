package com.cgmaybe.kotlinfreewan.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * 获取和当前时间的时间间隔
 * @param dateTime 时间戳
 */
fun getTimeInterval(dateTime: Long, targetFormat: String): String {
    val sourceDate = Date(dateTime)
    val curDate = Date()
    val between = (curDate.time - sourceDate.time) / 1000//除以1000是为了转换成秒
    val day = between / (24 * 3600)
    val hour = between % (24 * 3600) / 3600
    val minute = between % 3600 / 60
    if (day == 0L && hour == 0L && minute == 0L) {
        return "刚刚"
    } else if (day == 0L && hour == 0L) {
        return minute.toString() + "分钟前"
    } else if (day == 0L) {
        return hour.toString() + "小时前"
    } else {
        val newSDF = SimpleDateFormat(targetFormat, Locale.getDefault())
        return newSDF.format(sourceDate)
    }
}

/**
 * 获取和当前时间的时间间隔
 * @param dateTime 格式：2016-02-01 12:23
 */
fun getTimeInterval(dateTime: String, sourceFormat: String, targetFormat: String): String {
    try {
        val dfs = SimpleDateFormat(sourceFormat, Locale.getDefault())
        val sourceDate = dfs.parse(dateTime)
        val curDate = Date()
        val between = (curDate.time - sourceDate.time) / 1000//除以1000是为了转换成秒
        val day = between / (24 * 3600)
        val hour = between % (24 * 3600) / 3600
        val minute = between % 3600 / 60
        if (day == 0L && hour == 0L && minute == 0L) {
            return "刚刚"
        } else if (day == 0L && hour == 0L) {
            return minute.toString() + "分钟前"
        } else if (day == 0L) {
            return hour.toString() + "小时前"
        } else {
            val newSDF = SimpleDateFormat(targetFormat, Locale.getDefault())
            return newSDF.format(sourceDate)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return "时间解析异常"
}

/**
 * 转换日期格式
 */
fun transformDateFormat(date: Long, targetFormat: String): String {
    val sourceDate = Date(date)
    val targetDfs = SimpleDateFormat(targetFormat, Locale.getDefault())
    return targetDfs.format(sourceDate)
}

/**
 * 转换日期格式
 */
fun transformDateFormat(date: String, sourceFormat: String, targetFormat: String): String? {
    try {
        val sourceDfs = SimpleDateFormat(sourceFormat, Locale.getDefault())
        val sourceDate = sourceDfs.parse(date)
        val targetDfs = SimpleDateFormat(targetFormat, Locale.getDefault())
        return targetDfs.format(sourceDate)
    } catch (e: ParseException) {
        e.printStackTrace()
    }

    return null
}

/**
 * 判断是不是今天
 */
fun isToday(date: String, dateFormat: String): Boolean {
    try {
        val sourceDfs = SimpleDateFormat(dateFormat, Locale.getDefault())
        val compareDate = sourceDfs.parse(date)
        val compareCalendar = Calendar.getInstance()
        compareCalendar.time = compareDate
        val curCalendar = Calendar.getInstance()
        return (compareCalendar.get(Calendar.YEAR) == curCalendar.get(Calendar.YEAR)
                && compareCalendar.get(Calendar.MONTH) == curCalendar.get(Calendar.MONTH) &&
                compareCalendar.get(Calendar.DAY_OF_MONTH) == curCalendar.get(Calendar.DAY_OF_MONTH))
    } catch (e: ParseException) {
        e.printStackTrace()
    }

    return false
}