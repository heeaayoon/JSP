//DTO 
package common; //기본패키지 이외의 패키지

public class Person {
	private String name; //private 변수
	private int age;
	
	public Person() {} //기본 생성자 
	
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	//public 게터,세터 메소드들
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
