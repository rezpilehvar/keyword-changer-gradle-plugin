package ir.irezaa.keywordchanger.tasks

import ir.irezaa.keywordchanger.configs.Config
import ir.irezaa.keywordchanger.internal.tasks.TaskCreator
import org.gradle.api.Project
import org.gradle.api.tasks.Copy

abstract class CopyTask : Copy() {

    class CopyAndFilterCreator(
        override val name: String,
        private val project: Project,
        private val config: Config
    ) : TaskCreator<CopyTask> {
        override val type: Class<CopyTask>
            get() = CopyTask::class.java

        override fun configure(task: CopyTask) {
            task.from(config.srcPath)
            task.into(config.tmpPath)

            config.keywords.forEach { keyword ->
                task.filter { line ->
                    line.replace(
                        keyword.key,
                        keyword.value
                    )
                }

                if (config.renamePathAndFiles) {
                    task.eachFile {
                        it.path = it.path.replace(keyword.key, keyword.value)
                    }
                }
            }
        }
    }

    class CopyBackCreator(
        override val name: String,
        private val config: Config
    ) : TaskCreator<CopyTask> {
        override val type: Class<CopyTask>
            get() = CopyTask::class.java

        override fun configure(task: CopyTask) {
            task.from(config.tmpPath)
            task.into(config.srcPath)
        }
    }
}