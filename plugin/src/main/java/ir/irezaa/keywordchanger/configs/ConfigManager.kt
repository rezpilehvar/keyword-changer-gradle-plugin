package ir.irezaa.keywordchanger.configs

import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project

class ConfigManager(project: Project) {
    val configs: NamedDomainObjectContainer<Config> =
        project.container(
            Config::class.java,
            ConfigFactory()
        )
}