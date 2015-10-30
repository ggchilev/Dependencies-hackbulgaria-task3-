
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Demo {
	
	public static void main(String[] args) {

		Map<String,String> map = new TreeMap<String,String>();
	    map = checkAllPackages();
	    	    
	    String pack = checkDependencies();
	    String dependency = "";
	    	    
	    for (Map.Entry<String, String> entry : map.entrySet()) {
	    	if(entry.getKey().equals(pack))
	    	{
	    	if((entry.getValue()).equals(""))	
	    	{
	    		
	    		System.out.println("Installing "+entry.getKey());
	    		String str = "";
	    		for(int i=1;i<entry.getKey().length()-1;i++)
	    		{
	    			str+=entry.getKey().charAt(i);
	    		}  
	    		try {
					BufferedWriter bw = new BufferedWriter(new FileWriter("E:\\Programming\\Hack-Bulgaria\\installed_modules\\"+str));
				} catch (IOException e) {
					e.printStackTrace();
				}
	      
	    	}
	    	else
	    	{
	    		System.out.println("Installing "+pack);
	    	System.out.println("In order to install "+pack+" we need to install "+entry.getValue());
	    	}
             dependency = entry.getValue();
             if(dependency.contains(","))
             {
            	 pack = "";
            	 String dep = "";
            	 for(int i=0;i<dependency.length();i++)
            	 {
            		 while(dependency.charAt(i)!=',') 
            		 {
            			 if(dependency.charAt(i)==' ')
            			 {
            				 i++;
            			 }
            			 dep+=dependency.charAt(i); 
            			 pack=dep;
            			 if(i==dependency.length()-1)
            			 {
            				 break;
            			 }
            			 i++;
            		 }
            		 dep="";
            		 
            		 for (Map.Entry<String, String> entryTwo : map.entrySet()) 
            		 {
            		    	if(entryTwo.getKey().equals(pack))
            		    	{
            		    		if((entryTwo.getValue()).equals(""))	
            			    	{
            			    		System.out.println("Installing "+entryTwo.getKey());
              			    		String str = "";
            			    		for(int k=1;k<pack.length()-1;k++)
            			    		{
            			    			str+=pack.charAt(k);
            			    		}  
            			    		try {
            							BufferedWriter buf = new BufferedWriter(new FileWriter("E:\\Programming\\Hack-Bulgaria\\installed_modules\\"+str));
            						} catch (IOException e) {
            							// TODO Auto-generated catch block
            							e.printStackTrace();
            						}
            			    	}
            		    		else
            		    		{
            		    			System.out.println("Installing "+pack);
            		    	System.out.println("In order to install "+pack+" we need to install "+entryTwo.getValue());
            		    		}
            		    	pack=entryTwo.getValue();
            		    	}
            		  }            		 
            	 }
             }
             else
             {
            	 pack=dependency;
            	
	    	}
             
		    }
	    }
	    
	    installModules(map);
	    
	    String strPck = "";
	    String str = checkDependencies();
	    for(int i=1;i<str.length()-1;i++)
	    {
	    	strPck+=str.charAt(i);
	    }
	    try {
			BufferedWriter bf = new BufferedWriter(new FileWriter("E:\\Programming\\Hack-Bulgaria\\installed_modules\\"+strPck));
		} catch (IOException e) {
				e.printStackTrace();
		}
	    }	
	
	static void installModules(Map<String,String> map)
	{
		 for (Map.Entry<String, String> entry : map.entrySet()) {
			 String strV ="";
		      for(int i=1;i<entry.getValue().length()-1;i++)
		      {
		    	  strV+=entry.getValue().charAt(i);
		    	 
		      }
		      String strK ="";
		      if(new File("E:\\Programming\\Hack-Bulgaria\\installed_modules\\"+strV).exists()) { 
		    	    for(int j=1;j<entry.getKey().length()-1;j++){
		    	    	strK+=entry.getKey().charAt(j);
		    	    }
		    	    try {
						BufferedWriter bf = new BufferedWriter(new FileWriter("E:\\Programming\\Hack-Bulgaria\\installed_modules\\"+strK));
					} catch (IOException e) {
						e.printStackTrace();
					}
		    	}
		 }
	}
	
	
	static Map checkAllPackages(){
		
		Map<String,String> map = new TreeMap<String,String>();
		String key = "";
		String value = "";
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("E:\\Programming\\Hack-Bulgaria\\all_packages.json"));
			String dependancy = "";
		    while(dependancy != null){
		    	dependancy = br.readLine();
			    
		    	if(!(dependancy.equals("{")) && !(dependancy.equals("}")))
		    	{
		    	
		    		for(int i=0;i<dependancy.length();i++)
		    		{
		    			if(dependancy.charAt(i)==('"'))
		    			{
		    				while(dependancy.charAt(i)!=':')
		    				{
		    				key+=dependancy.charAt(i);
		    				i++;
		    				}
		    			}
		    			
		    			if(dependancy.charAt(i)==('['))
		    			{
		    				while(dependancy.charAt(i)!=']')
		    				{
		    					if(dependancy.charAt(i+1)==']')
		    					{
		    						break;
		    					}
		    					value+=dependancy.charAt(i+1);
		    					i++;
		    				}
		    			}
		    			
		    		}
		    		map.put(key, value);
			    	key = "";
			    	value = "";
		    			
		    	}
		    	
		    	if(dependancy.equals("}")){
		    		break;
		    	}
		    	
		    }
		  
		br.close();
		
		} 
		
		catch (IOException e) {
	
			e.printStackTrace();
		}

		return map;
		
		
	}
	
	static String checkDependencies()
	{
		String dep = "";
		String depValue="";
		boolean isDependancy = false;
		try {
			BufferedReader br = new BufferedReader(new FileReader("E:\\Programming\\Hack-Bulgaria\\dependencies.json"));
			
			while( dep !=null)
			{
				dep = br.readLine();
				if((dep.equals("}"))){
				break;
			}
				
			if(!(dep.equals("{")))
			{

				for(int i=0;i<dep.length();i++)
				{
					if(dep.charAt(i)=='['){
						isDependancy=true;
						break;
					}
				}
				if(isDependancy=true)
				{
				for(int i=0;i<dep.length();i++){
					if(dep.charAt(i)=='[')
					{
                        for(int j=i;j<dep.length()-2;j++){
                        	depValue+=dep.charAt(i+1);
                        	i++;
                        }
					}
				}
				}
			}
			
		}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
			return depValue;
	}
	
	
	}

