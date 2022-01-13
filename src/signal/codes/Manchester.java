package signal.codes;

import java.util.ArrayList;

public class Manchester implements Code {

	@Override
	public ArrayList<Ligne> calculSignal(String trame, int wSD, int hSD) {
		ArrayList<Ligne> al = new ArrayList<Ligne>();
		
		int cpt = 0;
		char previousBit = 'Ø';
		
		for(char bit : trame.toCharArray()) {
			
			
			if(bit == previousBit) {
				if(bit == '1') {
					al.add(new Ligne(	cpt * wSD,
										hSD,
										cpt * wSD,
										0));
				}else {
					al.add(new Ligne(	cpt * wSD,
										0,
										cpt * wSD,
										hSD));
				}
				
			}
			
			if(bit == '1'){
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
			
			if(bit == '0'){
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
			
			previousBit = bit;
			cpt++;
		}
		
		return al;
	}

}
