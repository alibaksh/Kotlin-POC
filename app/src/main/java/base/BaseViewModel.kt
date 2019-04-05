package base

import android.arch.lifecycle.ViewModel
import injections.components.DaggerViewModelInjector
import injections.components.ViewModelInjector
import injections.modules.NetworkModule
import posts.EventListViewModel

/**
 * Created on 4/4/19.
 */
abstract class BaseViewModel : ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is EventListViewModel -> injector.inject(this)
        }
    }
}