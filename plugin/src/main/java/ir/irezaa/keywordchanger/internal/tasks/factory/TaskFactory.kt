package ir.irezaa.keywordchanger.internal.tasks.factory

import ir.irezaa.keywordchanger.internal.tasks.TaskCreator
import org.gradle.api.Task
import org.gradle.api.tasks.TaskProvider

interface TaskFactory {
    fun <T : Task> register(
        creationAction: TaskCreator<T>
    ): TaskProvider<T>
}