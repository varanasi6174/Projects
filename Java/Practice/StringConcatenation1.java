package myPractice;

public class StringConcatenation1 {

	public static void main(String[] args) {
		String s = "Ravi"+" Pal";
		 String s1=50+30+" Ravi "+40+40; 
		 String s2 = "Ashu";
		 String s3 = " PAl";
		 String s4 = s2.concat(s3);
		System.out.println(s);// Ravi Pal
        System.out.println(s1);// 80 Ravi 4040
        System.out.println(s4);// Ashu PAl
	}

}
