package plugin.gradle.git.service.impl

import plugin.gradle.git.service.api.IVersionCreationService

class VersionCreationServiceImpl implements IVersionCreationService{

    private final StringBuilder builder

    VersionCreationServiceImpl() {
        this.builder = new StringBuilder()
    }

    def createNewVersionInDevOrQABranch(String curVersion, int newMinorVersion) {
        def newVer = builder.append(curVersion, 0, (int) curVersion.indexOf(".") + 1)
                .append(newMinorVersion).toString()
        builder.setLength(0)
        return newVer
    }

    @Override
    def createNewVersionInMasterBranch(String curVersion, int newMajorVersion) {
        def newVer = builder.append(curVersion[0])
                .append(newMajorVersion).append(".0").toString()
        builder.setLength(0)
        return newVer
    }

    @Override
    def createNewVersionWithPostfix(String oldVersion, String postfix) {
        oldVersion.contains("-")
                ? builder.append(oldVersion, 0, (int) oldVersion.indexOf("-"))
                : builder.append(oldVersion)
        def newVer = builder.append(postfix).toString()
        builder.setLength(0)
        return newVer
    }
}
