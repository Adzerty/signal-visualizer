package signal.codes;

import java.util.ArrayList;

public class NRZ implements Code {

	@Override
	public ArrayList<Ligne> calculSignal(String trame, int wSD, int hSD) {
		ArrayList<Ligne> al = new ArrayList<Ligne>();
		
		int cpt = 0;
		char previousBit = trame.length() != 0 ? trame.charAt(0) : 'Ø';
		
		for(char bit : trame.toCharArray()) {
			
			
			if(bit != previousBit) {
				al.add(new Ligne(	cpt * wSD,
									0,
									cpt * wSD,
									hSD));
			}
			if(bit == '1') {
				al.add(new Ligne( 	cpt * wSD, 
									0, 
									(cpt+1) * wSD, 
									0));
			}
			if(bit == '0') {
				al.add(new Ligne( 	cpt * wSD, 
									hSD, 
									(cpt+1) * wSD, 
									hSD));
			}
			
			previousBit = bit;
			cpt++;
		}
		
		return al;
	}

}
