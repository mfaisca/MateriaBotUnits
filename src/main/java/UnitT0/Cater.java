package UnitT0;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Cater")
public class Cater extends Unit{
	public Cater() {
		super("Cater", "caty");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{8626};
				break;
			case HP:
				ids = new int[]{8628};
				break;
			case S1:
				ids = new int[]{8622};
				break;
			case S2:
				ids = new int[]{8624};
				break;
			case EX:
				ids = new int[]{8631, 8632};
				break;
			case AA:
				ids = new int[]{4043};
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
			case 8626:
			case 8628:
				ret.fixStupidCriticalDamage(1611, 20);
				break;
			case 8631:
				ret.getAilmentById(1416).getEffects().get(0).val_specify = 8632;
				break;
		}
		return ret;
	}
}