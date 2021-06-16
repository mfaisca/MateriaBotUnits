package Unit7;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Ability.Details.Hit_Data.Target;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Aerith")
public class Aerith extends Unit{
	public Aerith() {
		super("Aerith", "aeris", "flower girl", "chairith");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) { //43 54
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{7771};
				break;
			case HP:
				ids = new int[]{7772};
				break;
			case S1:
				ids = new int[]{4795};
				break;
			case S2:
				ids = new int[]{4797};
				break;
			case EX:
				ids = new int[]{7770};
				break;
			case AA:
				ids = new int[]{4697};
				break;
			case LD:
				ids = new int[]{11625, 11660};
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
			case 4797:{ //S2
				ret.getDetails().getHits().stream().filter(h -> h.getId() == 2477 || h.getId() == 2478).forEach(h -> h.setTarget(Target.Ally));
				ret.fixMissingAuraAilment(528, 222, Ailment.EffectType.E2, Ailment.Target.Self);
				ret.fixMissingAuraAilment(528, 223, Ailment.EffectType.E8, Ailment.Target.Self);
				ret.fixMissingAuraAilment(528, 224, Ailment.EffectType.E5, Ailment.Target.Self);
				}break;
			case 4697:{ //AA
				ret.fixMissingAuraAilment(523, 212, Ailment.EffectType.E1, Ailment.Target.Party);
				ret.fixMissingAuraAilment(523, 214, Ailment.EffectType.E8, Ailment.Target.Party);
				}break;
			case 7770:{ //EX
				ret.fixMissingAuraAilment(1501, 573, Ailment.EffectType.E1, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1501, 574, Ailment.EffectType.E67, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1501, 575, Ailment.EffectType.E150, Ailment.Target.Party);
				}break;
			case 11625:
				ret.fixMissingAuraAilment(2426, 1016, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2426, 1017, null, Ailment.Target.AoE);
				ret.fixMissingAuraAilment(2426, 1018, null, Ailment.Target.AoE);
				ret.fixMissingAuraAilment(2426, 1019, null, Ailment.Target.AoE);
				ret.addStaticAilmentEffect(2426, "Triggers ?**" + this.getSpecificAbility(11660).getName() + "**」 after attacking");
				break;
			case 11660:
				ret.getDetails().setMovementCost(30);
				ret.addStaticHit("Followed by an HP");
				break;
		}
		return ret;
	}
}