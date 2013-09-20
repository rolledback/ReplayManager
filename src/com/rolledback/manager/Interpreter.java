package com.rolledback.manager;

import java.io.File;
import java.util.ArrayList;

public class Interpreter {
        ArrayList<Replay> ReplayList;
        public Interpreter(String directory) {
		ReplayList = new ArrayList<Replay>();
		File dir = new File(directory);
		String[] replayList = dir.list();
		
		if (replayList == null) {
		    System.out.println("ERROR");
		} 
		else {
		    for (int i = 0; i < replayList.length; i++) {
		    	if(replayList[i].endsWith(".wotreplay"))
		    		ReplayList.add(new Replay(replayList[i], i, directory + "//" + replayList[i]));
		    }
		}
                    if(ReplayList.size() == 0 && replayList != null)
			System.out.println("No Replays Found");
        }
}

class Replay {
	String year, month, day, country, tank, map, name, path;
	int number;
	
	public Replay(String name, int number, String path) {
		this.name = name;
		this.number = number + 1;
        this.path = path;
		year = name.substring(0, 4);
		month = name.substring(4, 6);
		day = name.substring(6, 8);
		country = findCountry();
		tank = findTank();
		map = findMap();
	}
	
	public String findCountry() {
		int beginning = 0;
		for(int x = 9; x < name.length(); x ++)
			if(name.charAt(x) == '_') {
				beginning = x + 1;
				break;
			}
		for(int x = beginning; x < name.length(); x++)
			if(name.charAt(x) == '-')
				return name.substring(beginning, x).toUpperCase();
		return null;
	}
	
	public String findTank() {
		int beginning = 0;
		for(int x = 0; x < name.length(); x++)
			if(name.charAt(x) == '-') {
				beginning = x + 1;
				break;
			}
		for(int x = beginning; x < name.length(); x++) 
			if(name.charAt(x) == '_')
				return name.substring(beginning, x);
		return null;
	}
	
	public String findMap() {
		int beginning = 0;
                String temp1 = "";
                char temp2[];
		for(int x = 0; x < name.length(); x++)
			if(name.charAt(x) == '_')
				beginning = x + 1;
		for(int x = beginning; x < name.length(); x++)
			if(name.charAt(x) == '.')
				temp1 = name.substring(beginning, x);
                temp2 = temp1.toCharArray();
                temp2[0] = Character.toUpperCase(temp2[0]);
                temp1 = new String(temp2);
                if(temp1 != null)
                    return temp1;
		return null;
	}
        
        public String getNumber() {
                return "Game: " + number;
        }
        
        public String getDate() {
                return "Date recorded: " + month + "/" + day + "/" + year;
        }
	
        public String getCountry() {
                return "Country played: " + country;
        }
        
        public String getMap() {
                return "Map played: " + map;
        }
        
        public String getTank() {
                return "Tank used: " + tank;
        }
        
        public String partialInfo(int type) {
                if(type == 0)
                    return Integer.toString(number);
                else if(type == 1)
                    return month + "/" + day + "/" + year;
                else if(type == 2)
                    return country;
                else if(type == 3)
                    return tank;
                else if(type == 4)
                    return map;
                else
                    return "";
        }
}