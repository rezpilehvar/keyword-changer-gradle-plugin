package ir.irezaa.keywordchanger.configs

class DefaultConfig(
    private val name: String
) : Config {
    override fun getName(): String {
        return name
    }

    override var srcPath: String = ""


    override fun srcPath(srcPath: String) {
        this.srcPath = srcPath
    }

    override var dstPath: String = ""

    override fun dstPath(dstPath: String) {
        this.dstPath = dstPath
    }

    override var keywords: MutableMap<String, String> = mutableMapOf()
    override var renamePathAndFiles: Boolean = true
}