# GradleGitTagsPlugin
*Gradle API | Groovy | Git*

Плагин предоставляет возможность работать с версионностью проекта, присваивать новые теги в локальном репозитории и публиковать их в удаленном репозитории

## Запуск плагина

### Подготовка

1. Склонировать плагин командой
```
   git clone https://github.com/YuliyaRR/GradleGitTagsPlugin.git
```

3. Открыть плагин в IDE и во вкладке Gradle выбрать *publishing -> publishToMavenLocal*. После этого плагин будет доступен в локальном репозитории .m2

### Использование

1. В файле **settings.gradle** добавить следующий блок 
```
pluginManagement {
    repositories {
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
    }
}
```

2. В файле **build.gradle** в блок *plugins* добавить запись
```
id 'plugin.gradle.git.incTags' version '1.0'
```

4. Обновите проект

5. Во вкладке Gradle в разделе **Tasks** появится новый пакет задач *git tags* 

## git tags
Пакет содержит следующие задачи:

* **checkGitIsInstalledAndLocalRepoExist** 
	- задача проверяет установлен ли в системе Git и есть ли локальный репозиторий у проекта
	- в случае отсутствия, в лог выводится сообщение о необходимость установки git или инициализации локального репозитория. Все остальные задачи пропускаются
	
* **checkCommitExistInRepo**
	- задача проверяет есть в системе зафиксированные изменения 
	- в случае отсутствия в лог выводится сообщение о том, что тег не может быть определен и присвоен, все остальные задачи пропускаются
	
* **checkLastCommitHaveTag**
	- задача проверяет присвоен ли текущему коммиту тег
	- в случае, если текущему состоянию уже присвоен тег, то новый тег не присваивается, в лог выводится соответствующее сообщение и последующие задачи defineNextVersion, assignNewVersion либо assignAndPublishNewVersion пропускаются
 	- в случае, если тег не присвоен, в лог выводится информационное сообщение 

* **defineLastVersion**
	- задача определяет последнюю опубликованную версию
	- соответствующее сообщение выводится в лог
	
* **checkUncommittedFiles** 
	- задача проверяет если ли в рабочей директории незакомиченные изменения, подсчитывает их и выводит в лог сообщение с указанием последней версии сборки и количества незакомиченных изменений
	- в случае наличия незакомиченных изменений в рабочей директории присвоение нового тега невозможно
 	- в случае отсутствия тегов, выводиться сообщение о количестве незафиксированных изменений без указания версии проекта
	
    ***Примечание:*** для корректного подсчета незакомиченных изменений необходимо установить следующую глобальную настройку для git:
    ```
  git config --global status.showuntrackedfiles all
    ```

* **defineCurrentBranch**
	- задача определяет текущую ветку и выводит соответствующее сообщение в лог

* **defineNextVersion**
	- задача определяет следующую версию билда исходя из следующих условий:
 
		1. *dev/qa* - инкремент минорной версии
 		2. *stage* - добавить постфикс -rc
  		3.  *master* - инкремент мажорной версии
  		4.  *любая другая ветка* - постфикс -SNAPSHOT
  	   
	- соответствующее сообщение выводится в лог
	
* **assignNewVersion**
	- задача присваивает новый git tag в локальном репозитории
	- соответствующее сообщение выводится в лог
	
* **publishNewVersion**
	- задача проверяет наличие удаленного репозитория и публикует новый тег в нем
 	- в случае отсутствия удаленного репозитория выводится соответствующее сообщение в лог
  	- в случае отсутствия тегов в локальном репозитории выводится соответствующее сообщение в лог
  	  
   ***Примечание:*** задача публикует только последний тег, поэтому для корректной отправки тегов в удаленный репозиторий необходимо вызывать задачу ***assignNewVersion*** однократно перед публикацией тега, либо воспользуйтесь задачей ***assignAndPublishNewVersion***, которая осуществляет присваивание и отправку тега в удаленный репозиторий в комплексе

 * **assignAndPublishNewVersion**
   	 - комплексная задача, включает в себя проверку наличия git в системе, локального и удаленного репозиториев, зафиксированных коммитов в логах, рассчитывает новую версию билда, сохраняет ее в локальном репозитории и отправляет в закрепленный удаленный репозиторий
   	 - фиксация в логах аналогична описанному выше
