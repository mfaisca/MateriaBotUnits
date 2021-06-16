package Unit13;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Lightning")
public class Lightning extends Unit{
	public Lightning() {
		super("Lightning", "light", "claire");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{4541, 4542};
				break;
			case HP:
				ids = new int[]{7877, 7878};
				break;
			case S1:
				ids = new int[]{4532, 4534};
				break;
			case S2:
				ids = new int[]{4538, 4540};
				break;
			case EX:
				ids = new int[]{7559};
				break;
			case AA:
				ids = new int[]{7572};
				break;
			case LD:
				ids = new int[]{9624};
				break;
			case BT:
				ids = new int[]{9625};
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
			case 4541:
			case 7877:
				ret.addStaticHit("Enabled when ?**" + this.getSpecificAilment(515).getName() + "**」 is active", 0);
				ret.removeHitDataById(5200);
				ret.removeHitDataById(5201);
				break;
			case 4542:
			case 7878:
				ret.addStaticHit("Enabled when ?**" + this.getSpecificAilment(518).getName() + "**」 is active", 0);
				ret.removeHitDataById(5203);
				ret.removeHitDataById(5204);
				break;
			case 4534: //S1+
				ret.addStaticHit("Enabled when ?**" + super.getSpecificAilment(518).getName() + "**」 has 2 stacks", 0);
				break;
			case 4540: //S2+
				ret.addStaticHit("Enabled when ?**" + super.getSpecificAilment(515).getName() + "**」 has 2 stacks", 0);
				break;
			case 7559:
				ret.fixDelayHitData(3732);
				ret.addStaticHit("Delays target by 1 turn if ?**" + this.getSpecificAilment(515).getName() + "**」 is active (Army of One+)");
				break;
			case 9624:
				ret.fixMissingAuraAilment(1791, 788, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1791, 789, null, Ailment.Target.Party);
				break;
			case 9625:
				ret.fixMissingAuraAilment(1792, 786, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1792, 787, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1792, 855, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1792, 871, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1792, 856, Ailment.EffectType.E67, Ailment.Target.Party);
				break;
		}
		return ret;
	}
	
	@Override
	public Ailment getSpecificAilment(int id){
		Ailment ret = super.getSpecificAilment(id);
		switch(id) {
			case 24:
				ret.setTarget(Ailment.Target.ST);
				break;
		}
		return ret;
	}
}