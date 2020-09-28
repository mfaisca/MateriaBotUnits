package Unit11;
import com.materiabot.GameElements.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Shantotto")
public class Shantotto extends Unit{
	public Shantotto() {
		super("Shantotto", "totto");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{8853};
				break;
			case HP:
				ids = new int[]{9159, 9218};
				break;
			case S1:
				ids = new int[]{4060};
				break;
			case S2:
				ids = new int[]{4064};
				break;
			case EX:
				ids = new int[]{6691};
				break;
			case AA:
				ids = new int[]{4039};
				break;
			case LD:
				ids = new int[]{9160, 9218};
				break;
			case BT:
				ids = new int[]{8855};
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
		super.getSpecificAilment(907).setName("Rage");
		super.getSpecificAilment(1688).setName("Rage");
		switch(ret.getId()) {
			case 9160: //LD
				ret.addStaticHit("Enables 「**" + super.getSpecificAbility(9218).getName() + "**」 for 8 turns", 0);
			case 9159: 
				ret.removeHitDataById(9615);
				break;
			case 9218: //HP++++
				ret.addStaticHit("Enabled after using 「**" + super.getSpecificAbility(9160).getName() + "**」 for 8 turns", 0);
			case 4060:
			case 4064:
				ret.addStaticHit("+20% BRV Damage against enemies with 「**" + super.getSpecificAilment(508).getName() + "**」", 0);
				break;
			case 6691:
				ret.getAilmentById(1323).getEffects().clear();
				ret.addStaticAilmentEffect(1323, "Lowers target ALL elemental Resist");
				break;
			case 8855:
				Ailment ail = ret.getAilmentById(1657);
				if(ail.getEffects().stream().noneMatch(e -> e.effectId == 60)) {
					ail.getEffects().add(new Ailment.EffectGrouping(60));
					ret.fixMissingAuraAilment(1657, 675, null, Ailment.Target.Party);
					ret.fixMissingAuraAilment(1657, 719, null, Ailment.Target.Party);
				}
				break;
		}
		return ret;
	}
}