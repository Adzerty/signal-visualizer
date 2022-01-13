package signal.codes;

import java.util.ArrayList;

public class Miller implements Code {

	@Override
	public ArrayList<Ligne> calculSignal(String trame, int wSD, int hSD) {
		ArrayList<Ligne> al = new ArrayList<Ligne>();
		
		int cpt = 0;
		boolean previousBitUp = false;
		char previousBit = 'Ø';
		
		for(char bit : trame.toCharArray()) {
			
		
			if(bit == '0'){
				if(previousBit == '0') {
					if(previousBitUp) {	
						al.add(new Ligne( 	cpt * wSD, 
											0, 
											cpt * wSD, 
											hSD));
						
						previousBitUp = false;
					}else {
						
						al.add(new Ligne( 	cpt * wSD, 
											hSD, 
											cpt * wSD, 
											0));
						previousBitUp = true;
					}
				}
				
				if(previousBitUp) {
					al.add(new Ligne( 	cpt * wSD, 
										0, 
										(cpt+1) * wSD, 
										0));

				}else{
					al.add(new Ligne( 	cpt * wSD, 
										hSD, 
										(cpt+1) * wSD, 
										hSD));

				}
				
				
			}else {
				if(previousBitUp) {
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
										(cpt+1) * wSD, 
										hSD));
					previousBitUp = false;
				}else {
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
				}
				
			}
			
			previousBit = bit;
			cpt++;
		}
		
		return al;
	}

}
