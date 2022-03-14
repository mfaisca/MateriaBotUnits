package UnitOther;
import com.materiabot.GameElements.*;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Layle")
public class Layle extends Unit{
	public Layle() { super("Layle"); }
	
	@Override
	public void loadFixGL() {
		this.getSpecificAbility(12912).getAilments().removeIf(a -> a.getId() == 111);
	}
}