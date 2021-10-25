package ir.irezaa.keywordchanger.internal.tasks

import org.gradle.api.Task
import org.gradle.api.tasks.TaskProvider

interface TaskCreator<TaskT : Task> : TaskConfigAction<TaskT>, TaskInformation<TaskT>,
    PreConfigAction,TaskProviderCallback<TaskT> {
    override fun preConfigure(taskName: String) {
        // default does nothing
    }

    override fun handleProvider(taskProvider: TaskProvider<TaskT>) {
        // default does nothing
    }
}

interface PreConfigAction {
    fun preConfigure(taskName: String)
}

interface TaskConfigAction<TaskT : Task> {

    fun configure(task: TaskT)
}

interface TaskInformation<TaskT : Task> {
    val name: String

    val type: Class<TaskT>
}
interface TaskProviderCallback<TaskT: Task> {
    fun handleProvider(taskProvider: TaskProvider<TaskT>)
}