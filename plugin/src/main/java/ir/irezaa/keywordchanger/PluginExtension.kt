package ir.irezaa.keywordchanger

import ir.irezaa.keywordchanger.configs.ConfigManager
import ir.irezaa.keywordchanger.configs.Config
import org.gradle.api.Action
import org.gradle.api.NamedDomainObjectContainer

abstract class PluginExtension(private val configManager: ConfigManager) {
    var addConfigsToAndroidRelease = false

    fun configs(action: Action<NamedDomainObjectContainer<Config>>) {
        action.execute(configManager.configs)
    }
}