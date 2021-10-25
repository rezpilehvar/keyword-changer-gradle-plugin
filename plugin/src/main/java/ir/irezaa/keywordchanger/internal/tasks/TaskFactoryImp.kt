package ir.irezaa.keywordchanger.internal.tasks


import ir.irezaa.keywordchanger.internal.tasks.factory.TaskFactory
import org.gradle.api.Task
import org.gradle.api.tasks.TaskProvider
import org.gradle.api.tasks.TaskContainer

class TaskFactoryImp(private val taskContainer: TaskContainer) : TaskFactory {
    override fun <T : Task> register(creationAction: TaskCreator<T>): TaskProvider<T> {
        val taskExecutor = TaskExecutor(creationAction)

        return taskContainer.register(creationAction.name, creationAction.type, taskExecutor)
    }
}