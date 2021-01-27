package com.example.advancedstudentlist.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
@Parcelize
data class Student(
    var id: Int? = null,
    val name : String? = null,
    var surname: String? = "default",
    var grade: Double? = 0.00,
    var image: String? = null): Parcelable{
    companion object {
        private var counter = 0
    }
    init {
         id = counter++
    }
}
