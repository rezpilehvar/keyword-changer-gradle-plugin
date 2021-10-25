package ir.irezaa.keywordchanger.tasks

import ir.irezaa.keywordchanger.configs.Config
import ir.irezaa.keywordchanger.internal.tasks.TaskCreator
import org.gradle.api.tasks.Copy
import javax.inject.Inject

abstract class CopyAndFilterTask : Copy() {

    class Creator(override val name: String, private val config: Config) :
        TaskCreator<CopyAndFilterTask> {
        override val type: Class<CopyAndFilterTask>
            get() = CopyAndFilterTask::class.java

        override fun configure(task: CopyAndFilterTask) {
            task.from(config.srcPath)
            task.into(config.dstPath)

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
}