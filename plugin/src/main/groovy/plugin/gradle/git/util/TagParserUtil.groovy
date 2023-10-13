package plugin.gradle.git.util

class TagParserUtil {

    static def parseMajorVersion (String tag) {
        Integer.parseInt(tag.substring(1, tag.indexOf(".")))
    }

    static def parseMinorVersion(String tag) {
        Integer.parseInt(tag.contains("-")
                ? tag.substring((tag.indexOf(".") + 1), tag.indexOf("-"))
                : tag.substring((tag.indexOf(".") + 1)))
    }
}
