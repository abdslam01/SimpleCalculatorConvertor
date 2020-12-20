public class Methods{	
	
	public static String toHex(String str, String from) {
		try {
			if(from.equals("Bin")) // Binary to Hexadecimal
				return Integer.toHexString(Integer.parseInt(str, 2)).toUpperCase();
			else { // Decimal to Hexadecimal
				if(Methods.isContains(str, '.'))
					return "inserez un entier pour la convertion";
				else
					return Integer.toHexString(Integer.parseInt(str)).toUpperCase();
				}
		}catch(Exception e) {
			return "";
		}
	}
	public static String toDec(String str, String from) {
		try {
			if(from.equals("Hex")) //Hexadecimal to Decimal
				return Integer.toString(Integer.parseInt(str, 16));
			else //Binary to Decimal
				return Integer.toString(Integer.parseInt(str, 2));
		}catch(Exception e) {
			return "";
		}
	}
	public static String toBin(String str, String from) {
		try {
			if(from.equals("Hex")) // Hexadecimal to Binary
				return Integer.toBinaryString(Integer.parseInt(str,16));
			else //Decimal to Binary
				if(Methods.isContains(str, '.'))
					return "inserez un entier pour la convertion";
				else
					return Integer.toBinaryString(Integer.parseInt(str));
		}catch(Exception e) {
			return "";
		}
	}
	 public static String[] concat(String []A, String []B){
		 String []C= new String[A.length+B.length];
		 int i=0;
		 for(String e : A)
			 C[i++]=e;
		 for(String e : B)
			 C[i++]=e;
		 return C;
	 }
	 public static String cancelLastChar(String str){
		 return str.length()>0 ? str.substring(0,str.length() - 1) : "";
	 }
	 public static boolean isContains(String [] arr, String str) {
		 for(String s : arr)
			 if(s.equals(str))
				 return true;
		 return false;
	 }
	 public static boolean isContains(String str, char c) {
		 return str.indexOf(c) > -1;
	 }
	 public static int returnIndex(String[] arr, String search) {
		 for(int i=0; i<arr.length; i++) {
			 if(arr[i].equals(search))
				 return i;
		 }
		 return -1;
	 }
	 public static String division(String A, String B, String OpType) {
		 String result = null;
		 try {
			 switch(OpType) {
			 	case "Dec":
			 		return Float.toString(Float.parseFloat(A) / Float.parseFloat(B));
			 	case "Bin":
			 		return Integer.toBinaryString(Integer.parseInt(A, 2) / Integer.parseInt(B, 2));
			 	case "Hex":
			 		return Integer.toHexString(Integer.parseInt(A, 16) / Integer.parseInt(B, 16)).toUpperCase();
			 	default:
			 		return "0";
			 }
		 }catch(ArithmeticException e) {
			 result="Exception, Division par 0";
		 }
		 return result;
	 }
	 public static String multiplication(String A, String B, String OpType) {
		 switch(OpType) {
		 	case "Dec":
		 		return Float.toString(Float.parseFloat(A) * Float.parseFloat(B));
		 	case "Bin":
		 		return Integer.toBinaryString(Integer.parseInt(A, 2) * Integer.parseInt(B, 2));
		 	case "Hex":
		 		return Integer.toHexString(Integer.parseInt(A, 16) * Integer.parseInt(B, 16)).toUpperCase();
		 	default:
		 		return "0";
		 }
	 }
	 public static String subtraction(String A, String B, String OpType) {
		 switch(OpType) {
		 	case "Dec":
		 		return Float.toString(Float.parseFloat(A) - Float.parseFloat(B));
		 	case "Bin":
		 		return Integer.toBinaryString(Integer.parseInt(A, 2) - Integer.parseInt(B, 2));
		 	case "Hex":
		 		return Integer.toHexString(Integer.parseInt(A, 16) - Integer.parseInt(B, 16)).toUpperCase();
		 	default:
		 		return "0";
		 }
	 }
	 public static String addition (String A, String B, String OpType) { 		
		 switch(OpType) {
		 	case "Dec":
		 		return Float.toString(Float.parseFloat(A) + Float.parseFloat(B));
		 	case "Bin":
		 		return Integer.toBinaryString(Integer.parseInt(A,2) + Integer.parseInt(B,2));
		 	case "Hex":
		 		return (Integer.toHexString(Integer.parseInt(A,16) + Integer.parseInt(B,16))).toUpperCase();
		 	default:
		 		return "0";
		 }
	 }
	 
}