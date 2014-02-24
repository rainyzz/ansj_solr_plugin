#Ansj中文分词器的Solr插件

本项目是著名的中文分词器[Ansj](https://github.com/ansjsun/ansj_seg)在Solr下的插件，支持最新版本的Ansj分词器（1.3以上）和最新版本的Solr（4.3以上）。

##使用

本项目基于Ansj官方的Lucene插件，使用时请将本项目对应的jar包与[Ansj](https://github.com/ansjsun/ansj_seg)项目中的ansj_seg.jar，tree_split.jar和ansj_lucene4_plugin.jar放置在Solr的Web文件夹的lib目录中，上述文件下载地址请访问[作者主页](https://github.com/ansjsun/ansj_seg)。

##Solr中的配置

在schema.xml加入如下配置：
```
	<fieldType name="text_ch" class="solr.TextField" positionIncrementGap="100">
 		<analyzer type="index">
  		 	<tokenizer class="org.ansj.solr.AnsjTokenizerFactory"  isQuery="false"/>
 		</analyzer>
     	<analyzer type="query">
   			<tokenizer class="org.ansj.solr.AnsjTokenizerFactory"/>
 		</analyzer>
   	</fieldType>
```


isQuery代表使用的是检索时需要的比较精确的分词方式还是建立索引时所需要的比较不精确但是产生词语较多的分词方式。

##自定义词库

在Solr的Web文件夹与lib文件夹一起的classes文件夹中新建library.properties文件，写入如下内容，具体目录可以根据自己的需要修改，需要注意的是在Solr中分词的默认的当前路径是Solr的目录，即下面的```D:/work_solr/example/```,如需使用相对路径请做对应的修改，自定义词库的格式请查看[作者主页](https://github.com/ansjsun/ansj_seg)。

```
  	#redress dic file path
  	ambiguityLibrary=library/ambiguity.dic
  	#path of userLibrary this is default library
	userLibrary=D:/work_solr/example/solr-webapp/webapp/WEB-INF/classes/library/default.dic
	#set real name
	isRealName=true
```	
##本项目jar包下载地址

[点击下载](https://www.dropbox.com/s/xidi7m2u3ggsu8p/ansjsolr.jar)