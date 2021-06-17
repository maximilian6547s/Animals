package com.maximcuker.animal

import com.maximcuker.animal.di.ApiModule
import com.maximcuker.animal.model.AnimalApi
import com.maximcuker.animal.model.AnimalApiService

class ApiModuleTest(val mockService:AnimalApiService): ApiModule() {

    override fun provideAnimalApiService(): AnimalApiService {
        return mockService
    }
}