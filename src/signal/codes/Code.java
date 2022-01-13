package signal.codes;

import java.util.ArrayList;

public interface Code {
	public abstract ArrayList<Ligne> calculSignal(String trame, int widthSubDivision, int heightSubDivision);
}
