package edu.sunyulster.cinas54.minefinder;
import java.security.SecureRandom;


public class Board {
	private boolean field[][];
	private int size;
	private int mines;
	
	public Board(int s) {
		this.size = s;
		mines = Math.round(((s*s) / 8) + ((s*s) / 16));
		field = new boolean[s][s];
		SecureRandom RNG = new SecureRandom();
		
		while (mines > 0) {
			int x = RNG.nextInt(size);
			int y = RNG.nextInt(size);
			
			if (field[x][y]) {
				continue;
			} else {
				field[x][y] = true;
				mines--;
			}
		}
		
		
		
	}
	
	public Boolean getIsMine(int x, int y) {
		return field[x][y];
	}
	
	public Integer getNumber(int x, int y) {
		if (getIsMine(x,y)) return null;
		
		int answer = 0;
		
		
		//corners
		if (x==0 && y==0) {
			if (field[x+1][y]) answer++;
			if (field[x][y+1]) answer++;
			if (field[x+1][y+1]) answer++;
		}
		else if (x==0 && y==size-1) {
			if (field[x][y-1]) answer++;
			if (field[x+1][y-1]) answer++;
			if (field[x+1][y]) answer++;
		}
		else if (x==size-1 && y==0) {
			if (field[x-1][y]) answer++;
			if (field[x-1][y+1]) answer++;
			if (field[x][y+1]) answer++;
		}
		else if (x==size-1 && y==size-1) {
			if (field[x-1][y]) answer++;
			if (field[x-1][y-1]) answer++;
			if (field[x][y-1]) answer++;
		}
		//sides
		else if (x==0) {
			if (field[x][y-1]) answer++;
			if (field[x+1][y-1]) answer++;
			if (field[x+1][y]) answer++;
			if (field[x+1][y+1]) answer++;
			if (field[x][y+1]) answer++;
		}
		else if (x==size-1) {
			if (field[x][y-1]) answer++;
			if (field[x-1][y-1]) answer++;
			if (field[x-1][y]) answer++;
			if (field[x-1][y+1]) answer++;
			if (field[x][y+1]) answer++;
		}
		else if (y==0) {
			if (field[x-1][y]) answer++;
			if (field[x-1][y+1]) answer++;
			if (field[x][y+1]) answer++;
			if (field[x+1][y+1]) answer++;
			if (field[x+1][y]) answer++;
		}
		else if (y==size-1) {
			if (field[x-1][y]) answer++;
			if (field[x-1][y-1]) answer++;
			if (field[x][y-1]) answer++;
			if (field[x+1][y-1]) answer++;
			if (field[x+1][y]) answer++;
		}
		//non-side squares
		else {
			if (field[x-1][y-1]) answer++;
			if (field[x][y-1]) answer++;
			if (field[x+1][y-1]) answer++;
			if (field[x-1][y]) answer++;
			if (field[x+1][y]) answer++;
			if (field[x-1][y+1]) answer++;
			if (field[x][y+1]) answer++;
			if (field[x+1][y+1]) answer++;
		}
		
		return answer;
		
	}
	
	public int getSize() {
		return this.size;
	}
}
