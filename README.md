spring-standalone-sample
========================

# spring-standalone-sample とは

スタンドアロンアプリケーション（デスクトップアプリケーション）にて、Spring を使った DI でオブジェクトを取得するため「だけ」の検証プログラムです。


# 動作環境

## Java

Java のバージョンは次の通りです。

```xml
$ java -version
java version "1.7.0_11"
Java(TM) SE Runtime Environment (build 1.7.0_11-b21)
Java HotSpot(TM) 64-Bit Server VM (build 23.6-b04, mixed mode)
```

ですが、いろいろ都合があって、pom.xml にはコンパイラのバージョンを 1.6 で定義しています。

```xml
<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-compiler-plugin</artifactId>
  <version>3.0</version>
  <configuration>
    <source>1.6</source>
    <target>1.6</target>
    <encoding>UTF-8</encoding>
  </configuration>
</plugin>
```

## Maven

この検証プログラムは Maven を使ってビルドしています。
プログラム作成時に使用した Maven のバージョンは次の通りです。

```xml
$ mvn -v
Apache Maven 3.0.4 (r1232337; 2012-01-17 17:44:56+0900)
Maven home: C:\Users\xxx\maven-3.0.4
Java version: 1.7.0_11, vendor: Oracle Corporation
Java home: C:\Program Files\Java\jdk1.7.0_11\jre
Default locale: ja_JP, platform encoding: MS932
OS name: "windows 7", version: "6.1", arch: "amd64", family: "windows"
```


## Spring

pom.xml を見ればそこにバージョンは書いてありますが。

```xml
<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-core</artifactId>
  <version>3.2.6.RELEASE</version>
</dependency>
<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-context</artifactId>
  <version>3.2.6.RELEASE</version>
</dependency>
```


# プログラムについて

検証プログラムは次の3本です。

- src/main/java/net/tomoyamkung/App.java
- src/main/java/net/tomoyamkung/model/HogeModel.java
- src/main/java/net/tomoyamkung/model/HogeModelImpl.java

それぞれのプログラムの JavaDoc を載せておきます。
どのような役割をもったプログラムなのか何となく分かると思います。


## App.java

```xml
/**
 * 動作確認用の実行クラス。
 * 
 * @author tomoyamkung
 *
 */
```


## HogeModel.java

```xml
/**
 * Context から取得するサンプル Bean のインタフェース。
 * 
 * @author tomoyamkung
 *
 */
```


## HogeModelImpl.java

```xml
/**
 * Context から取得するサンプル Bean クラス。
 * 
 * @author tomoyamkung
 *
 */
```


# applicationContext.xml について

Spring の設定ファイルである applicationContext.xml は次のディレクトリに置いてあります。

- src/main/resouces

このディレクトリはクラスパスが通っています（Maven のデフォルト設定）。

---

上記の `HogeModel.java` と `HogeModelImpl.java` の DI 設定は次のように定義してあります。

```xml
<?xml version="1.0" encoding="UTF-8"?> 
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="  
           http://www.springframework.org/schema/beans classpath:/org/springframework/beans/factory/xml/spring-beans-3.0.xsd
           http://www.springframework.org/schema/context classpath:/org/springframework/context/config/spring-context-3.0.xsd">

	<!-- model -->
	<context:component-scan base-package="net.tomoyamkung.model" />
	<bean id="hogeModel" class="net.tomoyamkung.model.HogeModelImpl" />

</beans>
```


# applicationContext から Bean を取得する方法

ここが本検証プログラムで確認したかった部分です。

手順は次の通り単純です。

1. クラスパス上にある applicationContext.xml を指定して `ClassPathXmlApplicationContext` を生成する
2. `ClassPathXmlApplicationContext#getBean` を使って Bean を取得する

具体的な実装は `App.java` にありますが、コードを載せておきます。

```xml
/**
 * 動作確認用の実行クラス。
 * 
 * @author tomoyamkung
 *
 */
public class App {

	/**
	 * Spring の設定ファイル "applicationContext.xml" のパス。
	 * 
	 * クラスパス上に applicationContext.xml がある場合、このような書き方で参照できる。
	 */
	private static final String SPRING_CONFIG_FILE = "classpath:applicationContext.xml";

	/**
	 * 動作確認用の実行メソッド。
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext(SPRING_CONFIG_FILE);

		HogeModel bean = context.getBean(HogeModel.class); // インタフェースの型を指定して取得する
		bean.printLog("Hello, spring-standalone-sample!!");
		
		context.close(); // close しないと警告が出る
	}
}
```


# 動作方法

1. 本プロジェクトを clone する、もしくはダウンロードする
2. プロジェクトに移動してビルドする
3. `App.java` を実行する

ビルドのコマンドは次の通りです。

```xml
$ mvn clean package
```

`App.java` を実行すると、次のような内容がコンソールに出力されます。

```xml
2014-01-21 00:49:58,145 DEBUG net.tomoyamkung.model.HogeModelImpl.printLog:22 - Hello, spring-standalone-sample!!
```


# 更新履歴

## 2014/01/22

このプロジェクトを JAR に固めて実行できるように pom.xml に必要なプラグインを追加。

0.2 としてタグ付け。

- [Release このプロジェクトを JAR に固めて実行できるように pom.xml に必要なプラグインを追加 · tomoyamkung/spring-standalone-sample](https://github.com/tomoyamkung/spring-standalone-sample/releases/tag/0.2)


## 2014/01/20

検証プログラムをコ作成したのでコミット。

0.1 としてタグ付け。

- [Release 検証プロジェクトをコミット。 · tomoyamkung/spring-standalone-sample](https://github.com/tomoyamkung/spring-standalone-sample/releases/tag/0.1)

