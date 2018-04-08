package com.github.freesk.pja2018javahomework2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;

public class App {
	
	public static Scanner scanner;
	public static ArrayList<Stop> stops;
	
    public static void main( String[] args ) {       
    	scanner = new Scanner(System.in);
    	stops = DummyStationsGenerator.getData(3, 20, 40, 40);
    	
//    	for (Stop s : stops) System.out.println(s);
    	
        System.out.println("Hello, this is a console app that lets you build a public transport line");
        showMainMenu();	
    }
    
    public static int infoMenuSingleAnswer(String message, ArrayList<String> items) {
    	if (message != null)
    		if (!message.isEmpty())
    			System.out.println(message);
    	
    	for (int i = 0; i < items.size(); i++) 
    		System.out.println((i + 1) + " – " + items.get(i));
    	
    	System.out.println("Input an index to proceed");
    	
    	int num = 0;
    	
    	while (num == 0) {
    		try {
    			int n = scanner.nextInt();
    			scanner.nextLine();
    			
    			if (n < 1 || n > items.size())
    				throw new IOException("[Error] invalid index");
    			
    			num = n;    			
    		} catch (IOException e) {
    			System.out.println(e.getMessage());
    		}
    		
    	}
        
        return num - 1;
    }
    
    public static ArrayList<Integer> infoMenuMultipleAnswers(String message, ArrayList<String> items) {
    	if (message != null)
    		if (!message.isEmpty()) System.out.println(message);
    	
    	for (int i = 0; i < items.size(); i++) 
    		System.out.println((i + 1) + " – " + items.get(i));
    	
    	LinkedHashSet<Integer> list = getArrayOfIntegers();
    	ArrayList<Integer> res = new ArrayList<Integer>();
    	
    	for (Integer n : list)
    		if (n > 0 && n < (items.size() + 1)) res.add(n - 1);

        return res;
    }
    
    public static void showMainMenu() {
        ArrayList<String> menuItems = new ArrayList<String>();
        
        menuItems.add("Create a new route");
        menuItems.add("See all the routes");
        menuItems.add("See all the stations");
        menuItems.add("Exit");
        
        int n = infoMenuSingleAnswer(null, menuItems);
        
        if (n == 3) {
        	System.out.println("Bye!");
            System.exit(0);
        } else if (n == 0) {
        	showCreateNewRouteMenu();
        } else if (n == 1) {
        	showRoutesMenu();
        } else if (n == 2) {
        	showStopsMenu();
        }
        	
    }
    
    public static void printStopInfo(ArrayList<Stop> stops) {
		for (Stop s : stops) {
			String message = "";
			ArrayList<Route> rountes = RouteService.getRoutesByStop(s);
			if (rountes.size() > 0) {
				String multiroute = "";
				for (int i = 0; i < rountes.size(); i++) {
					multiroute += rountes.get(i).getName();
					multiroute += (i == (rountes.size() - 1) ? "" : ", ");
				}
				message = " * " + s.getName() + " [" + multiroute + "]";
			} else {
				message = " * " + s.getName();
			}
			System.out.println(message);
		}
    }
    
    public static void showStopsMenu() {
    	printStopInfo(stops);   	
    	showMainMenu();
    }
    
    public static boolean yesOrNo() {
    	String string = "";
    	while (!(string.equals("yes") || string.equals("no"))) {
    		System.out.println("Please, enter yes or no");
    		string = scanner.nextLine();
        }
    	return string.equals("yes") ? true : false;
    }
    
    public static void showRoutesMenu() {
    	ArrayList<String> model = new ArrayList<String>(); 
    	
    	for (Route r : RouteService.routes)
    		model.add(r.getName() + " (" + RouteService.getRouteType(r) + ")");
    	
    	model.add("Exit");
    	
    	int n = infoMenuSingleAnswer("Select a route for options", model);
    	
    	if (n == (model.size() - 1))
    		showMainMenu();
    	else 
    		showOptionsForRoute(RouteService.getRoutById(n));
    }
    
