package signal.codes;

import java.util.ArrayList;

public class ManchesterDiff implements Code {

	@Override
	public ArrayList<Ligne> calculSignal(String trame, int wSD, int hSD) {
		ArrayList<Ligne> al = new ArrayList<Ligne>();
		
		int cpt = 0;
		boolean previousBitUp = false;
		
		for(char bit : trame.toCharArray()) {
			
			
			if(previousBitUp) {
				if(bit == '1') {
					al.add(new Ligne( 	cpt * wSD, 
										0, 
										cpt * wSD + (wSD/2), 
										0));
		
					al.add(new Ligne( 	cpt * wSD + (wSD/2), 
										0, 
										cpt * wSD + (wSD/2), 
										hSD));
					
					al.add(new Ligne( 	cpt * wSD + (wSD/2), 
										hSD, 
										cpt + (cpt+1) * wSD, 
										hSD));
					previousBitUp = false;
				}else {
					al.add(new Ligne(	cpt * wSD,
										0,
										cpt * wSD,
										hSD));
					
					al.add(new Ligne( 	cpt * wSD, 
										hSD, 
										cpt * wSD + (wSD/2), 
										hSD));
					
					al.add(new Ligne( 	cpt * wSD + (wSD/2), 
										hSD, 
										cpt * wSD + (wSD/2), 
										0));
					
					al.add(new Ligne( 	cpt * wSD + (wSD/2), 
										0, 
										(cpt+1) * wSD, 
										0));
				}
				
			}else {
				
				if(bit == '1') {
					al.add(new Ligne( 	cpt * wSD, 
										hSD, 
										cpt * wSD + (wSD/2), 
										hSD));
					
					al.add(new Ligne( 	cpt * wSD + (wSD/2), 
										hSD, 
										cpt * wSD + (wSD/2), 
										0));
					
					al.add(new Ligne( 	cpt * wSD + (wSD/2), 
										0, 
										(cpt+1) * wSD, 
										0));
					previousBitUp = true;
				}else {
					al.add(new Ligne(	cpt * wSD,
										hSD,
										cpt * wSD,
										0));
					
					al.add(new Ligne( 	cpt * wSD, 
										0, 
										cpt * wSD + (wSD/2), 
										0));
			
					al.add(new Ligne( 	cpt * wSD + (wSD/2), 
										0, 
										cpt * wSD + (wSD/2), 
										hSD));
					
					al.add(new Ligne( 	cpt * wSD + (wSD/2), 
										hSD, 
										cpt + (cpt+1) * wSD, 
										hSD));
				}
				
			}
			
			
			cpt++;
		}
		
		return al;
	}

}
