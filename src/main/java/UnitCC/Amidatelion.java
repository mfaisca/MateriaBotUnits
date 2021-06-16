package UnitCC;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Amidatelion")
public class Amidatelion extends Unit{
	public Amidatelion() {
		super("Amidatelion", "ami", "amidalion", "amy", "goldenrod", "golden rod");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{8819};
				break;
			case HP:
				ids = new int[]{8820};
				break;
			case S1:
				ids = new int[]{8483};
				break;
			case S2:
				ids = new int[]{8487};
				break;
			case EX:
				ids = new int[]{8818};
				break;
			case AA:
				ids = new int[]{8492};
				break;
			case LD:
				switch(region) {
					case "GL":
						ids = new int[]{8917}; break;
					case "JP":
						ids = new int[]{12019}; break;
				}
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
			case 8483: //S1
				ret.fixMissingAuraAilment(1578, 676, Ailment.EffectType.E8, Ailment.Target.Party);
				ret.addStaticHit("+50% BRV Damage when hitting weakness", 0);
				break;
			case 8818: //EX
				ret.removeAilmentById(1562);
				ret.removeHitDataById(9109);
				ret.addStaticHit("+50% BRV Damage when hitting weakness", 0);
				ret.addStaticHit("+5/10/20/30/40/50% Stolen BRV Overflow based on number of debuffs on target");
			case 8487:
				ret.addStaticHit("+50% BRV Damage when hitting weakness", 0);
				break;
			case 8492: //AA
				ret.fixMissingAuraAilment(1650, 677, Ailment.EffectType.E1, Ailment.Target.Party);
				break;
			case 8917: //LD
				ret.fixMissingAuraAilment(1584, 632, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1584, 633, Ailment.EffectType.E8, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1584, 634, Ailment.EffectType.E4, Ailment.Target.Party);
				break;
		}
		return ret;
	}
}