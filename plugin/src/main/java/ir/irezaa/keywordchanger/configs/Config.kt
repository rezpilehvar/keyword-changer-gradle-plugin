package ir.irezaa.keywordchanger.configs

import org.gradle.api.Named

interface Config : Named {
    override fun getName(): String

    var srcPath: String

    fun srcPath(srcPath: String)

    var tmpPath: String

    fun tmpPath(tmoPath: String)

    var keywords:MutableMap<String, String>

    var renamePathAndFiles : Boolean
}