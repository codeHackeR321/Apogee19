package com.anenigmatic.apogee19.screens.shared.data.room

import androidx.room.TypeConverter
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime

class AppTypeConverters {

    @TypeConverter
    fun fromLocalDateToString(ldt: LocalDate): String {
        return ldt.toString()
    }

    @TypeConverter
    fun fromStringToLocalDate(str: String): LocalDate {
        return LocalDate.parse(str)
    }

    @TypeConverter
    fun fromLocalTimeToString(ltm: LocalTime): String {
        return ltm.toString()
    }

    @TypeConverter
    fun fromStringToLocalTime(str: String): LocalTime {
        return LocalTime.parse(str)
    }
}