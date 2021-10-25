package ir.irezaa.keywordchanger.configs

import org.gradle.api.NamedDomainObjectFactory

class ConfigFactory : NamedDomainObjectFactory<Config> {
    override fun create(name: String): Config {
        return DefaultConfig(name)
    }
}