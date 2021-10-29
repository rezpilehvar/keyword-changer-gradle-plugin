package ir.irezaa.keywordchanger.tasks

import ir.irezaa.keywordchanger.configs.Config
import ir.irezaa.keywordchanger.internal.tasks.TaskCreator
import org.gradle.api.Project
import org.gradle.api.tasks.Delete
import java.io.File
import java.lang.Exception

abstract class DeleteTask : Delete() {

    class DeleteSrcFilesCreator(
        override val name: String,
        private val project: Project,
        private val config: Config
    ) : TaskCreator<DeleteTask> {
        override val type: Class<DeleteTask>
            get() = DeleteTask::class.java

        override fun configure(task: DeleteTask) {
            task.delete(config.srcPath)
            task.doLast {
                try {
                    File(config.srcPath).delete()
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }
        }
    }

    class DeleteTmpFilesCreator(
        override val name: String,
        private val project: Project,
        private val config: Config
    ) : TaskCreator<DeleteTask> {
        override val type: Class<DeleteTask>
            get() = DeleteTask::class.java

        override fun configure(task: DeleteTask) {
            task.delete(config.tmpPath)

            task.doLast {
                try {
                    File(config.tmpPath).delete()
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }
        }
    }
}