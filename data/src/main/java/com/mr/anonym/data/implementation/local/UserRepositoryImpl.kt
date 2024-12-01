package com.mr.anonym.data.implementation.local

import com.mr.anonym.data.local.room.UserDao
import com.mr.anonym.domain.repository.local.UserRepository

class UserRepositoryImpl(private val userDao: UserDao):UserRepository {

}