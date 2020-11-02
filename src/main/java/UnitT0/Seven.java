package UnitT0;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Seven")
public class Seven extends Unit{
	public Seven() {
		super("Seven", "sevensky");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{6224};
				break;
			case HP:
				ids = new int[]{6229, 12499, 12569};
				break;
			case S1:
				ids = new int[]{6221};
				break;
			case S2:
				ids = new int[]{6223};
				break;
			case EX:
				ids = new int[]{6226};
				break;
			case AA:
				ids = new int[]{4375};
				break;
			case LD:
				ids = new int[]{12159, 12499, 12569};
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
			case 6221: //S1
				ret.getAilmentById(472).setRank(-1);
				ret.getAilmentById(472).removeEffect(3);
				ret.getAilmentById(472).getEffects().get(0).rankData = new String[] {"-10", "-25", "-50"};
			case 6224:
			case 6229:
				ret.fixDelayHitData(6832);
			case 6226:
				ret.addStaticHit("+50% BRV Damage dealt if target has 「**" + this.getSpecificAilment(472).getName() + "**」", 0);
				break;
			case 6223:
				ret.addStaticHit("Cast this ability again if target has 「**" + this.getSpecificAilment(472).getName() + "**」");
				break;
			case 4375:
				ret.getAilmentById(1005).setRank(1);
				break;
			case 12159: //LD 
				ret.getAilmentById(472).setRank(-1);
				ret.getAilmentById(472).removeEffect(3);
				ret.getAilmentById(472).getEffects().get(0).rankData = new String[] {"-10", "-25", "-50"};
				ret.fixMissingAuraAilment(2540, 1091, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2540, 1092, Ailment.EffectType.E67, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2540, 1093, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2540, 1114, null, Ailment.Target.Party);
				ret.addStaticHit("Enables 「**" + this.getSpecificAbility(12499).getName() + "**」 for 1 use");
				break;
			case 12499: //HP+ from LD
				ret.fixRemoveDispels();
				ret.fixDelayHitData(6832);
				ret.fixMissingAuraAilment(2541, 1131, null, Ailment.Target.AoE);
				ret.fixMissingAuraAilment(2541, 1132, null, Ailment.Target.AoE);
				ret.fixMissingAuraAilment(2541, 1133, null, Ailment.Target.Party);
				ret.addStaticHit("Enabled after using 「**" + this.getSpecificAbility(12158).getName() + "**」 for 1 use", 0);
				break;
			case 12569: //Trap
				break;
		}
		return ret;
	}
	
	@Override
	public Ailment getSpecificAilment(int id){
		Ailment ret = super.getSpecificAilment(id);
		switch(id) {
			
		}
		return ret;
	}
}