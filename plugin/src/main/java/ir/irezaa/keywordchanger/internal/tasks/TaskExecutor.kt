package ir.irezaa.keywordchanger.internal.tasks

import org.gradle.api.Action
import org.gradle.api.Task

class TaskExecutor<T : Task>(private val creator : TaskCreator<T>) : Action<T> {
    override fun execute(task: T) {
        creator.configure(task)
    }
}