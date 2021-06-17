package com.maximcuker.animal.di

import android.app.Application
import com.maximcuker.animal.util.SharedPreferencesHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PrefsModule {

    @Provides
    @Singleton //we need single instance of shared preferences helper class
    fun provideSharedPreferences(app:Application): SharedPreferencesHelper {
        return SharedPreferencesHelper(app)
    }
}