package Unit07;
import com.materiabot.GameElements.*;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Tifa")
public class Tifa extends Unit{
	public Tifa() { super("Tifa"); }
	
	@Override
	public void loadFixGL() {
		this.getSpecificAilment(2662).setName(new Text("Attack Change"));
	}
	
	@Override
	public void loadFixJP() {
		loadFixGL();
	}
}