package com.rifftyo.flixlist.bookmark

import android.content.Context
import com.rifftyo.flixlist.BookmarkModuleDependencies
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [BookmarkModuleDependencies::class],
)
interface BookmarkComponent {
    fun inject(bookmarkActivity: BookmarkActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(dependencies: BookmarkModuleDependencies): Builder
        fun build(): BookmarkComponent
    }
}