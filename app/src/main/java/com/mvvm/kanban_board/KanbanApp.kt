package com.mvvm.kanban_board

import android.app.Application
import appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class KanbanApp: Application()
{
        override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@KanbanApp)
            modules(appModule)
        }

    }
}





