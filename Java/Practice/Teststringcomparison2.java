package myPractice;

public class Teststringcomparison2 {

	public static void main(String[] args) {
		String s1 = "Ravi";
		String s2 = "Ravi";
		String s4 = "Ashupal";
		String s3 = new String("Ravi");
		System.out.println(s1==s2);// true 
		System.out.println(s1==s3);// false
		System.out.println(s1.compareTo(s2));// 0
		System.out.println(s1.compareTo(s4));//17
		System.out.println(s4.compareTo(s2));//-17

	}

}
