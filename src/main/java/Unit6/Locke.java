package Unit6;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Locke")
public class Locke extends Unit{
	public Locke() {
		super("Locke", "treasure hunter");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{4710};
				break;
			case HP:
				ids = new int[]{4711, 4712, 5784};
				break;
			case S1:
				ids = new int[]{4708, 4709};
				break;
			case S2:
				ids = new int[]{4714};
				break;
			case EX:
				ids = new int[]{5648};
				break;
			case AA:
				ids = new int[]{4705};
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
			case 4709: //S1+
				ret.addStaticHit("Enabled after using ?**" + ret.getName().replace("+", "") + "**」 for 1 use", 0);
			case 4710: //BRV
			case 4705: //AA
				ret.addStaticHit("**Grants only one of the buffs at random**");
				break;
			case 5784: //HP+ Scroll
				ret.addStaticHit("50% Bonus BRV potency if target not targetting self", 0);
				ret.addStaticHit("**This has priority over the above versions**", 0);
			case 4711: //HP+ Genji
			case 4712: //HP+ Miracle
				ret.setName(ret.getName().replace("\\n", " "));
				ret.addStaticHit("Enabled if ?**" + ret.getName().substring(ret.getName().indexOf("(")+1, ret.getName().indexOf(")")) + "**」 is active", 0);
				break;
			case 4708: //S1
				ret.removeHitDataById(3649);
				break;
			case 5648: //EX
				ret.getAilmentById(684).removeEffect(53, 53);
				ret.getAilmentById(684).getEffects().add(new Ailment.EffectGrouping("At end of turn, either:"));
				ret.getAilmentById(684).getEffects().add(new Ailment.EffectGrouping("BRV Regen (40% IBRV) OR"));
				ret.getAilmentById(684).getEffects().add(new Ailment.EffectGrouping("HP Regen (10% Max HP)"));
				break;
		}
		return ret;
	}
}