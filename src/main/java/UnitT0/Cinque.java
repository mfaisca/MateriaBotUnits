package UnitT0;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Cinque")
public class Cinque extends Unit{
	public Cinque() {
		super("Cinque", "cinquey");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{7812};
				break;
			case HP:
				ids = new int[]{7813};
				break;
			case S1:
				ids = new int[]{3984, 3985};
				break;
			case S2:
				ids = new int[]{3992, 3993};
				break;
			case EX:
				ids = new int[]{7811};
				break;
			case AA:
				ids = new int[]{4333};
				break;
			case LD:
				ids = new int[]{};
				break;
			case BT:
				ids = new int[]{};
				break;
			case CA:
				ids = new int[]{};
				break;
		}
		if(ids.length == 0)
			return super.getAbility(type, region);
		else
			return IntStream.of(ids).boxed().map(i -> this.getSpecificAbility(i)).collect(Collectors.toList());
	}
	
	@Override
	public Ability getSpecificAbility(int id){
		Ability ret = super.getSpecificAbility(id);
		switch(ret.getId()) {
			case 3985: //S1+
			case 3993: //S2+
				ret.addStaticHit("Enabled when enemies have a total of 5+ stacks of 「**" + super.getSpecificAilment(927).getName() + "**」", 0);
				break;
			case 7811: //EX
				ret.fixDelayHitData(5126);
				break;
		}
		return ret;
	}
}