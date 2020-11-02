package Unit4;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Yang")
public class Yang extends Unit{
	public Yang() {
		super("Yang");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{7690};
				break;
			case HP:
				ids = new int[]{7689};
				break;
			case S1:
				switch(region) {
					case "GL":
						ids = new int[]{7676, 7678}; break;
					case "JP":
						ids = new int[]{12403, 12404}; break;
				}
				break;
			case S2:
				switch(region) {
					case "GL":
						ids = new int[]{7680}; break;
					case "JP":
						ids = new int[]{12408}; break;
				}
				break;
			case EX:
				ids = new int[]{7688};
				break;
			case AA:
				ids = new int[]{5037};
				break;
			case LD:
				ids = new int[]{12217, 12218};
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
			case 7678: //S1+
			case 12404: 
				ret.addStaticHit("Enabled after using 「**" + this.getSpecificAbility(7680).getName() + "**」 for 8 turns", 0);
				ret.getHitDataById(369).getEffect().setEffectValueType(Ability.Details.Hit_Data.BasedOnStat.Stat5.getId());
				break;
			case 7680:
				ret.fixMergeAbility(7682);
				break;
			case 12408:
				ret.fixMergeAbility(12412);
				break;
			case 5037: //AA
				ret.removeHitDataById(5787);
				ret.removeHitDataById(5788);
				ret.removeHitDataById(5789);
				ret.removeHitDataById(5790);
				ret.removeHitDataById(5791);
				ret.removeHitDataById(5792);
				ret.removeHitDataById(5793);
				break;
			case 12217: //LD
				ret.getAilmentById(2551).setRank(0);
				ret.fixMissingAuraAilment(2551, 1099, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2551, 1100, Ailment.EffectType.E67, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2551, 1101, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2551, 1102, null, Ailment.Target.Party);

				ret.fixMissingAuraAilment(2551, 1110, Ailment.EffectType.E1002, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2551, 1111, null, Ailment.Target.Party);
				break;
		}
		return ret;
	}
}