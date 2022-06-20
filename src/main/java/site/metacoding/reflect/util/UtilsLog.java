package site.metacoding.reflect.util;

// 싱글톤
public class UtilsLog {
	// 필드 변수가 제일 위
	private static UtilsLog instance = new UtilsLog();
	// 생성자가 그 다음
	private UtilsLog() {
		
	}
	// 메서드는 마지막
	public static UtilsLog getInsance() {
		return instance;
	}
	
	public void info(String TAG,String msg) {
		System.out.println("=============================INFO " + TAG + msg);
	
	}
}
