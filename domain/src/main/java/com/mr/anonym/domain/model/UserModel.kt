package com.mr.anonym.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("user")
data class UserModel(
    @PrimaryKey(autoGenerate = false)
    val id:Int = generateID(),
    val login:String,
    val phone:String,
    val city:String,
){
    companion object{
        fun generateID():Int {
            val integer = 0..999999
            return integer.random().hashCode()
        }
    }
}
