package Unit12;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Penelo")
public class Penelo extends Unit{
	public Penelo() {
		super("Penelo");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{6785, 6786, 6791, 6792};
				break;
			case HP:
				ids = new int[]{6787, 6788, 6793, 6794};
				break;
			case S1:
				ids = new int[]{6923};
				break;
			case S2:
				ids = new int[]{6927};
				break;
			case EX:
				ids = new int[]{6973};
				break;
			case AA:
				ids = new int[]{6805};
				break;
			case LD:
				ids = new int[]{11035};
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
			case 6785: //Rhumba BRV+
			case 6787: //Rhumba HP+
				ret.addStaticHit("Enabled when ?**" + super.getSpecificAilment(1334).getName() + "**」 is active", 0);
				break;
			case 6786: //Rhumba BRV++
			case 6788: //Rhumba HP++
				ret.addStaticHit("Enabled when ?**" + super.getSpecificAilment(1334).getName() + "**」 has 3 stacks", 0);
				break;
			case 6791: //Waltz BRV+
			case 6793: //Waltz HP+
				ret.addStaticHit("Enabled when ?**" + super.getSpecificAilment(1335).getName() + "**」 is active", 0);
				break;
			case 6792: //Waltz BRV++
			case 6794: //Waltz HP++
				ret.addStaticHit("Enabled when ?**" + super.getSpecificAilment(1335).getName() + "**」 has 3 stacks", 0);
				break;
			case 6923: //S1
				ret.fixMissingAuraAilment(1334, 416, Ailment.EffectType.E1, Ailment.Target.Party);
				ret.getAilmentById(1334).getAuras().remove(417);
				break;
			case 6927: //S2
				ret.fixMissingAuraAilment(1335, 418, Ailment.EffectType.E8, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1335, 419, Ailment.EffectType.E7, Ailment.Target.Party);
				break;
			case 6973: //EX
				ret.removeHitDataById(7372);
				ret.addStaticHit("Followed by an HP attack equal to 750% of own Int BRV", 5);
				ret.addStaticHit("Raises active ?**" + super.getSpecificAilment(1334).getName() + "**」 or ?**" + super.getSpecificAilment(1335).getName() + "**」 stacks by 1");
				break;
			case 11035: //LD
				ret.fixMissingAuraAilment(2009, 938, Ailment.EffectType.E4, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2009, 939, Ailment.EffectType.E5, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2009, 940, null, Ailment.Target.Party);
				break;
			case 6805: //AA
				ret.fixMissingAuraAilment(1337, 420, Ailment.EffectType.E5, Ailment.Target.Party);
				break;
		}
		return ret;
	}
}