# 大叔基础类库
> 我们一般会把公用的代码放在一个包里，然后其它 项目可以直接使用，就像你使用第三方包一样！

## 仓库
存储包的地方叫做仓库，一般可以分为本地仓库和远程仓库，本地一般用mavenLocal表示，在build.gradle中我们都可以看到，一般在安装包时，会优先从本地仓库下载，这样速度是最快的；远程仓库可以在世界各地使用你的包包，提高了代码的重用，面向对象里叫做`DRY`原则。

## 一 发到本地仓库
```
apply plugin: "maven-publish"
task sourceJar(type: Jar) {
    from sourceSets.main.allJava
    classifier "sources" //定义一个标志 (生成的jar包后面加上sources, 如: jlib-2.2.11-sources.jar)
}

publishing {
    publications {
        maven(MavenPublication) {
            from components.java
            artifact sourceJar
        }
    }

    //定义目标仓库 (包所存放的地方)
    repositories {
        mavenLocal()
    }
}

```
## 私有仓库如果添加用户名和密码
```
repositories {
    maven {

        if (project.version.endsWith('-SNAPSHOT')) {
            url = "快照版本的nexus仓库地址"
        } else {
            url = "release版本的仓库地址"
        }

        credentials {
            username 'nexus仓库用户名'
            password 'nexus仓库密码'
        }
    }
    }
}
```