package Managers;
import java.util.Arrays;
import java.util.List;
import org.plugface.core.annotations.Plugin;
import Unit.*;
import com.materiabot.GameElements.Unit;
import com.materiabot.IO.JSON.UnitParser.OverrideManager;

@Plugin(name = "Override.FF1")
public class Manager1 implements OverrideManager{
	private List<Unit> units = Arrays.asList(			
			new Warrior_of_Light(),
			new Garland()
	);

	@Override
	public Unit getUnit(String name) {
		return units.stream().filter(u -> u.getNicknames().contains(name.toLowerCase())).map(u -> u.clone()).findFirst().orElse(null);
	}
	
	@Override
	public List<Unit> getAllUnits(){
		return units;
	}
}