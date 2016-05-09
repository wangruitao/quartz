package quartz;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
  
public class Quartz {  
  
    
	private static ResourceBundle resb = null;
	
    public static void main(String[] args) {  
        // TODO Auto-generated method stub  
        SimpleDateFormat DateFormat = new SimpleDateFormat("yyyyMMddHHmmss");  
        Date d = new Date();  
        String returnstr = DateFormat.format(d);          
        
        List<String> preList = new ArrayList<String>();
        
        resb = ResourceBundle.getBundle("job");
        
        Enumeration<String> jobkeys = resb.getKeys();
        while(jobkeys.hasMoreElements()) {
        	String key = jobkeys.nextElement();
        	String pre = key.substring(0, key.lastIndexOf("."));
        	if(!preList.contains(pre)) {
        		preList.add(pre);
        	}
        	
        }
        
      
        try {  
        	if(preList != null && preList.size() > 0) {
        		System.out.println(returnstr+ "【系统启动】");  
        		for(String pref : preList) {
        			String enable = resb.getString(pref + ".enable");
        			if(enable == null || enable.equals("true")) {
        				QuartzManager.addJob(pref, resb.getString(pref + ".job"),  resb.getString(pref + ".cron")); //每2秒钟执行一次  
        			}
        		}
        	}

        }  catch (Exception e) {  
            e.printStackTrace();  
        }  
    } 
    
   
} 
