package Unit5;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Lenna")
public class Lenna extends Unit{
	public Lenna() {
		super("Lenna", "reina");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{8202};
				break;
			case HP:
				ids = new int[]{7900};
				break;
			case S1:
				ids = new int[]{7890, 7891};
				break;
			case S2:
				ids = new int[]{7896, 7897};
				break;
			case EX:
				ids = new int[]{7899};
				break;
			case AA:
				ids = new int[]{5362};
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
	public Ability getSpecificAbility(int id){ //3   6 
		Ability ret = super.getSpecificAbility(id);
		String debuff = null; 
		switch(ret.getId()) {
			case 7890:
				debuff = "3";
			case 7891:
				ret.removeAilmentById(443);
				ret.setMastery(7890, 7891, 1);
				debuff = debuff == null ? "6" : debuff;
				Ailment iD = new Ailment(this);
				iD.setId(12);
				if(ret.getDetails().getAilments().stream().noneMatch(a -> a.getId() == iD.getId())) {
					iD.setMaxStacks(15);
					iD.setTarget(Ailment.Target.ST);
					iD.setRank(0);
					iD.setRate(100);
					iD.setName("Int BRV Down");
					iD.setDuration(2);
					iD.setArgs(new int[] {0});
					iD.getEffects().add(new Ailment.EffectGrouping(4));
					iD.getEffects().get(0).rankData = new String[] {debuff};
					ret.getDetails().getAilments().add(iD);
					ret.addStaticAilmentEffect(12, "Potency increases with stacks");
				}
				break;
			case 7896:
			case 7897:
				ret.setMastery(7896, 7897, 1);
				break;
			case 7899:
				ret.fixMissingAuraAilment(1534, 592, Ailment.EffectType.E3, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1534, 593, Ailment.EffectType.E8, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1534, 594, Ailment.EffectType.E67, Ailment.Target.Party);
				break;
			case 5362:
				ret.fixMissingAuraAilment(1111, 295, Ailment.EffectType.E5, Ailment.Target.Party);
				break;
		}
		return ret;
	}
}