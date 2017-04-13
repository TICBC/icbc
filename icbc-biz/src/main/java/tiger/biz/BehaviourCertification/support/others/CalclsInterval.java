package tiger.biz.BehaviourCertification.support.others;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalclsInterval {

	public static long diff(String starttime,String endtime)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		Date d1=null;
		Date d2=null;
		long diffSeconds=0,diffMinutes=0,diffHours = 0,diffDays=0;
		try {
            d1 = sdf.parse(starttime);
            d2 = sdf.parse(endtime);
    
            //毫秒ms
            long diff = d2.getTime() - d1.getTime();
    
             diffSeconds = diff / 1000 % 60;
             diffMinutes = diff / (60 * 1000) % 60;
             diffHours = diff / (60 * 60 * 1000) % 24;
             diffDays = diff / (24 * 60 * 60 * 1000);
    /*
            System.out.print("两个时间相差：");
            System.out.print(diffDays + " 天, ");
            System.out.print(diffHours + " 小时, ");
            System.out.print(diffMinutes + " 分钟, ");
            System.out.print(diffSeconds + " 秒.");
    */
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		return diffHours;
	}
	/*
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		Date d=new Date();
		//java.sql.Date sd=new java.sql.Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		String s=sdf.format(d);
		String s1="2016-12-11 10:00:00";
		String s2="2016-12-11 11:00:00";
		Date d1=null;
		Date d2=null;
		try {
            d1 = sdf.parse(s1);
            d2 = sdf.parse(s2);
    
            //毫秒ms
            long diff = d2.getTime() - d1.getTime();
    
            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);
    
            System.out.print("两个时间相差：");
            System.out.print(diffDays + " 天, ");
            System.out.print(diffHours + " 小时, ");
            System.out.print(diffMinutes + " 分钟, ");
            System.out.print(diffSeconds + " 秒.");
    
        } catch (Exception e) {
            e.printStackTrace();
        }
		System.out.println(d1);
		System.out.println(d2);
		System.out.println(s);
		System.out.println(d1);
		System.out.println(d.getTime());
	}
	*/

}
