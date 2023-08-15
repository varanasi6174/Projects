package myPractice;

public class Teststringcomparison1 {

	public static void main(String[] args) {
		String s1 = "Ravi";
		String s2 = "Ravi";
		String s3 = new String("Ravi");
		String s4 = "Ashu";
		String s5 = "RAVI";
		String s6 = "ASHU";
		System.out.println(s1.equals(s2));//true
		System.out.println(s1.equals(s3));// true
		System.out.println(s1.equals(s4));// false
		System.out.println(s1.equals(s5));// false
		System.out.println(s1.equalsIgnoreCase(s5));// true
		System.out.println(s1.equals(s6));//false
		System.out.println(s4.equalsIgnoreCase(s6));// true
	}

}
