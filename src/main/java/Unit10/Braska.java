package Unit10;
import com.materiabot.GameElements.*;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Braska")
public class Braska extends Unit{
	public Braska() { super("Braska"); }
	
	@Override
	public void loadFixGL() {
		Ability a = new Ability("Overdrive Text");
		a.setId(3673);
		this.getAbilities().put(3673, a);
		a = new Ability("Overdrive Text");
		a.setId(3667);
		this.getAbilities().put(3667, a);
	}
}