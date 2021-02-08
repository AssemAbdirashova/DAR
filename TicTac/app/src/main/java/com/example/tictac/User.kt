package com.example.tictac
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
@Parcelize
data class User(
    var id: Int? = null,
    val name : String? = null,
    var winCount: Int? = 0,
    var loseCount: Int? = 0
   ): Parcelable {
    companion object {
        private var counter = 0
    }

    init {
        id = counter++
    }
    fun increaseLose(){
        this.loseCount = loseCount!! + 1
    }
    fun increaseWin(){
        this.winCount = winCount!! + 1
    }
}