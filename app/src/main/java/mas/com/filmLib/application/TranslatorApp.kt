package mas.com.filmLib.application

import android.app.Application
import mas.com.filmLib.di.application
import mas.com.filmLib.di.mainScreen
import org.koin.core.context.startKoin

class TranslatorApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(application, mainScreen))
        }
    }
}
