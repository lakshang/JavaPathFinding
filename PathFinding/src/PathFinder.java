import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;




public class PathFinder {

	
	public static void main(String[] args) {
		
		String[][] lines = null;
		try {
		File dir = new File(".");
		File fin = new File(dir.getCanonicalPath() + File.separator + "input.txt"); // small map
		//File fin = new File(dir.getCanonicalPath() + File.separator + "large_map.txt"); // large map
		 
		lines =	readFile2(fin);
		
		
		
		
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
		//System.out.println ("distance " +distance(1, 4, 0,4));
		//System.out.println("terrain cost " + CostofTerrain('^'));
		//getWalkableChoices(lines);
		
		StartoGoal(lines);
	}
	
	private static String [][] readFile2(File fin) throws IOException {
		// Construct BufferedReader from FileReader
		BufferedReader br = new BufferedReader(new FileReader(fin));
	 
		String line = null;
		//String[] line1 = null;
		String[][] lines = null;
		lines = new String[5][5]; // small map
		//lines = new String[50][50]; // large map
		
		int count = 0;
		while ((line = br.readLine()) != null) {
		//	System.out.println(line);
			
			//line1 = new String[line.length()];
			for (int i = 0; i<lines.length; i++) {
				
				
				///line1[i] = line.substring(i, i+1);
				lines[count][i] = line.substring(i, i+1);
			//	System.out.println(line.substring(i,i+1));
	
			}
			
			//lines[count] = line1;
			
			
			
			count++;
		
		}
		
		for (int j=0; j<lines.length;j++) {
			
			int count1 = lines[j].length;
			
			int count2 = 0;
			while (count2<count1 ) {
				
				
				if (lines[j][count2]!=null) {
				if (lines[j][count2].equals("l"))
					System.out.print("#");
				else
				System.out.print(lines[j][count2]);
					
				}
				count2++;
				
			}
			System.out.println();
			
		}
	 
		br.close();
		
		return lines;
	}
	
	
	private static int CostofTerrain(char terrain_type) {
		
		int temp = 0;
		
		if (terrain_type == '@'||terrain_type == '.'||terrain_type == 'X')
			temp = 1;
		else if (terrain_type == '*')
			temp = 2;
		else if (terrain_type == '^')
			temp = 3;
		else {
			//System.out.println("System could not find a matching terrain!");
		}
		return temp;
		
	}
	
	//step1
private static List<Coordinate> searchForWalkableChoices(String[][] lines , Coordinate start_index) {
		
		int max = lines.length;
	
		List<Coordinate> coordinates = new ArrayList<Coordinate>();
		
		int[] temp = null;
		
		
		
		int x1 =start_index.getX();
		int y1 = start_index.getY();
		
		Coordinate right_tile = new Coordinate(x1+1,y1);
		Coordinate diagonal_tile = new Coordinate(x1+1,y1+1);
		Coordinate bottom_tile = new Coordinate(x1,y1+1);
		/*Coordinate right_tile = new Coordinate(x1,y1+1);
		Coordinate diagonal_tile = new Coordinate(x1,y1);
		Coordinate bottom_tile = new Coordinate(x1+1,y1);*/
		
		
			if (right_tile.getX() < max) {    //checking to see that tile doesnt run out of index
				
				// check if the neighbour tile on the right is walkable	
				char tile = lines[right_tile.getY()][right_tile.getX()].charAt(0);		
				
				if (CostofTerrain(tile)>0) { 
					
					coordinates.add(new Coordinate(right_tile.getY(),right_tile.getX()));
				}
				
				
				  //check if the neighbour tile in-between the right and bottom tiles is walkable
				 tile = lines[diagonal_tile.getY()][diagonal_tile.getX()].charAt(0);
				 
				 if (CostofTerrain(tile)>0) { 
						
						coordinates.add(new Coordinate(diagonal_tile.getY(),diagonal_tile.getX()));
					}
				 
				// check if the neighbour tile on the botton is walkable
				 tile = lines[bottom_tile.getY()][bottom_tile.getX()].charAt(0);
				 
				 if (CostofTerrain(tile)>0) { 
						
						coordinates.add(new Coordinate(bottom_tile.getY(),bottom_tile.getX()));
					}
				 
				
				
				}
			
			return coordinates;
			
			}
		
//	}
		
		
				
	private static List<int[]> getWalkableChoices(String[][] lines) {
		
		
		List<int[]> temp2 = new ArrayList<int[]>();
		
		int[] temp = null;
		
		
		
		System.out.println("test");
		
		for (int i = 0; i<lines.length; i++) {
			
			
			for (int j = 0; j< lines[i].length; j++) {
				
				char terrian = lines[i][j].charAt(0);
				
				if (CostofTerrain(terrian)>0) {
					
				temp = new int[2];
				System.out.print(lines[i][j]+" ");
				
				temp[0] = i;
				temp[1] = j;
				temp2.add(temp);
				}
				
			}
			
			System.out.println("");
		}
	
		return temp2;
		
	}
	
	//step2
	private static int costSoFar() {
		int temp = 0;
		
		return temp;
	}
	
	private static int costConsidered() {
		
		int temp = 0;
		
		return temp;
	}
	
	private static int cost_so_far;
	
	private static int CostOfMovement(int cost_so_far, int cost_considered ) {
	
			int temp = 0;
			
			temp = cost_so_far + cost_considered;
		
		return temp;
	}
	

	private static int[] CostOfMovementArray (String lines[][],List walkable_choices, int cost_so_far) {
		
		
		int temp[] = null;
		temp = new int[walkable_choices.size()];
		
		int cost_considered = 0;
		for(int i=0; i<walkable_choices.size(); i++) {
			
			Coordinate tile = (Coordinate)walkable_choices.get(i);
			
			char terrain = lines[tile.getX()][tile.getY()].charAt(0);

				cost_considered = CostofTerrain(terrain);
		        
		     temp[i] =   CostOfMovement(cost_so_far, cost_considered);
		        
		        System.out.println("cost of movement "+CostOfMovement(cost_so_far, cost_considered));
		    }
		return temp;
		}
		
	
	//step3
	private static int distance(int x1, int x2, int y1, int y2) {
		
		
		int temp = 0;
		
		//temp = (x1 - x2) + (y1 - y2);
		temp = Math.abs(x1 - x2) + Math.abs(y1 - y2);
		
		return temp;
	}
	
	
	private static Coordinate goal_index;
	private static Coordinate start_index;
	
	private static int tileScore(int distance, int cost_of_movement) {
		
		int temp = 0;
		
		temp = distance + cost_of_movement;
		
		return temp;
	}
	
	
	private static int[] tileScoreArray(int[] cost_of_movement, List<Coordinate> walkable_choices , Coordinate goal_index  ) {
		
		int[] temp = null;
		
		temp = new int[walkable_choices.size()];
		for (int i = 0; i<walkable_choices.size(); i++) {
		
			Coordinate choice_tile =(Coordinate)walkable_choices.get(i);
			int x1 = choice_tile.getX();
			int x2 = goal_index.getX();
			int y1 = choice_tile.getY();
			int y2 = goal_index.getY();
			
			int tile_distance = distance(x1, x2, y1, y2);
			
			int tile_score = tileScore(tile_distance, cost_of_movement[i]);
			
			temp[i] = tile_score;
			
		}
		
		return temp;
		
	}
	
	
	//step4
	
	private static int BestTileScore( int[] tile_score_array ) {
		
		  int smallest_score = Integer.MAX_VALUE;
		  
		  
		  for (int i = 0; i<tile_score_array.length; i++) {
			  
			  if (smallest_score > tile_score_array[i]) {
				  
				  smallest_score = tile_score_array[i];
			  }
		  }
	
		return smallest_score;
		
	}
	
	
	private static Coordinate BestTileIndex( int[] tile_score_array, List<Coordinate> walkable_choices) {
		
		 int smallest_score = Integer.MAX_VALUE;
		 Coordinate score_index = new Coordinate();
		  	  
		  for (int i = 0; i<tile_score_array.length; i++) {
			  
			  if (smallest_score > tile_score_array[i]) {
				  
				  smallest_score = tile_score_array[i];
				  
				  score_index = (Coordinate)walkable_choices.get(i);
				  
			  }
		  }
	
		return score_index;
		
	}
	
	//step 5
	
	private static void StartoGoal(String lines[][]) {
		
		int best_tile_score = 0;
		start_index = new Coordinate(0,0);
		//start_index = new Coordinate(1,0);
		goal_index = new Coordinate(lines.length,lines.length);
		//goal_index = new Coordinate(5,5);
		cost_so_far = 0;
		finalRoute  = new ArrayList<Coordinate>();
		
		
		for (int i = 0; i<lines.length; i++) {
		
			//step1
			
		List<Coordinate> walkable_choices = new ArrayList<Coordinate>();
		
		walkable_choices = searchForWalkableChoices(lines, start_index);
		
		//step2
		System.out.println("cost of movement for this index " +i);
		int[] cost_of_movement_array = CostOfMovementArray(lines, walkable_choices, cost_so_far);
	
		
		//step3
		
		int[] tile_score_array =  tileScoreArray(cost_of_movement_array, walkable_choices, goal_index);
		
		//step4
		 best_tile_score = BestTileScore(tile_score_array);
		
		Coordinate best_tile_index =BestTileIndex(tile_score_array, walkable_choices);
		
		cost_so_far = best_tile_score;
		start_index = best_tile_index;
		
		finalRoute.add(best_tile_index);
		
		
		
		
		}
		printRoute(finalRoute, lines);
			
		
	}
	
	private static List<Coordinate> finalRoute;

	private static int[][] best_choice_tiles;
	
	private static void printRoute(List<Coordinate> final_route, String lines[][]) {
		
		lines[0][0] = "#";
		
			for (int i  = 0 ; i < final_route.size() ; i++) {
				
				
				
				Coordinate tile_index = (Coordinate)final_route.get(i);
				lines[tile_index.getX()][tile_index.getY()] = "#";
				
			}
			System.out.println("");
			for (int i = 0; i< lines.length; i++) {
				
				for (int j=0; j< lines.length; j ++) {
					
					System.out.print(lines[i][j] );
				}
				
				System.out.println("");
			}
	}
	
}
