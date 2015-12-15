package computerLeauge;

import java.util.Scanner;

public class CHMOD
{
	/**
	 * User, Group, and Other Permissions.
	 */
	Permission[] permissionSet = {new Permission(),new Permission(),new Permission()};
	String inType = "bin";
	
	public CHMOD(String a, String b, String c){
		if(a.contains("r")||a.contains("w")||a.contains("x")||a.contains("x")){
			inType = "char";
		}
		else if(a.length() == 1){
			inType = "int";
		}
		
		if(inType == "bin"){
			permissionSet[0].setBinary(a);
			permissionSet[1].setBinary(b);
			permissionSet[2].setBinary(c);
		}
		else if(inType == "int"){
			permissionSet[0].setInteger(Integer.parseInt(a));
			permissionSet[1].setInteger(Integer.parseInt(b));
			permissionSet[2].setInteger(Integer.parseInt(c));
		}
		else{
			permissionSet[0].setCharacter(a);
			permissionSet[1].setCharacter(b);
			permissionSet[2].setCharacter(c);
		}
	}
	
	public void printOtherTypes(){
		if(inType == "bin"){
			for(Permission p: permissionSet){
				System.out.format("%d",p.getInteger());
			}
			System.out.print(" and ");
			for(Permission p: permissionSet){
				System.out.format("%s,",p.getCharacter());
			}
		}
		else if(inType == "int"){
			for(Permission p: permissionSet){
				System.out.printf("%s,",p.getBinary());
			}
			System.out.print(" and ");
			for(Permission p: permissionSet){
				System.out.format("%s,",p.getCharacter());
			}
		}
		else{
			for(Permission p: permissionSet){
				System.out.printf("%s,",p.getBinary());
			}
			System.out.print(" and ");
			for(Permission p: permissionSet){
				System.out.format("%d",p.getInteger());
			}
		}
		System.out.println();
	}
	
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		while(true){
			CHMOD chmod = new CHMOD(in.nextLine(),in.nextLine(),in.nextLine());
			chmod.printOtherTypes();
		}
	}
	
	public static class Permission{
		private boolean[] permissions = new boolean[3];
		
		public void setBinary(String a){
			for(int x = 0; x <= 2; x++){
				permissions[x] = a.charAt(x) == '1';
			}
		}
		
		public void setInteger(int a){
			String temp = Integer.toBinaryString(a);
			if(a <= 1)
				temp = "00"+temp;
			else if(a <= 3)
				temp = "0"+temp;
			setBinary(temp);
		}
		
		public void setCharacter(String a){
			for(int x = 0; x <= 2; x++){
				permissions[x] = a.charAt(x) != '-';
			}
		}
		
		public String getBinary(){
			String temp = "";
			for(boolean e :permissions)
				if(e)
					temp += "1";
				else
					temp += "0";
			return temp;
		}
		
		public int getInteger(){
			return Integer.parseInt(getBinary(),2);
		}
		
		public String getCharacter(){
			String temp = "";
			if(permissions[0])
				temp += "r";
			else
				temp += "-";
			if(permissions[1])
				temp += "w";
			else
				temp += "-";
			if(permissions[2])
				temp += "x";
			else
				temp += "-";
			return temp;
		}
	}
}
