package com.example.sotsukenappproject

import PrefEntity
import UserEntity
import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    @Query("select pref_force_1 from prefInfo where pref_id = :id")
    fun getPrefForce1(id: Int): Long

    @Query("select pref_force_2 from prefInfo where pref_id = :id")
    fun getPrefForce2(id: Int): Long

    @Query("update userInfo set user_name = :user_name where user_id = :user_id")
    fun editName(user_id: Int, user_name: String): Int

    @Query("insert into userInfo(user_name, user_force) values (:user_name,100000)")
    fun createUser(user_name: String): Int

    @Query("insert into attackHistory(user_id, attack_pref_id, execute_time, spend_time) values(:user_id, :pref_id, :execute_time, :spend_time)")
    fun createAttackHistory(user_id: Int, pref_id: Int, execute_time: Long, spend_time: Long): Int

    @Query("insert into campHistory(user_id, camp_id, execute_time, add_user_force) values(:user_id, :camp_id, :execute_time, :add_user_force)")
    fun createCampHistory(user_id: Int, camp_id: Int, execute_time: Long, add_user_force: Long): Int



}