package com.maximcuker.animal

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.maximcuker.animal.di.ApiModule
import com.maximcuker.animal.di.AppModule
import com.maximcuker.animal.di.DaggerViewModelComponent
import com.maximcuker.animal.model.Animal
import com.maximcuker.animal.model.AnimalApiService
import com.maximcuker.animal.util.SharedPreferencesHelper
import com.maximcuker.animal.viewmodel.ListViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor

class ListViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var animalService:AnimalApiService

    @Mock
    lateinit var prefs:SharedPreferencesHelper

    val application = Mockito.mock(Application::class.java)

    var listViewModel = ListViewModel(application,true)

    private val key = "Test key"

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        DaggerViewModelComponent.builder()
            .appModule(AppModule(application))
            .apiModule(ApiModuleTest(animalService))
            .prefsModule(PrefsModuleTest(prefs))
            .build()
            .inject(listViewModel)
    }

    @Test
    fun getAnimalsSuccess() {
        Mockito.`when`(prefs.getApiKey()).thenReturn(key)
        val animal = Animal("cow", null, null, null,null,null,null)
        val animalList = listOf<Animal>(animal)
        val testSingle = Single.just(animalList)

        Mockito.`when`(animalService.getAnimals(key)).thenReturn(testSingle)

        listViewModel.refresh()

        Assert.assertEquals(1, listViewModel.animals.value?.size)
        Assert.assertEquals(false, listViewModel.loadError.value)
        Assert.assertEquals(false, listViewModel.loading.value)

    }

    @Before
    fun setupRxSchedulers() {
        val immediate = object : Scheduler() {
            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { it.run() }, true)
            }

        }

        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler -> immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> immediate }
    }
}