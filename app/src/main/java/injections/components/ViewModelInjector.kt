package injections.components

import dagger.Component
import injections.modules.NetworkModule
import posts.EventListViewModel
import javax.inject.Singleton

/**
 * Created on 4/4/19.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(eventListViewModel: EventListViewModel)

    @Component.Builder
    interface Builder {

        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}