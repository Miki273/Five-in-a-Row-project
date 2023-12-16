import java.awt.*;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Stones implements Serializable {
	private int [][] board = new int[15][15];
	private String player1;
	private String player2;
	
	public Stones() {}
	
	public Stones(Stones other) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				this.setBoardItem(i, j, other.getBoardItem(i, j));
			}
		}
		this.setName(other.getName(1), other.getName(2));
	}
	
	public int getBoardItem(int i, int j) {
		if (i >= 0 && i < board.length && j >= 0 && j < board[i].length)
			return board[i][j];
		return -1;
	}
	
	public void setBoardItem(int i, int j, int color) {
		if (i >= 0 && i < board.length && j >= 0 && j < board[i].length)
			board[i][j] = color;
	}
	
	public void setName(String player1, String player2) {
		this.player1 = player1;
		this.player2 = player2;
	}
	
	public String getName(int num) {
		if (num == 1) {
			return player1;
		}
		if (num == 2) {
			return player2;
		}
		return null;
	}
	
	// display the stones on the board
	public void display(Graphics g) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				// only draw stone if the spot is not empty
				if (board[i][j] != 0) {
					// if the stone is black
					if (board[i][j] == 1) {
						g.setColor(Color.BLACK);
					// if the stone is white
					} else {
						g.setColor(Color.WHITE);
					}
					// draw the stone
					g.fillOval(35*(j+1),35*(i+1),30,30);
					// add outline for white stones for better view
					if (board[i][j] == 2) {
						g.setColor(Color.BLACK);
                        g.drawOval(35*(j+1),35*(i+1),30,30);
					}
				}
			}
		}
	}
	
	// add a stone on the board
	// return True if success, False otherwise
	public boolean addStone(int row, int col, boolean isBlack) {
		// new stone can only be added to empty spot
		if (board[row][col] != 0)
			return false;
		// if spot is empty, determine the color of the stone
		board[row][col] = isBlack ? 1 : 2;
		return true;
	}
	
	// check if the board is full
	public boolean isFull() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == 0)
					return false;
			}
		}
		return true;
	}
	
	// determine if any player win
	public int isWin(int row, int col, boolean isBlack) {
		// check horizontally, vertically, and the diagonals
		return checkH(row,col,isBlack) + checkV(row, col, isBlack)
                + checkX1(row, col, isBlack) + checkX2(row, col, isBlack);
	}
	
	// check horizontally if there are five consecutive stones of same color
	public int checkH(int row, int col, boolean isBlack) {
		int count = 1;
        int currentRow = row;
        int currentCol = col;
        int color = isBlack ? 1 : 2;
        
        // from the input stone to its right
        while(currentCol < 14 && board[currentRow][++currentCol] == color) {
        	count++;
        }
        
        currentRow = row;
        currentCol = col;
        
        // from the input stone to its left
        while(currentCol > 0 && board[currentRow][--currentCol] == color) {
        	count++;
        }
        
        // check if we have five in total
        if (count >= 5) {
        	return color;
        } else {
        	return 0;
        }
	}
	
	// check vertically if there are five consecutive stones of same color
	public int checkV(int row, int col, boolean isBlack) {
		int count = 1;
	    int currentRow = row;
	    int currentCol = col;
	    int color = isBlack ? 1 : 2;
	        
	    // from the input stone to the ones below
	    while(currentRow < 14 && board[++currentRow][currentCol] == color) {
	        count++;
	    }
	        
	    currentRow = row;
	    currentCol = col;
	        
	    // from the input stone to the ones above
	    while(currentRow > 0 && board[--currentRow][currentCol] == color) {
	        count++;
	    }
	        
	    // check if we have five in total
	    if (count >= 5) {
        	return color;
        } else {
        	return 0;
        }
	}
	
	// check the stones from top left to bottom right
	public int checkX1(int row, int col, boolean isBlack){
        int count = 1;
        int currentRow = row;
        int currentCol = col;
        int color = isBlack ? 1 : 2;
        
        // from the input stone to its top left
        while(currentRow > 0 && currentCol > 0 && board[--currentRow][--currentCol] == color){
            count ++;
        }
        
        currentRow = row;
        currentCol = col;
        
        // from the input stone to its bottom right
        while(currentRow < 14 && currentCol < 14 && board[++currentRow][++currentCol] == color){
            count ++;
        }
        
        // check if we have five in total
	    if (count >= 5) {
        	return color;
        } else {
        	return 0;
        }
    }
	
	// check the stones from top right to bottom left
	public int checkX2(int row, int col, boolean isBlack){
	    int count = 1;
	    int currentRow = row;
	    int currentCol = col;
	    int color = isBlack ? 1 : 2;
	        
	    // from the input stone to its top right
	    while(currentRow > 0 && currentCol < 14 && board[--currentRow][++currentCol] == color){
	        count ++;
	    }
	        
	    currentRow = row;
	    currentCol = col;
	        
	    // from the input stone to its bottom left
	    while(currentRow < 14 && currentCol > 0 && board[++currentRow][--currentCol] == color){
	        count ++;
	    }
	    
	    // check if we have five in total
	    if (count >= 5) {
        	return color;
        } else {
        	return 0;
        }
	}
}
