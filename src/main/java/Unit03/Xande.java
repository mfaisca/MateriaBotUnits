package Unit03;
import com.materiabot.GameElements.*;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Xande")
public class Xande extends Unit{
	public Xande() {
		super("Xande");
	}
	
	@Override
	public void loadFix() {
		this.getUpgradedAbilities().removeIf(ua -> ua.getId() == 18 || ua.getId() == 19 || ua.getId() == 20);
	}
}