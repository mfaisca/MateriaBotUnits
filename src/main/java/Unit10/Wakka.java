package Unit10;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Wakka")
public class Wakka extends Unit{
	public Wakka() {
		super("Wakka", "tubby");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{7766};
				break;
			case HP:
				ids = new int[]{7767};
				break;
			case S1:
				ids = new int[]{7756};
				break;
			case S2:
				ids = new int[]{7759};
				break;
			case EX:
				ids = new int[]{7765};
				break;
			case AA:
				ids = new int[]{7580};
				break;
			case LD:
				ids = new int[]{11908};
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
			case 7580:
				ret.fixMissingAuraAilment(1452, 540, Ailment.EffectType.E1, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1452, 541, Ailment.EffectType.E5, Ailment.Target.Party);
				break;
			case 7765:
				ret.fixMissingAuraAilment(1483, 560, Ailment.EffectType.E4, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1483, 561, Ailment.EffectType.E8, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1483, 562, Ailment.EffectType.E1, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1483, 572, Ailment.EffectType.E5, Ailment.Target.Party);
				break;
			case 11908:
				ret.addStaticAilmentEffect(1939, "Randomly changes to another ?**" + ret.getName() + "**」 debuff at the end of turn", 0);
				ret.addStaticAilmentEffect(1940, "Randomly changes to another ?**" + ret.getName() + "**」 debuff at the end of turn", 0);
				ret.addStaticAilmentEffect(1941, "Randomly changes to another ?**" + ret.getName() + "**」 debuff at the end of turn", 0);
				break;
		}
		return ret;
	}
}