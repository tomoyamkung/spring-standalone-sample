package net.tomoyamkung;

import net.tomoyamkung.model.HogeModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 動作確認用の実行クラス。
 * 
 * @author tomoyamkung
 *
 */
@Component
public class App {
	
	/**
	 * Spring の設定ファイル "applicationContext.xml" のパス。
	 * 
	 * クラスパス上に applicationContext.xml がある場合、このような書き方で参照できる。
	 */
	private static final String SPRING_CONFIG_FILE = "classpath:applicationContext.xml";

	/**
	 * <code>HogeModel</code> クラスのインスタンス。
	 * 
	 * <code>@Autowired</code> でインスタンスが設定される。
	 */
	@Autowired
	private HogeModel hoge;

	/**
	 * 動作確認用の実行メソッド。
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext(SPRING_CONFIG_FILE);

		// App クラスの型を指定してインスタンスを取得する
		App app = context.getBean(App.class);
		
		// @Autowired により自動で HogeModel を実装した HogeModelImpl クラスのインスタンスが設定される
		app.hoge.printLog("Hello, spring-standalone-sample!!");
		
		context.close(); // close しないと警告が出る
	}
}
