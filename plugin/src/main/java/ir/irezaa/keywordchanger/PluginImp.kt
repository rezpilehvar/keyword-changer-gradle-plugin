package ir.irezaa.keywordchanger

import com.android.build.gradle.internal.tasks.factory.dependsOn
import ir.irezaa.keywordchanger.configs.Config
import ir.irezaa.keywordchanger.configs.ConfigManager
import ir.irezaa.keywordchanger.internal.tasks.TaskFactoryImp
import ir.irezaa.keywordchanger.internal.tasks.factory.TaskFactory
import ir.irezaa.keywordchanger.tasks.CopyTask
import ir.irezaa.keywordchanger.tasks.DeleteTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class PluginImp : Plugin<Project> {
    private lateinit var configManager: ConfigManager
    private lateinit var taskFactory: TaskFactory

    private lateinit var project: Project
    private lateinit var extension: PluginExtension

    override fun apply(project: Project) {
        this.project = project
        configManager = ConfigManager(project)
        taskFactory = TaskFactoryImp(project.tasks)

        extension = project.extensions.create(
            "keywordChanger",
            PluginExtension::class.java,
            configManager
        )

        project.afterEvaluate {
            configManager.configs.asMap.values.forEach {
                createTasksForConfig(it)
            }
        }
    }

    private fun createTasksForConfig(config: Config) {
        val copyAndFilterSrcFilesTask = taskFactory.register(
            CopyTask.CopyAndFilterCreator(
                "filterAndCopyToTemp${config.name.capitalize()}",
                project,
                config
            )
        )

        val deleteSrcFilesTask = taskFactory.register(
            DeleteTask.DeleteSrcFilesCreator(
                "deleteSrcFiles${config.name.capitalize()}",
                project,
                config
            )
        )
        deleteSrcFilesTask.dependsOn(copyAndFilterSrcFilesTask)

        val copyBackFilesTask = taskFactory.register(
            CopyTask.CopyBackCreator(
                "copyBackFiles${config.name.capitalize()}",
                config
            )
        )
        copyBackFilesTask.dependsOn(deleteSrcFilesTask)

        val deleteTmpFilesTask = taskFactory.register(
            DeleteTask.DeleteTmpFilesCreator(
                "deleteTmpFiles${config.name.capitalize()}",
                project,
                config
            )
        )
        deleteTmpFilesTask.dependsOn(copyBackFilesTask)


        val changeKeywordsTask = project.tasks.register("changeKeywords${config.name.capitalize()}") {
            it.dependsOn(deleteTmpFilesTask)
        }

        if (extension.addConfigsToAndroidRelease) {
            val task = project.tasks.named("assembleRelease")
            task.dependsOn(changeKeywordsTask)
        }
    }
}