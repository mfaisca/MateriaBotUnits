package Unit02;
import com.materiabot.GameElements.*;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Maria")
public class Maria extends Unit{
	public Maria() { super("Maria"); }
	
	@Override
	public void loadFixGL() {
		this.getSpecificAbility(16326).getAilments().removeIf(a -> a.getId() == 111);
	}
}