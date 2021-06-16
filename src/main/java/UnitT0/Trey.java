package UnitT0;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Trey")
public class Trey extends Unit{
	public Trey() {
		super("Trey", "treyski");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{8738};
				break;
			case HP:
				ids = new int[]{8739, 8740, 8741};
				break;
			case S1:
				ids = new int[]{8042, 8043, 8044, 8116};
				break;
			case S2:
				ids = new int[]{8054, 8055, 8056, 8128};
				break;
			case EX:
				ids = new int[]{8733};
				break;
			case AA:
				ids = new int[]{8065};
				break;
			case LD:
				ids = new int[]{11847, 11849};
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
		if(id == 1510) return null;
		Ability ret = super.getSpecificAbility(id);
		switch(ret.getId()) {
			case 8739:
			case 8042:
			case 8054:
				ret.removeHitDataById(8645);
				ret.removeHitDataById(8646);
				ret.removeHitDataById(8647);
				break;
			case 8740:
			case 8741:
				ret.getHitDataById(8645).setArguments(new Integer[] {8739}); break;
			case 8043:
			case 8044:
				ret.getHitDataById(8646).setArguments(new Integer[] {8042}); break;
			case 8055:
			case 8056:
				ret.getHitDataById(8647).setArguments(new Integer[] {8054}); break;
			case 11849:
				ret.getDetails().setMovementCost(30);
				break;
		}
		return ret;
	}
	
	@Override
	public Ailment getSpecificAilment(int id){
		Ailment ret = super.getSpecificAilment(id);
		switch(ret.getId()) {
			case 1508:
				ret.removeEffect(231, 94);
				ret.getEffects().add(0, new Ailment.EffectGrouping("Lower duration at the end of every turn"));
				break;
		}
		return ret;
	}
}