package net.tomoyamkung;

import net.tomoyamkung.model.HogeModel;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 動作確認用のクラス。
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
