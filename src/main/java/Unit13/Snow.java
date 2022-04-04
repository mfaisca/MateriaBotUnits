package Unit13;
import com.materiabot.GameElements.*;
import java.util.stream.Stream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Snow")
public class Snow extends Unit{
	public Snow() { super("Snow"); }
	
	@Override
	public void loadFixGL() {
		Stream.concat(this.getAbilities().values().stream().flatMap(a -> a.getAilments().stream()), 
					  this.getAilments().values().stream())
			.filter(a -> a.getId() == 331)
			.forEach(a -> 
				a.setFakeDesc(new Text("-50% BRV Damage taken" + System.lineSeparator() + "-10% BRV Damage taken per BRV attack taken (up to -50%)")));
	}
}