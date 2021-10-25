package ir.irezaa.keywordchanger

import ir.irezaa.keywordchanger.configs.Config
import ir.irezaa.keywordchanger.configs.ConfigManager
import ir.irezaa.keywordchanger.internal.tasks.TaskFactoryImp
import ir.irezaa.keywordchanger.internal.tasks.factory.TaskFactory
import ir.irezaa.keywordchanger.tasks.CopyAndFilterTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class PluginImp : Plugin<Project> {
    private lateinit var configManager: ConfigManager
    private lateinit var taskFactory: TaskFactory

    private lateinit var project: Project

    override fun apply(project: Project) {
        this.project = project
        configManager = ConfigManager(project)
        taskFactory = TaskFactoryImp(project.tasks)

        val extension: PluginExtension = project.extensions.create(
            "keywordChanger",
            PluginExtension::class.java,
            configManager
        )

        project.afterEvaluate {
            configManager.configs.asMap.values.forEach {
                createTaskForConfig(it)
            }
        }
    }

    private fun createTaskForConfig(config: Config) {
        taskFactory.register(
            CopyAndFilterTask.Creator(
                "changeKeywords${config.name.capitalize()}",
                config
            )
        )
    }
}