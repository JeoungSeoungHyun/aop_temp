package site.metacoding.reflect.test;

import java.lang.reflect.Constructor;

import site.metacoding.reflect.domain.Member;

public class MemberTest {

	
	public void newInstance_test() {
		System.out.println("실행됨");
		System.out.println(Member.class.getTypeName());
		
		Class<?> cls = Member.class;
		try {
			Constructor<?> construct = cls.getConstructor();
			Member member = (Member)construct.newInstance();
			member.setId(1);
			member.setUsername("ssar");
			member.setPassword("1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new MemberTest().newInstance_test();
		
		
	}
}
