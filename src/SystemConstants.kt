class SystemConstants {
    val OS: String
    val ARCH: String
    val VER: String
    init {
        OS = System.getProperty("os.name")
        ARCH = System.getProperty("os.arch")
        VER = System.getProperty("os.version")
    }
}