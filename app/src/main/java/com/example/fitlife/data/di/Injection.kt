package com.example.fitlife.data.di

import android.content.Context
import com.example.fitlife.data.Repository
import com.example.fitlife.data.pref.UserPreference
import com.example.fitlife.data.pref.dataStore

object Injection {
    fun provideRepository(context: Context): Repository {
        val pref = UserPreference.getInstance(context.dataStore)
        return Repository.getInstance(pref)
    }
}