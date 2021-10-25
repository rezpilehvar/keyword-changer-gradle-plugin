package ir.irezaa.keywordchanger.configs

import org.gradle.api.Named

interface Config : Named {
    override fun getName(): String

    var srcPath: String

    fun srcPath(srcPath: String)

    var dstPath: String

    fun dstPath(dstPath: String)

    var keywords:MutableMap<String, String>

    var renamePathAndFiles : Boolean
}