    public static void showOptionsForRoute(Route r) {
    	
    	System.out.println(r.getName() + " (" + RouteService.getRouteType(r) +  ")");
    	
        ArrayList<String> menuItems = new ArrayList<String>();
    
        menuItems.add("Update");
        menuItems.add("Info");
        menuItems.add("Rename");
        menuItems.add("Delete");
        menuItems.add("Exit");
        
        int n = infoMenuSingleAnswer(null, menuItems);
        
        if (n == 4) {
        	showRoutesMenu();
        	// back to the all the routes menu
        } else if (n == 0) {
        	r.assingStops(selectStops(r.getType()));
        	// back the the route menu
        	showOptionsForRoute(r);
        } else if (n == 1) {
        	printRouteInfo(r);
            // back the the route menu
            showOptionsForRoute(r);
        } else if (n == 2) {
        	String name = getRouteNameInput();
            r.setName(name);
            // back the the route menu
            showOptionsForRoute(r);
        } else if (n == 3) {
        	System.out.print("Are you sure? ");
        	boolean answer = yesOrNo();
        	if (answer) {
        		RouteService.removeRoute(r);
        		showRoutesMenu();
        	} else {
        		showOptionsForRoute(r);
        	}	
        } 
        
    }
    
    public static void printRouteInfo(Route r) {
    	printStopInfo(r.stops);	    
    }
    
    public static String getRouteNameInput() {
    	String name = "";

        while (name.isEmpty()) {
        	System.out.println("Input the name of the route (for example R1)");
        	try {
            	String string = scanner.nextLine();
            	name = RouteService.parseName(string);
            } catch (IOException e) {
            	System.out.println(e.getMessage());
            }
        }
        
        return name;
    }
    
    public static int getRouteTypeInput() {
        int type = 0;
        
        while (type == 0) {
        	System.out.println("Input the type of the route (1 for buses and 2 for trains)");
        	try {
            	int n = scanner.nextInt();
            	scanner.nextLine();
            	type = RouteService.parseType(n);
            } catch (IOException e) {
            	System.out.println(e.getMessage());
            }
        }
        
        return type;
    }
    
    public static void showCreateNewRouteMenu() { 
       
    	String name = getRouteNameInput();
        int type = getRouteTypeInput();
        
        System.out.print("Would you like to assing stations? "); 	
        
        boolean answer = yesOrNo();
        
        Route r = null;
        
        if (answer) 
        	r = new Route(type, name, selectStops(type));
        else 
        	r = new Route(type, name);
        
        RouteService.addRoute(r);
        
        System.out.println("Route " + r.getName() + " of type " + RouteService.getRouteType(r) + " has been created with " + r.getNumberOfStops() + " stations");
        
        showMainMenu();
    }
    
    public static ArrayList<Stop> selectStops(int type) {
    	ArrayList<Stop> availableStations = StopService.getStopsByType(stops, type);
    	ArrayList<String> model = new ArrayList<String>();
    	
    	for (Stop s : availableStations) model.add(s.getName());
    	
    	ArrayList<Integer> selectedIndexes = infoMenuMultipleAnswers(null, model);
    	ArrayList<Stop> selectedStations = new ArrayList<Stop>();
    	
    	for (Integer n: selectedIndexes)
    		selectedStations.add(availableStations.get(n));
    	
    	return selectedStations;
    }
   
    
    public static LinkedHashSet<Integer> getArrayOfIntegers() {
    	
    	LinkedHashSet<Integer> hs = new LinkedHashSet<Integer>();

        while (hs.size() == 0) {
        	System.out.println("Input indexes separated by commas. Non existing and repeating indexes will be ignored. The order matters");
        	try {
            	String string = scanner.nextLine();
            	
            	if (string.isEmpty())
            		throw new IOException("[Error] must containt at least one integer");
            	
            	List<String> list = Arrays.asList(string.split(","));
            	
            	ArrayList<Integer> arr = new ArrayList<Integer>(); 
            	
            	try {
            		for (String s : list) {
                		int n = Integer.parseInt(s.trim());
                		arr.add(n);
                	}	
            	} catch (RuntimeException e) {
            		throw new RuntimeException("[Error] cannot parse as integer(s)");
            	}
            	
            	for (Integer k : arr) hs.add(k);
            	            	
            } catch (IOException e) {
            	System.out.println(e.getMessage());
            }
        }
    	
    	return hs;
    }
   
}
