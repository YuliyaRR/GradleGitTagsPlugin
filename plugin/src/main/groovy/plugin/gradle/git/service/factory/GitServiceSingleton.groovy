package plugin.gradle.git.service.factory

import plugin.gradle.git.service.api.IGitService
import plugin.gradle.git.service.impl.GitServiceImpl

class GitServiceSingleton {

    private volatile static IGitService instance

    private GitServiceSingleton() {
    }

    static IGitService getInstance() {
        if(instance == null) {
            synchronized (GitServiceSingleton.class) {
                if(instance == null) {
                    instance = new GitServiceImpl(VersionCreationServiceSingleton.instance)
                }
            }
        }
        return instance
    }
}
