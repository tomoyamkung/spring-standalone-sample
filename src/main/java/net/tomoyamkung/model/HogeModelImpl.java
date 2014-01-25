package net.tomoyamkung.model;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * Context から取得するサンプル Bean クラス。
 * 
 * @author tomoyamkung
 *
 */
@Service // HogeModel を実装するクラスは本クラスだけなので @Service アノテーションの引数で論理名を指定しなくてもよい
public class HogeModelImpl implements HogeModel {
	
	private static final Logger log = Logger.getLogger(HogeModelImpl.class);

	/* (non-Javadoc)
	 * @see net.tomoyamkung.model.HogeModel#printLog(java.lang.String)
	 */
	@Override
	public void printLog(String message) {
		log.debug(message);
		
	}

}
