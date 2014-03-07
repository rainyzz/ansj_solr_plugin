#Ansj中文分词器的Solr插件

本项目是著名的中文分词器[Ansj](https://github.com/ansjsun/ansj_seg)在Solr下的插件，支持最新版本的Ansj分词器（1.3以上）和最新版本的Solr（4.3以上）。

##使用

本项目基于Ansj官方的Lucene插件，使用时请将本项目对应的jar包与[Ansj](https://github.com/ansjsun/ansj_seg)项目中的ansj_seg.jar，tree_split.jar和ansj_lucene4_plugin.jar放置在Solr的Web文件夹的lib目录中，上述文件下载地址请访问[作者主页](https://github.com/ansjsun/ansj_seg)。

##Solr中的配置

在schema.xml加入如下配置：
```
	<fieldType name="text_ch" class="solr.TextField" positionIncrementGap="100">
 		<analyzer type="index">
  		 	<tokenizer class="me.rainystars.ansj.solr.plugin.AnsjTokenizerFactory"  isQuery="false"/>
 		</analyzer>
     	<analyzer type="query">
   			<tokenizer class="me.rainystars.ansj.solr.plugin.AnsjTokenizerFactory"/>
 		</analyzer>
   	</fieldType>
```
插件一共支持isQuery，pstemming和words三个参数。

其中的isQuery为true代表使用分词的策略是检索时需要的比较精确的分词方式，false时是建立索引时所需要的比较不精确但是产生词语较多的分词方式。

其中的pstemming是原作者提供的参数，用来判断是否需要处理英文名词的单复数，第三人称等。

其中的words是停止词的路径，在我的使用中Solr服务器所在的目录为```D:\work_solr\example\```,如果把停止词放置在```D:\work_solr\example\solr\collection1\conf```文件夹下，就应该添加参数如下：

```
<tokenizer class="me.rainystars.ansj.solr.plugin.AnsjTokenizerFactory"  isQuery="false" words="solr/collection1/conf/stopwords_ch.txt"/>
```
然后就可以读取文件中的停止词列表，进行停止词过滤，文件请使用utf-8格式。

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

[点击下载](https://www.dropbox.com/s/xsoc4dgg73uept2/ansj_solr_plug-1.0.0.jar)