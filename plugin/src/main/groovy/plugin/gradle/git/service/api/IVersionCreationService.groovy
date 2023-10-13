package plugin.gradle.git.service.api

interface IVersionCreationService {
    createNewVersionInDevOrQABranch(String curVersion, int newMinorVersion)
    createNewVersionInMasterBranch(String curVersion, int newMajorVersion)
    createNewVersionWithPostfix(String oldVersion, String postfix)


}