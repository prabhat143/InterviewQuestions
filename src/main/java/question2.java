public class question2 {

    public static void main(String[] args) {
        String str = "prabhat";

        StringBuilder strbu = new StringBuilder();

        for(int i =0;i<=str.length();i++){
            if(i==str.length()) {
                str = strbu.replace(0,1,"").toString();
                strbu.delete(0,i-1);
                i =0;
            }
            strbu.append(str.charAt(i));
            System.out.println(strbu);
        }
    }
}
