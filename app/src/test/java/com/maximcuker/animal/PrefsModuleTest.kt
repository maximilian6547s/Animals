package com.maximcuker.animal

import android.app.Application
import com.maximcuker.animal.di.PrefsModule
import com.maximcuker.animal.util.SharedPreferencesHelper

class PrefsModuleTest(val mockPrefs: SharedPreferencesHelper) : PrefsModule(){
    override fun provideSharedPreferences(app: Application): SharedPreferencesHelper {
        return mockPrefs
    }
}