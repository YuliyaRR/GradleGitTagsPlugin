package plugin.gradle.git.service.factory

import plugin.gradle.git.service.api.IVersionCreationService
import plugin.gradle.git.service.impl.VersionCreationServiceImpl

class VersionCreationServiceSingleton {

    private volatile static IVersionCreationService instance

    private VersionCreationServiceSingleton() {
    }

    static IVersionCreationService getInstance() {
        if(instance == null) {
            synchronized (VersionCreationServiceSingleton.class) {
                if(instance == null) {
                    instance = new VersionCreationServiceImpl()
                }
            }
        }
        return instance
    }
}
